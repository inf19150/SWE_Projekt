package model.aggregations;

import java.util.ArrayList;

import model.Channel;
import model.Satellite;
import model.Transponder;
import model.container.CompositeContainer;

/**
 * Aggregates all Satellites with german channels implements {@link IAggregate}.
 *
 */
public class AGGREGATE_Satellite_Ger_Channel implements IAggregate {

	private static final String NAME = "German Channels per Satellite";
	private static final String DESCRIPTION = "<HTML><pre width=220px>"
			+ "- Satellite: A\n"
			+ "	- Channel: a\n"
			+ "	- Channel: b\n"
			+ "	- Channel: c\n"
			+ "</pre></HTML>";
	
	@Override
	public CompositeContainer aggregate(ArrayList<Satellite> satellitesList) {

		CompositeContainer topContainer = new CompositeContainer();

		for (Satellite satellite : satellitesList) {
			CompositeContainer temp = null;
			for (Transponder transponder : satellite.getTransponders()) {
				for (Channel channel : transponder.getChannels()) {
					if (isGerman(channel)) {
						if (temp == null) {
							temp = new CompositeContainer("Satellite", satellite.getSat());
							topContainer.addHierarchy(temp);
						}
						CompositeContainer channelContainer = new CompositeContainer("Channel", channel.getName());
						temp.addHierarchy(channelContainer);
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
	 * @return whether the channel is a german channel or not
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
