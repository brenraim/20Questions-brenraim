/**
*	Brendan Raimann
*	12/17/15
*	Version 1.0 - Iterable
*	Binary Tree Data Structure
*/

import java.util.Iterator;

public class BinaryTree<E> implements Iterable<E>
{
	/** The stored value in the tree */
	protected E value;
	
	/** The pointer to the left tree */
	protected BinaryTree<E> left;
	
	/** The pointer to the right tree */
	protected BinaryTree<E> right;
	
	/**
	*	Constructor with three parameters
	*	@param v The value to be stored
	*	@param l The pointer to the left tree
	*	@param r The pointer to the right tree
	*/
	public BinaryTree(E v, BinaryTree<E> l, BinaryTree<E> r)
	{
		value = v;
		left = l;
		right = r;
	}
	
	/**
	*	Constructor with one parameter
	*	@param v The value to be stored
	*/
	public BinaryTree(E v)
	{
		this(v, null, null);
	}
	
	/**
	*	Default Constructor
	*/
	public BinaryTree()
	{
		this(null, null, null);
	}
	
	/**
	*	Accessor for the left tree
	*	@return Returns the left tree
	*/
	public BinaryTree<E> left()
	{
		return left;
	}
	
	/**
	*	Accessor for the right tree
	*	@return Returns the right tree
	*/
	public BinaryTree<E> right()
	{
		return right;
	}
	
	/**
	*	Accessor for the value
	*	@return Returns the stored value
	*/
	public E value()
	{
		return value;
	}
	
	/**
	*	Sets the left pointer
	*	@param node The tree that will be assigned to the left
	*/
	public void setLeft(BinaryTree<E> node)
	{
		left = node;
	}
	
	/**
	*	Sets the right pointer
	*	@param node The tree that will be assigned to the right
	*/
	public void setRight(BinaryTree<E> node)
	{
		right = node;
	}
	
	/**
	*	Sets the value
	*	@param val The value that will be assigned to the tree
	*/
	public void setValue(E val)
	{
		value = val;
	}
	
	/**
	*	Returns if the tree has no pointers
	*	@return Returns if the left and right pointers are null
	*/
	public boolean isLeaf()
	{	
		if (left == null && right == null)
			return true;
		return false;
	}
	
	/**
	*	Returns the amount of trees
	*	@return Returns the amount of trees
	*/
	public int size()
	{
		if (isLeaf() == true)
			return 1;
		else
		{
			if (left != null && right == null)
				return 1 + left.size();
			if (left == null && right != null)
				return 1 + right.size();
			if (left != null && right != null)
				return 1 + left.size() + right.size();
			throw new Error("FATAL ERROR");   //should never occur
		}
	}
	
	/**
	*	Returns a String representation of the tree
	*	@return Returns a String representation of the tree
	*/
	public String toString()
	{
		if (isLeaf() == true)
			return (String)value;
		else
		{
			if (left != null && right == null)
				return value + "(" + left.toString() + ",)";
			if (left == null && right != null)
				return value + "(," + right.toString() + ")";
			return value + "(" + left.toString() + "," + right.toString() + ")";
		}
	}
	
	/**
	*	Returns whether or not the tree is full
	*	@return Returns true if the tree is full
	*/
	public boolean isFull()
	{
		if (isLeaf() == true)
			return true;
		else
		{
			if (left == null || right == null)
				return false;
			return (right.height() == left.height() && left.isFull() && right.isFull());
		}
	}
	
	/**
	*	Returns the height of the tree
	*	@return Returns the height of the tree
	*/
	public int height()
	{
		if (isLeaf() == true)
			return 0;
		else
		{
			if (left != null && right == null)
				return 1 + left.height();
			if (left == null && right != null)
				return 1 + right.height();
			return 1 + Math.max(left.height(), right.height());
		}
	}
	
	/**
	*
	*	Returns whether or not the tree is balanced
	*	@return Returns true if the tree is balanced
	*/
	public boolean isBalanced() 
	{
		if (isLeaf() == true)
			return true;
		if (left != null && right == null && left.height() < 1)
			return true;
		if (left == null && right != null && right.height() < 1)
			return true;
		return (Math.abs(left.height() - right.height()) <= 1);
	}
	
	/**
	*
	*	Returns whether or not the tree is complete
	*	@return Returns true if the tree is Balanced
	*/
	public boolean isComplete()
	{
		if (isLeaf() == true)
			return true;
		if (left == null)
			return false;
		if (right == null && left.isLeaf())
			return true;
		else
		{	
			if (left.height() - right.height() == 1)
				return (left.isComplete() && right.isFull());
			if (left.height() == right.height())
				return (left.isFull() && right.isComplete());
			return false;
		}
	}
	
	/**
	*	For iterating this class
	*	@return Iterator<E>	to iterate stored values
	*/
	public Iterator<E> iterator()
	{
		return inorderIterator();
	}
	
	/**
	*	For iterating this class
	*	@return Iterator<E>	to iterate stored values
	*/
	public Iterator<E> preorderIterator()
	{
		return new PreOrderIterator<E>(this);
	}
	
	/**
	*	For iterating this class
	*	@return Iterator<E>	to iterate stored values
	*/
	public Iterator<E> inorderIterator()
	{
		return new InOrderIterator<E>(this);
	}
	
	/**
	*	For iterating this class
	*	@return Iterator<E>	to iterate stored values
	*/
	public Iterator<E> postorderIterator()
	{
		return new PostOrderIterator<E>(this);
	}
}