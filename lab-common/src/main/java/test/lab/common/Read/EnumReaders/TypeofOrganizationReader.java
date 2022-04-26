package test.lab.common.Read.EnumReaders;

import test.lab.common.client.OrganizationType;

import java.util.Scanner;
import java.util.Arrays;

public class TypeofOrganizationReader {
    public  static boolean checkExist(String toContains){
        return Arrays.stream(OrganizationType.values()).anyMatch((OrganizationType) -> OrganizationType.name().equals(toContains));

    }

    public static OrganizationType read(boolean canBeNull) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите тип организации из представленных(" + Arrays.asList(OrganizationType.values()) + "): ");
        String toContains = in.nextLine().trim();

        if ((!checkExist(toContains)) && !canBeNull && !toContains.equals("") || !canBeNull && toContains.equals("") || canBeNull && !toContains.equals("")) {
            while (!checkExist(toContains)) {
                System.out.print("Вы ввели несуществующее значение из представленных. Попробуйте снова: ");
                toContains = in.nextLine().trim();
                checkExist(toContains);
            }
        }

        if (canBeNull && toContains.equals("")) { return null; }

        return Enum.valueOf(OrganizationType.class, toContains);



    }
}
