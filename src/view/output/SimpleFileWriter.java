package view.output;

import java.io.FileWriter;
import java.io.IOException;

import model.containers.CompositeContainer;
import model.containers.CompositeContainerHead;
import view.FileChooser;

/**
 * JSONFileWriter class inherits implements {@link IOutput}. Output module to
 * print composite structure with visualized depth level of hierarchy to a file.
 *
 */
public class SimpleFileWriter implements IOutput {

	private FileWriter fw;

	/**
	 * Outputs the composite Structure with visualized depth level to a file.
	 * 
	 * @param container Root container of the composite Structure
	 */
	@Override
	public void output(CompositeContainerHead container) {
		FileChooser fileChooser = new FileChooser(System.getProperty("user.home"), "Chose output-file!", "txt", "dat",
				"bin", "out", "log");
		int result = fileChooser.showOpenDialog(null);

		if (result == FileChooser.APPROVE_OPTION) {

			try {
				if (fileChooser.getSelectedFile() == null)
					throw new IOException();
				this.fw = new FileWriter(fileChooser.getSelectedFile());
			} catch (IOException e) {
			}
		}
		try {
			for (CompositeContainer comp : container.getCompositums()) {
				printCompositum(comp, 0);
			}
			this.fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Forms the output and writes it to the text file
	 * 
	 * @param c     container Root container of the composite Structure
	 * @param level depth level of hierarchy
	 * 
	 * @throws IOException if write to file fails
	 */
	private void printCompositum(CompositeContainer c, int level) throws IOException {

		for (int i = 0; i < level; i++)
			this.fw.write("\t");

		this.fw.write(c.getKey() + ": " + c.getData() + "\n");
		for (CompositeContainer comp : c.getCompositums()) {
			printCompositum(comp, level + 1);
		}
	}

	/**
	 * Returns decent name of class.
	 * 
	 * @return Decent name of class
	 */
	@Override
	public String getName() {
		return "Simple File Writer";
	}
}
