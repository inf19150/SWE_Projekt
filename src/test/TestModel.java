package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.JSONLoader;
import model.Channel;
import model.Satellite;
import model.Transponder;

public class TestModel {

	private static ArrayList<Satellite> satelliteList;
	private static Satellite satellite;
	private static Transponder transponder;
	private static Channel c;

	private static final String FILENAME = "data_test.json";

	/**
	 * 
	 */
	@BeforeAll
	public static void init() {
		new JSONFactory(FILENAME).createFile();
		JSONLoader jsonLoader = new JSONLoader(FILENAME);
		satelliteList = jsonLoader.getSatelliteList();
		satellite = satelliteList.get(0);
		transponder = satelliteList.get(0).getTransponders().get(0);
		c = transponder.getChannels().get(0);
	}

	/*
	 * 
	 */
	@Test
	public void testSatellite() {
		assertEquals(satellite.getSat(), "BulgariaSat-1");
	}

	/**
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
	 * 
	 */
	@Test
	public void testChannel() {
		assertEquals(c.getLanguage(), "2031 eng");
		assertEquals(c.getName(), "Fuel TV HD");
		assertEquals(c.getRes(), "HD");
		assertEquals(c.getType(), "TV");
	}

}
