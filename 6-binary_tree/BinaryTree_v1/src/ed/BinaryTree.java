/*
 * Implementação do Prof. André Kishimoto
 * 
 * */
package ed;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {

	private Node root;

	public BinaryTree(Node root) {
		this.root = root;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public int getDegree() {
		return getDegreeHelper(root);
	}

	public int getHeight() {
		if (isEmpty()) {
			return -1;
		}

		return root.getHeight();
	}

	public String inOrderTransversal() {
		return inOrderTransversalHelper(root);
	}

	public String preOrderTranversal() {
		return preOrderTranversalHelper(root);
	}

	public String posOrderTranversal() {
		return posOrderTranversalHelper(root);
	}

	public String leverOrderTransversal() {
		return leverOrderTransversalHelper(root);
	}

	private int getDegreeHelper(Node node) {
		if (node == null || node.isLeaf()) {
			return 0;
		}

		int degree = node.getDegree();

		if (node.hasLeftChild()) {
			degree = Math.max(degree, getDegreeHelper(node.getLeft()));
		}

		if (node.hasRightChild()) {
			degree = Math.max(degree, getDegreeHelper(node.getRight()));
		}

		return degree;
	}

	private String inOrderTransversalHelper(Node node) {
		if (node == null) {
			return "";
		}

		StringBuilder sb = new StringBuilder();

		sb.append(inOrderTransversalHelper(node.getLeft()));
		sb.append(node.getData() + " ");
		sb.append(inOrderTransversalHelper(node.getRight()));

		return sb.toString();
	}

	private String preOrderTranversalHelper(Node node) {
		if (node == null) {
			return "";
		}

		StringBuilder sb = new StringBuilder();

		sb.append(node.getData() + " ");
		sb.append(preOrderTranversalHelper(node.getLeft()));
		sb.append(preOrderTranversalHelper(node.getRight()));

		return sb.toString();
	}

	private String posOrderTranversalHelper(Node node) {
		if (node == null) {
			return "";
		}

		StringBuilder sb = new StringBuilder();

		sb.append(posOrderTranversalHelper(node.getLeft()));
		sb.append(posOrderTranversalHelper(node.getRight()));
		sb.append(node.getData() + " ");

		return sb.toString();
	}

	private String leverOrderTransversalHelper(Node node) {
		if (node == null) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		Queue<Node> queue = new LinkedList<>();

		queue.add(node);

		while (!queue.isEmpty()) {
			Node visited = queue.remove();
			sb.append(visited.getData() + " ");

			if (visited.hasLeftChild()) {
				queue.add(visited.getLeft());
			}

			if (visited.hasRightChild()) {
				queue.add(visited.getRight());
			}
		}

		return sb.toString();
	}

	@Override
	public String toString() {
		return String.format("Binary Tree\n " + " - isEmpty(): %s\n " + " - getDegree(): %d\n " + " - getHeight(): %d\n"
				+ " - root: { %s }", isEmpty(), getDegree(), getHeight(), root);
	}

}
