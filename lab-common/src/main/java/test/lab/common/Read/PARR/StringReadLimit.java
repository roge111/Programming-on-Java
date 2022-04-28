package test.lab.common.Read.PARR;

import java.util.Scanner;

public final class StringReadLimit {
    private StringReadLimit() {
    }

    public static String read(String messageForConsile, boolean canBenull, Integer maxl) {
        Scanner in = new Scanner(System.in);
        System.out.println(messageForConsile);
        String readString = in.nextLine().trim();
        String maxl2 = String.valueOf(maxl);

        while (!canBenull && ("").equals(readString) && readString.length() > maxl) {
            System.out.println("Данное поле не может быть пустым и быть длинее " + maxl2 + ". " + messageForConsile);
            readString = in.nextLine().trim();
        }

        if (canBenull && ("").equals(readString)) {
            readString = null;
        }
        return readString;

    }
}
