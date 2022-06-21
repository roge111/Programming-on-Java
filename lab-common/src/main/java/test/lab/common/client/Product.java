package test.lab.common.client;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.Objects;

public class Product implements Comparable<Product> {
    public static final int INPUT_FIELD_COUNT = 15;
    private Integer id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Double price; //Значение поля должно быть больше 0
    private String partNumber; //Поле не может быть null
    private Integer manufactureCost; //Поле может быть null
    private UnitOfMeasure unitOfMeasure; //Поле не может быть null
    private Organization manufacturer; //Поле не может быть null


    public Product() {

    }

    public Product(Product p) {
        this.id = p.id;
        this.name = p.name;
        this.coordinates = p.coordinates;
        this.creationDate = new Date();
        this.price = p.price;
        this.partNumber = p.partNumber;
        this.manufactureCost = p.manufactureCost;
        this.unitOfMeasure = p.unitOfMeasure;
        this.manufacturer = new Organization(p.manufacturer);
    }


    public Product(Integer id, String name, Coordinates coordinates, double price, String partNumber, UnitOfMeasure unitOfMeasure, Organization manufacturer) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = new Date();
        this.price = price;
        this.partNumber = partNumber;
        this.unitOfMeasure = unitOfMeasure;
        this.manufacturer = manufacturer;
    }


    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * @param coordinates
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Double getPrice() {
        return price;
    }

    /**
     * @param price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPartNumber() {
        return partNumber;
    }

    /**
     * @param partNumber
     */
    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public Integer getManufactureCost() {
        return manufactureCost;
    }

    /**
     * @param manufactureCost
     */
    public void setManufactureCost(Integer manufactureCost) {
        this.manufactureCost = manufactureCost;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    /**
     * @param unitOfMeasure
     */
    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public Organization getManufacturer() {
        return manufacturer;
    }

    /**
     * @param manufacturer
     */
    public void setManufacturer(Organization manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * @param product
     */
    public void updateProduct(Product product) {
        this.name = product.name;
        this.coordinates = product.coordinates;
        this.partNumber = product.partNumber;
        this.manufactureCost = product.manufactureCost;
        this.manufacturer = product.manufacturer;
        this.price = product.price;
        this.unitOfMeasure = product.unitOfMeasure;
    }

    /**
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return id.equals(product.id) && Objects.equals(name, product.name) && coordinates.equals(product.coordinates) && creationDate.equals(product.creationDate) && Objects.equals(manufacturer, product.manufacturer);
    }

    @Override
    public String toString() {
        return "ID: " + id
                + "\nКоординаты (x, y): " + coordinates.getX() + ", " + coordinates.getY()
                + "\nНаименование: " + name
                + "\nДата создания: " + creationDate
                + "\nЦена: " + price
                + "\nНомер: " + partNumber
                + "\nЦена изготовления: " + manufactureCost
                + "\nЕдиница измерения: " + unitOfMeasure.toString()
                + "\nID производства: " + manufacturer.getId()
                + "\nНазвание производства: " + manufacturer.getName()
                + "\nТип производства: " + manufacturer.getType()
                + "\nАдрес производства: " + manufacturer.getPostalAddress().getTown()
                + " улица " + manufacturer.getPostalAddress().getStreet();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, manufacturer);
    }

    /**
     * Возвращает 0 если объекты равны, иначе -1 - объект первый меньше втоорого, иначе 1
     *
     * @param o объект, который будем сравнивать
     * @return
     */
    public int compareTo(@NotNull Product o) {
        int result = name.compareTo(o.name);
        if (result != 0) {
            return result;
        }
        result = price.compareTo(o.price);
        if (result != 0) {
            return result;
        }
        result = manufactureCost.compareTo(o.manufactureCost);
        if (result != 0) {
            return result;
        }
        return manufacturer.compareTo(o.manufacturer);
    }
}


