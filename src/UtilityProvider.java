public class UtilityProvider extends Cell{
    private final int capacity = 100;

    public UtilityProvider(int row, int column){
        super(row, column);
    }

    public int getCapacity() {
        return capacity;
    }
}