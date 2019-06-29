/**
 * Ashwin G. Raj 
 * 
 * CS 201: Accelerated Introduction to Computer Science
 * MW 5:00 PM - 6:15 PM 
 * Lab F 5:00 PM 
 *
 * 
 * CTA Route object class with name, ArrayList of stops, 
 * corresponding default constructor, non-default constructor, mutators, accessors, toString Method, equals, and 
 * six additional methods as follows: add station, remove station, insert station, lookup station, and find the nearest
 * station given a latitude/longitude input or a geolocation.
 * Object class inherits from the CTAStations object. 
 */

package ashwinraj_courseproject;

import java.util.ArrayList;

public class CTARoute extends CTAStation {
	
	//Instance Variables 
	private String name; 
	private ArrayList<CTAStation> stops = new ArrayList<>();
	
	//Default Constructor 
	public CTARoute() {
		setName("Sample");
		setStops(new ArrayList<>()); 
	}
	
	//Non-Default Constructor
	public CTARoute(String name, ArrayList<CTAStation> list) {
		setName(name); 
		setStops(list); 	
	}
	
	//Accessors 
	public String getName() {
		return name; 
	}
	
	public ArrayList<CTAStation> getStops() {
		return stops; 
	}
	
	//Setters
	public void setName(String name) {
		this.name = name; 
	}

	public void setStops(ArrayList<CTAStation> list) { 
		for (int i = 0; i < list.size(); i++) {
			stops.add(list.get(i)); } 
		}	
	
	//Returns Object as String 
	public String toString() {
		String result = this.getName() + "\n" + this.getStops(); 
		return result; 
		
	}
	
	//Method to check if given CTARoute object is equal to another CTARoute object
	public boolean equals(CTARoute c) {
		
		if ((name == c.getName()) && (stops == c.getStops())) {		
			return true; 
		}
				
		else {
			return false; 
		}
	}
	
	//Add station to ArrayList of Stops
	public void addStation(CTAStation station) {
		stops.add(station); 
	}
	
	//Remove station from ArrayList of Stops
	public void removeStation(CTAStation station) {
		stops.remove(station); 
	}
	
	//Insert station given position and station 
	public void insertStation(int position, CTAStation station) {
		stops.add(position, station);
	}
	
	//Lookup station information given a station name
	public CTAStation lookupStation(String station) {
		
		int index = 0; 
		
		for (int i = 0; i < this.stops.size(); i++) {
			if (station == stops.get(i).getName()) {
				index = i;  
			}
			else {
				continue; 
			}
		}
		
		return stops.get(index); 
	
	}
	
	//Find Nearest Station given latitude and longitude 
	public String nearestStation(double lat, double lng) {
		
		double min_distance = stops.get(0).calcDistance(lat, lng);
		int index = 0; 	
	 
		for (int i = 1; i < stops.size(); i++) {    
			if (min_distance > stops.get(i).calcDistance(lat, lng)) {
				min_distance = stops.get(i).calcDistance(lat, lng);
				index = i; 
			}
			
			else {
			  continue; 
			}
		}
		
		return stops.get(index).getName(); 
		
	}
	
	//Find nearest Station given GeoLocation
	public String nearestStation(GeoLocation l) {
		
		double min_distance = stops.get(0).calcDistance(l.getLat(), l.getLng());
		int index = 0; 	
	 
		for (int i = 1; i < stops.size(); i++) {    
			if (min_distance > stops.get(i).calcDistance(l.getLat(), l.getLng())) {
				min_distance = stops.get(i).calcDistance(l.getLat(), l.getLng());
				index = i; 
			}
			
			else {
			  continue; 
			}
		}
		
		return stops.get(index).getName();
		
	}
	
		
}
	


