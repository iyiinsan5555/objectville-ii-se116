public class InternetHub extends UtilityProvider{
    private int totalInternet;

    public InternetHub(int row, int column){
        super(row, column);
        this.totalInternet = super.getCapacity();
    }

    public int getTotalInternet() {
        return totalInternet;
    }

    // Resets Totol Amount After Every Tick
    public void setTotalInternet(int totalInternet) {
        this.totalInternet = super.getCapacity();
    }

    public void decTotalInternet(int amount) {
        totalInternet -= amount;
    }

}
