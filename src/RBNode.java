
public class RBNode<T> extends BNode<T>
{
	private enum Color { DOUBLE_BLACK, BLACK, RED };

	public static final Color RED = Color.RED;
	public static final Color BLACK = Color.BLACK;

	private Color color;

	public RBNode()
	{
	}

	@Override
	public void initChildren()
	{
		for (int i = 0; i < 2; i++)
		{
			RBNode<T> node = new RBNode<T>();
			node.setColor(BLACK);
			node.setParent(this);
			children[i] = node;
		}
	}

	public void swapColor()
	{
		if (color == RED)
			color = BLACK;
		else
			color = RED;
	}

	public Color getColor() 
	{
		return color;
	}

	public void setColor(Color color) 
	{
		this.color = color;
	}

}
