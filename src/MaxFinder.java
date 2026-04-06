public class MaxFinder extends Thread{
    private final int[] array;
    private int max;

    public MaxFinder(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        max = array[0];
        for (int value : array) {
            if (value > max) {
                max = value;
            }
        }
    }

    public int getMax() {
        return max;
    }
}