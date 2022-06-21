package test.lab.common.utils;

import test.lab.common.Console;
import test.lab.common.client.Address;
import test.lab.common.client.Coordinates;
import test.lab.common.client.Location;
import test.lab.common.client.Organization;
import test.lab.common.client.OrganizationType;
import test.lab.common.client.Product;
import test.lab.common.client.UnitOfMeasure;

import java.util.List;
// Проверка
public final class ReadProps {
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
    private static final int MAX_ORGANIZATION_FULL_NAME = 1435;
    private static final int MAX_X_COORD = 814;
    private static final int MAX_Y_COORD = 85;

    private ReadProps() {

    }

    public static String readProductName() {
        System.out.print(MsgConsts.Product.NAME);
        return readValue(String.class, false, false);
    }


    public static String readOrganizationName() {
        System.out.print(MsgConsts.Organization.NAME);
        return readValue(String.class, false, false);
    }

    public static String readOrganizationFullName() {
        System.out.print(MsgConsts.Organization.FULL_NAME);
        while (true) {
            String fullName = readValue(String.class, true, false);
            if (fullName != null && fullName.length() > MAX_ORGANIZATION_FULL_NAME) {
                System.out.print(MsgConsts.ERROR_ORGANIZATION_FULL_NAME_MSG);
                continue;
            }
            return fullName;
        }
    }

    public static OrganizationType readOrganizationType() {
        System.out.print(MsgConsts.Organization.ORGANIZATION_TYPE);
        return readValue(OrganizationType.class, false, false);
    }

    public static String readAddressStreet() {
        System.out.print(MsgConsts.Address.STREET);
        return readValue(String.class, false, true);
    }

    public static String readAddressCity() {
        System.out.print(MsgConsts.Address.CITY);
        return readValue(String.class, false, false);
    }

    public static String readProductPartNumber() {
        System.out.print(MsgConsts.Product.NUMBER);
        return readValue(String.class, false, false);
    }

    public static Integer readManufactureCost() {
        System.out.print(MsgConsts.Product.COST);
        return readValue(Integer.class, true, false);
    }

    public static UnitOfMeasure readUnitOfMeasure() {
        System.out.print(MsgConsts.Product.UNIT);
        return readValue(UnitOfMeasure.class, false, false);
    }

    public static Double readProductPrice() {
        System.out.print(MsgConsts.Product.PRICE);
        while (true) {
            Double price = readValue(Double.class, false, false);
            if (price <= 0) {
                System.out.print(MsgConsts.ERROR_PRICE_MSG);
                continue;
            }
            return price;
        }
    }

    public static float readX() {
        System.out.print(MsgConsts.X_COORD_MSG);
        while (true) {
            float x = readValue(float.class, false, false);
            if (x > MAX_X_COORD) {
                System.out.print(MsgConsts.ERROR_X_MSG);
                continue;
            }
            return x;
        }
    }

    public static String readTownName() {
        System.out.print(MsgConsts.TOWN_COORD_MSG);
        return readValue(String.class, false, false);

    }

    public static Integer readXLocation() {
        System.out.print(MsgConsts.X_COORD_MSG);
        return readValue(Integer.class, false, false);
    }

    public static Integer readYLocation() {
        System.out.print(MsgConsts.Y_COORD_MSG);
        return readValue(Integer.class, false, false);
    }

    public static Float readZLocation() {
        System.out.print(MsgConsts.Z_COORD_MSG);
        return readValue(float.class, false, false);
    }

    public static long readY() {
        System.out.print(MsgConsts.Y_COORD_MSG);
        while (true) {
            long y = readValue(long.class, false, false);
            if (y > MAX_Y_COORD) {
                System.out.print(MsgConsts.ERROR_Y_MSG);
                continue;
            }
            return y;
        }
    }

