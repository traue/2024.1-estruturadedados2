/**
 * From https://github.com/mvyas85/Binary-Tree/tree/master
 */

package ed.bst;

public class BST {
	private BSTNode root;

	public BST() {
		root = null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public void insert(int data) {
		root = insert(root, data);
	}

	private BSTNode insert(BSTNode node, int data) {
		if (node == null)
			node = new BSTNode(data);
		else {
			if (data <= node.getData())
				node.left = insert(node.left, data);
			else
				node.right = insert(node.right, data);
		}
		return node;
	}

	public void delete(int k) {
		if (isEmpty()) {
			System.out.println("Tree Empty");
			return;
		} else if (search(k) == false) {
			System.out.println("Sorry " + k + " is not present");
			return;
		}
		root = delete(root, k);
		System.out.println(k + " deleted from the tree");
	}

	private BSTNode delete(BSTNode root, int k) {
		BSTNode p, p2, n;
		if (root.getData() == k) {
			BSTNode lT, rT;
			lT = root.getLeft();
			rT = root.getRight();
			if (lT == null && rT == null)
				return null;
			else if (lT == null) {
				p = rT;
				return p;
			} else if (rT == null) {
				p = lT;
				return p;
			} else {
				p2 = rT;
				p = rT;
				while (p.getLeft() != null)
					p = p.getLeft();
				p.setLeft(lT);
				return p2;
			}
		}
		if (k < root.getData()) {
			n = delete(root.getLeft(), k);
			root.setLeft(n);
		} else {
			n = delete(root.getRight(), k);
			root.setRight(n);
		}
		return root;
	}

	public int countNodes() {
		return countNodes(root);
	}

	private int countNodes(BSTNode r) {
		if (r == null)
			return 0;
		else {
			int l = 1;
			l += countNodes(r.getLeft());
			l += countNodes(r.getRight());
			return l;
		}
	}

	public boolean search(int val) {
		return search(root, val);
	}

	private boolean search(BSTNode r, int val) {
		boolean found = false;
		while ((r != null) && !found) {
			int rval = r.getData();
			if (val < rval)
				r = r.getLeft();
			else if (val > rval)
				r = r.getRight();
			else {
				found = true;
				break;
			}
			found = search(r, val);
		}
		return found;
	}

	public void inorder() {
		inorder(root);
	}

	private void inorder(BSTNode r) {
		if (r != null) {
			inorder(r.getLeft());
			System.out.print(r.toString() + " ");
			inorder(r.getRight());
		}
	}

	public void preorder() {
		preorder(root);
	}

	private void preorder(BSTNode r) {
		if (r != null) {
			System.out.print(r.toString() + " ");
			preorder(r.getLeft());
			preorder(r.getRight());
		}
	}

	public void postorder() {
		postorder(root);
	}

	private void postorder(BSTNode r) {
		if (r != null) {
			postorder(r.getLeft());
			postorder(r.getRight());
			System.out.print(r.toString() + " ");
		}
	}
}
