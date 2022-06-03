package test.lab.common.utils;

import test.lab.common.client.Organization;
import test.lab.common.client.Product;
import test.lab.common.client.ProductComparableProperty;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.Objects;

public final class ProductsManager {
    private static final String FILE_PATH = "filepath.txt";
    private static LinkedList<Product> products;
    private static ZonedDateTime creationDate;

    private ProductsManager() {
    }


    public static boolean checkExist(Integer id) {
        return products.stream().anyMatch(p -> p.getId().equals(id));
    }


    public static void initList() {
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
    public static void add(Product product) {
        products.add(product);
    }

    public static Integer getNewProductId() {
        if (products.size() > 0) {
            return products.getLast().getId() + 1;
        }
        return 0;
    }

    public static Integer getNewOrganizationId() {
        if (products.size() > 0) {
            return products.getLast().getManufacturer().getId() + 1;
        }
        return 0;
    }

    /**
     * @param product
     * @param id
     */
    public static void update(Product product, Integer id) {
        products.stream().filter(p -> p.getId().equals(id)).findAny().get().updateProduct(product);
    }

    /**
     * @param productId
     * @return
     */
    public static boolean removeById(Integer productId) {
        return products.removeIf(p -> Objects.equals(p.getId(), productId));
    }

    public static void clear() {
        products.clear();
    }

    public static Product head() {
        return products.size() > 0 ? products.get(0) : null;
    }

    /**
     * @param manufacturer
     * @return
     */
    public static int countGreaterThanManufacturer(String manufacturer) {
        return (int) products.stream()
                .filter(p -> p.getManufacturer().toString().compareTo(manufacturer) > 0).count();
    }

    /**
     * @param mfc
     * @return
     */
    public static boolean removeAllByManufactureCost(Integer mfc) {
        return products.removeIf(p -> p.getManufactureCost().equals(mfc));
    }

    public static int countByManufacturer(String manufacturer) {
        return (int) products.stream()
                .filter(p -> p.getManufacturer().getName().equals(manufacturer)).count();
    }

    /**
     * @param pcp
     * @param value
     * @return
     */
    public static boolean removeGreater(ProductComparableProperty pcp, Object value) {
        boolean isSuccessful = false;
        try {
            switch (pcp) {
                case PRICE:
                    double price = Double.parseDouble(value.toString());
                    isSuccessful = products.removeIf(p -> p.getPrice() > price);
                    break;
                case ID:
                    Integer id = Integer.parseInt(value.toString());
                    isSuccessful = products.removeIf(p -> p.getId() > id);
                    break;
                case MANUFACTURE_COST:
                    Integer cost = Integer.parseInt(value.toString());
                    isSuccessful = products.removeIf(p -> p.getManufactureCost() > cost);
                    break;
                case ORGANIZATION:
                    Organization organization = (Organization) value;
                    isSuccessful = products.removeIf(p -> p.getManufacturer().compareTo(organization) > 0);
                    break;
                default:
                    break;
            }
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return isSuccessful;
    }

    public static void removeFirst() {
        if (products.size() > 0) {
            products.removeFirst();
        }
    }

    /**
     * @param envVar
     */
    public static void save(String envVar) {
        String filePath = System.getenv(envVar);
        try (FileOutputStream file = new FileOutputStream(filePath);
             XMLEncoder encoder = new XMLEncoder(file)) {
            encoder.writeObject(products);
            saveFilePath(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param filePath
     * @throws FileNotFoundException
     */
    private static void saveFilePath(String filePath) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(FILE_PATH);
        printWriter.println(filePath);
        printWriter.close();
    }

    @SuppressWarnings("unchecked")
    public static void load() {
        try {
            String fileName = getFilePath();
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

    public static ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public static LinkedList<Product> getProducts() {
        return products;
    }

}
