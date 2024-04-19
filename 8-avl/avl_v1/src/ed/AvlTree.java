package ed;

import java.util.Stack;

import javax.security.sasl.SaslException;

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
					leftSimpleRotation(stack, nodeId, false);
				} else {
					//rotação dupla a esq
					leftDoubleRotation(stack, nodeId);
				}
			} else {
				if (stack.get(1).getBalance() < 0) {
					// rotação simples a direita
					rightSimpleRotation(stack, nodeId, false);
				} else {
					// rotação dupla a direita
					rightDoubleRotation(stack, nodeId);
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
				rightSimpleRotation(stack, nodeId, false);
			} else {
				// rotação dupla a dir
				rightDoubleRotation(stack, nodeId);
			}

		} else {
			// desbalanceamento do lado direito
			if (noId.getBalance() * childNodeId.getBalance() > 0) {
				leftSimpleRotation(stack, nodeId, false);
			} else {
				// rotação dupla a esquerda
				leftDoubleRotation(stack, nodeId);
			}
		}

	}

	private void leftSimpleRotation(Stack<AvlNode<?>> stack, int id, boolean rotation) {
		AvlNode<?> nodeId = stack.get(0);
		AvlNode<?> nodeIdParent;
		AvlNode<?> nodeIdChild = stack.get(id + 1);

		if (id == 0) {
			nodeIdParent = null; // root displacement
		} else {
			nodeIdParent = stack.get(id - 1);
		}

		// o filho passa a ser a nova raiz (e filho esq ou dir do pai)
		if (nodeIdParent != null) {
			if (nodeIdParent.getSide() == SIDE.left) {
				nodeIdParent.setLeft(nodeIdChild);
			} else {
				nodeIdParent.setRight(nodeIdChild);
			}
		}

		// subarvore esq do filho passa a ser direito da raiz anterior
		// se não exisir é nulo
		nodeId.setRight(nodeIdChild.getLeft());
		nodeIdChild.setLeft(nodeId);

		if (rotation) {
			return;
		}

		// correção das profundidades
		if (id == 0) {
			if (nodeId.getRight() != null) {
				nodeId.setLeftDepth(nodeId.getRight().maxDepth() + 1);
			} else {
				nodeId.setRightDepth(0);
			}
			nodeIdChild.setLeftDepth(nodeId.maxDepth() + 1);
			this.root = nodeIdChild;
		} else {
			// lado esq
			if (nodeId.getLeft() == null) {
				nodeId.setLeftDepth(0);
			} else { // tem filho á esq
				nodeId.setLeftDepth(nodeId.getLeft().maxDepth() + 1);
			}

			// lado dir
			if (nodeId.getRight() == null) {
				nodeId.setRightDepth(0);
			} else {
				nodeId.setRightDepth(nodeId.getRight().maxDepth() + 1);
			}
		}

		System.out.println("Left Simple Rotation");
	}

	private void rightSimpleRotation(Stack<AvlNode<?>> stack, int id, boolean rotation) {

		AvlNode<?> nodeId = stack.get(id);
		AvlNode<?> nodeIdParent;
		AvlNode<?> nodeIdChild = stack.get(id + 1);

		if (id == 0) {
			nodeIdParent = null;
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

		nodeId.setLeft(nodeIdChild.getRight());
		nodeIdChild.setRight(nodeId);

		if (rotation) {
			return;
		}

		// correção das profundidades
		if (id == 0) {
			if (nodeId.getLeft() != null) {
				nodeId.setLeftDepth(nodeId.getLeft().getLeftDepth() + 1);
			} else {
				nodeId.setLeftDepth(0);
			}
			nodeIdChild.setRightDepth(nodeId.maxDepth() + 1);
			this.root = nodeIdChild;
		} else {
			if (nodeId.getLeft() == null) {
				nodeId.setLeftDepth(0);
			} else {
				nodeId.setLeftDepth(nodeId.getLeft().maxDepth() + 1);
			}

			if (nodeId.getRight() == null) {
				nodeId.setRightDepth(0);
			} else {
				nodeId.setRightDepth(nodeId.getRight().maxDepth() + 1);
			}
			nodeIdChild.setLeftDepth(nodeId.maxDepth() + 1);
		}
		System.out.println("Right Simple Rotation");
	}

	private void leftDoubleRotation(Stack<AvlNode<?>> stack, int id) {
		rightSimpleRotation(stack, id + 1, false);
		leftSimpleRotation(getStackPath(stack.peek().getNodeId()), id, false);
	}

	private void rightDoubleRotation(Stack<AvlNode<?>> stack, int id) {
		leftSimpleRotation(stack, id + 1, false);
		rightSimpleRotation(getStackPath(stack.peek().getNodeId()), id, false);
	}
	
	private Stack<AvlNode<?>> getStackPath(int value) {
		if (qty == 0) {
			return null;
		}
		Stack<AvlNode<?>> stack = new Stack<>();
		AvlNode<?> cursor = root;
		stack.add(cursor);
		while (true) {
			if (value == cursor.getNodeId()) {
				cursor.setSide(SIDE.root);
				return stack;
			}
			
			if (value < cursor.getNodeId()) {
				cursor.setSide(SIDE.left);
				cursor = cursor.getLeft();
			} else if (value > cursor.getNodeId()) {
				cursor.setSide(SIDE.right);
				cursor = cursor.getRight();
			}
			
			if (cursor == null) {
				stack.add(null);
				return stack;
			}
			
			stack.add(cursor);
		}
	}
	
}





