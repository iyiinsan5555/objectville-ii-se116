package com.objectville.entity.cells.zones;

public class Commercial extends Zone {
    private int receivedPopulation;
    private int receivedGoods;
    private int prevLevel;

    public Commercial(int x, int y) {
        super(x, y);
        this.receivedPopulation = 0;
        this.receivedGoods = 0;
    }

    //Updates output, sets minimum utility demand, changes level, and cleans data.
    @Override
    public void update() {

        updateLevel();
        this.setOutput(calculateOutput());
        this.setUtilityDemand(Math.max(1, this.getOutput()));

        System.out.printf("Commercial at %s generated %d lifestyle", this.toString(), getOutput());
        
        if(this.getLevel() < prevLevel){
            System.out.printf("Commercial at (%d,%d) levels down from %d to %d", this.toString(), this.getLevel(), prevLevel);
        } else{
            System.out.printf("Commercial at (%d,%d) levels up from %d to %d", this.toString(), this.getLevel(), prevLevel);
        }
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
        int m = getM(); // com.objectville.entity.cells.zones.Commercial requires Electricity, Water, and Internet

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
            else if (this.receivedPopulation > 0 && this.receivedGoods > 0) {
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
