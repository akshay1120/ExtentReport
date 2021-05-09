package login;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

public class Login_3 
{
	@Test
	public void getData() throws Exception 
	{
		FileInputStream fis = new FileInputStream("Data.xls");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Sheet1");
		
		int row = sh.getPhysicalNumberOfRows();
		
		for(int i=0 ; i<row ; i++) 
		{
			int col =sh.getRow(i).getLastCellNum();
			
			for(int j=0;j<col;j++) 
			{
				Cell cell= sh.getRow(i).getCell(j);	
				
				if(cell.toString().contains(".0")) 
				{
					String val= cell.toString().substring(0,cell.toString().indexOf("."));
					System.out.print(val+"  ");
				}
				
				else
				{
					System.out.print(cell.toString()+"  ");	
				}
			}
			System.out.println();
		}
	}
}
