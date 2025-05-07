/**
 * File Name: RecipeManager.java
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

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.IllegalFormatException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class manages the input of recipes from an external file and outputs
 * a shopping list file based on information received from RecipeManagerTest.
 * The class records the recipes read from the external text file and stores
 * them for use by RecipeManagerTest.
 * 
 * @author Simon Tan
 * @version 1.0, 04/06/2025
 * @see assn3
 * @since JDK 21.0.4
 */
public class RecipeManager {
	
	/**
	 * An ArrayList of type Recipe that records all the recipes parsed from the
	 * external recipelist file. 
	 */
	private ArrayList<Recipe> recipes = new ArrayList<>();
	
	/**
	 * Gets the ArrayList of Recipes recorded in the external recipelist file.
	 * 
	 * @return an ArrayList of Recipes recorded in the external recipelist file.
	 */
	public ArrayList<Recipe> getRecipes() {
		return recipes;
	}
	
	/**
	 * This method parses the external recipelist file called recipelist.txt that is
	 * located in the same folder that the program is running from. A relative path of
	 * "recipelist.txt" is passed to the Paths.get() method. The Path object is then 
	 * passed to a Scanner object that is used to parse the file. The Scanner's 
	 * hasNextLine() is used to determine when the end of the file is reached. The Scanner
	 * then reads input from the file based on a pre-determined format (see assignment 
	 * requirements). A temporary recipe object is used to hold all the data for one 
	 * Recipe before storing it in the RecipeManager's ArrayList.
	 * <p>
	 * This method catches several exceptions...
	 * <ul>
	 * <li> InvalidPathException - If an invalid pathname format is used. Ex. "////pathname"
	 * <li> IOException - If the Scanner is unable to open the source using the Path object
	 * <li> InputMismatchException - If the file does not follow the pre-formatted content rules
	 * <li> IllegalArgumentException - If the file does not follow the pre-formatted content rules
	 * <li> IllegalStateException - If the Scanner has been closed before reading all values in the file
	 * </ul>
	 */
	public void loadRecipes() {
		try (Scanner fileReader = new Scanner(Paths.get("recipelist.txt"))) {
			while (fileReader.hasNextLine()) {
				String recipeHeader = fileReader.next();
				if (recipeHeader.equals("Recipe")) {
					Recipe tempRecipe = new Recipe();
					tempRecipe.setRecipeName(fileReader.nextLine().trim());
					for (int i = 0; i < 5; i++) {
						String lineStart = fileReader.next();
						if (lineStart.equalsIgnoreCase("sugar")) {
							tempRecipe.setSugar(fileReader.nextFloat());
							fileReader.nextLine();
						} else if (lineStart.equalsIgnoreCase("eggs")) {
							tempRecipe.setEggs(fileReader.nextFloat());
							fileReader.nextLine();
						} else if (lineStart.equalsIgnoreCase("flour")) {
							tempRecipe.setFlour(fileReader.nextFloat());
							fileReader.nextLine();
						} else if (lineStart.equalsIgnoreCase("yeast")) {
							tempRecipe.setYeast(fileReader.nextFloat());
							fileReader.nextLine();
						} else if (lineStart.equalsIgnoreCase("butter")) {
							tempRecipe.setButter(fileReader.nextFloat());
							fileReader.nextLine();
						}
					}
					recipes.add(tempRecipe);
				}
				else if (!recipeHeader.isBlank()){
					throw new IllegalArgumentException();
				}
			}
		}
		catch (InvalidPathException e) {
			System.err.printf("Invalid pathname format used. Please exit and try again.%n");
			e.printStackTrace();
		}
		catch (IOException e) {
			System.err.println("RecipeManager: Unable to read file retrieved from \"" + e.getMessage() + "\"");
		}
		catch (InputMismatchException | IllegalArgumentException e) {
			System.err.printf("Invalid/corrupted file contents in \"recipelist.txt\". Please exit and re-download.%n");
		}
		catch (IllegalStateException e) {
			System.err.printf("Scanner is closed before reading the contents of \"recipelist.txt\"%n");
		}
	}
	
	/**
	 * This method creates a "shoppinglist.txt" file based on a String received in its parameter.
	 * The method uses a Formatter object to write the text into the file. 
	 * <p>
	 * This method catches several exceptions...
	 * <ul>
	 * <li> IllegalFormatException - If the String passed in the parameter contains illegal syntax
	 * <li> FormatterClosedException - If the Formatter object was closed before being able to write to shoppinglist.txt
	 * <li> SecurityException - If the Formatter does not have permission to write to the file
	 * <li> FileNotFoundException - If the filename is not a writable regular file or a file of that name
	 * cannot be created
	 * </ul>
	 * 
	 * @param shoppingList a formatted String that contains the quantities of bread ordered 
	 * and total ingredients needed.
	 */
	public void createShoppingList(String shoppingList) {
		try (Formatter output = new Formatter("shoppinglist.txt")) {
			output.format(shoppingList);
		} 
		catch (IllegalFormatException e) {
			System.err.printf("String passed to createShoppingList() contains an illegal syntax.");
			e.printStackTrace();
		}
		catch (FormatterClosedException e) {
			System.err.printf("Formatter closed before being able to write to file \"shoppinglist.txt\".");
			e.printStackTrace();
		} 
		catch (SecurityException e) {
			System.err.printf("You do not have permission to write to \"shoppinglist.txt\".");
			e.printStackTrace();
		} 
		catch (FileNotFoundException e) {
			System.err.printf("The file \"shoppinglist.txt\" cannot be created in the active directory");
			e.printStackTrace();
		}
	}
}
