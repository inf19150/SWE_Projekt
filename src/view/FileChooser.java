package view;

import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * {@link FileChooser} inherits {@link JFileChooser}
 * 
 */
public class FileChooser extends JFileChooser {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4595760880290991931L;

	/**
	 * 
	 * Creates File explorer for the User to choose a file.
	 * 
	 * @param path             path to start directory
	 * @param title            title of the explorer
	 * @param expectedSuffixes list of file extensions to be shown
	 */
	public FileChooser(String path, String title, String... expectedSuffixes) {
		super(path);
		super.setDialogTitle(title);
		super.setFileFilter(new FileNameExtensionFilter("Allowed file-suffix " + Arrays.toString(expectedSuffixes),
				expectedSuffixes));

	}

}
