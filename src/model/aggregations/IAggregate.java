package model.aggregations;

import java.util.ArrayList;

import misc.IDescriptor;
import model.Satellite;
import model.container.CompositeContainer;

/**
 * Interface for all aggregation modules inherits {@link IDescriptor}
 *
 */
public interface IAggregate extends IDescriptor {

	/**
	 * Creates composite structure to store the aggregate.
	 * 
	 * @param satellitesList list of all Satellite objects
	 * @return CompositeContainer Root composite container
	 */
	public CompositeContainer aggregate(ArrayList<Satellite> satellitesList);
}