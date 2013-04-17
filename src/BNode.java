import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class BNode<T> extends BSNode<T>
{
	private ArrayList<BNode<T>> list_node;
	int valueOfT = 0;
	
	public BNode(int valueOfT)
	{
		this.valueOfT = valueOfT;
		this.list_node = new ArrayList<BNode<T>>((valueOfT*2)+1);
	}

	public void initChildren()
	{
		for (int i = 0; i < 2; i++)
		{
			BSNode<T> node = new BSNode<T>(valueOfT);
			node.setParent(this);
			children[i] = node;
		}
	}
}