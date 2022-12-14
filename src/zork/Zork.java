package zork;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Zork {
    /*
     * --- game will start with (start: west_of_house)
     * West of House
     * description.....
     * > <waitforinput>
     *
     * --- if invalid input
     * You can't go there
     * 
     * > look
     * West of House
     * description
     * 
     * > exit
     */
    public static String start;

    public static void main(String[] args) {
        // invalid cmdline arguments
        if (args.length != 1) {
            System.err.println("Usage> Zork <filename.txt>");
            return;
        }
        String filename = args[0];
        Map<String, Location> locations = new HashMap<>();
        Console cons = System.console();

        // read the file and build the world
        locations = buildWorld(readFile(filename));
        // TEST WORLD
        // for (String key : locations.keySet()) {
        // System.out.println(locations.get(key).getDescription());
        // }

        // Start the game, set current location as start
        Location currentLocation = locations.get(start);
        // display
        System.out.println(currentLocation.getName() + "\n" + currentLocation.getDescription());
        while (true) {
            // get valid inputs
            Set<String> validDirections = currentLocation.getOptions();
            // System.out.println(validDirections);

            // get user input
            String userInput = cons.readLine("> ");
            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                break;
            } else if (userInput.equalsIgnoreCase("look")) {
                System.out.println(currentLocation.getName() + "\n" + currentLocation.getDescription());
            } else if (validDirections.contains(userInput)) {
                currentLocation = locations.get(currentLocation.getDirection(userInput));
                System.out.println(currentLocation.getName() + "\n" + currentLocation.getDescription());
            } else {
                System.err.println("You can't get there...");
            }
        }
    }

    public static Map<String, Location> buildWorld(ArrayList<String> data) {
        Map<String, Location> locations = new HashMap<>();
        // System.err.println(data);
        Location location = new Location();
        for (String line : data) {
            line = line.trim();
            // System.err.println(line);
            if (line.length() > 1) {
                String[] d = line.split(": ");
                // System.err.println(d.length);
                String type = d[0];
                String desc = d[1];
                if (type.equalsIgnoreCase("start")) {
                    start = desc;
                } else {
                    switch (type) {
                        case "room":
                            location.setRoom(desc);
                            break;
                        case "name":
                            location.setName(desc);
                            break;
                        case "description":
                            location.setDescription(desc);
                            break;
                        case "direction":
                            location.addDirection(desc);
                    }
                }
            } else {
                locations.put(location.getRoom(), location);
                location = new Location();
            } // end if

        } // end for
        return locations;
    }

    public static ArrayList<String> readFile(String filename) {
        ArrayList<String> data = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("ERROR> File not found.");
        } catch (IOException e) {
            System.err.println("ERROR> Unable to read file.");
            // e.printStackTrace();
        }
        return data;
    }
}
