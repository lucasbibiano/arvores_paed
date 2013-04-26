import java.util.Arrays;
import java.util.Comparator;

@SuppressWarnings("unchecked")
public class BNode<T> {
	private BData<T>[] nodeData;
	private BNode<T>[] children;
	private BNode<T> parent;
	private int maxNodes;
	private int nNodes;
	private boolean isLeaf;
	private int t;
	
	public BNode(int t) {
		nNodes = 0;
		this.t = t;
		maxNodes = 2 * t - 1;
		nodeData = (BData<T>[]) new BData<?>[maxNodes];
		children = (BNode<T>[]) new BNode<?>[maxNodes + 1];
		isLeaf = true;
	}

	public void add(T data, int key) {
		nodeData[nNodes++] = new BData<T>(key, data);
		Arrays.sort(nodeData, 0, nNodes);
		Arrays.sort(children, new Comparator<BNode<T>>() {

			@Override
			public int compare(BNode<T> o1, BNode<T> o2) {
				if (o1 == null || o2 == null)
					return 0;
				
				return o1.maxKey() - o2.maxKey();
			}
			
		});
	}
	
	protected void add(BData<T> data) {
		add(data.getData(), data.getKey());
	}
	
	public BData<T> median() {
		return nodeData[nNodes/2];
	}
	
	public BNode<T> medianLeft() {
		BNode<T> result = new BNode<T>(t);
		
		
		for (int i = 0; i < nNodes/2; i++)
			result.add(nodeData[i]);
				
		return result;
	}
	
	public BNode<T> medianRight() {
		BNode<T> result = new BNode<T>(t);
				
		for (int i = nNodes/2 + 1; i < nodeData.length; i++)
			result.add(nodeData[i]);
				
		return result;
	}
	
	public int maxKey() {
		return nodeData[nNodes - 1].getKey();
	}
	
	public void splitChild(BNode<T> child) {
		int i;
		
		for (i = 0; i < children.length && children[i] != child; i++);
				
		BNode<T> nodesLeft = children[i].medianLeft();
		BNode<T> nodesRight = children[i].medianRight();
		
		nodesLeft.setLeaf(true);
		nodesRight.setLeaf(true);
		
		children[i] = nodesLeft;
		
		for (int j = children.length - 2; j >= i + 1; j--) {
			children[j + 1] = children[j];
		}
		
		children[i + 1] = nodesRight;
	}
	
	public void setChildren(BNode<T>[] children) {		
		
		for (BNode<T> c: children)
			if (c != null)
				c.setParent(this);
		
		this.children = children;
	}

	public BNode<T>[] getChildren() {
		return children;
	}
	
/*	public void addNode(T data, int key) {
		nodeData[nNodes++] = new BNodeData<T>(data, key);

	public boolean isFull() {
		return nNodes == maxNodes;
	}

	public BNode<T> getChildrenByKey(int key) {
		int i = 0;
				
		while (i < nodeData.length && key > nodeData[i].getKey()) {
			i++;
		}
		
		return children[i];
	}
	
	@Override
	public String toString() {
		return "| " + Arrays.toString(nodeData) + " |";
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public BNode<T> getParent() {
		return parent;
	}

	public void setParent(BNode<T> parent) {
		this.parent = parent;
	}

}
