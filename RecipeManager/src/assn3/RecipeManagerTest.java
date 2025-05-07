/**
 * File Name: RecipeManagerTest.java
 * Author: Simon Tan, 041161622
 * Course: CST8284 - Object Oriented Programming
 * Section: 331
 * Assignment: Assignment 3
 * Date: April 6th, 2025
 * Professor: Sandra Iroakazi
 * Class list: Recipe.java, RecipeManager.java, RecipeManagerTest.java
 * This program models a recipe managing application that loads bread recipes from
 * an external text file and provides a menu for the user to create a shopping list
 * based on the recipes. The user will be able to specify the type of bread and 
 * how many loaves of bread they would like to make.
 * This assignment has been completed for demo by: Simon Tan. 
 */
package assn3;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class serves as a driver class and contains method main + several other methods 
 * used to process the menu options in main.
 */
public class RecipeManagerTest {
	
	/**
	 * A Scanner object used to accept input from System.in for all methods in this class.
	 */
	private static Scanner input;
	
	/**
	 * A RecipeManager object shared between all methods in this class.
	 */
	private static RecipeManager recipeManager;
	
	/**
	 * An ArrayList of Recipes that will hold the Recipes parsed in RecipeManager. Shared
	 * between all methods in this class.
	 */
	private static ArrayList<Recipe> recipes;
	
	/**
	 * This method starts the program and loads the main menu for the user to
	 * interact with. It instantiates a RecipeManager object and calls the RecipeManager's
	 * loadRecipes() method to parse the external recipelist file. The getRecipes()
	 * of the RecipeManager is then used to get the ArrayList of Recipes that holds the
	 * parsed Recipes. The user will be able to select options for showing the available
	 * recipes, ordering/creating a shopping list, printing/saving a shopping list, and
	 * quitting the program.
	 * <p>
	 * This method catches several exceptions such as...
	 * <ul>
	 * <li>InputMismatchException - If the Scanner is unable to parse an int from user input
	 * <li>IllegalArgumentException - If the user selects an invalid option. Ex. Ordering a negative
	 * amount of bread
	 * <li>IllegalStateException - If the Scanner was somehow closed and could not read user input
	 * </ul>
	 * 
	 * @param args command line arguments are not used in this program.
	 */
	public static void main(String[] args) {
		input = new Scanner(System.in);
		recipeManager = new RecipeManager();
		recipeManager.loadRecipes();
		recipes = recipeManager.getRecipes();
		int choice = 0;
		String shouldContinue = "yes";
		System.out.println("Welcome to Simon's recipe manager.");
		while (shouldContinue.equals("yes")) {
			boolean badMenuChoice = true;
			System.out.printf("Please select one of the following options:%n"
							+ "1. Show available recipes.%n"
							+ "2. Create Shopping List.%n"
							+ "3. Print Shopping List.%n"
							+ "4. Quit Program.%n"
							+ "0. to reprint this menu.%n");
			while (badMenuChoice) {
				try {
					System.out.print("Please enter your choice: ");
					choice = input.nextInt();
					if (choice > 4 || choice < 0) {
						throw new IllegalArgumentException();
					}
					badMenuChoice = false;
				} 
				catch (InputMismatchException e) {
					System.out.println("Please only type digits.");
					System.out.println("Valid input are digits from 0 to 4.");
				} 
				catch (IllegalArgumentException e) {
					System.out.println("Valid input are digits from 0 to 4.");
				} 
				catch (IllegalStateException e) {
					System.err.printf("Unable to read input as Scanner is closed%n");
				}
				finally {
					input.nextLine();
				}
			}
			switch (choice) {
				case 1:
					displayRecipes();
					break;
				case 2:
					boolean badBreadChoice = true;
					boolean badQuantity = true;
					int breadChoice = 0;
					int quantity = 0;
					while (badBreadChoice) {
						try {
							System.out.print("Which bread would you like? ");
							breadChoice = input.nextInt() - 1;
							if (breadChoice > recipes.size() - 1 || breadChoice < 0) {
								throw new IllegalArgumentException();
							}
							badBreadChoice = false;
						} 
						catch (InputMismatchException e) {
							System.out.println("Please only type digits.");
							System.out.println("Valid input are digits from 1 to "  + recipes.size());
						} 
						catch (IllegalArgumentException e) {
							System.out.println("Valid input are digits from 1 to "  + recipes.size());
						} 
						catch (IllegalStateException e) {
							System.err.printf("Unable to read input as Scanner is closed%n");
						}
						finally {
							input.nextLine();
						}
					}
					while (badQuantity) {
						try {
							System.out.print("How much of this bread would you like? ");
							quantity = input.nextInt();
							order(breadChoice, quantity);
							badQuantity = false;
						} 
						catch (InputMismatchException e) {
							System.out.println("Please only type digits.");
						} 
						catch (IllegalArgumentException e) {
							System.err.printf("%s%n", e.getMessage());
						} 
						catch (IllegalStateException e) {
							System.err.printf("Unable to read input as Scanner is closed%n");
						}
						finally {
							input.nextLine();
						}
					}
					break;
				case 3:
					printShoppingList();
					break;
				case 4:
					shouldContinue = "no";
					break;
				case 0:
					break;
				default:
					break;
			}
		}
		input.close();
	}
	
