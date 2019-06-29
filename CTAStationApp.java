/**
 * Ashwin G. Raj 
 * 
 * CS 201: Accelerated Introduction to Computer Science
 * MW 5:00 PM - 6:15 PM 
 * Lab F 5:00 PM 
 * 
 * Friday May 10, 2019 
 * 
 * More information found in the README file
 *  
 */

package ashwinraj_courseproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;    
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class CTAStationApp {
	
	public static void main(String [] args) {  
		
		ArrayList<CTAStation> AllStops = new ArrayList<CTAStation>();
		ArrayList<CTAStation> RedLineStops = new ArrayList<CTAStation>();
		ArrayList<CTAStation> GreenLineStops = new ArrayList<CTAStation>();
		ArrayList<CTAStation> BlueLineStops = new ArrayList<CTAStation>();
		ArrayList<CTAStation> BrownLineStops = new ArrayList<CTAStation>();
		ArrayList<CTAStation> PurpleLineStops = new ArrayList<CTAStation>();
		ArrayList<CTAStation> PinkLineStops = new ArrayList<CTAStation>();
		ArrayList<CTAStation> OrangeLineStops = new ArrayList<CTAStation>();
		ArrayList<CTAStation> YellowLineStops = new ArrayList<CTAStation>();
		
		
		//Reads in CSV file to Java objects 
		readCSV(AllStops, RedLineStops, GreenLineStops, BlueLineStops, BrownLineStops, PurpleLineStops, 
				PinkLineStops, OrangeLineStops, YellowLineStops); 
		
		
		//Sort stations in routes in correct order
		Collections.sort(RedLineStops, new sortByRed()); 
		Collections.sort(GreenLineStops, new sortbyGreen());
		Collections.sort(BlueLineStops, new sortByBlue());
		Collections.sort(BrownLineStops, new sortByBrown()); 
		Collections.sort(PurpleLineStops, new sortByPurple()); 
		Collections.sort(PinkLineStops, new sortByPink());
		Collections.sort(OrangeLineStops, new sortByOrange());
		Collections.sort(YellowLineStops, new sortByYellow());
		
		
		CTARoute CTARedLine = new CTARoute("Red Line", RedLineStops);
	    CTARoute CTAGreenLine = new CTARoute("Green Line", GreenLineStops);
	    CTARoute CTABlueLine = new CTARoute("Blue Line", BlueLineStops);
	    CTARoute CTABrownLine = new CTARoute("Brown Line", BrownLineStops);
	    CTARoute CTAPurpleLine = new CTARoute("Purple Line", PurpleLineStops);
	    CTARoute CTAPinkLine = new CTARoute("Pink Line", PinkLineStops);
	    CTARoute CTAOrangeLine = new CTARoute("Orange Line", OrangeLineStops);
	    CTARoute CTAYellowLine = new CTARoute("Yellow Line", YellowLineStops);
		
	    ArrayList<CTARoute> CTATransitSystem = new ArrayList<CTARoute>(); 
	    
	    CTATransitSystem.add(CTARedLine); 
	    CTATransitSystem.add(CTAGreenLine);
	    CTATransitSystem.add(CTABlueLine);
	    CTATransitSystem.add(CTABrownLine);
	    CTATransitSystem.add(CTAPurpleLine);
	    CTATransitSystem.add(CTAPinkLine);
	    CTATransitSystem.add(CTAOrangeLine);
	    CTATransitSystem.add(CTAYellowLine);
	    
		while (true) {
			
			//Display menu of options for user 
			displayMenu(); 
			
			Scanner user_input = new Scanner (System.in);
			String input = user_input.nextLine();
			
		  try {
			  
			int function = Integer.parseInt(input); 
			
			switch (function) {
				
				//Display Station Names 
				case 1:
					
					printCTAStationNames(CTATransitSystem); 
					
					continue; 
				
				//Create a new station
				case 2:
					createStation(CTATransitSystem, AllStops, CTARedLine, CTAGreenLine, CTABlueLine, 
							CTABrownLine, CTAPurpleLine, CTAPinkLine, 
							CTAOrangeLine, CTAYellowLine); 
					continue;
				
				//Modify existing station
				case 3:
					modifyStation(CTATransitSystem); 
					continue; 
				
				//Remove Station
				case 4:
					removeStation(CTATransitSystem); 
					continue; 
				
				//Search Station	
				case 5:
					moreInformation(CTATransitSystem, AllStops); 
					continue; 
				
				//Find Nearest Station 	
				case 6:
					findNearest(AllStops, CTATransitSystem); 
					continue; 
				
				//Find a path between two stations 
				case 7:
					
					String starting_station = getStartingStation(AllStops); 
					String ending_station = getEndingStation(AllStops); 
					
					findPath(CTATransitSystem, AllStops, starting_station, ending_station);
					
					continue; 
					
				//Exit
				case 8: 
					System.out.println("Goodbye!");
					break; 
					
				default:
					System.out.println("Please enter a valid number from the menu.");
					System.out.println(" ");
					continue; 
				}
			
		  } catch (Exception e) {
			  
			  System.out.println("Please enter a valid number from the menu.");
			  System.out.println("");
		  }
			
		  user_input.close();
		  break;  
		}
		
		
	}

	//Reads in CSV file of CTA Station Stops and creates a list of CTAStations objects 
	 public static void readCSV(ArrayList<CTAStation> AllStops, ArrayList<CTAStation> RedLineStops,
			 ArrayList<CTAStation> GreenLineStops, ArrayList<CTAStation> BlueLineStops, 
			 ArrayList<CTAStation> BrownLineStops, ArrayList<CTAStation> PurpleLineStops,
			 ArrayList<CTAStation> PinkLineStops, ArrayList<CTAStation> OrangeLineStops, 
			 ArrayList<CTAStation> YellowLineStops) {
			
			 //sets filename and delimiters 		
			  String Filename = "src/project/CTAStops.csv";    
			  BufferedReader br = null;    
			  String line = "";    
			  String splitBy = ",";    
			   
			    
			  try {     
				  
				   br = new BufferedReader(new FileReader(Filename)); 
				   line = br.readLine(); 
				   line = br.readLine();
				   
				   while ((line = br.readLine()) != null) {    
					   
				    // split on comma(',')    
				    String[] CTAinfo = line.split(splitBy);  
				    
				    // get the information fields for each stop
				    String strName = CTAinfo[0];
				    String strLat = CTAinfo[1];
				    String strLng = CTAinfo[2];
				    String strLocation = CTAinfo[3];
				    String strWheelchair = CTAinfo[4];
				    String strRedLine = CTAinfo[5];
				    String strGreenLine = CTAinfo[6]; 
				    String strBlueLine = CTAinfo[7];
				    String strBrownLine = CTAinfo[8]; 
				    String strPurpleLine = CTAinfo[9];
				    String strPinkLine = CTAinfo[10]; 
				    String strOrangeLine = CTAinfo[11];
				    String strYellowLine = CTAinfo[12]; 
				    
			
				    // Parse variables into correct type
				    double dblLat = Double.parseDouble(strLat);
				    double dblLng = Double.parseDouble(strLng);
				    boolean boolWheelchair = Boolean.parseBoolean(strWheelchair);
				    int intRedLine = Integer.parseInt(strRedLine); 
				    int intGreenLine = Integer.parseInt(strGreenLine);
				    int intBlueLine = Integer.parseInt(strBlueLine); 
				    int intBrownLine = Integer.parseInt(strBrownLine);
				    int intPurpleLine = Integer.parseInt(strPurpleLine); 
				    int intPinkLine = Integer.parseInt(strPinkLine);
				    int intOrangeLine = Integer.parseInt(strOrangeLine); 
				    int intYellowLine = Integer.parseInt(strYellowLine);
				    
				    
				    // create car object to store values    
				    CTAStation CTAObject = new CTAStation();
				    
				    //Set variables into object 
				    CTAObject.setName(strName);
				    CTAObject.setLat(dblLat);
				    CTAObject.setLng(dblLng);
				    CTAObject.setLocation(strLocation);
				    CTAObject.setWheelchair(boolWheelchair);
				    CTAObject.setRedLine(intRedLine); 
				    CTAObject.setGreenLine(intGreenLine);
				    CTAObject.setBlueLine(intBlueLine); 
				    CTAObject.setBrownLine(intBrownLine);
				    CTAObject.setPurpleLine(intPurpleLine); 
				    CTAObject.setPinkLine(intPinkLine);
				    CTAObject.setOrangeLine(intOrangeLine); 
				    CTAObject.setYellowLine(intYellowLine);
				    
				    //AllStops.add(CTAObject); 
				    
				    if (intRedLine != -1) 
				    	RedLineStops.add(CTAObject); 
				    
				    if (intGreenLine != -1)
				    	GreenLineStops.add(CTAObject); 
				    
				    if (intBlueLine != -1) 
				    	BlueLineStops.add(CTAObject); 
				    
				    if (intBrownLine != -1)
				    	BrownLineStops.add(CTAObject);
				    
				    if (intPurpleLine != -1) 
				    	PurpleLineStops.add(CTAObject); 
				    
				    if (intPinkLine != -1)
				    	PinkLineStops.add(CTAObject);
				    
				    if (intOrangeLine != -1) 
				    	OrangeLineStops.add(CTAObject); 
				    
				    if (intYellowLine != -1)
				    	YellowLineStops.add(CTAObject);
				    
				    AllStops.add(CTAObject);
				   
				   }	
			   
		  }
		  		  
		  catch (FileNotFoundException e) {    
			   e.printStackTrace();    
			  } catch (IOException e) {    
			   e.printStackTrace();    
			  } finally {    
			   if (br != null) {    
			    try {    
			     br.close();    
			    } catch (IOException e) {    
			     e.printStackTrace();    
			    }    
			   }    
			  }
			  
			  
	}	
	 
	 // Displays Menu to User 
	 public static void displayMenu() {
		 
		 	System.out.println("Welcome, to the CTA Station App! Please enter the number of your choice: ");
			System.out.println(" ");
			System.out.println("1: Display station names");
			System.out.println("2: Create a new station");
			System.out.println("3: Modify an existing station");
			System.out.println("4: Remove an existing station");
			System.out.println("5: Learn more about a station");
			System.out.println("6: Find nearest station");
			System.out.println("7: Find a path between two stations");
			System.out.println("8: Exit Application");
			System.out.println("");
			
	 }
	 
	 //Prompts user to pick a route to either create or modify a station
	 public static int displayRouteChoice() {
		 
		 while (true) {
			 
		 Scanner user_input = new Scanner (System.in); 
			
		 System.out.println("Enter the route the station is on:" + "\n" + 
		 			"-----------------------------------" + "\n" +
		 			"'0' for Red Line" + "\n" + 
		 			"'1' for Green Line " + "\n" +
		 			"'2' for Blue Line " + "\n" +
		 			"'3' for Brown Line " + "\n" +
		 			"'4' for Purple Line " + "\n" +
		 			"'5' for Pink Line " + "\n" +
		 			"'6' for Orange Line " + "\n" +
		 			"'7' for Yellow Line ");
		 	
	  	 String route = user_input.nextLine();
	  	 
	  	 try {
	  		int route_choice = Integer.parseInt(route); 
	  		
	  		if (route_choice >= 0 & route_choice <= 7 )
	  			return route_choice; 
	  			
	  		else {
	  			System.out.println("Please enter a valid route number.");
	  		 	System.out.println(" "); 
	  		}
	  	  } catch (Exception e) {
	  		 System.out.println("Please enter a valid route number.");
	  		 System.out.println(" ");
	  		
	  	  }
	  	 
		} 
		
	 }
	 
	 //Prints all CTA Station names in an Arraylist 
	 public static void printCTAStationNames(ArrayList<CTARoute> routes) {    
		 	
		 for (CTARoute route: routes) {
			 
		 	System.out.println("");
		 	System.out.println(route.getName()); 
		 	System.out.println("Stations Names");
		 	System.out.println("--------------");
		 
		 	for (int i = 0; i < route.getStops().size(); i++) {    
			  System.out.println(route.getStops().get(i).getName());    
		 	}
		 	
		 	System.out.println("");
		 }
	 } 
	 
	 //Create a new Station 
	 public static void createStation(ArrayList<CTARoute> routes, ArrayList<CTAStation> AllStops, CTARoute CTARedLine, CTARoute CTAGreenLine, 
			 CTARoute CTABlueLine, CTARoute CTABrownLine, CTARoute CTAPurpleLine, CTARoute CTAPinkLine,
			 CTARoute CTAOrangeLine, CTARoute CTAYellowLine) {
	 
		 	Scanner user_input = new Scanner (System.in); 
			
		 	int route_choice = displayRouteChoice();  
			
	 		System.out.println("Enter Station Name: ");
			String str_name = user_input.nextLine(); 
			
			Double dbl_lat = 0.0; 
			Double dbl_lng = 0.0; 
			Boolean bool_wheelchair = null; 
			String station_after = ""; 
			
			while (true) {
				
				System.out.println("Enter Latitude: ");
				String latitude = user_input.nextLine();
				
				try {
					
					dbl_lat = Double.parseDouble(latitude);
					
					if (dbl_lat > -90 & dbl_lat < 90) 
						break; 
					else 
						System.out.println("Please enter a valid number between -90 and 90. "); 
					
				} catch (Exception e) {
					System.out.println("Please enter a valid number between -90 and 90. ");
				}
			  
			}	
			
			while (true) {
				
				System.out.println("Enter Longitude: ");
				String longitude = user_input.nextLine();
				
				try {
					
					dbl_lng = Double.parseDouble(longitude);
					
					if (dbl_lng > -180 & dbl_lng < 180) 
						break; 
					else 
						System.out.println("Please enter a valid number between -180 and 180. "); 
					
				} catch (Exception e) {
					System.out.println("Please enter a valid number between -180 and 180. ");
				}
			  
			}	
			
			System.out.println("Enter Location: ");
			String str_location = user_input.nextLine();
			
			while (true) {
				
				System.out.println("Enter Wheelchair Accessibility (true/false): ");
				String wheelchair = user_input.nextLine();
				
				try {
					if (wheelchair.charAt(0) == 't') {
						bool_wheelchair = true; 
						break; 
						
					} else if (wheelchair.charAt(0) == 'f') {
						bool_wheelchair = false; 
						break; 
						
					} else {
						System.out.println("Please enter either 'true' or 'false'");
					}
					
				} catch (Exception e) {
					System.out.println("Please enter either 'true' or 'false'");
				}
				
			  //break; 
			}
			
			while (true) {
				
				System.out.println("Enter the name of the station that will be AFTER this addition: ");
				station_after = user_input.nextLine();
				
				try {
					if (stationCheck(routes.get(route_choice).getStops(),station_after))
						break; 
					else
						System.out.println("This station does not exist on this CTA Route"); 
					
				} catch (Exception e) {
					System.out.println("This station does not exist on this CTA Route");
				}
			
			}
			
			int index = findIndex(routes.get(route_choice).getStops(), station_after);  
			CTAStation new_station = new CTAStation(str_name, dbl_lat, dbl_lng, str_location, bool_wheelchair, 
													-1, -1, -1, -1, -1, -1, -1, -1); 
			
			if (route_choice == 0) {
			    new_station.setRedLine(index);  
				
			} else if (route_choice == 1) {
				new_station.setGreenLine(index);
				
			} else if (route_choice == 2) {
				new_station.setBlueLine(index);
				
			} else if (route_choice == 3) {
				new_station.setBrownLine(index);
				
			} else if (route_choice == 4) {
				new_station.setPurpleLine(index);
				
			} else if (route_choice == 5) {
				new_station.setPinkLine(index);
				
			} else if (route_choice == 6) {
				new_station.setOrangeLine(index);
				
			} else if (route_choice == 7) {
				 new_station.setYellowLine(index);
			}
			
			routes.get(route_choice).insertStation(index, new_station);
			
			AllStops.add(new_station); 
			
			System.out.println("Completed!");
	 }
	 
	 public static void modifyStation(ArrayList<CTARoute> routes) {
		 
		 	Scanner user_input = new Scanner (System.in); 
		 	
		 	//Ask user for the route the station is on and name of station to be modified 
		 	int route_choice = displayRouteChoice(); 
		 	
		 	String station_name = ""; 
		 	
		 	while (true) {
				
				System.out.println("Enter Station Name: ");
				station_name = user_input.nextLine();
				
				try {
					if (stationCheck(routes.get(route_choice).getStops(),station_name))
						break; 
					else
						System.out.println("This station does not exist on this CTA Route"); 
					
				} catch (Exception e) {
					System.out.println("This station does not exist on this CTA Route");
				}
			
			}
		 	
			
			//call findIndex function to return the index to display current information  
			int station_index = findIndex(routes.get(route_choice).getStops(), station_name);
			
			//Display current information of station 
			System.out.println("Current Station Information:");
			System.out.println("---------------------------");
			System.out.println(routes.get(route_choice).getStops().get(station_index).toString());
			System.out.println("---------------------------");
			
		 	System.out.println("The program will now prompt you for changes to be made to the station."
		 			+ "\n" + "If you would NOT like to change the indicated field of information, "
		 			+ "\n" + "enter 'nc' to indicate no change.");
			
		 	System.out.println(" ");
		 	
		 	//Prompt user for CTA Station information 
		 	System.out.println("Enter Station Name: ");
			String str_name = user_input.nextLine(); 
			
			if (!(str_name.equalsIgnoreCase("nc"))) {
				routes.get(route_choice).getStops().get(station_index).setName(str_name);
			} 
			
			
			while (true) {
				
				System.out.println("Enter Latitude: ");
				String str_lat = user_input.nextLine();
				
				try {
					
					if (str_lat.equalsIgnoreCase("nc")) {
						break; 
						
					} 
					
					double dbl_lat = Double.parseDouble(str_lat);
					
					if (dbl_lat > -90 & dbl_lat < 90) {
						routes.get(route_choice).getStops().get(station_index).setLat(dbl_lat);
						break; 
					} else 
						System.out.println("Please enter a valid number between -90 and 90 or 'nc'. "); 
					
				} catch (Exception e) {
					System.out.println("Please enter a valid number between -90 and 90 or 'nc'. ");
				}
			  
			}	
			
			
			while (true) {
				
				System.out.println("Enter Longitude: ");
				String str_lng = user_input.nextLine();
				
				try {
					
					if (str_lng.equalsIgnoreCase("nc")) {
						break; 
						
					} 
					
					double dbl_lng = Double.parseDouble(str_lng);
					
					if (dbl_lng > -180 & dbl_lng < 180) {
						routes.get(route_choice).getStops().get(station_index).setLng(dbl_lng);
						break; 
					} else {
						System.out.println("Please enter a valid number between -180 and 180 or 'nc' "); 
					}	
					
				} catch (Exception e) {
					System.out.println("Please enter a valid number between -180 and 180 or 'nc'");
				}
			  
			}			
			
			System.out.println("Enter Location: ");
			String str_location = user_input.nextLine();
			
			if (!(str_location.equalsIgnoreCase("nc")))
				routes.get(route_choice).getStops().get(station_index).setLocation(str_location);
			
			while (true) {
				
				System.out.println("Enter Wheelchair Accessibility (true/false): ");
				String wheelchair = user_input.nextLine();
				
				try {
					
					if (wheelchair.equalsIgnoreCase("nc")) {
						break; 
						
					} else if (wheelchair.charAt(0) == 't') {
						routes.get(route_choice).getStops().get(station_index).setWheelchair(true);
						break; 
						
					} else if (wheelchair.charAt(0) == 'f') {
						routes.get(route_choice).getStops().get(station_index).setWheelchair(false);
						break; 
						
					} else {
						System.out.println("Please enter either 'true' or 'false' or 'nc'");
					}
					
				} catch (Exception e) {
					System.out.println("Please enter either 'true' or 'false' or 'nc'");
				}
			}
			
			
			//Display updated information   
			System.out.println("Updated Information of Station:");
			System.out.println("-----------------------");
			System.out.println(routes.get(route_choice).getStops().get(station_index).toString());
			System.out.println("-----------------------");
		 
	 }
	 
	 public static int findIndex(ArrayList<CTAStation> C, String station) {

		    for (int i = 0; i < C.size(); i++) {
				if (station.equalsIgnoreCase(C.get(i).getName()))
					return i;
				else	
					continue; 
		    }	
			
		    return -1;
		}
	 
	 //Removes a given station from a single route 
	 public static void removeStation(ArrayList<CTARoute> routes) {

		 Scanner user_input = new Scanner (System.in); 
		 	
		 //Ask user for the route the station is on and name of station to be modified 
		 int route_choice = displayRouteChoice();
		 
		 String station_name = ""; 
		 	
		 	while (true) {
				
				System.out.println("Enter Station Name: ");
				station_name = user_input.nextLine();
				
				try {
					if (stationCheck(routes.get(route_choice).getStops(),station_name))
						break; 
					else
						System.out.println("This station does not exist on this CTA Route"); 
					
				} catch (Exception e) {
					System.out.println("This station does not exist on this CTA Route");
				}
			
			}
		 
		 int station_index = findIndex(routes.get(route_choice).getStops(), station_name);
		 
		 System.out.println("Station Information:");
	     System.out.println("-----------------------");
		 System.out.println(routes.get(route_choice).getStops().get(station_index).toString());
		 System.out.println("-----------------------");
		 //System.out.println(" ");
		 
		 System.out.println("Are you sure you'd like to delete this station from the route? (Enter Yes or No): ");
		 String confirmation = user_input.nextLine(); 
		 
		 if (confirmation.charAt(0) == 'y') {
			 routes.get(route_choice).removeStation(routes.get(route_choice).getStops().get(station_index));
			 
			 System.out.println("The " + station_name + " station has been removed from the " + 
				 		routes.get(route_choice).getName());
			 
			 System.out.println(" ");
			 
		 } else if (confirmation.charAt(0) == 'n') {
			 System.out.println(" ");
			 System.out.println("Okay!");
			 System.out.println(" ");
		 }
		 
		 
	 }
	 
	 
	 //Check to see if a given station (string) is in a CTA Route
	 public static boolean stationCheck(ArrayList<CTAStation> route, String station_name) {
		 		 
		 for (int i = 0; i < route.size(); i++) {
				 if (station_name.equalsIgnoreCase(route.get(i).getName())) { 
					 return true;  
				 } else {
					 continue; 
				 } 
		 } 	 
		 
		 return false; 
		 
	 }
	 
	 //Display information from a given station (string)
	 public static void moreInformation(ArrayList<CTARoute> routes, ArrayList<CTAStation> AllStops) {
		 
		 Scanner user_input = new Scanner (System.in); 
		 String station_name = ""; 
		 
		 while (true) {
				
				System.out.println("Enter Station Name: ");
				station_name = user_input.nextLine();
				
				try {
					if (stationCheck(AllStops, station_name)) 
						break; 
					
					else
						System.out.println("This station does not exist on any CTA Route"); 
					
				} catch (Exception e) {
					System.out.println("This station does not exist on any Route");
				}
			
			}
		 
		 ArrayList<CTAStation> station_appearances = new ArrayList<CTAStation>(); 
		 
		 //Iterates through each route in the CTATransit system to find matches, if a match exists, the 
		 //corresponding CTA Station Object is added to a list of appearances
		 for (int j = 0; j < routes.size(); j++) {
			 for (int i = 0; i < routes.get(j).getStops().size(); i++) {
				 if (station_name.equalsIgnoreCase(routes.get(j).getStops().get(i).getName())) { 
					 station_appearances.add(routes.get(j).getStops().get(i)); 
				 } else {
					 continue; 
				 } 
			 }
		 } 	 
		 
		//Prints out first Station Appearance
		System.out.println(" ");
		System.out.println(station_appearances.get(0).toString());
		 
		//Checks to see if next appearances are the same station, if not, prints them out as well
		 for (int i = 1; i < station_appearances.size(); i++) {
			 if (!(station_appearances.get(i).equals(station_appearances.get(i-1)))) {
				 System.out.println(station_appearances.get(i));
			 }
			 
			 else {
				 continue; 
			 }
		 }
		 
	 }
	 
	 //Finds nearest CTA Station given latitude and longitude inputs by user 
	 public static void findNearest(ArrayList<CTAStation> all_stops, ArrayList<CTARoute> routes) {    
		 	
		 	Scanner user_input = new Scanner(System.in); 
			
			double dbl_lat = 0;
			double dbl_lng = 0;
			
			while (true) {
				
				System.out.println("Enter Latitude: ");
				String latitude = user_input.nextLine();
				
				try {
					
					dbl_lat = Double.parseDouble(latitude);
					
					if (dbl_lat > -90 & dbl_lat < 90) 
						break; 
					
					else 
						System.out.println("Please enter a valid number between -90 and 90. "); 
					
				} catch (Exception e) {
					System.out.println("Please enter a valid number between -90 and 90. ");
				}
			  
			}	
			
			while (true) {
				
				System.out.println("Enter Longitude: ");
				String longitude = user_input.nextLine();
				
				try {
					
					dbl_lng = Double.parseDouble(longitude);
					
					if (dbl_lng > -180 & dbl_lng < 180) 
						break; 
					else 
						System.out.println("Please enter a valid number between -180 and 180. "); 
					
				} catch (Exception e) {
					System.out.println("Please enter a valid number between -180 and 180. ");
				}
			  
			}
			
			double min_distance = all_stops.get(0).calcDistance(dbl_lat, dbl_lng);
			int index = 0; 
		 	
		 	System.out.println("");
			System.out.println("Nearest Station");
			System.out.println("---------------");
		 
			for (int i = 1; i < all_stops.size(); i++) {    
				if (min_distance > all_stops.get(i).calcDistance(dbl_lat, dbl_lng)) {
					min_distance = all_stops.get(i).calcDistance(dbl_lat, dbl_lng);
					index = i; 
				}
				
				else {
				  continue; 
				}
			}
			
			String station_name = (all_stops.get(index).getName()); 
			System.out.print(station_name); 
			System.out.println("\n");
			System.out.println("This station exists on the following routes:");
			System.out.println(stationRoutes(routes, station_name));
			
	 }
	 
	 //Function that finds all the routes a given a station is on 
	 public static ArrayList<String> stationRoutes(ArrayList<CTARoute> routes, String station_name) {
		 
		 	ArrayList<String> relevant_stations = new ArrayList<String>();
			
			for (int j = 0; j < routes.size(); j++) {
				 for (int i = 0; i < routes.get(j).getStops().size(); i++) {
					 if (station_name.equalsIgnoreCase(routes.get(j).getStops().get(i).getName())) {
						 relevant_stations.add(routes.get(j).getName()); 
					 } else {
						 continue; 
					 }
					 
				 }	
			 }
			
			return relevant_stations; 
	 }
	 
	 public static String getStartingStation(ArrayList<CTAStation> AllStops) {
		 
		 Scanner user_input = new Scanner(System.in); 
		 
		 String starting_station = ""; 
		 
		 while (true) {
				
				System.out.println("Enter Station Name: ");
				starting_station = user_input.nextLine();
				
				try {
					if (stationCheck(AllStops, starting_station)) 
						return starting_station;  
					
					else
						System.out.println("This station does not exist on any CTA Route"); 
					
				} catch (Exception e) {
					System.out.println("This station does not exist on any Route");
				}
			
			}
		 
	 }
	 
	 public static String getEndingStation(ArrayList<CTAStation> AllStops) {
		 
		 Scanner user_input = new Scanner(System.in); 
		 
		 String ending_station = ""; 
		 
		 while (true) {
				
				System.out.println("Enter Station Name: ");
				ending_station = user_input.nextLine();
				
				try {
					if (stationCheck(AllStops, ending_station)) 
						return ending_station;  
					
					else
						System.out.println("This station does not exist on any CTA Route"); 
					
				} catch (Exception e) {
					System.out.println("This station does not exist on any Route");
				}
			
			}
		 
	 }
	 
	 //Finds the path between two given CTA Stations 
	 public static void findPath(ArrayList<CTARoute> routes, ArrayList<CTAStation> AllStops, String starting_station,
			 					String ending_station) {
		 
		 
		 ArrayList<String> starting_station_routes = stationRoutes(routes, starting_station); 
		 ArrayList<String> ending_station_routes = stationRoutes(routes, ending_station);
		 
		 CTARoute route1 = findCTARouteObject(starting_station_routes.get(0), routes); 
		 CTARoute route2 = findCTARouteObject(ending_station_routes.get(0), routes);
		 
		 String same_route = sameRouteCheck(starting_station_routes, ending_station_routes); 
		 
		 //Print out path if two stations on the same CTA Route
		 if (!(same_route.equalsIgnoreCase("none"))) {
			 
			 notransferPath(routes, same_route, starting_station, ending_station); 
			
		  } else if (intersectionCheck(route1, route2)) {

			 onetransferPath(route1, route2, starting_station, ending_station); 
			  
		 } else {
			  
			 twoTransferPath(routes, route1, route2, starting_station, ending_station); 
					  
		  } 
		 
		 System.out.println(" ");
		 
		 Scanner user_input = new Scanner(System.in);
		 
		 System.out.println("Would you like to save your path to an output file?");
		 String file_choice = user_input.nextLine();
			
			if (file_choice.charAt(0) == 'y') {
				
				try { 
					
					File file = new File("itinerary.txt");

					if (!file.exists()) {
						file.createNewFile();
					}
					
					//Prints array into file 
					PrintWriter pw = new PrintWriter( file );

					pw.println("CTA Itinerary");
					
					pw.println("from " + starting_station + " to " + ending_station);
					pw.println("---------------------------");
					
					pw.close();
					
					System.out.println("The file has been created!");
						
					} 
				
				//Exception handling 
				catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				} 
	}
	
	 
	 
	//Method that prints out path if the two stations of interest are on the same route  
	public static void notransferPath(ArrayList<CTARoute> routes, String same_route, String starting_station,
										String ending_station) {
		
		int route = 0; 
		//translates same route string to route object 
		 for (int i = 0; i < routes.size(); i++) {
			 if (same_route.equalsIgnoreCase(routes.get(i).getName())) {
				 route = i; 
			 } else {
				 continue; 
			 }	 
		  }
		 
		int starting_index = findIndex(routes.get(route).getStops(), starting_station); 
		int ending_index = findIndex(routes.get(route).getStops(), ending_station);
			
		System.out.println("CTA " + routes.get(route).getName() + " Route");
		System.out.println("from " + starting_station + " to " + ending_station);
		System.out.println("----------------");
		
		printPath(starting_index, ending_index, routes.get(route));
	
		
	}
	
	//Prints out path if the two stations of interest require one transfers between routes 
	public static void onetransferPath(CTARoute route1, CTARoute route2, String starting_station, String ending_station) {
		
		  CTAStation intersection_station = findIntersectionStation(route1, route2); 
		  
		  int starting_index = findIndex(route1.getStops(), starting_station); 
		  int ending_index = findIndex(route2.getStops(),ending_station);
		  int intersection_index_route1 = findIndex(route1.getStops(), intersection_station.getName()); 
		  int intersection_index_route2 = findIndex(route2.getStops(), intersection_station.getName());
		  
		  System.out.println(" ");
		  System.out.println("CTA Route");
		  System.out.println("from " + starting_station + " to " + ending_station);
		  System.out.println("----------------");
		  
		  System.out.println(" ");
		  System.out.println("-" + route1.getName() + "-");
		  printPath(starting_index, intersection_index_route1, route1); 
		  
		  System.out.println(" ");
		  System.out.println("transfer to " + route2.getName() + " at " + intersection_station.getName());
		  System.out.println(" ");
		  
		  System.out.println("-" + route2.getName() + "-");
		  printPath(intersection_index_route2, ending_index, route2); 
		  
		  System.out.println(" ");
		  System.out.println("Happy Trails!");
		
	}
	
	//Prints out path if the two stations of interest require two transfers between routes 
	public static void twoTransferPath(ArrayList<CTARoute> routes, CTARoute route1, CTARoute route2, String starting_station, String ending_station) {
		
		for (int i = 0; i < routes.size(); i++) {
			  if ((intersectionCheck(route1, routes.get(i))) & (intersectionCheck(route2, routes.get(i)))) {
				  
				  CTAStation intersection_station1 = findIntersectionStation(route1, routes.get(i));
				  CTAStation intersection_station2 = findIntersectionStation(route2, routes.get(i));
				  
				  int starting_index = findIndex(route1.getStops(), starting_station); 
				  int ending_index = findIndex(route2.getStops(),ending_station);
				  int intersection_index_route1 = findIndex(route1.getStops(), intersection_station1.getName()); 
				  int intersection_index1_connecting_route = findIndex(routes.get(i).getStops(), intersection_station1.getName()); 
				  int intersection_index2_connecting_route = findIndex(routes.get(i).getStops(), intersection_station2.getName()); 
				  int intersection_index_route2 = findIndex(route2.getStops(), intersection_station2.getName());
				  
				  System.out.println(" ");
				  System.out.println("CTA Route");
				  System.out.println("from " + starting_station + " to " + ending_station);
				  System.out.println("----------------");
				  
				  System.out.println(" ");
				  System.out.println("-" + route1.getName() + "-");
				  printPath(starting_index, intersection_index_route1, route1); 
				  
				  System.out.println(" ");
				  System.out.println("transfer to " + routes.get(i).getName() + " at " + intersection_station1.getName());
				  System.out.println(" ");
				  
				  System.out.println(" ");
				  System.out.println("-" + routes.get(i).getName() + "-");
				  printPath(intersection_index1_connecting_route, intersection_index2_connecting_route, routes.get(i));
				  
				  System.out.println(" ");
				  System.out.println("transfer to " + route2.getName() + " at " + intersection_station2.getName());
				  System.out.println(" ");
				  
				  System.out.println(" ");
				  System.out.println("-" + route2.getName() + "-");
				  printPath(intersection_index_route2, ending_index, route2);
				  
				  System.out.println(" ");
				  System.out.println("Happy Trails!");
				  
			  }
			  
		}
	}
	
	//Function to print out generated path between two starting and ending points 
	public static void printPath(int starting, int ending, CTARoute route) {
		
		if (starting > ending) {
			for (int j = starting; j >= ending; j--) {
				System.out.println(route.getStops().get(j).getName());
			}
		} else if (starting < ending) {
			for (int j = starting; j <= ending; j++) {
				System.out.println(route.getStops().get(j).getName());
			}
		}
		
	}
		 
	 
	//function that checks to see if a two different stations exist on one route 	    
	public static String sameRouteCheck(ArrayList<String> starting_station_routes, 
			ArrayList<String> ending_station_routes) {
		
		for (int i = 0; i < starting_station_routes.size(); i++) {
			 if (ending_station_routes.contains(starting_station_routes.get(i))) {
				 return starting_station_routes.get(i);  
			 } else {
				 continue; 
			 }	 
		 }	
		
		return "none"; 
	}
	
	//Returns the CTARoute object a given station is on 
	public static CTARoute findCTARouteObject(String given_station_route, ArrayList<CTARoute> routes) {
		
		CTARoute route_object = new CTARoute(); 
		
		for (int i = 0; i < routes.size(); i++) {
			 if (given_station_route.equalsIgnoreCase(routes.get(i).getName())) {
				 route_object = routes.get(i); 
			 } else {
				 continue; 
			 }	 
		  }
		
		return route_object; 
	}
	
	//Checks to see if the two different routes for the starting and ending station share a common station for transfers
	public static boolean intersectionCheck (CTARoute route1, CTARoute route2) {
		
		for (int i = 0; i < route1.getStops().size(); i++) {
			if (route2.getStops().contains(route1.getStops().get(i))) {
				return true;
			} else {
				continue; 
			}
		}
		
		return false; 
	}
	
	//Finds the route connecting two different routes 
	public static CTAStation findIntersectionStation(CTARoute route1, CTARoute route2) {
		
		CTAStation connection = new CTAStation(); 
		
		for (int i = 0; i < route1.getStops().size(); i++) {
			if (route2.getStops().contains(route1.getStops().get(i))) {
				return route1.getStops().get(i);
			} else {
				continue; 
			}
		}
		
		return connection; 
	}
	
	
	
}