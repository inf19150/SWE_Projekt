package misc;

/**
 * Interface for both aggregation and output modules
 *
 */
public interface IDescriptor {

	/**
	 * Returns decent name of class.
	 * 
	 * @return Decent name of class
	 */
	public String getName();

	/**
	 * Returns description of implemented Aggregation.
	 * 
	 * @return Description
	 */
	public String getDescription();
}
