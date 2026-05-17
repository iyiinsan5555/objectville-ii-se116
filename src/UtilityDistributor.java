import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.HashSet;

public class UtilityDistributor {
    private ArrayList<Cell> cellArrayList;
    private HashMap<Point, Cell> cellHashMap;
    private ArrayList<UtilityProvider> utilityProviders;

    public UtilityDistributor(ArrayList<Cell> cellArrayList, HashMap<Point, Cell> cellHashMap, ArrayList<UtilityProvider> utilityProviders) {
        this.cellArrayList = cellArrayList;
        this.cellHashMap = cellHashMap;
        this.utilityProviders = utilityProviders;
    }

    public void distribute() {
        for (UtilityProvider provider : utilityProviders) {
            if (provider instanceof PowerPlant) {
                distributeBFS((PowerPlant) provider);
            } else if (provider instanceof WaterPumpingStation) {
                distributeBFS((WaterPumpingStation) provider);
            } else if (provider instanceof InternetHub) {
                distributeBFS((InternetHub) provider);
            }
        }
    }

    /*

    BFS logic is done below. What it does?
    I overloaded for each UtilityProvider type for our ease. (Type casting was horrible looking)

    Used Queue data type because --> FIFO (First In First Out)
    Used HashSet data type because --> it runs in constant time
    We have overwritten equals() & hashCode in Point class so it is okay to use HashSet.
    Check: https://docs.oracle.com/javase/8/docs/api/java/util/HashSet.html#contains-java.lang.Object-

    The logic:
    We first look at to the first element in the queue.
    If it is a zone --> consumes
    Then until the total utility is consumed or all possible paths are checked:
        --> get neighbors, if not visited add to queue and hashSet
        --> the zones in the queue consumes
        --> repeat

     */

    private void distributeBFS(PowerPlant powerPlant) {

        Queue<Cell> queue = new LinkedList<>();
        HashSet<Cell> visited = new HashSet<>();

        queue.add(powerPlant);
        visited.add(powerPlant);

        while (!queue.isEmpty() && powerPlant.getTotalElectric() > 0) {
            Cell current = queue.poll();

            if (current instanceof Zone) {
                Zone zone = (Zone) current;
                int consumed = Math.min(zone.getUtilityDemand(), powerPlant.getTotalElectric());

                zone.setReceivedElectricity(zone.getReceivedElectricity() + consumed);
                powerPlant.decTotalElectric(consumed);
            }

            for (Cell neighbor : GridManager.getNeighbors(current, cellHashMap)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    private void distributeBFS(WaterPumpingStation waterPumpingStation) {

        Queue<Cell> queue = new LinkedList<>();
        HashSet<Cell> visited = new HashSet<>();

        queue.add(waterPumpingStation);
        visited.add(waterPumpingStation);

        while (!queue.isEmpty() && waterPumpingStation.getTotalWater() > 0) {
            Cell current = queue.poll();

            if (current instanceof Zone) {
                Zone zone = (Zone) current;
                int consumed = Math.min(zone.getUtilityDemand(), waterPumpingStation.getTotalWater());

                zone.setReceivedWater(zone.getReceivedWater() + consumed);
                waterPumpingStation.decTotalWater(consumed);
            }

            for (Cell neighbor : GridManager.getNeighbors(current, cellHashMap)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    private void distributeBFS(InternetHub internetHub) {

        Queue<Cell> queue = new LinkedList<>();
        HashSet<Cell> visited = new HashSet<>();

        queue.add(internetHub);
        visited.add(internetHub);

        while (!queue.isEmpty() && internetHub.getTotalInternet() > 0) {
            Cell current = queue.poll();

            if (current instanceof Zone) {
                Zone zone = (Zone) current;
                int consumed = Math.min(zone.getUtilityDemand(), internetHub.getTotalInternet());

                zone.setReceivedInternet(zone.getReceivedInternet() + consumed);
                internetHub.decTotalInternet(consumed);
            }

            for (Cell neighbor : GridManager.getNeighbors(current, cellHashMap)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }
}