    public static Product readProductFromList(List<String> parameters) {
        String name = ReadProps.checkAndReturnValue(parameters.get(NAME_INDEX), String.class,
                false, false);
        Float x = ReadProps.checkAndReturnValue(parameters.get(X_INDEX), float.class,
                false, false);
        Long y = ReadProps.checkAndReturnValue(parameters.get(Y_INDEX), long.class,
                false, false);
        Double price = ReadProps.checkAndReturnValue(parameters.get(PRICE_INDEX), Double.class,
                false, false);
        String partNumber = ReadProps.checkAndReturnValue(parameters.get(PART_NUMBER_INDEX), String.class,
                false, false);
        Integer manufactureCost = ReadProps.checkAndReturnValue(parameters.get(MANUFACTURE_COST_INDEX), Integer.class,
                true, false);
        UnitOfMeasure unitOfMeasure = ReadProps.checkAndReturnValue(parameters.get(UNIT_OF_MEASURE_INDEX),
                UnitOfMeasure.class, false, false);
        String nameOrganization = ReadProps.checkAndReturnValue(parameters.get(NAME_ORGANIZATION_INDEX),
                String.class, false, false);
        String fullName = ReadProps.checkAndReturnValue(parameters.get(FULL_NAME_INDEX), String.class,
                false, true);
        OrganizationType type = ReadProps.checkAndReturnValue(parameters.get(TYPE_INDEX), OrganizationType.class,
                false, false);
        String street = ReadProps.checkAndReturnValue(parameters.get(STREET_INDEX), String.class,
                false, false);
        Integer xLocation = ReadProps.checkAndReturnValue(parameters.get(X_LOCATION_INDEX), Integer.class,
                false, false);
        Integer yLocation = ReadProps.checkAndReturnValue(parameters.get(Y_LOCATION_INDEX), Integer.class,
                false, false);
        Float zLocation = ReadProps.checkAndReturnValue(parameters.get(Z_LOCATION_INDEX), float.class,
                false, false);
        String nameLocation = ReadProps.checkAndReturnValue(parameters.get(NAME_LOCATION_INDEX), String.class,
                false, false);
        Product p = new Product(0, name, new Coordinates(x, y), price, partNumber, unitOfMeasure,
                new Organization(0, nameOrganization, fullName, type,
                        new Address(street,
                                new Location(xLocation, yLocation, zLocation, nameLocation))));
        p.setManufactureCost(manufactureCost);
        return p;
    }

    /**
     *
     * @param clazz
     * @param isCanBeNull
     * @param <T>
     * @return
     */
    public static <T> T readValue(Class<T> clazz, boolean isCanBeNull, boolean isCanBeEmpty) {
        while (true) {
            try {
                String stringValue = Console.SCANNER.nextLine();
                T value = checkAndReturnValue(stringValue, clazz, isCanBeNull, isCanBeEmpty);
                if (value == null && !isCanBeNull) {
                    System.out.print(MsgConsts.ERROR_STR_MSG);
                } else {
                    return value;
                }
            } catch (IllegalArgumentException e) {
                System.out.print(MsgConsts.ERROR_STR_MSG);
            }
        }
    }

    /**
     *
     * @param stringValue
     * @param clazz
     * @param isCanBeNull
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T checkAndReturnValue(String stringValue, Class<T> clazz, boolean isCanBeNull, boolean isCanBeEmpty)
        throws ClassCastException, NumberFormatException {
        T value;
        if ((!isCanBeNull && stringValue == null) || (stringValue.isEmpty() && !isCanBeEmpty)) {
            return null;
        }
        if (clazz.isAssignableFrom(Integer.class)) {
            value = (T) Integer.valueOf(stringValue);
        } else if (clazz.isAssignableFrom(float.class)) {
            value = (T) Float.valueOf(stringValue);
        } else if (clazz.isAssignableFrom(long.class)) {
            value = (T) Long.valueOf(stringValue);
        } else if (clazz.isAssignableFrom(Double.class)) {
            value = (T) Double.valueOf(stringValue);
        } else if (clazz.isAssignableFrom(OrganizationType.class)) {
            value = (T) OrganizationType.valueOf(stringValue);
        } else if (clazz.isAssignableFrom(UnitOfMeasure.class)) {
            value = (T) UnitOfMeasure.valueOf(stringValue);
        } else {
            value = (T) stringValue;
        }
        return value;


    }
}

