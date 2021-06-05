package view.output;

import java.io.FileWriter;
import java.io.IOException;

import model.containers.CompositeContainer;
import model.containers.CompositeContainerHead;
import view.FileChooser;

public class SimpleFileWriter implements IOutput {

	private FileWriter fw;

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

	private void printCompositum(CompositeContainer c, int level) throws IOException {

		for (int i = 0; i < level; i++)
			this.fw.write("\t");

		this.fw.write(c.getKey() + ": " + c.getData() + "\n");
		for (CompositeContainer comp : c.getCompositums()) {
			printCompositum(comp, level + 1);
		}
	}

	@Override
	public String getName() {
		return "Simple File Writer";
	}
}
