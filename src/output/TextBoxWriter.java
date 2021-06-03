package output;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import aggregations.CompositeContainer;
import aggregations.CompositeContainerHead;

public class TextBoxWriter extends JFrame implements IOutput {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4098967462709374739L;
	private StringBuilder builder;
	private JTextArea textArea;

	public TextBoxWriter() {
		super("TextBox Writer");
		super.setSize(1280, 720);

		this.builder = new StringBuilder();

		this.textArea = new JTextArea();
		this.textArea.setEditable(false);
		DefaultCaret caret = (DefaultCaret) this.textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		JScrollPane jScrollPaneChat = new JScrollPane(this.textArea);

		super.getContentPane().add(jScrollPaneChat, BorderLayout.CENTER);

		super.setVisible(true);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void output(CompositeContainerHead container) {
		for (CompositeContainer comp : container.getCompositums()) {
			printCompositum(comp, 0);
		}
		this.textArea.setText(this.builder.toString());
	}

	private void printCompositum(CompositeContainer c, int level) {
		for (int i = 0; i < level; i++)
			this.builder.append("\t");

		this.builder.append(c.getKey() + ": " + c.getData() + "\n");
		for (CompositeContainer comp : c.getCompositums()) {
			this.printCompositum(comp, level + 1);
		}

	}
}
