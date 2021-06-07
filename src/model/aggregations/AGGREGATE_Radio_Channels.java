package model.aggregations;

import java.util.ArrayList;

import model.Channel;
import model.Satellite;
import model.Transponder;
import model.containers.CompositeContainer;
import model.containers.CompositeContainerHead;

/**
 * Aggregates all Satellites with radio channels.
 *
 */
public class AGGREGATE_Radio_Channels implements IAggregate {

	/**
	 * Creates Composite structure to store all satellites with radio channels.
	 * 
	 * @param satellitesList list of all Satellite objects
	 * @return topContainer root composite container
	 */
	@Override
	public CompositeContainerHead aggregate(ArrayList<Satellite> satellitesList) {

		CompositeContainerHead topContainer = new CompositeContainerHead();

		for (Satellite satellite : satellitesList) {
			CompositeContainer temp = null;
			for (Transponder transponder : satellite.getTransponders()) {
				for (Channel channel : transponder.getChannels()) {
					if (isRadio(channel)) {
						if (temp == null) {
							temp = new CompositeContainer("Satellite", satellite.getSat());
							topContainer.addHierarchy(temp);
						}
						CompositeContainer channelContainer = new CompositeContainer("Radio channel",
								channel.getName());
						temp.addHierarchy(channelContainer);
					}
				}
			}
		}
		return topContainer;
	}

	/**
	 * Returns whether the channel is a radio channel.
	 * 
	 * @param c Channel object
	 * @return whether the channel is a radio channel or not
	 */
	private boolean isRadio(Channel c) {
		return c.getType().equals("R");
	}

	/**
	 * Returns decent name of class.
	 * 
	 * @return Decent name of class
	 */
	@Override
	public String getName() {
		return "Radio Channels per Satellite";
	}

	@Override
	public String getDescription() {
		return this.getName();
	}
}
