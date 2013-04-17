public class BNode<T> extends BSNode<T>
{
	private BNode<T> next = null;
	
	public BNode(){}
	
	public BNode<T> getNext()
	{
		return next;
	}
	
	public void setNext(BNode<T> node)
	{
		next = node;
	}

}