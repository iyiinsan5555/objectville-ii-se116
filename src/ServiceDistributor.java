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
                Point location = z.getLocation();
                int X =  location.getX();
                int Y = location.getY();
                if (s.getLocation().distanceTo(location) <= s.getRadius()){
                    switch (s.getServiceType()){
                        case "PoliceStation":
                            z.setHasSecurity(true);
                            System.out.printf("%s at (%d,%d) received security service", z.getClass().getSimpleName(), X, Y);
                            break;
                        case "Hospital":
                            z.setHasHealth(true);
                            System.out.printf("%s at (%d,%d) received health service", z.getClass().getSimpleName(), X, Y);
                            break;
                        case "School":
                            z.setHasEducation(true);
                            System.out.printf("%s at (%d,%d) received education service", z.getClass().getSimpleName(), X, Y);
                            break;
                    }
                }
            }
        }
    }
}
