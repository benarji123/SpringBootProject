package com.techmojo.web.automation.util;

import java.util.HashMap;

public class TestDataProvider {

	private TestDataProvider() {
	}

	/************** To the Data for TestCase ******************/
	public static Object[][] getTestData(String dataFileName, String sheetName, String testName) {
		Object[][] dataSet = null;
		try {
			ReadExcelDataFile readdata = new ReadExcelDataFile(
					System.getProperty("user.dir") + "//src//test//resources//data//" + dataFileName);

			// Find Start Row of TestCase
			int startRowNum = 0;
			while (!readdata.getCellData(sheetName, 0, startRowNum).equalsIgnoreCase(testName)) {
				startRowNum++;
			}

			int startTestColumn = startRowNum + 1;
			int startTestRow = startRowNum + 2;

			// Find Number of Rows of TestCase
			int rows = 0;
			while (!readdata.getCellData(sheetName, 0, startTestRow + rows).equals("")) {
				rows++;
			}

			// Find Number of Columns in Test
			int colmns = 0;
			while (!readdata.getCellData(sheetName, colmns, startTestColumn).equals("")) {
				colmns++;
			}

			// Define Two Object Array
			dataSet = new Object[rows][1];
			HashMap<String, String> dataTable = null;
			int dataRowNumber = 0;
			for (int rowNumber = startTestRow; rowNumber <= startTestColumn + rows; rowNumber++) {
				dataTable = new HashMap<>();
				for (int colNumber = 0; colNumber < colmns; colNumber++) {
					String key = readdata.getCellData(sheetName, colNumber, startTestColumn);
					String value = readdata.getCellData(sheetName, colNumber, rowNumber);
					dataTable.put(key, value);
				}
				dataSet[dataRowNumber][0] = dataTable;
				dataRowNumber++;
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return dataSet;
	}
}