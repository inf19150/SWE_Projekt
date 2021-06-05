package view.output;

import misc.IDescriptor;
import model.containers.CompositeContainerHead;

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
	public void output(CompositeContainerHead container);
}
