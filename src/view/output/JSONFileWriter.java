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
	private static final String DESCRIPTION = "Description";

	private FileWriterWrapper fw;
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();

	/**
	 * Outputs the composite Structure as JSON to a file.
	 * 
	 * @param container Root container of the composite Structure
	 */
	@Override
	public void output(CompositeContainer container) {
		FileChooser fileChooser = new FileChooser(System.getProperty("user.home"), "Chose *.json output-file!", "json");

		File file = fileChooser.getFile();
		if (file == null)
			return;
		this.fw = new FileWriterWrapper(file);
		
		String serializedJson = this.gson.toJson(container);
		
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
}
