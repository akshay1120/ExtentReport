package basic;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

public class A 
{
	@Test
	public void getData() throws Exception 
	{
		String data = null ;
		FileInputStream fis = new FileInputStream("dashboard.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Sheet1");
		for(int i=1;i<sh.getPhysicalNumberOfRows();i++)
		{
			int col =sh.getRow(i).getLastCellNum();
			for(int j=0;j<col-2;j++) 
			{
				Cell cell= sh.getRow(i).getCell(j);	
				
				if(cell.getCellTypeEnum()==CellType.STRING) 
				{
					data = cell.getStringCellValue();
				}
				
				else if (cell.getCellTypeEnum()==CellType.NUMERIC)
				{
					data = String.valueOf(cell.getNumericCellValue());
				}
				System.out.print(data + "           ");	
			}
			System.out.println();
		}
	}
}
