public class Housing extends Zone{
    private int receivedLifestyle;

    public Housing(int x, int y) {
        super(x, y);
        this.receivedLifestyle = 0;
    }

    @Override
    public void update() {

    }

    @Override
    public void resetData() {

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
        // Lifestyle points enhance the total population output.
        return (this.getLevel() + 1) * m + receivedLifestyle;
    }

    @Override
    public void updateLevel() {

    }
}
