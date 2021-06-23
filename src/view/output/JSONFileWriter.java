package view.output;

import java.io.File;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.container.CompositeContainer;
import view.FileChooser;

/**
 * JSONFileWriter class implements {@link IOutput}. Output module to print
 * composite structure as JSON to a file.
 *
 */
public class JSONFileWriter implements IOutput {

	private static final String NAME = "JSON File Writer";
	private static final String DESCRIPTION = "<HTML><pre width=220px>"
			+ "Outputs the selected aggregation\nin a weird but valid json format\n"
			+ "The selected aggregation can be\nhierarchically indefinitely deep\n" + "</pre></HTML>";

	private File file = null;
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private FileWriterWrapper fw;

	/**
	 * The default constructor is mentioned explicitly because a modified
	 * constructor is required for the tests.
	 * 
	 */
	public JSONFileWriter() {
	}

	/**
	 * Modified constructor for testing which takes a file.
	 * 
	 * @param f {@link File}
	 */
	public JSONFileWriter(File f) {
		this.file = f;
	}

	@Override
	public void output(CompositeContainer container) {

		String serializedJson = this.gson.toJson(container);

		if (this.file == null) {
			FileChooser fileChooser = new FileChooser(System.getProperty("user.home"), "Chose *.json output-file!",
					"json");

			this.file = fileChooser.getFile();
			if (this.file == null)
				return;
		}

		this.fw = new FileWriterWrapper(this.file);
		this.fw.write(serializedJson);
		this.fw.close();
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}

	@Override
	public void reset() {
		this.file = null;
	}
}
