/**
 * Student Name: Vuong Phung An
 * Student Number: 041016629
 * Assignment number: 2
 * @author An Vuong
 */


import java.util.InputMismatchException;
import java.util.Scanner;

public class Assign2 {

	/**
	 * @param args - Array of Strings passed as parameters when running through command line
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		displayMenu();
	}
	
	/**
	 * Method to display the menu for the user to choose
	 */
	public static void displayMenu() {
		/*Option for the user to choose*/ 
		int option = 0;
		
		/*The scanner object to allow the user to input information of an item*/
		Scanner scanner = new Scanner(System.in);
		
		/*Initialize an Inventory object with the default constructor*/
		Inventory inventory = new Inventory();

		while (option != 8) {
			try {
				//Menu options
				System.out.println("Please select one of the following: ");
				System.out.println("1: Add Item to Inventory ");
				System.out.println("2: Display Current Inventory");
				System.out.println("3: Buy Item(s)");
				System.out.println("4: Sell Item(s)");
				System.out.println("5: Search for Item");
				System.out.println("6: Save Inventory to File");
				System.out.println("7: Read Inventory from File");
				System.out.println("8: To Exit");
				System.out.print("> ");
				option = scanner.nextInt();
				
				switch(option) {
					//Add an item to the inventory
					case 1:
						inventory.addItem(scanner, false);
						break;
					//Display the inventory
					case 2:
						inventory.toString();
						break;
					//Buy items 
					case 3:
						inventory.updateQuantity(scanner, true);
						break;
					//Sell items 
					case 4:
						inventory.updateQuantity(scanner, false);
						break;
					case 5:
						inventory.searchForItem(scanner);
						break;
					case 6:
						inventory.saveToFile(scanner);
						break;
					case 7:
						inventory.readFromFile(scanner);
						break;
						
					//Exit 
					case 8:
						System.out.println("Exitting...");
						break;
					//for Invalid options
					default:
						System.err.println("Incorrect value entered");
						break;
				}
				System.out.println();
			}
			catch(InputMismatchException e) {
				System.err.println("Incorrect value entered");
				
				//prevent infinite loop
				scanner.next();
			}
		}
		scanner.close();
	}

}
