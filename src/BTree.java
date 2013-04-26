import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class BTree<T> {
	private BNode<T> root;
	private int t;

	public BTree(int t) {
		setRoot(new BNode<T>(t));
		this.t = t;
	}

	public void insertValue(T value, int key) {
		BNode<T> whereToInsert = getRoot();

		while (!whereToInsert.isLeaf()) {
			whereToInsert = whereToInsert.getChildrenByKey(key);
		}
		
		System.out.println(value.toString());
		
		if (whereToInsert.isFull()) {
			split(whereToInsert);
		}
		else
			whereToInsert.add(value, key);
	}
	
	@SuppressWarnings("unchecked")
	private void split(BNode<T> whereToInsert) {
		if (whereToInsert == getRoot()) {
			BNode<T> newRoot = new BNode<T>(t);
			
			newRoot.add(whereToInsert.median());
			
			BNode<T> nodesLeft = whereToInsert.medianLeft();
			BNode<T> nodesRight = whereToInsert.medianRight();
			
			nodesLeft.setLeaf(true);
			nodesRight.setLeaf(true);
			newRoot.setLeaf(false);
			
			newRoot.setChildren(concat((BNode<T>[]) new BNode<?>[] { nodesLeft, nodesRight }, nullPadding()));
			
			setRoot(newRoot);
		}
		else {
			whereToInsert.getParent().add(whereToInsert.median());
			
			whereToInsert.getParent().splitChild(whereToInsert);
			
			whereToInsert.setLeaf(false);
		}
	}
	
	private BNode<T>[] nullPadding() {
		return (BNode<T>[]) new BNode<?>[2 * t - 1 - 2];
	}

	public static <T> T[] concat(T[] first, T[] second) {
		  T[] result = Arrays.copyOf(first, first.length + second.length);
		  System.arraycopy(second, 0, result, first.length, second.length);
		  return result;
	}

	public void printTree() {		
		Queue<BNode<T>> queue = new ArrayDeque<BNode<T>>();
		int k = 1;
		int i = 0;

		queue.offer((BNode<T>) getRoot());

		while (!queue.isEmpty())
		{
			BNode<T> node = (BNode<T>) queue.poll();
			i++;

			if (k == i)
			{
				k = k * 2;
				System.out.println();
			}

			System.out.print(node.toString());
			
			BNode<T>[] children = node.getChildren();
			
			for (int j = 0; j < children.length; j++) {
				if (children[j] == null)
					continue;
				
				queue.offer(children[j]);
			}
		}

		System.out.println("=====================");
	}

	public BNode<T> getRoot() {
		return root;
	}

	public void setRoot(BNode<T> root) {
		this.root = root;
	}
}
