package aggregations;

import java.util.ArrayList;

import model.Satellite;

public interface IAggregate {

	public CompositeContainer aggregate(ArrayList<Satellite> satellitesList);

}
