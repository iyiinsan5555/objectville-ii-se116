package com.objectville.entity.cells.zones;

public class Housing extends Zone{
    private int receivedLifestyle;
    private int oldLevel;

    public Housing(int x, int y) {
        super(x, y);
        this.receivedLifestyle = 0;
        this.oldLevel = 0;
    }

    /*
    Runs the per-tick simulation logic for the housing zone.
    It updates output, demand, and level status in order.
     */
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

    @Override
    public String getZoneType() {
        return "House";
    }

    /*
    Calculates population output based on level, basic utilities, and lifestyle.
    Production is limited by the minimum utility value (m).
     */
    @Override
    public int calculateOutput() {
        int m = getM();

        switch (this.getLevel()) {
            case 1:
                return m;
            case 2:
                return 2 * m;
            case 3:
                return (2 * m) + this.receivedLifestyle;
            default:
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

        if (currentLevel == 0) {
            // Level 1 only requires basic utilities (m > 0)
            this.setLevel(1);
        } else if (currentLevel == 1) {
            // Check if it qualifies to upgrade to Level 2
            if (this.isHasSecurity() && this.isHasHealth() && this.isHasEducation()) {
                this.setLevel(2);
            }
        } else if (currentLevel == 2) {
            // Gradual fall: if it loses any required service, it drops back to Level 1
            if (!this.isHasSecurity() || !this.isHasHealth() || !this.isHasEducation()) {
                this.setLevel(1);
            } else if (this.receivedLifestyle > 0) {
                this.setLevel(3);
            }
        } else if (currentLevel == 3) {
            // Gradual fall: if it loses lifestyle or any service, it drops back to Level 2
            if (this.receivedLifestyle == 0 || !this.isHasSecurity() || !this.isHasHealth() || !this.isHasEducation()) {
                this.setLevel(2);
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
