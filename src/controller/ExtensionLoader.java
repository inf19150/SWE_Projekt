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
 * https://stackabuse.com/example-loading-a-java-class-at-runtime
 * 
 * @param directory Directory where the modules are in 
 * @param <C> Type of the module (parent class)
 * 
 * @return List of the instantiated classes
 */
public class ExtensionLoader<C> {

	public ArrayList<C> LoadClasses(String directory, Class<C> parentClass) {
		ArrayList<C> plugins = new ArrayList<C>();
		File pluginsDir = new File(System.getProperty("user.dir") + directory);
		for (File jar : pluginsDir.listFiles()) {
			try {

				ClassLoader loader = URLClassLoader.newInstance(new URL[] { jar.toURI().toURL() },
						this.getClass().getClassLoader());
				Class<?> clazz = Class.forName(jar.getName().split("\\.")[0], true, loader);
				Class<? extends C> newClass = clazz.asSubclass(parentClass);
				// Apparently its bad to use Class.newInstance, so we use
				// newClass.getConstructor() instead
				Constructor<? extends C> constructor = newClass.getConstructor();
				plugins.add(constructor.newInstance());

			} catch (ClassNotFoundException e) {
				// There might be multiple JARs in the directory,
				// so keep looking
				continue;
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
		}
		return plugins;
	}
}