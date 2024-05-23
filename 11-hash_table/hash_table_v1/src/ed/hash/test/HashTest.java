package ed.hash.test;

import java.util.Scanner;

import ed.hash.Element;
import ed.hash.HashTable;

public class HashTest {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("\n*** WELCOME TO HASH TABLE TEST PROGRAM ***\n\n");

		System.out.print("Table size: ");
		int size = sc.nextInt();

		System.out.print("Repeat alowed [1] yes | [2] no: ");
		boolean repeat = sc.nextInt() == 1;

		HashTable ht = new HashTable(size, repeat);

		while (true) {

			printMenu();

			System.out.print("Option: ");
			int opt = sc.nextInt();

			if (opt == 9) {
				break;

			} else if (opt == 1) {

				Element e = createElement();
				if (e == null) {
					System.err.println("Invalid element!");
					continue;
				}
				switch (ht.insert(e)) {
				case ELEMENT_REPEATED:
					System.err.println("Element repeated!!");
					break;
				case TABLE_FULL:
					System.err.println("Table full!!");
					break;
				case SUCCESS:
					System.out.println("Element inserted!");
					break;
				}

			} else if (opt == 2) {
				System.out.print("Element VALUE to find: ");
				int value = sc.nextInt();
				int pos = ht.findElement(value);
				if (pos == -1) {
					System.out.println("Could not find!");
				} else {
					System.out.println("Element found at position: " + pos);
					System.out.println("Element info: " + ht.getElementFromPosition(pos).toString());
				}
			} else if (opt == 3) {
				System.out.print("Element VALUE to extract: ");
				int value = sc.nextInt();
				Element e = ht.remove(value);
				if (e == null) {
					System.err.println("Could not find the element!");
				} else {
					System.out.println("Element removed!");
					System.out.println("Element info: " + e.toString());
				}
			} else if (opt == 4) {
				System.out.println(ht.printTable());
			}
		}

		sc.close();
	}

	private static void printMenu() {
		System.out.println("\n *** OPTIONS ***\n");
		System.out.println("[1] Insert element");
		System.out.println("[2] Find element (no extraction)");
		System.out.println("[3] Get element (extract)");
		System.out.println("[4] Print HashTable");
		System.out.println("[9] Exit");
		System.out.println("******************************");
	}

	private static Element createElement() {

		try {

			Scanner sc = new Scanner(System.in);
			System.out.print("> Element VALUE: ");
			int value = sc.nextInt();

			sc.nextLine();

			System.out.print("> Element NAME: ");
			String name = sc.nextLine();

			System.out.print("> Element DATA: ");
			String data = sc.nextLine();

			Element e = new Element(value, name, data);

			return e;

		} catch (Exception e) {
			return null;
		}
	}

}
