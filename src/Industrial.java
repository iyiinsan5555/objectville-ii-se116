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

    @Override
    public int calculateOutput() {
        return 0;
    }

    @Override
    public void updateLevel() {

    }

    @Override
    public String getOutputType() {
        return "";
    }
}
