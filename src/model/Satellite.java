package model;

import java.util.ArrayList;

public class Satellite {

	private String sat;
	private ArrayList<Transponder> transponders;

	/**
	 * @param sat
	 */
	public Satellite(String sat) {
		this.sat = sat;
		this.transponders = new ArrayList<Transponder>();
	}

	/**
	 * @param t
	 */
	public Satellite(Transponder t) {
		this(t.getSat());
		this.addTransponder(t);
	}

	public void addTransponder(Transponder toAdd) {
		this.transponders.add(toAdd);
	}

	public String getSat() {
		return sat;
	}

	public ArrayList<Transponder> getTransponders() {
		return transponders;
	}
	
}
