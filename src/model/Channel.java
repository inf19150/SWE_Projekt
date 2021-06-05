package model;

public class Channel {

	private int sid;
	private String type, name, res, a_pid, compression;

	/**
	 * 
	 * @return the language
	 */
	public String getLanguage() {
		return this.a_pid;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return the a_pid
	 */
	public String getA_pid() {
		return this.a_pid;
	}

	/**
	 * @return the res
	 */
	public String getRes() {
		return this.res;
	}

	/**
	 * @return the compression
	 */
	public String getCompression() {
		return this.compression;
	}

	/**
	 * 
	 * @return the sid
	 */
	public int getSid() {
		return this.sid;
	}

}
