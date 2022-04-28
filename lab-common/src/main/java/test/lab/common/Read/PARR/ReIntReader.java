package test.lab.common.Read.PARR;

import java.util.Scanner;

public class ReIntReader {
    public static Integer read(String messageForConsole, boolean canBeNull) {
        Scanner in = new Scanner(System.in);
        System.out.print(messageForConsole);
        Integer readDouble = null;
        boolean end = false;
        while (!end) {
            try {
                readDouble = Integer.parseInt(in.nextLine().trim());
                if (canBeNull && readDouble.equals("")) {
                    {
                        end = true;
                    }
                    return null;
                }
                if (!canBeNull && readDouble.equals("")) {
                    {
                        System.out.println("Поле не может быть null. Попробуйте снова: ");
                    }
                }
                if (readDouble instanceof Integer) {
                    end = true;
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели неподходящее значение. Введите число:");
            }
        }
        return readDouble;
    }
}
