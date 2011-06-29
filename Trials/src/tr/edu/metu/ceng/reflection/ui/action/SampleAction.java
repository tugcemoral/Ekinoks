package tr.edu.metu.ceng.reflection.ui.action;

import java.io.File;

import org.apache.commons.lang.StringUtils;

public class SampleAction {

	private static final String MODEL = ".model.";
	private static final String POJO = ".pojo.";

	public static void main(String[] args) {

		SampleAction action = new SampleAction();

		// action.fileOutProperties();
		// action.packageOutProperties();
		action.enhanceOutProperties();
	}

	private void enhanceOutProperties() {

		// extract the pojo name from Action
		String pojoName = StringUtils.substringBeforeLast(this.getClass()
				.getSimpleName(), "Action");

		// // get root package (up-level is 2) of action class.
		// String rootPackageName = StringUtils.substringBeforeLast(StringUtils
		// .substringBeforeLast(StringUtils.substringBeforeLast(
		// this.getClass().getName(), "."), "."), ".");
		//
		// System.out.println(rootPackageName);
		//
		// File rootPackageFile = new File(rootPackageName);
		//
		// System.out.println(rootPackageFile.getPath());

		// String[] listFiles = rootPackageFile.getPalist();
		//
		// for (String file : listFiles) {
		// System.out.println(file);
		// }

		File clazzFile = new File(this.getClass().getResource("").getFile());

		// get parent package of action in runtime.
		File parentPackage = new File(clazzFile.getParentFile().getParentFile()
				.getPath());

		// get files list of the parent package.
		File[] filesList = parentPackage.listFiles();
		Object recurseTraverse = recurseTraverse(filesList, pojoName);
		
		System.out.println(recurseTraverse);

	}

	private Object recurseTraverse(File[] filesList, String pojoName) {
		// traverse files list of the parent package.
		for (File file : filesList) {
			if (file.isDirectory()) {
				// System.out.println("folder: " + file.getName());
				// System.out.println("  " + file.getPath());
				if (file.listFiles() != null) {
					System.out.println("searching: " + file.getPath());
					recurseTraverse(file.listFiles(), pojoName);
				}
			} else {
				// System.out.println("file: " + file.getName());

				String actualFileName = StringUtils.substringBeforeLast(
						file.getName(), ".");

				if (StringUtils.equalsIgnoreCase(actualFileName, pojoName)) {
					String className = StringUtils.substringBeforeLast(
							(StringUtils.substringAfter(file.getPath(), "bin/")
									.replace(File.separatorChar, '.')), ".");

					Object newInstance = null;
					try {
						newInstance = Class.forName(className).newInstance();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}

					return newInstance;

				}

			}
		}
		return null;
	}

	private void packageOutProperties() {

		String actionClassName = this.getClass().getName();

		// extract the pojo name from Action
		String pojoName = StringUtils.substringBeforeLast(this.getClass()
				.getSimpleName(), "Action");

		String rootPackageName = StringUtils.substringBeforeLast(
				StringUtils.substringBeforeLast(actionClassName, "."), ".");

		Object pojo = null;

		try {
			pojo = Class.forName(rootPackageName + MODEL + pojoName)
					.newInstance();
		} catch (ClassNotFoundException e) {
			try {
				pojo = Class.forName(rootPackageName + POJO + pojoName)
						.newInstance();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (InstantiationException ie) {
				ie.printStackTrace();
			} catch (IllegalAccessException iae) {
				iae.printStackTrace();
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		System.out.println(pojo);

	}

	private void fileOutProperties() {

		try {
			Thread.currentThread().getContextClassLoader()
					.loadClass("tr.edu.metu.ceng.reflection.model.Sample");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		// extract the pojo name from Action
		String pojoName = StringUtils.substringBeforeLast(this.getClass()
				.getSimpleName(), "Action");

		// get file of action class' instance.
		File clazzFile = new File(this.getClass().getResource("").getFile());
		// get parent package of action in runtime.
		File parentPackage = new File(clazzFile.getParentFile().getPath());

		// get files list of the parent package.
		File[] filesList = parentPackage.listFiles();
		// traverse files list of the parent package.
		for (File file : filesList) {
			// check that actual directory name includes "model" name.
			if (file.isDirectory()
					&& StringUtils.containsIgnoreCase(file.getName(), "model")) {
				// change into this directory.
				File[] pojoFiles = file.listFiles();

				for (File pojoFile : pojoFiles) {
					System.out.println("pojoFile name: " + pojoFile.getName());
					if (StringUtils.containsIgnoreCase(pojoFile.getName(),
							pojoName)) {

						try {
							@SuppressWarnings("unchecked")
							Class<?> pojoClass = (Class<?>) Class
									.forName(pojoFile.getAbsolutePath());
							System.out.println(pojoClass.getName());
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}

					}
				}
			}
		}

	}

}
