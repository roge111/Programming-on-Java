package test.lab.common.commands;

public class RemoveGreater extends Command {
    public CommandReceiver commandReceiver;

    public RemoveGreater(CommandReceiver commandReceiver){
        this.commandReceiver = commandReceiver;
    }

    @Override
    protected void execute(String[] args){
        if (args.length > 2){
            System.out.println("Введен ненужный аргумент. Команда приведена к базовой команде remove_geter.");

        }

        commandReceiver.remove_greater(Integer.getInteger(args[1]));
    }

    @Override
    protected void writeInfo(){
        System.out.println("Команда remove_greater- удаляет из коллекции все элементы, превышающие заданный");
    }
}

