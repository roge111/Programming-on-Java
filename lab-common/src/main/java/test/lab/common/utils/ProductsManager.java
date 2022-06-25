package test.lab.common.utils;

import test.lab.common.Console;
import test.lab.common.client.Command;
import test.lab.common.client.Organization;
import test.lab.common.client.Product;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
//Проверка

public final class ProductsManager {

    private static final String FILE_PATH = "LabFilePath";
    private LinkedList<Product> products;
    private ZonedDateTime creationDate;
    private Console console;

    public ProductsManager() {
    }

    public ProductsManager(Console console) {
        this.console = console;
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
        return 1;
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
    public boolean update(Product product, Integer id) {
        if (checkExist(id)) {
            Optional<Product> op = products.stream().filter(p -> p.getId().equals(id)).findAny();
            if (op.isPresent()) {
                op.get().updateProduct(product);
                return true;
            } else {
                throw new RuntimeException();
            }
        }
        return false;
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

    public int countGreaterThanManufacturer(Organization organization) {
        return (int) products.stream()
                .filter(p -> p.getManufacturer().compareTo(organization) > 0).count();
    }

    /**
     * @param mfc
     * @return
     */
    public boolean removeAllByManufactureCost(Integer mfc) {
        return products.removeIf(p -> p.getManufactureCost().equals(mfc));
    }

    public int countByManufacturer(Organization organization) {
        return (int) products.stream()
                .filter(p -> p.getManufacturer().equals(organization)).count();
    }

    public boolean removeGreater(Product product) {
        return products.removeIf(p -> p.compareTo(product) > 0);
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
        String env = envVar;
        if (env == null) {
            env = FILE_PATH;
        }
        String filePath = System.getenv(env);
        try (XMLEncoder encoder = new XMLEncoder(new FileOutputStream(filePath))) {
            encoder.writeObject(products);
        } catch (IOException | NullPointerException ex) {
            System.out.println(ex.getMessage());
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
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            if (file.exists() && file.isFile() && br.readLine() != null) {
                try (FileInputStream fileInputStream = new FileInputStream(fileName);
                     XMLDecoder decoder = new XMLDecoder(fileInputStream)) {
                    products = (LinkedList<Product>) decoder.readObject();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                            params.add(checkLine(br.readLine()));
                        }
                        executeSuccessful = addUpdateProductFromScript(command, params, command == Command.ADD ? null : Integer.parseInt(lines.get(1)));
                        if (!executeSuccessful) {
                            return false;
                        }
                    } else {
                        console.executeCommand(command, lines.subList(1, lines.size()));
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
        } catch (IOException e) {
            System.out.println(MsgConsts.FILE_NOT_FOUND_MSG);
        }
        return false;
    }

    /**
     * Проверка на пустоту строки
     *
     * @param line строка из файла
     * @throws IllegalArgumentException
     */

    private static String checkLine(String line) throws IllegalArgumentException {
        if (line == null || Command.isValidCommand(line.split(" ")[0])) {
            throw new IllegalArgumentException();
        }
        return line;
    }

    /**
     * Создание продукта
     *
     * @param command   название команды
     * @param params    параметры создания продукта
     * @param productId id продукта
     */
    private boolean addUpdateProductFromScript(Command command, List<String> params, Integer productId) {
        if (params.size() == Product.INPUT_FIELD_COUNT) {
            Product product = createProductFromList(params);
            if (product != null) {
                if (command == Command.ADD) {
                    add(product);
                    return true;
                } else {
                    return update(product, productId);
                }
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            return false;
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
            Product p = ReadProps.readProductFromList(parameters);
            p.setId(getNewProductId());
            p.getManufacturer().setId(getNewOrganizationId());
            return p;
        } catch (NumberFormatException | ClassCastException ex) {
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
