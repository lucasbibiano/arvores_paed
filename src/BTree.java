public class BTree<T> {
	private BNode<T> root;

	public BTree(int t) {
		setRoot(new BNode<T>(t));
	}

	public void insertValue(T value) {
		BNode<T> whereToInsert = getRoot();

		while (whereToInsert != null) {

		}	
	}

	public BNode<T> getRoot() {
		return root;
	}

	public void setRoot(BNode<T> root) {
		this.root = root;
	}
}
