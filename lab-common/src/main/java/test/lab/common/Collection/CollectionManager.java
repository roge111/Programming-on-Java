package test.lab.common.Collection;

import test.lab.common.client.Product;

import java.beans.XMLEncoder;
import java.io.*;
import java.time.ZonedDateTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;


public class CollectionManager {
    public static LinkedList<Product> linkedList;
    public static ZonedDateTime creationDate;

    public static LinkedList<Product> getLinkedList() {
        return linkedList;
    }

    public static void initList() {
        if (linkedList == null) {
            linkedList = new LinkedList<>();
            creationDate = ZonedDateTime.now();
        }
    }

    public static void add(Product product) {
        linkedList.add(product);
    }


    public static void getInfo() {
        System.out.println("Тип коллекции – " + linkedList.getClass().getName());
        System.out.println("Дата инициализации коллекции – " + creationDate);
        System.out.println("Количество элементов в коллекции – " + linkedList.size());
        System.out.println("_________________________________________________________\n");
    }

    public static void show() {
        linkedList.forEach(CollectionUntils::display);

    }

    public static void update(Product producttoupdate, Integer elementID) {
        linkedList.forEach(Product -> {
            if (Product.getId().equals(elementID)) {
                Product.setName(producttoupdate.getName());
                Product.setCoordinates(producttoupdate.getCoordinates());

            }

        });
    }

    public static void removeById(Integer productId) {
        Iterator<Product> i = linkedList.iterator();
        while (i.hasNext()) {
            Product product = i.next();

            if (product.getId().equals(productId)) {
                i.remove();
                System.out.println("Элемент из коллекции успешно удалён. ");

            }

        }
        if (!i.hasNext()) {
            System.out.println("Элемента с указанным вами ID нет в коллекции");

        }
    }

    public static void clear() {
        linkedList.clear();
    }

    public static void head() {
        if (linkedList.size() > 0) {
            CollectionUntils.display(linkedList.getFirst());

        } else {
            System.out.println("Коллекция пустая");
        }
    }

    public static void countGreaterThanManufacturer(String manufacturer) {
        if (linkedList.size() > 0) {
            for (Product listProduct : linkedList) {
                if (String.valueOf(listProduct.getManufacturer()).compareTo(manufacturer) > 0) {
                    CollectionUntils.display(listProduct);
                } else {
                    System.out.println("Не найдено элементов с manufacturer больше заданного");
                    break;
                }
            }
        } else {
            System.out.println("Коллекция пустая");
        }

    }


    public static void removeAllByManufactureCost(Integer mfc) {
        int cnt = 0;
        try {
            for (Product linkedProduct : linkedList) {
                if (linkedProduct.getManufactureCost() == mfc) {
                    linkedList.remove(linkedProduct);
                    cnt++;
                }
            }
            if (cnt == 0) {
                System.out.println("Таких элиментов не найдено.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Нет таких элементов.");
        }

    }

    public static void countByManufacturer(String manufacturer) {
        int cnt = 0;
        for (Product linkedProduct : linkedList) {
            if (String.valueOf(linkedProduct.getManufacturer()) == manufacturer) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    public static void removeGreater(Integer id) {
        Iterator<Product> i = linkedList.iterator();
        while (i.hasNext()) {
            Product product = i.next();
            if (product.getId() > id) {
                i.remove();
            }
        }

    }

    public static void removeFirst() {
        Iterator<Product> i = linkedList.iterator();
        if (i.hasNext()) {
            i.next();
            i.remove();
        }
    }

    public static void save(String fileName){
        try{
            FileOutputStream file = new FileOutputStream(new File(fileName + ".xml"));
            XMLEncoder encoder = new XMLEncoder(file);
            for (Product product : linkedList){
                Product product1 = new Product(product.getName(), product.getCoordinates(), product.getPrice(), product.getPartNumber(), product.getManufactureCost(), product.getUnitOfMeasure(), product.getManufacturer());
                encoder.writeObject(product1);
            }
            encoder.close();
            file.close();
        } catch ( IOException e){
            e.printStackTrace();
        }





    }


}
