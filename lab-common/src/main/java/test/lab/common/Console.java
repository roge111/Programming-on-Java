package test.lab.common;

import test.lab.common.client.Address;
import test.lab.common.client.Command;
import test.lab.common.client.Coordinates;
import test.lab.common.client.Location;
import test.lab.common.client.Organization;
import test.lab.common.client.OrganizationType;
import test.lab.common.client.Product;
import test.lab.common.client.ProductComparableProperty;
import test.lab.common.client.UnitOfMeasure;
import test.lab.common.utils.ProductsManager;
import test.lab.common.utils.ReadProps;
import test.lab.common.utils.MsgConsts;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;


public final class Console {
    public static final Scanner SCANNER = new Scanner(System.in);
    private static final List<String> ARGS = new LinkedList<>();
    private static final Map<Command, Runnable> COMMANDS = new HashMap<>();

    static {
        COMMANDS.put(Command.ADD, Console::addProduct);
        COMMANDS.put(Command.UPDATE, () -> update(ARGS.get(0)));
        COMMANDS.put(Command.HELP, Console::help);
        COMMANDS.put(Command.INFO, Console::info);
        COMMANDS.put(Command.SAVE, () -> save(ARGS.get(0)));
        COMMANDS.put(Command.REMOVE_BY_ID, () -> removeById(ARGS.get(0)));
        COMMANDS.put(Command.SHOW, Console::show);
        COMMANDS.put(Command.CLEAR, Console::clear);
        COMMANDS.put(Command.EXECUTE_SCRIPT, () -> executeScript(ARGS.get(0)));
        COMMANDS.put(Command.HEAD, Console::head);
        COMMANDS.put(Command.REMOVE_FIRST, Console::removeFirst);
        COMMANDS.put(Command.REMOVE_GREATER, Console::removeGreater);
        COMMANDS.put(Command.REMOVE_ALL_BY_MANUFACTURE_COST, () -> removeAllByManufactureCost(ARGS.get(0)));
        COMMANDS.put(Command.COUNT_BY_MANUFACTURER, () -> countByManufacturer(ARGS.get(0)));
        COMMANDS.put(Command.COUNT_GREATER_THAN_MANUFACTURER, () -> countGreaterThanManufacturer(ARGS.get(0)));
        COMMANDS.put(Command.EXIT, Console::exit);
    }

    private static final int NAME_INDEX = 0;
    private static final int X_INDEX = 1;
    private static final int Y_INDEX = 2;
    private static final int PRICE_INDEX = 3;
    private static final int PART_NUMBER_INDEX = 4;
    private static final int MANUFACTURE_COST_INDEX = 5;
    private static final int UNIT_OF_MEASURE_INDEX = 6;
    private static final int NAME_ORGANIZATION_INDEX = 7;
    private static final int FULL_NAME_INDEX = 8;
    private static final int TYPE_INDEX = 9;
    private static final int STREET_INDEX = 10;
    private static final int X_LOCATION_INDEX = 11;
    private static final int Y_LOCATION_INDEX = 12;
    private static final int Z_LOCATION_INDEX = 13;
    private static final int NAME_LOCATION_INDEX = 14;

    private Console() {
    }

    public static void main(String[] args) {
        startInteractiveMode();
    }

    public static void startInteractiveMode() {
        System.out.print(MsgConsts.FIRST_MSG);
        try {
            ProductsManager.initList();
        } catch (IllegalArgumentException ignored) {
            System.out.println(MsgConsts.INIT_ERROR_MSG);
            ProductsManager.clear();
        }
        while (true) {
            List<String> lines = Arrays.asList(SCANNER.nextLine().trim().split(" "));

            if (lines.get(0).isEmpty() || lines.get(0) == null) {
                System.out.print(MsgConsts.UNKNOWN_COMMAND_MSG);
                continue;
            }
            try {
                Command command = Command.valueOf(lines.get(0).toUpperCase());
                int argsCount = lines.size() - 1;
                if (!command.isValidArgsCount(argsCount)) {
                    System.out.println(MsgConsts.ARGS_COUNT_MSG);
                    continue;
                }
                ARGS.clear();
                ARGS.addAll(lines.subList(1, lines.size()));
                doCommand(command);
                System.out.print(MsgConsts.COMMAND_INPUT);
            } catch (IllegalArgumentException e) {
                System.out.println(MsgConsts.UNKNOWN_COMMAND_MSG);
            }

        }
    }

