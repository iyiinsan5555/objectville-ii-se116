import java.nio.file.Path;

public class SimulationRunner {
    private int nTicks;
    private MapReader mapReader;
    private GridManager gridManager;
    private ServiceDistributor serviceDistributor;
    private UtilityDistributor utilityDistributor;
    private ResourceDistributor resourceDistributor;


    public SimulationRunner(int nTicks, Path mapPath) {
        this.nTicks = nTicks;
        mapReader = new MapReader();
        gridManager = new GridManager(mapReader.readMap(mapPath)); //reads the map and return the cellArrayList
        serviceDistributor = new ServiceDistributor(gridManager.getZones(), gridManager.getServiceProviders());
        utilityDistributor = new UtilityDistributor(gridManager.getCellArrayList(), gridManager.getCellHashMap());
        resourceDistributor = new ResourceDistributor(gridManager.cellArrayList, gridManager.zones);
    }


    public void tick() {

        //Step 1
        serviceDistributor.distribute(); //it handles everything, I do not need to concern about its internal structure (abstraction)

        //Step 2
        utilityDistributor.distribute();

        //Step 3
        resourceDistributor.distribute();

        //Step 4
        for (Zone zone : gridManager.getZones()) {
            zone.update();
        }

        //Step 5
        resourceDistributor.accumulate();

    }

    public void run() {
        for (int i=0; i<nTicks; i++) {
            System.out.println("Tick " + (i+1)); //saw in sample output
            this.tick(); //tick() for number of ticks
        }
    }



}
