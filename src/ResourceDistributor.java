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

    /*
    Takes accumulated resources from the city-wide global pools and divides them
    equally among all matching target zones using integer division.
      */
    public void distribute() {
        // 1. Distribute Population to Industrial and Commercial zones
        int populationTargets = this.totalIndustrial + this.totalCommercial;
        if (populationTargets > 0 && this.totalPopulation > 0) {
            // The lost remainder value is accepted as the mathematical cost of distribution.
            int sharedPopulation = this.totalPopulation / populationTargets;

            // Deliver the calculated equal share to each eligible working zone
            for (Zone zone : zones) {
                if (zone instanceof Industrial) {
                    ((Industrial) zone).setReceivedPopulation(sharedPopulation);
                } else if (zone instanceof Commercial) {
                    ((Commercial) zone).setReceivedPopulation(sharedPopulation);
                }
            }
        }

        // Goods produced by Industrial zones are split equally only among Commercial zones.
        if (this.totalCommercial > 0 && this.totalGoods > 0) {
            int sharedGoods = this.totalGoods / this.totalCommercial;

            // Loop through all zones to update commercial instances with their share of goods
            for (Zone zone : zones) {
                if (zone instanceof Commercial) {
                    ((Commercial) zone).setReceivedGoods(sharedGoods);
                }
            }
        }

        // Lifestyle items produced by Commercial zones are split equally only among Housing zones.
        if (this.totalHousing > 0 && this.totalLifestyle > 0) {
            int sharedLifestyle = this.totalLifestyle / this.totalHousing;

            // Loop through all zones to update housing instances with their share of lifestyle points.
            for (Zone zone : zones) {
                if (zone instanceof Housing) {
                    ((Housing) zone).setReceivedLifestyle(sharedLifestyle);
                }
            }
        }
    }
}
