package Helper;

public class Item {
	private int Key;
	private String value;

	public Item(int Key, String value) {
		super();
		this.Key = Key;
		this.value = value;
	}

	public int getKey() {
		return Key;
	}

	public void setKey(int Key) {
		this.Key = Key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;

	}

}
