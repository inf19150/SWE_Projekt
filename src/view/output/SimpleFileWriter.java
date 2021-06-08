package view.output;

import java.io.File;

import misc.FileWriterWrapper;
import model.containers.CompositeContainer;
import view.FileChooser;

/**
 * JSONFileWriter class inherits implements {@link IOutput}. Output module to
 * print composite structure with visualized depth level of hierarchy to a file.
 *
 */
public class SimpleFileWriter implements IOutput {

	
	private static final String NAME = "Simple File Writer";
	private static final String DESCRIPTION = "Description";
	private FileWriterWrapper fw;

	/**
	 * Outputs the composite Structure with visualized depth level to a file.
	 * 
	 * @param container Root container of the composite Structure
	 */
	@Override
	public void output(CompositeContainer container) {
		FileChooser fileChooser = new FileChooser(System.getProperty("user.home"), "Chose output-file!", "txt", "dat",
				"bin", "out", "log");

		File file = fileChooser.getFile();
		if (file == null)
			return;
		this.fw = new FileWriterWrapper(file);
		for (CompositeContainer comp : container.getCompositums())
			printCompositum(comp, 0);
		this.fw.close();
	}

	/**
	 * Forms the output and writes it to the text file
	 * 
	 * @param c     container Root container of the composite Structure
	 * @param level depth level of hierarchy
	 */
	private void printCompositum(CompositeContainer c, int level) {

		for (int i = 0; i < level; i++)
			this.fw.write("\t");

		this.fw.write(c.getKey() + ": " + c.getData() + "\n");
		for (CompositeContainer comp : c.getCompositums()) {
			printCompositum(comp, level + 1);
		}
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
