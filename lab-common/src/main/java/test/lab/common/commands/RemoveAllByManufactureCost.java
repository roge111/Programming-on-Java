package test.lab.common.commands;

public class RemoveAllByManufactureCost extends Command {
    public final CommandReceiver commandReceiver;

    public RemoveAllByManufactureCost(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    @Override
    protected void execute(String[] args) {
        if (args.length > 2) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде remove_all_by_manufacture_cost.");

        } else if (args.length == 0) {
            System.out.println("Вы не ввели аргумент поиска. Пожалуйста, вызовите команду еще раз с одним аргументом.");

        }
        if (args.length > 0) {
            commandReceiver.removeallbymanufacturecost(Integer.getInteger(args[1]));
        }
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда remove_all_by_manufacture_cost - удалить из коллекции все элементы, значение поля manufactureCost которого эквивалентно заданному");
    }


}
