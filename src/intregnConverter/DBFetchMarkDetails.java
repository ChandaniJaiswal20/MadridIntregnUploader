package intregnConverter;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;



/**
 * This class is having code for fetching Intregn having null values
 * @author Chandani Jaiswal
 *
 */

public class DBFetchMarkDetails {
	
	
	
	/**
	 * This is used to get the Connection
	 * 
	 * @return
	 * @throws IOException 
	 */
	 public  Connection 	getConnection(String databaseHost, String databasePort,
				String databaseTool, String databaseName,String userName,String password ) {
			// TODO Auto-generated method stub
			Connection conn = null;
			if( databaseTool.equalsIgnoreCase("oracle") ){
			//("jdbc:oracle:thin:@" + databaseHost + ":" + databasePort + ":"+databaseName,userName,password  );
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn	=	DriverManager.getConnection("jdbc:oracle:thin:@" + databaseHost + ":" + databasePort + ":"+databaseName,userName,password  );
				
				
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			else if(databaseTool.equalsIgnoreCase("sql")){
				
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					
					conn	=	DriverManager.getConnection( "jdbc:sqlserver://"+databaseHost+ ":" + databasePort +";" +
				    		  "databaseName="+databaseName+";"+"user="+userName+";"+"password="+password);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
			
			return conn;
			
			
		}
	//byte
	
	

	
	
	
	
	
	
	

	//load intregn from ip_mark


	
	public   ArrayList <HashMap<String,String>> readMarksFromDB(Connection connection,String fileType) {
		// TODO Auto-generated method stub
		
		HashMap	<String,String> mark_detail_map	=	new HashMap<String ,String>();
		ArrayList <HashMap<String,String>> list_of_mark_detail_map=	new ArrayList<HashMap<String,String>>();
		PreparedStatement preStmt	=	null;
		ResultSet rs				=	null;
		String query	=	"SELECT  FILE_NBR,FILE_SEQ,FILE_SER,FILE_TYP FROM IP_MARK WHERE FILE_NBR IS NOT NULL  and INTREGN is  null AND FILE_TYP = ?";
			
		try {
			 preStmt	=		connection.prepareStatement(query);
				
			preStmt.setString(1, fileType);
			
			rs	=	  preStmt.executeQuery();
			
			while(rs.next()){
					

				mark_detail_map.put("FILE_SEQ", rs.getString("FILE_SEQ"));
				mark_detail_map.put("FILE_TYP", rs.getString("FILE_TYP"));
				mark_detail_map.put("FILE_SER", rs.getString("FILE_SER"));
				mark_detail_map.put("FILE_NBR", rs.getString("FILE_NBR"));
					
				list_of_mark_detail_map.add(mark_detail_map);
				
			}
					
			
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return list_of_mark_detail_map;
	}
	
	
	
	
	/***
	 * Execute Program
	 * 
	 * @param args
	 * @throws SQLException
	 */

	
	
	
	

	
	





}