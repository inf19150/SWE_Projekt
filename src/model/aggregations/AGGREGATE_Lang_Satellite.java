package model.aggregations;

import java.util.ArrayList;

import model.Channel;
import model.Satellite;
import model.Transponder;
import model.containers.CompositeContainer;
import model.containers.CompositeContainerHead;

public class AGGREGATE_Lang_Satellite implements IAggregate {

	@Override
	public CompositeContainerHead aggregate(ArrayList<Satellite> satellitesList) {

		CompositeContainerHead topContainer = new CompositeContainerHead();
		CompositeContainer langContainer = new CompositeContainer("Language", "GER");

		topContainer.addHierarchy(langContainer);

		for (Satellite satellite : satellitesList) {
			outerloop: for (Transponder transponder : satellite.getTransponders()) {
				for (Channel channel : transponder.getChannels()) {
					if (channel.getLanguage().contains("ger")) {
						langContainer.addHierarchy(new CompositeContainer("Satellite", satellite.getSat()));
						break outerloop;
					}

				}
			}
		}
		return topContainer;
	}

	@Override
	public String getName() {
		return "Satellites with german Channels";
	}
}
