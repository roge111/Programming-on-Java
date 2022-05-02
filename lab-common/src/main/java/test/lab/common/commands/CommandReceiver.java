package test.lab.common.commands;

import test.lab.common.client.Product;
import test.lab.common.Collection.CollectionManager;
import test.lab.common.Collection.CollectionUntils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.nio.charset.StandardCharsets;


public class CommandReceiver {
    static final int C151 = 15;
    private final CommandInvoker commandInvoker;

    private final int c15 = C151;

    public CommandReceiver(CommandInvoker commandInvoker) {
        this.commandInvoker = commandInvoker;
    }

    public void help() {
        commandInvoker.getCommandMap().forEach((name, command) -> command.writeInfo());
    }

    public void countGreaterThanManufacturer(String manufacturer) {
        CollectionManager.countGreaterThanManufacturer(manufacturer);
    }

    public void info() {
        CollectionManager.getInfo();
    }

    public void show() {
        CollectionManager.show();
    }

    public void add() {
        CollectionManager.add(ElementCreator.creatProduct());
        System.out.println("Элемент добавлен в коллекцию.");
    }

    public void update(String iD) {
        Integer productId;
        try {
            productId = Integer.parseInt(iD);
            if (CollectionUntils.checkExist(productId)) {
                CollectionManager.update(ElementCreator.creatProduct(), productId);

            } else {
                System.out.println("Элемента с таким ID нет в коллекции.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Команда не выполнена. Был введен некорректный аргумент.");

        }
    }

    public void remiveById(String iD) {
        Integer productId;
        try {
            productId = Integer.parseInt(iD);
            CollectionManager.removeById(productId);
        } catch (NumberFormatException e) {
            System.out.println("Команда не выполнена. Был введен некорректный аргумент.");
        }
    }

    public void clear() {
        CollectionManager.clear();
        System.out.println("Коллекция успешно очищена.");
    }

    public void exit() {
        System.out.println("Завершение работы программы.");
        System.exit(0);
    }

    public void head() {
        CollectionManager.head();
    }


    public void removeAllByManufactureCost(Integer manufactureCost) {
        CollectionManager.removeAllByManufactureCost(manufactureCost);
    }

    public void removeGreater(Integer id) {

        CollectionManager.removeGreater(id);
    }

    public void executeScript(String path) {
        String line;
        String command;
        ArrayList<String> parameters = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(path)), StandardCharsets.UTF_8))) {

            while ((line = bufferedReader.readLine()) != null) {
                if (line.split(" ")[0].matches("add|update|")) {
                    command = line;
                    for (int i = 0; i < c15; i++) {
                        if (line != null) {
                            line = bufferedReader.readLine();
                            parameters.add(line);
                        } else {
                            System.out.println("Не хватка параметров для создания объектов.");
                            break;
                        }
                    }
                    Product product = ElementCreator.creatScriptProduct(parameters);
                    switch (command.split(" ")[0]) {
                        case "add":
                            CollectionManager.add(product);
                            break;
                        case "update":
                            CollectionManager.update(product, Integer.parseInt(command.split(" ")[1]));
                            break;
                        default: throw new IllegalStateException("Unexpected value: " + command.split(" ")[0]);
                    }
                } else if (line.split(" ")[0].equals("execute_script") && line.split(" ")[1].equals(ExecuteScript.getPath())) {
                    System.out.println("Пресечена попытка рекурсивного вызова скрипта.");
                } else {
                    commandInvoker.executeCommand(line.split(" "));
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка!" + e.getMessage());
        }
    }

    public void removeFirst() {
        CollectionManager.removeFirst();
        System.out.println("Первый элемент удален.");
    }

    public void countByManufacturer(String manufacturer) {
        CollectionManager.countByManufacturer(manufacturer);
    }


}
