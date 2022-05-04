package test.lab.common.commands;

import test.lab.common.Collection.CollectionManager;

public class Save extends Command {

    public final CommandReceiver commandReceiver;

    public Save(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    @Override
    protected void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Введен не ненужный аргумент. Команда приведена к базовой команде save");

        }
        commandReceiver.save();

    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда save – сохранить коллекцию в файл.");
    }
}
