package ed;

import java.util.Stack;

public class BinarySearchTree {

	private Node<?> root;
	private int qty;

	public Node<?> getRoot() {
		return root;
	}

	public int getQty() {
		return qty;
	}

	// inserção não recursiva
	public boolean insert(Node<?> Node) {

		if (root == null) {
			root = Node;
			qty = 1;
			return true;
		}

		Node<?> cursor = root;

		while (true) {
			// verifica se há existe nó com o mesmo id
			if (Node.getId() == cursor.getId()) {
				return false;
			}

			// caminho à esquerda
			if (Node.getId() < cursor.getId()) {
				if (cursor.getLeft() == null) {
					cursor.setLeft(Node);
					qty++;
					return true;
				}
				cursor = cursor.getLeft();
				continue;
			}

			// caminho à direita
			if (Node.getId() > cursor.getId()) {
				if (cursor.getRight() == null) {
					cursor.setRight(Node);
					qty++;
					return true;
				}
				cursor = cursor.getRight();
			}
		}
	}

	public Node<?> remove(int id) {

		// verifica se a árvore está vazia
		if (qty == 0) {
			return null;
		}

		// verifica se o nó é a root e não há filhos
		if (qty == 1 && id == root.getId()) {
			Node<?> Node = root;
			root = null;
			qty = 0;
			return Node;
		}

		Node<?>[] Node = findNodeById(id);

		if (Node[1] == null) {
			return null;
		}

		Node<?> parentNode = Node[0]; // nó pai do nó a ser removido
		Node<?> nodeId = Node[1]; // nó a ser removido

		// filhos do nó a ser removido
		Node<?> leftIdChild = nodeId.getLeft();
		Node<?> rightIdChild = nodeId.getRight();

		// 1º caso: O nó é folha
		if (leftIdChild == null && rightIdChild == null) {
			if (nodeId.getId() < parentNode.getId()) { // nó a esquerda do pai
				parentNode.setLeft(null);
			} else {
				parentNode.setRight(null);
			}
		}
		// 2º caso: nó possui apenas um filho
		else if (leftIdChild == null && rightIdChild != null || leftIdChild != null && rightIdChild == null) {

			// caso nó removido seja raiz
			if (nodeId == root) {
				if (leftIdChild != null) {
					root = leftIdChild;
				} else {
					root = rightIdChild;
				}
			}
			// nó não é raiz
			else {
				if (nodeId.getId() > parentNode.getId()) { // nó removido é filho da direita
					if (leftIdChild != null) {
						parentNode.setRight(leftIdChild);
					} else {
						parentNode.setRight(rightIdChild);
					}
				} else { // nó removido é filho da esquerda
					if (leftIdChild != null) {
						parentNode.setLeft(leftIdChild);
					} else {
						parentNode.setLeft(rightIdChild);
					}
				}
			}

		}
		// Caso 3: o nó possui dois filho
		else if (leftIdChild != null && rightIdChild != null) {

			// verifica se é nó root
			if (nodeId == root) {
				Node<?>[] leftNode = getLeftBiggerValue(root);
				Node<?>[] rightNode = getRightBiggerValue(root);

				Node<?>[] auxNode; // nó substituto
				if (Math.abs(root.getId() - leftNode[1].getId()) > Math.abs(root.getId() - rightNode[1].getId())) {
					auxNode = leftNode;
				} else {
					auxNode = rightNode;
				}

				remove(auxNode[1].getId());

				auxNode[1].setLeft(root.getLeft());
				auxNode[1].setRight(root.getRight());
				root.setLeft(null);
				root.setRight(null);
				Node<?> ret = root;
				root = auxNode[1];
				qty--;
				return ret;
			}

			if (nodeId.getId() > parentNode.getId()) { // o nó removido é filho da direita

				Node<?>[] biggerLeft = getLeftBiggerValue(nodeId);
				parentNode.setRight(biggerLeft[1]);
				biggerLeft[1].setRight(nodeId.getRight());

				if (biggerLeft[0] != nodeId) {
					biggerLeft[1].setLeft(nodeId.getLeft());
					biggerLeft[0].setRight(null);
				}
			} else { // o nó removido é filho da esquerda

				Node<?>[] smallerRight = getRightBiggerValue(nodeId);
				parentNode.setLeft(smallerRight[1]);
				smallerRight[1].setLeft(nodeId.getLeft());

				if (smallerRight[0] != nodeId) {
					smallerRight[1].setRight(nodeId.getRight());
					smallerRight[0].setLeft(null);
				}
			}
		}

		qty--;
		return nodeId;

	}

	// procura um nó pelo ID
	public Node<?>[] findNodeById(int id) {

		Node<?>[] Node = new Node<?>[2];

		Node[1] = root;

		while (true) {

			if (Node[1] == null || Node[1].getId() == id) {
				return Node;
			}

			Node[0] = Node[1]; // nó analisado passa a condição de pai

			if (id < Node[0].getId()) {
				Node[1] = Node[0].getLeft();
			} else {
				Node[1] = Node[0].getRight();
			}
		}
	}

	// Procura o maior ID da subrárvore da esquerda
	private Node<?>[] getLeftBiggerValue(Node<?> Node) {

		Node<?>[] auxNode = new Node<?>[2];

		auxNode[0] = Node;
		auxNode[1] = Node.getLeft(); // primeiro nó da subarvore esquerda

		// o nó maior está no ramo mais a direita
		while (auxNode[1].getRight() != null) {
			auxNode[0] = auxNode[1];
			auxNode[1] = auxNode[1].getRight();
		}

		return auxNode;

	}

