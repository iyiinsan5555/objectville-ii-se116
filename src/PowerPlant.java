public class PowerPlant extends UtilityProvider{
    private int totalElectric;

    public PowerPlant(int row, int column){
        super(row, column);
        this.totalElectric = super.getCapacity();
    }

    public int getTotalElectric() {
        return totalElectric;
    }

    // Resets Totol Amount After Every Tick
    public void setTotalElectric(int totalElectric) {
        this.totalElectric = super.getCapacity();
    }

    public void decTotalElectric(int amount) {
        totalElectric -= amount;
    }
}