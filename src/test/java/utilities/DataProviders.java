package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//DataProvider 1
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException{
		String path="./testData/Opencart_LoginData.xlsx";
		ExcelUtility xlUtil=new ExcelUtility(path);
		int totalrows=xlUtil.getRowCount("sheet1");
		int totalcols=xlUtil.getCellCount("sheet1",1);
		//creating two dimension array
		String logindata[][]=new String[totalrows][totalcols];
		
		for(int i=1;i<=totalrows;i++) {
			for(int j=0;j<totalcols;j++) {
				logindata[i-1][j]=xlUtil.getCellData("sheet1",i,j);
			}
		}
		return logindata;
	}


	//DataProvider 2
	
	//DataProvider 3
	
}

