package test.lab.common.commands;

public class Clear extends Command{
    public final  CommandReceiver commandReceiver;

    public Clear(CommandReceiver commandReceiver){
        this.commandReceiver = commandReceiver;
    }

    @Override
    protected void execute(String[] args){
        if (args.length > 1){
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде clear.");

        }

        commandReceiver.clear();
    }

    @Override
    protected void writeInfo(){
        System.out.println("Команда clear - очистить коллекцию");
    }

}
