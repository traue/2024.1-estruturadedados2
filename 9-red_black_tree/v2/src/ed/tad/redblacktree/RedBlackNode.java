package ed.tad.redblacktree;

public class RedBlackNode<E> {

	private E data;
	private RedBlackNode<E> left;
	private RedBlackNode<E> right;
	private boolean isRed;

	public RedBlackNode(E data) {
		this.data = data;
		this.left = null;
		this.right = null;
		this.isRed = true;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public RedBlackNode<E> getLeft() {
		return left;
	}

	public void setLeft(RedBlackNode<E> left) {
		this.left = left;
	}

	public RedBlackNode<E> getRight() {
		return right;
	}

	public void setRight(RedBlackNode<E> right) {
		this.right = right;
	}

	public boolean isRed() {
		return isRed;
	}

	public void setIsRed(boolean isRed) {
		this.isRed = isRed;
	}

}
