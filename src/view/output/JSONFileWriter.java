package view.output;

import java.io.File;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import misc.FileWriterWrapper;
import model.containers.CompositeContainer;
import view.FileChooser;

/**
 * JSONFileWriter class inherits implements {@link IOutput}. Output module to
 * print composite structure as JSON to a file.
 *
 */
public class JSONFileWriter implements IOutput {

	private static final String NAME = "JSON File Writer";
	private static final String DESCRIPTION = "<HTML><pre width=220px>"
			+ "Outputs the selected aggregation\nin a weird but valid json format\n"
			+ "The selected aggregation can be\nhierarchically indefinitely deep\n" + "</pre></HTML>";

	private File file = null;
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();

	/**
	 * 
	 */
	public JSONFileWriter() {
	}

	/**
	 * 
	 * @param f
	 */
	public JSONFileWriter(File f) {
		this.file = f;
	}

	/**
	 * Outputs the composite Structure as JSON to a file.
	 * 
	 * @param container Root container of the composite Structure
	 */
	@Override
	public void output(CompositeContainer container) {

		String serializedJson = this.gson.toJson(container);
		FileWriterWrapper fw;

		if (this.file == null) {
			FileChooser fileChooser = new FileChooser(System.getProperty("user.home"), "Chose *.json output-file!",
					"json");

			this.file = fileChooser.getFile();
			if (this.file == null)
				return;
		}

		fw = new FileWriterWrapper(this.file);
		fw.write(serializedJson);
		fw.close();
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
