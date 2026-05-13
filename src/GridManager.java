import java.util.ArrayList;
import java.util.HashMap;

public class GridManager {
    ArrayList<Cell> cellArrayList;
    HashMap<Point, Cell> cellHashMap;
    ArrayList<Cell> zones;
    ArrayList<UtilityProvider> utilityProviders;
    ArrayList<ServiceProvider> serviceProviders;

    public GridManager(ArrayList<Cell> cellArrayList) {
        this.cellArrayList = cellArrayList;

        //Implementing other fields
        cellHashMap = new HashMap<>();
        zones = new ArrayList<>();
        utilityProviders = new ArrayList<>();
        serviceProviders = new ArrayList<>();

        for (Cell cell : cellArrayList) {
            cellHashMap.put(cell.getLocation(), cell);
            if (cell instanceof Zone) {zones.add( (Zone) cell);}
            else if (cell instanceof UtilityProvider) {utilityProviders.add( (UtilityProvider) cell);}
            else if (cell instanceof ServiceProvider) {serviceProviders.add( (ServiceProvider) cell);}
        }

    }

    //Getters
    public ArrayList<Cell> getCellArrayList() {
        return cellArrayList;
    }

    public HashMap<Point, Cell> getCellHashMap() {
        return cellHashMap;
    }

    public ArrayList<Cell> getZones() {
        return zones;
    }

    public ArrayList<UtilityProvider> getUtilityProviders() {
        return utilityProviders;
    }

    public ArrayList<ServiceProvider> getServiceProviders() {
        return serviceProviders;
    }

    //Static Methods (Util Methods)
    public static ArrayList<Cell> getNeighbors(HashMap<Point, Cell> cellHashMap, Cell cell) {
        ArrayList<Cell> neighbors = new ArrayList<>();

        Point root = cell.getLocation();
        int x = root.getX();
        int y = root.getY();

        //Change it to 8-neighborhood!!!
        Point[] neighborPoints = {new Point(x, y + 1), new Point(x, y - 1), new Point(x + 1 , y), new Point(x - 1, y), new Point(x + 1, y + 1), new Point(x - 1, y - 1), new Point(x + 1, y - 1), new Point(x - 1, y - 1)};

        for (Point point : neighborPoints) {
            if (cellHashMap.containsKey(point)) {
                Cell neigborCell = cellHashMap.get(point);

                if (neigborCell instanceof Transferable) {
                    neighbors.add(neigborCell);
                }
            }
        }

        return neighbors;
    }
}
