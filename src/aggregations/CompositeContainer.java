package aggregations;

import java.util.ArrayList;

public class CompositeContainer {

	private String data = "";

	private ArrayList<CompositeContainer> compositums = new ArrayList<CompositeContainer>();

	public void addHierarchy(CompositeContainer container) {
		this.compositums.add(container);
	}

	public CompositeContainer(String data) {
		this.data = data;
	}

	public void set(String data) {
		this.data = data;
	}

	public String getData() {
		return data;
	}
		
	public ArrayList<CompositeContainer> getCompositums() {
		return compositums;
	}

}
