package net.frank.sk.pricebase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PriceBaseBuilder {

	public void build(String inputFile, String outputFile) {
		CsvModel cvs = new CsvModel();
		cvs.readCvs(inputFile);
		int rowCount = cvs.getRowCount();
		Double[] prices = new Double[rowCount];
		List<String[]> newEntries = new ArrayList<String[]>();
		PriceBase pb = new PriceBase();
		List<Double> priceList = Arrays.asList(prices);
		boolean buy = false;
		boolean sale = false;
		for (int row = 0; row < rowCount; row++) {
			prices[row] = Double.valueOf(cvs.getCell(row, 1));
			int d = pb.findRange(priceList, row);
			if (d == PriceBase.NO_MATCH || d == PriceBase.BOTTON_OR_TOP
					|| (buy && d > 0) || (sale && d < 0)) {
				newEntries.add(new String[] { "", "" });
			} else {
				if (d < 0) {
					buy = false;
					sale = true;
					newEntries.add(new String[] { "",
							String.valueOf(prices[row]) });
				} else {
					sale = false;
					buy = true;
					newEntries.add(new String[] { String.valueOf(prices[row]),
							"" });
				}

			}
		}
		cvs.setNewEntries(newEntries);

		cvs.writeToFile(outputFile);
	}

	public List<Double> testBuild(String file, String tag) {
		CsvModel cvs = new CsvModel();
		cvs.readCvs(file);
		int rowCount = cvs.getRowCount();
		int buyColumn = 2;
		int saleColumn = 3;

		String readyBuy = null;
		String buyDate = null;
		double sum = 0;
		List<Double> result = new ArrayList<Double>();
		for (int i = 0; i < rowCount; i++) {
			String buy = cvs.getCell(i, buyColumn);
			String sale = cvs.getCell(i, saleColumn);
			if (!buy.trim().equals("")) {
				readyBuy = buy;
				buyDate = cvs.getCell(i, 0);
			}
			if (!"".equals(sale.trim()) && readyBuy != null) {
				double own = (Double.valueOf(sale) / Double.valueOf(readyBuy) - 1);
				if (own < -0.099999) {
					System.out.println(tag + "\t" + buyDate + " - "
							+ cvs.getCell(i, 0) + "\t" + own);
				}
				sum += own;
				result.add(sum);
				readyBuy = null;
			}
		}
		return result;
	}
}
