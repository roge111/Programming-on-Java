package test.lab.common.commands;

public class Info extends Command {
    public final CommandReceiver commandReceiver;

    public Info(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    @Override
    protected void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде info.");
        }
        commandReceiver.info();
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда info - выводит в стандартный поток вывода информацию о коллекции. ");
    }
}
