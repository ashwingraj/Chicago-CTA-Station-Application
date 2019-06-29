/* Ashwin G. Raj 
 * 
 * CS 201: Accelerated Introduction to Computer Science
 * MW 5:00 PM - 6:15 PM 
 * Lab F 5:00 PM
 * 
 * CTAStation Object class 
 * 
 * CTA Stations object class with name, location, wheelchair accessibility (boolean), and open (boolean), 
 * corresponding default constructor, non-default constructor, mutators, accessors, toString Method, equals, and 
 * two additional methods. One to return distance between latitude and longitude input, another to return distance
 * between GeoLocation input. Object class inherits from the GeoLocation object. 
 * 
 */

package ashwinraj_courseproject;

public class CTAStation extends GeoLocation {
	
	//Instance Variables 
		private String name;
		private String location; 
		private Boolean wheelchair; 
		private int greenline;
		private int redline;
		private int blueline;
		private int brownline;
		private int purpleline;
		private int pinkline;
		private int orangeline;
		private int yellowline;
		
		//Default Constructor 
		public CTAStation() {
			setName("sample");
			setLat(0);
			setLng(0);
			setLocation("sample");
			setWheelchair(true);
			setRedLine(-1); 
			setGreenLine(-1); 
			setBlueLine(-1);
			setBrownLine(-1);
			setPurpleLine(-1);
			setPinkLine(-1);
			setOrangeLine(-1);
			setYellowLine(-1);
		}
		
		//Non-default Constructor 
		public CTAStation(String name, double lat, double lng, String location, 
							boolean wheelchair, int redline, int greenline, int blueline, int brownline, 
							int purpleline, int pinkline, int orangeline, int yellowline) {
			
			setName(name);
			setLat(lat);
			setLng(lng);
			setLocation(location);
			setWheelchair(wheelchair);
			setRedLine(redline); 
			setGreenLine(greenline);
			setBlueLine(blueline); 
			setBrownLine(brownline);
			setPurpleLine(purpleline); 
			setPinkLine(pinkline);
			setOrangeLine(orangeline); 
			setYellowLine(yellowline);
		}
		
		//Mutators
		public void setName(String name) {
			this.name = name;  
		}
		
		public void setLocation(String location) {
			this.location = location; 
		}
		
		public void setWheelchair(boolean wheelchair) {
			this.wheelchair = wheelchair; 
		}
		
		public void setRedLine(int redline) {
			this.redline = redline; 
		}
		
		public void setGreenLine(int greenline) {
			this.greenline = greenline; 
		}
		
		public void setBlueLine(int blueline) {
			this.blueline = blueline; 
		}
		
		public void setBrownLine(int brownline) {
			this.brownline = brownline; 
		}
		
		public void setPurpleLine(int purpleline) {
			this.purpleline = purpleline; 
		}
		
		public void setPinkLine(int pinkline) {
			this.pinkline = pinkline; 
		}
		
		public void setOrangeLine(int orangeline) {
			this.orangeline = orangeline; 
		}
		
		public void setYellowLine(int yellowline) {
			this.yellowline = yellowline; 
		}
		
		//Accessors / Getters
		public String getName() {
			return name; 
		}
		
		public String getLocation() {
			return location;
		}
		
		public Boolean getWheelchair() {
			return wheelchair;
		}

		public int getRedLine() {
			return redline; 
		}
		
		public int getGreenLine() {
			return greenline;
		}
		
		public int getBlueLine() {
			return blueline; 
		}
		
		public int getBrownLine() {
			return brownline; 
		}
		
		public int getPurpleLine() {
			return purpleline; 
		}
		
		public int getPinkLine() {
			return pinkline; 
		}
		
		public int getOrangeLine() {
			return orangeline; 
		}
		
		public int getYellowLine() {
			return yellowline; 
		}
		
		//To String 
		public String toString() { 
			
			String stations = ""; 
			
			if (this.getRedLine() != -1) 
				stations = stations + "Red Line" + "\n"; 
			
			if (this.getGreenLine() != -1) 
				stations = stations + "Green Line" + "\n"; 
			
			if (this.getBlueLine() != -1) 
				stations = stations + "Blue Line" + "\n"; 
			
			if (this.getBrownLine() != -1) 
				stations = stations + "Brown Line" + "\n"; 
			
			if (this.getPurpleLine() != -1) 
				stations = stations + "Purple Line" + "\n"; 
			
			if (this.getPinkLine() != -1) 
				stations = stations + "Pink Line" + "\n"; 
			
			if (this.getOrangeLine() != -1) 
				stations = stations + "Orange Line" + "\n"; 
			
			if (this.getYellowLine() != -1) 
				stations = stations + "Yellow Line" + "\n"; 
			
			String result = "Name: " + this.getName() + "\n" + "Latitude/Longitude: ("+ this.getLat() 
							+ ", " + this.getLng()+")" + "\n" +  "Location: "+ this.getLocation() + "\n" + 
							"Wheelchair: " + this.getWheelchair() + "\n" + "This station exists on the: " 
							+ "\n" + stations; 
			
	        return result;       
	    }
		
		//Method to get distance between two latitude/longitude points with a GeoLocation class as input 
		public Double calcDistance(GeoLocation g) {
			
			double lat1 = g.getLat(); 
			double lng1 = g.getLng(); 
			double lat2 = this.getLat(); 
			double lng2 = this.getLng(); 
			
			double result = Math.sqrt(Math.pow(lat1 - lat2, 2) + Math.pow(lng1 - lng2, 2)); 
			return result; 
		}
		
		//Method to get distance between two latitude/longitude points with latitude/longitude as input 
		public Double calcDistance(double lat, double lng ) {
			
			double lat1 = lat; 
			double lng1 = lng;  
			double lat2 = this.getLat(); 
			double lng2 = this.getLng(); 
			
			double result = Math.sqrt(Math.pow(lat1 - lat2, 2) + Math.pow(lng1 - lng2, 2)); 
			return result; 
		}
		
		public void assign(CTAStation c) {
			
			setName(c.getName());
			setLat(c.getLat());
			setLng(c.getLng());
			setLocation(c.getLocation());
			setWheelchair(c.getWheelchair());
			setRedLine(c.getRedLine()); 
			setGreenLine(c.getGreenLine()); 
			setBlueLine(c.getBlueLine());
			setBrownLine(c.getBrownLine());
			setPurpleLine(c.getPurpleLine());
			setPinkLine(c.getPinkLine());
			setOrangeLine(c.getOrangeLine());

		}
		
		//Method to check if object is equal to another object 
		public Boolean equals(CTAStation c) {
					
			if ((name == c.getName()) && (location == c.getLocation())
				&& (lat == c.getLat()) && (lng == c.getLng()) && (wheelchair == c.getWheelchair())
				&& (redline == c.getRedLine()) && (greenline == c.getGreenLine()) && (blueline == c.getBlueLine())
				&& (brownline == c.getBrownLine()) && (purpleline == c.getPurpleLine()) && (pinkline == c.getPinkLine())
				&& (orangeline == c.getOrangeLine()) && (yellowline == c.getYellowLine())) {
						
				return true; 
			}
					
			else {
				return false; 
			}
		}



}
