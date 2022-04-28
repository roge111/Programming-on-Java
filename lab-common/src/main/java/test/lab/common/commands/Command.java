package test.lab.common.commands;

public abstract class Command {
    protected abstract void writeInfo();

    protected abstract void execute(String[] args);
}
