/** Brendan Raimann
*	11/19/15
*	Interface for Queue
*/

public interface Queue<E>
{
	/**	
	*	Adds item to end of the queue
	*	@param item The item to be added to the end of the list
	*/
	void offer(E item);
	
	/**	
	*	Removes the front item from the queue and returns it
	*	@return Returns the front item of the queue
	*/
	E poll();
	
	/**	
	*	Returns the front item from the queue
	*	@return Returns the front item of the queue
	*/
	E peek();
	
	/**	
	*	Returns whether the queue is empty or not
	*	@return Returns whether or not the queue is empty
	*/
	boolean isEmpty();
}