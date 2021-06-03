package model.containers;

public class CompositeContainer extends CompositeContainerHead {

	private String key = null;
	private String data = null;

	public CompositeContainer(String key, String data) {
		this.key = key;
		this.data = data;
	}

	public String getData() {
		return this.data;
	}

	public String getKey() {
		return this.key;
	}

}
