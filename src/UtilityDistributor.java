import java.util.*;

public class UtilityDistributor{

    private HashMap<Point, Cell> cellHashMap;

    private ArrayList<WaterPumpingStation> waterProvList = new ArrayList<>();
    private ArrayList<InternetHub> intProvList = new ArrayList<>();
    private ArrayList<PowerPlant> elecProvList = new ArrayList<>();


    public UtilityDistributor( HashMap<Point, Cell> cellHashMap, ArrayList<UtilityProvider> utilityProviders) {

        this.cellHashMap = cellHashMap;

        for (UtilityProvider provider : utilityProviders){
            if(provider instanceof WaterPumpingStation){
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

    public void distributeWater(){


        Queue<Cell> unvisitedQ = new ArrayDeque<>();
        ArrayList<Cell> visited = new ArrayList<>();
        for(WaterPumpingStation startingCell: waterProvList){
            visited.add(startingCell);
            unvisitedQ.add(startingCell);

            while (!unvisitedQ.isEmpty() && startingCell.getTotalWater()>0) {
                Cell current = unvisitedQ.remove(); //take the first element and stores in current
                //this is where the unique distribution will happen
                if (current instanceof Zone zone) {
                    int consumed = Math.min(zone.getUtilityDemand(), startingCell.getTotalWater());

                    zone.setReceivedWater(zone.getReceivedWater() + consumed);
                    startingCell.decTotalWater(consumed);
                }

                ArrayList<Cell> neighbors = GridManager.getNeighbors(current,cellHashMap);
                //storing adjacent cells to the queue
                for (Cell neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        unvisitedQ.add(neighbor);

                    }
                }
            }
        }



    }

    public void distributeInternet(){

        Queue<Cell> unvisitedQ = new ArrayDeque<>();
        ArrayList<Cell> visited = new ArrayList<>();
        for(InternetHub startingCell: intProvList){
            visited.add(startingCell);
            unvisitedQ.add(startingCell);


            while (!unvisitedQ.isEmpty() && startingCell.getTotalInternet()>0) {
                Cell current = unvisitedQ.remove(); //take the first element and stores in current
                //this is where the unique distribution will happen
                if(current instanceof Housing housing){
                    int consumed = Math.min((housing).getUtilityDemand(),startingCell.getTotalInternet());

                    startingCell.decTotalInternet(consumed);
                    (housing).setReceivedInternet((housing).getReceivedInternet() + consumed);
                } else if(current instanceof Commercial commercial){
                    int consumed = Math.min((commercial).getUtilityDemand(),startingCell.getTotalInternet());

                    startingCell.decTotalInternet(consumed);
                    (commercial).setReceivedInternet((commercial).getReceivedInternet() + consumed);
                }



                ArrayList<Cell> neighbors = GridManager.getNeighbors(current,cellHashMap);
                //storing adjacent cells to the queue
                for (Cell neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        unvisitedQ.add(neighbor);

                    }
                }
            }
        }
    }

    public void distributeElec(){

        Queue<Cell> unvisitedQ = new ArrayDeque<>();
        ArrayList<Cell> visited = new ArrayList<>();
        for(PowerPlant startingCell: elecProvList){
            visited.add(startingCell);
            unvisitedQ.add(startingCell);

            int totElec = startingCell.getTotalElectric();

            while (!unvisitedQ.isEmpty() && totElec>0) {
                Cell current = unvisitedQ.remove(); //take the first element and stores in current
                //this is where the unique distribution will happen
                if (current instanceof Zone zone) {
                    int consumed = Math.min(zone.getUtilityDemand(), startingCell.getTotalElectric());

                    zone.setReceivedElectricity(zone.getReceivedElectricity() + consumed);
                    startingCell.decTotalElectric(consumed);
                }

                ArrayList<Cell> neighbors = GridManager.getNeighbors(current,cellHashMap);
                //storing adjacent cells to the queue
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
