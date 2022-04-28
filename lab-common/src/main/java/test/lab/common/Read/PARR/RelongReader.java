package test.lab.common.Read.PARR;

import java.util.Scanner;

public class RelongReader {
    public static long read(String messageForConsole, int limit, String type) {
        System.out.print(messageForConsole);
        Scanner sc = new Scanner(System.in);
        long result = 0;
        boolean end = false;
        while (!end) {
            try {
                result = Long.parseLong(sc.nextLine().trim());
                switch (type) {
                    case ("MIN"):
                        if (result > limit) {
                            end = true;
                        } else {
                            System.out.print("Вы ввели не подходящее значение. " + "Оно должно быть больше " + limit + ". Попробуйте снова: ");
                        }
                        break;
                    case ("MAX"):
                        if (result < limit) {
                            end = true;
                        } else {
                            System.out.print("Вы ввели не подходящее значение. " + "Оно должно быть меньше " + limit + ". Попробуйте снова: ");
                        }
                        break;
                }
            } catch (NumberFormatException ex) {
                System.out.print("Вы должны ввести число, попробуйте снова: ");
            }
        }

        return result;
    }
}