    /**
     * Выполняет команду из консоли
     *
     * @param command команда, которую нужно выполнить
     */
    private static void doCommand(Command command) {
        COMMANDS.get(command).run();
    }

    /**
     * Подсчитывает количество продуктов, значение manufacturer которых больше, чем заданное
     *
     * @param manufacturer исходное значение manufacturer
     */
    private static void countGreaterThanManufacturer(String manufacturer) {
        System.out.println(MsgConsts.COUNT_INFO_MSG + ProductsManager.countGreaterThanManufacturer(manufacturer));
    }

    /**
     * @param manufacturer
     */
    private static void countByManufacturer(String manufacturer) {
        System.out.println(MsgConsts.COUNT_INFO_MSG + ProductsManager.countByManufacturer(manufacturer));
    }

    /**
     * @param costString
     */
    private static void removeAllByManufactureCost(String costString) {
        try {
            Integer cost = Integer.parseInt(costString);
            if (ProductsManager.removeAllByManufactureCost(cost)) {
                System.out.println(MsgConsts.SUCCESSFUL_MSG);
            } else {
                System.out.println(MsgConsts.ELEMENT_NOT_FOUND_MSG);
            }
        } catch (NumberFormatException ex) {
            System.out.println(MsgConsts.ERROR_NFE_MSG);
        }
    }

