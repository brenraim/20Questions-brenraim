/**
*	Brendan Raimann
*	12/18/15
*	Class for iterating a binaryTree using InOrder.
*/

import java.util.Iterator;

public class InOrderIterator<E> implements Iterator<E>
{
	/** Queue for storing the sequence of values*/
	private Queue<E> queue;
	
	/**
	*	Constructor with a BinaryTree as a parameter
	*	@param tree A BinaryTree to be iterated
	*/
	public InOrderIterator(BinaryTree<E> tree)         //left, value, right
	{
		queue = new LinkedList<E>();
		fillQueue(tree);
	}
	
	/**
	*	Fills the queue with the sequence of values
	*	@param tree The BinaryTree to be iterated
	*/
	public void fillQueue(BinaryTree<E> tree)
	{
		if (tree.isLeaf() == true)
			queue.offer(tree.value());
		else
		{
			if (tree.left() != null)
				fillQueue(tree.left());
			queue.offer(tree.value());
			if (tree.right() != null)
				fillQueue(tree.right());
		}
	}
	
	/**
	*	Returns true if there is more to be iterated
	*	@return Returns whether there is another value to be added to the queue
	*/
	public boolean hasNext()
	{
		return !queue.isEmpty();
	}
	
	/**
	*	Returns the next value of the queue and removes it
	*	@return Returns the next value in the sequence
	*/
	public E next()
	{
		return queue.poll();
	}
}