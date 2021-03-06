package model.aggregations;

import java.util.ArrayList;

import model.Channel;
import model.Satellite;
import model.Transponder;
import model.container.CompositeContainer;

/**
 * Aggregates all Satellites with radio channels implements {@link IAggregate}.
 *
 */
public class AGGREGATE_Radio_Channels implements IAggregate {

	private static final String NAME = "Radio Channels per Satellite";
	private static final String DESCRIPTION = "<HTML><pre width=220px>"
			+ "- Satellite: A\n"
			+ "	- FM-Channel: i\n"
			+ "	- FM-Channel: ii\n"
			+ "	- FM-Channel: iii\n"
			+ "</pre></HTML>";	

	@Override
	public CompositeContainer aggregate(ArrayList<Satellite> satellitesList) {

		CompositeContainer topContainer = new CompositeContainer();

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

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}
}
