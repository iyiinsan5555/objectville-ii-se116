package com.objectville.util;

import com.objectville.entity.cells.Cell;
import com.objectville.entity.cells.zones.*;
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
        if (populationTargets > 0 && totalPopulation > 0) {
            // The lost remainder value is accepted as the mathematical cost of distribution.
            int sharedPopulation = this.totalPopulation / populationTargets;

            // Deliver the calculated equal share to each eligible working zone
            for (Zone zone : zones) {
                if (zone instanceof Industrial) {
                    ((Industrial) zone).setReceivedPopulation(sharedPopulation);
                    System.out.println(zone.getZoneType() + " at " + zone.getLocation() + " received " + sharedPopulation + " population");
                } else if (zone instanceof Commercial) {
                    ((Commercial) zone).setReceivedPopulation(sharedPopulation);
                    System.out.println(zone.getZoneType() + " at " + zone.getLocation() + " received " + sharedPopulation + " population");
                }
            }
        }

        // Goods produced by Industrial zones are split equally only among Commercial zones.
        if (this.totalCommercial > 0 && totalGoods > 0) {
            int sharedGoods = this.totalGoods / this.totalCommercial;

            // Loop through all zones to update commercial instances with their share of goods
            for (Zone zone : zones) {
                if (zone instanceof Commercial) {
                    ((Commercial) zone).setReceivedGoods(sharedGoods);
                    System.out.println(zone.getZoneType() + " at " + zone.getLocation() + " received " + sharedGoods + " goods");
                }
            }
        }

        // Lifestyle items produced by Commercial zones are split equally only among Housing zones.
        if (this.totalHousing > 0 && totalLifestyle > 0) {
            int sharedLifestyle = this.totalLifestyle / this.totalHousing;

            // Loop through all zones to update housing instances with their share of lifestyle points.
            for (Zone zone : zones) {
                if (zone instanceof Housing) {
                    ((Housing) zone).setReceivedLifestyle(sharedLifestyle);
                    System.out.println(zone.getZoneType() + " at " + zone.getLocation() + " received " + sharedLifestyle + " lifestyles");
                }
            }
        }
    }

    /*
    Accumulate Production
    Resets the central global resource pools and aggregates the new outputs calculated by each zone type.
     */
    public void accumulate() {
        // Clear all previous central resource pools to prepare for fresh data collection
        this.totalPopulation = 0;
        this.totalGoods = 0;
        this.totalLifestyle = 0;

        // Re-run zone counter in case zones were added, modified, or demolished mid-tick
        countZones();

        // Accumulate production outputs strictly matching the specific resource each zone creates
        for (Zone zone : zones) {
            if (zone instanceof Housing) {
                // Housing generates Population; accumulate output to totalPopulation pool
                this.totalPopulation += zone.getOutput();
            } else if (zone instanceof Industrial) {
                // Industrial generates Goods; accumulate output to totalGoods pool
                this.totalGoods += zone.getOutput();
            } else if (zone instanceof Commercial) {
                // Commercial generates Lifestyle; accumulate output to totalLifestyle pool
                this.totalLifestyle += zone.getOutput();
            }
        }
    }

    // Getters & Setters
    public int getTotalPopulation() {
        return totalPopulation;
    }
    public void setTotalPopulation(int totalPopulation) {
        this.totalPopulation = totalPopulation;
    }
    public int getTotalGoods() {
        return totalGoods;
    }
    public void setTotalGoods(int totalGoods) {
        this.totalGoods = totalGoods;
    }
    public int getTotalLifestyle() {
        return totalLifestyle;
    }
    public void setTotalLifestyle(int totalLifestyle) {
        this.totalLifestyle = totalLifestyle;
    }
    public int getTotalHousing() {
        return totalHousing;
    }
    public void setTotalHousing(int totalHousing) {
        this.totalHousing = totalHousing;
    }
    public int getTotalCommercial() {
        return totalCommercial;
    }
    public void setTotalCommercial(int totalCommercial) {
        this.totalCommercial = totalCommercial;
    }
    public int getTotalIndustrial() {
        return totalIndustrial;
    }
    public void setTotalIndustrial(int totalIndustrial) {
        this.totalIndustrial = totalIndustrial;
    }
    public ArrayList<Cell> getCellsArrayList() {
        return cellsArrayList;
    }
    public void setCellsArrayList(ArrayList<Cell> cellsArrayList) {
        this.cellsArrayList = cellsArrayList;
    }
    public ArrayList<Zone> getZones() {
        return zones;
    }
    public void setZones(ArrayList<Zone> zones) {
        this.zones = zones;
    }
}