    private static void removeGreater() {
        try {
            ProductComparableProperty pcp = ProductComparableProperty.valueOf(Console.ARGS.get(0));
            Object value;
            switch (pcp) {
                case ID:
                    System.out.println(MsgConsts.GET_ID_MSG);
                    value = SCANNER.nextInt();
                    break;
                case PRICE:
                    System.out.println(MsgConsts.Product.PRICE);
                    value = SCANNER.nextDouble();
                    break;
                case MANUFACTURE_COST:
                    System.out.println(MsgConsts.Product.COST);
                    value = SCANNER.nextDouble();
                    break;
                case ORGANIZATION:
                    value = createOrganization();
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            if (ProductsManager.removeGreater(pcp, value)) {
                System.out.println(MsgConsts.SUCCESSFUL_MSG);
            } else {
                System.out.println(MsgConsts.ELEMENT_NOT_FOUND_MSG);
            }
        } catch (IllegalArgumentException ex) {
            System.out.println(MsgConsts.ERROR_STR_MSG);
        }
    }

    private static void head() {
        if (ProductsManager.getProducts().size() > 0) {
            System.out.println(Objects.requireNonNull(ProductsManager.head()));
        } else {
            System.out.println(MsgConsts.ELEMENT_NOT_FOUND_MSG);
        }
    }

    private static void removeFirst() {
        ProductsManager.removeFirst();
        System.out.println(MsgConsts.REMOVE_FIRST_MSG);
    }

    private static void addProduct() {
        ProductsManager.add(createProduct());
        System.out.println(MsgConsts.ELEMENT_ADD_MSG);
    }


    private static Product createProduct() {
        String name = ReadProps.readProductName();
        float x = ReadProps.readX();
        long y = ReadProps.readY();
        Double price = ReadProps.readProductPrice();
        String partNumber = ReadProps.readProductPartNumber();
        Integer manufactureCost = ReadProps.readManufactureCost();
        UnitOfMeasure unitOfMeasure = ReadProps.readUnitOfMeasure();
        return new Product(name,
                new Coordinates(x, y), price, partNumber, manufactureCost, unitOfMeasure, createOrganization());
    }

    private static Organization createOrganization() {
        String name = ReadProps.readOrganizationName();
        String fullName = ReadProps.readOrganizationFullName();
        OrganizationType type = ReadProps.readOrganizationType();
        return new Organization(name, fullName, type, createAddress());
    }

    private static Address createAddress() {
        String street = ReadProps.readAddressStreet();
        return new Address(street, createLocation());
    }

    private static Location createLocation() {
        Integer x = ReadProps.readXLocation();
        Integer y = ReadProps.readYLocation();
        Float z = ReadProps.readZLocation();
        String name = ReadProps.readTownName();
        return new Location(x, y, z, name);
    }

    /**
     * Удаляет элемент по его id
     *
     * @param idString введенный id
     */
    public static void removeById(String idString) {
        try {
            Integer productId = Integer.parseInt(idString);
            if (ProductsManager.removeById(productId)) {
                System.out.println(MsgConsts.SUCCESSFUL_MSG);
            } else {
                System.out.println(MsgConsts.ERROR_GET_BY_ID_MSG);
            }
        } catch (NumberFormatException ex) {
            System.out.println(MsgConsts.ERROR_NFE_MSG);
        }
    }

    /**
     * Обновляет значение элемента коллекции, id которого равен заданному
     *
     * @param idString введенный id
     */
    public static void update(String idString) {
        try {
            Integer productId = Integer.parseInt(idString);
            if (ProductsManager.checkExist(productId)) {
                ProductsManager.update(createProduct(), productId);
                System.out.println(MsgConsts.SUCCESSFUL_MSG);
            } else {
                System.out.println(MsgConsts.ERROR_NOT_ID_UPDATE_MSG);
            }
        } catch (NumberFormatException e) {
            System.out.println(MsgConsts.ERROR_NFE_MSG);
        }
    }

    public static void info() {
        System.out.println("Тип коллекции – " + ProductsManager.getProducts().getClass().getName());
        System.out.println("Дата инициализации коллекции – " + ProductsManager.getCreationDate());
        System.out.println("Количество элементов в коллекции – " + ProductsManager.getProducts().size());
    }

    public static void help() {
        System.out.println(MsgConsts.HELP_MSG);
    }

    /**
     * Сохраняет коллекцию в файл из переменной окружения
     *
     * @param envVar имя переменной окружения
     */
    public static void save(String envVar) {
        if (ProductsManager.checkEnvVariable(envVar)) {
            ProductsManager.save(envVar);
            System.out.println(MsgConsts.SUCCESSFUL_MSG);
        } else {
            System.out.println(MsgConsts.ERROR_ENV_PATH_MSG);
        }
    }

    public static void show() {
        for (Product p : ProductsManager.getProducts()) {
            System.out.println(p.toString());
        }
    }

    public static void clear() {
        ProductsManager.clear();
        System.out.println(MsgConsts.CLEAR_MSG);
    }

    /**
     * Выполняет команды из файла
     *
     * @param path путь к файлу
     */
    public static void executeScript(String path) {
        boolean executeSuccessful = true;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String strings = br.readLine();
            if (strings == null) {
                return;
            }
            List<String> lines = Arrays.asList(strings.split(" "));
            while (lines.size() > 0) {
                try {
                    Command command = checkAndReturnCommand(lines);
                    if (command == Command.ADD || command == Command.UPDATE) {
                        List<String> params = new LinkedList<>();
                        for (int i = 0; i < Product.INPUT_FIELD_COUNT; i++) {
                            String line = br.readLine();
                            checkLine(line);
                            params.add(line);
                        }
                        addUpdateProductFromScript(command, params, Integer.parseInt(lines.get(1)));
                    } else {
                        executeCommand(command, lines.subList(1, lines.size()));
                    }
                } catch (IllegalArgumentException e) {
                    executeSuccessful = false;
                    break;
                }
                strings = br.readLine();
                if (strings == null) {
                    break;
                }
                lines = Arrays.asList(strings.split(" "));
            }
            printExecuteResult(executeSuccessful);
        } catch (FileNotFoundException e) {
            System.out.println(MsgConsts.FILE_NOT_FOUND_MSG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Выводит результат команды execute_script
     *
     * @param isSuccessful значение, показывающее успешность работы команды
     */
    private static void printExecuteResult(boolean isSuccessful) {
        if (isSuccessful) {
            System.out.println(MsgConsts.SUCCESSFUL_MSG);
        } else {
            System.out.println(MsgConsts.ERROR_EXECUTE_SCRIPT_MSG);
        }
    }

    /**
     * Выполняет команду из файла
     *
     * @param command команда
     * @param lines   строка
     */
    private static void executeCommand(Command command, List<String> lines) {
        ARGS.clear();
        ARGS.addAll(lines);
        doCommand(command);
    }

    /**
     * Проверка команды
     *
     * @param lines строка из файла
     * @return возвращает команду, если все успешно
     * @throws IllegalArgumentException
     */
    private static Command checkAndReturnCommand(List<String> lines) throws IllegalArgumentException {
        if (lines.get(0).isEmpty() || lines.get(0) == null) {
            throw new IllegalArgumentException();
        }
        Command command = Command.valueOf(lines.get(0).toUpperCase());
        if (!command.isValidArgsCount(lines.size() - 1)) {
            throw new IllegalArgumentException();
        }
        return command;
    }

    /**
     * Проверка на пустоту строки
     *
     * @param line строка из файла
     * @throws IllegalArgumentException
     */

    private static void checkLine(String line) throws IllegalArgumentException {
        if (line == null || Command.isValidCommand(line.split(" ")[0])) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Создание продукта
     *
     * @param command   название команды
     * @param params    параметры создания продукта
     * @param productId id продукта
     */
    private static void addUpdateProductFromScript(Command command, List<String> params, Integer productId) {
        if (params.size() == Product.INPUT_FIELD_COUNT) {
            Product product = createProductFromList(params);
            if (product != null) {
                if (command == Command.ADD) {
                    ProductsManager.add(product);
                } else {
                    ProductsManager.update(product, productId);
                }
            }
            throw new IllegalArgumentException();
        }
    }

    /**
     * Создание продукта
     *
     * @param parameters параметры
     * @return возвращает созданный продукт
     */
    public static Product createProductFromList(List<String> parameters) {
        try {
            String name = ReadProps.checkAndReturnValue(parameters.get(NAME_INDEX), String.class, false);
            Float x = ReadProps.checkAndReturnValue(parameters.get(X_INDEX), float.class, false);
            Long y = ReadProps.checkAndReturnValue(parameters.get(Y_INDEX), long.class, false);
            Double price = ReadProps.checkAndReturnValue(parameters.get(PRICE_INDEX), Double.class, false);
            String partNumber = ReadProps.checkAndReturnValue(parameters.get(PART_NUMBER_INDEX), String.class, false);
            Integer manufactureCost = ReadProps.checkAndReturnValue(parameters.get(MANUFACTURE_COST_INDEX), Integer.class, false);
            UnitOfMeasure unitOfMeasure = ReadProps.checkAndReturnValue(parameters.get(UNIT_OF_MEASURE_INDEX), UnitOfMeasure.class, false);
            String nameOrganization = ReadProps.checkAndReturnValue(parameters.get(NAME_ORGANIZATION_INDEX), String.class, false);
            String fullName = ReadProps.checkAndReturnValue(parameters.get(FULL_NAME_INDEX), String.class, false);
            OrganizationType type = ReadProps.checkAndReturnValue(parameters.get(TYPE_INDEX), OrganizationType.class, false);
            String street = ReadProps.checkAndReturnValue(parameters.get(STREET_INDEX), String.class, false);
            Integer xLocation = ReadProps.checkAndReturnValue(parameters.get(X_LOCATION_INDEX), Integer.class, false);
            Integer yLocation = ReadProps.checkAndReturnValue(parameters.get(Y_LOCATION_INDEX), Integer.class, false);
            Float zLocation = ReadProps.checkAndReturnValue(parameters.get(Z_LOCATION_INDEX), Float.class, false);
            String nameLocation = ReadProps.checkAndReturnValue(parameters.get(NAME_LOCATION_INDEX), String.class, false);

            return new Product(name, new Coordinates(x, y), price, partNumber, manufactureCost, unitOfMeasure,
                    new Organization(nameOrganization, fullName, type,
                            new Address(street,
                                    new Location(xLocation, yLocation, zLocation, nameLocation))));
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    public static void exit() {
        System.out.println(MsgConsts.EXIT_MSG);
        System.exit(0);
    }
}
