package misc;

/**
 * Interface for both aggregation and output modules to add functionality
 * regarding name and description.
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
	 * Returns description of implemented module.
	 * 
	 * @return Description
	 */
	public String getDescription();
}
