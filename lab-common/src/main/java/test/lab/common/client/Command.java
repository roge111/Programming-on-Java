package test.lab.common.client;

public enum Command {
    ADD(0),
    HELP(0),
    INFO(0),
    SHOW(0),
    UPDATE(1),
    REMOVE_BY_ID(1),
    CLEAR(0),
    SAVE(1),
    EXECUTE_SCRIPT(1),
    EXIT(0),
    REMOVE_FIRST(0),
    HEAD(0),
    REMOVE_GREATER(0),
    REMOVE_ALL_BY_MANUFACTURE_COST(1),
    COUNT_BY_MANUFACTURER(1),
    COUNT_GREATER_THAN_MANUFACTURER(1);

    private final int argsCount;

    Command(int argsCount) {
        this.argsCount = argsCount;
    }

    public boolean isValidArgsCount(int count) {
        return this.argsCount == count;
    }

    /**
     * Преобразует введенное название команды из строки в соответствующий класс Command, и возвращает true, если все успешно
     *
     * @param command название команды
     * @return
     */
    public static boolean isValidCommand(String command) {
        try {
            Command.valueOf(command);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

}
