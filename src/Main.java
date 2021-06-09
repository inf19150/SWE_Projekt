import java.io.File;

import controller.Controller;

public class Main {

	/**
	 * Main, starts the program with instantiating the Controller with either an in
	 * place JSON, a as command-line argument supplied path or no path
	 * 
	 * @param args possible path of JSON file
	 */
	public static void main(String[] args) {

		Controller controller = Controller.getInstance();
		if (new File(System.getProperty("user.dir") + "/data.json").exists()) {
			controller.init("data.json");
		} else if (args.length == 1) {
			controller.init(args[0]);
		} else {
			controller.init(null);
		}
	}
}