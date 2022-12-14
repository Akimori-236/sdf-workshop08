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
        locations = Location.buildWorld(readFile(filename));
        // TEST WORLD
        // for (String key : locations.keySet()) {
        // System.out.println(locations.get(key).getDescription());
        // }

        // Start the game, set current location as start
        Location start = locations.get("start");
        Location currentLocation = locations.get(start.getRoom());
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
                // reprint description again
                System.out.println(currentLocation.getName() + "\n" + currentLocation.getDescription());
            } else if (validDirections.contains(userInput)) {
                // valid direction -> set current location as the new one
                currentLocation = locations.get(currentLocation.getDirection(userInput));
                System.out.println(currentLocation.getName() + "\n" + currentLocation.getDescription());
            } else {
                // invalid command
                System.err.println("You can't get there...");
            }
        }
    }

    public static ArrayList<String> readFile(String filename) {
        ArrayList<String> data = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line);
            }
            br.close();

        } catch (FileNotFoundException e) {
            System.err.println("ERROR> File not found.");
        } catch (IOException e) {
            System.err.println("ERROR> Unable to read file.");
            // e.printStackTrace();
        }
        return data;
    }
}
