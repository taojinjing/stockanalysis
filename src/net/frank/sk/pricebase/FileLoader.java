package net.frank.sk.pricebase;

import java.io.File;

public class FileLoader {
	public static String BASE_DIR = "D:\\dev\\skprojects\\data\\";

	public static String[] loadCvsSourceFiles() {
		File baseFolder = new File(BASE_DIR + "\\csvsrc\\");
		File[] files = baseFolder.listFiles();
		String[] fileName = new String[files.length];
		for (int i = 0; i < files.length; i++) {
			fileName[i] = files[i].getName();
		}
		return fileName;
	}

	public static String[] loadTxtSrcFiles() {
		File baseFolder = new File(BASE_DIR + "\\src\\");
		File[] files = baseFolder.listFiles();
		String[] fileName = new String[files.length];
		for (int i = 0; i < files.length; i++) {
			fileName[i] = files[i].getAbsolutePath();
			System.out.println(fileName[i]);
		}
		return fileName;
	}

	public static String getCvsFileNameFromTxtName(String txtFileName) {
		File file = new File(txtFileName);
		String fileName = file.getName();
		fileName = fileName.replaceFirst("TXT", "csv");
		fileName = FileLoader.BASE_DIR + "\\csvsrc\\" + fileName;
		return fileName;
	}
}
