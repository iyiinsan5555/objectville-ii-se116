public class InternetHub extends UtilityProvider{
    private int totalInternet;

    public InternetHub(int row, int column){
        super(row, column);
        this.totalInternet = super.getCapacity();
    }

    public int getTotalInternet() {
        return totalInternet;
    }
    
    public void setTotalInternet(int totalInternet) {
        this.totalInternet = totalInternet;
    }

    // When the resource is Consumed Simulation Runner can call this
    public void decTotalInternet(int amount) {
        totalInternet -= amount;
    }

    @Override
    public void resetUtility() {
        this.totalInternet = super.getCapacity();
    }
}
