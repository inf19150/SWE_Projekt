package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.JSONLoader;
import model.Satellite;
import model.aggregations.AGGREGATE_Ger_Satellite;
import model.aggregations.AGGREGATE_Radio_Channels;
import model.aggregations.AGGREGATE_Satellite_Channels_HD;
import model.aggregations.AGGREGATE_Satellite_Eng_Channel;
import model.aggregations.AGGREGATE_Satellite_Ger_Channel;
import model.aggregations.AGGREGATE_Satellite_Transponder_Count_Channels;
import model.aggregations.IAggregate;
import model.containers.CompositeContainer;

/**
 * TestAggregations class tests all aggregations.
 *
 */
public class TestAggregations {

	static ArrayList<Satellite> satelliteList;

	private static final String FILENAME = "data_test.json";
	private static TestJSONBuilder jsonFactory;

	/**
	 * Init function, gets called before all tests. Creates test JSON file and
	 * parses satellite data from it.
	 * 
	 */
	@BeforeAll
	public static void init() {
		jsonFactory = new TestJSONBuilder(FILENAME);
		jsonFactory.createFile();
		satelliteList = JSONLoader.getSatelliteList(FILENAME);
	}

	/**
	 * Tests AGGREGATE_Ger_Satellite. Creates expected composite structure and
	 * compares them.
	 * 
	 */
	@Test
	void testGerSatellite() {
		IAggregate target = new AGGREGATE_Ger_Satellite();
		CompositeContainer result = target.aggregate(satelliteList);

		CompositeContainer expectedResult = new CompositeContainer();
		CompositeContainer firstLayer = new CompositeContainer("Language", "GER");

		CompositeContainer secondLayer1 = new CompositeContainer("Satellite", "BulgariaSat-1");
		CompositeContainer secondLayer2 = new CompositeContainer("Satellite", "Astra 4A");
		firstLayer.addHierarchy(secondLayer1);
		firstLayer.addHierarchy(secondLayer2);
		expectedResult.addHierarchy(firstLayer);

		assertEquals(expectedResult, result);
	}

	/**
	 * Tests AGGREGATE_Ger_Satellite. Creates expected composite structure and
	 * compares them.
	 * 
	 */
	@Test
	void testRadioChannels() {
		IAggregate target = new AGGREGATE_Radio_Channels();
		CompositeContainer result = target.aggregate(satelliteList);

		CompositeContainer expectedResult = new CompositeContainer();
		CompositeContainer firstLayer = new CompositeContainer("Satellite", "BulgariaSat-1");
		CompositeContainer secondLayer = new CompositeContainer("Radio channel", "SWR3");
		firstLayer.addHierarchy(secondLayer);
		expectedResult.addHierarchy(firstLayer);

		assertEquals(expectedResult, result);
	}

	/**
	 * Tests AGGREGATE_Satellite_Channels_HD. Creates expected composite structure
	 * and compares them.
	 * 
	 */
	@Test
	void testSatelliteChannelsHD() {
		IAggregate target = new AGGREGATE_Satellite_Channels_HD();
		CompositeContainer result = target.aggregate(satelliteList);

		CompositeContainer expectedResult = new CompositeContainer();
		CompositeContainer firstLayer1 = new CompositeContainer("Satellite", "BulgariaSat-1");

		CompositeContainer secondLayer11 = new CompositeContainer("Channel", "Fuel TV HD");
		CompositeContainer secondLayer12 = new CompositeContainer("Channel", "Gametoon HD");
		CompositeContainer secondLayer13 = new CompositeContainer("Channel", "Test HD");
		CompositeContainer secondLayer14 = new CompositeContainer("Channel", "Auto Motor und Sport HD");
		CompositeContainer secondLayer15 = new CompositeContainer("Channel", "Chasse & Peche HD");
		CompositeContainer secondLayer16 = new CompositeContainer("Channel", "Wness TV HD");

		firstLayer1.addHierarchy(secondLayer11);
		firstLayer1.addHierarchy(secondLayer12);
		firstLayer1.addHierarchy(secondLayer13);
		firstLayer1.addHierarchy(secondLayer14);
		firstLayer1.addHierarchy(secondLayer15);
		firstLayer1.addHierarchy(secondLayer16);

		CompositeContainer firstLayer2 = new CompositeContainer("Satellite", "Astra 4A");

		CompositeContainer secondLayer21 = new CompositeContainer("Channel", "C More Fotboll HD");
		CompositeContainer secondLayer22 = new CompositeContainer("Channel", "RTL HD");

		firstLayer2.addHierarchy(secondLayer21);
		firstLayer2.addHierarchy(secondLayer22);

		expectedResult.addHierarchy(firstLayer1);
		expectedResult.addHierarchy(firstLayer2);

		assertEquals(expectedResult, result);
	}

