package model;

public class Channel {

	private int sid;
	private String type, name, res, a_pid, compression;

	public String getLanguage() {
		return this.a_pid.split(" ")[1];
	}

}
