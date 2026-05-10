import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UtilityDistributor{

    private ArrayList<Cell> cellArrayList = new ArrayList<>();

    public UtilityDistributor(ArrayList<Cell> cellArrayList) {
        this.cellArrayList = cellArrayList;

        for (Cell cell : cellArrayList) {
            grid.put(cell.getLocation(), cell);
        }
    }

    private ArrayList<Cell> visited = new ArrayList<>();
    private ArrayList<Cell> unvisited = new ArrayList<>();

    private Map<Point,Cell> grid = new HashMap<>();

    public ArrayList<Cell> getNeighbors(Cell cell) {
        ArrayList<Cell> neighbors = new ArrayList<>();

        Point p = cell.getLocation();
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

        if (north != null || north.isTransferable()) neighbors.add(north);
        if(northEast != null || northEast.isTransferable()) neighbors.add(northEast);
        if (east != null|| east.isTransferable()) neighbors.add(east);
        if(southEast != null || southEast.isTransferable()) neighbors.add(southEast);
        if (south != null || south.isTransferable()) neighbors.add(south);
        if(southWest != null|| southWest.isTransferable()) neighbors.add(southWest);
        if(west != null|| west.isTransferable()) neighbors.add(west);
        if(northWest != null|| northWest.isTransferable()) neighbors.add(northWest);

        return neighbors;
    }

    public void distribute(Cell startingCell){

    }
}