	// Procura o maior ID da subrárvore da direita
	private Node<?>[] getRightBiggerValue(Node<?> Node) {

		Node<?>[] auxNode = new Node<?>[2];

		auxNode[0] = Node;
		auxNode[1] = Node.getRight(); // primeiro nó da sub direita

		// o nó menor estó no ramo mais a esquerda da subarvore
		while (auxNode[1].getLeft() != null) {
			auxNode[0] = auxNode[1];
			auxNode[1] = auxNode[1].getLeft();
		}

		return auxNode;
	}

	// impresões iterativas (em teste)

	public void inOrderIterative() {

		if (qty == 0) {
			System.err.println("Tree is empty!");
		} else {
			Stack<Node<?>> stack = new Stack<>();
			Node<?> cursor = this.root;
			cursor.side = 'X';
			stack.push(cursor);
			boolean checkRightSide = true;

			boolean doWhile1 = true;
			while1: while (doWhile1) {
				// caminhar tudo à esquerda e imprimir o último
				if (cursor.getLeft() != null) {
					while (cursor.getLeft() != null) {
						cursor = cursor.getLeft();
						cursor.side = 'E';
						stack.push(cursor);
					}
				}
				System.out.print(cursor.getId() + " ");

				while (true) {

					// verifica se há ramo a direita
					if (checkRightSide && cursor.getRight() != null) {
						cursor = cursor.getRight();
						cursor.side = 'D';
						stack.push(cursor);
						checkRightSide = true;
						// volta ao inicio do laço para processar todo a árvore esquerda deste nó
						continue while1;
					}

					char childSide = stack.pop().side;
					if (stack.empty()) {
						doWhile1 = false;
						break while1;
					}

					cursor = stack.peek();
					if (childSide == 'E') {
						System.out.print(cursor.getId() + " ");
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
			System.err.println("Tree is empty!");
		} else {

			Stack<Node<?>> stack = new Stack<>();
			Node<?> cursor = this.root;
			cursor.side = 'X';
			stack.push(cursor);
			System.out.print(cursor.getId() + " ");

			while (true) {

				while (cursor.getLeft() != null) {
					cursor = cursor.getLeft();
					cursor.side = 'E';
					stack.push(cursor);
					System.out.print(cursor.getId() + " ");
				}

				if (cursor.getRight() != null) {
					cursor = cursor.getRight();
					cursor.side = 'D';
					stack.push(cursor);
					System.out.print(cursor.getId() + " ");
					continue;
				}

				while (!stack.empty()) {
					char childSide = stack.pop().side;
					if (stack.empty()) {
						break;
					}

					cursor = stack.peek();

					if (childSide == 'D') {
						continue;
					}

					if (cursor.getRight() != null) {
						cursor = cursor.getRight();
						cursor.side = 'D';
						stack.push(cursor);
						System.out.print(cursor.getId() + " ");
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
			Stack<Node<?>> stack = new Stack<>();
			Node<?> cursor = this.root;
			cursor.side = 'X';
			stack.push(cursor);
			boolean checkRightSide = true;

			while1: while (true) {

				// caminho à esquerda
				if (cursor.getLeft() != null) {
					while (cursor.getLeft() != null) {
						cursor = cursor.getLeft();
						cursor.side = 'E';
						stack.push(cursor);
					}
				}

				while (true) {
					if (checkRightSide) {
						if (cursor.getRight() != null) {
							cursor = cursor.getRight();
							cursor.side = 'D';
							stack.push(cursor);
							checkRightSide = true;
							continue while1;
						} else {
							System.out.print(cursor.getId() + " ");
						}
					}

					char childSide = stack.pop().side;
					if (stack.empty()) {
						break while1;
					}

					cursor = stack.peek();
					if (childSide == 'D') {
						System.out.print(cursor.getId() + " ");
						checkRightSide = false;
					} else {
						checkRightSide = true;
					}
				}

			}
			System.out.println();
		}
	}

	// impressões recursiva

	public void inOrderRecursive(Node<?> Node) {
		if (Node != null) {
			inOrderRecursive(Node.getLeft());
			System.out.print(Node.getId() + " ");
			inOrderRecursive(Node.getRight());
		}
	}

	public void preOrderRecursive(Node<?> Node) {
		if (Node != null) {
			System.out.print(Node.getId() + " ");
			preOrderRecursive(Node.getLeft());
			preOrderRecursive(Node.getRight());
		}
	}

	public void postOrderRecursive(Node<?> Node) {
		if (Node != null) {
			postOrderRecursive(Node.getLeft());
			postOrderRecursive(Node.getRight());
			System.out.print(Node.getId() + " ");
		}
	}

	public Node<?> biggerValue() {
		if (qty == 0) {
			return null;
		}
		Node<?> cursor = root;
		while (cursor.getRight() != null) {
			cursor = cursor.getRight();
		}
		return cursor;
	}

	public Node<?> smallerValue() {
		if (qty == 0) {
			return null;
		}
		Node<?> cursor = root;
		while (cursor.getLeft() != null) {
			cursor = cursor.getLeft();
		}
		return cursor;
	}

	/**
	 * PRECISA REVISAR
	 * Inserção recursiva Baseado em:
	 * https://www.scaler.com/topics/binary-tree-implementation-in-java/
	 */
	public boolean insert(Node<?> branch, Node<?> nodeId) {
		if (branch == null) {
			root = nodeId;
			qty++;
			return true;
		}
		if (nodeId.getId() < branch.getId()) {
			if (branch.getLeft() != null) {
				insert(branch.getLeft(), nodeId);
			} else {
				branch.setLeft(nodeId);
				qty++;
				return true;
			}
		} else if (nodeId.getId() > branch.getId()) {
			if (branch.getRight() != null) {
				insert(branch.getRight(), nodeId);
			} else {
				branch.setRight(nodeId);
				qty++;
				return true;
			}
		}
		return false;
	}
}
