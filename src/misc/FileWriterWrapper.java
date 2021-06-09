package misc;

import java.io.File;
import java.io.IOException;

public class FileWriterWrapper extends AFileWriter {

	public FileWriterWrapper(File file) {
		super(file);
	}

	@Override
	public void write(String content) {
		try {
			this.fileWriter.write(content);
			this.fileWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		try {
			this.fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
