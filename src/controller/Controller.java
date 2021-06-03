package controller;

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
import model.aggregations.AGGREGATE_Lang_Satellite;
import model.aggregations.AGGREGATE_Satellite_Ger_Channel;
import model.aggregations.AGGREGATE_Satellite_Transponder_Count_Channels;
import model.aggregations.IAggregate;
import model.containers.CompositeContainerHead;
import output.IOutput;
import output.SimpleFileWriter;
import output.TextBoxWriter;
import view.GUI;

public class Controller {

	private GUI gui;

	private ArrayList<Satellite> satellitesList;

	private ArrayList<IAggregate> aggregationModules = new ArrayList<IAggregate>();
	private ArrayList<IOutput> outputModules = new ArrayList<IOutput>();

	public Controller(String file) {
		this.gui = new GUI(this);
		this.loadJsonData(file);
		this.loadAggregationModules();
		this.loadOutputModules();
		this.gui.setModules(aggregationModules, outputModules);
	}

	public void aggregate() {
		IAggregate selectedAggregation = this.gui.getSelectedAggregation();
		IOutput selectedOutput = this.gui.getSelectedOutput();
		CompositeContainerHead aggregationResult = selectedAggregation.aggregate(satellitesList);
		selectedOutput.output(aggregationResult);
		
//		this.gui.getSelectedOutput().output(this.gui.getSelectedAggregation().aggregate(this.satellitesList));
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
		this.aggregationModules.add(new AGGREGATE_Lang_Satellite());
		this.aggregationModules.add(new AGGREGATE_Satellite_Ger_Channel());
		this.aggregationModules.add(new AGGREGATE_Satellite_Transponder_Count_Channels());

//		ExtensionLoader<IAggregate> loader = new ExtensionLoader<IAggregate>();

//		IAggregate aggregateTest = null;

//		try {
//			aggregateTest = loader.LoadClass("/test", "AGGREGATE_extention", IAggregate.class);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}

//		IAggregate aggregateCount = new AGGREGATE_Satellite_Transponder_Count_Channels();
//		CompositeContainerHead compositeContainer = aggregateTest.aggregate(satellitesList);
	}
	
	private void loadOutputModules() {
		this.outputModules.add(new SimpleFileWriter());
		this.outputModules.add(new TextBoxWriter());
	}

	public static void main(String[] args) {
		new Controller("data.json");
	}

}
