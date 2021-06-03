package view.output;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.containers.CompositeContainer;
import model.containers.CompositeContainerHead;

public class JSONFileWriter implements IOutput {

	private FileWriter fw;
	JSONObject sampleObject = new JSONObject();

	public JSONFileWriter(String fileName) {
		try {
			fw = new FileWriter(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void output(CompositeContainerHead container) {
		try {
			for (CompositeContainer comp : container.getCompositums()) {
				printCompositum(comp);
			}
			fw.write(sampleObject.toJSONString());
			this.fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void printCompositum(CompositeContainer c) throws IOException {

		this.sampleObject = new JSONObject();
		this.sampleObject.put(c.getKey(), c.getData());

		JSONArray messages = new JSONArray();
		for (CompositeContainer comp : c.getCompositums()) {
			if (comp.getSize() > 1) {
				for (CompositeContainer temp : comp.getCompositums()) {
					messages.add(temp.getData());
				}
				this.sampleObject.put("records", messages);
			}

			printCompositum(comp);
		}
	}
	@Override
	public String getName() {
		return "JSON File Writer";
	}
}
