package test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import model.container.CompositeContainer;
import view.output.IOutput;
import view.output.JSONFileWriter;
import view.output.SimpleFileWriter;
import view.output.TextBoxWriter;

/**
 * TestOutputs tests all outputs.
 *
 */
public class TestOutputs {

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
		CompositeContainer dep01 = new CompositeContainer("Rome", "2873000");
		CompositeContainer dep02 = new CompositeContainer("Parma", "194417");

		dep0.addHierarchy(dep01);
		dep0.addHierarchy(dep02);

		CompositeContainer dep1 = new CompositeContainer("country", "China");
		CompositeContainer dep11 = new CompositeContainer("Shenzen", "12590000");
		CompositeContainer dep12 = new CompositeContainer("Peking", "21540000");

		dep1.addHierarchy(dep11);
		dep1.addHierarchy(dep12);

		CompositeContainer dep2 = new CompositeContainer("country", "United States");
		CompositeContainer dep21 = new CompositeContainer("Los Angeles", "3967000");
		CompositeContainer dep22 = new CompositeContainer("Orlando", "280832");

		dep2.addHierarchy(dep21);
		dep2.addHierarchy(dep22);

		dummyCompositeContainer.addHierarchy(dep0);
		dummyCompositeContainer.addHierarchy(dep1);
		dummyCompositeContainer.addHierarchy(dep2);

	}

	/*
	 * Tests SimpleFileWriter class. Creates output and compares it to the expected
	 * output.
	 * 
	 */
	@Test
	public void testFileWriter() {
		File out = new File("test_simplefw.txt");
		IOutput fileWriter = new SimpleFileWriter(out);
		fileWriter.output(dummyCompositeContainer);

		StringBuffer buffer = null;

		try {
			Scanner reader = new Scanner(out);

			buffer = new StringBuffer();

			while (reader.hasNextLine()) {
				buffer.append(reader.nextLine() + "\r\n");
			}

			reader.close();

			out.delete();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String expectedResult = "country: Italy\r\n" + "	Rome: 2873000\r\n" + "	Parma: 194417\r\n"
				+ "country: China\r\n" + "	Shenzen: 12590000\r\n" + "	Peking: 21540000\r\n"
				+ "country: United States\r\n" + "	Los Angeles: 3967000\r\n" + "	Orlando: 280832\r\n";

		assertEquals(expectedResult, buffer.toString());
	}

	/*
	 * Tests JSONFileWriter class. Creates output and compares it to the expected
	 * output.
	 * 
	 */
	@Test
	public void testJSONFileWriter() {
		File out = new File("test_json_writer.txt");
		IOutput fileWriter = new JSONFileWriter(out);
		fileWriter.output(dummyCompositeContainer);

		StringBuffer buffer = null;

		try {
			Scanner reader = new Scanner(out);

			buffer = new StringBuffer();

			while (reader.hasNextLine()) {
				buffer.append(reader.nextLine() + "\r\n");
			}

			reader.close();

			out.delete();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String expectedResult = "{\r\n" + "  \"key\": \"planet\",\r\n" + "  \"value\": \"Earth\",\r\n"
				+ "  \"compositums\": [\r\n" + "    {\r\n" + "      \"key\": \"country\",\r\n"
				+ "      \"value\": \"Italy\",\r\n" + "      \"compositums\": [\r\n" + "        {\r\n"
				+ "          \"key\": \"Rome\",\r\n" + "          \"value\": \"2873000\",\r\n"
				+ "          \"compositums\": []\r\n" + "        },\r\n" + "        {\r\n"
				+ "          \"key\": \"Parma\",\r\n" + "          \"value\": \"194417\",\r\n"
				+ "          \"compositums\": []\r\n" + "        }\r\n" + "      ]\r\n" + "    },\r\n" + "    {\r\n"
				+ "      \"key\": \"country\",\r\n" + "      \"value\": \"China\",\r\n" + "      \"compositums\": [\r\n"
				+ "        {\r\n" + "          \"key\": \"Shenzen\",\r\n" + "          \"value\": \"12590000\",\r\n"
				+ "          \"compositums\": []\r\n" + "        },\r\n" + "        {\r\n"
				+ "          \"key\": \"Peking\",\r\n" + "          \"value\": \"21540000\",\r\n"
				+ "          \"compositums\": []\r\n" + "        }\r\n" + "      ]\r\n" + "    },\r\n" + "    {\r\n"
				+ "      \"key\": \"country\",\r\n" + "      \"value\": \"United States\",\r\n"
				+ "      \"compositums\": [\r\n" + "        {\r\n" + "          \"key\": \"Los Angeles\",\r\n"
				+ "          \"value\": \"3967000\",\r\n" + "          \"compositums\": []\r\n" + "        },\r\n"
				+ "        {\r\n" + "          \"key\": \"Orlando\",\r\n" + "          \"value\": \"280832\",\r\n"
				+ "          \"compositums\": []\r\n" + "        }\r\n" + "      ]\r\n" + "    }\r\n" + "  ]\r\n"
				+ "}\r\n";

		assertEquals(expectedResult, buffer.toString());
	}

	/*
	 * Tests TextBoxWriter class. Can only check whether the class throws an
	 * exception.
	 * 
	 */
	@Test
	public void testTextBoxWriter() {
		assertDoesNotThrow(new Executable() {
			@Override
			public void execute() throws Throwable {
				IOutput textBoxWriter = new TextBoxWriter();
				textBoxWriter.output(dummyCompositeContainer);
			}
		});
	}

}
