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

import aggregations.AGGREGATE_Satellite_Ger_Channel;
import aggregations.AGGREGATE_Satellite_Transponder_Count_Channels;
import aggregations.CompositeContainer;
import aggregations.IAggregate;
import model.Satellite;
import model.Transponder;

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

		IAggregate aggregate = new AGGREGATE_Satellite_Ger_Channel();
		CompositeContainer compositeContainer = aggregate.aggregate(satellitesList);

		try {
			printCompositum(compositeContainer, -1);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void printCompositum(CompositeContainer c, int level) throws Exception {

		for (int i = 0; i < level; i++) {
			// System.out.print("\t");
			this.fileWriter.write("\t");
		}

		// System.out.println(c.getData());
		this.fileWriter.write(c.getData() + "\n");

		for (CompositeContainer comp : c.getCompositums()) {
			printCompositum(comp, level + 1);
		}

	}

	public static void main(String[] args) throws Exception {
		new Main();
	}
}
