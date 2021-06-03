package model.aggregations;

import java.util.ArrayList;

import misc.IDescriptor;
import model.Satellite;
import model.containers.CompositeContainerHead;

public interface IAggregate extends IDescriptor {

	public CompositeContainerHead aggregate(ArrayList<Satellite> satellitesList);
}
