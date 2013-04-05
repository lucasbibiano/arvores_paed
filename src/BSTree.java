import java.util.ArrayDeque;
import java.util.Queue;

public class BSTree<T> 
{
	private BNode<T> root;

	public BSTree()
	{
		root = new BNode<T>();
	}

	public boolean isValid()
	{
		Queue<BNode<T>> queue = new ArrayDeque<BNode<T>>();

		queue.offer(root);

		while (!queue.isEmpty())
		{
			BNode<T> node = queue.poll();

			if (node.isEmpty())
			{
				continue;
			}

			if (node != getRoot())
			{
				//se o no eh esquerdo e a chave dele eh maior do que a do pai
				if (node.isLeft() && node.getKey() > node.getParent().getKey())
					return false;

				//se o no eh direito e a chave dele eh menor do que a do pai
				if (node.isRight() && node.getKey() < node.getParent().getKey())
					return false;

				queue.offer(node.getLeft());
				queue.offer(node.getRight());
			}
		}

		return true;
	}

	public void breadthTraversalPrint()
	{
		Queue<BNode<T>> queue = new ArrayDeque<BNode<T>>();

		queue.offer(root);

		while (!queue.isEmpty())
		{
			BNode<T> node = queue.poll();

			if (node.isEmpty())
			{
				System.out.println("E");
				continue;
			}

			System.out.println(node.getValue());
			queue.offer(node.getLeft());
			queue.offer(node.getRight());
		}
	}

	public void printInOrder()
	{
		inorder(root);
	}

	private void inorder(BNode<T> node)
	{
		if (node.isEmpty())
			return;

		inorder(node.getLeft());
		System.out.println(node.getValue());
		inorder(node.getRight());
	}

	public void printPostOrder()
	{
		postorder(root);
	}

	private void postorder(BNode<T> node)
	{
		if (node.isEmpty())
			return;

		postorder(node.getLeft());
		postorder(node.getRight());
		System.out.println(node.getValue());
	}

	public void printPreOrder()
	{
		preorder(root);
	}

	private void preorder(BNode<T> node)
	{
		if (node.isEmpty())
			return;

		System.out.println(node.getValue());	
		preorder(node.getLeft());
		preorder(node.getRight());
	}

	public T search(int key)
	{
		BNode<T> foundNode = searchHelper(root, key);
		return foundNode == null ? null : foundNode.getValue();
	}

	private BNode<T> searchHelper(BNode<T> node, int key)
	{
		if (node.isEmpty())
			return null;

		if (key > node.getKey())
			return searchHelper(node.getRight(), key);
		else if (key < node.getKey())
			return searchHelper(node.getLeft(), key);

		return node;
	}

	public T getMin()
	{
		return getMinHelper(root).getValue();
	}

	private BNode<T> getMinHelper(BNode<T> node)
	{	
		while (!node.isEmpty())
		{
			node = node.getLeft();
		}

		return node;
	}

	public T getMax()
	{
		return getMaxHelper(root).getValue();
	}

	private BNode<T> getMaxHelper(BNode<T> node)
	{
		while (!node.isEmpty())
		{
			node = node.getRight();
		}

		return node; 
	}

	public T getPredecessor(int key)
	{
		BNode<T> predecessorNode = getPredecessorHelper(key);
		return predecessorNode == null ? null : predecessorNode.getValue();
	}

	public BNode<T> getPredecessorHelper(int key)
	{
		BNode<T> node = searchHelper(root, key);

		if (node.hasLeftChild())
		{
			return getMaxHelper(node.getLeft());
		}
		else 
		{
			if (node == getRoot())
				return null;

			node = node.getParent();

			while (node.getKey() > key)
			{
				node = node.getParent();
			}

			return node;
		}
	}

	public T getSuccessor(int key)
	{
		BNode<T> successorNode = getSuccessorHelper(key);
		return successorNode == null ? null : successorNode.getValue();    
	}

	public BNode<T> getSuccessorHelper(int key)
	{
		BNode<T> node = searchHelper(root, key);

		if (node.hasRightChild())
		{
			return getMinHelper(node.getRight());
		}
		else 
		{
			node = node.getParent();

			while (node.getKey() < key)
			{
				node = node.getParent();
			}

			return node;
		}
	}

	public boolean insert(T value, int key)
	{
		return insertHelper(root, value, key);
	}

	private boolean insertHelper(BNode<T> node, T value, int key)
	{
		if (node.isEmpty())
		{
			node.setValue(value);
			node.setKey(key);

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

	public T remove(int key)
	{
		//TODO
		/*
		BNode<T> foundNode = searchHelper(root, key);
		
		if (foundNode == null)
			return null;
		
		if (foundNode.hasRightChild()) {
			BNode<T> successor = getSuccessorHelper(key);
			
			BNode<T> parent = 
			
		} else {
			
		}*/
	}

	public BNode<T> getRoot() 
	{
		return root;
	}

	public void setRoot(BNode<T> root) 
	{
		this.root = root;
	}
}