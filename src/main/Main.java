package main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import aggregations.*;
import model.Satellite;
import model.Transponder;
import output.JSONFileWriter;
import output.SimpleFileWriter;

public class Main {

	private FileWriter fileWriter;

	public Main() {
		try {
			this.fileWriter = new FileWriter("out.dat");
		} catch (IOException e) {
			e.printStackTrace();
		}
		StringBuffer fileContent = new StringBuffer();

		Path path = FileSystems.getDefault().getPath("", "data.json");

		InputStream inputStream = null;
		try {
			inputStream = Files.newInputStream(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
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

		IAggregate aggregate = new AGGREGATE_Satellite_Transponder_Count_Channels();
		CompositeContainerHead compositeContainer = aggregate.aggregate(satellitesList);
		
		new SimpleFileWriter("simpleFileWriter.txt").output(compositeContainer);	
		new JSONFileWriter("JSONFileWriter.txt").output(compositeContainer);

	}

	public static void main(String[] args) throws Exception {
		new Main();
	}
}
