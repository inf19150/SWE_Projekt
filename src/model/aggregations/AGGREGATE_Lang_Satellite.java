package model.aggregations;

import java.util.ArrayList;

import model.Channel;
import model.Satellite;
import model.Transponder;
import model.containers.CompositeContainer;
import model.containers.CompositeContainerHead;

/**
 * Aggregates all Satellites that have german channels.
 *
 */
public class AGGREGATE_Lang_Satellite implements IAggregate {

	/**
	 * Creates Composite structure to store all satellites that have german
	 * channels.
	 * 
	 * @param satellitesList list of all Satellite objects
	 * @return topContainer root composite container
	 */
	@Override
	public CompositeContainerHead aggregate(ArrayList<Satellite> satellitesList) {

		CompositeContainerHead topContainer = new CompositeContainerHead();
		CompositeContainer langContainer = new CompositeContainer("Language", "GER");

		topContainer.addHierarchy(langContainer);

		for (Satellite satellite : satellitesList) {
			outerloop: for (Transponder transponder : satellite.getTransponders()) {
				for (Channel channel : transponder.getChannels()) {
					if (isGerman(channel)) {
						langContainer.addHierarchy(new CompositeContainer("Satellite", satellite.getSat()));
						break outerloop;
					}
				}
			}
		}
		return topContainer;
	}

	/**
	 * Returns whether the channel is a german channel.
	 * 
	 * @param c Channel object
	 * @return whether the channel is a radio channel or not
	 */
	private boolean isGerman(Channel c) {
		return c.getLanguage().contains("ger");
	}

	/**
	 * Returns decent name of class.
	 * 
	 * @return Decent name of class
	 */
	@Override
	public String getName() {
		return "Satellites with german Channels";
	}
}
