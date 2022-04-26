package test.lab.common.Collection;

import test.lab.common.client.Product;


public class CollectionUntils {
    public static  boolean checkExist(Integer ID){
        for (Product product:CollectionManager.getLinkedList()){
            return product.getId().equals(ID);
        }
        return false;
    }

    static void display(Product product){
        System.out.println("ID элемента: "+product.getId());
        System.out.println("Координата X: " + product.getCoordinates().getX());
        System.out.println("Название продукта: "+product.getName());

        System.out.println("Координата Y: " + product.getCoordinates().getY());
        System.out.println("Дата создание продукта: " + product.getCreationDate());
        System.out.println("Цена продукта: " + product.getPrice());
        System.out.println("Номер продукта: " + product.getPartNumber());
        System.out.println("Цена изготовления: " + product.getManufactureCost());
        System.out.println("ID производства: " + product.getManufacturer().getId());
        System.out.println("Единицы измерения: " + product.getUnitOfMeasure());
        System.out.println("Название производства: " + product.getManufacturer().getName());
        System.out.println("Тип производства: " + product.getManufacturer().getType());
        System.out.println("Адрес производства: " + product.getManufacturer().getPostalAddress().getTown() + " улица " + product.getManufacturer().getPostalAddress().getStreet());


    }
}
