package test.lab.common.Read.PARR;

import java.util.Scanner;

public class FloatReader {
    public static Float read(String messageForConsole, boolean canBeNull) {
        Scanner in = new Scanner(System.in);
        System.out.print(messageForConsole);
        Float readDouble = null;
        boolean end = false;
        while (!end) {
            try {
                readDouble = Float.parseFloat(in.nextLine().trim());
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
                if (readDouble instanceof Float) {
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
