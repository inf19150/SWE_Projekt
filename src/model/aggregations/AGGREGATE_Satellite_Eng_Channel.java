package model.aggregations;

import java.util.ArrayList;

import model.Channel;
import model.Satellite;
import model.Transponder;
import model.aggregations.IAggregate;
import model.containers.CompositeContainer;
import model.containers.CompositeContainerHead;

public class AGGREGATE_Satellite_Eng_Channel implements IAggregate {
	@Override
	public CompositeContainerHead aggregate(ArrayList<Satellite> satellitesList) {

		CompositeContainerHead topContainer = new CompositeContainerHead();

		for (Satellite satellite : satellitesList) {
			CompositeContainer temp = null;
			for (Transponder transponder : satellite.getTransponders()) {
				for (Channel channel : transponder.getChannels()) {
					if (channel.getLanguage().contains("eng")) {
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

	@Override
	public String getName() {
		return "English Channels per Satellite";
	}
}
