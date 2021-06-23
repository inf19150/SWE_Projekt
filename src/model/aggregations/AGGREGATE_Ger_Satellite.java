package model.aggregations;

import java.util.ArrayList;

import model.Channel;
import model.Satellite;
import model.Transponder;
import model.container.CompositeContainer;

/**
 * Aggregates all Satellites that have german channels implements {@link IAggregate}.
 *
 */
public class AGGREGATE_Ger_Satellite implements IAggregate {

	private static final String NAME = "Satellites with german Channels";
	private static final String DESCRIPTION = "<HTML><pre width=220px>"
			+ "- Language: GER\n"
			+ "	- Satellite: A\n"
			+ "	- Satellite: B\n"
			+ "	- Satellite: C\n"
			+ "</pre></HTML>";
	
	@Override
	public CompositeContainer aggregate(ArrayList<Satellite> satellitesList) {

		CompositeContainer topContainer = new CompositeContainer();
		CompositeContainer langContainer = new CompositeContainer("Language", "GER");

		topContainer.addHierarchy(langContainer);

		for (Satellite satellite : satellitesList) {
			outerloop: for (Transponder transponder : satellite.getTransponders()) {
				for (Channel channel : transponder.getChannels()) {
					if (isGerman(channel)) {
						langContainer.addHierarchy(new CompositeContainer("Satellite", satellite.getSat()));
						break outerloop;
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
