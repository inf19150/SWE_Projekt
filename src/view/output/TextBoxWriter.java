package view.output;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import model.containers.CompositeContainer;

/**
 * JSONFileWriter class inherits {@link JFrame} implements {@link IOutput}.
 * Output module to print composite structure in a text box.
 *
 */
public class TextBoxWriter extends JFrame implements IOutput {

	private static final String NAME = "Text Box Writer";
	private static final String DESCRIPTION = "<HTML><pre width=300px>"
			+ "Outputs the selected aggregation like\n the simple text writer on a gui (text area)\n"
			+ "The selected aggregation can be\nhierarchically indefinitely deep\n" + "</pre></HTML>";

	private static final long serialVersionUID = 7385967735541537200L;
	private StringBuilder builder = new StringBuilder();
	private JTextArea textArea;

	/**
	 * Constructor of TextBoxWriter. Initializes a User Interface to show output.
	 * 
	 */
	public TextBoxWriter() {
		super("TextBox Writer Aggregation von " + IOutput.class);
		super.setSize(1280, 720);

		this.builder = new StringBuilder();

		this.textArea = new JTextArea();
		this.textArea.setEditable(false);
		DefaultCaret caret = (DefaultCaret) this.textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		JScrollPane jScrollPane = new JScrollPane(this.textArea);

		super.getContentPane().add(jScrollPane, BorderLayout.CENTER);

		super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	/**
	 * Outputs the composite Structure as in a text box.
	 * 
	 * @param container Root container of the composite Structure
	 */
	@Override
	public void output(CompositeContainer container) {
		for (CompositeContainer comp : container.getCompositums())
			printCompositum(comp, 0);

		this.textArea.setText(this.builder.toString());

		super.pack();
		this.textArea.setCaretPosition(0);
		super.setVisible(true);
	}

	/**
	 * Forms the output with an builder object
	 * 
	 * @param c     container Root container of the composite Structure
	 * @param level depth level of hierarchy
	 */
	private void printCompositum(CompositeContainer c, int level) {
		for (int i = 0; i < level; i++)
			this.builder.append("\t");

		this.builder.append(c.getKey() + ": " + c.getValue() + "\n");
		for (CompositeContainer comp : c.getCompositums()) {
			this.printCompositum(comp, level + 1);
		}
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}

	@Override
	public void reset() {
		this.builder = new StringBuilder();
	}
}
