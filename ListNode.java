/**	Brendan Raimann
*	11/23/15
*	Version 1.0
*	List Node data structure
*/
public class ListNode<E>
{
	/**	Item stored in the ListNode*/
	private E item;
	/**	Pointer to the next ListNode*/
	private ListNode<E> next;
	
	/**
	*	Constructor with an item
	*	@param item1 The stored element of the node
	*/
	public ListNode(E item1)
	{
		item = item1;
	}
	
	/**
	*	Constructor with an item and pointer
	*	@param item1 A element to be stored in the node
	*	@param next1 A pointer to be stored in the node
	*/
	public ListNode(E item1, ListNode<E> next1)
	{
		item = item1;
		next = next1;
	}
	
	/**
	*	Returns the item stored in the node
	*	@return The item stored in the node
	*/
	public E getItem()
	{
		return item;
	}
	
	/**
	*	Returns the pointer to the next node
	*	@return The pointer to the next node
	*/
	public ListNode<E> getNext()
	{
		return next;
	}
	
	/**
	*	Sets item to a specified element
	*	@param item1 Element to be stored in the null
	*/
	public void setItem(E item1)
	{
		item = item1;
	}
	
	/**
	*	Sets the pointer to the next node
	*	@param next1 The pointer to the next node
	*/
	public void setNext(ListNode<E> next1)
	{
		next = next1;
	}
	
	/**
	*	Returns a string representation of the item
	*	@return Returns a string representation of the item
	*/
	public String toString()
	{
		return (String)item;
	}
	
}