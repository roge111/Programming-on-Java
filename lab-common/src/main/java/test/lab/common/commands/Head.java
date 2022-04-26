package test.lab.common.commands;

public class Head extends Command{
    public final CommandReceiver commandReceiver;

    public  Head(CommandReceiver commandReceiver){
        this.commandReceiver = commandReceiver;
    }

    @Override
    protected void execute(String[] args){
        if (args.length > 1){
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде head.");
        }
        commandReceiver.head();
    }

    @Override
    protected void writeInfo(){
        System.out.println("Команда head - выводит первый элемент коллекции. ");
    }

}
