package view;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controller.Controller;
import misc.Constants;
import model.aggregations.IAggregate;
import view.output.IOutput;

/**
 * GUI class inherits {@link JFrame} and implements {@link ActionListener} for
 * buttons
 * 
 * @author Nick
 *
 */
public class GUI extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 732987927918732005L;

	private Controller controller;
	private JComboBox<IAggregate> comboBoxAggregation;
	private JComboBox<IOutput> comboBoxOutput;
	private JButton btnAggregate;
	private JButton btnHelp;
	private JButton btnReload;

	/**
	 * Creates GUI
	 * 
	 * @param controller to be passed in
	 */
	public GUI(Controller controller) {
		super();
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/assets/logo.png")));
		setResizable(false);
		this.controller = controller;

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		setTitle("DHBW SWT-Projekt");
		getContentPane().setLayout(null);

		JPanel panelMenu = new JPanel();
		panelMenu.setBounds(0, 0, 534, 33);
		FlowLayout flowLayout_1 = (FlowLayout) panelMenu.getLayout();
		flowLayout_1.setHgap(0);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panelMenu.setBackground(Color.DARK_GRAY);
		getContentPane().add(panelMenu);

		btnHelp = new JButton("Help");
		btnHelp.addActionListener(this);

		btnReload = new JButton("Reload Modules");
		btnReload.addActionListener(this);
		panelMenu.add(btnReload);
		panelMenu.add(btnHelp);

		comboBoxAggregation = new JComboBox<IAggregate>();
		comboBoxAggregation.setBounds(20, 100, 200, 30);
		getContentPane().add(comboBoxAggregation);

		JLabel lblAggregation = new JLabel("Aggregationsmodules");
		lblAggregation.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAggregation.setBounds(20, 56, 168, 33);
		getContentPane().add(lblAggregation);

		JLabel lblOutput = new JLabel("Outputmodules");
		lblOutput.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblOutput.setBounds(300, 56, 168, 33);
		getContentPane().add(lblOutput);

		comboBoxOutput = new JComboBox<IOutput>();
		comboBoxOutput.setBounds(300, 100, 200, 30);
		getContentPane().add(comboBoxOutput);

		btnAggregate = new JButton("Aggregate!");
		btnAggregate.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnAggregate.setBounds(195, 180, 150, 80);
		this.btnAggregate.addActionListener(this);
		getContentPane().add(btnAggregate);
		this.initialize();

	}

	public void setModules(ArrayList<IAggregate> aggregationModules, ArrayList<IOutput> outputModules) {
		this.comboBoxAggregation.removeAllItems();
		for (IAggregate iAggregate : aggregationModules) {
			this.comboBoxAggregation.addItem(iAggregate);
		}
		this.comboBoxAggregation.setRenderer(new ComboBoxRenderer());

		this.comboBoxOutput.removeAllItems();
		for (IOutput iOutput : outputModules) {
			this.comboBoxOutput.addItem(iOutput);
		}
		this.comboBoxOutput.setRenderer(new ComboBoxRenderer());
	}

	public IOutput getSelectedOutput() {
		return (IOutput) this.comboBoxOutput.getSelectedItem();
	}

	public IAggregate getSelectedAggregation() {
		return (IAggregate) this.comboBoxAggregation.getSelectedItem();
	}

	/**
	 * Initialized Windows
	 */
	private void initialize() {
		setBounds(100, 100, 527, 320);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object src = e.getSource();
		if (src.equals(this.btnAggregate)) {
			this.controller.aggregate();
		} else if (src.equals(this.btnHelp)) {
			Desktop desktop = java.awt.Desktop.getDesktop();
			try {
				desktop.browse(new URI(Constants.HELP_URL));
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
		} else if (src.equals(this.btnReload)) {
			this.controller.initModules();
		}

	}
}
