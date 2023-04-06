package Common_Method;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Get_Data {
	//public static String TestDataPath;
	public static ArrayList<String> Get_Data_Excel(String testSheetName, String testCaseName) throws IOException{
		
		ArrayList<String> arrayData = new ArrayList<String>(); 
		//TestDataPath = System.getProperty("User.dir");
		//System.out.println(TestDataPath);
		//Step1: Locate the file,by creating the object of file input stream
		FileInputStream fis = new FileInputStream("E:\\Prutha\\API\\Test_Data.xlsx");
		//FileInputStream fis = new FileInputStream(TestDataPath"/Test_Data.xlsx");
		
		//Step2: Create object of XSSFWorkbook to open the excel file
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		//Step3: Access the desired sheet  
		//Step3.1: fetch the count of sheets available in the excel file
		int countofsheet=workbook.getNumberOfSheets();
		//Step3.2 fetch the name of sheet and compare against desired sheet name
		for(int i=0;i<countofsheet;i++)
		{
			String sheetname = workbook.getSheetName(i);
			//System.out.println(sheetname);
			if (sheetname.equalsIgnoreCase(testSheetName))
			{
				//System.out.println(testSheetName);
				//Step4: Access the sheet and iterate through row to fetch the column in which test case name column is found
				XSSFSheet sheet = workbook.getSheetAt(i);
				//Step4.1 Create iterator for rows
				Iterator<Row> Rows = sheet.iterator();
				Row FirstRow = Rows.next();
				//Step4.2 create Iterator for cells
				Iterator<Cell> Cells =FirstRow.cellIterator();
				int j=0;
				int tc_column=0;
				//Step4.3 Read the cell values of row no1 to compare against the test case name
				while(Cells.hasNext())
				{
					Cell cellvalue = Cells.next();
					//System.out.println(cellvalue);
					if (cellvalue.getStringCellValue().equalsIgnoreCase("tc_name"))
					{
						tc_column= j;
						//System.out.println(tc_column);
					}
					j++;
				}
		
				//fetch the data for designed test case
				while (Rows.hasNext())
				{
					Row DataRow = Rows.next();
					if (DataRow.getCell(tc_column).getStringCellValue().equalsIgnoreCase(testCaseName))
					{
						Iterator<Cell> DataCellValue =DataRow.cellIterator();
						while(DataCellValue.hasNext())
						{
							Cell DataofCell = DataCellValue.next();
							
							try {
								String TestData =DataofCell.getStringCellValue();
								System.out.println(TestData);
								arrayData.add(TestData);
							}
							catch(Exception e)
							{
								int IntestData = (int) DataofCell.getNumericCellValue();
								String StringTestData=Integer.toString(IntestData);
								System.out.println(StringTestData);
								arrayData.add(StringTestData);
								
							}
//							CellType DataType = DataofCell.getCellType();
//							//System.out.println(DataType);
//							
//							if(DataType.toString()== "NUMERIC")
//							{
//								int IntestData = (int) DataofCell.getNumericCellValue();
//								System.out.println(IntestData);
//							}
//							else if(DataType.toString()== "STRING")
//							{
//								String TestData = DataofCell.getStringCellValue();
//								System.out.println(TestData);
//							}
//							//method 3 -- extract the data by converting it into String 
//		        		    String TestData = DataofCell.toString().replaceAll("\\.\\d+$", "");
//		        		    arrayData.add(TestData);
//		        		    System.out.println(TestData);
//		        		    
//		        		    //method 4 -- Extract the data by using Dataformatter
//		        		    DataFormatter format = new DataFormatter();
//		        		    String testData = format.formatCellValue(DataofCell);
//		        		    arrayData.add(testData);
//		        		    System.out.println(TestData);
    					}
					}
						
				}
				
			}
		}
		return arrayData;
	}
}
