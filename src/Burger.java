/*
 * BurgerBaron
 */

/**
 * Burger class.
 *
 * @author Tzu-Chien Lu tclu82@uw.edu
 * @version Winter 2016
 */
public class Burger {
	
	// Stores ingredients of this burger to print out.
	private MyStack<Object> myBurger;
	
	// A recipe burger.
	private MyStack<Object> myRecipe;
	
	// How many patties of this burger
	private int myPattyCount;
	
	private String myPatty;

	/**
	 * Constructor initializes a Burger or a Baron Burger
	 * 
	 * @param theWorks make Baron Burger when theWorks is true.
	 */
	public Burger(boolean theWorks) {
		myBurger = new MyStack<Object>();
		myPattyCount = 1;
		myPatty = "Beef";
		
		myRecipe = new MyStack<Object>();
		
		myRecipe.push(new Bun());
		myRecipe.push(new Ketchup());
		myRecipe.push(new Mustard());
		myRecipe.push(new Mushrooms());
		myRecipe.push(new Patties());
		myRecipe.push(new Cheddar());
		myRecipe.push(new Mozzarella());
		myRecipe.push(new Pepperjack());
		myRecipe.push(new Onions());
		myRecipe.push(new Tomato());
		myRecipe.push(new Lettuce());
		myRecipe.push(new BaronSauce());
		myRecipe.push(new Mayonnaise());
		myRecipe.push(new Bun());
		myRecipe.push(new Pickle());	
		
		// Baron Burger
		if (theWorks) {
			MyStack<Object> temp = new MyStack<Object>();
			
			while (!myRecipe.isEmpty()) temp.push(myRecipe.pop());
			
			while (!temp.isEmpty()) {
				Object ingredient = temp.pop();
				myBurger.push(ingredient);
				myRecipe.push(ingredient);
			}
			
		// Burger
		} else {
			myBurger.push(new Bun());
			myBurger.push(new Patties());
			myBurger.push(new Bun());
		}
	}
	
	/**
	 * Changes all patties in the Burger to the pattyType.
	 * 
	 * @param thePattyType Beef, Chicken or Veggie
	 */
	public void changePatties(String thePattyType) {
		MyStack<Object> temp = new MyStack<Object>();
		MyStack<Object> tempRecipe = new MyStack<Object>();
		myPatty = thePattyType;
		
		while (!myBurger.isEmpty()) {
			Object ingredient = myBurger.pop();
			
			if (ingredient instanceof Patties) {
				
				try {
					
				// http://goo.gl/jGx1xB
				ingredient = Class.forName(myPatty).newInstance();
				
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					
				} catch (InstantiationException e) {
					e.printStackTrace();
				}
			}
			temp.push(ingredient);
		}
		
		while (!myRecipe.isEmpty()) {
			Object ingredient = myRecipe.pop();
			
			if (ingredient instanceof Patties) {
				
				try {
					
				// http://goo.gl/jGx1xB
				ingredient = Class.forName(myPatty).newInstance();
				
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					
				} catch (InstantiationException e) {
					e.printStackTrace();
				}
			}
			tempRecipe.push(ingredient);
		}

		while (!temp.isEmpty()) {
			myBurger.push(temp.pop());
		}
		
		while (!tempRecipe.isEmpty()) {
			myRecipe.push(tempRecipe.pop());
		}
	}

	/**
	 * This method adds one patty to the Burger up to the maximum of 3.
	 */
	public void addPatty() {
		
		if (myPattyCount >= 3) return;
		
		myPattyCount++;
		MyStack<Object> temp = new MyStack<Object>();
		MyStack<Object> tempRecipe = new MyStack<Object>();
		
		while (!myBurger.isEmpty()) {
			Object ingredient = myBurger.pop();
			
			// 2nd or 3rd patty goes above Cheese
			if (ingredient instanceof Cheese || ingredient instanceof Patties) {
				temp.push(new Patties());
				temp.push(ingredient);
				break;
			}
			temp.push(ingredient);
		}
		
		while (!myRecipe.isEmpty()) {
			Object ingredient = myRecipe.pop();
			
			// 2nd or 3rd patty goes above Cheese
			if (ingredient instanceof Cheese || ingredient instanceof Patties) {
				tempRecipe.push(new Patties());
				tempRecipe.push(ingredient);
				break;
			}
			tempRecipe.push(ingredient);
		}
			
		while (!temp.isEmpty()) myBurger.push(temp.pop());
		
		while (!tempRecipe.isEmpty()) myRecipe.push(tempRecipe.pop());
		
		
	}
	
	/**
	 * This method removes one patty from the Burger down to the minimum of 1.
	 */
	public void removePatty() {
		
		if (this.myPattyCount <= 1) return;
		
		myPattyCount--;
		MyStack<Object> temp = new MyStack<Object>();
		MyStack<Object> tempRecipe = new MyStack<Object>();
		
		while (!myBurger.isEmpty()) {
			Object ingredient = myBurger.pop();
			
			if (ingredient instanceof Patties) break;
			temp.push(ingredient);
		}
		
		while (!myRecipe.isEmpty()) {
			Object ingredient = myRecipe.pop();
			
			if (ingredient instanceof Patties) break;
			tempRecipe.push(ingredient);
		}
		
		while (!temp.isEmpty()) myBurger.push(temp.pop());
		
		while (!tempRecipe.isEmpty()) myRecipe.push(tempRecipe.pop());	
	}
	
