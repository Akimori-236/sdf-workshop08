package zork;

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

    public String getDescription() {
        String desc = this.description.replaceAll("<break>", "\n");
        return desc;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
