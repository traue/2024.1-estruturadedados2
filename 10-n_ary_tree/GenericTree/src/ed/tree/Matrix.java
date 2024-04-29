package ed.tree;

import java.util.ArrayList;

public class Matrix {

	private ArrayList<Node> parentNodes;
	private ArrayList<Node> childNodes;
	private ArrayList<Ponter> printList;

	public Matrix() {
		parentNodes = new ArrayList<>();
		childNodes = new ArrayList<>();
		printList = new ArrayList<>();
	}

	public Node[] find(String parent, String child, boolean remove) {
		if (parentNodes.isEmpty()) {
			return null;
		}
		for (int i = 0; i < parentNodes.size(); i++) {
			if (parentNodes.get(i).getNodeId().equals(parent) && childNodes.get(i).getNodeId().equals(child)) {
				Node[] ret = new Node[2];
				ret[0] = parentNodes.get(i);
				ret[1] = childNodes.get(i);
				if (remove) {
					parentNodes.remove(i);
					childNodes.remove(i);
				}
				return ret;
			}
		}
		return null;
	}

	public boolean insert(Node parent, Node child) {
		if (find(parent.getNodeId(), child.getNodeId(), false) == null) {
			return false;
		}
		parentNodes.add(parent);
		childNodes.add(child);
		return true;
	}

	public void print(boolean relist, String code) {
		if (relist) {
			refazLista(code);
		}

	}

	private void refazLista(String codigo) {
		printList.clear();

	}

}
