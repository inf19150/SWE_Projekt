package aggregations;

import java.util.ArrayList;

import model.Satellite;

public interface IAggregate {

	public CompositeContainerHead aggregate(ArrayList<Satellite> satellitesList);

}
