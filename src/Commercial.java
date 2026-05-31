public class Commercial extends Zone{
    private int receivedPopulation;
    private int receivedGoods;

    public Commercial(int x, int y) {
        super(x, y);
        this.receivedPopulation = 0;
        this.receivedGoods = 0;
    }

    //Updates output, sets minimum utility demand, changes level, and cleans data.
    @Override
    public void update() {
        this.setOutput(calculateOutput());
        this.setUtilityDemand(Math.max(1, this.getOutput()));
        updateLevel();

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
        Point location = this.getLocation();
        int X =  location.getX();
        int Y = location.getY();

        switch (this.getLevel()) {
            case 1:
                System.out.printf("Commercial at (%d,%d) generated %d lifestyle", X, Y, m);
                return m;
            case 2:
                System.out.printf("Commercial at (%d,%d) generated %d lifestyle", X, Y, 2 * m);
                return 2 * m;
            case 3: //Level 3 output depends on the minimum value between received population and goods.
                System.out.printf("Commercial at (%d,%d) generated %d lifestyle", X, Y, (2 * m) + Math.min(this.receivedPopulation, this.receivedGoods));
                return (2 * m) + Math.min(this.receivedPopulation, this.receivedGoods);
            default:
                System.out.printf("Commercial at (%d,%d) generated %d lifestyle", X, Y, 0);
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

        Point location = this.getLocation();
        int X =  location.getX();
        int Y = location.getY();

        if (currentLevel == 0) {
            // Level 1 requires basic utilities (m > 0), population, and goods
            if (this.receivedPopulation > 0 && this.receivedGoods > 0) {
                this.setLevel(1);
                System.out.printf("Commercial at (%d,%d) levels up from 0 to 1", X, Y);
            }
        }
        else if (currentLevel == 1) {
            // Upgrade to Level 2 requires maintaining Level 1 conditions + Security service
            if (this.receivedPopulation > 0 && this.receivedGoods > 0 && this.isHasSecurity()) {
                this.setLevel(2);
                System.out.printf("Commercial at (%d,%d) levels up from 1 to 2", X, Y);
            }
            // Gradual fall to Level 0 if it loses required resources
            else if (this.receivedPopulation == 0 || this.receivedGoods == 0) {
                this.setLevel(0);
                System.out.printf("Commercial at (%d,%d) levels down from 1 to 0", X, Y);
            }
        }
        else if (currentLevel == 2) {
            // If it loses security or required inputs, it drops back to Level 1
            if (this.receivedPopulation == 0 || this.receivedGoods == 0 || !this.isHasSecurity()) {
                this.setLevel(1);
                System.out.printf("Commercial at (%d,%d) levels down from 2 to 1", X, Y);
            }
            // Upgrade to Level 3 requires excess population and goods
            else if (this.receivedPopulation > 0 && this.receivedGoods > 0) {
                this.setLevel(3);
                System.out.printf("Commercial at (%d,%d) levels up from 2 to 3", X, Y);
            }
        }
        else if (currentLevel == 3) {
            // If conditions fail, it drops back to Level 2
            if (this.receivedPopulation == 0 || this.receivedGoods == 0 || !this.isHasSecurity()) {
                this.setLevel(2);
                System.out.printf("Commercial at (%d,%d) levels down from 3 to 2", X, Y);
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
