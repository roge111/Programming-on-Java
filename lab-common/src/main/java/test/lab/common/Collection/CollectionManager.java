package test.lab.common.Collection;

import test.lab.common.client.Product;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

    public void save() {
        try {
            Scanner in = new Scanner(System.in);
            String filename = in.nextLine().trim();
            File file = new File(filename + ".xml");
            PrintWriter writer = new PrintWriter(file);
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<java version=\"16.0.2\" class=\"test.lab.common.Collection.CollectionManager\">\n" + " <object class=\"test.lab.common.client.Product\">\n");
            for (Product product : linkedList) {
                writer.write("  <void property=\"manufactureCost\">\n" + "   <int>" + product.getManufactureCost() + "</int>\n" + "  </void>\n" + "  <void property=\"manufacturer\">\n" + "   <object class=\"test.lab.common.client.Organization\">\n" + "    <void property=\"name\">\n" + "     <string>" + product.getManufacturer().getName() + "</string>\n" + "    </void>\n" + "    <void property=\"postalAddress\">\n" + "     <object class=\"test.lab.common.client.Address\">\n" + "      <void property=\"street\">\n" + "       <string>" + product.getManufacturer().getPostalAddress().getStreet() + "</string>\n" + "      </void>\n" + "      </void>\n" + "     </object>\n" + "    </void>\n" + "    <void property=\"type\">\n" + "     <object class=\"java.lang.Enum\" method=\"valueOf\">\n" + "      <class>test.lab.common.client.OrganizationType</class>\n" + "      <string>" + product.getManufacturer().getType() + "</string>\n" + "     </object>\n" + "    </void>\n" + "   </object>\n" + "  </void>\n" + "  <void property=\"name\">\n" + "   <string>" + product.getName() + "</string>\n" + "  </void>\n" + "  <void property=\"partNumber\">\n" + "   <string>" + product.getPartNumber() + "</string>\n" + "  </void>\n" + "  <void property=\"price\">\n" + "   <double>" + product.getPrice() + "</double>\n" + "  </void>\n" + "  <void property=\"unitOfMeasure\">\n" + "   <object class=\"java.lang.Enum\" method=\"valueOf\">\n" + "    <class>test.lab.common.client.UnitOfMeasure</class>\n" + "    <string>" + product.getUnitOfMeasure() + "</string>\n" + "   </object>\n" + "  </void>\n");
                writer.close();
            }
            writer.write("\" </object>\\n\" + \"</java>\"");
            System.out.println("Коллекция сохранена в файл: " + filename + ".xml");
        } catch (IOException e) {
            System.out.println("Произошла ошибка");
        }

    }


}
