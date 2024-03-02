package com.company;

public class Main {

    public static void main(String[] args) {
	    // паттерн (обработки запросов), грубо говоря нам нужно что-бы наше сообщение ушло
        // через консоль -> файл -> БД
        // для этого и создаём обработчик запросов

        // здесь мы создаем 2 вида принтеров
        MessagePrinter mpConsole = new ConsoleMessagePrinter();
        FileMessagePrinter mpFile = new FileMessagePrinter();

        // и сетаем один в другой
        mpConsole.setMessagePrinter(mpFile);
        // или вообще новый
        mpFile.setMessagePrinter(new BDMessagePrinter());

        // а теперь само сообщение
        mpConsole.print("hi");
    }
}

// главный класс обработки запросов
abstract class MessagePrinter{
    // следующий после запроса
    MessagePrinter nextMP;

    void setMessagePrinter(MessagePrinter MP) {
        nextMP = MP;
    }

    void print(String massage){
        printMassege(massage);
        if (nextMP!=null){
            nextMP.print(massage);
        }
    }

    // сами действия (т.е. вывод на экран)
    abstract void printMassege(String massage);
}

//А здесь все классы запросов (действий, надстройкой)
class ConsoleMessagePrinter extends MessagePrinter{
    @Override
    void printMassege(String massage) {
        System.out.println("print console: " + massage);
    }
}

class FileMessagePrinter extends MessagePrinter{
    @Override
    void printMassege(String massage) {
        System.out.println("print file: " + massage);
    }
}

class BDMessagePrinter extends MessagePrinter{
    @Override
    void printMassege(String massage) {
        System.out.println("print db: " + massage);
    }
}