import java.util.Arrays;

public class BNode<T> {
    private BNodeData<?>[] nodes;
    private BNode<?>[] children;
    private int maxNodes;
    private int nNodes;

    public BNode(int t) {
	nNodes = 0;
	maxNodes = 2 * t - 1;
    }

    public void addNode(T data, int key) {
	nodes[nNodes++] = new BNodeData<T>(data, key);

	Arrays.sort(nodes);
    }

    public boolean isFull() {
	return nNodes == maxNodes;
    }

    public BNode<T> whereToInsert(T value) {	
	return null;
    }

}
