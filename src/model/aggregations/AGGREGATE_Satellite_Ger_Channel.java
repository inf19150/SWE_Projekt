package model.aggregations;

import java.util.ArrayList;

import model.Channel;
import model.Satellite;
import model.Transponder;
import model.containers.CompositeContainer;

/**
 * Aggregates all Satellites with german channels implements {@link IAggregate}.
 *
 */
public class AGGREGATE_Satellite_Ger_Channel implements IAggregate {

	/**
	 * Creates Composite structure to store all satellites with german channels.
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
		return "German Channels per Satellite";
	}

	@Override
	public String getDescription() {
		return this.getName();
	}
}
