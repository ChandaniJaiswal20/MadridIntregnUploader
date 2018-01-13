package intregnConverter;

import org.apache.log4j.Logger;



public class UtilityConfiguration {

	ConfigParam	config	=	null;
	public String iiopPort;
	public String iiopHost;
	public String databaseHost;
	public String databasePort;
	public String databaseTool;
	public String databaseName;
	public String userName;
	public String password;
	public String fileType;
	public String intregnUtilityFolder;
	public static Logger logger	= Logger.getLogger(UtilityConfiguration.class);

	public UtilityConfiguration loadConfiguration() {
		logger.debug("loading configuration.....");
			config	= new ConfigParam("patentAnnuitityConfig.properties");
			intregnUtilityFolder	=	config.getProperty("intregnUtilityFolder");
			iiopPort	= config.getProperty("iiopPort");
			iiopHost	= config.getProperty("iiopHost");
			databaseHost	= config.getProperty("databaseHost");
			databasePort	= config.getProperty("databasePort");
			databaseTool	= config.getProperty("databaseTool");
			databaseName	=	config.getProperty("databaseName");
			userName		=	config.getProperty("userName");
			password		=	config.getProperty("password");
		
			return this;
				
	}
	
	
	public void checkValuesOfConfigFile(){
		if(intregnUtilityFolder==null){
			
			System.out.println("Please specify value of  intregnUtilityFolder in patentAnnuitityConfig.properties");
			throw new MissingConfigurationException("Please specify value of  intregnUtilityFolder in patentAnnuitityConfig.properties");
			
		}
		if(iiopPort==null||iiopPort.isEmpty()){
			
			System.out.println("Please specify value of  iiopPort in patentAnnuitityConfig.properties");
			throw new MissingConfigurationException("Please specify value of  iiopPort in patentAnnuitityConfig.properties");
		}
		
		if(iiopHost==null||iiopHost.isEmpty()){
			
			System.out.println("Please specify value of  iiopHost in patentAnnuitityConfig.properties");
			throw new MissingConfigurationException("Please specify value of  iiopHost in patentAnnuitityConfig.properties");
		}
		
		if(databaseHost==null||databaseHost.isEmpty()){
			
			
			System.out.println("Please specify value of  databaseHost in patentAnnuitityConfig.properties");
			throw new MissingConfigurationException("Please specify value of  databaseHost in patentAnnuitityConfig.properties");
		}
		
		if(databasePort==null||databasePort.isEmpty()){
			
			
			System.out.println("Please specify value of  databasePort in patentAnnuitityConfig.properties");
			throw new MissingConfigurationException("Please specify value of  databasePort in patentAnnuitityConfig.properties");
		}
		
		if(databaseTool==null||databaseTool.isEmpty()){
			
			System.out.println("Please specify value of  databaseTool in patentAnnuitityConfig.properties");
			throw new MissingConfigurationException("Please specify value of  databaseTool in patentAnnuitityConfig.properties");
			
		}
		else if(!(databaseTool.equalsIgnoreCase("sql")||databaseTool.equalsIgnoreCase("oracle"))){
			
			System.out.println("Please specify correct database tool in patentAnnuitityConfig.properties ie. for oracle database::<oracle> and for sql database::<sql>");
			throw new MissingConfigurationException("Please specify correct database tool in patentAnnuitityConfig.properties ie. for oracle database::<oracle> and for sql database::<sql>");
		}
		
		if(databaseName==null||databaseName.isEmpty()){
			
			
			System.out.println("Please specify value of  databaseName in patentAnnuitityConfig.properties");
			throw new MissingConfigurationException("Please specify value of  databaseName in patentAnnuitityConfig.properties");
		}
		
		if(userName==null||userName.isEmpty()){
			
			System.out.println("Please specify value of  userName in patentAnnuitityConfig.properties");
			throw new MissingConfigurationException("Please specify value of  userName in patentAnnuitityConfig.properties");
		}
		
		if(password==null||password.isEmpty()){
			
			
			System.out.println("Please specify value of  password in patentAnnuitityConfig.properties");
			throw new MissingConfigurationException("Please specify value of  password in patentAnnuitityConfig.properties");
		}
		
		if(fileType==null||fileType.isEmpty()){
			
			
			System.out.println("Please specify value of  fileType in patentAnnuitityConfig.properties");
			throw new MissingConfigurationException("Please specify value of  fileType in patentAnnuitityConfig.properties");
		}
		

		
	
		
	}
	
	
	
	public void checkDateFormat(){
		
		
		
	}
	
	public static void main(String[] args) {
		
		UtilityConfiguration uc = new UtilityConfiguration();
		
		uc.loadConfiguration();
		
	}
	
}
