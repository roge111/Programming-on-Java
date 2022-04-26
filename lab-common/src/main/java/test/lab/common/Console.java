package test.lab.common;

import test.lab.common.Collection.CollectionManager;
import test.lab.common.commands.*;

import java.io.IOException;
import java.util.Scanner;

public class Console {
    void startInteractiveMode() throws IOException{
        CommandInvoker commandInvoker = new CommandInvoker();
        CommandReceiver commandReceiver = new CommandReceiver(commandInvoker);
        System.out.println("Введите первую команду. Чтобы узнать все команды введите 'help'.");
        Scanner fileName = new Scanner(System.in);
        CollectionManager collectionManager = new CollectionManager();
        CollectionManager.initList();


        commandInvoker.register("add", new Add(commandReceiver));
        commandInvoker.register("update", new Update(commandReceiver));
        commandInvoker.register("help", new Help(commandReceiver));
        commandInvoker.register("save", new Save(collectionManager));
        commandInvoker.register("show", new Show(commandReceiver));
        commandInvoker.register("remove_by_id", new RemoveByID(commandReceiver));
        commandInvoker.register("head", new Head(commandReceiver));
        commandInvoker.register("clear", new Clear(commandReceiver));
        commandInvoker.register("exit", new Exit(commandReceiver));
        commandInvoker.register("execute_script", new ExecuteScript(commandReceiver));
        commandInvoker.register("remove_first", new RemoveFirst(commandReceiver));
        commandInvoker.register("count_by_manufacturer manufacturer ", new CountByManufacturer(commandReceiver));
        commandInvoker.register("remove_all_by_manufacture_cost", new RemoveAllByManufactureCost(commandReceiver));
        commandInvoker.register("count_greater_than_manufacturer", new CountGreaterThanManufacturer(commandReceiver));
        commandInvoker.register("remove_greater", new RemoveGreater(commandReceiver));


        try (Scanner scanner = new Scanner(System.in)){
            while (scanner.hasNextLine()){
                commandInvoker.executeCommand(scanner.nextLine().trim().split(" "));
            }
        }
    }




}
