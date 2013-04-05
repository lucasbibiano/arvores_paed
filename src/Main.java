
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
	RBTree<Integer> rbtree = new RBTree<Integer>();
	
	rbtree.insert(42, 42);
	rbtree.breadthTraversalPrint();

	rbtree.insert(11, 11);
	rbtree.breadthTraversalPrint();

	rbtree.insert(1, 1);
	rbtree.breadthTraversalPrint();

	rbtree.insert(90, 90);
	rbtree.breadthTraversalPrint();

	rbtree.insert(39, 39);
	rbtree.breadthTraversalPrint();

	rbtree.insert(32, 32);
	rbtree.breadthTraversalPrint();

	rbtree.insert(9, 9);
	rbtree.breadthTraversalPrint();

	rbtree.insert(23, 23);
	rbtree.breadthTraversalPrint();

	rbtree.insert(19, 19); //destroi a parada feio

	rbtree.breadthTraversalPrint();
	
	System.out.println(rbtree.isValid());
    }

}