	/**
	 * This method adds all items of the type to the Burger in the proper locations.
	 * 
	 * @param theType add this type category.
	 */
	public void addCategory(String theType) {
		
		try {
			
			// http://goo.gl/jGx1xB
			Object category = Class.forName(theType).newInstance();
			
			if (category instanceof Cheese) {
				addIngredient("Cheddar");
				addIngredient("Mozzarella");
				addIngredient("Pepperjack");
				
			} else if (category instanceof Sauce) {
				addIngredient("Mayonnaise");
				addIngredient("BaronSauce");
				addIngredient("Mustard");
				addIngredient("Ketchup");
				
			} else if (category instanceof Veggies) {
				addIngredient("Lettuce");
				addIngredient("Tomato");
				addIngredient("Onions");
				addIngredient("Pickle");
				addIngredient("Mushrooms");
			}
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method removes all items of the type from the Burger.
	 * 
	 * @param theType remove this type category.
	 */
	public void removeCategory(String theType) {
		
		try {
			
			// http://goo.gl/jGx1xB
			Object category = Class.forName(theType).newInstance();
			
			if (category instanceof Cheese) {
				removeIngredient("Cheddar");
				removeIngredient("Mozzarella");
				removeIngredient("Pepperjack");
				
			} else if (category instanceof Sauce) {
				removeIngredient("Mayonnaise");
				removeIngredient("BaronSauce");
				removeIngredient("Mustard");
				removeIngredient("Ketchup");
				
			} else if (category instanceof Veggies) {
				removeIngredient("Lettuce");
				removeIngredient("Tomato");
				removeIngredient("Onions");
				removeIngredient("Pickle");
				removeIngredient("Mushrooms");
			}
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method adds the ingredient type to the Burger in the proper location.
	 * 
	 * @param theType add this ingredient.
	 */
	public void addIngredient(String theType) {
		MyStack<Object> recipeTemp = new MyStack<Object>();
		MyStack<Object> burgerTemp = new MyStack<Object>();

			
		while (!myRecipe.peek().getClass().getSimpleName().equals(theType)) {
			String s1 = myRecipe.peek().getClass().getSimpleName();
			String s2 = myBurger.peek().getClass().getSimpleName(); 
		
			if (s1.equals(s2)) burgerTemp.push(myBurger.pop());
			
			recipeTemp.push(myRecipe.pop());
		}
		myBurger.push(myRecipe.peek());
		
		while (!recipeTemp.isEmpty()) myRecipe.push(recipeTemp.pop());
		
		while (!burgerTemp.isEmpty()) myBurger.push(burgerTemp.pop());
	}
	
	/**
	 * This method removes the ingredient type from the Burger.
	 * 
	 * @param theType remove this ingredient.
	 */
	public void removeIngredient(String theType) {
		
		try {
			MyStack<Object> temp = new MyStack<Object>();
			
			// http://goo.gl/jGx1xB
			Object category = Class.forName(theType).newInstance();
			
			// Not bun.
			if (category instanceof Bun) return;
			
			while (!myBurger.isEmpty()) {
				Object ingredient = myBurger.pop();
				
				if (ingredient.getClass().equals(category.getClass())) continue;
				temp.push(ingredient);
			}
			
			while (!temp.isEmpty()) myBurger.push(temp.pop());
		
		} catch (InstantiationException e) {
			e.printStackTrace();
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * toString method prints out the burger.
	 */
	public String toString() {
		MyStack<Object> temp = new MyStack<Object>(); 
		String s = "[";
		
		// Traverse all ingredient in myBurger.
		while (!myBurger.isEmpty()) {
			Object ingredient = myBurger.pop();
			String name = ingredient.getClass().getSimpleName();
			
			if (name == "Patties") {
				name = myPatty;
			}
			
			if (name == "BaronSauce") name = "Baron-Sauce";
			
			if (!myBurger.isEmpty()) s += name + ", ";
			
			else s += name + "]";
			
			temp.push(ingredient);
		}
		
		// Get it back to myBurger.
		while (!temp.isEmpty()) myBurger.push(temp.pop());
		return s;
	}
}

class Bun {
}

class Patties {
}

class Cheese {
}

class Veggies {
}

class Sauce {
}
class Beef extends Patties {
}

class Chicken extends Patties {
}

class Veggie extends Patties {
}

class Cheddar extends Cheese {
}

class Mozzarella extends Cheese {
}

class Pepperjack extends Cheese {
}

class Lettuce extends Veggies {
}

class Tomato extends Veggies {
}

class Onions extends Veggies {
}

class Pickle extends Veggies {
}

class Mushrooms extends Veggies {
}

class Ketchup extends Sauce {
}

class Mustard extends Sauce {
}

class Mayonnaise extends Sauce {
}

class BaronSauce extends Sauce {
}
