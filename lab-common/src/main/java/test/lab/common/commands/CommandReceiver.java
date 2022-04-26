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
    private final CommandInvoker commandInvoker;

    public CommandReceiver(CommandInvoker commandInvoker) {
        this.commandInvoker = commandInvoker;
    }

     public void help(){
        commandInvoker.getCommandMap().forEach((name, command) -> command.writeInfo());
     }
    public  void  count_greater_than_manufacturer(String manufacturer){
        CollectionManager.count_greater_than_manufacturer(manufacturer);
    }

     public void info(){
        CollectionManager.getInfo();
     }

     public  void show(){
        CollectionManager.show();
     }

     public void  add(){
        CollectionManager.add(ElementCreator.creatProduct());
        System.out.println("Элемент добавлен в коллекцию.");
     }

     public void update(String ID){
         Integer productId;
         try{
             productId = Integer.parseInt(ID);
             if (CollectionUntils.checkExist(productId)){
                 CollectionManager.update(ElementCreator.creatProduct(), productId);

             } else System.out.println("Элемента с таким ID нет в коллекции.");

         } catch (NumberFormatException e){
             System.out.println("Команда не выполнена. Был введен некорректный аргумент.");

         }
     }

     public void remivw_by_id(String ID){
        Integer productId;
        try{
            productId = Integer.parseInt(ID);
            CollectionManager.remove_by_id(productId);
        } catch (NumberFormatException e){
            System.out.println("Команда не выполнена. Был введен некорректный аргумент.");
        }
     }

     public void clear(){
        CollectionManager.clear();
        System.out.println("Коллекция успешно очищена.");
     }

     public void exit(){
        System.out.println("Завершение работы программы.");
        System.exit(0);
     }

     public void head(){
        CollectionManager.head();
     }



     public void remove_all_by_manufacture_cost(Integer manufactureCost){
        CollectionManager.remove_all_by_manufacture_cost(manufactureCost);
     }

     public void remove_greater(Integer id){
        CollectionManager.remove_greater(id);
     }

     public  void executeScript(String path){
        String line;
        String command;
        ArrayList<String> parameters = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(path)), StandardCharsets.UTF_8))){

            while((line = bufferedReader.readLine()) != null){
                if (line.split(" ")[0].matches("add|update|")){
                    command = line;
                    for (int i = 0; i < 15; i++){
                        if (line != null) {
                            line = bufferedReader.readLine();
                            parameters.add(line);
                        } else {
                            System.out.println("Не хватка праметров для создания объектов.");
                            break;
                        }
                    }

                    Product product = ElementCreator.creatScriptProduct(parameters);

                    switch (command.split(" ")[0]){
                        case "add":
                            CollectionManager.add(product);
                            break;
                        case "update":
                            CollectionManager.update(product, Integer.parseInt(command.split(" ")[1]));

                    }
                } else if (line.split(" ")[0].equals("execute_script")
                        && line.split(" ")[1].equals(ExecuteScript.getPath()) ){
                    System.out.println("Пресечена попытка рекурсивного вызова скрипта.");
                } else commandInvoker.executeCommand(line.split(" "));
            }

        } catch (IOException e){
            System.out.println("Ошибка!" + e.getMessage());

        }

     }

     public void remove_first(){
        CollectionManager.remove_first();
        System.out.println("Первый элемент удален.");
     }

    public void count_by_manufacturer(String manufacturer){
        CollectionManager.count_by_manufacturer(manufacturer);
    }






}
