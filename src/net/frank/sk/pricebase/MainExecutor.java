package net.frank.sk.pricebase;

import java.util.ArrayList;
import java.util.List;

public class MainExecutor {
	public static void main(String[] args) {
		String baseDir = FileLoader.BASE_DIR;
		String cvssrcFolder = baseDir + "\\csvsrc\\";
		String analysisFolder = baseDir + "\\analysis\\";
		Txt2CsvFolderConvertor c = new Txt2CsvFolderConvertor();
		c.folderConvertor();
		PriceBaseBuilder builder = new PriceBaseBuilder();
		String[] files = FileLoader.loadCvsSourceFiles();
		List<List<Double>> results = new ArrayList<List<Double>>();
		for (String file : files) {
			System.out.println("Starting " + file);
			builder.build(cvssrcFolder + file, analysisFolder + file);
			results.add(builder.testBuild(analysisFolder + file, file));
		}
		String file = baseDir + "results.csv";
		CsvModel model = new CsvModel();
		model.composeNewCsv(file, results);
	}
}
