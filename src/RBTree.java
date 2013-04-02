import java.util.ArrayDeque;
import java.util.Queue;

public class RBTree<T> extends BSTree<T> 
{
    public RBTree()
    {
	setRoot(new RBNode<T>());
    }
    
    @Override
    public boolean isValid()
    {
	//TODO
	if (!super.isValid())
	    return false;
	
	RBNode<T> root = (RBNode<T>) getRoot();
	
	if (root.getColor() == RBNode.RED)
	    return false;
	
	return true;
    }
    
    //nao sei traduzir altura de preto
    private boolean hasSameBlackHeight(RBNode<T> node, int actualHeight)
    {
	//TODO
	return true;
    }
    
    @Override
    public void breadthTraversalPrint()
    {
	Queue<RBNode<T>> queue = new ArrayDeque<RBNode<T>>();
	int k = 1;
	int i = 0;
	
	queue.offer((RBNode<T>) getRoot());
	
	while (!queue.isEmpty())
	{
	    RBNode<T> node = (RBNode<T>) queue.poll();
	    i++;
	    
	    if (k == i)
	    {
		k = k * 2;
		System.out.println();
	    }
	    
	    if (node.isEmpty())
	    {
		System.out.print("E" + (node.getColor() == RBNode.RED ? "R " : "B "));
		continue;
	    }
	    
	    System.out.print(node.getValue() + (node.getColor() == RBNode.RED ? "R " : "B "));
	    queue.offer((RBNode<T>) node.getLeft());
	    queue.offer((RBNode<T>) node.getRight());
	}
	
	System.out.println("=====================");
    }
    
    @Override
    public boolean insert(T value, int key)
    {
	return insertHelper(getRoot(), value, key);
    }
    
    private boolean insertHelper(BNode<T> rbnode, T value, int key)
    {
	RBNode<T> node = (RBNode<T>) rbnode;
	
	if (node.isEmpty())
	{
	    node.setValue(value);
	    node.setKey(key);
	    
	    node.setColor(RBNode.RED);
	    
	    verifyNode(node);
	    
	    return true;
	}
	else
	{
	    if (key > node.getKey())
		return insertHelper(node.getRight(), value, key);
	    else if (key < node.getKey())
		return insertHelper(node.getLeft(), value, key);
	    
	    return false;
	}	
    }
    
    private void verifyNode(RBNode<T> node)
    {
	if (node == getRoot())
	{   
	    node.setColor(RBNode.BLACK);
	    return;
	}
	    
	RBNode<T> parent = (RBNode<T>) node.getParent();
	
	if (parent.getColor() == RBNode.BLACK)
	{
	    return;
	}
	
	RBNode<T> uncle = (RBNode<T>) node.getUncle();
	RBNode<T> grand = (RBNode<T>) node.getGrandfather();

	if (uncle.getColor() == RBNode.RED)
	{
	    parent.swapColor();
	    uncle.swapColor();
	    grand.swapColor();
	    
	    verifyNode(grand);
	}
	else
	{
	    if (uncle.isRight())
	    {
		if (node.isLeft())
		{
		    rightRot(grand, parent);
		    parent.swapColor();
		    grand.swapColor();
		}
		else
		{
		    leftRot(parent, node);
		    verifyNode(parent);
		}
	    }
	    else
	    {
		if (node.isRight())
		{
		    leftRot(grand, parent);
		    parent.swapColor();
		    grand.swapColor();
		}
		else
		{
		    rightRot(parent, node);
		    verifyNode(parent);
		}
	    }
	}	    		
    }
    
    private void rightRot(RBNode<T> x, RBNode<T> y)
    {	
	RBNode<T> aux = (RBNode<T>) x.getParent();
	
	if (x == getRoot())
	    setRoot(y);
	else
	{
	    if (x.isLeft())
		aux.setLeft(y);
	    else 
		aux.setRight(y);
	}
	
	x.setLeft(y.getRight());		
	y.setRight(x);
    }
    
    private void leftRot(RBNode<T> x, RBNode<T> y)
    {	
	RBNode<T> aux = (RBNode<T>) x.getParent();
	
	if (x == getRoot())
	    setRoot(y);
	else
	{
	    if (x.isLeft())
		aux.setLeft(y);
	    else 
		aux.setRight(y);
	}
	
	x.setRight(y.getLeft());	
	y.setLeft(x);
    }
}

