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
                    switch (s.getServiceType()){
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

                    System.out.printf("%s at %s received %s service", z.getClass().getSimpleName(), z.toString(), z.getServiceType());
                }
            }
        }
    }
}
