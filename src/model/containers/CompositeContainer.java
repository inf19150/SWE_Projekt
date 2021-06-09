package model.containers;

import java.util.ArrayList;

/**
 * CompositeContainer class to represent a hierarchical structure of any depth
 * Consists of an optional key and value that describes it.
 * 
 */
public class CompositeContainer {

	private String key, value;

	private ArrayList<CompositeContainer> compositums = new ArrayList<CompositeContainer>();

	/**
	 * Constructor of CompositContainer, which takes a key and a value and safes
	 * them.
	 * 
	 * @param key  Key value of the composite that describes the value
	 * @param data data of the composite
	 */
	public CompositeContainer(String key, String value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * Constructor of CompositContainer, which takes nor key or value to represent
	 * the root
	 */
	public CompositeContainer() {
		this.key = null;
		this.value = null;
	}

	/**
	 * Adds a branch/leave to the composite.
	 * 
	 * @param container CompositeContainer
	 */
	public void addHierarchy(CompositeContainer container) {
		this.compositums.add(container);
	}

	/**
	 * Returns a list of all branches/leaves from this composite.
	 * 
	 * @return List of all branches/leaves from this composite.
	 */
	public ArrayList<CompositeContainer> getCompositums() {
		return this.compositums;
	}

	/**
	 * Returns the value of data.
	 * 
	 * @return data
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * Returns the value of key.
	 * 
	 * @return key
	 */
	public String getKey() {
		return this.key;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((compositums == null) ? 0 : compositums.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompositeContainer other = (CompositeContainer) obj;
		if (compositums == null) {
			if (other.compositums != null)
				return false;
		} else if (!compositums.equals(other.compositums))
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
