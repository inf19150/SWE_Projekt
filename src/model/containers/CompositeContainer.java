package model.containers;

/**
 * CompositeContainer class extends {@link CompositeContainerHead} to represent
 * a hierarchical structure of any depth Consists of a value and a key value
 * that describes it.
 *
 */
public class CompositeContainer extends CompositeContainerHead {

	private String key = null;
	private String data = null;

	/**
	 * Constructor of CompositContainer, which takes a key and a value and safes
	 * them.
	 * 
	 * @param key  Key value of the composite that describes the value
	 * @param data data of the composite
	 */
	public CompositeContainer(String key, String data) {
		this.key = key;
		this.data = data;
	}

	/**
	 * Returns the value of data.
	 * 
	 * @return data
	 */
	public String getData() {
		return this.data;
	}

	/**
	 * Returns the value of key.
	 * 
	 * @return key
	 */
	public String getKey() {
		return this.key;
	}

}
