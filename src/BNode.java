import java.util.Arrays;

public class BNode {
	
	private BNode parent;
	private BNode[] children;
	private int[] keys;
	
	private int maxNodes;
	private int nKeys;

	private boolean isLeaf;
	
	public BNode(int t) {
		nKeys 	 = 0;
		maxNodes = (2 * t) - 1;
		children = new BNode[maxNodes + 1];
		keys 	 = new int[maxNodes];
		isLeaf 	 = true;
		parent 	 = null;
	}
	
	public BNode(int t, int key) {
		nKeys 	 = 0;
		maxNodes = (2 * t) - 1;
		children = new BNode[maxNodes + 1];
		keys 	 = new int[maxNodes];
		isLeaf 	 = true;
		parent	 = null;
		keys[0]  = key; 
	}
	
	public boolean isFull() {
		return nKeys == maxNodes;
	}
	
	public int getMaxNodes(){
		return maxNodes;
	}
	
	public final int[] getKeys() {
	    return keys;
	}
	
	public final boolean isLeaf() {
	    return isLeaf;
	}
	
	public void setNoLeaf(){
		isLeaf = false;
	}
	
	public final BNode[] getChildren() {
	    return children;
	}

	public int getNumberOfKeys(){
		return nKeys;
	}
	
	public BNode getParent(){
		return parent;
	}
	
	public void setParent(BNode parent){
		this.parent = parent;
	}
	
	public void addKey(int key){
			keys[nKeys] = key;
			nKeys++;
			Arrays.sort(keys);
	}
	
	public int getPosition(int key){
		for(int i = 0; i < getNumberOfKeys(); i++){
			if (key == getKeys()[i])
				return i;
		}
	}
	
	/*
	public void addKey(int key, BNode childrenLeft, BNode childrenRight){
			//encontrar o lugar para add a nova chave
			int i = 0;
			for (int key_in_node : getKeys()) {
				if(key_in_node > key)
					break;
				i++;
			}
			//inserir nova chave e mover chaves e filhos para o lugar correto
			int[] new_keys = new int[maxNodes];
			BNode[] new_children = new BNode[maxNodes+1];

			for(int j=0; j <= nKeys; j++){
				if(j == i){
					new_keys[j] = key;
					new_children[j] = children[j];
					j++;
				} else {
					new_keys[j] = keys[j];
				}
			}
			keys = new_keys;
		}*/
	}
	
/*	public void addNode(T data, int key) {
		nodeData[nNodes++] = new BNodeData<T>(data, key);

		Arrays.sort(nodeData);
	}

	public BNode<T> whereToInsert(int key) {
		int i = 0;

		BNodeData<T> data = (BNodeData<T>) nodeData[i];

		while (data.getKey() < key) {
			i++;
			data = (BNodeData<T>) nodeData[i];
		}

		return data.getLess();
	}*/
}
