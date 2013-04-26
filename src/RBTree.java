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

		return hasSameBlackHeight(root);
	}

	private boolean hasSameBlackHeight(RBNode<T> node) {
		if (node.isEmpty())
			return true;

		RBNode<T> left = (RBNode<T>) node.getLeft();
		RBNode<T> right = (RBNode<T>) node.getRight();

		int bhLeft = getBlackHeight(left, 0);
		int bhRight = getBlackHeight(right, 0);

		if (bhLeft != bhRight)
			return false;
		else
			return hasSameBlackHeight(left) && hasSameBlackHeight(right);	
	}

	//nao sei traduzir altura de preto
	private int getBlackHeight(RBNode<T> node, int actualHeight)
	{
		if (node.isEmpty())
			return actualHeight;
		else
		{
			if (node.getColor() == RBNode.BLACK)
			{
				actualHeight++;
			}

			RBNode<T> left = (RBNode<T>) node.getLeft();
			RBNode<T> right = (RBNode<T>) node.getRight();

			return Math.max(getBlackHeight(left, actualHeight), getBlackHeight(right, actualHeight));
		}
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

	private boolean insertHelper(BSNode<T> rbnode, T value, int key)
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

	@Override
	public T remove(int key){

		//achar o nó
		RBNode node = (RBNode) search(key);

		if (node == null)
			return null;
		else if (node.getLeft() != null && node.getRight() != null) {
			//Node tem filhos, copia o valor do predecessor
			RBNode predecessor = (RBNode) getPredecessor(key);
			node.setValue(predecessor.getValue());
			node = predecessor;
		}

		//Node tem um filho
		RBNode up = (RBNode) ((node.getLeft() == null) ? node.getRight() : node.getLeft());

		if (up != null) {
			// Retirar o no e arrumar a arvore se o no for negro
			if (node == getRoot())
				setRoot(up);
			else if (node.getParent().getLeft() == node)
				node.getParent().setLeft(up);
			else
				node.getParent().setRight(up);

			if (node.getColor() == RBNode.BLACK)
				adjustTree(up);

		} 
		else if (node == getRoot()) 
		{
			setRoot(null);
		} 
		else 
		{
			if (node.getColor() == RBNode.BLACK) {
				adjustTree(node);
			}

			//remover a referenvia do pai
			if (node.isLeft())
				node.getParent().setLeft(new RBNode<T>());
			else
				node.getParent().setRight(new RBNode<T>());
		}
		
		return (T) node.getValue();
	}

	public void adjustTree(RBNode node)
	{
		while (node != getRoot() && (node.getColor() == RBNode.BLACK)) 
		{
			// node é filho a esquerda
			if (node.isLeft()) 
			{
				RBNode sibling = (RBNode) node.getBrother();

				if (sibling.getColor() == RBNode.RED) 
				{
					RBNode parent = (RBNode) node.getParent();
					
					leftRot(parent, sibling);
					parent.swapColor();
					sibling.swapColor();
					
					sibling = (RBNode) node.getParent().getRight();
				}

				RBNode left_sibling = (RBNode) sibling.getLeft();
				RBNode right_sibling = (RBNode) sibling.getRight();

				if (left_sibling.getColor() == RBNode.BLACK && right_sibling.getColor() == RBNode.BLACK)
				{
					sibling.setColor(RBNode.RED);
					node = (RBNode) node.getParent();
				} else {
					if (right_sibling.getColor() == RBNode.BLACK) 
					{
						rightRot(sibling, left_sibling);
						left_sibling.swapColor();
						sibling.swapColor();
						
						sibling = (RBNode) sibling.getParent().getRight();
					}

					RBNode parent = (RBNode) node.getParent();
					leftRot(parent, sibling);
					sibling.swapColor();
					parent.swapColor();
					
					node = (RBNode) getRoot();
				}
			} 
			// node e filho a direita
			else {
				RBNode sibling = (RBNode) node.getBrother();

				if (sibling.getColor() == RBNode.RED) 
				{
					RBNode parent = (RBNode) node.getParent();
					
					rightRot(parent, sibling);
					parent.swapColor();
					sibling.swapColor();
					
					sibling = (RBNode) node.getParent().getLeft();
				}

				RBNode left_sibling = (RBNode) sibling.getLeft();
				RBNode right_sibling = (RBNode) sibling.getRight();

				if (left_sibling.getColor() == RBNode.BLACK && right_sibling.getColor() == RBNode.BLACK)
				{
					sibling.setColor(RBNode.RED);
					node = (RBNode) node.getParent();
				} 
				else 
				{
					if (right_sibling.getColor() == RBNode.BLACK)  
					{
						rightRot(sibling, left_sibling);
						left_sibling.swapColor();
						sibling.swapColor();
						
						sibling = (RBNode) sibling.getParent().getRight();
					}

					RBNode parent = (RBNode) node.getParent();
					leftRot(parent, sibling);
					sibling.swapColor();
					parent.swapColor();
					
					node = (RBNode) getRoot();
				}
			}
		}
		node.setColor(RBNode.BLACK);
	}

}

