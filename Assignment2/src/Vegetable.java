
/**
 * Student Name: Vuong Phung An
 * Student Number: 041016629
 * Assignment number: 1
 * @author An Vuong
 */

import java.util.Formatter;
import java.util.Scanner;

public class Vegetable extends FoodItem {
	/**
	 * The name of farm
	 */
	private String farmName;

	/**
	 * Default Constructor
	 */
	Vegetable() {

	}

	/**
	 * Displays the representation of vegetable
	 * 
	 * @return string representation of the vegetable
	 */
	@Override
	public String toString() {
		String temp = super.toString();
		System.out.printf(" farm supplier: %s%n", this.farmName);
		return temp + " farm supplier: " + this.farmName;
	}

	/**
	 * A method to add information of a vegetable item to inventory array
	 * 
	 * @param scanner -The scanner object to allow the user to input information of
	 *                an item
	 * @return true if successful false if not
	 */
	@Override
	public boolean addItem(Scanner scanner, boolean fromFile) {

		if (fromFile) {
			super.addItem(scanner, true);
			this.farmName = scanner.nextLine();
			return true;
		}

		super.addItem(scanner, false);

		System.out.print("Enter the name of the farm supplier: ");
		this.farmName = scanner.nextLine();

		return true;
	}

	/**
	 * A method to print farm name to file
	 * @param writer - use to print
	 */
	public void outputItem(Formatter writer) {
		writer.format("v\n");
		super.outputItem(writer);
		writer.format("%s\n", this.farmName);

	}
}
