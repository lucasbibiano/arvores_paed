@SuppressWarnings("unchecked")
public class BSNode<T>
{
	private T value;
	private int key;
	private BSNode<T> parent;

	protected BSNode<?>[] children;

	public BSNode(int numOfChildren)
	{
		this.key = 0;
		children = new BSNode<?>[numOfChildren];
	}

	public BSNode() 
	{
		this(2);
	}

	public BSNode<T> getLeft()
	{
		return (BSNode<T>) children[0];
	}

	public BSNode<T> getRight()
	{
		return (BSNode<T>) children[1];
	}

	public T getValue() 
	{
		return value;
	}

	public int getKey() 
	{
		return key;
	}

	public void setValue(T value) 
	{
		this.value = value;
		initChildren();
	}

	public void initChildren()
	{
		//quando um valor � setado, esse n� deixa de ser nulo,
		//e seus filhos s�o inicializados
		for (int i = 0; i < 2; i++)
		{
			BSNode<T> node = new BSNode<T>();
			node.setParent(this);
			children[i] = node;
		}
	}

	public void setKey(int key) 
	{
		this.key = key;
	}

	public void setLeft(BSNode<T> node)
	{
		node.setParent(this);
		children[0] = node;
	}

	public void setRight(BSNode<T> node)
	{
		node.setParent(this);
		children[1] = node;
	}

	public boolean isEmpty() 
	{
		return this.key == 0;
	}

	public boolean hasChild(int num)
	{
		return children[num] != null && !((BSNode<T>) children[num]).isEmpty();
	}

	public boolean hasLeftChild()
	{
		return hasChild(0);
	}

	public boolean hasRightChild()
	{
		return hasChild(1);
	}

	public boolean isLeft()
	{
		return (this == parent.getLeft());
	}

	public boolean isRight()
	{
		return !isLeft();
	}

	public BSNode<T> getBrother()
	{
		boolean isLeft = isLeft();
		return (isLeft ? parent.getRight() : parent.getLeft());
	}

	public BSNode<T> getUncle()
	{
		return parent.getBrother();
	}

	public BSNode<T> getGrandfather()
	{
		return parent.getParent();
	}

	public BSNode<T> getParent() 
	{
		return parent;
	}

	public void setParent(BSNode<T> parent) 
	{
		this.parent = parent;
	}
}