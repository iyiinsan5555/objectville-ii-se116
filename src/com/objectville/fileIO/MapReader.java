package com.objectville.fileIO;

import com.objectville.entity.cells.Cell;
import com.objectville.entity.cells.Empty;
import com.objectville.entity.cells.Road;
import com.objectville.entity.cells.serviceProviders.*;
import com.objectville.entity.cells.zones.*;
import com.objectville.entity.cells.utilityProviders.*;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;

public class MapReader {

    //Constants
    public static final char housingSymbol = 'H';
    public static final char industrialSymbol = 'I';
    public static final char commercialSymbol = 'C';
    public static final char powerPlantSymbol = 'P';
    public static final char waterPumpingStationSymbol = 'W';
    public static final char internetHubSymbol = 'T';
    public static final char policeStationSymbol = 'F';
    public static final char hospitalSymbol = 'D';
    public static final char schoolSymbol = 'S';
    public static final char roadSymbol = 'R';
    public static final char emptySymbol = 'E';


    public ArrayList<Cell> readMap(Path mapPath) {

        ArrayList<Cell> cellArrayList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(mapPath.toFile()))) {

            String line;
            int row = 0;

            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                //Now I need to get every character and create a cell based on the char
                for (int i=0; i<line.length(); i++) { // i = column
                    char symbol = line.charAt(i);

                    Cell cell = null;
                    try {
                        cell = createCellFromSymbol(symbol, row, i);
                    } catch (IllegalArgumentException e) {
                        System.err.println(e);
                        System.err.println("By default, cell will be Empty type");

                        cell = new Empty(row, i);
                    } finally {
                        cellArrayList.add(cell);
                    }

                }
                row++;
            }

        } catch (FileNotFoundException e) {
            System.err.println(e);
            System.exit(1); //If it cannot find the file, what can we do???
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }

        return cellArrayList;
    }

    private Cell createCellFromSymbol(char symbol, int x, int y) throws IllegalArgumentException {
        Cell cell = null;

        switch (symbol) {
            case housingSymbol:
                cell = new Housing(x, y); //implicit upcasting
                break;
            case industrialSymbol:
                cell = new Industrial(x, y);
                break;
            case commercialSymbol:
                cell = new Commercial(x, y);
                break;
            case powerPlantSymbol:
                cell = new PowerPlant(x, y);
                break;
            case waterPumpingStationSymbol:
                cell = new WaterPumpingStation(x, y);
                break;
            case internetHubSymbol:
                cell = new InternetHub(x, y);
                break;
            case policeStationSymbol:
                cell = new PoliceStation(x, y);
                break;
            case hospitalSymbol:
                cell = new Hospital(x, y);
                break;
            case schoolSymbol:
                cell = new School(x, y);
                break;
            case roadSymbol:
                cell = new Road(x, y);
                break;
            case emptySymbol:
                cell = new Empty(x, y);
                break;
            default:
                System.err.println("Cannot resolve symbol: " + symbol);
                throw new IllegalArgumentException("Symbol (" + symbol + ") cannot be resolved");
        }

        return cell;
    }
}