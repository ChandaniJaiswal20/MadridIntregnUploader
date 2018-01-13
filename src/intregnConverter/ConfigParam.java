package intregnConverter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigParam {
	
	
	String fileName	=	null;
	Properties prop	= null;
	
	ConfigParam(String filename){
		
		
		fileName	=	filename;
		
		
		
	}
	
	public void initialise(){
	try {
		FileReader fr = new FileReader("fileName");
		prop	=	new Properties();
		prop.load(fr);
		
		
		
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	
	public String getProperty(String property){
		
		if(prop==null){
			
			initialise();
			
		}
		
		return prop.getProperty(property);
		
	}
	
	
	
	
	

}
