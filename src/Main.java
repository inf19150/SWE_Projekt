import java.io.File;

import controller.Controller;

/**
 * Class Main is the run target class and contains the main function
 *
 */
public class Main {

	/**
	 * Main, starts the program with the instantiation of the Controller either with
	 * an existing JSON, a path provided as command line argument or without a path.
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