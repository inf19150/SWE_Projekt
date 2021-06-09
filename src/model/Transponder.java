package model;

import java.util.ArrayList;

/**
 * Class to represent a Transponder with the for this project needed information
 * (could be extended).
 *
 */
public class Transponder {

	private int freq;
	private String sat;

	private ArrayList<Channel> channels;

	/**
	 * Returns a list of channels.
	 * 
	 * @return List of channels
	 */
	public ArrayList<Channel> getChannels() {
		return channels;
	}

	/**
	 * Returns the amount of channel this transponder holds for a specific type.
	 * 
	 * @param type in question
	 * @return number of channels of the type in question
	 */
	private int getAmountChannels(String type) {
		return (int) this.channels.parallelStream().filter(c -> c.getType().equals(type)).count();
	}

	/**
	 * Returns number of TV channels by calling getAmountChannels() with its type.
	 * 
	 * @return number of TV channels
	 */
	public int getAmountTVChannels() {
		return this.getAmountChannels("TV");
	}

	/**
	 * Returns number of radio channels by calling getAmountChannels() with its
	 * type.
	 * 
	 * @return number of radio channels
	 */
	public int getAmountRadioChannels() {
		return this.getAmountChannels("R");
	}

	/**
	 * Returns the satellite the transponder belongs to.
	 * 
	 * @return satellite
	 */
	public String getSat() {
		return sat;
	}

	/**
	 * Returns the frequency of the transponder.
	 * 
	 * @return frequency
	 */
	public int getFreq() {
		return freq;
	}
}
