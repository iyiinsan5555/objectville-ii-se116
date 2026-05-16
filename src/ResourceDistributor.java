import java.util.ArrayList;

public class ResourceDistributor {
    private int totalPopulation;
    private int totalGoods;
    private int totalLifestyle;
    private int totalHousing;
    private int totalCommercial;
    private int totalIndustrial;
    private ArrayList<Cell> cellsArrayList;
    private ArrayList<Zone> zones;

    /*
    Initializes the ResourceDistributor with the grid data.
    Sets all accumulation and count attributes to zero initially.
     */
    public ResourceDistributor(ArrayList<Cell> cellsArrayList, ArrayList<Zone> zones) {
        this.cellsArrayList = cellsArrayList;
        this.zones = zones;

        this.totalPopulation = 0;
        this.totalGoods = 0;
        this.totalLifestyle = 0;
        this.totalHousing = 0;
        this.totalCommercial = 0;
        this.totalIndustrial = 0;

        // Count the number of each zone type in the city
        countZones();
    }

    /*
    Counts the total number of each zone type present in the city pool.
    This is needed to divide resources equally among target zones.
     */
    private void countZones() {
        this.totalHousing = 0;
        this.totalCommercial = 0;
        this.totalIndustrial = 0;

        for (Zone zone : zones) {
            if (zone instanceof Housing) {
                this.totalHousing++;
            } else if (zone instanceof Commercial) {
                this.totalCommercial++;
            } else if (zone instanceof Industrial) {
                this.totalIndustrial++;
            }
        }
    }
}
