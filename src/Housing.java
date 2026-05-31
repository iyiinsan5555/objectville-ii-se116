public class Housing extends Zone{
    private int receivedLifestyle;

    public Housing(int x, int y) {
        super(x, y);
        this.receivedLifestyle = 0;
    }

    /*
    Runs the per-tick simulation logic for the housing zone.
    It updates output, demand, and level status in order.
     */
    @Override
    public void update() {
        updateLevel();
        this.setOutput(calculateOutput());
        this.setUtilityDemand(Math.max(1, this.getOutput()));

        this.resetData();
    }

    /*
     Resets the temporary resources and services.
     This prepares the zone for the next simulation tick.
     */
    @Override
    public void resetData() {
        this.setReceivedElectricity(0);
        this.setReceivedWater(0);
        this.setReceivedInternet(0);
        this.receivedLifestyle = 0;

        this.setHasSecurity(false);
        this.setHasEducation(false);
        this.setHasHealth(false);

    }

    /*
    Returns the name of the resource produced by this zone.
    Housing zones are responsible for generating "Population".
     */
    @Override
    public String getOutputType() {
        return "Population";
    }

    /*
    Calculates population output based on level, basic utilities, and lifestyle.
    Production is limited by the minimum utility value (m).
     */
    @Override
    public int calculateOutput() {
        int m = getM();
        Point location = this.getLocation();
        int X =  location.getX();
        int Y = location.getY();

        switch (this.getLevel()) {
            case 1:
                System.out.printf("House at (%d,%d) generated %d population", X, Y, m);
                return m;
            case 2:
                System.out.printf("House at (%d,%d) generated %d population", X, Y, 2 * m);
                return 2 * m;
            case 3:
                System.out.printf("House at (%d,%d) generated %d population", X, Y, (2 * m) + this.receivedLifestyle);
                return (2 * m) + this.receivedLifestyle;
            default:
                System.out.printf("House at (%d,%d) generated %d population", X, Y, 0);
                return 0; // Level 0 produces 0 population
        }
    }

    //Updates the level of the housing zone based on received services and utilities.
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
            // Level 1 only requires basic utilities (m > 0)
            this.setLevel(1);
            System.out.printf("Housing at (%d,%d) levels up from 0 to 1", X, Y);
        }
        else if (currentLevel == 1) {
            // Check if it qualifies to upgrade to Level 2
            if (this.isHasSecurity() && this.isHasHealth() && this.isHasEducation()) {
                this.setLevel(2);
                System.out.printf("Housing at (%d,%d) levels up from 1 to 2", X, Y);
            }
        }
        else if (currentLevel == 2) {
            // Gradual fall: if it loses any required service, it drops back to Level 1
            if (!this.isHasSecurity() || !this.isHasHealth() || !this.isHasEducation()) {
                this.setLevel(1);
                System.out.printf("Housing at (%d,%d) levels down from 2 to 1", X, Y);
            }
            else if (this.receivedLifestyle > 0) {
                this.setLevel(3);
                System.out.printf("Housing at (%d,%d) levels up from 2 to 3", X, Y);
            }
        }
        else if (currentLevel == 3) {
            // Gradual fall: if it loses lifestyle or any service, it drops back to Level 2
            if (this.receivedLifestyle == 0 || !this.isHasSecurity() || !this.isHasHealth() || !this.isHasEducation()) {
                this.setLevel(2);
                System.out.printf("Housing at (%d,%d) levels down from 3 to 2", X, Y);
            }
        }
    }

    // Getter & Setter
    public int getReceivedLifestyle() {
        return receivedLifestyle;
    }
    public void setReceivedLifestyle(int receivedLifestyle) {
        this.receivedLifestyle = receivedLifestyle;
    }
}
