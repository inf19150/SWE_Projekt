package misc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class AFileWriter {

	protected FileWriter fileWriter;

	protected AFileWriter(File file) {
		try {
			this.fileWriter = new FileWriter(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	}

	public abstract void write(String content);

	public abstract void close();
}
