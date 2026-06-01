package com.objectville.entity.cells.utilityProviders;

public class WaterPumpingStation extends UtilityProvider {
    private int TotalWater;

    public WaterPumpingStation(int row, int column){
        super(row, column);
        this.TotalWater = super.getCapacity();
    }

    public int getTotalWater() {
        return TotalWater;
    }
    
    public void setTotalWater(int totalWater) {
        this.TotalWater = totalWater;
    }

    // When the resource is Consumed Simulation Runner can call this
    public void decTotalWater(int amount) {
        TotalWater = Math.max(0, TotalWater - amount);
    }
    
    @Override
    public void refresh(){
        this.TotalWater = super.getCapacity();
    }

}
