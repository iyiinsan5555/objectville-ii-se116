public class WaterPumpingStation extends UtilityProvider {
    private int TotalWater;

    public WaterPumpingStation(int row, int column){
        super(row, column);
        this.TotalWater = super.getCapacity();
    }

    public int getTotalWater() {
        return TotalWater;
    }

    // Resets Totol Amount After Every Tick
    public void setTotalWater(int totalWater) {
        this.TotalWater = super.getCapacity();
    }

    public void decTotalWater(int amount) {
        TotalWater -= amount;
    }

}
