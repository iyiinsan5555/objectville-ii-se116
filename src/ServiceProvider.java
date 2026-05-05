public abstract class ServiceProvider extends Cell {
    private int radius;

    //Constructor
    public ServiceProvider(int x, int y, int radius) {
        super(x,y);
        this.radius = radius;
    }

    //Getter
    public int getRadius() {
        return radius;
    }

    //Distribution logic will be implemented by using a separate class called "ServiceDistributor"
    abstract void distribute();
}
