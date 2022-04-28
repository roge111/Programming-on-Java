package test.lab.common.Read.EnumReaders;

import test.lab.common.client.OrganizationType;

import java.util.Arrays;
import java.util.Scanner;

public final class TypeofOrganizationReader {
    private TypeofOrganizationReader() {
    }

    public static boolean checkExist(String toContains) {
        return Arrays.stream(OrganizationType.values()).anyMatch((OrganizationType) -> OrganizationType.name().equals(toContains));

    }

    public static OrganizationType read(boolean canBeNull) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите тип организации из представленных(" + Arrays.asList(OrganizationType.values()) + "): ");
        String toContains = in.nextLine().trim();

        if ((!("").equals(toContains) && !canBeNull && !checkExist(toContains)) || !canBeNull && ("").equals(toContains) || canBeNull && !("").equals(toContains)) {
            while (!checkExist(toContains)) {
                System.out.print("Вы ввели несуществующее значение из представленных. Попробуйте снова: ");
                toContains = in.nextLine().trim();
                checkExist(toContains);
            }
        }

        if (canBeNull && ("").equals(toContains)) {
            return null;
        }

        return Enum.valueOf(OrganizationType.class, toContains);


    }
}
