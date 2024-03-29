/**
 * From https://github.com/mvyas85/Binary-Tree/tree/master
 */
package ed.vew;

import java.util.Scanner;

import ed.bst.BST;

public class BSTTest {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			BST bst = new BST();
			System.out.println("Binary Search Tree\n");
			char ch;
			do {
				System.out.println("\nBinary Search Tree Operations\n");
				System.out.println("1. Insert ");
				System.out.println("2. Delete");
				System.out.println("3. Search");
				System.out.println("4. Count nodes");
				System.out.println("5. Check empty");
				System.out.println("6. Fill BST (ToDO)");
				System.out.print(">> Option: ");

				int choice = sc.nextInt();
				switch (choice) {
				case 1:
					System.out.print("Enter integer element to insert: ");
					bst.insert(sc.nextInt());
					break;
				case 2:
					System.out.print("Enter integer element to delete: ");
					bst.delete(sc.nextInt());
					break;
				case 3:
					System.out.print("Enter integer element to search: ");
					System.out.println("Search result : " + bst.search(sc.nextInt()));
					break;
				case 4:
					System.out.println("Nodes = " + bst.countNodes());
					break;
				case 5:
					System.out.println("Empty status = " + bst.isEmpty());
					break;
				case 6:
					// TODO
					break;
				default:
					System.out.println("Wrong Entry \n ");
					break;
				}
				/* Display tree */
				System.out.print("\nPost order: ");
				bst.postorder();
				System.out.print("\nPre order: ");
				bst.preorder();
				System.out.print("\nIn order: ");
				bst.inorder();

				System.out.println("\nDo you want to continue [Y] | [N]: ");
				ch = sc.next().charAt(0);
			} while (ch == 'Y' || ch == 'y');
		}
	}
}