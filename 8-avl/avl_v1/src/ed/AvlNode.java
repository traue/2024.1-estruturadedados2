package ed;

public class AvlNode<T> {

	private AvlNode<?> left;
	private AvlNode<?> right;
	private final int nodeId;
	private final T data;
	private SIDE side;
	private int leftDepth;
	private int rightDepth;

	public AvlNode(int id, T data) {
		this.nodeId = id;
		this.data = data;
		this.setSide(SIDE.ROOT);
	}

	public AvlNode<?> getLeft() {
		return left;
	}

	public void setLeft(AvlNode<?> left) {
		this.left = left;
	}

	public AvlNode<?> getRight() {
		return right;
	}

	public void setRight(AvlNode<?> right) {
		this.right = right;
	}

	public int getNodeId() {
		return nodeId;
	}

	public T getData() {
		return data;
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

	public SIDE getSide() {
		return side;
	}

	public void setSide(SIDE side) {
		this.side = side;
	}

	public void setRightDepth(int rightDepth) {
		this.rightDepth = rightDepth;
	}

	public int getBalance() {
		return rightDepth - leftDepth;
	}

	public int maxDepth() {
		return Math.max(leftDepth, rightDepth);
	}

	@Override
	public String toString() {
		return String.format("ID: %d | LeftDelpth: %d | RightDepth: %d | Side: %c | Balance: %d", getNodeId(),
				getLeftDepth(), getRightDepth(), getSide(), getBalance());
	}
}
