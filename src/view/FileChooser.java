package view;

import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * {@link FileChooser} inherits {@link JFileChooser}
 * 
 * @author Nick
 *
 */
public class FileChooser extends JFileChooser {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4595760880290991931L;

	public FileChooser(String path, String title, String... expectedSuffixes) {
		super(path);
		super.setDialogTitle(title);
		super.setFileFilter(new FileNameExtensionFilter("Allowed file-suffix " + Arrays.toString(expectedSuffixes),
				expectedSuffixes));

	}

}
