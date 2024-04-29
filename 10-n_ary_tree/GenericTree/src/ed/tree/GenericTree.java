package ed.tree;

import java.util.ArrayList;

public class GenericTree {

	public GenericTree(Node root) {
		root.setLevel(0);
		this.genericTree = new ArrayList<>();
		genericTree.add(root);
		qty = 1;
	}

	ArrayList<Node> genericTree;
	int qty;

	public int getQty() {
		return this.qty;
	}

	public boolean insert(String parentNodeId, Node node) {

		Node parentCursor;
		for (int i = 0; i < genericTree.size(); i++) {

			parentCursor = genericTree.get(i);

			if (parentCursor.getNodeId().equals(parentNodeId)) {
				node.setLevel(parentCursor.getLevel() + 1);
				if (i == genericTree.size() - 1) {
					genericTree.add(node);
				} else {
					genericTree.add(i + 1, node);
				}
				qty++;
				return true;

			}

		}
		return false;
	}

	private int _findNode(String nodeId) {

		for (int i = 0; i < genericTree.size(); i++) {
			if (genericTree.get(i).getNodeId().equals(nodeId)) {
				return i;
			}
		}
		return -1;
	}

	public Node findNode(String nodeId) {

		if (genericTree.isEmpty()) {
			System.err.println("Tree is empty!");
		}

		int idx = _findNode(nodeId);
		if (idx >= 0) {
			return genericTree.get(idx);
		} else {
			System.err.println("Can't find");
		}

		return null;

	}

	public void print(String nodeId) {

		if (genericTree.isEmpty()) {
			System.err.println("Tree is empty!");
			return;
		}

		String tab = "\t\t";
		int idx = 0;

		if (nodeId != null) {
			idx = _findNode(nodeId);
			if (idx == -1) {
				System.err.println("Can't find it");
				return;
			}
		}

		System.out.println("\n\nTree Print");

		int startlevel = genericTree.get(idx).getLevel();
		System.out.println("Stating by: " + genericTree.get(idx).getNodeId());
		System.out.println("\n");

		for (int i = idx; i < genericTree.size(); i++) {
			Node e = genericTree.get(i);
			if (e.getLevel() < startlevel) {
				System.out.println("\nEnd of the tree");
				return;
			}
			for (int j = 0; j <= e.getLevel() - startlevel; j++) {
				System.out.print(tab);
			}
			printNode(e, false);
		}

	}

	public void printNode(Node node, boolean descr) {
		String desc = "";
		if (descr) {
			desc = node.getNodeDesc();
		}

		System.out.println(node.getNodeId() + " | " + node.getNodeName() + " | " + desc);
	}

}
