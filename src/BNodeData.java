public class BNodeData<T> implements Comparable<BNodeData<T>> {
	private static final int INVALID_KEY = Integer.MAX_VALUE;

	private T data;
	private int key;

	private BNode less;
	private BNode high;

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
	public int compareTo(BNodeData<T> other) {
		return this.key - other.key;
	}

	public BNode getLess() {
		return less;
	}

	public void setLess(BNode less) {
		this.less = less;
	}

	public BNode getHigh() {
		return high;
	}

	public void setHigh(BNode high) {
		this.high = high;
	}
}