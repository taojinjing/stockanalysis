package net.frank.sk.pricebase;

public class Txt2CsvFolderConvertor {
	public static String BASE_DIR = "";

	public void folderConvertor() {
		String[] txtFiles = FileLoader.loadTxtSrcFiles();
		Txt2CsvConvertor convertor = new Txt2CsvConvertor();
		for (String txtFile : txtFiles) {
			String csvFile = FileLoader.getCvsFileNameFromTxtName(txtFile);
			convertor.convertor(txtFile, csvFile);
		}
	}
}
