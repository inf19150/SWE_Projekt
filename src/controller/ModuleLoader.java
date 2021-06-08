package controller;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

/**
 * Loads modules from a given directory with a given parent class.
 * @see https://stackabuse.com/example-loading-a-java-class-at-runtime
 * 
 * @param directory Directory where the modules are in
 * @param <C>       Type of the module (parent class)
 * 
 * @return List of the instantiated classes
 */
public class ModuleLoader<C> {

	private String directory;
	private Class<C> parentClass;

	public ModuleLoader(String directory, Class<C> parentClass) {
		this.directory = directory;
		this.parentClass = parentClass;
	}

	public ArrayList<C> LoadClasses() {
		ArrayList<C> modules = new ArrayList<C>();
		File modulesDir = new File(System.getProperty("user.dir") + this.directory);
		for (File jar : modulesDir.listFiles()) {
			try {

				ClassLoader loader = URLClassLoader.newInstance(new URL[] { jar.toURI().toURL() },
                        this.getClass().getClassLoader());
                Class<?> clazz = Class.forName(jar.getName().split("\\.")[0], true, loader);
                Class<? extends C> newClass = clazz.asSubclass(this.parentClass);
                // Apparently its bad to use Class.newInstance, so we use
                // newClass.getConstructor() instead
                Constructor<? extends C> constructor = newClass.getConstructor();
                modules.add(constructor.newInstance());

			} catch (ClassNotFoundException e) {
				continue;
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException | MalformedURLException e) {
				System.err.println(
						"ERROR while loading module: " + jar.getAbsolutePath() + "\n(" + e.getLocalizedMessage() + ")");
			}
		}
		return modules;
	}
}