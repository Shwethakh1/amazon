package generic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class Excel {
	public static Workbook wb;

	public static String getData(String path,String sheet,int r,int c)
	{
		String v="";
		try {
			Workbook wb=WorkbookFactory.create(new FileInputStream(path));
			v=wb.getSheet(sheet).getRow(r).getCell(c).toString();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return v;
	}

	public static void setData(String path, String sheet, int r, int c, String value) throws IOException {
		//Setting the result as data 
		try {
			wb.getSheet(sheet).getRow(r).getCell(c).setCellValue(value);
			wb.write(new FileOutputStream(path));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		wb.close();
		
	}

	
}
