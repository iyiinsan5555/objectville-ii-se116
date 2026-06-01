import com.objectville.core.SimulationRunner;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main { //will handle -jar (and args --> nTick & mapFileName)
    public static void main(String[] args) {

        /*if (args.length != 2) {
            System.err.println("Usage: java -jar ObjectVilleGame.jar <map name> <tick count>");
            System.out.println("Example usage: java -jar ObjectVilleGame.jar mymap.txt 5");
            return;
        }

         */

        try {
            //String mapFileName = args[0];
            //int nTicks = Integer.parseInt(args[1]);

            String mapFileName = "exampleMap.txt";
            int nTicks = 10;

            Path mapPath = Paths.get(mapFileName);

            SimulationRunner simulationRunner = new SimulationRunner(nTicks, mapPath);
            simulationRunner.run();

        } catch (NumberFormatException e) {
            System.err.println("Please provide an integer as the second argument!");
            System.err.println("Error message: " + e);
        } catch (Exception e) {
            System.err.println("Something went wrong: " + e);
        }


    }
}
