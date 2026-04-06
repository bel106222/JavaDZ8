public class AvgFinder extends Thread{
    private final int[] array;
    private double avg;

    public AvgFinder(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        avg = (double)sum/array.length;
    }

    public double getAvg() {
        return avg;
    }
}