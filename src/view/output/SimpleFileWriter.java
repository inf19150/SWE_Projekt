package view.output;

import java.io.File;

import model.container.CompositeContainer;
import view.FileChooser;

/**
 * SimpleFileWriter class implements {@link IOutput}. Output module to
 * print composite structure with visualized depth level of hierarchy to a file.
 *
 */
public class SimpleFileWriter implements IOutput {

	private static final String NAME = "Simple File Writer";
	private static final String DESCRIPTION = "<HTML><pre width=250px>" + "Outputs the selected aggregation\n"
			+ "in a simple (tab-based)\ntext-format\n"
			+ "The selected aggregation can be\nhierarchically indefinitely deep\n" + "</pre></HTML>";

	private File file = null;
	private FileWriterWrapper fw;

	/**
	 * The default constructor is mentioned explicitly because a modified
	 * constructor is required for the tests.
	 * 
	 */
	public SimpleFileWriter() {
	}

	/**
	 * Modified constructor for testing which takes a file.
	 * 
	 * @param f {@link File}
	 */
	public SimpleFileWriter(File f) {
		this.file = f;
	}

	@Override
	public void output(CompositeContainer container) {
		if (this.file == null) {
			FileChooser fileChooser = new FileChooser(System.getProperty("user.home"), "Chose output-file!", "txt",
					"dat", "bin", "out", "log");

			this.file = fileChooser.getFile();
			if (this.file == null)
				return;
		}

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

		this.fw.write(c.getKey() + ": " + c.getValue() + "\n");
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

	@Override
	public void reset() {
		this.file = null;
	}
}
