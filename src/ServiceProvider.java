public abstract class ServiceProvider extends Cell {
    private int radius;

    //Constructor
    public ServiceProvider(int x, int y, int radius) {
        super(x,y);
        this.radius = radius;
    }
    
    //Getters
    public int getRadius() {
        return radius;
    }

    public abstract String getServiceType();
}
