package test.lab.common.Untils;

import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;


public class OutputManager {

    private final PrintWriter printWriter;
    private Massegenf massegenf = Massegenf.ON;

    private enum Massegenf {
        ON,
        OFF
    }

    public OutputManager(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    public void println(String string) {
        if (massegenf.equals(Massegenf.ON)) {
            printlnImportantMessage(string);
        }
    }

    public void printlnImportantMessage(String string) {
        printWriter.write(string);
        printWriter.write("\n");
    }

    public void printlnImportantColorMessage(String string, Color color) {
        printlnImportantMessage(color.toString() + string + "\u001B[0m");
    }


}
