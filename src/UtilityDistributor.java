import java.util.*;

public class UtilityDistributor{

    private ArrayList<Cell> cellArrayList = new ArrayList<>();

    public UtilityDistributor(ArrayList<Cell> cellArrayList) {
        this.cellArrayList = cellArrayList;

        for (Cell cell : cellArrayList) {
            grid.put(cell.getLocation(), cell);
        }
    }

    private Map<Point,Cell> grid = new HashMap<>();


    //lists for each provider type. ı will then put a seperate individual distributor method. and call those in the main distributor.
    ArrayList<WaterPumpingStation> waterProvList = new ArrayList<>();
    ArrayList<InternetHub> intProvList = new ArrayList<>();
    ArrayList<PowerPlant> elecProvList = new ArrayList<>();

    public void findProviders(){
        waterProvList.clear();
        intProvList.clear();
        elecProvList.clear();

        for (Cell cell : grid.values()){
            if(cell instanceof WaterPumpingStation){
                waterProvList.add((WaterPumpingStation) cell);
            } else if (cell instanceof InternetHub) {
                intProvList.add((InternetHub) cell);
            } else if (cell instanceof PowerPlant) {
                elecProvList.add((PowerPlant) cell);
            }
        }
    }

    public ArrayList<Cell> getNeighbors(Cell start) {
        ArrayList<Cell> neighbors = new ArrayList<>();

        Point p = start.getLocation();
        int x = p.getX();
        int y = p.getY();

        Cell north    = grid.get(new Point(x, y + 1));
        Cell northEast = grid.get(new Point(x + 1,y + 1));
        Cell east = grid.get(new Point(x + 1, y));
        Cell southEast = grid.get(new Point(x + 1, y - 1));
        Cell south = grid.get(new Point( x,y - 1));
        Cell southWest = grid.get(new Point(x - 1,y - 1));
        Cell west = grid.get(new Point(x - 1,y));
        Cell northWest = grid.get(new Point( x - 1, y + 1));

        if (north != null || north instanceof Transferable) neighbors.add(north);
        if(northEast != null || northEast instanceof Transferable) neighbors.add(northEast);
        if (east != null|| east instanceof Transferable) neighbors.add(east);
        if(southEast != null || southEast instanceof Transferable) neighbors.add(southEast);
        if (south != null || south instanceof Transferable) neighbors.add(south);
        if(southWest != null|| southWest instanceof Transferable) neighbors.add(southWest);
        if(west != null|| west instanceof Transferable) neighbors.add(west);
        if(northWest != null|| northWest instanceof Transferable) neighbors.add(northWest);

        return neighbors;
    }

    public void distribute() {
        findProviders();
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

            int totWater = startingCell.getTotalWater();

            while (!unvisitedQ.isEmpty() && totWater>0) {
                Cell current = unvisitedQ.remove(); //take the first element and stores in current
                //this is where the unique distribution will happen
                if(current instanceof Housing &&((Housing) current).getReceivedWater()<1){
                    totWater--;
                    ((Housing) current).setReceivedWater(1);
                } else if (current instanceof Commercial && ((Commercial) current).getReceivedWater()<1) {
                    totWater--;
                    ((Commercial) current).setReceivedWater(1);
                } else if (current instanceof Industrial &&((Industrial) current).getReceivedWater()<1) {
                    totWater--;
                    ((Industrial) current).setReceivedWater(1);
                }

                ArrayList<Cell> neighbors = getNeighbors(current);
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

            int totInt = startingCell.getTotalInternet();

            while (!unvisitedQ.isEmpty() && totInt>0) {
                Cell current = unvisitedQ.remove(); //take the first element and stores in current
                //this is where the unique distribution will happen
                if(current instanceof Housing && ((Housing) current).getReceivedInternet()<1){
                    totInt--;
                    ((Housing) current).setReceivedInternet(1);
                } else if(current instanceof Commercial && ((Commercial) current).getReceivedInternet()<1){
                    totInt--;
                    ((Commercial) current).setReceivedInternet(1);
                }



                ArrayList<Cell> neighbors = getNeighbors(current);
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
                if(current instanceof Housing && ((Housing) current).getReceivedElectricity()<1){
                    totElec--;
                    ((Housing) current).setReceivedElectricity(1);
                } else if (current instanceof Industrial && ((Industrial) current).getReceivedElectricity()<1) {
                    totElec--;
                    ((Industrial) current).setReceivedElectricity(1);
                } else if (current instanceof Commercial && ((Commercial) current).getReceivedElectricity()<1) {
                    totElec--;
                    ((Commercial) current).setReceivedElectricity(1);
                }

                ArrayList<Cell> neighbors = getNeighbors(current);
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