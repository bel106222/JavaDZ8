import java.io.*;
public class OddFinder extends Thread{
    private final int[] numbers;
    private int oddCount;

    public OddFinder(int[] numbers) {
        this.numbers = numbers;
        this.oddCount = 0;
    }

    @Override
    public void run() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("odd_numbers.txt"))) {
            for (int num : numbers) {
                if (num % 2 != 0) {
                    writer.write(num + "\n");
                    oddCount++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getOddCount() {
        return oddCount;
    }
}
