package com.objectville.core;

import com.objectville.entity.cells.utilityProviders.UtilityProvider;
import com.objectville.entity.cells.zones.Zone;
import com.objectville.fileIO.MapReader;
import com.objectville.util.ResourceDistributor;
import com.objectville.util.ServiceDistributor;
import com.objectville.util.UtilityDistributor;

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
        utilityDistributor = new UtilityDistributor(gridManager.getCellHashMap(), gridManager.getUtilityProviders());
        resourceDistributor = new ResourceDistributor(gridManager.getCellArrayList(), gridManager.getZones());
    }


    public void tick() {

        //Step 1
        serviceDistributor.distribute(); //it handles everything, I do not need to concern about its internal structure (abstraction)

        //Step 2
        utilityDistributor.distribute();

        for (UtilityProvider utilityProvider : gridManager.getUtilityProviders()) {
            utilityProvider.refresh();
        }

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