	/**
	 * Tests AGGREGATE_Satellite_Eng_Channel. Creates expected composite structure
	 * and compares them.
	 * 
	 */
	@Test
	void testSatelliteChannelsEng() {
		IAggregate target = new AGGREGATE_Satellite_Eng_Channel();
		CompositeContainer result = target.aggregate(satelliteList);

		CompositeContainer expectedResult = new CompositeContainer();
		CompositeContainer firstLayer1 = new CompositeContainer("Satellite", "BulgariaSat-1");

		CompositeContainer secondLayer11 = new CompositeContainer("Channel", "Fuel TV HD");
		CompositeContainer secondLayer12 = new CompositeContainer("Channel", "Gametoon HD");
		CompositeContainer secondLayer14 = new CompositeContainer("Channel", "Auto Motor und Sport HD");
		CompositeContainer secondLayer15 = new CompositeContainer("Channel", "Chasse & Peche HD");

		firstLayer1.addHierarchy(secondLayer11);
		firstLayer1.addHierarchy(secondLayer12);
		firstLayer1.addHierarchy(secondLayer14);
		firstLayer1.addHierarchy(secondLayer15);

		CompositeContainer firstLayer2 = new CompositeContainer("Satellite", "Astra 4A");

		CompositeContainer secondLayer21 = new CompositeContainer("Channel", "NHK World TV");

		firstLayer2.addHierarchy(secondLayer21);

		expectedResult.addHierarchy(firstLayer1);
		expectedResult.addHierarchy(firstLayer2);

		assertEquals(expectedResult, result);
	}

	/**
	 * Tests AGGREGATE_Satellite_Ger_Channel. Creates expected composite structure
	 * and compares them.
	 * 
	 */
	@Test
	void testSatelliteChannelsGer() {
		IAggregate target = new AGGREGATE_Satellite_Ger_Channel();
		CompositeContainer result = target.aggregate(satelliteList);

		CompositeContainer expectedResult = new CompositeContainer();
		CompositeContainer firstLayer1 = new CompositeContainer("Satellite", "BulgariaSat-1");

		CompositeContainer secondLayer11 = new CompositeContainer("Channel", "SWR3");

		firstLayer1.addHierarchy(secondLayer11);

		CompositeContainer firstLayer2 = new CompositeContainer("Satellite", "Astra 4A");
		CompositeContainer secondLayer21 = new CompositeContainer("Channel", "RTL HD");

		firstLayer2.addHierarchy(secondLayer21);

		expectedResult.addHierarchy(firstLayer1);
		expectedResult.addHierarchy(firstLayer2);

		assertEquals(expectedResult, result);
	}

	/**
	 * Tests AGGREGATE_Satellite_Transponder_Count_Channels. Creates expected
	 * composite structure and compares them.
	 * 
	 */
	@Test
	void testSatelliteTransponderCount() {
		IAggregate target = new AGGREGATE_Satellite_Transponder_Count_Channels();
		CompositeContainer result = target.aggregate(satelliteList);

		CompositeContainer expectedResult = new CompositeContainer();

		CompositeContainer firstLayer1 = new CompositeContainer("Satellite", "BulgariaSat-1");
		CompositeContainer secondLayer11 = new CompositeContainer("Frequency", "12072");
		CompositeContainer thirdLayer11 = new CompositeContainer("Radio", "1");
		CompositeContainer thirdLayer12 = new CompositeContainer("TV", "2");

		CompositeContainer secondLayer21 = new CompositeContainer("Frequency", "12188");
		CompositeContainer thirdLayer21 = new CompositeContainer("Radio", "0");
		CompositeContainer thirdLayer22 = new CompositeContainer("TV", "4");

		secondLayer11.addHierarchy(thirdLayer11);
		secondLayer11.addHierarchy(thirdLayer12);

		secondLayer21.addHierarchy(thirdLayer21);
		secondLayer21.addHierarchy(thirdLayer22);

		firstLayer1.addHierarchy(secondLayer11);
		firstLayer1.addHierarchy(secondLayer21);

		CompositeContainer firstLayer2 = new CompositeContainer("Satellite", "Astra 4A");
		CompositeContainer secondLayer31 = new CompositeContainer("Frequency", "11881");
		CompositeContainer thirdLayer31 = new CompositeContainer("Radio", "0");
		CompositeContainer thirdLayer32 = new CompositeContainer("TV", "3");

		secondLayer31.addHierarchy(thirdLayer31);
		secondLayer31.addHierarchy(thirdLayer32);

		firstLayer2.addHierarchy(secondLayer31);

		expectedResult.addHierarchy(firstLayer1);
		expectedResult.addHierarchy(firstLayer2);

		assertEquals(expectedResult, result);
	}

	/**
	 * Clean up function, gets called after all tests. Deletes test JSON file.
	 * 
	 */
	@AfterAll
	public static void cleanUp() {
		jsonFactory.destruct();
	}

}
