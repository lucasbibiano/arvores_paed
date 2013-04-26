public class BData<T> implements Comparable<BData<T>>{
	private int key;
	private T data;

	public BData(int key, T data) {
		this.setKey(key);
		this.setData(data);
	}
	
	@Override
	public String toString() {
		return data.toString();
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public int compareTo(BData<T> o) {
		return this.key - o.key;
	}
}