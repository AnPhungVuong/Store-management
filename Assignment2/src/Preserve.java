/**
 * Student Name: Vuong Phung An
 * Student Number: 041016629
 * Assignment number: 2
 * @author An Vuong
 */

import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Preserve extends FoodItem{
	/**
	 * The size of the jar
	 */
	private int jarSize;
	
	/**
	 * Default Constructor
	 */
	Preserve() {
		
	}
	
	/**
	 * Displays the representation of a preserve
	 * @return string representation of the preserve.
	 */
	@Override
	public String toString() {
		String temp = super.toString();
		System.out.printf(" size: %dml%n", this.jarSize);
		return temp + " size: " + this.jarSize + "ml";
		
	}
	
	/**
	 * A method to add information of preserve item to inventory array
	 * @param scanner - The scanner object to ask the user for the input information
	 * @return 	true if successful
	 * 			false if not
	 */
	@Override
	public boolean addItem(Scanner scanner, boolean fromFile) {
		
		if (fromFile) {
			try {
				//Asking the user for the input
				super.addItem(scanner, true);
				this.jarSize = scanner.nextInt();
				scanner.nextLine();
				return true;
	
			} catch (InputMismatchException e) {
				System.out.println("Invalid entry");
				scanner.next();
				return false;
			}
		}
		super.addItem(scanner, false);
		
		boolean valid = false;
		//Checking if the user input is valid.
		do {
			try {
				//Asking the user for the input
				System.out.print("Enter the sizse of the jar in millilitres: ");
				this.jarSize = scanner.nextInt();
				scanner.nextLine();
				valid = true;
			} catch (InputMismatchException e) {
				System.out.println("Invalid entry");
				scanner.next();
			}
		} while (!valid || this.jarSize < 0);
		
		
		return true;
	}
	
	/**
	 * A method to print jar size to file
	 * @param writer - use to print
	 */
	public void outputItem(Formatter writer) {
		writer.format("p\n");
		super.outputItem(writer);
		writer.format("%d\n", this.jarSize);
	}
}
