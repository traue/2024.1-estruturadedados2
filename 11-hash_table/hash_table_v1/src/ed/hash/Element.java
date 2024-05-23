package ed.hash;

public class Element {

	private int value;
	private String name;
	private String data;

	public Element(int value, String name, String data) {
		this.value = value;
		this.name = name;
		this.data = data;
	}

	public Element() {
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		String ret = this.getValue() + " | " + this.getName();
		if (!data.isEmpty()) {
			ret += " | " + this.getData();
		}
		return ret;
	}

}
