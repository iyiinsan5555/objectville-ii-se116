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
    ArrayList<Cell> waterProvList = new ArrayList<>();
    ArrayList<Cell> intProvList = new ArrayList<>();
    ArrayList<Cell> elecProvList = new ArrayList<>();

    public void findProviders(){

        for (Cell cell : grid.values()){
            if(cell instanceof WaterPumpingStation){
                waterProvList.add(cell);
            } else if (cell instanceof InternetHub) {
                intProvList.add(cell);
            } else if (cell instanceof PowerPlant) {
                elecProvList.add(cell);
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
        //note to self. add finder here and act accordingly as to not leave anything out
        //there will be method callers to individual distributors.

    }

    public void distributeWater(ArrayList<Cell> waterList){
        waterList = waterProvList;

        Queue<Cell> unvisitedQ = new ArrayDeque<>();
        ArrayList<Cell> visited = new ArrayList<>();
        for(Cell startingCell: waterList){
            visited.add(startingCell);
            unvisitedQ.add(startingCell);
        }


        while (!unvisitedQ.isEmpty()) {
            Cell current = unvisitedQ.remove(); //take the first element and stores in current
            //this is where the unique distribution will happen


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

    public void distributeInternet(ArrayList<Cell> intList){
        intList = intProvList;

        Queue<Cell> unvisitedQ = new ArrayDeque<>();
        ArrayList<Cell> visited = new ArrayList<>();
        for(Cell startingCell: intList){
            visited.add(startingCell);
            unvisitedQ.add(startingCell);
        }


        while (!unvisitedQ.isEmpty()) {
            Cell current = unvisitedQ.remove(); //take the first element and stores in current
            //this is where the unique distribution will happen


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

    public void distributeElec(ArrayList<Cell> elecList){
        elecList = elecProvList;

        Queue<Cell> unvisitedQ = new ArrayDeque<>();
        ArrayList<Cell> visited = new ArrayList<>();
        for(Cell startingCell: elecList){
            visited.add(startingCell);
            unvisitedQ.add(startingCell);
        }


        while (!unvisitedQ.isEmpty()) {
            Cell current = unvisitedQ.remove(); //take the first element and stores in current
            //this is where the unique distribution will happen


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