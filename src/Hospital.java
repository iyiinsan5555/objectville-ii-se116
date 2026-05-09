public class Hospital extends ServiceProvider {
    private static final int radius = 3;

    public Hospital(int x, int y) {
        super(x, y, radius);
    }

    @Override
    public String getServiceType() {
        return "Hospital";
    }
}