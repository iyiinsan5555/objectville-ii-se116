package com.objectville.entity.cells.utilityProviders;

public class PowerPlant extends UtilityProvider {
    private int totalElectric;

    public PowerPlant(int row, int column){
        super(row, column);
        this.totalElectric = super.getCapacity();
    }

    public int getTotalElectric() {
        return totalElectric;
    }

    public void setTotalElectric(int totalElectric) {
        this.totalElectric = totalElectric;
    }
    
    // When the resource is Consumed Simulation Runner can call this
    public void decTotalElectric(int amount) {
        totalElectric = Math.max(0, totalElectric - amount);
    }

    @Override
    public void refresh(){
        this.totalElectric = super.getCapacity();
    }
}