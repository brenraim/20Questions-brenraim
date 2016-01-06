/** Brendan Raimann
*	10/28/15
*	Class for iterating LinkedList<E> class in a For Each loop
*/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListIterator<E> implements Iterator<E>
{
	/**	The current node*/
	private ListNode<E> curr;
	
	/**	
	*	Constructor that takes in the first ListNode<E>
	*	@param head	The first node of the LinkedList
	*/
	public LinkedListIterator(ListNode<E> head)
	{
		curr = head;
	}
	
	/**
	*	Checks if there is a value (could be null) at the specified index
	*	@return boolean if there is a value at the current index
	*/
	public boolean hasNext()
	{
		return curr != null;
	}
	
	/**
	*	Returns the value in the LinkedList at current index
	*	@return E value at current index
	*/
	public E next()
	{
		if (hasNext() == false)
			throw new NoSuchElementException("There is no value here");
		E temp = curr.getItem();
		curr = curr.getNext();
		return temp;
	}
}