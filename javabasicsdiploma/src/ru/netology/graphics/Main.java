package ru.netology.graphics;

import ru.netology.graphics.image.TextGraphicsConverterImpl;

import java.io.File;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) throws Exception {
        TextGraphicsConverterImpl converter = new TextGraphicsConverterImpl();

//        GServer server = new GServer(converter);
//        server.start();

        // Или то же, но с сохранением в файл:

//        PrintWriter fileWriter = new PrintWriter(new File("converted-image.txt"));
//        converter.setMaxWidth(200);
//        converter.setMaxHeight(300);
//        fileWriter.write(converter.convert("https://i.ibb.co/6DYM05G/edu0.jpg"));
//        fileWriter.close();

//    То же, но считываем из файла на компе, а потом сохраняем на комп.
        PrintWriter fileWriter = new PrintWriter(new File("BBB.txt"));
        fileWriter.write(converter.convert("DSC_5059.jpg"));

    }
}
