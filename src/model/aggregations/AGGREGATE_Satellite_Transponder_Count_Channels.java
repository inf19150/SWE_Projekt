package model.aggregations;

import java.util.ArrayList;

import model.Satellite;
import model.Transponder;
import model.containers.CompositeContainer;
import model.containers.CompositeContainerHead;

/**
 * Aggregates all Satellites with their respective count of radio and TV
 * channels implements {@link IAggregate}.
 *
 */
public class AGGREGATE_Satellite_Transponder_Count_Channels implements IAggregate {

	/**
	 * Creates Composite structure to store all satellites with their respective
	 * count of radio and TV channels.
	 * 
	 * @param satellitesList list of all Satellite objects
	 * @return topContainer root composite container
	 */
	@Override
	public CompositeContainerHead aggregate(ArrayList<Satellite> satellitesList) {

		CompositeContainerHead topContainer = new CompositeContainerHead();

		for (Satellite satellite : satellitesList) {
			CompositeContainer compositeContainer = new CompositeContainer("Satellite", satellite.getSat());
			topContainer.addHierarchy(compositeContainer);

			for (Transponder t : satellite.getTransponders()) {
				CompositeContainer transponder = new CompositeContainer("Frequency", t.getFreq() + "");
				compositeContainer.addHierarchy(transponder);
				transponder.addHierarchy(new CompositeContainer("Radio", t.getAmountRadioChannels() + ""));
				transponder.addHierarchy(new CompositeContainer("TV", t.getAmountTVChannels() + ""));
			}
		}
		return topContainer;
	}

	/**
	 * Returns decent name of class.
	 * 
	 * @return Decent name of class
	 */
	@Override
	public String getName() {
		return "Count Channel per Transponder";
	}

}
