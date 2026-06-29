package utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider(name = "Admin")
    public String [][] getData() throws IOException{
        String path = "testData/admincred.xlsx";
        utilities.ExcelUtility xlutil = new utilities.ExcelUtility(path);
        int tr = xlutil.getRowCount("Sheet1");
        int tc = xlutil.getCellCount("Sheet1",0);
        String [][] data = new String [tr][tc];
        for(int i=1;i<=tr;i++){
            for(int j=0;j<tc;j++){
                data[i-1][j] = xlutil.getCellData("Sheet1",i,j);
            }
        }
        return data;
    }
    @DataProvider(name="poc")
    public String [][] getPocData() throws IOException{
        String path = "testData/pocdet.xlsx";
        utilities.ExcelUtility xlutil = new utilities.ExcelUtility(path);
        int tr = xlutil.getRowCount("Sheet1");
        int tc = xlutil.getCellCount("Sheet1",0);
        String [][] data = new String [tr][tc];
        for(int i=1;i<=tr;i++){
            for(int j=0;j<tc;j++){
                data[i-1][j] = xlutil.getCellData("Sheet1",i,j);
            }
        }
        return data;
    }
}
