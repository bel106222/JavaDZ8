import java.io.*;
public class EvenFinder extends Thread{
    private final int[] numbers;
    private int evenCount;

    public EvenFinder(int[] numbers) {
        this.numbers = numbers;
        this.evenCount = 0;
    }

    @Override
    public void run() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("even_numbers.txt"))) {
            for (int num : numbers) {
                if (num % 2 == 0) {
                    writer.write(num + "\n");
                    evenCount++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getEvenCount() {
        return evenCount;
    }
}