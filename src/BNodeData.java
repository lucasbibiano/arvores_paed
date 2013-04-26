public class BNodeData<T> implements Comparable<Integer> {
	private static final int INVALID_KEY = Integer.MAX_VALUE;

	private T data;
	private int key;

	private BNode<T> less;
	private BNode<T> high;

	public BNodeData(T data, int key) {
		this.data = data;
		this.key = key;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public void setData(T data) {
		this.data = data;
	}

	public boolean isValid() {
		return key != INVALID_KEY;
	}

	public T getData() {
		return data;
	}

	@Override
	public int compareTo(Integer o) {
		return this.key - o;
	}

	public BNode<T> getLess() {
		return less;
	}

	public void setLess(BNode<T> less) {
		this.less = less;
	}

	public BNode<T> getHigh() {
		return high;
	}

	public void setHigh(BNode<T> high) {
		this.high = high;
	}
}