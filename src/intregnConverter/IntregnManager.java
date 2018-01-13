package intregnConverter;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.apache.log4j.Logger;
import org.ipas.commons.CActionTypeId;
import org.ipas.commons.CCriteriaFileId;
import org.ipas.commons.CCriteriaMark;
import org.ipas.commons.CCriteriaProcessByAction;
import org.ipas.commons.CCriteriaStatus;
import org.ipas.commons.CFileSummary;
import org.ipas.commons.CFileSummaryList;
import org.ipas.commons.CMark;
import org.ipas.commons.CommonsProxyFactory;
import org.ipas.commons.IMark;
import org.ipas.proxy.IpasException;
import org.ipas.proxy.IpasInteger;


public class IntregnManager {

	
	 HashSet<String> intregnSet	= null;
	
	 	UtilityConfiguration configuration = new UtilityConfiguration();
		static	IMark iMark	=	null;
		DBFetchMarkDetails  fetchMarkDetails =new DBFetchMarkDetails();
		static String intregnUtilityFolder	=	null;
		static String fileType;
		static ArrayList <HashMap<String,String>> list_of_mark_detail_map=	new ArrayList<HashMap<String,String>>();
		public static String log4jConfigFile	= null;
		Connection conn	=null;
	public static CommonsProxyFactory commonsProxyFactory;
	
	Logger logger	= Logger.getLogger(IntregnManager.class);
		
	public static void main(String[] args) {
		
		IntregnManager im	=	 new IntregnManager();
		
		im.init();
		
		im.fetchMarkDetailWithNullIntregn();
		
		
		Iterator<HashMap<String, String>> itr	=list_of_mark_detail_map.iterator();
		CMark	mark	= new CMark();
		while(itr.hasNext()){
			
			
			mark	=	getCurrentMark((HashMap<String, String>)itr.next());
			
			updateMarkIntregn(mark);
			
		}
		
		
	
	
	}

	public void fetchMarkDetailWithNullIntregn() {
		
		
			
		 conn	=	fetchMarkDetails.getConnection(configuration.databaseHost,configuration.databasePort,
					configuration.databaseTool,configuration.databaseName,configuration.userName,configuration.password);

		 list_of_mark_detail_map	=	fetchMarkDetails.readMarksFromDB(conn,fileType);
			
		
	}
	

	
	
	// get CMark instance on the basis of criteria set

public static CMark getCurrentMark(HashMap<String, String> markDetailsMap){
		
	 CCriteriaMark cCriteriaMark =	null;
	 CCriteriaFileId fileId =	null;
	 CCriteriaStatus cCriteriaStatus	=	null;
	 CCriteriaProcessByAction criteriaProcessByAction = null;
	 CMark mark = null; 
	 iMark = commonsProxyFactory.getIMark();
	 cCriteriaMark = new CCriteriaMark();
		fileId = new CCriteriaFileId();
		fileId.setFileSeq(markDetailsMap.get("FILE_SEQ"));
		fileId.setFileType(markDetailsMap.get("FILE_TYP"));
		fileId.setFileSeries(new IpasInteger(markDetailsMap.get("FILE_SER")));
		fileId.setFileNbrFrom(new IpasInteger(markDetailsMap.get("FILE_NBR")));
		fileId.setFileNbrTo(new IpasInteger(markDetailsMap.get("FILE_NBR")));
	
		cCriteriaMark.setCriteriaFileId(fileId);
	
		// Setting status criteria
				 cCriteriaStatus = new CCriteriaStatus();
		//	cCriteriaStatus.setStatusCode("ackn");
				 cCriteriaMark.setCriteriaStatus(cCriteriaStatus);
		//Setting action criteria
				 criteriaProcessByAction = new CCriteriaProcessByAction();
				 criteriaProcessByAction.setActionTypeId(new CActionTypeId("ack_app"));
				
				try {
					CFileSummaryList fileSummaryList = iMark.mGetList(cCriteriaMark);

					//System.out.println("Total mark Count:"+fileSummaryList.size());
					if(fileSummaryList != null && !fileSummaryList.isEmpty()){
						for (CFileSummary fileSummary : fileSummaryList) {
							
							mark	=	iMark.mRead(fileSummary.getFileId(), true, false);
							
						}
					}
											
				} catch (IpasException e) {
					e.printStackTrace();
					System.out.println("iMark.mRead() error:" + e.getMessage()+"\r\n");
				}
	return mark;
		
}


	public static void updateMarkIntregn(CMark mark){
		
		
		mark.getMadridApplicationData().setInternationalFileNumber(String.valueOf(mark.getFile().getFileId().getFileNbr()));
				
				try {
					iMark.mUpdate(mark, null);
				} catch (IpasException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Update Completed");
	
}
	
	private  void init() {
		logger.info("Initializing Utility....");		
		
		configuration.loadConfiguration();
		
		commonsProxyFactory = new CommonsProxyFactory(configuration.iiopHost +":"+configuration.iiopPort);
		
		log4jConfigFile	=	configuration.intregnUtilityFolder;
		
		
		logger.info("Initializing Completed....!!!");
	}
	
	// read property from config.properties
	
	

	
	
	
}
