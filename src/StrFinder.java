import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class StrFinder extends Thread{
    private final String strFind;
    private final String filePath = "numbers.txt";
    private int stringNumber;

    public StrFinder(String strFind) {
        this.strFind = strFind;
        this.stringNumber = 0;
    }
    @Override
    public void run() {
        // Чтение чисел из файла
        List<String> strList = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            while (fileScanner.hasNextInt()) {
                strList.add(fileScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + filePath);
            return;
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла.");
            return;
        }
        System.out.println("Считаны данные: " + strList);

        int count = 1;
        for (String value : strList) {
            if (Objects.equals(strFind, value)) {
                stringNumber = count;
            }
            count++;
        }
    }

    public String getStringNumber() {
        return (stringNumber > 0) ? ("Искомое значение найдено в строке " + stringNumber) : ("Строка " + strFind + " в файле не найдена.");
    }
}
