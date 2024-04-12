package ed;

import java.util.Stack;

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

		if (root == null) {
			root = node;
			qty = 1;
			return true;
		}

		Stack<AvlNode<?>> stack = new Stack<>();
		AvlNode<?> cursor = root;

		while (true) {

			stack.add(cursor);

			if (node.getNodeId() == cursor.getNodeId()) {
				return false;
			}

			if (node.getNodeId() < cursor.getNodeId()) {
				cursor.setSide(SIDE.left);

				if (cursor.getLeft() == null) {
					cursor.setLeft(node);
					stack.add(node);
					qty++;
					checkBalance(stack);
					return true;
				}
				cursor = cursor.getLeft();
				continue;
			}

			if (node.getNodeId() > cursor.getNodeId()) {
				cursor.setSide(SIDE.right);

				if (cursor.getRight() == null) {
					cursor.setRight(cursor);
					stack.add(node);
					qty++;
					checkBalance(stack);
					return true;
				}
				cursor = cursor.getRight();
				continue;
			}
		}

	}

	private void checkBalance(Stack<AvlNode<?>> stack) {

		int size = stack.size();
		int leftDepth, rightDepth;
		AvlNode<?> cursor, parentCursor;

		for (int i = size - 1; i >= 0; i--) {
			cursor = stack.get(i);
			leftDepth = cursor.getLeftDepth();
			rightDepth = cursor.getRightDepth();

			if (Math.abs(leftDepth - rightDepth) > 1) {
				balanceInsertion(stack, i);
				return;
			}

			if (i > 0) {
				parentCursor = stack.get(i - 1);
				if (parentCursor.getSide() == SIDE.left) {
					parentCursor.setLeftDepth(cursor.maxDepth() + 1);
				} else {
					parentCursor.setRightDepth(cursor.maxDepth() + 1);
				}
			}
		}

	}

	private void balanceInsertion(Stack<AvlNode<?>> stack, int nodeId) {

		if (nodeId == 0) {
			if (stack.get(0).getBalance() > 0) {
				if (stack.get(1).getBalance() >= 0) {
					// rotação simples a esquerda
				} else {
					// rotação dupla a equerda
				}
			} else {
				if (stack.get(1).getBalance() < 0) {
					// rotação simples a direita
				} else {
					// rotação dupla a direita
				}
			}
			return;
		}

		AvlNode<?> noId = stack.get(nodeId);
		AvlNode<?> childNodeId = stack.get(nodeId + 1);

		// desbalanceamento do lado esquerdo
		if (noId.getBalance() < 0) {
			// fator de balanceamento do nó desbalanceado tem o mesmo sinal do fator de
			// balanceamento do filho
			if (noId.getBalance() * childNodeId.getBalance() >= 0) {
				// rotação simples a direita
			} else {
				// rotação dupla a direita
			}

		} else {
			//desbalanceamento do lado direito
			if (noId.getBalance() * childNodeId.getBalance() > 0) {
				//rotação simples a esquera
			} else {
				//rotação dupla a esquerda
			}
		}

	}
	
	
	private void leftSimpleRotation(Stack<AvlNode<?>> stack, int id, boolean rotation) {
		
		AvlNode<?> nodeId = stack.get(0);
		AvlNode<?> nodeIdParent;
		AvlNode<?> nodeIdChild = stack.get(id + 1);
		
		if (id == 0) {
			nodeIdParent = null; //root displacement
		} else {
			nodeIdParent = stack.get(id - 1);
		}
		
		if (nodeIdParent != null) {
			if (nodeIdParent.getSide() == SIDE.left) {
				nodeIdParent.setLeft(nodeIdChild);
			} else {
				nodeIdParent.setRight(nodeIdChild);
			}
		}
		
		nodeId.setRight(nodeIdChild.getLeft());
		nodeIdChild.setLeft(nodeId);
		
		if (rotation) {
			return;
		}
		
		//paramos aqui
		
	}

}






