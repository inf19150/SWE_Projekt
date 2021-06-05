package model.aggregations;
import java.util.ArrayList;

import model.Channel;
import model.Satellite;
import model.Transponder;
import model.aggregations.IAggregate;
import model.containers.CompositeContainer;
import model.containers.CompositeContainerHead;

public class AGGREGATE_Radio_Channels implements IAggregate {
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
						CompositeContainer channelContainer = new CompositeContainer("Channel", channel.getName());
						temp.addHierarchy(channelContainer);
					}

				}
			}
		}
		return topContainer;
	}

	private boolean isRadio(Channel c) {
		return c.getType().equals("R");
	}

	@Override
	public String getName() {
		return "Radio Channels per Satellite";
	}
}
