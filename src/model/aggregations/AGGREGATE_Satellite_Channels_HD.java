package model.aggregations;

import java.util.ArrayList;

import model.Channel;
import model.Satellite;
import model.Transponder;
import model.container.CompositeContainer;

/**
 * Aggregates all Satellites with HD channels implements {@link IAggregate}.
 *
 */
public class AGGREGATE_Satellite_Channels_HD implements IAggregate {

	private static final String NAME = "HD Channels per Satellite";
	private static final String DESCRIPTION = "<HTML><pre width=220px>"
			+ "- Satellite: A\n"
			+ "	- HD-Channel: i\n"
			+ "	- HD-Channel: ii\n"
			+ "	- HD-Channel: iii\n"
			+ "</pre></HTML>";
	
	@Override
	public CompositeContainer aggregate(ArrayList<Satellite> satellitesList) {

		CompositeContainer topContainer = new CompositeContainer();

		for (Satellite satellite : satellitesList) {
			CompositeContainer temp = null;
			for (Transponder transponder : satellite.getTransponders()) {
				for (Channel channel : transponder.getChannels()) {
					if (isHD(channel)) {
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
	 * Returns whether the channel is a HD channel.
	 * 
	 * @param c Channel object
	 * @return whether the channel is a HD channel or not
	 */
	private boolean isHD(Channel c) {
		return c.getRes().equals("HD");
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
