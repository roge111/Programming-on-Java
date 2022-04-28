package test.lab.common.commands;

public class Help extends Command {
    public final CommandReceiver commandReceiver;

    public Help(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    @Override
    protected void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде help.");
        }
        commandReceiver.help();
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда help - даёт справку по доступным командам. ");
    }
}
