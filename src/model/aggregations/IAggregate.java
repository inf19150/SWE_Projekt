package model.aggregations;

import java.util.ArrayList;

import misc.IDescriptor;
import model.Satellite;
import model.containers.CompositeContainerHead;

/**
 * Interface for all aggregation modules inherits {@link IDescriptor}
 *
 */
public interface IAggregate extends IDescriptor {

	/**
	 * Creates composite structure to store the aggregate.
	 * 
	 * @param satellitesList list of all Satellite objects
	 * @return CompositeContainerHead Root composite container
	 */
	public CompositeContainerHead aggregate(ArrayList<Satellite> satellitesList);
}