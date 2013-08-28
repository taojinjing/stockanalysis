package net.frank.sk.pricebase;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

public class CsvModelTest {
	@Test
	public void testCvs() {
		String csvFile = "D:\\dev\\skprojects\\data\\SZ002242.csv";
		CsvModel cvs = new CsvModel();
		cvs.readCvs(csvFile);
		String[] row = { "2/5/2009", "11.73" };
		int rowi = 165;
		assertArrayEquals(row, cvs.getRow(rowi));

		List<String[]> newEntries = new ArrayList<String[]>();
		for (int i = 0; i < 1242; i++) {
			newEntries.add(new String[] { "0.93", "-9.87" });
		}

		cvs.setNewEntries(newEntries);
		cvs.writeToFile("D:\\dev\\skprojects\\data\\SZ002242-2.csv");

		CsvModel cvs2 = new CsvModel();
		cvs2.readCvs("D:\\dev\\skprojects\\data\\SZ002242-2.csv");
		for (int i = 0; i < 1241; i++) {
			assertEquals(cvs.getCell(i, 0), cvs2.getCell(i, 0));
			assertEquals(cvs.getCell(i, 1), cvs2.getCell(i, 1));
			assertEquals("0.93", cvs2.getCell(i, 2));
			assertEquals("-9.87", cvs2.getCell(i, 3));
		}
	}
}
