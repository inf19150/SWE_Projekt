package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.containers.CompositeContainer;

public class TestCompositeContainer {

	private static CompositeContainer dummyCompositeContainer;

	@BeforeAll
	public static void init() {
		dummyCompositeContainer = new CompositeContainer("planet", "Earth");

		CompositeContainer dep0 = new CompositeContainer("country", "Italy");

		dummyCompositeContainer.addHierarchy(dep0);
	}

	/*
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