	/**
	 * This method is used to display the recipes from the ArrayList of Recipes obtained from 
	 * RecipeManager. The method uses a ListIterator object to print the Recipe names.
	 * <p>
	 * The method catches a NoSuchElementException if the ListIterator's next() method returned
	 * a null value.
	 */
	private static void displayRecipes() {
		System.out.println("Available Recipes:");
		ListIterator<Recipe> iter = recipes.listIterator();
		try {
			while (iter.hasNext()) {
				System.out.printf("%d. %s%n", iter.nextIndex()+1, iter.next().getRecipeName());
			}
		} catch (NoSuchElementException e) {
			System.err.printf("There was an empty recipe in recipelist.txt");
		}
	}
	
	/**
	 * This method is used to order from a Recipe based on a given type and quantity. Uses
	 * the setters/getters from class Recipe.
	 * 
	 * @param breadChoice an int representing the index of the desired Recipe in the ArrayList
	 * @param quantity an int representing the quantity of bread the user wishes to order
	 * @throws IllegalArgumentException If the user tries to order a negative amount of bread
	 */
	private static void order(int breadChoice, int quantity) throws IllegalArgumentException{
		Recipe selectedBread = recipes.get(breadChoice);
		int currentAmountOrdered = selectedBread.getQuantityOrdered();
		if (currentAmountOrdered + quantity < 0) {
			throw new IllegalArgumentException("Cannot order a negative amount of " + selectedBread.getRecipeName()
												+ ". Current amount ordered: " + currentAmountOrdered);
		}
		selectedBread.setQuantityOrdered(currentAmountOrdered + quantity);
	}
	
	/**
	 * This method prints a shopping list based on the total types/quantity of bread ordered
	 * by the user. The method uses a ListIterator to go through the ArrayList of Recipes and
	 * gathers the total amount of ingredients required to make all the Recipes. A String 
	 * containing the shopping list is saved and printed for the user to see. The method also
	 * prompts the user to ask if they would like to save the list to an external file. If yes,
	 * the method passes the shoppinglist String to the RecipeManager's createShoppingList() 
	 * method.
	 * <p>
	 * This method catches a couple of exceptions...
	 * <ul>
	 * <li>NoSuchElementException - If the ListIterator returned a null value from the ArrayList
	 * <li>IllegalArgumentException - If the user did not type "Y"/"y" or "N"/"n" when asked if
	 * they wished to save the shoppinglist.
	 * </ul>
	 */
	private static void printShoppingList() {
		String breadTypes = "";
		float sugar = 0.0F;
		float eggs = 0.0F;
		float flour = 0.0F;
		float yeast = 0.0F;
		float butter = 0.0F;
		ListIterator<Recipe> iter = recipes.listIterator();
		try {
			while (iter.hasNext()) {
				Recipe bread = iter.next();
				int quantity = bread.getQuantityOrdered();
				if (quantity > 0) {
					breadTypes +=  quantity + " " + bread.getRecipeName() + " loaf/loaves%n";
					sugar = bread.getSugar() * quantity;
					eggs += bread.getEggs() * quantity;
					flour += bread.getFlour() * quantity;
					yeast += bread.getYeast() * quantity;
					butter += bread.getButter() * quantity;
				}
			}
		} catch (NoSuchElementException e) {
			System.err.printf("There was an error reading the list of ordered recipes.%n");
			e.printStackTrace();
		}
		String shoppingList = String.format("%s%nYou will need a total of:%n", breadTypes);
		if (yeast > 0) {
			shoppingList += (int) yeast + " grams of yeast%n";
		}
		if (flour > 0) {
			shoppingList += (int) flour + " grams of flour%n";
		}
		if (sugar > 0) {
			shoppingList += (int) sugar + " grams of sugar%n";
		}
		if (butter > 0) {
			shoppingList += (int) butter + " grams of butter%n";
		}
		if (eggs > 0) {
			shoppingList += (int) eggs + " egg(s)%n";
		}
		System.out.printf(shoppingList);
		boolean badPrintOption = true;
		while (badPrintOption) {
			try {
				System.out.println("Do you wish to save this list? (Y/N)");
				String decision = input.nextLine();
				if (decision.equalsIgnoreCase("Y")) {
					recipeManager.createShoppingList(shoppingList);
					badPrintOption = false;
				} else if (decision.equalsIgnoreCase("N")) {
					badPrintOption = false;
				} else {
					throw new IllegalArgumentException();
				}
			} catch (IllegalArgumentException e) {
				System.out.println("Please enter \"Y\" or \"N\".");
			}
		}
	}
}

