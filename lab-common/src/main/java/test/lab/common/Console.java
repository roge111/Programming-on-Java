package test.lab.common;

import test.lab.common.client.Address;
import test.lab.common.client.Command;
import test.lab.common.client.Coordinates;
import test.lab.common.client.Location;
import test.lab.common.client.Organization;
import test.lab.common.client.OrganizationType;
import test.lab.common.client.Product;
import test.lab.common.client.UnitOfMeasure;
import test.lab.common.utils.ProductsManager;
import test.lab.common.utils.ReadProps;
import test.lab.common.utils.MsgConsts;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;


public final class Console {
    public static final Scanner SCANNER = new Scanner(System.in);
    public final List<String> args = new LinkedList<>();
    private final ProductsManager productsManager = new ProductsManager(this);
    private final Map<Command, Runnable> commands = new HashMap<>();

    public Console() {
        commands.put(Command.ADD, this::addProduct);
        commands.put(Command.UPDATE, () -> this.update(args.get(0)));
        commands.put(Command.HELP, this::help);
        commands.put(Command.INFO, this::info);
        commands.put(Command.SAVE, this::save);
        commands.put(Command.REMOVE_BY_ID, () -> this.removeById(args.get(0)));
        commands.put(Command.SHOW, this::show);
        commands.put(Command.CLEAR, this::clear);
        commands.put(Command.EXECUTE_SCRIPT, () -> this.executeScript(args.get(0)));
        commands.put(Command.HEAD, this::head);
        commands.put(Command.REMOVE_FIRST, this::removeFirst);
        commands.put(Command.REMOVE_GREATER, this::removeGreater);
        commands.put(Command.REMOVE_ALL_BY_MANUFACTURE_COST, () -> this.removeAllByManufactureCost(args.get(0)));
        commands.put(Command.COUNT_BY_MANUFACTURER, this::countByManufacturer);
        commands.put(Command.COUNT_GREATER_THAN_MANUFACTURER, this::countGreaterThanManufacturer);
        commands.put(Command.EXIT, Console::exit);
    }

    public static void main(String[] args) {
        Console console = new Console();
        console.startInteractiveMode();
    }

    public void startInteractiveMode() {
        System.out.print(MsgConsts.FIRST_MSG);
        try {
            productsManager.initList();
        } catch (IllegalArgumentException ignored) {
            System.out.println(MsgConsts.INIT_ERROR_MSG);
            productsManager.clear();
        }
        while (true) {
            if (!SCANNER.hasNextLine()) {
                System.out.println(MsgConsts.INPUT_ERROR_MSG);
                return;
            }
            List<String> lines = Arrays.asList(SCANNER.nextLine().trim().split(" "));

            if (lines.get(0).isEmpty() || lines.get(0) == null) {
                System.out.print(MsgConsts.UNKNOWN_COMMAND_MSG);
                continue;
            }
            try {
                Command command = Command.valueOf(lines.get(0).toUpperCase());
                int argsCount = lines.size() - 1;
                if (!command.isValidArgsCount(argsCount) && (command == Command.SAVE && (argsCount != 0 && argsCount != 1))) {
                    System.out.println(MsgConsts.ARGS_COUNT_MSG);
                    continue;
                }
                args.clear();
                args.addAll(lines.subList(1, lines.size()));
                doCommand(command);
                System.out.print(MsgConsts.COMMAND_INPUT);
            } catch (IllegalArgumentException e) {
                System.out.println(MsgConsts.UNKNOWN_COMMAND_MSG);
            }
        }
    }

    private void doCommand(Command command) {
        commands.get(command).run();
    }

    /**
     * Выполняет команду из файла
     *
     * @param command команда
     * @param lines   строка
     */
    public void executeCommand(Command command, List<String> lines) {
        args.clear();
        args.addAll(lines);
        doCommand(command);
    }


    /**
     * Подсчитывает количество продуктов, значение manufacturer которых больше, чем заданное
     *
     */
    public void countGreaterThanManufacturer() {
        Organization organization = createOrganization();
        System.out.println(MsgConsts.COUNT_INFO_MSG + productsManager.countGreaterThanManufacturer(organization));
    }

    public void countByManufacturer() {
        Organization organization = createOrganization();
        System.out.println(MsgConsts.COUNT_INFO_MSG + productsManager.countByManufacturer(organization));
    }

