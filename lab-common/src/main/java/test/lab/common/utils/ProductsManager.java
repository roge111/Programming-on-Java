package test.lab.common.utils;

import test.lab.common.client.Product;
import test.lab.common.client.Organization;
import test.lab.common.client.ProductComparableProperty;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.Objects;

public final class ProductsManager {
    public static final String FILE_NAME = "out.xml";
    public static final String BACKUP_NAME = "backup.xml";
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
            load(FILE_NAME);
            if (products.size() > 0) {
                Product.setCountId(getMaxProductId());
                Organization.setCountId(getMaxOrganizationId());
            }
            creationDate = ZonedDateTime.now();
        }
    }

    public static void add(Product product) {
        products.add(product);
    }

    private static Integer getMaxProductId() {
        return products.getLast().getId();
    }

    private static Integer getMaxOrganizationId() {
        return products.getLast().getManufacturer().getId();
    }

    public static void update(Product product, Integer id) {
        products.stream().filter(p -> p.getId().equals(id)).findAny().get().updateProduct(product);
    }

    public static boolean removeById(Integer productId) {
        return products.removeIf(p -> Objects.equals(p.getId(), productId));
    }

    public static void clear() {
        products.clear();
    }

    public static Product head() {
        return products.size() > 0 ? products.get(0) : null;
    }

    public static int countGreaterThanManufacturer(String manufacturer) {
        return (int) products.stream()
                .filter(p -> p.getManufacturer().toString().compareTo(manufacturer) > 0).count();
    }


    public static boolean removeAllByManufactureCost(Integer mfc) {
        return products.removeIf(p -> p.getManufactureCost().equals(mfc));
    }

    public static int countByManufacturer(String manufacturer) {
        return (int) products.stream()
                .filter(p -> p.getManufacturer().getName().equals(manufacturer)).count();
    }

    public static boolean removeGreater(ProductComparableProperty pcp, String value) {
        boolean isSuccessful = false;
        try {
            switch (pcp) {
                case PRICE:
                    double price = Double.parseDouble(value);
                    isSuccessful = products.removeIf(p -> p.getPrice() > price);
                    break;
                case ID:
                    Integer id = Integer.parseInt(value);
                    isSuccessful = products.removeIf(p -> p.getId() > id);
                    break;
                case MANUFACTURE_COST:
                    Integer cost = Integer.parseInt(value);
                    isSuccessful = products.removeIf(p -> p.getManufactureCost() > cost);
                    break;
                default:
                    break;
            }
        } catch (NumberFormatException ignored) {
            ignored.printStackTrace();
        }
        return isSuccessful;
    }

    public static boolean removeFirst() {
        if (products.size() > 0) {
            products.removeFirst();
            return true;
        }
        return false;
    }

    public static void save(String fileName) {
        try (FileOutputStream file = new FileOutputStream(fileName);
             XMLEncoder encoder = new XMLEncoder(file)) {
            encoder.writeObject(products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void load(String fileName) {
        try {
            File file = new File(fileName);
            if (file.exists() && file.isFile()) {
                try (FileInputStream fileInputStream = new FileInputStream(FILE_NAME);
                     XMLDecoder decoder = new XMLDecoder(fileInputStream)) {
                    products = (LinkedList<Product>) decoder.readObject();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public static LinkedList<Product> getProducts() {
        return products;
    }

}
