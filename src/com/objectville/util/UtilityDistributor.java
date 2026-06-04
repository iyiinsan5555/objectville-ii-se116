package com.objectville.util;

import com.objectville.entity.cells.Cell;
import com.objectville.entity.cells.zones.*;
import com.objectville.core.GridManager;
import com.objectville.entity.cells.utilityProviders.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.HashSet;
import java.util.ArrayDeque;

public class UtilityDistributor {
    private HashMap<Point, Cell> cellHashMap;
    private ArrayList<WaterPumpingStation> waterProvList = new ArrayList<>();
    private ArrayList<InternetHub> intProvList = new ArrayList<>();
    private ArrayList<PowerPlant> elecProvList = new ArrayList<>();

    public UtilityDistributor(HashMap<Point, Cell> cellHashMap, ArrayList<UtilityProvider> utilityProviders) {
        this.cellHashMap = cellHashMap;

        for (UtilityProvider provider : utilityProviders) {
            if (provider instanceof WaterPumpingStation) {
                waterProvList.add((WaterPumpingStation) provider);
            } else if (provider instanceof InternetHub) {
                intProvList.add((InternetHub) provider);
            } else if (provider instanceof PowerPlant) {
                elecProvList.add((PowerPlant) provider);
            }
        }
    }

    public void distribute() {
        distributeInternet();
        distributeWater();
        distributeElec();
    }

    public void distributeWater() {
        for (WaterPumpingStation startingCell : waterProvList) {
            Queue<Cell> unvisitedQ = new ArrayDeque<>();
            HashSet<Cell> visited = new HashSet<>();

            visited.add(startingCell);
            unvisitedQ.add(startingCell);

            while (!unvisitedQ.isEmpty() && startingCell.getTotalWater() > 0) {
                Cell current = unvisitedQ.remove();

                if (current instanceof Zone zone) {
                    int consumed = Math.min(zone.getUtilityDemand(), startingCell.getTotalWater());

                    zone.setReceivedWater(zone.getReceivedWater() + consumed);
                    startingCell.decTotalWater(consumed);

                    System.out.println(zone.getZoneType() + " at " + zone.getLocation() + " received " + consumed + " water");
                }

                ArrayList<Cell> neighbors = GridManager.getNeighbors(current, cellHashMap);
                for (Cell neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        unvisitedQ.add(neighbor);
                    }
                }
            }
        }
    }

    public void distributeInternet() {
        for (InternetHub startingCell : intProvList) {
            Queue<Cell> unvisitedQ = new ArrayDeque<>();
            HashSet<Cell> visited = new HashSet<>();

            visited.add(startingCell);
            unvisitedQ.add(startingCell);

            while (!unvisitedQ.isEmpty() && startingCell.getTotalInternet() > 0) {
                Cell current = unvisitedQ.remove();

                if (current instanceof Housing housing) {
                    int consumed = Math.min(housing.getUtilityDemand(), startingCell.getTotalInternet());

                    startingCell.decTotalInternet(consumed);
                    housing.setReceivedInternet(housing.getReceivedInternet() + consumed);

                    System.out.println(housing.getZoneType() + " at " + housing.getLocation() + " received " + consumed + " internet");
                } else if (current instanceof Commercial commercial) {
                    int consumed = Math.min(commercial.getUtilityDemand(), startingCell.getTotalInternet());

                    startingCell.decTotalInternet(consumed);
                    commercial.setReceivedInternet(commercial.getReceivedInternet() + consumed);

                    System.out.println(commercial.getZoneType() + " at " + commercial.getLocation() + " received " + consumed + " internet");
                }

                ArrayList<Cell> neighbors = GridManager.getNeighbors(current, cellHashMap);
                for (Cell neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        unvisitedQ.add(neighbor);
                    }
                }
            }
        }
    }

    public void distributeElec() {
        for (PowerPlant startingCell : elecProvList) {
            Queue<Cell> unvisitedQ = new ArrayDeque<>();
            HashSet<Cell> visited = new HashSet<>();

            visited.add(startingCell);
            unvisitedQ.add(startingCell);


            while (!unvisitedQ.isEmpty() && startingCell.getTotalElectric() > 0) {
                Cell current = unvisitedQ.remove();

                if (current instanceof Zone zone) {
                    int consumed = Math.min(zone.getUtilityDemand(), startingCell.getTotalElectric());

                    zone.setReceivedElectricity(zone.getReceivedElectricity() + consumed);
                    startingCell.decTotalElectric(consumed);

                    System.out.println(zone.getZoneType() + " at " + zone.getLocation() + " received " + consumed + " electricity");
                }

                ArrayList<Cell> neighbors = GridManager.getNeighbors(current, cellHashMap);
                for (Cell neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        unvisitedQ.add(neighbor);
                    }
                }
            }
        }
    }
}