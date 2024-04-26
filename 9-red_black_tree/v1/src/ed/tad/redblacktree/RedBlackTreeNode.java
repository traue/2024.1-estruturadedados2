package ed.tad.redblacktree;

public class RedBlackTreeNode<T extends Comparable<T>> {

	public COLOR color;
	public T key;
	RedBlackTreeNode<T> left;
	RedBlackTreeNode<T> right;
	public RedBlackTreeNode<T> parent;

	public RedBlackTreeNode(T key, COLOR color, RedBlackTreeNode<T> parent, RedBlackTreeNode<T> left,
			RedBlackTreeNode<T> right) {
		this.key = key;
		this.color = color;
		this.parent = parent;
		this.left = left;
		this.right = right;
	}

	public T getKey() {
		return key;
	}

	public String toString() {
		return "" + key + (this.color == COLOR.RED ? "(RED)" : "BLACK");
	}
}
