package com.objectville.entity.cells.zones;

import com.objectville.entity.cells.Cell;
import com.objectville.interfaces.Transferable;

public abstract class Zone extends Cell implements Transferable {
    private int level;
    private int utilityDemand;
    private int output;

    private boolean hasSecurity;
    private boolean hasEducation;
    private boolean hasHealth;

    private int receivedElectricity;
    private int receivedWater;
    private int receivedInternet;

    public Zone(int x, int y) {
        super(x, y);
        this.level = 0;
        this.utilityDemand = 1;
        this.output = 0;
        resetData();
    }

    public abstract String getUtilityType();

    public abstract boolean canSupply(Cell cell);

    /*
    Updates the zone state.
     1. Updates level based on received resources.
     2. Calculates new output based on the new level.
     3. Sets utility demand for the NexT tick.
     */
    public abstract void update();

    //Resets temporary variables received during a simulation tick.
    public abstract void resetData();

    //Logic for calculating the zone's specific production (population, goods, or lifestyle).
    public abstract int calculateOutput();

    //Handles level transitions (up, down, or immediate drop to 0 if utilities are lost).
    public abstract void updateLevel();

    //Returns the type of resource produced by the zone.
    public abstract String getOutputType();

    //Helper method to calculate 'm' (the minimum amount of required utility delivered)
    public int getM() {
        return Math.min(receivedElectricity, Math.min(receivedWater, receivedInternet));
    }

    // Getters and Setters
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getUtilityDemand() {
        return utilityDemand;
    }
    public void setUtilityDemand(int utilityDemand) {
        this.utilityDemand = Math.max(1, utilityDemand);
    }
    public int getOutput() {
        return output;
    }
    public void setOutput(int output) {
        this.output = output;
    }

    public boolean isHasSecurity() {
        return hasSecurity;
    }
    public void setHasSecurity(boolean hasSecurity) {
        this.hasSecurity = hasSecurity;
    }
    public boolean isHasEducation() {
        return hasEducation;
    }
    public void setHasEducation(boolean hasEducation) {
        this.hasEducation = hasEducation;
    }
    public boolean isHasHealth() {
        return hasHealth;
    }
    public void setHasHealth(boolean hasHealth) {
        this.hasHealth = hasHealth;
    }

    public int getReceivedElectricity() {
        return receivedElectricity;
    }
    public void setReceivedElectricity(int receivedElectricity) {
        this.receivedElectricity = receivedElectricity;
    }
    public int getReceivedWater() {
        return receivedWater;
    }
    public void setReceivedWater(int receivedWater) {
        this.receivedWater = receivedWater;
    }
    public int getReceivedInternet() {
        return receivedInternet;
    }
    public void setReceivedInternet(int receivedInternet) {
        this.receivedInternet = receivedInternet;
    }
}
