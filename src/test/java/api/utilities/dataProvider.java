
package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class dataProvider {

    // DataProvider for reading all columns from Excel
    @DataProvider(name = "Data")
    public String[][] getAllData() throws IOException {

        String path = System.getProperty("user.dir")
                + "//testData//Userdata.xlsx";

        XLUsimple xl = new XLUsimple(path);

        int rownum = xl.getRowCount("Sheet1");
        int colcount = xl.getCellCount("Sheet1", 1);

        // Exclude the header row
        String[][] apidata = new String[rownum - 1][colcount];

        for (int i = 1; i < rownum; i++) {
            for (int j = 0; j < colcount; j++) {

                apidata[i - 1][j] =
                        xl.getCellData("Sheet1", i, j);
            }
        }

        return apidata;
    }

    // DataProvider for reading userNames from Excel
    @DataProvider(name = "UserNames")
    public String[] getUserNames() throws IOException {

        String path = System.getProperty("user.dir")
                + "//testData//Userdata.xlsx";

        XLUsimple xl = new XLUsimple(path);

        int rownum = xl.getRowCount("Sheet1");

        // Exclude the header row
        String[] apidata = new String[rownum - 1];

        for (int i = 1; i < rownum; i++) {

            // Column index 1 = second Excel column (B)
            apidata[i - 1] =
                    xl.getCellData("Sheet1", i, 1);
        }

        return apidata;
    }
}
