package test.lab.common.utils;


import test.lab.common.client.OrganizationType;
import test.lab.common.client.UnitOfMeasure;

import java.util.Arrays;

public final class MsgConsts {

    public static final String FIRST_MSG = "Введите первую команду. Чтобы узнать все команды введите 'help': ";
    public static final String UNKNOWN_COMMAND_MSG = "Неизвестная команда. Повторите ввод: ";
    public static final String COMMAND_INPUT = "Введите команду: ";
    public static final String ERROR_STR_MSG = "Значение пустое или не совпадает заданному типу. Повторите ввод: ";
    public static final String ERROR_PRICE_MSG = "Цена должна быть больше нуля. Повторите ввод: ";
    public static final String ERROR_X_MSG = "Значение X не должно быть больше 814. Повторите ввод: ";
    public static final String ERROR_Y_MSG = "Значение Y не должно быть больше 85. Повторите ввод: ";
    public static final String ERROR_ORGANIZATION_FULL_NAME_MSG = "Полное название не должно превышать 1435 символов. Повторите ввод: ";
    public static final String X_COORD_MSG = "Введите X: ";
    public static final String Y_COORD_MSG = "Введите Y: ";
    public static final String Z_COORD_MSG = "Введите Z: ";
    public static final String TOWN_COORD_MSG = "Введите название города: ";
    public static final String ARGS_COUNT_MSG = "Неправильное количество аргументов. Повторите ввод: ";
    public static final String FILE_NOT_FOUND_MSG = "Файл не найден";
    public static final String GET_ID_MSG = "Введите ID элемента: ";
    public static final String ELEMENT_ADD_MSG = "Элемент добавлен в коллекцию.";
    public static final String ERROR_NOT_ID_UPDATE_MSG = "Элемента с таким ID нет в коллекции.";
    public static final String ERROR_NFE_MSG = "Команда не выполнена. Был введен некорректный аргумент.";
    public static final String CLEAR_MSG = "Коллекция успешно очищена.";
    public static final String EXIT_MSG = "Завершение работы программы.";
    public static final String REMOVE_FIRST_MSG = "Первый элемент удален.";
    public static final String SUCCESSFUL_MSG = "Команда выполнена.";
    public static final String COUNT_INFO_MSG = "Количество элементов: ";
    public static final String ELEMENT_NOT_FOUND_MSG = "Таких элементов не найдено";
    public static final String ERROR_GET_BY_ID_MSG = "Элемент с данным ID не найден";
    public static final String ERROR_EXECUTE_SCRIPT_MSG = "Скрипт не выполнен";
    public static final String INIT_ERROR_MSG = "Не удалось проинициализировать список";
    public static final String HELP_MSG =
            "\nhelp: вывести справку по доступным командам"
                    + "\ninfo: вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)"
                    + "\nshow: вывести в стандартный поток вывода все элементы коллекции в строковом представлении"
                    + "\nadd: добавить новый элемент в коллекцию"
                    + "\nupdate 'id': обновить элемент коллекции id которого равен заданному"
                    + "\nremove_by_id 'id': удалить элемент из коллекции по его id"
                    + "\nclear: очистить коллекцию"
                    + "\nsave: сохранить коллекцию в файл"
                    + "\nexecute_script 'file_name': считать и исполнить скрипт из файла file_name"
                    + "\nexit: завершить программу"
                    + "\nremove_first: удалить первый элемент из коллекции"
                    + "\nhead: вывести первый элемент коллекции"
                    + "\nremove_greater 'element': удалить из коллекции все элементы превыщающие заданный"
                    + "\nremove_all_by_manufacturer 'manufacturer': удалить из коллекции все элементы, значение поля manufacturerCost которого эквивалентно заданному"
                    + "\ncount_by_manufacturer 'manufacturer': вывести количество элементов, значение поля manufacturer которых равно заданному"
                    + "\ncount_greater_than_manufacturer 'manufacturer': вывести количество элементов, значение поля manufacturer которых больше заданного\n";

    private MsgConsts() {

    }

    public static class Product {
        public static final String NAME = "Введите название продукта: ";
        public static final String PRICE = "Введите цену: ";
        public static final String NUMBER = "Введите номер продукта: ";
        public static final String COST = "Введите стоимость изготовления: ";
        public static final String UNIT = "Введите единицу измерения " + Arrays.toString(UnitOfMeasure.values()) + ": ";
    }

    public static class Address {
        public static final String STREET = "Введите название улицы: ";
        public static final String CITY = "Введите название города: ";
    }

    public static class Organization {
        public static final String NAME = "Введите название производства: ";
        public static final String FULL_NAME = "Введите полное название производства: ";
        public static final String ORGANIZATION_TYPE = "Введите тип организации " + Arrays.toString(OrganizationType.values()) + ": ";
    }
}
