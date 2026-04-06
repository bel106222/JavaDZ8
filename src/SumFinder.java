public class SumFinder extends Thread{
    private final int[] array;
    private int sum;

    public SumFinder(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        sum = 0;
        for (int value : array) {
            sum += value;
        }
    }

    public int getSum() {
        return sum;
    }
}