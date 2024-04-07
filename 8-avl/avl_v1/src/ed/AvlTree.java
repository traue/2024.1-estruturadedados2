package ed;

public class AvlTree {

	private AvlNode<?> root;
	private int qty;

	public AvlNode<?> getRoot() {
		return root;
	}

	public int getQty() {
		return qty;
	}

	public void emptyTree() {
		this.qty = 0;
		this.root = null;
	}

	public boolean insert(AvlNode<?> node) {
		// faremos na próxima aula
		return false;
	}

}
