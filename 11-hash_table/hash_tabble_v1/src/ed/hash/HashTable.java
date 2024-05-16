package ed.hash;

public class HashTable {

	private final Element[] hashTable;
	private final boolean repeat;
	private int qty;

	public HashTable(int size, boolean repeat) {
		this.qty = 0;
		this.repeat = repeat;
		this.hashTable = new Element[size];
	}

	private int hash(int value) {
		return value % hashTable.length;
	}

	public HashOperation insert(Element e) {

		if (qty == hashTable.length) {
			return HashOperation.TABLE_FULL;
		}

		int h = hash(e.getValue());

		if (!repeat) {

			for (int i = h; i < hashTable.length; i++) {
				if (hashTable[i] != null && hashTable[i].getValue() == e.getValue()) {
					return HashOperation.ELEMENT_REPEATED;
				}
			}

			for (int i = 0; i < h; i++) {
				if (hashTable[i] != null && hashTable[i].getValue() == e.getValue()) {
					return HashOperation.ELEMENT_REPEATED;
				}
			}
		}

		boolean inserted = false;

		for (int i = h; i < hashTable.length; i++) {
			if (hashTable[i] == null) {
				hashTable[i] = e;
				inserted = true;
				break;
			}
		}

		if (!inserted) {
			for (int i = 0; i < h; i++) {
				if (hashTable[i] == null) {
					hashTable[i] = e;
					inserted = true;
					break;
				}
			}
		}

		qty++;
		return HashOperation.SUCCESS;

	}

	public int findElement(int value) {

		int h = hash(value);

		for (int i = h; i < hashTable.length; i++) {
			if (hashTable[i] != null && hashTable[i].getValue() == value) {
				return i;
			}
		}

		for (int i = 0; i < h; i++) {
			if (hashTable[i] != null && hashTable[i].getValue() == value) {
				return i;
			}
		}
		return -1;
	}

	public Element remove(int value) {

		int i = findElement(value);
		if (i == -1) {
			return null;
		}

		Element e = hashTable[i];
		hashTable[i] = null;
		qty--;
		return e;
	}

	public String printTable() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < hashTable.length; i++) {
			Element e = hashTable[i];
			if (e == null) {
				sb.append("\n").append(i).append(" | --- empty");
			} else {
				sb.append("\n").append(i).append(" | --- ").append(e.toString());
			}
		}
		return sb.toString();
	}
	
	public Element getElementFromPosition(int position) {
		return this.hashTable[position];
	}

}
