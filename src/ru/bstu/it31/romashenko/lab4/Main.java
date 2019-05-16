package ru.bstu.it31.romashenko.lab4;

import java.util.Scanner;
import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {
    public static void main(String[] args) {
        // Переменная для хренения анализируемого текста
        String content = "";
        String resualt = "";
        // Считываем данные из файла
        try (Scanner scannerFile = new Scanner( new File("index.html"), "UTF-8" )) {
            content = scannerFile.useDelimiter("\\A").next();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        // Инизиализует сканнер для консоли
        Scanner scanner = new Scanner(System.in);
        // Анализ ссылок в теге <a>
        Pattern patternUrlTagA = Pattern.compile("<a(.*?)href=([\'\"])(.*?)([\'\"])(.*?)>(.*?)</a>");
        Matcher matcher = patternUrlTagA.matcher(content);
        while (matcher.find()) {
            resualt += matcher.group(3) + " " + matcher.group(6) + "\n";
        }
        // Анализ ссылок отличных от тега <a>
        patternUrlTagA = Pattern.compile("<[^a]*(.*?)>(.*?)[\\s]?(http://[a-z0-9/?=-_&.]+)[\\s]?(.*?)</[^a]*>");
        matcher = patternUrlTagA.matcher(content);
        while (matcher.find()) {
            resualt += matcher.group(3) + "\n";
        }
        // Вывод на экран результат
        System.out.println(resualt);
        // Запись в файл результат
        try (PrintWriter printer = new PrintWriter("output.txt");) {
            printer.write(resualt);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        // Конец
        scanner.close();
    }
}