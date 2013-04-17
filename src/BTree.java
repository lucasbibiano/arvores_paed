public class BTree<T> {
    private BNode<T> root;

    public BTree(int t) {
	setRoot(new BNode<T>(t));
    }

    public void insertValue(T value) {
	BNode<T> node = getRoot();
    }

    public BNode<T> getRoot() {
	return root;
    }

    public void setRoot(BNode<T> root) {
	this.root = root;
    }
}
