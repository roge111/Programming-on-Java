package test.lab.common.utils;

import test.lab.common.Console;
import test.lab.common.client.OrganizationType;
import test.lab.common.client.UnitOfMeasure;

public final class ReadProps {

    private static final int MAX_ORGANIZATION_FULL_NAME = 1435;
    private static final int MAX_X_COORD = 814;
    private static final int MAX_Y_COORD = 85;

    private ReadProps() {

    }

    public static String readProductName() {
        System.out.print(MsgConsts.Product.NAME);
        return readValue(String.class, false);
    }


    public static String readOrganizationName() {
        System.out.print(MsgConsts.Organization.NAME);
        return readValue(String.class, false);
    }

    public static String readOrganizationFullName() {
        System.out.print(MsgConsts.Organization.FULL_NAME);
        while (true) {
            String fullName = readValue(String.class, true);
            if (fullName.length() > MAX_ORGANIZATION_FULL_NAME) {
                System.out.print(MsgConsts.ERROR_ORGANIZATION_FULL_NAME_MSG);
                continue;
            }
            return fullName;
        }
    }

    public static OrganizationType readOrganizationType() {
        System.out.print(MsgConsts.Organization.ORGANIZATION_TYPE);
        return readValue(OrganizationType.class, false);
    }

    public static String readAddressStreet() {
        System.out.print(MsgConsts.Address.STREET);
        return readValue(String.class, false);
    }

    public static String readAddressCity() {
        System.out.print(MsgConsts.Address.CITY);
        return readValue(String.class, false);
    }

    public static String readProductPartNumber() {
        System.out.print(MsgConsts.Product.NUMBER);
        return readValue(String.class, false);
    }

    public static Integer readManufactureCost() {
        System.out.print(MsgConsts.Product.COST);
        return readValue(Integer.class, true);
    }

    public static UnitOfMeasure readUnitOfMeasure() {
        System.out.print(MsgConsts.Product.UNIT);
        return readValue(UnitOfMeasure.class, false);
    }

    public static Double readProductPrice() {
        System.out.print(MsgConsts.Product.PRICE);
        while (true) {
            Double price = readValue(Double.class, false);
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
            float x = readValue(float.class, false);
            if (x > MAX_X_COORD) {
                System.out.print(MsgConsts.ERROR_X_MSG);
                continue;
            }
            return x;
        }
    }

    public static String readTownName() {
        System.out.print(MsgConsts.TOWN_COORD_MSG);
        return readValue(String.class, false);

    }

    public static Integer readXLocation() {
        System.out.print(MsgConsts.X_COORD_MSG);
        return readValue(Integer.class, false);
    }

    public static Integer readYLocation() {
        System.out.print(MsgConsts.Y_COORD_MSG);
        return readValue(Integer.class, false);
    }

    public static Float readZLocation() {
        System.out.print(MsgConsts.Z_COORD_MSG);
        return readValue(float.class, false);
    }

    public static long readY() {
        System.out.print(MsgConsts.Y_COORD_MSG);
        while (true) {
            long y = readValue(long.class, false);
            if (y > MAX_Y_COORD) {
                System.out.print(MsgConsts.ERROR_Y_MSG);
                continue;
            }
            return y;
        }
    }

    public static <T> T readValue(Class<T> clazz, boolean isCanBeNull) {
        while (true) {
            try {
                String stringValue = Console.SCANNER.nextLine();
                T value = checkAndReturnValue(stringValue, clazz, isCanBeNull);
                if (value == null) {
                    System.out.print(MsgConsts.ERROR_STR_MSG);
                } else {
                    return value;
                }
            } catch (IllegalArgumentException e) {
                System.out.print(MsgConsts.ERROR_STR_MSG);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T checkAndReturnValue(String stringValue, Class<T> clazz, boolean isCanBeNull) {


        T value;
        if ((!isCanBeNull && stringValue == null) || stringValue.isEmpty()) {
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

