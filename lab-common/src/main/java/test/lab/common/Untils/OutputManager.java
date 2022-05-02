package test.lab.common.Untils;

import java.io.PrintWriter;


public class OutputManager {

    private final PrintWriter printWriter;
    private final Massegenf massegenf = Massegenf.ON;

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



    private enum Massegenf {
        ON,
        OFF
    }


}
