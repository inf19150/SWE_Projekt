package output;

import java.io.FileWriter;
import java.io.IOException;

import aggregations.CompositeContainer;
import aggregations.CompositeContainerHead;

public class SimpleFileWriter implements IOutput {

	private FileWriter fw;

	public SimpleFileWriter(String fileName) {
		try {
			fw = new FileWriter(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void output(CompositeContainerHead container) {
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
}
