package model;

import java.util.ArrayList;

/**
 * Class to represent a Satellite with the for this project needed information
 * (could be extended).
 *
 */
public class Satellite {

	private String sat;
	private ArrayList<Transponder> transponders = new ArrayList<Transponder>();

	/**
	 * Constructor for Satellites, adds a transponder to the list of transponders
	 * and sets the name of the satellite.
	 * 
	 * @param t Transponder object
	 */
	public Satellite(Transponder t) {
		this.sat = t.getSat();
		this.addTransponder(t);
	}

	/**
	 * Adds a transponder to the transponders list.
	 * 
	 * @param toAdd Transponder to add
	 */
	public void addTransponder(Transponder toAdd) {
		this.transponders.add(toAdd);
	}

	/**
	 * Returns the name of the Satellite
	 * 
	 * @return name of satellite
	 */
	public String getSat() {
		return sat;
	}

	/**
	 * Returns list of transponders.
	 * 
	 * @return list of transponders
	 */
	public ArrayList<Transponder> getTransponders() {
		return transponders;
	}

}