    /**
     * @param costString
     */
    public void removeAllByManufactureCost(String costString) {
        try {
            Integer cost = Integer.parseInt(costString);
            if (productsManager.removeAllByManufactureCost(cost)) {
                System.out.println(MsgConsts.SUCCESSFUL_MSG);
            } else {
                System.out.println(MsgConsts.ELEMENT_NOT_FOUND_MSG);
            }
        } catch (NumberFormatException ex) {
            System.out.println(MsgConsts.ERROR_NFE_MSG);
        }
    }

    public void removeGreater() {
        try {
            Product product = createProduct();
            if (productsManager.removeGreater(product)) {
                System.out.println(MsgConsts.SUCCESSFUL_MSG);
            } else {
                System.out.println(MsgConsts.ELEMENT_NOT_FOUND_MSG);
            }
        } catch (IllegalArgumentException ex) {
            System.out.println(MsgConsts.ERROR_STR_MSG);
        }
    }

    public void head() {
        if (productsManager.getProducts().size() > 0) {
            System.out.println(Objects.requireNonNull(productsManager.head()));
        } else {
            System.out.println(MsgConsts.ELEMENT_NOT_FOUND_MSG);
        }
    }

    public void removeFirst() {
        productsManager.removeFirst();
        System.out.println(MsgConsts.REMOVE_FIRST_MSG);
    }

    public void addProduct() {
        productsManager.add(createProduct());
        System.out.println(MsgConsts.ELEMENT_ADD_MSG);
    }


    private Product createProduct() {
        String name = ReadProps.readProductName();
        float x = ReadProps.readX();
        long y = ReadProps.readY();
        Double price = ReadProps.readProductPrice();
        String partNumber = ReadProps.readProductPartNumber();
        Integer manufactureCost = ReadProps.readManufactureCost();
        UnitOfMeasure unitOfMeasure = ReadProps.readUnitOfMeasure();
        Product p = new Product(productsManager.getNewProductId(), name,
                new Coordinates(x, y), price, partNumber, unitOfMeasure, createOrganization());
        p.setManufactureCost(manufactureCost);
        return p;
    }

    private Organization createOrganization() {
        String name = ReadProps.readOrganizationName();
        String fullName = ReadProps.readOrganizationFullName();
        OrganizationType type = ReadProps.readOrganizationType();
        return new Organization(productsManager.getNewOrganizationId(), name, fullName, type, createAddress());
    }

    private Address createAddress() {
        String street = ReadProps.readAddressStreet();
        return new Address(street, createLocation());
    }

    private Location createLocation() {
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
    public void removeById(String idString) {
        try {
            Integer productId = Integer.parseInt(idString);
            if (productsManager.removeById(productId)) {
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
    public void update(String idString) {
        try {
            Integer productId = Integer.parseInt(idString);
            if (productsManager.update(createProduct(), productId)) {
                System.out.println(MsgConsts.SUCCESSFUL_MSG);
            } else {
                System.out.println(MsgConsts.ERROR_NOT_ID_UPDATE_MSG);
            }
        } catch (NumberFormatException e) {
            System.out.println(MsgConsts.ERROR_NFE_MSG);
        }
    }

    public void info() {
        System.out.println("Тип коллекции – " + productsManager.getProducts().getClass().getName());
        System.out.println("Дата инициализации коллекции – " + productsManager.getCreationDate());
        System.out.println("Количество элементов в коллекции – " + productsManager.getProducts().size());
    }

    public void help() {
        System.out.println(MsgConsts.HELP_MSG);
    }

    /**
     * Сохраняет коллекцию в файл из переменной окружения
     *
     */
    public void save() {
        String envVar = args.size() > 0 ? args.get(0) : null;
        if (envVar == null || ProductsManager.checkEnvVariable(envVar)) {
            productsManager.save(envVar);
            System.out.println(MsgConsts.SUCCESSFUL_MSG);
        } else {
            System.out.println(MsgConsts.ERROR_ENV_PATH_MSG);
        }
    }

    public void show() {
        for (Product p : productsManager.getProducts()) {
            System.out.println(p.toString());
        }
    }

    public void clear() {
        productsManager.clear();
        System.out.println(MsgConsts.CLEAR_MSG);
    }


    /**
     * Выводит результат команды execute_script
     *
     * @param path путь к файлу
     */
    public void executeScript(String path) {
        if (productsManager.executeScript(path)) {
            System.out.println(MsgConsts.SUCCESSFUL_MSG);
        } else {
            System.out.println(MsgConsts.ERROR_EXECUTE_SCRIPT_MSG);
        }
    }

    public static void exit() {
        System.out.println(MsgConsts.EXIT_MSG);
        System.exit(0);
    }
}
