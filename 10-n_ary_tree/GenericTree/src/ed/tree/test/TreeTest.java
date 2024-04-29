package ed.tree.test;

import java.util.Scanner;
import ed.tree.GenericTree;
import ed.tree.Node;

public class TreeTest {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("ROOT ELEMENT:");
		Node root = newNode();
		GenericTree tree = new GenericTree(root);

		while (true) {

			System.out.println("\n\n");
			System.out.println("[0] - exit");
			System.out.println("[1] - insert node");
			System.out.println("[2] - find node");
			System.out.println("[3] - print tree");
			System.out.println("[4[ - create test tree");
			System.out.print("Option: ");

			int opc = sc.nextInt();

			if (opc == 0) {
				break;
			} else if (opc == 1) {
				System.out.print("Parent node ID:");
				sc.nextLine();
				String parentId = sc.nextLine();
				Node e = newNode();
				if (tree.insert(parentId, e)) {
					System.out.println("Done!");
				} else {
					System.err.println("Fail");
				}

			} else if (opc == 2) {
				System.out.print("Node ID to find: ");
				sc.nextLine();
				String cod = sc.nextLine();
				Node e = tree.findNode(cod);
				tree.printNode(e, true);
			} else if (opc == 3) {
				sc.nextLine();
				System.out.print("Enter the code of the root element of the branch to be printed. Hint \".\" for root");
				String code = sc.nextLine().trim();
				if (".".equals(code)) {
					code = null;
				}
				tree.print(code);
			} else if (opc == 4) {
				Node e = new Node("A", "Node A", "A description");
				tree = new GenericTree(e);
				createTree(tree);
			}

		}
		sc.close();

	}

	private static Node newNode() {
		Scanner sc = new Scanner(System.in);
		System.out.println("New node");
		System.out.print("Node ID: ");
		String id = sc.nextLine();
		System.out.print("Node Name: ");
		String name = sc.nextLine();
		System.out.print("Node desc: ");
		String descr = sc.nextLine();
		sc.close();
		return new Node(id, name, descr);
	}

	private static void createTree(GenericTree a) {
		if (a.getQty() > 1) {
			System.out.println("⚠ ️TREE CONTAINS ELEMENTS OTHER THAN THE ROOT ELEMENT!");
			return;
		}
		Node root = a.findNode("A");
		if (root == null) {
			System.out.println("Can't find node \"A\"");
			return;
		}

		a.insert("A", new Node("A.2", "Element A.2", " Element A.2"));
		a.insert("A.2", new Node("A.2.5", "Element A.2.5", " Element A.2.5"));

		a.insert("A.2", new Node("A.2.4", "Element A.2.4", " Element A.2.4"));
		a.insert("A.2.4", new Node("A.2.4.2", "Element A.2.4.2", " Element A.2.4.2"));
		a.insert("A.2.4.2", new Node("A.2.4.2.2", "Element A.2.4.2.2", " Element A.2.4.2.2"));
		a.insert("A.2.4.2", new Node("A.2.4.2.1", "Element A.2.4.2.1", " Element A.2.4.2.1"));
		a.insert("A.2.4", new Node("A.2.4.1", "Element A.2.4.1", " Element A.2.4.1"));

		a.insert("A.2", new Node("A.2.3", "Element A.2.3", " Element A.2.3"));
		a.insert("A.2", new Node("A.2.2", "Element A.2.2", " Element A.2.2"));
		a.insert("A.2", new Node("A.2.1", "Element A.2.1", " Element A.2.1"));
		a.insert("A.2.4.2.2", new Node("A.2.4.2.2.1", "Element A.2.4.2.2.1", " Element A.2.4.2.2.1"));
		a.insert("A.2.4.2.2", new Node("A.2.4.2.2.2", "Element A.2.4.2.2.2", " Element A.2.4.2.2.2"));
		a.insert("A.2.4.2", new Node("A.2.4.2.3", "Element A.2.4.2.3", " Element A.2.4.2.3"));

		a.insert("A", new Node("A.1", "Element A.1", " Element A.1"));

		a.insert("A.1", new Node("A.1.2", "Element A.1.2", " Element A.1.2"));
		a.insert("A.1.2", new Node("A.1.2.3", "Element A.1.2.3", " Element A.1.2.3"));
		a.insert("A.1.2.3", new Node("A.1.2.3.2", "Element A.1.2.3.2", " Element A.1.2.3.2"));
		a.insert("A.1.2.3", new Node("A.1.2.3.1", "Element A.1.2.3.1", " Element A.1.2.3.1"));
		a.insert("A.1.2", new Node("A.1.2.2", "Element A.1.2.2", " Element A.1.2.2"));
		a.insert("A.1.2", new Node("A.1.2.1", "Element A.1.2.1", " Element A.1.2.1"));

		a.insert("A.1", new Node("A.1.1", "Element A.1.1", " Element A.1.1"));
		a.insert("A.1.1", new Node("A.1.1.2", "Element A.1.1.2", " Element A.1.1.2"));
		a.insert("A.1.1", new Node("A.1.1.1", "Element A.1.1.1", " Element A.1.1.1"));

	}

}
