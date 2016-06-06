/*
 * BurgerBaron
 */

/**
 * MyStack class.
 *
 * @author Tzu-Chien Lu tclu82@uw.edu
 * @version Winter 2016
 */
public class MyStack<Type> {

	/** Current node from inner class */
	private Node myNode;
	
	/** Size of stack */
	private int mySize;

	/**
	 * Initialize MyStack, default empty
	 */
	public MyStack() {
		myNode = null;
		mySize = 0;
	}

	/**
	 * Check if MyStack is empty
	 * 
	 * @return true if is empty 
	 */
	public boolean isEmpty() {
		return size() == 0;
	}
	
	/**
	 * Push one item to MyStack.
	 * 
	 * @param item
	 */
	public void push(Type item) {
		
		if (isEmpty()) {
			myNode = new Node(item);
			
			// The first node, link to null.
			myNode.setNextNode(null);
			
		} else {
			Node temp = new Node(item);
			
			// The 2nd node, link to previous node.
			temp.setNextNode(myNode);
			
			// Assign myNode to the top node.
			myNode = temp;
		}
		mySize++;
	}
	
	/**
	 * Remove the top item of MyStack then return it.
	 * 
	 * @return the top item of MyStack
	 */
	public Type pop() {
		
		if (isEmpty()) {
			return null;
		}
		
		Node temp = myNode;
		
		// Assign next node as the new top node.
		myNode = myNode.myNextNode;
		mySize--;
		return temp.getIngredient();
	}

	/**
	 * get the top item of MyStack
	 * 
	 * @return the top item of MyStack
	 */
	public Type peek() {
		
		if (isEmpty()) {
			return null;
		}
		return myNode.getIngredient();
	}
	
	/**
	 * get the size of MyStack
	 * 
	 * @return size
	 */
	public int size() {
		return mySize;
	}

	/**
	 * Return a string for current node.
	 * 
	 * @return a String represents MyStack
	 */
	public String toString() {
		String result = "";
		Node temp = myNode;
		
		while (temp != null) {
			result += temp.getIngredient().toString() + " ";
			temp = temp.myNextNode;
		}
		return result;
	}
	
	/**
	 * Inner class.
	 * 
	 * @author Tzu-Chien Lu
	 * @param <Type> A ingredient
	 */
	private class Node {
		
		Type myIngredient;
		
		Node myNextNode;
		
		/**
		 * Constructor
		 * 
		 * @param theIngredient
		 */
		private Node(Type theIngredient) {
			myIngredient = theIngredient;
		}

		/**
		 * Getter method
		 * 
		 * @return ingredient
		 */
		private Type getIngredient() {
			return myIngredient;
		}

		/**
		 * Setter method
		 * 
		 * @param theNext next node 
		 */
		private void setNextNode(Node theNext) {
			myNextNode = theNext;
		}
	}
}
