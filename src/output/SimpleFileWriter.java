package output;

import java.io.FileWriter;
import java.io.IOException;

import model.containers.CompositeContainer;
import model.containers.CompositeContainerHead;

public class SimpleFileWriter implements IOutput {

	private FileWriter fw;

	public SimpleFileWriter(String fileName) {
		try {
			fw = new FileWriter(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public SimpleFileWriter() {
		this("simpleOutput.txt");
	}

	@Override
	public void output(CompositeContainerHead container) {
		try {
			for (CompositeContainer comp : container.getCompositums()) {
				printCompositum(comp, 0);
			}
			
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
}
