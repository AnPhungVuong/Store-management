/**
 * Student Name: Vuong Phung An
 * Student Number: 041016629
 * Assignment number: 2
 * @author An Vuong
 */


import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;


public class FoodItem implements Comparable<FoodItem>{
	/**
	 * Code of item
	 */
	protected int itemCode;
	
	/**
	 * Name of item
	 */
	protected String itemName;
	
	/**
	 * Price of item
	 */
	protected float itemPrice;
	
	/**
	 * Quantity in stock of item
	 */
	protected int itemQuantityInStock;
	
	/**
	 * Cost of item
	 */
	protected float itemCost;
	
	/**
	 * Default Constructor
	 */
	FoodItem() {
		
	}
	
	/**
	 * Displays the representation of an item
	 * @return string representation of the item.
	 */
	@Override
	public String toString() {
		System.out.printf("Item: %d %s %d price: $%.2f cost: $%.2f", this.itemCode, this.itemName, this.itemQuantityInStock, this.itemPrice, this.itemCost);
		return "Item: " + this.itemCode + " " + this.itemName + " " + this.itemQuantityInStock + " price: $" 
				+ this.itemPrice + " cost: $" + this.itemCost; 
	}
	
	/**
	 * A method to buy or sell an item.
	 * @param amount - The amount to update the item
	 * @return 	true if successfully updated
	 * 		    false if not
	 */
	public boolean updateItem(int amount) {
		//Checking if the item can update
		if (this.itemQuantityInStock + amount >= 0) {
			this.itemQuantityInStock += amount;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * A method to compare item codes.
	 * @param item - The item that is compared to this item
	 * @return 	true if the item codes are equal
	 * 			or false if not
	 */
	public boolean isEqual(FoodItem item) {
		return this.itemCode == item.itemCode;
	}
	
	/**
	 * A method to add an item to the inventory
	 * @param scanner - The scanner object to allow the user to input information of an item
	 * @return 	true if the method is executed successfully
	 * 			or false if the method is failed
	 */
	public boolean addItem(Scanner scanner, boolean fromFile) {
		
		if (fromFile) {
			try {
				//Asking for the user input
				this.itemName = scanner.nextLine();
				this.itemQuantityInStock = scanner.nextInt();
				this.itemCost = scanner.nextFloat();
				this.itemPrice = scanner.nextFloat();
				scanner.nextLine();
				return true;
			
			} catch (InputMismatchException e) {
				System.out.println("Invalid entry");
				scanner.next();
				return false;
			}
			
		}
		
		System.out.print("Enter the name of the item: ");
		this.itemName = scanner.nextLine();
		
		//Checking if quantity is valid or not
		boolean valid = false;
		do {
			try {
				//Asking for the user input
				System.out.print("Enter the quantity of the item: ");
				this.itemQuantityInStock = scanner.nextInt();
				valid = true;
				if (this.itemQuantityInStock < 0) {
					System.out.println("Invalid entry");
					valid = false;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid entry");
				scanner.next();
			}
		} while (!valid || this.itemQuantityInStock < 0);
		
		//Checking if cost is valid
		valid = false;
		do {
			try {
				//Prompt for the user input
				System.out.print("Enter the cost of the item: ");
				this.itemCost = scanner.nextFloat();
				valid = true;
				if (this.itemCost < 0) {
					System.out.println("Invalid entry");
					valid = false;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid entry");
				scanner.next();
			}
		} while (!valid || this.itemCost < 0);
		
		//Checking if the sales price is valid
		valid = false;
		do {
			try {
				//Asking for the user input
				System.out.print("Enter the sales price of the item: ");
				this.itemPrice = scanner.nextFloat();
				scanner.nextLine();
				valid = true;
				if (this.itemPrice < 0) {
					System.out.println("Invalid entry");
					valid = false;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid entry");
				scanner.next();
			}
		} while (!valid || this.itemPrice < 0);
		
		
		return true;
	}
	
	/**
	 * A method to ask the user for an input code of an item
	 * @param scanner - The scanner object to allow the user to input information of an item
	 * @return 	true if successful
	 * 			false if not
	 */
	public boolean inputCode(Scanner scanner, boolean fromFile) {
		
		
		if (fromFile) {
			try {
				//Prompt for the user input

				this.itemCode = scanner.nextInt();
				scanner.nextLine();
				return true;
				
			} catch (InputMismatchException e) {
				System.out.println("Invalid entry");
				scanner.next();
				return false;
			}
		}
		
		boolean valid = false;
		//Checking if the input code is valid
		do {
			try {
				//Prompt for the user input
				System.out.print("Enter the code of the item: ");
				this.itemCode = scanner.nextInt();
				scanner.nextLine();
				valid = true;
				
			} catch (InputMismatchException e) {
				System.out.println("Invalid entry");
				scanner.next();
			}
			
		} while (!valid);
		
		
		return true;
	}
	
	
	/**
	 * A method to get item code
	 * @return 	item code
	 */
	public int getItemCode() {
		return this.itemCode;
	}
	
	/**
	 * A method to print to file
	 * @param writer - use to print
	 */
	public void outputItem(Formatter writer) {
		writer.format("%d\n%s\n%d\n%.2f\n%.2f\n", this.itemCode, this.itemName, this.itemQuantityInStock, this.itemCost, this.itemPrice);
	}

	
	/**
	 * A method is used for the collection sorting
	 * @param o -  other food item to compare to 
	 * @return a value that will determine the sorting order
	 */
	@Override
	public int compareTo(FoodItem o) {
		// TODO Auto-generated method stub
		return this.itemCode - o.itemCode;
	}
}