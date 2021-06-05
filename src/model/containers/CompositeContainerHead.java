package model.containers;

import java.util.ArrayList;

/**
 * CompositeContainer class extends to represent the root of a hierarchical
 * structure of any depth.
 * 
 */
public class CompositeContainerHead {

	private ArrayList<CompositeContainer> compositums = new ArrayList<CompositeContainer>();

	/**
	 * Constructor of CompositeContainerHead.
	 */
	public CompositeContainerHead() {
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
	 * Returns number of branches/leaves from this composite.
	 * 
	 * @return Number of branches/leaves from this composite.
	 */
	public int getSize() {
		return this.compositums.size();
	}

}
