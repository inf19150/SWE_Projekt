package model.containers;

import java.util.ArrayList;

public class CompositeContainerHead {

	private ArrayList<CompositeContainer> compositums = new ArrayList<CompositeContainer>();

	public CompositeContainerHead() {
	}

	public void addHierarchy(CompositeContainer container) {
		this.compositums.add(container);
	}

	public ArrayList<CompositeContainer> getCompositums() {
		return this.compositums;
	}
	
	public int getSize() {
		return this.compositums.size();
	}

}
