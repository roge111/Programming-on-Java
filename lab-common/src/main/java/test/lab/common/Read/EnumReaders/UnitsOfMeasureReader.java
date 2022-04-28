package test.lab.common.Read.EnumReaders;

import java.util.Arrays;
import java.util.Scanner;

import test.lab.common.client.UnitOfMeasure;

public class UnitsOfMeasureReader {
    public static boolean checkExist(String toContains) {
        return Arrays.stream(UnitOfMeasure.values()).anyMatch((UnitOfMeasure) -> UnitOfMeasure.name().equals(toContains));
    }

    public static UnitOfMeasure read(String messageFoConsole, boolean canBeNull) {
        Scanner in = new Scanner(System.in);
        System.out.println(messageFoConsole + "Выберите еденицы измерения из представленных (" + Arrays.asList(UnitOfMeasure.values()) + "): ");
        String toContains = in.nextLine().trim();

        if ((!checkExist(toContains)) && !canBeNull && !toContains.equals("") || !canBeNull && toContains.equals("") || canBeNull && !toContains.equals("")) {
            while (!checkExist(toContains)) {
                System.out.println("Вы ввели не существующее значение из представленных. Попробуйте снова: ");
                toContains = in.nextLine().trim();
                checkExist(toContains);
            }
        }

        if (canBeNull && toContains.equals("")) {
            return null;
        }

        return Enum.valueOf(UnitOfMeasure.class, toContains);
    }


}
