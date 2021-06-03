package controller;

import java.util.Set;

import org.reflections.Reflections;

import model.aggregations.AGGREGATE_Lang_Satellite;
import model.aggregations.IAggregate;

public class Main {

	public Main() {

		Reflections reflections = new Reflections("model.aggregations.AGGREGATE_Satellite_Transponder_Count_Channels");
		Set<Class<? extends IAggregate>> classes = reflections.getSubTypesOf(IAggregate.class);

		model.aggregations.AGGREGATE_Satellite_Transponder_Count_Channels test;

		System.out.println(classes);

//		InputStream inputStream = null;
//		try {
//			inputStream = Files.newInputStream(FileSystems.getDefault().getPath("", "data.json"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		JsonReader reader = new JsonReader(new InputStreamReader(inputStream));
//		Gson gson = new Gson();
//
//		Transponder[] transponders = gson.fromJson(reader, Transponder[].class);
//
//		ArrayList<Satellite> satellitesList = new ArrayList<Satellite>();
//
//		for (Transponder t : transponders) {
//			boolean flag = false;
//			for (Satellite satellite : satellitesList) {
//				if (satellite.getSat().equals(t.getSat())) {
//					flag = true;
//					satellite.addTransponder(t);
//				}
//			}
//			if (!flag)
//				satellitesList.add(new Satellite(t));
//		}

//		ExtensionLoader<IAggregate> loader = new ExtensionLoader<IAggregate>();
//		IAggregate aggregateTest = null;

//		try {
//			aggregateTest = loader.LoadClass("/test", "AGGREGATE_extention", IAggregate.class);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}

//		IAggregate aggregateCount = new AGGREGATE_Satellite_Transponder_Count_Channels();
//		CompositeContainerHead compositeContainer = aggregateTest.aggregate(satellitesList);

		IAggregate aggregate_lang_sat = new AGGREGATE_Lang_Satellite();

//		new SimpleFileWriter("simpleFileWriter.txt").output(compositeContainer);
//		new JSONFileWriter("JSONFileWriter.txt").output(compositeContainer);

//		new TextBoxWriter().output(compositeContainer);
//		new TextBoxWriter().output(aggregate_lang_sat.aggregate(satellitesList));

	}

	public static void main(String[] args) throws Exception {
		new Main();
	}
}
