package test.lab.common.Read.PARR;

import java.util.Scanner;

public final class StringReader {
    private StringReader() {
    }

    public static String read(String messageForConsile, boolean canBenull) {
        Scanner in = new Scanner(System.in);
        System.out.println(messageForConsile);
        String readString = in.nextLine().trim();

        while (!canBenull && ("").equals(readString)) {
            System.out.println("Данное поле не может быть пустым. " + messageForConsile);
            readString = in.nextLine().trim();
        }

        if (canBenull && ("").equals(readString)) {
            readString = null;
        }
        return readString;

    }
}
