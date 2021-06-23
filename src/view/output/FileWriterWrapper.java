package view.output;

import java.io.File;
import java.io.IOException;

/**
 * 
 * FileWriterWrapper member of class adapter pattern (adapter). 
 * Maps the desired behavior to the target.
 * 
 * @extends {@link AFileWriter} Target
 *
 */
public class FileWriterWrapper extends AFileWriter {

	/**
	 * Constructor of FileWriterWrapper delegates to constructor of {@link AFileWriter}.
	 * 
	 * @param file to be written to.
	 */
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
