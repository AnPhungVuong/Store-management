
/**
 * Student Name: Vuong Phung An
 * Student Number: 041016629
 * Assignment number: 2
 * @author An Vuong
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Inventory {
	/**
	 * An inventory array to store items
	 */
	private ArrayList<FoodItem> inventory;

	/**
	 * The number of current items in the array
	 */
	private int numItems;

	/**
	 * Default Constructor
	 */
	Inventory() {
		this.inventory = new ArrayList<FoodItem>(20);
		this.numItems = 0;
	}

	/**
	 * Displays the representation of the inventory.
	 * 
	 * @return string representation of the inventory.
	 */
	public String toString() {
		String temp = "Inventory: \n";
		System.out.println("Inventory: ");
		for (int i = 0; i < this.numItems; i++) {
			temp += this.inventory.get(i).toString() + "\n";
		}

		return temp;
	}

	/**
	 * A method to check if an item code exists in the array or not
	 * 
	 * @param item - The item that for checking
	 * @return the index location of the item if found -1 if the item is not found
	 *         in the array.
	 */
	public int alreadyExists(FoodItem item) {
		for (int i = 0; i < this.numItems; i++) {
			if (inventory.get(i).isEqual(item)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * A method to add an item to the inventory
	 * 
	 * @param scanner - The scanner object to ask the user for the input information
	 * @return true if successful false if not
	 */
	public boolean addItem(Scanner scanner, boolean fromFile) {

		if (fromFile) {

			char type = scanner.next().charAt(0);

			switch (type) {
			case 'f':
				inventory.add(numItems, new Fruit());
				break;
			case 'v':
				inventory.add(numItems, new Vegetable());
				break;
			case 'p':
				inventory.add(numItems, new Preserve());
				break;
			default:
				System.out.println("Invalid option");
				break;
			}

			// Prompt user to input the item code
			inventory.get(numItems).inputCode(scanner, true);

			// Checking if the item exists in the inventory, if no, add the item to the
			// inventory
			if (this.alreadyExists(inventory.get(numItems)) >= 0) {
				System.out.println("Item code already exists");
				System.out.println("Error encountered while reading the file, aborting...");
				return false;
			} else {
				this.inventory.get(numItems++).addItem(scanner, true);
				Collections.sort(this.inventory);
			}
			;
			
			return true;

		}
		char userChoose = '-';

		// Asking for the type
		do {
			try {
				System.out.print("Do you wish to add a fruit(f), vegetable(v) or a preserve(p)? ");
				userChoose = scanner.next().charAt(0);
				if (userChoose != 'f' && userChoose != 'v' && userChoose != 'p') {
					System.out.println("Invalid entry");
				}

			} catch (InputMismatchException e) {
				System.out.println("Invalid entry");
				scanner.next();
			}
		} while (userChoose != 'f' && userChoose != 'v' && userChoose != 'p');

		// Polymorphism based on the user's choose
		switch (userChoose) {
		case 'f':
			inventory.add(numItems, new Fruit());
			break;
		case 'v':
			inventory.add(numItems, new Vegetable());
			break;
		case 'p':
			inventory.add(numItems, new Preserve());
			break;
		default:
			System.out.println("Invalid option");
		}

		// Prompt user to input the item code
		inventory.get(numItems).inputCode(scanner, false);

		// Checking if the item exists in the inventory, if no, add the item to the
		// inventory
		if (this.alreadyExists(inventory.get(numItems)) >= 0) {
			System.out.println("Item code already exists");
			return false;
		} else {
			this.inventory.get(numItems++).addItem(scanner, false);
		}
		;

		Collections.sort(this.inventory);
		return true;
	}

	/**
	 * A method to buy or sell an item.
	 * 
	 * @param scanner   - The scanner object to ask the user for the input
	 *                  information
	 * @param buyOrSell - A boolean value return true if the user buys items, false
	 *                  if the user sell items
	 * @return true if successful false if not
	 */
	public boolean updateQuantity(Scanner scanner, boolean buyOrSell) {
		// Checking if the inventory is empty
		if (this.numItems == 0) {
			System.out.println(buyOrSell ? "Error...could not buy item" : "Error...could not sell item");
			return false;
		}
		// Prompt the user to enter the code of item that user wants to buy or sell
		FoodItem exampleItem = new FoodItem();
		exampleItem.inputCode(scanner, false);

		// Find the location of the item
		int locationOfItemCode = this.alreadyExists(exampleItem);

		// Buying
		if (buyOrSell == true) {

			// Checking the item is not found in inventory
			if (locationOfItemCode == -1) {
				System.out.println("Could not found in inventory...");
				System.out.println("Error... Could not buy item");
				return false;
				// If it is found, continue
			} else {

				int quantity = -1;

				boolean valid = false;
				// Checking if quantity is valid
				do {
					try {
						// Prompt the user to enter the quantity
						System.out.print("Enter valid quantity to buy: ");
						quantity = scanner.nextInt();
						if (quantity < 0) {
							System.out.println("Invalid quantity...");
							valid = false;
						}
						valid = true;

					} catch (InputMismatchException e) {
						System.out.println("Invalid quantity...");
						scanner.next();
					}
				} while (!valid || quantity < 0);

				this.inventory.get(locationOfItemCode).updateItem(quantity);
				return true;
			}
			// Selling
		} else {

			if (locationOfItemCode == -1) {
				System.out.println("Could not found in inventory...");
				System.out.println("Error... Could not sell item");
				return false;
			} else {

				int quantity = -1;

				boolean valid = false;
				// Checking if quantity is valid
				do {
					try {
						// Asking the user for the quantity
						System.out.print("Enter valid quantity to sell: ");
						quantity = scanner.nextInt();
						if (quantity < 0) {
							System.out.println("Invalid quantity...");
							valid = false;
						}
						valid = true;

					} catch (InputMismatchException e) {
						System.out.println("Invalid quantity...");
						scanner.next();
					}
				} while (!valid || quantity < 0);

				// Checking if the item sales in stock is sufficient or not
				if (this.inventory.get(locationOfItemCode).updateItem(quantity * (-1)) == false) {
					System.out.println("Insufficient stock in inventory...");
					System.out.println("Error...could not sell item");
					return false;
				} else {
					return true;
				}
			}
		}
	}
	
	/**
	 * A method to call binarysearch
	 * 
	 * @param scanner   - The scanner object to ask the user for the input
	 *                  information 
	 */
	public void searchForItem(Scanner scanner) {
		System.out.print("Enter the code for the item: ");
		int tempCode = scanner.nextInt();

		binarySearch(0, this.numItems - 1, tempCode);

		// condition to end
		// condition to end

	}

	/**
	 * A method to search for item code
	 * 
	 * @param start - start index of array searching
	 * @param end - end index of array searching
	 * @param key - key to search
	 */
	public void binarySearch(int start, int end, int key) {
		if (start > end) {
			System.out.println("Code not found in inventory...");
			return;
		}

		// formula to find the middle index
		int middle = (start + end + 1) / 2;

		// if case find the key, return the middle index aka the index of the key
		if (this.inventory.get(middle).getItemCode() == key) {
			this.inventory.get(middle).toString();
		} else if (this.inventory.get(middle).getItemCode() > key) {
			binarySearch(start, middle - 1, key);
		}

		else {
			binarySearch(start + 1, end, key);
		}
	}

	/**
	 * A method to save information to file.txt
	 * 
	 * @param scanner   - The scanner object to ask the user for the input
	 *                  information 
	 */
	public void saveToFile(Scanner scanner) {
		System.out.print("Please enter a filename to save to: ");
		String fileName = scanner.next(); 
		Formatter writer = null;
		
		try {
			writer = new Formatter("src/" + fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File Not Found, ignoring...");
			return;
		}
		
		for (int i = 0; i < this.numItems; i++) {
			this.inventory.get(i).outputItem(writer);
		}
		
		writer.close();
	}
	
	/**
	 * A method to read information to file.txt
	 * 
	 * @param scanner   - The scanner object to ask the user for the input
	 *                  information 
	 */
	public void readFromFile(Scanner scanner) {
		System.out.print("Please enter a filename to read from: ");
		String fileName = scanner.next();
		File file = new File("src/" + fileName);
		Scanner fileReader = null;
		try {
			fileReader = new Scanner(file);
			while (fileReader.hasNext()) {
				if (this.addItem(fileReader, true) == false) {
					return;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File Not Found, ignoring...");

		}

	}
	
}
