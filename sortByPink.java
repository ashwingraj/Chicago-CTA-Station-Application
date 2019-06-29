/* Ashwin G. Raj 
 * 
 * CS 201: Accelerated Introduction to Computer Science
 * MW 5:00 PM - 6:15 PM 
 * Lab F 5:00 PM
 * 
 * Class created to sort List of Pink Line Stops using comparator 
 * 
 *
 */


package ashwinraj_courseproject;

import java.util.Comparator;

public class sortByPink implements Comparator<CTAStation> {
	
	public int compare(CTAStation a, CTAStation b) {
		
		return a.getPinkLine() - b.getPinkLine(); 
	}

} 

