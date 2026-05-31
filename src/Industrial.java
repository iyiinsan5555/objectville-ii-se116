public class Industrial extends Zone{
    private int receivedPopulation;

    //Initializes the Industrial zone with coordinates and sets received population to 0.
    public Industrial(int x, int y) {
        super(x, y);
        this.receivedPopulation = 0;
    }

    /*
    Runs the per-tick simulation logic for the industrial zone.
    Updates output, sets minimum utility demand, changes level, and cleans data.
     */
    @Override
    public void update() {
        this.setOutput(calculateOutput());
        this.setUtilityDemand(Math.max(1, this.getOutput()));
        updateLevel();

        // Clean up temporary data.
        this.resetData();
    }

    //Resets temporary resources, services, and the unique population field.
    @Override
    public void resetData() {
        this.setReceivedElectricity(0);
        this.setReceivedWater(0);
        this.setReceivedInternet(0);
        this.receivedPopulation = 0;

        this.setHasSecurity(false);
        this.setHasEducation(false);
        this.setHasHealth(false);
    }

    /*
    Overriding getM because Industrial ONLY requires Electricity and Water.
    Internet is not a constraint for Industrial production.
     */
    @Override
    public int getM() {
        return Math.min(this.getReceivedElectricity(), this.getReceivedWater());
    }

    /*
    Calculates goods output based on level and required utilities (m).
    Level 3 includes the total population received from the city-wide pool.
     */
    @Override
    public int calculateOutput() {
        int m = getM();
        Point location = this.getLocation();
        int X =  location.getX();
        int Y = location.getY();

        switch (this.getLevel()) {
            case 1:
                System.out.printf("Industrial at (%d,%d) generated %d goods", X, Y, m);
                return m;
            case 2:
                System.out.printf("Industrial at (%d,%d) generated %d goods", X, Y, 2 * m);
                return 2 * m;
            case 3:
                System.out.printf("Industrial at (%d,%d) generated %d goods", X, Y, (2 * m) + this.receivedPopulation);
                return (2 * m) + this.receivedPopulation;
            default:
                System.out.printf("Industrial at (%d,%d) generated %d goods", X, Y, 0);
                return 0;
        }
    }

    /*
    Updates the level of the industrial zone based on rules and services.
     */
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
            // Level 1 requires basic utilities (m > 0) AND population workers
            if (this.receivedPopulation > 0) {
                this.setLevel(1);
                System.out.printf("Industrial at (%d,%d) levels up from 0 to 1", X, Y);
            }
        }
        else if (currentLevel == 1) {
            // Upgrade to Level 2 requires maintaining Level 1 conditions + Security service
            if (this.receivedPopulation > 0 && this.isHasSecurity()) {
                this.setLevel(2);
                System.out.printf("Industrial at (%d,%d) levels up from 1 to 2", X, Y);
            }
            //Gradual fall to Level 0 if it loses workers
            else if (this.receivedPopulation == 0) {
                this.setLevel(0);
                System.out.printf("Industrial at (%d,%d) levels down from 1 to 0", X, Y);
            }
        }
        else if (currentLevel == 2) {
            // If it loses security or workers, it drops back to Level 1
            if (this.receivedPopulation == 0 || !this.isHasSecurity()) {
                this.setLevel(1);
                System.out.printf("Industrial at (%d,%d) levels down from 2 to 1", X, Y);
            }
            // Upgrade to Level 3 requires excess population presence
            else if (this.receivedPopulation > 0) {
                this.setLevel(3);
                System.out.printf("Industrial at (%d,%d) levels up from 2 to 3", X, Y);
            }
        }
        else if (currentLevel == 3) {
            // If conditions fail, it drops back to Level 2
            if (this.receivedPopulation == 0 || !this.isHasSecurity()) {
                this.setLevel(2);
                System.out.printf("Industrial at (%d,%d) levels down from 3 to 2", X, Y);
            }
        }

    }

    @Override
    public String getOutputType() {
        return "Goods";
    }

    public int getReceivedPopulation() {
        return receivedPopulation;
    }
    public void setReceivedPopulation(int receivedPopulation) {
        this.receivedPopulation = receivedPopulation;
    }
}
