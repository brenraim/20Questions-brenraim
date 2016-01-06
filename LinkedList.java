/**	@author Brendan Raimann
*	11/24/15
*	@version 1.2 - Fixed for Stack
* 	Linked List data structure
*/
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements Iterable<E>, Queue<E>
{
	/** pointer to first node */
	private ListNode<E> head;
	
	/** pointer to last node */
	private ListNode<E> tail;
	
	/** amount of nodes in the list */
	private int size;
	
	/**
	*	Default constructor
	*/
	public LinkedList()
	{
		head = null;
		tail = null;
		size = 0;
	}
	
	/**
	*	Constructor with first node
	*	@param h A ListNode<E> that is placed into the LinkedList
	*/
	public LinkedList(ListNode<E> h)
	{
		head = tail = h;
		size = 1;
	}
	
	
	/**
	*	Copy constructor
	*	@param other Another LinkedList<E> that is copied
	*/
	public LinkedList(LinkedList<E> other)
	{
		for (int i = 0; i < other.size(); i++)
			add(other.get(i));
		size = other.size();
	}
	
	/**
	*	Adds an item of type E to the end of the list
	*	@param item The item to be added to the end of the list
	*	@return Returns true if the item was successfully added to the list
	*/
	public boolean add(E item)
	{
		size++;
		if (head == null)
			head = tail = new ListNode<E>(item, null);
		else
		{
			ListNode<E> temp = new ListNode<E>(item, null);
			tail.setNext(temp);
			tail = temp;
		}
		return true;	
	}
	
	/**
	*	Add object to stack
	*	@param item The item to be added to the end of the list
	*/
	public void push(E item)
	{
		addFirst(item);
	}
	

	/**
	*	Add object to queue
	*	@param item The item to be added to the end of the list
	*/
	public void offer(E item)
	{
		add(item);
	}
	
	/**
	*	Remove item and return whether or not object was removed
	*	@param o Object to be removed from the list
	*	@return Returns true of the item is removed successfully 
	*/
	public boolean remove(E o)
	{
		int index = indexOf(o);
		if (index < 0)
			return false;
		remove(index);
		size--;
		return true;
	}

	/**
	*	Remove and return item at specified index
	*	@param index The index for the item to be removed from the lsit
	*	@return Returns the value of the item removed
	*/
	public E remove(int index)
	{
		if (index >= size || head == null)
			throw new IndexOutOfBoundsException("The index of " + index + " is greater than the size of the list");
		if (index < 0)
			throw new IllegalArgumentException("The index of " + index + " is below zero");
		if (index == 0)
		{
			ListNode<E> temp = head;
			head = head.getNext();
			return temp.getItem();
		}
		else
		{
			int count = 0;
			ListNode<E> temp = head;
			for (ListNode<E> curr = head; count < index; curr = curr.getNext())
			{
				temp =  curr;
				count++;
			}
			E obj = temp.getNext().getItem();
			if (index < size - 1) 		//  the -1 is there because code relies on an existing node after the index
				temp.setNext(temp.getNext().getNext());
			else 	// if the index  is equal to the size
			{
				temp.setNext(null);
				tail = temp;
			}
			size--;
			return obj;
			
		}
			
	}

	/**
	*	Remove and return first item in the list
	*	@return Returns the item at the first node
	*/
	public E removeFirst()
	{
		if (isEmpty() == true)
			throw new NoSuchElementException();
		return remove(0);
	}

	/**
	*	Remove and return last item in the list
	*	@return Returns the item at the last node
	*/
	public E removeLast()
	{
		if (isEmpty() == true)
			throw new NoSuchElementException();
		return remove(size - 1);
	}

	/**
	*	Add object to front of list
	*	@param item The item to be added to the beginning of the list
	*/
	public void addFirst(E item)
	{
		add(0, item);
	}
	
	/**
	*	Add object to end of list
	*	@param item The item to be added to the end of the list
	*/
	public void addLast(E item)
	{
		add(item);
	}

	/**
	*	Return whether or not list contains specified object
	*	@param o The object to be searched for in the list
	*	@return Returns boolean for the existence of the object in the list
	*/
	public boolean contains(E o)
	{
		if (indexOf(o) < 0)
			return false;
		return true;
	}

	/**
	*	Returns the size of the list
	*	@return The size of the list
	*/
	public int size()
	{
		return size;
	}
	
	/**
	*	Returns the size of the list from a specified node to the end
	*	@param node Starting node for counting
	*	@return The amount of nodes from a starting node to the end
	*/
	private int size (ListNode<E> node)
	{
		if (node == null)
			return 0;
		return 1 + size(node.getNext());
	}
	
	/**
	*	Remove all items from the list
	*/
	public void clear()
	{
		head = tail = null;
		size = 0;
	}

	/**
	*	Returns the item at any specified index
	*	@param i Index of the element to return
	*	@return the element at the specified index of the list
	*/
	public E get(int i)
	{
		if (i >= size || head == null)
			throw new IndexOutOfBoundsException("The index of " + i + " is greater than the size of the list");
		if (i < 0)
			throw new IllegalArgumentException("The index of " + i + " is below zero");
		int count = 0;
		for (ListNode<E> curr = head; curr != null; curr = curr.getNext())
		{
			if (count == i)
				return curr.getItem();
			count++;
		}
		throw new Error("Fatal Error: get(" + i + ")");
	}

	/**
	*	Place object at specified location
	*	@param index The index for the object to be set
	*	@param o The object to be set into the list
	*	@return Returns the object previously in the list
	*/
	public E set(int index, E o)
	{
		if (index >= size || head == null)
			throw new IndexOutOfBoundsException("The index of " + index + " is not in the list");
		if (index < 0)
			throw new IllegalArgumentException("The index of " + index + " is below zero");
		int count = 0;
		ListNode<E> temp = head;
		for (ListNode<E> curr = head; curr != null; curr = curr.getNext())
		{
			if (count == index)
			{
				temp = curr;
				break;
			}
			count++;
		}
		E output = temp.getItem();
		temp.setItem(o);
		return output;
	}

	/**
	*	Adds an object at a specified index. Other items get shifted down the list.
	*	@param index Index for the element to be added
	*	@param item	Element to be added into the list
	*/
	public void add(int index, E item)
	{
		if (index > size)
			throw new IndexOutOfBoundsException("The index of " + index + " is greater than the size of the list");
		if (index < 0)
			throw new IllegalArgumentException("The index of " + index + " is below zero");
		if (index == size) //adds to tail
			add(item);
		else
		{
			if (head != null) //if list has 1 node or more
			{
				int count = 0;
				for (ListNode<E> curr = head; curr != null; curr = curr.getNext())
				{
					if (count == 0 && count == index)
							head = new ListNode<E> (item, head);
					if (count == index - 1)
					{
						curr.setNext(new ListNode<E> (item, curr.getNext()));
						if (count == size)
							tail = curr.getNext();
						break;
					}
					count++;
				}
			}
			else //if list is empty
				head = tail = new ListNode<E>(item, null);
		}
		size++;
	}

	/**
	*	Return index of the first instance of specified object
	*	@param o Object to be searched for in the list
	*	@return The index of the object. If it does not exist, it returns -1
	*/
	public int indexOf(E o)
	{
		if (o == null)
		{
			int count = 0;
			for (ListNode<E> curr = head; curr != null; curr = curr.getNext())
			{
				if (curr.getItem() == o)
					return count;
				count++;
			}
			return -1;
		}
		int count = 0;
		for (ListNode<E> curr = head; curr != null; curr = curr.getNext())
		{
			if (curr.getItem().equals(o))
				return count;
			count++;
		}
		return -1;
	}

	/**
	*	Removes the front item from the queue and returns it
	*	@return Returns the front item of the queue
	*/
	public E poll()
	{
		return removeFirst();
	}
	
	/**
	*	Removes the top item from the stack and returns it
	*	@return Returns the top item from the stack
	*/
	public E pop()
	{
		return removeFirst();
	}

	/**
	*	Returns the front item 
	*	@return Returns the front item
	*/
	public E peek()
	{
		if (head != null)
			return head.getItem();
		return null;
	}

	/**
	*	Return whether or not list is empty
	*	@return Returns whether or not the list is empty
	*/
	public boolean isEmpty()
	{
		if (head == null)
			return true;
		return false;
	}

	/**
	*	Returns a string representation of the list
	*	@return String representation of the list
	*/
	public String toString()
	{
		String output = "";
		for(ListNode<E> curr = head; curr != null; curr = curr.getNext())
		{
			output += curr;
			if (curr.getNext() != null)
				output += ",";
		}
		return output;
	}

	/**
	*	For iterating this class
	*	@return Iterator<E>	to iterate data
	*/
	public Iterator<E> iterator()
	{
		return new LinkedListIterator<E>(head);
	}
}