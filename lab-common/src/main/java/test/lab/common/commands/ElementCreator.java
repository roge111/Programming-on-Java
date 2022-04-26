package test.lab.common.commands;

import test.lab.common.Read.EnumReaders.TypeofOrganizationReader;
import test.lab.common.Read.EnumReaders.UnitsOfMeasureReader;
import test.lab.common.Read.PARR.*;
import test.lab.common.client.*;
import test.lab.common.client.Location;

import java.util.ArrayList;

public class ElementCreator {
    public static Product creatProduct(){
        String name = StringReader.read("Введите название продукта: ", false);
        float x = ReFloatReader.read("Введите Х: ", 814, "MAX");
        long y = RelongReader.read("Введите Y: ", 85, "MAX");
        Double price = RefDoubleReader.read("Введите цену: ", 0, "MIN");
        String partNumber = StringReader.read("Введите номер продукта: ", false);
        Integer manufactureCost = ReIntReader.read("Введите стоимость изготовления: ", false);
        UnitOfMeasure unitOfMeasure = UnitsOfMeasureReader.read("Введите единицу измерения", false);

        return new Product(name, new Coordinates(x, y),price, partNumber, manufactureCost, unitOfMeasure, createOrganization());
    }

    public static Address createAddress(){
        String street = StringReader.read("Введите название улицы:" , false);
        Integer x = ReIntReader.read("Введите x:", false);
        Integer y = ReIntReader.read("Введите y: ", false);
        Float z  = FloatReader.read("Введите z: ", false);
        String name = StringReader.read("Введите название города: ", false);


        return new Address(street, new Location(x, y, z, name));
    }

    public static Organization createOrganization() {
        String name = StringReader.read("Введите название производства: ", false);
        String fullname =StringReadLimit.read("Введите полное название: ", false, 1435);
        OrganizationType type = TypeofOrganizationReader.read(false);

        return new Organization(name, fullname, type, createAddress());

    }

    public static Product creatScriptProduct(ArrayList<String> parameters){
        if (validateArray(parameters)){
            UnitOfMeasure unitOfMeasure = null;
            OrganizationType typeofOrganization = null;

            if (!parameters.get(6).isEmpty()) {
                unitOfMeasure = UnitOfMeasure.valueOf(parameters.get(6));}
            if (!parameters.get(9).isEmpty()) {
                typeofOrganization = OrganizationType.valueOf((parameters.get(9)));}
            return new Product(parameters.get(0),
                    new Coordinates(Float.parseFloat(parameters.get(1)), Long.parseLong(parameters.get(2))),
                    Float.parseFloat(parameters.get(3)),
                    parameters.get(4),
                    Integer.parseInt(parameters.get(5)),
                    unitOfMeasure,
                    new Organization(parameters.get(7), parameters.get(8), typeofOrganization,
                            new Address(parameters.get(10),
                                    new Location (Integer.parseInt(parameters.get(11)), Integer.parseInt(parameters.get(12)), Float.parseFloat(parameters.get(13)), parameters.get(14)))));
        }
        else { System.out.println("Один из параметров не соответствует требованиям."); }

        return null;
        }


    private static boolean validateArray(ArrayList<String> parameters) {
        try {
            return (!parameters.get(0).isEmpty()
                    && !parameters.get(1).isEmpty()
                    && !parameters.get(2).isEmpty()
                    && !parameters.get(3).isEmpty()
                    && !parameters.get(4).isEmpty() || parameters.get(4).isEmpty()
                    && !parameters.get(5).isEmpty()  || parameters.get(5).isEmpty()
                    && (UnitsOfMeasureReader.checkExist(parameters.get(6)) || parameters.get(6).isEmpty())
                    && !parameters.get(7).isEmpty()
                    && !parameters.get(8).isEmpty() || parameters.get(8).isEmpty()
                    && TypeofOrganizationReader.checkExist(parameters.get(9)) || parameters.get(9).isEmpty()
                    && !parameters.get(10).isEmpty() || parameters.get(10).isEmpty()
                    && !parameters.get(11).isEmpty()
                    && !parameters.get(12).isEmpty()
                    && !parameters.get(13).isEmpty()
                    && !parameters.get(14).isEmpty());

        } catch (NumberFormatException ex) { return false; }
    }



}
