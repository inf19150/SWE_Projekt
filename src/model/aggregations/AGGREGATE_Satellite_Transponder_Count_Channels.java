package model.aggregations;

import java.util.ArrayList;

import model.Satellite;
import model.Transponder;
import model.aggregations.IAggregate;
import model.containers.CompositeContainer;
import model.containers.CompositeContainerHead;

public class AGGREGATE_Satellite_Transponder_Count_Channels implements IAggregate {

	@Override
	public CompositeContainerHead aggregate(ArrayList<Satellite> satellitesList) {

		CompositeContainerHead topContainer = new CompositeContainerHead();

		for (Satellite satellite : satellitesList) {
			CompositeContainer compositeContainer = new CompositeContainer("Satellite", satellite.getSat());
			topContainer.addHierarchy(compositeContainer);
			// System.out.println("- " + satellite.getSat());

			for (Transponder t : satellite.getTransponders()) {

				CompositeContainer transponder = new CompositeContainer("Frequency", t.getFreq() + "");

				compositeContainer.addHierarchy(transponder);
				// System.out.println("\t-f=" + t.getFreq());

				transponder.addHierarchy(new CompositeContainer("Radio", t.getAmountRadioChannels() + ""));
				transponder.addHierarchy(new CompositeContainer("TV", t.getAmountTVChannels() + ""));

//				System.out.println("\t\tTV: " + t.getAmountTVChannels());
//				System.out.println("\t\tRadio: " + t.getAmountRadioChannels());
			}
		}
		return topContainer;
	}

	@Override
	public String getName() {
		return "Count Channel per Transponder";
	}

}
