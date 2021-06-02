package aggregations;

import java.util.ArrayList;

import model.Satellite;
import model.Transponder;

public class AGGREGATE_Satellite_Transponder_Count_Channels implements IAggregate {

	@Override
	public CompositeContainer aggregate(ArrayList<Satellite> satellitesList) {

		CompositeContainer topContainer = new CompositeContainer("");

		for (Satellite satellite : satellitesList) {
			CompositeContainer compositeContainer = new CompositeContainer(satellite.getSat());
			topContainer.addHierarchy(compositeContainer);
			// System.out.println("- " + satellite.getSat());

			for (Transponder t : satellite.getTransponders()) {

				CompositeContainer transponder = new CompositeContainer("f=" + t.getFreq());

				compositeContainer.addHierarchy(transponder);
				// System.out.println("\t-f=" + t.getFreq());

				transponder.addHierarchy(new CompositeContainer("Radio: " + t.getAmountRadioChannels()));
				transponder.addHierarchy(new CompositeContainer("TV: " + t.getAmountTVChannels()));

//				System.out.println("\t\tTV: " + t.getAmountTVChannels());
//				System.out.println("\t\tRadio: " + t.getAmountRadioChannels());
			}
		}
		return topContainer;
	}

}
