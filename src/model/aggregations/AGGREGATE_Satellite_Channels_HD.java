package model.aggregations;

import java.util.ArrayList;

import model.Channel;
import model.Satellite;
import model.Transponder;
import model.containers.CompositeContainer;

/**
 * Aggregates all Satellites with HD channels implements {@link IAggregate}.
 *
 */
public class AGGREGATE_Satellite_Channels_HD implements IAggregate {

	/**
	 * Creates Composite structure to store all satellites with HD channels.
	 * 
	 * @param satellitesList list of all Satellite objects
	 * @return topContainer root composite container
	 */
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

	/**
	 * Returns decent name of class.
	 * 
	 * @return Decent name of class
	 */
	@Override
	public String getName() {
		return "HD Channels per Satellite";
	}

	@Override
	public String getDescription() {
		return this.getName();
	}
}
