package main;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import model.Satellite;
import model.Transponder;

public class Main {

	public static void main(String[] args) throws Exception {

		StringBuffer fileContent = new StringBuffer();

		Path path = FileSystems.getDefault().getPath("", "data.json");

		InputStream inputStream = Files.newInputStream(path);
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

		int x = 5;

//		ArrayList<Satellite> distinctSatellites = new ArrayList<Satellite>();
//
//		for (Satellite satellite : s) {
//			if (distinctSatellites.isEmpty()) {
//
//			}
//		}

	}
}
