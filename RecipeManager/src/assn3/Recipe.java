/**
 * File Name: Recipe.java
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

/**
 * This class models a recipe for a general type of bread. The class records the
 * name of the bread, the ingredients needed to make it, and the quantity that
 * the user would like to make. It is a simple class that only contains instance
 * variables and getters/setters.
 * 
 * @author Simon Tan
 * @version 1.0, 04/06/2025
 * @see assn3
 * @since JDK 21.0.4
 */
public class Recipe {
	
	/**
	 * An int that records the quantity of bread loaves ordered for the
	 * specific recipe. Default value of 0.
	 */
	private int quantityOrdered = 0;
	
	/**
	 * A String that records the name of the recipe. Default value of
	 * "UNKNOWN".
	 */
	private String recipeName = "UNKNOWN";
	
	/**
	 * A float that records the amount of sugar required to make one
	 * loaf of the bread recipe in grams. Default value of 0.0 grams.
	 */
	private float sugar = 0.0F;
	
	/**
	 * A float that records the amount of eggs required to make one
	 * loaf of the bread recipe. Default value of 0.0.
	 */
	private float eggs = 0.0F;
	
	/**
	 * A float that records the amount of flour required to make one
	 * loaf of the bread recipe in grams. Default value of 0.0 grams.
	 */
	private float flour = 0.0F;
	
	/**
	 * A float that records the amount of yeast required to make one
	 * loaf of the bread recipe in grams. Default value of 0.0 grams.
	 */
	private float yeast = 0.0F;
	
	/**
	 * A float that records the amount of butter required to make one
	 * loaf of the bread recipe in grams. Default value of 0.0 grams.
	 */
	private float butter = 0.0F;

	/**
	 * Returns the quantity of loaves ordered for this recipe.
	 * 
	 * @return an int representing the quantity of loaves ordered for this recipe.
	 */
	public int getQuantityOrdered() {
		return quantityOrdered;
	}

	/**
	 * Sets the quantity of loaves ordered for this recipe.
	 * 
	 * @param quantityOrdered an int representing the quantity of loaves ordered for this recipe.
	 */
	public void setQuantityOrdered(int quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}

	/**
	 * Gets the name of the bread recipe.
	 * 
	 * @return a String representing the name of the bread recipe.
	 */
	public String getRecipeName() {
		return recipeName;
	}

	/**
	 * Sets the name of the bread recipe.
	 * 
	 * @param recipeName a String representing the name of the bread recipe.
	 */
	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	/**
	 * Gets the amount of sugar required to make one loaf of this bread in grams.
	 * 
	 * @return a float representing the amount of sugar required to make one loaf of this bread in grams.
	 */
	public float getSugar() {
		return sugar;
	}

	/**
	 * Sets the amount of sugar required to make one loaf of this bread in grams.
	 * 
	 * @param sugar a float representing the amount of sugar required to make one loaf of this bread in grams.
	 */
	public void setSugar(float sugar) {
		this.sugar = sugar;
	}

	/**
	 * Gets the amount of eggs required to make one loaf of this bread.
	 * 
	 * @return a float representing the amount of eggs required to make one loaf of this bread.
	 */
	public float getEggs() {
		return eggs;
	}

	/**
	 * Sets the amount of eggs required to make one loaf of this bread.
	 * 
	 * @param eggs a float representing the amount of eggs required to make one loaf of this bread.
	 */
	public void setEggs(float eggs) {
		this.eggs = eggs;
	}

	/**
	 * Gets the amount of flour required to make one loaf of this bread in grams.
	 * 
	 * @return a float representing the amount of flour required to make one loaf of this bread in grams.
	 */
	public float getFlour() {
		return flour;
	}

	/**
	 * Sets the amount of flour required to make one loaf of this bread in grams.
	 * 
	 * @param flour a float representing the amount of flour required to make one loaf of this bread in grams.
	 */
	public void setFlour(float flour) {
		this.flour = flour;
	}

	/**
	 * Gets the amount of yeast required to make one loaf of this bread in grams.
	 * 
	 * @return a float representing the amount of yeast required to make one loaf of this bread in grams.
	 */
	public float getYeast() {
		return yeast;
	}

	/**
	 * Sets the amount of yeast required to make one loaf of this bread in grams.
	 * 
	 * @param yeast a float representing the amount of yeast required to make one loaf of this bread in grams.
	 */
	public void setYeast(float yeast) {
		this.yeast = yeast;
	}

	/**
	 * Gets the amount of butter required to make one loaf of this bread in grams.
	 * 
	 * @return a float representing the amount of butter required to make one loaf of this bread in grams.
	 */
	public float getButter() {
		return butter;
	}

	/**
	 * Sets the amount of butter required to make one loaf of this bread in grams.
	 * 
	 * @param butter a float representing the amount of butter required to make one loaf of this bread in grams.
	 */
	public void setButter(float butter) {
		this.butter = butter;
	}

	
}
