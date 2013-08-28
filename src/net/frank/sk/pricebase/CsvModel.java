package net.frank.sk.pricebase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvModel {
	List<String[]> entries = new ArrayList<String[]>();
	List<String[]> newEntries = new ArrayList<String[]>();

	public void readCvs(String csvFile) {
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		entries.clear();
		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				String[] entry = line.split(cvsSplitBy);
				entries.add(entry);
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
	}

	public String getCell(int row, int column) {
		String[] cells = getRow(row);
		if (column + 1 > cells.length) {
			return "";
		} else {
			return cells[column];
		}
	}

	public String[] getRow(int row) {
		return entries.get(row);
	}

	public void setNewEntries(List<String[]> newEntries) {
		this.newEntries = newEntries;
	}

	public int getRowCount() {
		return entries.size();
	}

	public int getColumnCount() {
		return entries.get(0).length;
	}

	public void writeToFile(String csvFile) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(csvFile));
			for (int i = 0; i < entries.size(); i++) {
				String[] entry = entries.get(i);

				String line = "";
				for (String s : entry) {
					line += s + ",";
				}
				if (newEntries.size() > i) {
					String[] newEntry = newEntries.get(i);
					for (String k : newEntry) {
						line += k + ",";
					}
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

	public void composeNewCsv(String file, List<List<Double>> lists) {
		int columnCount = 0;
		for (List<Double> list : lists) {
			if (columnCount < list.size()) {
				columnCount = list.size();
			}
		}
		List<String[]> entries = new ArrayList<String[]>();

		for (int i = 0; i < columnCount; i++) {
			String[] columns = new String[lists.size()];
			for (int j = 0; j < lists.size(); j++) {
				List<Double> list = lists.get(j);
				if (i < list.size()) {
					columns[j] = String.valueOf(list.get(i));
				} else {
					columns[j] = "";
				}
			}
			entries.add(columns);
		}
		this.entries = entries;
		this.newEntries.clear();
		this.writeToFile(file);
	}
}