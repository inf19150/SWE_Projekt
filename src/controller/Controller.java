package controller;

import java.io.File;
import java.util.ArrayList;

import model.Satellite;
import model.aggregations.IAggregate;
import model.container.CompositeContainer;
import view.FileChooser;
import view.GUI;
import view.output.IOutput;

/**
 * Controller class which handles the GUI, modules and a list of objects of the
 * highest hierarchy (satellites).
 *
 */
public class Controller {

	/**
	 * Singleton instance of Controller
	 */
	private static Controller controller;

	private GUI gui;

	private ArrayList<Satellite> satelliteList;

	private ArrayList<IAggregate> aggregationModules;
	private ArrayList<IOutput> outputModules;

	/**
	 * Constructor of Controller, holds GUI instance and passes itself to the GUI.
	 * 
	 */
	private Controller() {
		this.gui = GUI.getInstance();
		this.gui.setController(this);
	}

	/**
	 * Singleton getter for Controller, instantiates itself if and only if not
	 * already existent.
	 * 
	 * @return controller {@link Controller} object
	 */
	public static Controller getInstance() {
		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}

	/**
	 * Makes sure file path to JSON file is existent and gets list of Satellites.
	 * 
	 * @param file path of the given JSON file or null if not given
	 */
	public void init(String file) {
		if (file == null) {
			file = Controller.getSourceFile();
		}
		this.satelliteList = JSONLoader.getSatelliteList(file);
		this.initModules();
	}

	/**
	 * Loads aggregation and output modules and passes them to the GUI.
	 * 
	 */
	public void initModules() {
		this.loadAggregationModules();
		this.loadOutputModules();
		this.gui.setModules(aggregationModules, outputModules);
	}

	/**
	 * Asks the user for the file path of the JSON.
	 * 
	 * @return file path of the JSON file, if not given program exits with status
	 *         -42
	 */
	private static String getSourceFile() {
		FileChooser fileChooser = new FileChooser(System.getProperty("user.home"),
				"Chose json file containing satellite data!", "json");
		File file = fileChooser.getFile();
		if (file == null) {
			System.exit(-42);
		}
		return file.getAbsolutePath();
	}

	/**
	 * Fetches the selected method of aggregation and performs it on the data.
	 * 
	 */
	public void aggregate() {
		IAggregate selectedAggregation = this.gui.getSelectedAggregation();
		this.output(selectedAggregation.aggregate(satelliteList));
	}

	/**
	 * Fetches the selected method of output and outputs the result of the
	 * aggregation.
	 * 
	 * @param aggregationResult The result of the aggregation
	 */
	private void output(CompositeContainer aggregationResult) {
		IOutput selectedOutput = this.gui.getSelectedOutput();
		selectedOutput.output(aggregationResult);
		selectedOutput.reset();
	}

	/**
	 * Takes all modules in "/Aggregation_Modules", instantiates them and saves the
	 * objects in the aggregationModules list of aggregates.
	 * 
	 */
	private void loadAggregationModules() {
		this.aggregationModules = new ModuleLoader<IAggregate>("/Aggregation_Modules", IAggregate.class).loadClasses();
	}

	/**
	 * Takes all modules in "/Output_Modules", instantiates them and saves the
	 * objects in the outputModules list of outputs.
	 * 
	 */
	private void loadOutputModules() {
		this.outputModules = new ModuleLoader<IOutput>("/Output_Modules", IOutput.class).loadClasses();
	}
}
