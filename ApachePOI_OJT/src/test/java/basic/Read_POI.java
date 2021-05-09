package basic;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class Read_POI 
{
	@Test
	public void ReadExcel () throws Exception 
	{
		String data = null ;
		
		FileInputStream fis = new FileInputStream("Data.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("Sheet1");
		
		int row= sh.getPhysicalNumberOfRows();
		
		for(int i=0 ; i<row ; i++) 
		{
			int col= sh.getRow(i).getLastCellNum();
			
			for(int j=0;j<col;j++) 
			{
				XSSFCell cell = sh.getRow(i).getCell(j);
				
				if(cell.getCellTypeEnum() == CellType.STRING)
					data = cell.getStringCellValue();
				
				else if(cell.getCellTypeEnum() == CellType.NUMERIC)
					data= String.valueOf((long)cell.getNumericCellValue());
				
				System.out.print(data+"  ");
			}
			System.out.println();
		}
		wb.close();	
	}
}
