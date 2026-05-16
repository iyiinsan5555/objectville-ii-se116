import java.util.ArrayList;
import java.util.HashMap;

public class GridManager {
    ArrayList<Cell> cellArrayList;
    ArrayList<Zone> zones;
    ArrayList<UtilityProvider> utilityProviders;
    ArrayList<ServiceProvider> serviceProviders;

    public GridManager(ArrayList<Cell> cellArrayList) {
        this.cellArrayList = cellArrayList;

        //Implementing other fields
        zones = new ArrayList<>();
        utilityProviders = new ArrayList<>();
        serviceProviders = new ArrayList<>();

        for (Cell cell : cellArrayList) {
            if (cell instanceof Zone) {zones.add( (Zone) cell);}
            else if (cell instanceof UtilityProvider) {utilityProviders.add( (UtilityProvider) cell);}
            else if (cell instanceof ServiceProvider) {serviceProviders.add( (ServiceProvider) cell);}
        }

    }

    //Getters
    public ArrayList<Cell> getCellArrayList() {
        return cellArrayList;
    }

    public ArrayList<Zone> getZones() {
        return zones;
    } // changed into appropriate object since Zone is active now

    public ArrayList<UtilityProvider> getUtilityProviders() {
        return utilityProviders;
    }

    public ArrayList<ServiceProvider> getServiceProviders() {
        return serviceProviders;
    }

    //Static Methods (Util Methods)
    //getNeighbors() needs fix. At the moment should not use HashMap because it gives undesired results
}
