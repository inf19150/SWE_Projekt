package main;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import aggregations.AGGREGATE_Satellite_Transponder_Count_Channels;
import aggregations.CompositeContainerHead;
import aggregations.IAggregate;
import model.Satellite;
import model.Transponder;
import output.JSONFileWriter;
import output.SimpleFileWriter;
import output.TextBoxWriter;

public class Main {

	public Main() {

		InputStream inputStream = null;
		try {
			inputStream = Files.newInputStream(FileSystems.getDefault().getPath("", "data.json"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		JsonReader reader = new JsonReader(new InputStreamReader(inputStream));
		Gson gson = new Gson();

		Transponder[] transponders = gson.fromJson(reader, Transponder[].class);

		ArrayList<Satellite> satellitesList = new ArrayList<Satellite>();

		for (Transponder t : transponders) {
			boolean flag = false;
			for (Satellite satellite : satellitesList) {
				if (satellite.getSat().equals(t.getSat())) {
					flag = true;
					satellite.addTransponder(t);
				}
			}
			if (!flag)
				satellitesList.add(new Satellite(t));
		}

		ExtensionLoader<IAggregate> loader = new ExtensionLoader<IAggregate>();
		IAggregate aggregateTest = null;

		try {
			aggregateTest = loader.LoadClass("/test", "AGGREGATE_extention", IAggregate.class);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		aggregateTest = new AGGREGATE_Satellite_Transponder_Count_Channels();
		CompositeContainerHead compositeContainer = aggregateTest.aggregate(satellitesList);

		new SimpleFileWriter("simpleFileWriter.txt").output(compositeContainer);
		new JSONFileWriter("JSONFileWriter.txt").output(compositeContainer);
		new TextBoxWriter().output(compositeContainer);

	}

	public static void main(String[] args) throws Exception {
		new Main();
	}
}
