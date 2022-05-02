package test.lab.common.commands;

public class RemoveFirst extends Command {

    private final CommandReceiver commandReceiver;

    public RemoveFirst(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    @Override
    protected void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Введен ненужный аргумент. Команда приведена к базовой команде remove_first.");

        }

        commandReceiver.removeFirst();
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда remove_first - удаляет первый элемент из коллекции");
    }
}
