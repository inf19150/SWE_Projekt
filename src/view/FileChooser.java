package view;

import javax.swing.JFileChooser;

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

	public FileChooser(String path) {
		super(path);
	}

}
