
package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class dataProvider {

	// DataProvider for reading all columns from Excel
	@DataProvider(name = "Data")
	public String[][] getAllData() throws IOException {

		String path = System.getProperty("user.dir") + "//testData//Userdata.xlsx";

		XLUsimple xl = new XLUsimple(path);

		int rownum = xl.getRowCount("Users");
		int colcount = xl.getCellCount("Users", 1);

		// Exclude the header row
		String[][] apidata = new String[rownum - 1][colcount];

		for (int i = 1; i < rownum; i++) {
			for (int j = 0; j < colcount; j++) {

				apidata[i - 1][j] = xl.getCellData("Users", i, j);
			}
		}

		return apidata;
	}

	// DataProvider for reading userNames from Excel
	@DataProvider(name = "UserNames")
	public String[] getUserNames() throws IOException {

		String path = System.getProperty("user.dir") + "//testData//Userdata.xlsx";

		XLUsimple xl = new XLUsimple(path);

		int rownum = xl.getRowCount("Users");

		// Exclude the header row
		String[] apidata = new String[rownum - 1];

		for (int i = 1; i < rownum; i++) {

			// Column index 1 = second Excel column (B)
			apidata[i - 1] = xl.getCellData("Users", i, 1);
		}

		return apidata;
	}

	// =====================================================
	// PET MODULE - DATA PROVIDER
	// =====================================================

	@DataProvider(name = "PetData")
	public String[][] getPetData() throws IOException {

		String path = System.getProperty("user.dir") + "//testData//Userdata.xlsx";

		XLUsimple xl = new XLUsimple(path);

		int rownum = xl.getRowCount("Pets");
		int colcount = xl.getCellCount("Pets", 0);

		// Exclude the header row
		String[][] apidata = new String[rownum - 1][colcount];

		for (int i = 1; i < rownum; i++) {
			for (int j = 0; j < colcount; j++) {

				apidata[i - 1][j] = xl.getCellData("Pets", i, j);
			}
		}

		return apidata;
	}
	
	// DataProvider for reading petIDs from Excel

	@DataProvider(name = "PetIDs")
	public String[] getPetIDs() throws IOException {

	    String path = System.getProperty("user.dir")
	            + "//testData//Userdata.xlsx";

	    XLUsimple xl = new XLUsimple(path);

	    int rownum = xl.getRowCount("Pets");

	    // Exclude the header row
	    String[] apidata = new String[rownum - 1];

	    for (int i = 1; i < rownum; i++) {

	        // Column index 0 = first Excel column (A) = PetID
	        apidata[i - 1] = xl.getCellData("Pets", i, 0);
	    }

	    return apidata;
	}
}
