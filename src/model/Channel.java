package model;

public class Channel {

	private int sid;
	private String type, name, res, a_pid, compression;

	public String getLanguage() {
		return this.a_pid;
	}
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the a_pid
	 */
	public String getA_pid() {
		return a_pid;
	}

}
