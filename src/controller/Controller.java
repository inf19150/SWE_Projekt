package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import model.Satellite;
import model.Transponder;
import model.aggregations.IAggregate;
import model.containers.CompositeContainerHead;
import view.FileChooser;
import view.GUI;
import view.output.IOutput;

/**
 * Controller class which handles the GUI, modules and a list of objects of the
 * highest hierarchy (satellites).
 *
 */
public class Controller {

	private GUI gui;

	private ArrayList<Satellite> satellitesList;

	private ArrayList<IAggregate> aggregationModules;
	private ArrayList<IOutput> outputModules;

	/**
	 * Constructor of Controller, initializes GUI, Satellite, Transponder and
	 * Channel objects and modules.
	 * 
	 * @param file path of the given JSON file
	 */
	public Controller(String file) {
		this.gui = new GUI(this);
		this.loadJsonData(file);
		this.initModules();
	}

	/**
	 * Constructor of Controller, asks User for the path of the JSON file and calls
	 * the other Constructor with the path.
	 */
	public Controller() {
		this(getSourceFile());
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
		int result = fileChooser.showOpenDialog(null);

		if (result == FileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile().getAbsolutePath();
		}
		return "";
	}

	/**
	 * Fetches the selected aggregation and performs it on the data.
	 */
	public void aggregate() {
		IAggregate selectedAggregation = this.gui.getSelectedAggregation();
		this.output(selectedAggregation.aggregate(satellitesList));
	}

	/**
	 * Fetches the selected method of output and outputs the result of the
	 * aggregation.
	 * 
	 * @param aggregationResult The result of the aggregation
	 */
	public void output(CompositeContainerHead aggregationResult) {
		IOutput selectedOutput = this.gui.getSelectedOutput();
		selectedOutput.output(aggregationResult);
	}

	/**
	 * Creates Satellite, Transponder and Channel objects with the right hierarchy.
	 * 
	 * @param file path of the JSON file
	 */
	private void loadJsonData(String file) {
		InputStream inputStream = null;
		try {
			inputStream = Files.newInputStream(FileSystems.getDefault().getPath("", file));
		} catch (IOException e) {
			e.printStackTrace();
		}

		JsonReader reader = new JsonReader(new InputStreamReader(inputStream));
		Gson gson = new Gson();

		Transponder[] transponders = gson.fromJson(reader, Transponder[].class);

		this.satellitesList = new ArrayList<Satellite>();

		for (Transponder t : transponders) {
			boolean flag = false;
			for (Satellite satellite : satellitesList) {
				if (satellite.getSat().equals(t.getSat())) {
					flag = true;
					satellite.addTransponder(t);
				}
			}
			if (!flag)
				this.satellitesList.add(new Satellite(t));
		}
	}

	/**
	 * Takes all modules in "/Aggregation_Modules", instantiates them and saves the
	 * objects in the aggregationModules list of aggregates.
	 */
	private void loadAggregationModules() {
		ExtensionLoader<IAggregate> loader = new ExtensionLoader<IAggregate>();
		this.aggregationModules = loader.LoadClasses("/Aggregation_Modules", IAggregate.class);
	}

	/**
	 * Takes all modules in "/Output_Modules", instantiates them and saves the
	 * objects in the outputModules list of outputs.
	 */
	private void loadOutputModules() {
		ExtensionLoader<IOutput> loader = new ExtensionLoader<IOutput>();
		this.outputModules = loader.LoadClasses("/Output_Modules", IOutput.class);
	}

	/**
	 * Main, starts the program with instantiating the Controller with either an in
	 * place JSON, a as command-line argument supplied path or no path
	 * 
	 * @param args possible path of JSON file
	 */
	public static void main(String[] args) {

		if (new File(System.getProperty("user.dir") + "/data.json").exists()) {
			new Controller("data.json");
		} else if (args.length == 1) {
			new Controller(args[0]);
		} else {
			new Controller();
		}
	}
}
