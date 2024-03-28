package ed;

public class Node {

	private String data;
	private Node parent;
	private Node left;
	private Node right;

	// constructors
	public Node() {
		this("", null);
	}

	public Node(String data) {
		this(data, null);
	}

	public Node(String data, Node parent) {
		this.data = data;
		this.parent = parent;
		this.left = null;
		this.right = null;
	}

	// sets & gets
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	// methods

	public boolean hasLeftChild() {
		return getLeft() != null;
	}

	public boolean hasRightChild() {
		return getRight() != null;
	}

	public boolean isRoot() {
		return getParent() == null;
	}

	public boolean isLeaf() {
		return getLeft() == null && getRight() == null;
	}

	public int getDegree() {
		int degree = 0;

		if (hasLeftChild()) {
			++degree;
		}

		if (hasRightChild()) {
			++degree;
		}

		return degree;
	}

	public int getLevel() {
		if (isRoot()) {
			return 0;
		}

		return getParent().getLevel() + 1;
	}

	public int getHeight() {
		if (isLeaf()) {
			return 0;
		}

		int height = 0;

		if (hasLeftChild()) {
			height = Math.max(height, getLeft().getHeight());
		}

		if (hasRightChild()) {
			height = Math.max(height, getRight().getHeight());
		}

		return height + 1;
	}

	@Override
	public String toString() {
		return String.format(
				"Data: %s\n" + "Parent: %s\n" + "Left: %s\n" + "Right: %s\n" + "Is Root: %s\n" + "Is Leaf: %s\n"
						+ "Degree: %d\n" + "Level: %d\n" + "Height: %d",
				getData(), (getParent() != null ? getParent().getData() : "<no parent data>"),
				(getLeft() != null ? getLeft().getData() : "<no left data>"),
				(getRight() != null ? getRight().getData() : "<no right data>"), (isRoot() ? "Yes" : "No"),
				(isLeaf() ? "Yes" : "No"), getDegree(), getLevel(), getHeight());
	}

}
