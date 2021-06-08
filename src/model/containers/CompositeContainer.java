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
	 * Constructor of CompositContainer, which takes nor key or value to represent the root
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
	public String getData() {
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

}
