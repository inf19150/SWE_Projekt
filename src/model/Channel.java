package model;

/**
 * Class to represent Channels with the for this project needed information
 * (could be extended).
 *
 */
public class Channel {

	private String type, name, res, a_pid;

	/**
	 * Returns the language.
	 * 
	 * @return language
	 */
	public String getLanguage() {
		return this.a_pid;
	}

	/**
	 * Returns the type (whether radio or TV channel).
	 * 
	 * @return type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns the resolution.
	 * 
	 * @return resolution
	 */
	public String getRes() {
		return this.res;
	}

}
