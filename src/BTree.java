import java.util.ArrayList;

public class BTree {
	private BNode root;

	public BTree(int t) {
		setRoot(new BNode(t));
	}
	
	public BNode getRoot() {
		return root;
	}

	public void setRoot(BNode root) {
		this.root = root;
	}

	public void insertValue(int key) {
		BNode whereToInsert = searchHelper(getRoot(), key);
		
		if(!whereToInsert.isFull()){
			whereToInsert.addKey(key);
		} else {
				
		}
	}
	
	public BNode split (BNode node){
		int max_node = node.getMaxNodes();
		int middle = (max_node-1)/2;
		BNode new_node = new BNode(max_node, node.getKeys()[middle]);
		new_node.setParent(node.getParent());
		
		BNode new_children_left = new BNode(max_node);
		BNode new_children_right = new BNode(max_node);

		for (int i = 0; i < middle; i++) {
			new_children_left.getKeys()[i] = node.getKeys()[i];
			new_children_right.getKeys()[max_node-i] = node.getKeys()[max_node-i];
		}
		
		new_children_left.setParent(new_node);
		new_children_right.setParent(new_node);
		
		BNode[] children = new_node.getChildren();
		children[0] = new_children_left;
		children[1] = new_children_right;
		
		new_node.setNoLeaf();
		
		return new_node;
	}
	
	public Integer search(int key){
		BNode node = searchHelper(getRoot(), key);
		
		if(node == null)
			return null;
		
		return node.getPosition(key);
	}
	
	private BNode searchHelper(BNode node, int key){
		if(node.isLeaf())
			return node;
		
		int[] keys = node.getKeys();
		
		for (int i = 0; i < node.getNumberOfKeys(); i++) {
			int key_in_node = keys[i];
			
			if(key_in_node == key)
				return node;
			if(key_in_node > key)
				searchHelper(node.getChildren()[i], key);
			if(key_in_node < key && i == (node.getNumberOfKeys()-1))
				searchHelper(node.getChildren()[i+1], key);
		}
		
		return null;
	}
	
	public void inOrdem(BNode node){
		if (node != null){
			for (int i = 0; i <= node.getNumberOfKeys(); i++){
				inOrdem(node.getChildren()[i]);
				
				if(i < node.getNumberOfKeys())
					System.out.println(node.getKeys()[i]);
			}
			inOrdem(node.getChildren()[i]);
		}
	}
}
