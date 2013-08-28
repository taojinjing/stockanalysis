package net.frank.sk.pricebase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Txt2CsvConvertor {
	public void convertor(String txtFile, String csvFile) {
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		List<String[]> rows = new ArrayList<String[]>();
		try {
			br = new BufferedReader(new FileReader(txtFile));
			while ((line = br.readLine()) != null) {
				String[] entry = line.split(cvsSplitBy);
				if (entry.length > 4) {
					String[] newEntry = new String[2];
					newEntry[0] = entry[0];
					newEntry[1] = entry[4];
					rows.add(newEntry);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		composeCsv(csvFile, rows);
	}

	public void composeCsv(String file, List<String[]> rows) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(file));
			for (int i = 0; i < rows.size(); i++) {
				String[] entry = rows.get(i);

				String line = "";
				for (String s : entry) {
					line += s + ",";
				}
				line += "\n";
				bw.write(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
