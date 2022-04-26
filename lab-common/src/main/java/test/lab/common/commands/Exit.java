package test.lab.common.commands;

public class Exit extends Command{
    private  final CommandReceiver commandReceiver;

    public Exit (CommandReceiver commandReceiver){
        this.commandReceiver = commandReceiver;
    }

    @Override
    protected  void execute(String[] args){
        if (args.length > 1){
            System.out.println("Введен не нужный аргумент. Команда приведена к базовому виду команды exit");

        }
        commandReceiver.exit();


    }
    @Override
    protected void writeInfo(){
        System.out.println( "Команда exit - завершить программу без сохранения.");

    }


}
