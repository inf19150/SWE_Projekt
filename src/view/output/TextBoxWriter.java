package view.output;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import model.containers.CompositeContainer;
import model.containers.CompositeContainerHead;
import view.output.IOutput;

public class TextBoxWriter extends JFrame implements IOutput {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7385967735541537200L;
	private StringBuilder builder;
	private JTextArea textArea;

	public TextBoxWriter() {
		super("TextBox Writer Aggregation von " + IOutput.class);
		super.setSize(1280, 720);

		this.builder = new StringBuilder();

		this.textArea = new JTextArea();
		this.textArea.setEditable(false);
		DefaultCaret caret = (DefaultCaret) this.textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		JScrollPane jScrollPaneChat = new JScrollPane(this.textArea);

		super.getContentPane().add(jScrollPaneChat, BorderLayout.CENTER);

		super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	@Override
	public void output(CompositeContainerHead container) {
		this.builder = new StringBuilder();
		for (CompositeContainer comp : container.getCompositums()) {
			printCompositum(comp, 0);
		}
		this.textArea.setText(this.builder.toString());

		super.setVisible(true);
	}

	private void printCompositum(CompositeContainer c, int level) {
		for (int i = 0; i < level; i++)
			this.builder.append("\t");

		this.builder.append(c.getKey() + ": " + c.getData() + "\n");
		for (CompositeContainer comp : c.getCompositums()) {
			this.printCompositum(comp, level + 1);
		}
	}

	@Override
	public String getName() {
		return "Text Box Writer";
	}
}
