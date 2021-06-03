package model;

import java.util.ArrayList;

public class Transponder {

	private int freq;
	private String pol, sat;

	private ArrayList<Channel> channels;

	public ArrayList<Channel> getChannels() {
		return channels;
	}

	private int getAmountChannels(String type) {
		return (int) this.channels.parallelStream().filter(c -> c.getType().equals(type)).count();
	}

	public int getAmountTVChannels() {
		return this.getAmountChannels("TV");
	}

	public int getAmountRadioChannels() {
		return this.getAmountChannels("R");
	}

	public String getPol() {
		return pol;
	}

	public String getSat() {
		return sat;
	}

	public int getFreq() {
		return freq;
	}

}
