package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.JSONLoader;
import model.Channel;
import model.Satellite;
import model.Transponder;

/**
 * TestModel class tests the data model.
 *
 */
public class TestModel {

	private static ArrayList<Satellite> satelliteList;
	private static Satellite satellite;
	private static Transponder transponder;
	private static Channel c;

	private static final String FILENAME = "data_test.json";
	private static TestJSONBuilder jsonFactory;

	/**
	 * Init function, gets called before all tests. Creates test JSON file, parses
	 * satellite data from it and holds the first satellite, its first transponder
	 * and its first channel.
	 * 
	 */
	@BeforeAll
	public static void init() {
		jsonFactory = new TestJSONBuilder(FILENAME);
		jsonFactory.createFile();
		satelliteList = JSONLoader.getSatelliteList(FILENAME);
		satellite = satelliteList.get(0);
		transponder = satelliteList.get(0).getTransponders().get(0);
		c = transponder.getChannels().get(0);
	}

	/*
	 * Tests Satellite class.
	 * 
	 */
	@Test
	public void testSatellite() {
		assertEquals(satellite.getSat(), "BulgariaSat-1");
	}

	/*
	 * Tests Transponder class.
	 * 
	 */
	@Test
	public void testTransponder() {
		assertEquals(transponder.getFreq(), 12072);
		assertEquals(transponder.getSat(), "BulgariaSat-1");
		assertEquals(transponder.getAmountTVChannels(), 2);
		assertEquals(transponder.getAmountRadioChannels(), 1);
	}

	/**
	 * Tests Channel class.
	 * 
	 */
	@Test
	public void testChannel() {
		assertEquals(c.getLanguage(), "2031 eng");
		assertEquals(c.getName(), "Fuel TV HD");
		assertEquals(c.getRes(), "HD");
		assertEquals(c.getType(), "TV");
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
