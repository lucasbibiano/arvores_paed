@SuppressWarnings("unchecked")
public class BNode<T>
{
    private T value;
    private int key;
    private BNode<T> parent;
    
    protected BNode<?>[] children;
    
    public BNode(int numOfChildren)
    {
	this.key = 0;
	children = new BNode<?>[numOfChildren];
    }

    public BNode() 
    {
	this(2);
    }
    
    public BNode<T> getLeft()
    {
	return (BNode<T>) children[0];
    }

    public BNode<T> getRight()
    {
	return (BNode<T>) children[1];
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
	//quando um valor é setado, esse nó deixa de ser nulo,
	//e seus filhos são inicializados
	for (int i = 0; i < 2; i++)
	{
	    BNode<T> node = new BNode<T>();
	    node.setParent(this);
	    children[i] = node;
	}
    }
    
    public void setKey(int key) 
    {
        this.key = key;
    }
    
    public void setLeft(BNode<T> node)
    {
	node.setParent(this);
	children[0] = node;
    }
    
    public void setRight(BNode<T> node)
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
	return children[num] != null && !((BNode<T>) children[num]).isEmpty();
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
    
    public BNode<T> getBrother()
    {
	boolean isLeft = isLeft();
	return (isLeft ? parent.getRight() : parent.getLeft());
    }

    public BNode<T> getUncle()
    {
	return parent.getBrother();
    }
    
    public BNode<T> getGrandfather()
    {
	return parent.getParent();
    }
    
    public BNode<T> getParent() 
    {
	return parent;
    }

    public void setParent(BNode<T> parent) 
    {
	this.parent = parent;
    }
}