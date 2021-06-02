package aggregations;

import java.util.ArrayList;

import model.Satellite;
import model.Transponder;
import model.Channel;

public class AGGREGATE_Satellite_Ger_Channel implements IAggregate {
	@Override
	public CompositeContainer aggregate(ArrayList<Satellite> satellitesList) {

		CompositeContainer topContainer = new CompositeContainer("");

		for (Satellite satellite : satellitesList) {
			CompositeContainer temp = null;
			for (Transponder transponder: satellite.getTransponders()) {
				for (Channel channel: transponder.getChannels()) {
					if(channel.getLanguage().contains("ger")) {
						if(temp == null) {
							temp = new CompositeContainer(satellite.getSat());
							topContainer.addHierarchy(temp);
						}
						CompositeContainer channelContainer = new CompositeContainer(channel.getName());
						temp.addHierarchy(channelContainer);
					}
						
				}
			}
		}
		return topContainer;
	}
}


