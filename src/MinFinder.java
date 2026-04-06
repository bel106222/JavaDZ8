public class MinFinder extends Thread{
    private final int[] array;
    private int min;

    public MinFinder(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        min = array[0];
        for (int value : array) {
            if (value < min) {
                min = value;
            }
        }
    }

    public int getMin() {
        return min;
    }
}