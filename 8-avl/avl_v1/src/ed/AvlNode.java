package ed;

public class AvlNode<T> {

	private AvlNode<T> left;
	private AvlNode<T> right;
	private final int nodeId;
	private final T data;
	private SIDE side;
	private int leftDepth;
	private int rightDepth;

	public AvlNode(int nodeId, T data) {
		this.nodeId = nodeId;
		this.data = data;
		this.side = SIDE.root;
	}

	public AvlNode<T> getLeft() {
		return left;
	}

	public void setLeft(AvlNode<T> left) {
		this.left = left;
	}

	public AvlNode<T> getRight() {
		return right;
	}

	public void setRight(AvlNode<T> right) {
		this.right = right;
	}

	public SIDE getSide() {
		return side;
	}

	public void setSide(SIDE side) {
		this.side = side;
	}

	public int getLeftDepth() {
		return leftDepth;
	}

	public void setLeftDepth(int leftDepth) {
		this.leftDepth = leftDepth;
	}

	public int getRightDepth() {
		return rightDepth;
	}

	public void setRightDepth(int rightDepth) {
		this.rightDepth = rightDepth;
	}

	public int getNodeId() {
		return nodeId;
	}

	public T getData() {
		return data;
	}

	public int getBalance() {
		return rightDepth - leftDepth;
	}

	public int maxDepth() {
		return Math.max(rightDepth, leftDepth);
	}
	
	@Override
	public String toString() {
		return String.format("ID: %d | LetDepth: %d | RightDepth: %d | Side: %s | Balance: %d",
				getNodeId(), getLeftDepth(), getRightDepth(), getSide(), getBalance());
	}

}
