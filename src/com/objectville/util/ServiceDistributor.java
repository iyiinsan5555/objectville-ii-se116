package com.objectville.util;

import com.objectville.entity.cells.serviceProviders.ServiceProvider;
import com.objectville.entity.cells.zones.Zone;

import java.util.ArrayList;

public class ServiceDistributor {
    private ArrayList<Zone> zones;
    private ArrayList<ServiceProvider> serviceProviders;

    public ServiceDistributor(ArrayList<Zone> zones, ArrayList<ServiceProvider> serviceProviders) {
        this.zones = zones;
        this.serviceProviders = serviceProviders;
    }

    public void distribute(){
        for(ServiceProvider s : serviceProviders){
            for (Zone z : zones){
                if (s.getLocation().distanceTo(z.getLocation()) <= s.getRadius()){
                    switch (s.getServiceProviderType()){
                        case "PoliceStation":
                            z.setHasSecurity(true);
                            break;
                        case "Hospital":
                            z.setHasHealth(true);
                            break;
                        case "School":
                            z.setHasEducation(true);
                            break;
                    }
                    //Print message
                    System.out.println(z.getZoneType() + " at " + z.getLocation() + " received " + s.getServiceType().toLowerCase() +" service");
                }
            }
        }
    }
}
