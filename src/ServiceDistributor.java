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
                            z.setSecurity(true);
                            break;
                        case "Hospital":
                            z.setHealth(true);
                            break;
                        case "School":
                            z.setEducation(true);
                            break;
                    }
                }
            }
        }
    }
}
