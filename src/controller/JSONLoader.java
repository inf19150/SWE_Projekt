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

public class JSONLoader {
	
	private String file;
	
	public JSONLoader(String file) {
		this.file = file;
	}
	
	/**
	 * Creates Satellite, Transponder and Channel objects with the right hierarchy.
	 * 
	 */
	public ArrayList<Satellite> getSatelliteList() {
		InputStream inputStream = null;
		try {
			inputStream = Files.newInputStream(FileSystems.getDefault().getPath(this.file));
		} catch (IOException e) {
			e.printStackTrace();
		}

		JsonReader reader = new JsonReader(new InputStreamReader(inputStream));
		Gson gson = new Gson();

		Transponder[] transponders = gson.fromJson(reader, Transponder[].class);

		ArrayList<Satellite> satelliteList = new ArrayList<Satellite>();

		for (Transponder t : transponders) {
			boolean flag = false;
			for (Satellite satellite : satelliteList) {
				if (satellite.getSat().equals(t.getSat())) {
					flag = true;
					satellite.addTransponder(t);
				}
			}
			if (!flag)
				satelliteList.add(new Satellite(t));
		}
		return satelliteList;
	}	
}
