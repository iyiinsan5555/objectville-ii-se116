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

    @Override
    public int calculateOutput() {
        return 0;
    }

    @Override
    public void updateLevel() {

    }

    @Override
    public String getOutputType() {
        return "Lifestyle";
    }
}
