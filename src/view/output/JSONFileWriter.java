package view.output;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.containers.CompositeContainerHead;
import view.FileChooser;

/**
 * JSONFileWriter class inherits implements {@link IOutput}. Output module to
 * print composite structure as JSON to a file.
 *
 */
public class JSONFileWriter implements IOutput {

	private FileWriter fw;
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();

	/**
	 * Outputs the composite Structure as JSON to a file.
	 * 
	 * @param container Root container of the composite Structure
	 */
	@Override
	public void output(CompositeContainerHead container) {

		FileChooser fileChooser = new FileChooser(System.getProperty("user.home"), "Chose *.json output-file!", "json");
		int result = fileChooser.showOpenDialog(null);

		if (result == FileChooser.APPROVE_OPTION) {
			try {
				if (fileChooser.getSelectedFile() == null)
					throw new IOException();
				this.fw = new FileWriter(fileChooser.getSelectedFile());
			} catch (IOException e) {
			}
		}

		String serializedJson = this.gson.toJson(container);

		try {
			this.fw.write(serializedJson);
			this.fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns decent name of class.
	 * 
	 * @return Decent name of class
	 */
	@Override
	public String getName() {
		return "JSON File Writer";
	}

	@Override
	public String getDescription() {
		return this.getName();
	}
}
