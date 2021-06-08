package model.aggregations;

import java.util.ArrayList;

import model.Channel;
import model.Satellite;
import model.Transponder;
import model.containers.CompositeContainer;

/**
 * Aggregates all Satellites that have german channels.
 *
 */
public class AGGREGATE_Lang_Satellite implements IAggregate {

	private static final String NAME = "Satellites with german Channels";
	private static final String DESCRIPTION = "Description";
	
	/**
	 * Creates Composite structure to store all satellites that have german
	 * channels.
	 * 
	 * @param satellitesList list of all Satellite objects
	 * @return topContainer root composite container
	 */
	@Override
	public CompositeContainer aggregate(ArrayList<Satellite> satellitesList) {

		CompositeContainer topContainer = new CompositeContainer();
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

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}
}
