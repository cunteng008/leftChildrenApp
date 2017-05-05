package model;
import java.util.*;

public class LocationCollection {
	
	private static LocationCollection instance = new LocationCollection();
	
	private LocationCollection() {
		locations = new ArrayList<Location>();
	}
	
	public static LocationCollection getInstance() {
		return instance;
	}

	private List<Location> locations;
	
	public List<Location> getLocations() {
		return locations;
	}
	
	public void addLocation(Location location) {
		locations.add(location);
	}
	
}
