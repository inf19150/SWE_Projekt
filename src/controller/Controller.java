package controller;

import java.io.File;
import java.util.ArrayList;

import model.Satellite;
import model.aggregations.IAggregate;
import model.containers.CompositeContainer;
import view.FileChooser;
import view.GUI;
import view.output.IOutput;

/**
 * Controller class which handles the GUI, modules and a list of objects of the
 * highest hierarchy (satellites).
 *
 */
public class Controller {

	private static Controller controller;

	private GUI gui;

	private ArrayList<Satellite> satelliteList;

	private ArrayList<IAggregate> aggregationModules;
	private ArrayList<IOutput> outputModules;

	/**
	 * Constructor of Controller, initializes GUI, Satellite, Transponder and
	 * Channel objects and modules. Constructor of Controller, asks User for the
	 * path of the JSON file and calls the other Constructor with the path.
	 * 
	 * @param file path of the given JSON file
	 */
	private Controller() {
		this.gui = GUI.getInstance();
		this.gui.setController(this);
	}

	public static Controller getInstance() {
		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}

	public void init(String file) {
		if (file == null) {
			file = Controller.getSourceFile();
		}
		this.satelliteList = new JSONLoader(file).getSatelliteList();
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
	 * @return file path of the JSON file, if not given empty String
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
	 * Fetches the selected aggregation and performs it on the data.
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
	 */
	private void loadAggregationModules() {
		ModuleLoader<IAggregate> loader = new ModuleLoader<IAggregate>("/Aggregation_Modules", IAggregate.class);
		this.aggregationModules = loader.loadClasses();
//		this.aggregationModules = new ArrayList<IAggregate>();
//		this.aggregationModules.add(new AGGREGATE_Ger_Satellite());
//		this.aggregationModules.add(new AGGREGATE_Radio_Channels());
//		this.aggregationModules.add(new AGGREGATE_Satellite_Channels_HD());
//		this.aggregationModules.add(new AGGREGATE_Satellite_Eng_Channel());
//		this.aggregationModules.add(new AGGREGATE_Satellite_Ger_Channel());
//		this.aggregationModules.add(new AGGREGATE_Satellite_Transponder_Count_Channels());
	}

	/**
	 * Takes all modules in "/Output_Modules", instantiates them and saves the
	 * objects in the outputModules list of outputs.
	 */
	private void loadOutputModules() {
		ModuleLoader<IOutput> loader = new ModuleLoader<IOutput>("/Output_Modules", IOutput.class);
		this.outputModules = loader.loadClasses();
//		this.outputModules = new ArrayList<IOutput>();
//		this.outputModules.add(new SimpleFileWriter());
//		this.outputModules.add(new JSONFileWriter());
//		this.outputModules.add(new TextBoxWriter());
	}
}
