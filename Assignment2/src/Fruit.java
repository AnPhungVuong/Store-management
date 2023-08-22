/**
 * Student Name: Vuong Phung An
 * Student Number: 041016629
 * Assignment number: 1
 * @author An Vuong
 */

import java.util.Formatter;
import java.util.Scanner;

public class Fruit extends FoodItem{
	/**
	 * The name of the fruit orchard
	 */
	private String orchardName;
	
	/**
	 * Default Constructor
	 */
	Fruit() {
		
	}
	
	/**
	 * Displays the representation of a fruit
	 * @return string representation of the fruit.
	 */
	@Override
	public String toString() {
		String temp = super.toString();
		System.out.printf(" orchard supplier: %s%n", this.orchardName);
		return temp + " orchard supplier: " + this.orchardName;
	}
	
	/**
	 * A method to add information of a fruit item to inventory array
	 * @param scanner - The scanner object to allow the user to input information of an item
	 * @return 	true if successful
	 * 			false if not
	 */
	@Override
	public boolean addItem(Scanner scanner, boolean fromFile) {
		
		if (fromFile) {
			
			super.addItem(scanner, true);
			this.orchardName = scanner.nextLine();
			return true;
		}
		
		super.addItem(scanner, false);
		
		System.out.print("Enter the name of the orchard supplier: ");
		this.orchardName = scanner.nextLine();
		
		return true;
	}
	
	/**
	 * A method to print orchard name to file
	 * @param writer - use to print
	 */
	public void outputItem(Formatter writer) {
		writer.format("f\n");
		super.outputItem(writer);
		writer.format("%s\n", this.orchardName);
	}
	
}