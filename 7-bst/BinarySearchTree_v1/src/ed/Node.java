package ed;

public class Node<T> {

	private Node<?> left;
	private Node<?> right;
	private final int id;
	private final T data;
	public char side;

	public Node(int id, T data) {

		this.id = id;
		this.data = data;
	}

	public Node<?> getLeft() {
		return left;
	}

	public void setLeft(Node<?> left) {
		this.left = left;
	}

	public Node<?> getRight() {
		return right;
	}

	public void setRight(Node<?> right) {
		this.right = right;
	}

	public int getId() {
		return id;
	}

	public T getData() {
		return data;
	}
}
