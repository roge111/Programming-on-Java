package test.lab.common.commands;

import test.lab.common.client.Organization;

public class CountGreaterThanManufacturer extends Command{


    public final CommandReceiver commandReceiver;

    public CountGreaterThanManufacturer(CommandReceiver commandReceiver){
        this.commandReceiver = commandReceiver;
    }

    @Override
    protected void execute(String[] args) {
        if (args.length > 2){
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде count_greater_than_manufacturer.");

        }

        commandReceiver.count_greater_than_manufacturer(args[1]);
    }

    @Override
    protected void writeInfo(){
        System.out.println("Команда count_greater_than_manufacturer - выводит количество элементов, значение поля manufacturer которых больше заданного");
    }
}
