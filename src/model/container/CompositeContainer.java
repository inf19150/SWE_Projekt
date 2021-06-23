package model.container;

import java.util.ArrayList;

/**
 * CompositeContainer class to represent a hierarchical structure of any depth.
 * Consists of an optional key, and value that describes it.
 * 
 */
public class CompositeContainer {

	private String key, value;

	private ArrayList<CompositeContainer> compositums = new ArrayList<CompositeContainer>();

	/**
	 * Constructor of CompositContainer, which takes a key and a value and safes
	 * them.
	 * 
	 * @param key   Key of the composite that describes the value
	 * @param value value of the composite
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
	 * Returns the value.
	 * 
	 * @return value
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * Returns the key.
	 * 
	 * @return key
	 */
	public String getKey() {
		return this.key;
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
