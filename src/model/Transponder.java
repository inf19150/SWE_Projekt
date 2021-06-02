package model;

import java.util.ArrayList;

public class Transponder {

	// private boolean isHorizontallyPolarized;

	private int freq;
	private String pol, sat;

	private ArrayList<Channel> channels;

	public ArrayList<Channel> getChannels() {
		return channels;
	}

	public String getPol() {
		return pol;
	}

	public String getSat() {
		return sat;
	}

}
