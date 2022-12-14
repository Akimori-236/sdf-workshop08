package zork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Location {
    private String room;
    private String name;
    private String description;
    private Map<String, String> directions = new HashMap<>();

    public String getRoom() {
        return this.room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {;
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description.replaceAll("<break>", "\n");
    }

    public String getDirection(String d) {
        return this.directions.get(d);
    }

    public Set<String> getOptions() {
        return this.directions.keySet();
    }

    public void addDirection(String directionLocation) {
        String[] data = directionLocation.split(" ");
        directions.put(data[0], data[1]);
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
                    Location startingLocation = new Location();
                    startingLocation.setRoom(desc);
                    locations.put("start", startingLocation);
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
                } // end if else
            } else {
                locations.put(location.getRoom(), location);
                location = new Location();
            } // end if else
        } // end for
        return locations;
    }
}
