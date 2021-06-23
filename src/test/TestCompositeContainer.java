package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.container.CompositeContainer;

/**
 * TestCompositeContainer class tests composite container.
 *
 */
public class TestCompositeContainer {

	private static CompositeContainer dummyCompositeContainer;

	/**
	 * Init function, gets called before all tests. Creates test composite
	 * structure.
	 * 
	 */
	@BeforeAll
	public static void init() {
		dummyCompositeContainer = new CompositeContainer("planet", "Earth");

		CompositeContainer dep0 = new CompositeContainer("country", "Italy");

		dummyCompositeContainer.addHierarchy(dep0);
	}

	/*
	 * Tests arbitrary key and value of CompositeContainer.
	 * 
	 */
	@Test
	public void testCompositeContainer() {
		assertEquals(dummyCompositeContainer.getKey(), "planet");
		assertEquals(dummyCompositeContainer.getValue(), "Earth");

		assertEquals(dummyCompositeContainer.getCompositums().get(0).getKey(), "country");
		assertEquals(dummyCompositeContainer.getCompositums().get(0).getValue(), "Italy");
	}

	/*
	 * Tests hierarchy of CompositeContainer.
	 * 
	 */
	@Test
	public void testCompositeContainerHierarchy() {
		CompositeContainer test = new CompositeContainer("country", "Italy");
		ArrayList<CompositeContainer> testList = new ArrayList<CompositeContainer>();
		testList.add(test);

		assertEquals(dummyCompositeContainer.getCompositums(), testList);
	}

}
