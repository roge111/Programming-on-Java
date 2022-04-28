package test.lab.common.commands;

import test.lab.common.Collection.CollectionManager;
import test.lab.common.Untils.OutputManager;

public class Save extends Command {

    public final CollectionManager collectionManager;

    public Save(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    protected void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Введен не ненужный аргумент. Команда приведена к базовой команде save");

        }
        collectionManager.save();

    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда save – сохранить коллекцию в файл.");
    }
}
