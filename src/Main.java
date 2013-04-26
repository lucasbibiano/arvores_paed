public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BSTree<Integer> rbtree = new BSTree<Integer>();

		rbtree.insert(42, 42);

		rbtree.insert(11, 11);

		rbtree.insert(1, 1);

		rbtree.insert(90, 90);
		rbtree.insert(91, 91);
		rbtree.insert(80, 80);
		rbtree.insert(39, 39);

		rbtree.insert(32, 32);

		rbtree.insert(9, 9);

		rbtree.insert(23, 23);

		rbtree.insert(19, 19);
		rbtree.printPreOrder();

		rbtree.remove(90);
		rbtree.printPreOrder();

		rbtree.remove(80);
		rbtree.printPreOrder();

		rbtree.remove(90);
		rbtree.printPreOrder();

		rbtree.remove(91);
		rbtree.printPreOrder();

		System.out.println(rbtree.isValid());
		
		BTree<Integer> btree = new BTree<Integer>(3);
		
		btree.insertValue(50, 50);
		btree.insertValue(45, 45);
		btree.insertValue(40, 40);
		btree.insertValue(35, 35);
		btree.insertValue(30, 30);
		btree.insertValue(25, 25);
		btree.insertValue(20, 20);
		btree.insertValue(15, 15);
		btree.insertValue(10, 10);
		btree.insertValue(5, 5);
		
		btree.printTree();

	}

}
