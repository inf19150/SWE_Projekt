package view;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controller.Controller;
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

	/**
	 * Creates GUI
	 * 
	 * @param controller to be passed in
	 */
	public GUI(Controller controller) {
		super();
		setType(Type.UTILITY);
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
		panelMenu.setBounds(0, 0, 741, 45);
		FlowLayout flowLayout_1 = (FlowLayout) panelMenu.getLayout();
		flowLayout_1.setHgap(0);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panelMenu.setBackground(Color.DARK_GRAY);
		getContentPane().add(panelMenu);

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		menuBar.setBackground(Color.DARK_GRAY);
		panel.add(menuBar);

		panelMenu.add(panel);

		comboBoxAggregation = new JComboBox<IAggregate>();
		comboBoxAggregation.setBounds(10, 139, 293, 31);
		getContentPane().add(comboBoxAggregation);

		JLabel lblAggregation = new JLabel("Aggregationsmodules");
		lblAggregation.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAggregation.setBounds(10, 56, 168, 33);
		getContentPane().add(lblAggregation);

		JLabel lblOutput = new JLabel("Outputmodules");
		lblOutput.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblOutput.setBounds(210, 56, 168, 33);
		getContentPane().add(lblOutput);

		comboBoxOutput = new JComboBox<IOutput>();
		comboBoxOutput.setBounds(210, 89, 256, 31);
		getContentPane().add(comboBoxOutput);

		btnAggregate = new JButton("Aggregate!");
		btnAggregate.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnAggregate.setBounds(210, 170, 157, 80);
		this.btnAggregate.addActionListener(this);
		getContentPane().add(btnAggregate);
		this.initialize();

	}

	public void setModules(ArrayList<IAggregate> aggregationModules, ArrayList<IOutput> outputModules) {
		for (IAggregate iAggregate : aggregationModules) {
			this.comboBoxAggregation.addItem(iAggregate);
		}
		this.comboBoxAggregation.setRenderer(new ComboBoxRenderer());

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
		setBounds(100, 100, 496, 333);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object src = e.getSource();
		if (src.equals(this.btnAggregate)) {
			this.controller.aggregate();
		}

	}
}
