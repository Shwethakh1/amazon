package generic;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public abstract class Utility {

	public static String getProperty(String path,String key)
	{
		String value="";
		try 
		{
			Properties p=new Properties();
			p.load(new FileInputStream(path));
			value = p.getProperty(key);
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return value;
	}
	
	public static boolean compareText(String actual,String expected)
	{
		System.out.println("Actual Text:"+actual);
		System.out.println("Expected Text:"+expected);
		return actual.contains(expected);
	}
	
	public static boolean comparePrice(double actualPrice,double expectedPrice)
	{
	
		System.out.println("Actual Price:"+actualPrice);
		
		System.out.println("Expected Price:"+expectedPrice);
		 
		if(actualPrice==expectedPrice)
			return true;
		else
			return false;
	}
	
	public static double formatPrice(String price)
	{
		
	        // Remove non-numeric characters
	        String numericStr = price.substring(1).replaceAll(",","");
	        
	        // Convert to integer
	        double number = Double.parseDouble(numericStr);
	        
	        // Print the result
	        return number;
	}
	
	public static int convertStringToNumber(String value)
	{
		return Integer.parseInt(value);
	}
}
