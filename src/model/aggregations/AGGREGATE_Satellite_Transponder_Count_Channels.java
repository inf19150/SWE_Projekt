package model.aggregations;

import java.util.ArrayList;

import model.Satellite;
import model.Transponder;
import model.container.CompositeContainer;

/**
 * Aggregates all Satellites with their respective count of radio and TV
 * channels implements {@link IAggregate}.
 *
 */
public class AGGREGATE_Satellite_Transponder_Count_Channels implements IAggregate {

	private static final String NAME = "Count Channel per Transponder";
	private static final String DESCRIPTION = "<HTML><pre width=220px>"
			+ "- Satellit A\n"
			+ "	- Transponder B\n"
			+ "		- 20 Radio Programme\n"
			+ "		- 10 Fernsehprogramme\n"
			+ "	- Transponder C\n"
			+ "		- 1 Radio Programm\n"
			+ "		- 100 Fernsehprogramme"
			+ "</pre></HTML>";
	
	@Override
	public CompositeContainer aggregate(ArrayList<Satellite> satellitesList) {

		CompositeContainer topContainer = new CompositeContainer();

		for (Satellite satellite : satellitesList) {
			CompositeContainer compositeContainer = new CompositeContainer("Satellite", satellite.getSat());
			topContainer.addHierarchy(compositeContainer);

			for (Transponder t : satellite.getTransponders()) {
				CompositeContainer transponder = new CompositeContainer("Frequency", t.getFreq() + "");
				compositeContainer.addHierarchy(transponder);
				transponder.addHierarchy(new CompositeContainer("Radio", t.getAmountRadioChannels() + ""));
				transponder.addHierarchy(new CompositeContainer("TV", t.getAmountTVChannels() + ""));
			}
		}
		return topContainer;
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
