package misc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Abstract class AFileWriter member of class adapter pattern (Target).
 * (Does not extend FileWriter to prevent the need of handling possible thrown
 * exceptions outside of Target.)
 * Offers the possibility to map functionality to FileWriter.
 *
 * {@link FileWriter} Adaptee
 */
public abstract class AFileWriter {

	protected FileWriter fileWriter;

	/**
	 * Implemented constructor of AFileWriter which takes a file and handles
	 * possible IOExceptions.
	 * 
	 * @param file to be written to.
	 */
	protected AFileWriter(File file) {
		try {
			this.fileWriter = new FileWriter(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Write function for writing to a file. Exceptions are handled and the buffer
	 * gets flushed after every call to ensure the file to be written to even if it
	 * is not closed properly.
	 * 
	 * @param content
	 */
	public abstract void write(String content);

	/**
	 * Close function for closing a file. Exceptions are handled.
	 * 
	 */
	public abstract void close();
}
