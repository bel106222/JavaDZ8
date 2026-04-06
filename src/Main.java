/*
Задание 1
Пользователь с клавиатуры вводит значения в массив. После чего
запускаются две потока. Первый поток находит максимум в массиве.
Второй поток находит минимум в массиве. Результаты вычислений
возвращаются в метод main.
Задание 2
Пользователь с клавиатуры вводит значения в массив.
После чего запускаются две потока. Первый поток находит сумму
элементов в массиве. Второй поток находит среднеарифметическое
в массиве. Результаты вычислений возвращаются в метод main.
Задание 3
Пользователь с клавиатуры вводит путь к файлу, содержащему набор чисел.
После чего запускаются две потока. Первый поток создает новый файл,
в который запишет только четные элементы массива. Второй поток создает
новый файл, в который запишет только нечетные элементы массива.
Количество четных и нечетных элементов возвращается в метод main.
Задание 4
Пользователь с клавиатуры вводит путь к файлу и слово для поиска.
После чего запускается поток. Он ищет это слово в файле. Результат поиска
возвращается в main.
*/
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
        scanner.close();
    }

    public static void task1() {

        int [] array = createArray();
        MaxFinder maxThread = new MaxFinder(array);
        MinFinder minThread = new MinFinder(array);
        maxThread.start();
        minThread.start();
        try {
            maxThread.join();
            minThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Максимум: " + maxThread.getMax());
        System.out.println("Минимум: " + minThread.getMin());
    }
    public static void task2() {

        int [] array = createArray();
        SumFinder sumThread = new SumFinder(array);
        AvgFinder avgThread = new AvgFinder(array);
        sumThread.start();
        avgThread.start();
        try {
            sumThread.join();
            avgThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Сумма элементов: " + sumThread.getSum());
        System.out.println("Среднее арифметическое: " + avgThread.getAvg());
    }
    public static int[] createArray () {
        System.out.print("Введите размер массива:>");
        int size = scanner.nextInt();
        int[] array = new int[size];

        System.out.println("Введите элементы массива:");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }
        return array;
    }
    public static void task3() {
        System.out.print("Введите путь к файлу с числами:>");
        //String filePath = scanner.nextLine();
        String filePath = "numbers.txt";
        System.out.println(filePath);

        // Чтение чисел из файла
        List<Integer> numbersList = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            while (fileScanner.hasNextInt()) {
                numbersList.add(fileScanner.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + filePath);
            return;
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла.");
            return;
        }
        System.out.println("Считаны данные: " + numbersList);

        // Преобразование списка в массив
        int[] numbers = new int[numbersList.size()];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = numbersList.get(i);
        }

        // Создание и запуск потоков
        EvenFinder evenThread = new EvenFinder(numbers);
        OddFinder oddThread = new OddFinder(numbers);
        evenThread.start();
        oddThread.start();

        // Ожидание завершения потоков
        try {
            evenThread.join();
            oddThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Количество чётных элементов: " + evenThread.getEvenCount());
        System.out.println("Количество нечётных элементов: " + oddThread.getOddCount());
    }
    public static void task4() {
        System.out.print("Введите искомую строку:>");
        //String strFind = scanner.nextLine();
        String strFind = "19";
        System.out.println(strFind);

        StrFinder strThread = new StrFinder(strFind);
        strThread.start();
        try {
            strThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Результат поиска: " + strThread.getStringNumber());
    }
}
