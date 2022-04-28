package test.lab.common.commands;

import test.lab.common.Read.EnumReaders.TypeofOrganizationReader;
import test.lab.common.Read.EnumReaders.UnitsOfMeasureReader;
import test.lab.common.Read.PARR.FloatReader;
import test.lab.common.Read.PARR.ReFloatReader;
import test.lab.common.Read.PARR.ReIntReader;
import test.lab.common.Read.PARR.RefDoubleReader;
import test.lab.common.Read.PARR.RelongReader;
import test.lab.common.Read.PARR.StringReadLimit;
import test.lab.common.Read.PARR.StringReader;
import test.lab.common.client.Address;
import test.lab.common.client.Coordinates;
import test.lab.common.client.Location;
import test.lab.common.client.Organization;
import test.lab.common.client.OrganizationType;
import test.lab.common.client.Product;
import test.lab.common.client.UnitOfMeasure;

import java.util.ArrayList;

public final class ElementCreator {
    static final int C1 = 1;
    static final int C2 = 2;
    static final int C3 = 3;
    static final int C4 = 4;
    static final int C5 = 5;
    static final int C6 = 6;
    static final int C7 = 7;
    static final int C8 = 8;
    static final int C9 = 9;
    static final int C10 = 10;
    static final int C11 = 11;
    static final int C12 = 12;
    static final int C13 = 13;
    static final int C14 = 14;
    static final int C814 = 814;
    static final int C85 = 85;
    static final int C0 = 0;
    static final int C1435 = 1435;

    private ElementCreator() {

    }

    public static Product creatProduct() {
        String name = StringReader.read("Введите название продукта: ", false);
        float x = ReFloatReader.read("Введите Х: ", C814, "MAX");
        long y = RelongReader.read("Введите Y: ", C85, "MAX");
        Double price = RefDoubleReader.read("Введите цену: ", C0, "MIN");
        String partNumber = StringReader.read("Введите номер продукта: ", false);
        Integer manufactureCost = ReIntReader.read("Введите стоимость изготовления: ", false);
        UnitOfMeasure unitOfMeasure = UnitsOfMeasureReader.read("Введите единицу измерения", false);

        return new Product(name, new Coordinates(x, y), price, partNumber, manufactureCost, unitOfMeasure, createOrganization());
    }

    public static Address createAddress() {
        String street = StringReader.read("Введите название улицы:", false);
        Integer x = ReIntReader.read("Введите x:", false);
        Integer y = ReIntReader.read("Введите y: ", false);
        Float z = FloatReader.read("Введите z: ", false);
        String name = StringReader.read("Введите название города: ", false);


        return new Address(street, new Location(x, y, z, name));
    }

    public static Organization createOrganization() {
        String name = StringReader.read("Введите название производства: ", false);
        String fullname = StringReadLimit.read("Введите полное название: ", false, C1435);
        OrganizationType type = TypeofOrganizationReader.read(false);

        return new Organization(name, fullname, type, createAddress());

    }

    public static Product creatScriptProduct(ArrayList<String> parameters) {

        if (validateArray(parameters)) {
            UnitOfMeasure unitOfMeasure = null;
            OrganizationType typeofOrganization = null;

            if (!parameters.get(C6).isEmpty()) {
                unitOfMeasure = UnitOfMeasure.valueOf(parameters.get(C6));
            }
            if (!parameters.get(C9).isEmpty()) {
                typeofOrganization = OrganizationType.valueOf((parameters.get(C9)));
            }
            return new Product(parameters.get(C0),
                    new Coordinates(Float.parseFloat(parameters.get(1)), Long.parseLong(parameters.get(C2))),
                    Float.parseFloat(parameters.get(C3)),
                    parameters.get(C4),
                    Integer.parseInt(parameters.get(C5)),
                    unitOfMeasure,
                    new Organization(parameters.get(C7), parameters.get(C8), typeofOrganization,
                            new Address(parameters.get(C10),
                                    new Location(Integer.parseInt(parameters.get(C11)), Integer.parseInt(parameters.get(C12)), Float.parseFloat(parameters.get(C13)), parameters.get(C14)))));
        } else {
            System.out.println("Один из параметров не соответствует требованиям.");
        }
        return null;
    }

    private static boolean valAr2(ArrayList<String> parameters) {
        return !parameters.get(C0).isEmpty() && !parameters.get(C1).isEmpty() && !parameters.get(C2).isEmpty() && !parameters.get(C3).isEmpty() && !parameters.get(C4).isEmpty();
    }

    private static boolean valAr(ArrayList<String> parameters) {
        boolean i = valAr2(parameters);
        return ((i || parameters.get(C4).isEmpty() && !parameters.get(C5).isEmpty() || parameters.get(C5).isEmpty() && (UnitsOfMeasureReader.checkExist(parameters.get(C6)) || parameters.get(C6).isEmpty()) && !parameters.get(C7).isEmpty() && !parameters.get(C8).isEmpty() || parameters.get(C8).isEmpty() && TypeofOrganizationReader.checkExist(parameters.get(C9)) || parameters.get(C9).isEmpty() && !parameters.get(C10).isEmpty() || parameters.get(C10).isEmpty()));
    }

    private static boolean validateArray(ArrayList<String> parameters) {
        try {
            boolean i = valAr(parameters);
            return i && !parameters.get(C11).isEmpty() && !parameters.get(C12).isEmpty() && !parameters.get(C13).isEmpty() && !parameters.get(C14).isEmpty();
        } catch (NumberFormatException ex) {
            return false;
        }
    }

}
