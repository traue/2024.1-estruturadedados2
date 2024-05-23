package ed.hash;

import java.util.LinkedList;

public class HashTable {

	private final LinkedList<Element>[] hashTable;
	private final boolean repeat;
	private int qty;

	public HashTable(int size, boolean repeat) {
		this.hashTable = new LinkedList[size];
		this.repeat = repeat;
		this.qty = 0;

		for (int i = 0; i < size; i++) {
			this.hashTable[i] = new LinkedList<>();
		}
	}

	public int hash(int value) {
		return value % this.hashTable.length;
	}

	public int getElementIndex(int value) {
		int hash = hash(value);

		if (this.hashTable[hash].isEmpty()) {
			return -1;
		}

		for (int i = 0; i < this.hashTable[hash].size(); i++) {
			if (this.hashTable[hash].get(i).getValue() == value) {
				return i;
			}
		}

		return -1;
	}

	public HashOperation insert(Element e) {
		int value = e.getValue();
		int hash = hash(value);

		if (!repeat) {
			if (getElementIndex(value) >= 0) {
				return HashOperation.ELEMENT_REPEATED;
			}
		}

		this.hashTable[hash].add(e);
		qty++;
		return HashOperation.SUCCESS;
	}

	public Element removeElement(int value) {
		int i = getElementIndex(value);

		if (i == -1) {
			return null;
		}

		int hash = hash(value);
		Element e = hashTable[hash].remove(i);
		qty--;
		return e;
	}

	public String printTable() {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < this.hashTable.length; i++) {

			sb.append("Index: " + i + ":");
			sb.append("\n");

			if (this.hashTable[i].isEmpty()) {
				sb.append(" > EMPTY\n");
				continue;
			}

			for (int j = 0; j < this.hashTable[i].size(); j++) {
				Element e = this.hashTable[i].get(j);
				sb.append(" > [" + i + "]: [" + j + "] -> " + e.toString());
				sb.append("\n");
			}
			
		}
		return sb.toString();
	}

}
