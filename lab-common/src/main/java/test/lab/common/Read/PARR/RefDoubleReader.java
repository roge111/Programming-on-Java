package test.lab.common.Read.PARR;

import java.util.Scanner;

public class RefDoubleReader {
    public static Double read(String messageForConsole, int limit, String type) {
        System.out.println(messageForConsole);
        Scanner sc = new Scanner(System.in);
        double result = 0;
        boolean end = false;
        while (!end) {
            try {
                result = Double.parseDouble(sc.nextLine().trim());
                switch (type) {
                    case ("MIN"):
                        if (result > limit) {
                            end = true;
                        } else {

                            System.out.println("Вы ввели не подходящее значение. " + "Оно должно быть больше " + limit + ". Попробуйте снова: ");

                        }
                        break;
                    case ("MAX"):
                        if (result < limit) {
                            end = true;
                        } else {
                            System.out.println("Вы ввели не подходящее значение. " + "Оно должно быть меньше " + limit + ". Попробуйте снова: ");
                        }
                        break;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Введите число заново.");
            }
        }

        return result;


    }


}
