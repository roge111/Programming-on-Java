package test.lab.common.utils;

import test.lab.common.Console;
import test.lab.common.client.Address;
import test.lab.common.client.Command;
import test.lab.common.client.Coordinates;
import test.lab.common.client.Location;
import test.lab.common.client.Organization;
import test.lab.common.client.OrganizationType;
import test.lab.common.client.Product;
import test.lab.common.client.ProductComparableProperty;
import test.lab.common.client.UnitOfMeasure;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public final class ProductsManager {

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

    private static final String FILE_PATH = "FilePath";
    private LinkedList<Product> products;
    private ZonedDateTime creationDate;

    public ProductsManager() {
    }


    public boolean checkExist(Integer id) {
        return products.stream().anyMatch(p -> p.getId().equals(id));
    }


    public void initList() {
        if (products == null) {
            products = new LinkedList<>();
            load();
            creationDate = ZonedDateTime.now();
        }
    }

    /**
     * @param envVar
     * @return
     */
    public static boolean checkEnvVariable(String envVar) {
        if (System.getenv().containsKey(envVar)) {
            String filePath = System.getenv(envVar);
            File file = new File(filePath);
            return file.exists() && file.isFile() && filePath.toLowerCase().endsWith(".xml");
        }
        return false;
    }

    /**
     * @param product
     */
    public void add(Product product) {
        products.add(product);
    }

    public Integer getNewProductId() {
        if (products.size() > 0) {
            return products.getLast().getId() + 1;
        }
        return 0;
    }

    public Integer getNewOrganizationId() {
        if (products.size() > 0) {
            return products.getLast().getManufacturer().getId() + 1;
        }
        return 0;
    }

    /**
     * @param product
     * @param id
     */
    public void update(Product product, Integer id) {
        products.stream().filter(p -> p.getId().equals(id)).findAny().get().updateProduct(product);
    }

    /**
     * @param productId
     * @return
     */
    public boolean removeById(Integer productId) {
        return products.removeIf(p -> Objects.equals(p.getId(), productId));
    }

    public void clear() {
        products.clear();
    }

    public Product head() {
        return products.size() > 0 ? products.get(0) : null;
    }

    /**
     * @param manufacturer
     * @return
     */
    public int countGreaterThanManufacturer(String manufacturer) {
        return (int) products.stream()
                .filter(p -> p.getManufacturer().toString().compareTo(manufacturer) > 0).count();
    }

    /**
     * @param mfc
     * @return
     */
    public boolean removeAllByManufactureCost(Integer mfc) {
        return products.removeIf(p -> p.getManufactureCost().equals(mfc));
    }

    public int countByManufacturer(String manufacturer) {
        return (int) products.stream()
                .filter(p -> p.getManufacturer().getName().equals(manufacturer)).count();
    }

    /**
     * @param pcp
     * @param value
     * @return
     */
    public boolean removeGreater(ProductComparableProperty pcp, Object value) {
        boolean isSuccessful = false;
            switch (pcp) {
                case PRICE:
                    double price = Double.parseDouble(value.toString());
                    isSuccessful = products.removeIf(p -> {
                        Product p2 = new Product(p);
                        p2.setPrice(price);
                        return p.compareTo(p2) > 0;
                    });
                    break;
                case ID:
                    Integer id = Integer.parseInt(value.toString());
                    isSuccessful = products.removeIf(p -> {
                        Product p2 = new Product(p);
                        p2.setId(id);
                        return p.compareTo(p2) > 0;
                    });
                    break;
                case MANUFACTURE_COST:
                    Integer cost = Integer.parseInt(value.toString());
                    isSuccessful = products.removeIf(p -> {
                        Product p2 = new Product(p);
                        p2.setManufactureCost(cost);
                        return p.compareTo(p2) > 0;
                    });
                    break;
                case ORGANIZATION:
                    Organization organization = (Organization) value;
                    isSuccessful = products.removeIf(p -> {
                        Product p2 = new Product(p);
                        p2.setManufacturer(organization);
                        return p.compareTo(p2) > 0;
                    });
                    break;
                default:
                    break;
            }
        return isSuccessful;
    }

    public void removeFirst() {
        if (products.size() > 0) {
            products.removeFirst();
        }
    }

    /**
     * @param envVar
     */
    public void save(String envVar) {
        String filePath = System.getenv(envVar);
        try (FileOutputStream file = new FileOutputStream(filePath);
             XMLEncoder encoder = new XMLEncoder(file)) {
            encoder.writeObject(products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void load() {
        try {
            String fileName = System.getenv(FILE_PATH);
            if (fileName == null) {
                return;
            }
            File file = new File(fileName);
            if (file.exists() && file.isFile()) {
                try (FileInputStream fileInputStream = new FileInputStream(fileName);
                     XMLDecoder decoder = new XMLDecoder(fileInputStream)) {
                    products = (LinkedList<Product>) decoder.readObject();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getFilePath() throws IOException {
        File file = new File(FILE_PATH);
        if (file.exists() && file.isFile()) {
            return new BufferedReader(new InputStreamReader(new FileInputStream(FILE_PATH))).readLine();
        }
        return null;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public LinkedList<Product> getProducts() {
        return products;
    }

    /**
     * Выполняет команды из файла
     *
     * @param path путь к файлу
     */
    public boolean executeScript(String path) {
        boolean executeSuccessful = true;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String strings = br.readLine();
            if (strings == null) {
                return false;
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
                        Console.CONSOLE.executeCommand(command, lines.subList(1, lines.size()));
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
            return executeSuccessful;
        } catch (FileNotFoundException e) {
            System.out.println(MsgConsts.FILE_NOT_FOUND_MSG);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
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
    private void addUpdateProductFromScript(Command command, List<String> params, Integer productId) {
        if (params.size() == Product.INPUT_FIELD_COUNT) {
            Product product = createProductFromList(params);
            if (product != null) {
                if (command == Command.ADD) {
                    add(product);
                } else {
                    update(product, productId);
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
    public Product createProductFromList(List<String> parameters) {
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

            return new Product(getNewProductId(), name, new Coordinates(x, y), price, partNumber, manufactureCost, unitOfMeasure,
                    new Organization(getNewOrganizationId(), nameOrganization, fullName, type,
                            new Address(street,
                                    new Location(xLocation, yLocation, zLocation, nameLocation))));
        } catch (NumberFormatException ex) {
            return null;
        }
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

}
