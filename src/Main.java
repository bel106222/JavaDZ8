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
Пользователь с клавиатуры вводит путь к файлу,
содержащему набор чисел. После чего запускаются две
потока. Первый поток создает новый файл, в который
запишет только четные элементы массива. Второй поток
создает новый файл, в который запишет только нечетные элементы
массива. Количество четных и нечетных
элементов возвращается в метод та.
*/
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        task1();
        task2();
    }

    public static void task1() {
        System.out.print("Введите размер массива:>");
        int size = scanner.nextInt();
        int[] array = new int[size];

        System.out.println("Введите элементы массива:");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }

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
        scanner.close();
    }
    public static void task2() {
        System.out.print("Введите размер массива:>");
        int size = scanner.nextInt();
        int[] array = new int[size];

        System.out.println("Введите элементы массива:");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }

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
        scanner.close();
    }
}
