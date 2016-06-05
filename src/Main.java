/*
 * BurgerBaron
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Main class.
 *
 * @author Tzu-Chien Lu tclu82@uw.edu
 * @version Winter 2016
 */
public class Main {
	
	// Input file 
	private static final String INPUT = "customer.txt";
	
	// Prefix words
	private static final String PROCESS_ORDER = "Processing Order ";
	
	// For burger order
	private int myCount;
	
	/**
	 * Constructor.
	 */
	public Main() {
		myCount = 0;
	}
	
	/**
	 * Parses a line of input from the file and outputs the burger.
	 * 
	 * @param theLine input line
	 */
	public void parseLine(String theLine) {
		List<String> list = new ArrayList<String>();
		List<String> addList = new ArrayList<String>();
		List<String> removeList = new ArrayList<String>();
		
		// http://goo.gl/r2npds
		// http://goo.gl/0Dqc3g
		list = Arrays.asList(theLine.split(" "));
		
		for (int i = 0; i <list.size(); i++) {

			if (list.get(i).length() == 11) list.set(i, "BaronSauce");				
		}
		
		// Baron or regular burger.
		int indexBaron = list.indexOf("Baron");
		boolean work;
		
		// Regular
		if (indexBaron != -1) work = true;
		
		// Baron Burger
		else work = false;
		
		Burger b = new Burger(work);
		
		// How many layer do you want?
		int indexDouble = list.indexOf("Double");
		int indexTriple = list.indexOf("Triple");
		
		if (indexDouble != -1) b.addPatty();
		
		else if (indexTriple != -1) {
			b.addPatty();
			b.addPatty();
		}
		
		// What patties do you want?	
		int indexChicken = list.indexOf("Chicken");
		int indexVeggie = list.indexOf("Veggie");
		
		if (indexChicken != -1) b.changePatties("Chicken");
		
		else if (indexVeggie != -1) b.changePatties("Veggie");
		
		else b.changePatties("Beef");		
		
		preferenceCheck(list, addList, removeList);
		
		int indexWith = list.indexOf("with");
		int indexNo = list.indexOf("no");

		if (indexNo == indexWith + 1) {
			
			// removeList
			for (int i = 0; i < removeList.size(); i++) {
				String s = removeList.get(i);
				
				// Category
				if (s.equals("Cheese") || s.equals("Veggies") || s.equals("Sauce")) {
					b.removeCategory(s);
				}
				
				// Ingredient
				else b.removeIngredient(s);		
			}
			
			// addList
			for (int i = 0; i < addList.size(); i++) {
				String s = addList.get(i);
				
				// Category
				if (s.equals("Cheese") || s.equals("Sauce") || s.equals("Veggies")) {			
					b.addCategory(s);
				}
			
				// Ingredient
				else if (!s.equals("Single") && !s.equals("Double") && !s.equals("Triple") 
					  && !s.equals("Baron") && !s.equals("Burger") && !s.equals("Beef")
					  && !s.equals("Chicken") && !s.equals("Veggie")) {
					
					b.addIngredient(s);	
				}		
			}
			
		} else if (indexWith != -1) {
			
			// addList
			for (int i = 0; i < addList.size(); i++) {
				String s = addList.get(i);
				
				// Category
				if (s.equals("Cheese") || s.equals("Sauce") || s.equals("Veggies")) {			
					b.addCategory(s);
				}
			
				// Ingredient
				else {
					b.addIngredient(s);	
				}		
			}
			
			// removeList
			for (int i = 0; i < removeList.size(); i++) {
				String s = removeList.get(i);
				
				// Category
				if (s.equals("Cheese") || s.equals("Veggies") || s.equals("Sauce")) {
					b.removeCategory(s);
				}
				
				// Ingredient
				else b.removeIngredient(s);	
			}
		}
		System.out.println(b.toString() + "\n");
	}	
	
	/**
	 * Helper method for parseLine.
	 * 
	 * @param list contain all elements
	 * @param addList elements to add
	 * @param removeList  elements to remove
	 */
	private void preferenceCheck(List<String> list, 
							List<String> addList,
							List<String> removeList) {
		
		// Condition
		int indexWith = list.indexOf("with");
		int indexBut = list.indexOf("but");
		int indexNo = list.indexOf("no");
		
		// with no, but
		if (indexNo == indexWith + 1) {
			
			// with no, but
			if (indexBut != -1) {
				
				for (int i = indexNo + 1; i < indexBut; i++) {
					removeList.add(list.get(i));
				}
	
				for (int i = indexBut + 1; i < list.size(); i++) {
					addList.add(list.get(i));
				}
			
			// with no
			} else {
				
				for (int i = indexNo + 1; i < list.size(); i++) {
					removeList.add(list.get(i));
				}	
			}
		}
		
		// with, but no
		else if (indexWith != -1) {
			
			// with
			if (indexBut == -1 && indexNo == -1) {
				
				for (int i = indexWith + 1; i < list.size(); i++) {
					addList.add(list.get(i));
				}
			}
			
			// with, but no
			else {
				
				if (indexNo == indexBut + 1) {
					
					for (int i = indexWith + 1; i < indexBut; i++) {
						addList.add(list.get(i));
					}
					
					for (int i = indexNo + 1; i < list.size(); i++) {
						removeList.add(list.get(i));
					}
				}
			}
		}
	}
	
	/**
	 * Main method
	 * 
	 * @param theArgs theArgs
	 */
	public static void main(String[] theArgs) {	
		Main m = new Main();
		Scanner input = null;
		
		if (INPUT == null) System.out.println("Ah-ha! Where's your input file?");
			
		try {
			input = new Scanner(new File(INPUT));
			
			while (input.hasNextLine()) {
				String line = input.nextLine();
				System.out.println(PROCESS_ORDER + m.myCount + ": " + line);
				m.parseLine(line);
				m.myCount++;
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test method for MyStack.
	 */
	public void testMyStack() {
		MyStack<String> s = new MyStack<String>();
		s.push("1");
		s.push("2");
		s.push("3");
		s.pop();
		s.push("4");
		s.toString();
		System.out.println("MyStack size is: " + s.size());
	}
	
	/**
	 * Test method for Burger.
	 */
	public void testBurger() {
		
//		//Burger
//		Burger b = new Burger(false);
//		b.changePatties("Beef");
		
//		//Double Baron Burger
//		Burger b = new Burger(true);
//		b.addPatty();
//		b.changePatties("Beef");
		
		//Triple Chicken Burger with Onions Cheese but Cheddar
		Burger b = new Burger(false);
		b.addCategory("Cheese");
		b.addIngredient("Onions");
		b.removeIngredient("Cheddar");
		b.addPatty();
		b.addPatty();
		b.changePatties("Chicken");

		System.out.println(PROCESS_ORDER + ": ThisIsUnkonwBurger");
		System.out.println(b.toString());
	}
}
