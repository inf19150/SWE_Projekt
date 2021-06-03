package model.aggregations;

import java.util.ArrayList;

import model.Satellite;
import model.containers.CompositeContainerHead;

public interface IAggregate {

	public CompositeContainerHead aggregate(ArrayList<Satellite> satellitesList);


}
