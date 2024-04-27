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
				cursor.setSide(SIDE.LEFT);
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
				cursor.setSide(SIDE.RIGHT);
				if (cursor.getRight() == null) {
					cursor.setRight(node);
					stack.add(node);
					qty++;
					checkBalance(stack);
					return true;
				}
				cursor = cursor.getRight();
			}
		}
	}

	// recursive insertion
	// based on https://www.scaler.com/topics/binary-tree-implementation-in-java
	public boolean insert(AvlNode<?> rootBranch, AvlNode<?> nodeId) {
		if (rootBranch == null) {
			root = nodeId;
			qty++;
			return true;
		}
		if (nodeId.getNodeId() < rootBranch.getNodeId()) {
			if (rootBranch.getLeft() != null) {
				insert(rootBranch.getLeft(), nodeId);
			} else {
				rootBranch.setLeft(nodeId);
				qty++;
				return true;
			}
		} else if (nodeId.getNodeId() > rootBranch.getNodeId()) {
			if (rootBranch.getRight() != null) {
				insert(rootBranch.getRight(), nodeId);
			} else {
				rootBranch.setRight(nodeId);
				qty++;
				return true;
			}
		}
		return false;
	}

	public AvlNode<?> deleteNodeById(int nodeId) {

		if (qty == 0) {
			return null;
		}

		if (qty == 1 && nodeId == root.getNodeId()) {
			AvlNode<?> node = root;
			root = null;
			qty = 0;
			return node;
		}

		Stack<AvlNode<?>> stack = getStackPath(nodeId);

		int maxId = stack.size() - 1;

		if (stack.get(maxId) == null) {
			return null;
		}

		AvlNode<?> parentNode = stack.get(maxId - 1);
		AvlNode<?> noId = stack.get(maxId);

		AvlNode<?> leftChildren = noId.getLeft();
		AvlNode<?> rightChildren = noId.getRight();

		// if the node does not have children (leaf node)
		if (leftChildren == null && rightChildren == null) {
			if (noId.getNodeId() < parentNode.getNodeId()) {
				parentNode.setLeft(null);
			} else {
				parentNode.setRight(null);
			}

			checkBalanceOut(stack, 0, null, null);

		}
		// the node has only 1 child
		else if (leftChildren == null && rightChildren != null || leftChildren != null && rightChildren == null) {
			if (noId == root) {
				if (leftChildren != null) {
					root = leftChildren;
				} else {
					root = rightChildren;
				}
			} else {
				if (noId.getNodeId() > parentNode.getNodeId()) {
					if (leftChildren != null) {
						parentNode.setRight(leftChildren);
					} else {
						parentNode.setRight(rightChildren);
					}
				} else {
					if (leftChildren != null) {
						parentNode.setLeft(leftChildren);
					} else {
						parentNode.setLeft(rightChildren);
					}
				}
			}

			checkBalanceOut(stack, 1, null, null);

		}
		// the node has two 1 children
		else if (leftChildren != null && rightChildren != null) {

			AvlNode<?> replacementParentNode;

			if (noId == root) {
				AvlNode<?>[] leftAuxNode = biggerLeftValue(root);
				AvlNode<?>[] rightAuxNode = smallerRightValue(root);

				AvlNode<?>[] replacementAuxNode;

				if (Math.abs(root.getNodeId() - leftAuxNode[1].getNodeId()) > Math
						.abs(root.getNodeId() - rightAuxNode[1].getNodeId())) {
					replacementAuxNode = leftAuxNode;
				} else {
					replacementAuxNode = rightAuxNode;
				}
				replacementParentNode = replacementAuxNode[0];

				deleteNodeById(replacementAuxNode[1].getNodeId());

				replacementAuxNode[1].setLeft(root.getLeft());
				replacementAuxNode[1].setRight(root.getRight());
				root.setRight(null);
				root.setLeft(null);
				AvlNode<?> ret = root;
				root = replacementAuxNode[1];
				qty--;

				checkBalanceOut(stack, 2, replacementParentNode, replacementAuxNode[1]);

				return ret;
			}

			AvlNode<?> replaceNode;
			if (noId.getNodeId() > parentNode.getNodeId()) {

				AvlNode<?>[] biggerLeft = biggerLeftValue(noId);

				replacementParentNode = biggerLeft[0];
				replaceNode = biggerLeft[1];

				parentNode.setRight(biggerLeft[1]);

				biggerLeft[1].setRight(noId.getRight());

				if (biggerLeft[0] != noId) {
					biggerLeft[1].setLeft(noId.getLeft());
					biggerLeft[0].setRight(null);
				}

			}

			else {
				AvlNode<?>[] smallerRight = smallerRightValue(noId);

				replacementParentNode = smallerRight[0];
				replaceNode = smallerRight[1];

				parentNode.setLeft(smallerRight[1]);

				smallerRight[1].setLeft(noId.getLeft());

				if (smallerRight[0] != noId) {
					smallerRight[1].setRight(noId.getRight());
					smallerRight[0].setLeft(null);
				}

			}

			checkBalanceOut(stack, 2, replacementParentNode, replaceNode);

		}

		qty--;
		return noId;
	}

	public AvlNode<?>[] findNodeById(int nodeId) {

		AvlNode<?>[] auxNode = new AvlNode<?>[2];
		auxNode[1] = root;

		while (true) {
			if (auxNode[1] == null || auxNode[1].getNodeId() == nodeId) {
				return auxNode;
			}

			auxNode[0] = auxNode[1];

			if (nodeId < auxNode[0].getNodeId()) {
				auxNode[1] = auxNode[0].getLeft();
			} else {
				auxNode[1] = auxNode[0].getRight();
			}
		}
	}

	public AvlNode<?> biggerValue() {
		if (qty == 0) {
			return null;
		}
		AvlNode<?> cursor = root;
		while (cursor.getRight() != null) {
			cursor = cursor.getRight();
		}
		return cursor;
	}

	public AvlNode<?> smallerIdValue() {
		if (qty == 0) {
			return null;
		}
		AvlNode<?> cursor = root;
		while (cursor.getLeft() != null) {
			cursor = cursor.getLeft();
		}
		return cursor;
	}

	// iterative print
	public void inOrderIterative() {
	
		if (qty == 0) {
			System.err.println("Tree is empty!");
		} else {
			Stack<AvlNode<?>> stack = new Stack<>();
			AvlNode<?> cursor = this.root;
			cursor.setSide(SIDE.ROOT);
			stack.push(cursor);
			boolean checkRightSide = true;
	
			boolean doWhile1 = true;
			while1: while (doWhile1) {
	
				if (cursor.getLeft() != null) {
					while (cursor.getLeft() != null) {
						cursor = cursor.getLeft();
						cursor.setSide(SIDE.LEFT);
						stack.push(cursor);
					}
				}
				System.out.print(cursor.getNodeId() + " ");
	
				while (true) {
	
					if (checkRightSide && cursor.getRight() != null) {
						cursor = cursor.getRight();
						cursor.setSide(SIDE.RIGHT);
						stack.push(cursor);
						checkRightSide = true;
						continue while1;
					}
	
					SIDE filhoLado = stack.pop().getSide();
					if (stack.empty()) {
						doWhile1 = false;
						break while1;
					}
	
					cursor = stack.peek();
					if (filhoLado == SIDE.LEFT) {
						System.out.print(cursor.getNodeId() + " ");
						checkRightSide = true;
					} else {
						checkRightSide = false;
					}
				}
	
			}
	
			System.out.println();
	
		}
	
	}

	public void preOrderIterative() {
	
		if (qty == 0) {
			System.out.println("Tree is empty!");
		} else {
	
			Stack<AvlNode<?>> stack = new Stack<>();
			AvlNode<?> cursor = this.root;
			cursor.setSide(SIDE.ROOT);
			stack.push(cursor);
			System.out.println(cursor);
	
			while (true) {
	
				while (cursor.getLeft() != null) {
					cursor = cursor.getLeft();
					cursor.setSide(SIDE.LEFT);
					stack.push(cursor);
					System.out.println(cursor);
				}
	
				if (cursor.getRight() != null) {
					cursor = cursor.getRight();
					cursor.setSide(SIDE.ROOT);
					stack.push(cursor);
					System.out.println(cursor);
					continue;
				}
	
				while (!stack.empty()) {
					SIDE filhoLado = stack.pop().getSide();
					if (stack.empty()) {
						break;
					}
	
					cursor = stack.peek();
	
					if (filhoLado == SIDE.RIGHT) {
						continue;
					}
	
					if (cursor.getRight() != null) {
						cursor = cursor.getRight();
						cursor.setSide(SIDE.RIGHT);
						stack.push(cursor);
						System.out.println(cursor);
						break;
					} else {
						continue;
					}
				}
	
				if (stack.empty()) {
					break;
				}
			}
			System.out.println();
	
		}
	
	}

	public void postOrderIterative() {
	
		if (qty == 0) {
			System.err.println("Tree is empty!");
		} else {
			Stack<AvlNode<?>> stack = new Stack<>();
			AvlNode<?> cursor = this.root;
			cursor.setSide(SIDE.ROOT);
			stack.push(cursor);
			boolean checkRightSide = true;
	
			while1: while (true) {
	
				if (cursor.getLeft() != null) {
					while (cursor.getLeft() != null) {
						cursor = cursor.getLeft();
						cursor.setSide(SIDE.LEFT);
						stack.push(cursor);
					}
				}
	
				while (true) {
					if (checkRightSide) {
						if (cursor.getRight() != null) {
							cursor = cursor.getRight();
							cursor.setSide(SIDE.RIGHT);
							stack.push(cursor);
							checkRightSide = true;
							continue while1;
						} else {
							System.out.print(cursor.getNodeId() + " ");
						}
					}
	
					SIDE childSide = stack.pop().getSide();
					if (stack.empty()) {
						break while1;
					}
	
					cursor = stack.peek();
					if (childSide == SIDE.RIGHT) {
						System.out.print(cursor.getNodeId() + " ");
						checkRightSide = false;
					} else {
						checkRightSide = true;
					}
				}
	
			}
			System.out.println("FIM");
	
		}
	
	}

	// recursive prints
	public void inOrderRecursive(AvlNode<?> node) {
		if (node != null) {
			inOrderRecursive(node.getLeft());
			System.out.print(node.getNodeId() + " ");
			inOrderRecursive(node.getRight());
		}
	}

	public void preOrderRecursive(AvlNode<?> node) {
		if (node != null) {
			System.out.print(node.getNodeId() + " ");
			preOrderRecursive(node.getLeft());
			preOrderRecursive(node.getRight());
		}
	}

	public void postOrderRecursive(AvlNode<?> node) {
		if (node != null) {
			postOrderRecursive(node.getLeft());
			postOrderRecursive(node.getRight());
			System.out.print(node.getNodeId() + " ");
		}
	}

	private AvlNode<?>[] biggerLeftValue(AvlNode<?> node) {
	
		AvlNode<?>[] auxNode = new AvlNode<?>[2];
	
		auxNode[0] = node;
		auxNode[1] = node.getLeft();
	
		while (auxNode[1].getRight() != null) {
			auxNode[0] = auxNode[1];
			auxNode[1] = auxNode[1].getRight();
		}
	
		return auxNode;
	}

	private AvlNode<?>[] smallerRightValue(AvlNode<?> node) {
	
		AvlNode<?>[] auxNode = new AvlNode<?>[2];
	
		auxNode[0] = node;
		auxNode[1] = node.getRight();
	
		while (auxNode[1].getLeft() != null) {
			auxNode[0] = auxNode[1];
			auxNode[1] = auxNode[1].getLeft();
		}
	
		return auxNode;
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
				if (parentCursor.getSide() == SIDE.LEFT) {
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
					leftSimpleRotation(stack, nodeId, false);
				} else {
					leftDoubleRotation(stack, nodeId);
				}
			} else {
				if (stack.get(1).getBalance() < 0) {
					rightSimpleRotation(stack, nodeId, false);
				} else {
					rightDoubleRotation(stack, nodeId);
				}
			}
			return;
		}

		AvlNode<?> noId = stack.get(nodeId);
		AvlNode<?> childNodeId = stack.get(nodeId + 1);

		if (noId.getBalance() < 0) {
			if (noId.getBalance() * childNodeId.getBalance() >= 0) {
				rightSimpleRotation(stack, nodeId, false);
			} else {
				rightDoubleRotation(stack, nodeId);
			}
		}

		else {
			if (noId.getBalance() * childNodeId.getBalance() >= 0) {
				leftSimpleRotation(stack, nodeId, false);
			} else {
				leftDoubleRotation(stack, nodeId);
			}
		}

	}

	private void leftSimpleRotation(Stack<AvlNode<?>> stack, int id, boolean rotation) {

		AvlNode<?> noId = stack.get(id);
		AvlNode<?> noIdParent;
		if (id == 0) {
			noIdParent = null;
		} else {
			noIdParent = stack.get(id - 1);
		}
		AvlNode<?> noIdChild = stack.get(id + 1);

		if (noIdParent != null) {
			if (noIdParent.getSide() == SIDE.LEFT) {
				noIdParent.setLeft(noIdChild);
			} else {
				noIdParent.setRight(noIdChild);
			}
		}

		noId.setRight(noIdChild.getLeft());

		noIdChild.setLeft(noId);

		if (rotation) {
			return;
		}

		if (id == 0) {
			if (noId.getRight() != null) {
				noId.setRightDepth(noId.getRight().maxDepth() + 1);
			} else {
				noId.setRightDepth(0);
			}
			noIdChild.setLeftDepth(noId.maxDepth() + 1);
			this.root = noIdChild;
		} else {

			if (noId.getLeft() == null) {
				noId.setLeftDepth(0);
			} else {
				noId.setLeftDepth(noId.getLeft().maxDepth() + 1);
			}

			if (noId.getRight() == null) {
				noId.setRightDepth(0);
			} else {
				noId.setRightDepth(noId.getRight().maxDepth() + 1);
			}
			noIdChild.setLeftDepth(noId.maxDepth() + 1);
		}

		System.out.println("SIMPLE LEFT ROTATION");

	}

	private void rightSimpleRotation(Stack<AvlNode<?>> stack, int id, boolean rotation) {

		AvlNode<?> noId = stack.get(id);
		AvlNode<?> noIdParent;
		if (id == 0) {
			noIdParent = null;
		} else {
			noIdParent = stack.get(id - 1);
		}
		AvlNode<?> noIdChild = stack.get(id + 1);

		if (noIdParent != null) {
			if (noIdParent.getSide() == SIDE.LEFT) {
				noIdParent.setLeft(noIdChild);
			} else {
				noIdParent.setRight(noIdChild);
			}
		}

		noId.setLeft(noIdChild.getRight());

		noIdChild.setRight(noId);

		if (rotation) {
			return;
		}

		if (id == 0) {
			if (noId.getLeft() != null) {
				noId.setLeftDepth(noId.getLeft().maxDepth() + 1);
			} else {
				noId.setLeftDepth(0);
			}
			noIdChild.setRightDepth(noId.maxDepth() + 1);
			this.root = noIdChild;
		} else {
			if (noId.getLeft() == null) {
				noId.setLeftDepth(0);
			} else {
				noId.setLeftDepth(noId.getLeft().maxDepth() + 1);
			}

			if (noId.getRight() == null) {
				noId.setRightDepth(0);
			} else {
				noId.setRightDepth(noId.getRight().maxDepth() + 1);
			}
			noIdChild.setRightDepth(noId.maxDepth() + 1);
		}

		System.out.println("SIMPLE RIGHT ROTATION");

	}

	private void leftDoubleRotation(Stack<AvlNode<?>> stack, int id) {
		rightSimpleRotation(stack, id + 1, false);
		Stack<AvlNode<?>> auxStack = getStackPath(stack.peek().getNodeId());
		leftSimpleRotation(auxStack, id, false);
	}

	private void rightDoubleRotation(Stack<AvlNode<?>> stack, int id) {
		leftSimpleRotation(stack, id + 1, false);
		Stack<AvlNode<?>> pilhaAux = getStackPath(stack.peek().getNodeId());
		rightSimpleRotation(pilhaAux, id, false);
	}

	private Stack<AvlNode<?>> getStackPath(int value) {

		if (qty == 0) {
			return null;
		}

		Stack<AvlNode<?>> stack = new Stack<>();

		AvlNode<?> cursor = root;
		stack.add(root);
		while (true) {

			if (value == cursor.getNodeId()) {
				cursor.setSide(SIDE.ROOT);
				return stack;
			}

			if (value < cursor.getNodeId()) {
				cursor.setSide(SIDE.LEFT);
				cursor = cursor.getLeft();
			} else if (value > cursor.getNodeId()) {
				cursor.setSide(SIDE.RIGHT);
				cursor = cursor.getRight();
			}

			if (cursor == null) {
				stack.add(null);
				return stack;
			}
			stack.add(cursor);
		}
	}

	private void checkBalanceOut(Stack<AvlNode<?>> stack, int type, AvlNode<?> nodeParentSub, AvlNode<?> nodeSub) {

		switch (type) {
		case 0:
			getStackLeafOut(stack, true);
			balenceLeafOut(stack);
			break;
		case 1:
			stack = getStackOneChildOut(stack, true);
			balanceOneChildOut(stack);
			break;
		case 2:
			stack = getTwoChildrenStackOut(null, nodeParentSub, nodeSub);
			balanceTwoChildrenOut(stack);

		}
	}

	private void getStackLeafOut(Stack<AvlNode<?>> stack, boolean firstStep) {

		AvlNode<?> parentNode;
		AvlNode<?> nodeId;

		if (firstStep) {

			stack.pop();

			parentNode = stack.peek();

			if (parentNode.getSide() == SIDE.LEFT) {
				parentNode.setLeftDepth(0);
				if (Math.abs(parentNode.getBalance()) > 1) {
					stack.add(parentNode.getRight());
					parentNode.setSide(SIDE.RIGHT);
				}
			} else {
				parentNode.setRightDepth(0);
				if (Math.abs(parentNode.getBalance()) > 1) {
					stack.add(parentNode.getLeft());
					parentNode.setSide(SIDE.LEFT);
				}
			}
		}

		for (int i = stack.size() - 1; i > 0; i--) {
			nodeId = stack.get(i);
			parentNode = stack.get(i - 1);
			if (parentNode.getSide() == SIDE.LEFT) {
				parentNode.setLeftDepth(nodeId.maxDepth() + 1);
			} else {
				parentNode.setRightDepth(nodeId.maxDepth() + 1);
			}
		}
	}

	private Stack<AvlNode<?>> getStackOneChildOut(Stack<AvlNode<?>> stack, boolean primeiraPassagem) {

		AvlNode<?> nodeId;
		AvlNode<?> childNode;

		if (primeiraPassagem) {
			nodeId = stack.peek();
			childNode = nodeId.getLeft();
			if (childNode == null) {
				childNode = nodeId.getRight();
			}

			stack = getStackPath(childNode.getNodeId());
		}

		AvlNode<?> parentNode;
		for (int i = stack.size() - 1; i > 0; i--) {
			nodeId = stack.get(i);
			parentNode = stack.get(i - 1);
			if (parentNode.getSide() == SIDE.LEFT) {
				parentNode.setLeftDepth(nodeId.maxDepth() + 1);
			} else {
				parentNode.setRightDepth(nodeId.maxDepth() + 1);
			}
		}

		return stack;

	}

	private Stack<AvlNode<?>> getTwoChildrenStackOut(Stack<AvlNode<?>> stack, AvlNode<?> replacedParent,
			AvlNode<?> replacedNode) {

		if (stack == null) {
			stack = getStackPath(replacedParent.getNodeId());
			AvlNode<?> nodeTop = stack.peek();

			if (nodeTop == null) {
				stack.pop();
				nodeTop = stack.peek();
			}

			if (nodeTop.getLeft() == null) {
				nodeTop.setLeftDepth(0);
			}
			if (nodeTop.getRight() == null) {
				nodeTop.setRightDepth(0);
			}
		}

		AvlNode<?> nodeParent;
		AvlNode<?> noChild;
		for (int i = stack.size() - 1; i > 0; i--) {
			noChild = stack.get(i);
			nodeParent = stack.get(i - 1);
			if (nodeParent.getSide() == SIDE.LEFT) {
				nodeParent.setLeftDepth(noChild.maxDepth() + 1);
				if (nodeParent == replacedNode) {
					if (nodeParent.getRight() == null) {
						nodeParent.setRightDepth(0);
					} else {
						nodeParent.setRightDepth(nodeParent.getRight().maxDepth() + 1);
					}
				}
			} else {
				nodeParent.setRightDepth(noChild.maxDepth() + 1);
				if (nodeParent == replacedNode) {
					if (nodeParent.getLeft() == null) {
						nodeParent.setLeftDepth(0);
					} else {
						nodeParent.setLeftDepth(nodeParent.getLeft().maxDepth() + 1);
					}
				}
			}
		}

		return stack;
	}

	private void balenceLeafOut(Stack<AvlNode<?>> stack) {

		AvlNode<?> noTopo = stack.peek();

		while (true) {
			int idx = checkBalances(stack);
			if (idx == -1) {
				return;
			}
			balanceOut(stack.get(idx));
			stack = getStackPath(noTopo.getNodeId());
			getStackLeafOut(stack, false);
		}

	}

	private void balanceOneChildOut(Stack<AvlNode<?>> stack) {

		AvlNode<?> noTopo = stack.peek();

		while (true) {
			int idx = checkBalances(stack);
			if (idx == -1) {
				return;
			}
			balanceOut(stack.get(idx));
			stack = getStackPath(noTopo.getNodeId());
			getStackOneChildOut(stack, false);
		}

	}

	private void balanceTwoChildrenOut(Stack<AvlNode<?>> stack) {

		AvlNode<?> nodeTop = stack.peek();

		while (true) {
			int id = checkBalances(stack);
			if (id == -1) {
				return;
			}
			balanceOut(stack.get(id));
			stack = getStackPath(nodeTop.getNodeId());
			getTwoChildrenStackOut(stack, null, null);

		}

	}

	private int checkBalances(Stack<AvlNode<?>> stack) {
		for (int i = stack.size() - 1; i >= 0; i--) {
			if (Math.abs(stack.get(i).getBalance()) > 1) {
				return i;
			}
		}
		return -1;
	}

	private void balanceOut(AvlNode<?> nodeId) {

		Stack<AvlNode<?>> stack = new Stack<>();

		if (nodeId == this.root) {

			stack.add(nodeId);

			if (nodeId.getBalance() > 0) {

				nodeId.setSide(SIDE.RIGHT);
				stack.add(nodeId.getRight());

				if (stack.get(1).getBalance() >= 0) {
					leftSimpleRotation(stack, 0, false);
				} else {
					leftDoubleRotation(stack, 0);
				}

			} else {
				nodeId.setSide(SIDE.LEFT);
				stack.add(nodeId.getLeft());

				if (stack.get(1).getBalance() < 0) {
					rightSimpleRotation(stack, 0, false);
				} else {
					rightDoubleRotation(stack, 0);
				}
			}

			return;
		}

		stack = getStackPath(nodeId.getNodeId());
		int id = stack.size() - 1;

		if (nodeId.getBalance() > 0) {

			nodeId.setSide(SIDE.RIGHT);
			stack.add(nodeId.getRight());

			if (stack.get(1).getBalance() >= 0) {
				leftSimpleRotation(stack, id, false);
			} else {
				leftDoubleRotation(stack, id);
			}

		} else {
			nodeId.setSide(SIDE.LEFT);
			stack.add(nodeId.getLeft());

			if (stack.get(1).getBalance() < 0) {
				rightSimpleRotation(stack, id, false);
			} else {
				rightDoubleRotation(stack, id);
			}
		}
	}
}
