package com.objectville.entity.cells.zones;


public class Commercial extends Zone {
    private int receivedPopulation;
    private int receivedGoods;
    private int oldLevel;

    public Commercial(int x, int y) {
        super(x, y);
        this.receivedPopulation = 0;
        this.receivedGoods = 0;
        this.oldLevel = 0;
    }

    //Updates output, sets minimum utility demand, changes level, and cleans data.
    @Override
    public void update() {
        int oldLevel = this.getLevel();
        updateLevel();
        this.setOutput(calculateOutput());
        this.setUtilityDemand(Math.max(1, this.getOutput()));

        System.out.println(getZoneType() + " at " + getLocation() + " generated " + getOutput() + " " + getOutputType().toLowerCase());
        printLevel(oldLevel, getLevel());

        this.resetData();
    }

    @Override
    public void resetData() {
        this.setReceivedElectricity(0);
        this.setReceivedWater(0);
        this.setReceivedInternet(0);
        this.receivedPopulation = 0;
        this.receivedGoods = 0;

        this.setHasSecurity(false);
        this.setHasEducation(false);
        this.setHasHealth(false);
    }

    //Calculates lifestyle output based on level and required utilities (m).
    @Override
    public int calculateOutput() {
        int m = getM(); // Commercial requires Electricity, Water, and Internet

        switch (this.getLevel()) {
            case 1:
                return m;
            case 2:
                return 2 * m;
            case 3: //Level 3 output depends on the minimum value between received population and goods.
                return (2 * m) + Math.min(this.receivedPopulation, this.receivedGoods);
            default:
                return 0;
        }
    }

    //Updates the level of the commercial zone based on rules and services.
    @Override
    public void updateLevel() {
        int m = getM();

        if (m == 0) {
            this.setLevel(0);
            return;
        }

        int currentLevel = this.getLevel();

        if (currentLevel == 0) {
            // Level 1 requires basic utilities (m > 0), population, and goods
            if (this.receivedPopulation > 0 && this.receivedGoods > 0) {
                this.setLevel(1);
            }
        }
        else if (currentLevel == 1) {
            // Upgrade to Level 2 requires maintaining Level 1 conditions + Security service
            if (this.receivedPopulation > 0 && this.receivedGoods > 0 && this.isHasSecurity()) {
                this.setLevel(2);
            }
            // Gradual fall to Level 0 if it loses required resources
            else if (this.receivedPopulation == 0 || this.receivedGoods == 0) {
                this.setLevel(0);
            }
        }
        else if (currentLevel == 2) {
            // If it loses security or required inputs, it drops back to Level 1
            if (this.receivedPopulation == 0 || this.receivedGoods == 0 || !this.isHasSecurity()) {
                this.setLevel(1);
            }
            // Upgrade to Level 3 requires excess population and goods
            else if (this.receivedPopulation - getM() > 0 && this.receivedGoods - getM() > 0) {
                this.setLevel(3);
            }
        }
        else if (currentLevel == 3) {
            // If conditions fail, it drops back to Level 2
            if (this.receivedPopulation == 0 || this.receivedGoods == 0 || !this.isHasSecurity()) {
                this.setLevel(2);
            }
        }
    }

    @Override
    public String getOutputType() {
        return "Lifestyle";
    }

    @Override
    public String getZoneType() {
        return "Commercial";
    }

    //Getter & Setter
    public int getReceivedPopulation() {
        return receivedPopulation;
    }
    public void setReceivedPopulation(int receivedPopulation) {
        this.receivedPopulation = receivedPopulation;
    }
    public int getReceivedGoods() {
        return receivedGoods;
    }
    public void setReceivedGoods(int receivedGoods) {
        this.receivedGoods = receivedGoods;
    }
}
