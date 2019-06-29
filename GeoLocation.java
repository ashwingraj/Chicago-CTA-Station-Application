/**
 * Ashwin G. Raj 
 * 
 * CS 201: Accelerated Introduction to Computer Science
 * MW 5:00 PM - 6:15 PM 
 * Lab F 5:00 PM 
 
 * GeoLocation object class with latitude and longitude fields, corresponding default constructor, 
 * non-default constructor, mutators, accessors, toString Method, and two additional methods. 
 * One to check if latitude is between -90 and 90, another to check if longitude is between 
 * -180 and 180
 */

package ashwinraj_courseproject;

public class GeoLocation {
	
	//Instance Variables
	public double lat;
	public double lng; 
	
	//Default Constructor 
	public GeoLocation() {
		setLat(0);
		setLng(0);
	}
	
	//Non-default Constructor 
	public GeoLocation(double lat, double lng) {
		setLat(lat);
		setLng(lng);
	}

	//Mutators 
	public void setLat(double lat) {
		this.lat = lat;
	}
	
	public void setLng(double lng) {
		this.lng = lng;
	}
	
	//Accessors (Getters)
	public Double getLat() {
		return lat;
	}
	
	public Double getLng() {
		return lng;
	}
	
	//toString Method 
	public String toString() { 
		String result = "Location: ("+ this.getLat()+ ", " + this.getLng()+")";
        return result;       
    } 
    
	//Return boolean to check if lat in between -90 and 90
    public Boolean latCheck() {
    	if (-90 <= lat && lat <= 90) {
    		return true;
    	}
    	else 
    		return false;
    }
  
    //Return boolean to check if lng in between -180 and 180
    public Boolean lngCheck() {
    	if (-180 <= lng && lng <= 180) {
    		return true;
    	}
    	else 
    		return false;
    }
    
    //Returns distance between two geolocation values 
    public Double calcDistance(double lat2, double long2) {
    
    	Double result = Math.sqrt(Math.pow(lat-lat2,2) + Math.pow(lng-long2,2));
    	return result; 
    }
    
    public static void main(String[] args) { 
    	
        GeoLocation instance1 = new GeoLocation(41.8781, 87.6298); 
        
        System.out.println(instance1.toString());
 
    } 
}

