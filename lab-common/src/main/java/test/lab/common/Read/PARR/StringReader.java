package test.lab.common.Read.PARR;

import java.util.Scanner;

public class StringReader {
    public  static  String read(String messageForConsile, boolean canBenull){
        Scanner in = new Scanner(System.in);
        System.out.println(messageForConsile);
        String readString = in.nextLine().trim();

        while (!canBenull && readString.equals("")){
            System.out.println("Данное поле не может быть пустым. " + messageForConsile);
            readString = in.nextLine().trim();
        }

        if (canBenull && readString.equals("")) readString = null;
        return  readString;

    }
}
