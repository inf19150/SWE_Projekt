package view.output;

import misc.IDescriptor;
import model.container.CompositeContainer;

/**
 * Interface for all output modules inherits {@link IDescriptor}
 *
 */
public interface IOutput extends IDescriptor {

	/**
	 * Outputs the contents of the composite structure
	 * 
	 * @param container Root composite container
	 */
	public void output(CompositeContainer container);

	/**
	 * Resets the Output module object to its initial state.
	 * 
	 */
	public void reset();

}
