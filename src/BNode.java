import java.util.Arrays;

public class BNode<T> {
    private BNodeData<T>[] nodeData;
    private int maxNodes;
    private int nNodes;

    public BNode(int t) {
	nNodes = 0;
	maxNodes = 2 * t - 1;
	nodeData = (BNodeData<T>[]) new BNodeData<?>[maxNodes];
    }

    public void addNode(T data, int key) {
	nodeData[nNodes++] = new BNodeData<T>(data, key);

	Arrays.sort(nodeData);
    }

    public boolean isFull() {
	return nNodes == maxNodes;
    }

    public BNode<T> whereToInsert(int key) {
	int i = 0;
	
	BNodeData<T> data = (BNodeData<T>) nodeData[i];
	
	while (data.getKey() < key)
	{
	    i++;
	    data = (BNodeData<T>) nodeData[i];
	}
	
	return data.getLess();
    }
}
