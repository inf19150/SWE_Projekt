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

public class Controller {

	private GUI gui;

	private ArrayList<Satellite> satellitesList;

	private ArrayList<IAggregate> aggregationModules;
	private ArrayList<IOutput> outputModules;

	public Controller(String file) {
		this.gui = new GUI(this);
		this.loadJsonData(file);
		this.initModules();
	}

	public Controller() {
		this(getSourceFile());
	}

	public void initModules() {
		this.loadAggregationModules();
		this.loadOutputModules();
		this.gui.setModules(aggregationModules, outputModules);
	}

	private static String getSourceFile() {
		FileChooser fileChooser = new FileChooser(System.getProperty("user.home"),
				"Chose json file containing satellite data!", "json");
		int result = fileChooser.showOpenDialog(null);

		if (result == FileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile().getAbsolutePath();
		}
		return "";
	}

	public void aggregate() {
		IAggregate selectedAggregation = this.gui.getSelectedAggregation();
		IOutput selectedOutput = this.gui.getSelectedOutput();
		CompositeContainerHead aggregationResult = selectedAggregation.aggregate(satellitesList);
		selectedOutput.output(aggregationResult);
	}

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

	private void loadAggregationModules() {
		ExtensionLoader<IAggregate> loader = new ExtensionLoader<IAggregate>();
		this.aggregationModules = loader.LoadClasses("/Aggregation_Modules", IAggregate.class);
	}

	private void loadOutputModules() {
		ExtensionLoader<IOutput> loader = new ExtensionLoader<IOutput>();
		this.outputModules = loader.LoadClasses("/Output_Modules", IOutput.class);
	}

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
