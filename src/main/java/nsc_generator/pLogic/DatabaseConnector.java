/**	NSC_Generator v0.0		Dh	19.04.2021
 * 	
 * 	pLogic
 * 	  DatabaseController
 * 
 * Exceptions:
 * 	  01 Wrong length
 * 	  02 Wrong Value
 * 	  03 Calculation Error
 * 	  04 Nullpointer Error
 * 	  05 Empty List Error
 * 	  06 Wrong Type Error
 * 	  07 Index Error
 * 	  08 Equal Object Error
 * 
 * 	  20 Wrong OS
 * 	  21 File does not exist
 */

package nsc_generator.pLogic;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import pDataStructures.List;
import nsc_generator.pLogic.pPack.Pack;
import nsc_generator.pLogic.pPack.Subculture;
import nsc_generator.pLogic.pPack.Subrace;

public abstract class DatabaseConnector {
	private static String gamePath = "/NSC_Generator";
	
	private static String packPath = "/packs/";
	private static String sessionPath = "/sessions/";
	
	private static File systemFile;
	
	private static List packList, sessionList;
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	10.03.2021
	 * 
	 * @throws Exception
	 */
	public static void initConnector() throws Exception{
		try { 
			systemFile = getFileSystem();
			
			loadAllPacks();
			loadAllSessions();
		}catch(Exception ex) { throw ex;}
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	07.03.2021
	 * 
	 * @return
	 * @throws Exception
	 */
	private static File getFileSystem() throws Exception {
		String vOS, vArch, vHome;
		File vHomeFile;
		
		vOS = System.getProperty("os.name");
		vArch = System.getProperty("os.arch");
		vHome = System.getProperty("user.home");
		
		if (vOS.contains("Windows")) vHome = vHome+ "/AppData/Roaming"+gamePath;
		else if (vOS.contains("Linux")) {
			if (!vArch.contains("aarch64")) vHome = vHome+"/.local"+gamePath;
			
		} else throw new Exception("20; gFS,DaCon: " + vOS + "; "+vHome);
		
		vHomeFile = new File(vHome);
		if (!vHomeFile.exists()) {
			try{ makeFileSystem(vOS, vHomeFile); }
			catch(Exception ex) {throw ex;}
		}
		
		return vHomeFile;
	}
	/**	Dh	10.03.2021
	 * 
	 * @param vOS
	 * @param vHomeFile
	 * @throws Exception
	 */
	private static void makeFileSystem(String vOS, File vHomeFile) throws Exception {
		if (vOS.contains("Windows") || vOS.contains("Linux")) {
			vHomeFile.mkdir();
			new File(vHomeFile.getAbsoluteFile()+packPath).mkdir();
			new File(vHomeFile.getAbsoluteFile()+sessionPath).mkdir();
		}
		else throw new Exception("20; mFS,DaCon");
	}

//--------------------------------------------------------------------------------------------------------

	/**	Dh	09.03.2021
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public static Pack getPack(int pID) throws Exception{
		Pack vRet = null;
		
		if (pID >= 0) {
			packList.toFirst();
			while(!packList.isEnd() && (vRet == null)) {
				vRet = (Pack) packList.getCurrent();
				
				if (vRet.getId() != pID) vRet = null;
				
				packList.next();
			}
			
			if (vRet == null) throw new Exception("02a; gPa,DaCon");
		} else throw new Exception("02b; gPa,DaCon");
		
		return vRet;
	}
	/**	Dh	10.03.2021
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public static Session getSession(int pID) throws Exception{
		Session vRet = null;
		
		if (pID >= 0) {
			sessionList.toFirst();
			while(!sessionList.isEnd() && (vRet == null)) {
				vRet = (Session) sessionList.getCurrent();
				
				if (vRet.getId() != pID) vRet = null;
				
				sessionList.next();
			}
			
			if (vRet == null) throw new Exception("02a; gSe,DaCon");
		} else throw new Exception("02b; gSe,DaCon");
		
		return vRet;
	}
	
	/**	Dh	09.03.2021
	 * 
	 * @return
	 */
	public static List getPackList() {
		return packList;
	}
	/**	Dh	10.03.2021
	 * 
	 * @return
	 */
	public static List getSessionList() {
		return sessionList;
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	09.03.2021
	 * 
	 * @return
	 * @throws Exception
	 */
 	public static Pack newPack() throws Exception{
		Pack vRet = new Pack(genNewIDFromIDElementList(packList), "");

		addPack(vRet);
		
		return vRet;
	}
 	/**	Dh	11.03.2021
 	 * 
 	 * @param pName
 	 * @param pPackID
 	 * @return
 	 * @throws Exception
 	 */
 	public static Session newSession(String pName, int pPackID) throws Exception{
		Session vRet;
		
		if (doesPackExist(pPackID)) {
			vRet = new Session(genNewIDFromIDElementList(sessionList), pName, getPack(pPackID));
			addSession(vRet);
		} else throw new Exception("02; nSe,DaCon");
		
		return vRet;
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	19.04.2021
	 * 
	 * @param pPack
	 * @throws Exception
	 */
	public static void addPack(Pack pPack) throws Exception{
		if (pPack != null) {
			if (!checkIfIDIsInList(pPack.getId(), packList)) {
				packList.append(pPack);
				
				sortListByID(packList);
			} else throw new Exception("02; aPa,DaCon:"+pPack.getId());
		} else throw new Exception("04; aPa,DaCon");
	}
	/**	Dh	19.04.2021
	 * 
	 * @param pSession
	 * @throws Exception
	 */
	public static void addSession(Session pSession) throws Exception{
		if (pSession != null) {
			if (!checkIfIDIsInList(pSession.getId(), sessionList)) {
				sessionList.append(pSession);
				
				sortListByID(sessionList);
			}
			else throw new Exception("02; aSe,DaCon");
		} else throw new Exception("04; aSe,DaCon");
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	09.03.2021
	 * 
	 * @param pID
	 * @throws Exception
	 */
	public static void removePack(int pID) throws Exception{
		Pack vCur;
		
		if (pID >= 0) {
			packList.toFirst();
			while(!packList.isEnd()) {
				vCur = (Pack)packList.getCurrent();
				
				if (vCur.getId() == pID) {
					packList.remove();
					deletePack(pID);
				}
				
				packList.next();
			}
		}else throw new Exception("02; rPa,DaCon");
	}
	/**	Dh	10.03.2021
	 * 
	 * @param pID
	 * @throws Exception
	 */
	public static void removeSession(int pID) throws Exception{
		Session vCur;
		
		if (pID >= 0) {
			sessionList.toFirst();
			while(!sessionList.isEnd()) {
				vCur = (Session)sessionList.getCurrent();
				
				if (vCur.getId() == pID) {
					sessionList.remove();
					deleteSession(pID);
				}
				
				sessionList.next();
			}
		}else throw new Exception("02; rSe,DaCon");
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	09.03.2021
	 * 
	 * @param pID
	 * @throws Exception
	 */
	public static void resetPack(int pID) throws Exception{
		Pack vCur, vOldPack;
		
		if (pID >= 0) {
			packList.toFirst();
			while(!packList.isEnd()) {
				vCur = (Pack)packList.getCurrent();
				
				if (vCur.getId() == pID) {
					vOldPack = loadPack(pID);
					
					copyPack(vCur, vOldPack);
					
					packList.toLast();
				}
				
				packList.next();
			}
		}else throw new Exception("02; rsPa,DaCon");
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	09.03.2021
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public static boolean doesPackExist(int pID) throws Exception{
		boolean vRet = false;
		Pack vCur;
		
		if (pID >= 0) {
			packList.toFirst();
			while(!packList.isEnd() && (vRet == false)) {
				vCur = (Pack) packList.getCurrent();
				
				if (vCur.getId() == pID) vRet = true;
				
				packList.next();
			}
			
		} else throw new Exception("02; dPaE,DaCon");
		
		return vRet;
	}
	/**	Dh	10.03.2021
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public static boolean doesSessionExist(int pID) throws Exception{
		boolean vRet = false;
		Session vCur;
		
		if (pID >= 0) {
			sessionList.toFirst();
			while(!sessionList.isEnd() && (vRet == false)) {
				vCur = (Session) sessionList.getCurrent();
				
				if (vCur.getId() == pID) vRet = true;
				
				sessionList.next();
			}
			
		} else throw new Exception("02; dSeE,DaCon");
		
		return vRet;
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	10.03.2021
	 * 
	 * @param pPack
	 * @throws Exception
	 */
	public static void savePack(Pack pPack) throws Exception {
		File vFile;
		JAXBContext jc;
		Marshaller marschaller;
		
		if (pPack != null) {
			if (!pPack.getName().equals("")) {
				deletePack(pPack.getId());
				
				vFile = new File(systemFile+packPath+pPack.getName()+"_"+pPack.getId()+".pac");
				jc = JAXBContext.newInstance(Pack.class);
				marschaller = jc.createMarshaller();
				
				marschaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				
				if (!vFile.exists()) {
					try{ vFile.createNewFile();}
					catch(Exception ex) {throw ex;}
				}
				
			    marschaller.marshal(pPack, vFile);
			} else throw new Exception("02; sPa,DaCon");
		} else throw new Exception("04; sPa,DaCon");
	}
	/**	Dh	11.03.2021
	 * 
	 * @param pSession
	 * @throws Exception
	 */
	public static void saveSession(Session pSession) throws Exception {
		File vFile;
		JAXBContext jc;
		Marshaller marschaller;
		
		if (pSession != null) {
			if (!pSession.getName().equals("")) {
				deleteSession(pSession.getId());
				
				vFile = new File(systemFile+sessionPath+pSession.getName()+"_"+pSession.getId()+".ses");
				jc = JAXBContext.newInstance(Session.class);
				marschaller = jc.createMarshaller();
				
				marschaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				
				if (!vFile.exists()) {
					try{ vFile.createNewFile();}
					catch(Exception ex) {throw ex;}
				}
				
			    marschaller.marshal(pSession, vFile);
			} else throw new Exception("02; sSe,DaCon");
		} else throw new Exception("04; sSe,DaCon");
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	09.03.2021
	 * 
	 * @param pName
	 * @return
	 * @throws Exception
	 */
	public static Pack loadPack(int pID) throws Exception{
		Pack vRet;
		File vFile;
		
		vFile = new File(systemFile+packPath+getPack(pID).getName()+"_"+pID+".pac");
		
		if (vFile.exists()) vRet = JAXB.unmarshal(vFile, Pack.class);
		else throw new Exception("21; lPa,DaCon");
		
		return vRet;
	}
	/**	Dh	10.03.2021
	 * 
	 * @param pName
	 * @return
	 * @throws Exception
	 */
	public static Session loadSession(int pID) throws Exception{
		Session vRet;
		File vFile;
		
		vFile = new File(systemFile+sessionPath+getSession(pID)+"_"+pID+".ses");
		
		if (vFile.exists()) vRet = JAXB.unmarshal(vFile, Session.class);
		else throw new Exception("21; lSe,DaCon");
		
		return vRet;
	}
	
	/**	Dh	11.03.2021
	 * 
	 * @throws Exception
	 */
	public static void loadAllPacks() throws Exception{
		Pack vPack;
		File vDirectory, vCurFile;
		Object[] vPackFiles;
		
		packList = new List();
		
		vDirectory = new File(systemFile+packPath);
		vPackFiles = Files.list(vDirectory.toPath()).toArray();
		
		if (vPackFiles != null) {
			for (int i=0; i<vPackFiles.length; i++) {
				vCurFile = ((Path)vPackFiles[i]).toFile();
				
				vPack = JAXB.unmarshal(vCurFile, Pack.class);
				packList.append(vPack);
			}
			
			sortListByID(packList);
			setParentsForSubElementsInPackList(packList);
		}
	}
	/**	Dh	10.03.2021
	 * 
	 * @throws Exception
	 */
	public static void loadAllSessions() throws Exception{
		Session vSession;
		File vDirectory, vCurFile;
		Object[] vSessionFiles;
		
		sessionList = new List();
		
		vDirectory = new File(systemFile+sessionPath);
		vSessionFiles = Files.list(vDirectory.toPath()).toArray();
		
		if (vSessionFiles != null) {
			for (int i=0; i<vSessionFiles.length; i++) {
				vCurFile = ((Path)vSessionFiles[i]).toFile();
				
				vSession = JAXB.unmarshal(vCurFile, Session.class);
				sessionList.append(vSession);
			}
			
			sortListByID(sessionList);
		}
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	10.03.2021
	 * 
	 * @param pID
	 * @throws Exception
	 */
	public static void deletePack(int pID) throws Exception {
		File vDirectory;
		Path vCurPath;
		Object[] vPackFiles;
		
		vDirectory = new File(systemFile+packPath);
		vPackFiles = Files.list(vDirectory.toPath()).toArray();
		
		if (vPackFiles != null) {
			for (int i=0; i<vPackFiles.length; i++) {
				vCurPath = (Path)vPackFiles[i];
				
				if (vCurPath.toString().endsWith(("_"+pID+".xml"))) {
					((Path)vPackFiles[i]).toFile().delete();
				}
			}
		}
	}
	/**	Dh	10.03.2021
	 * 
	 * @param pID
	 * @throws Exception
	 */
	public static void deleteSession(int pID) throws Exception {
		File vDirectory;
		Path vCurPath;
		Object[] vSessionFiles;
		
		vDirectory = new File(systemFile+sessionPath);
		vSessionFiles = Files.list(vDirectory.toPath()).toArray();
		
		if (vSessionFiles != null) {
			for (int i=0; i<vSessionFiles.length; i++) {
				vCurPath = (Path)vSessionFiles[i];
				
				if (vCurPath.toString().endsWith(("_"+pID+".xml"))) {
					((Path)vSessionFiles[i]).toFile().delete();
				}
			}
		}
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	19.04.2021
	 * 
	 * @param pStage
	 * @return
	 * @throws Exception
	 */
	public static Pack importPack(File pFile) throws Exception{
		Pack vRet;
		
		if (pFile.exists()) {
			vRet = JAXB.unmarshal(pFile, Pack.class);
			
			vRet.setId(genNewIDFromIDElementList(packList));
			addPack(vRet);
		}
		else throw new Exception("21; iPa,DaCon");
		
		return vRet;
	}
	/**	Dh	19.04.2021
	 * 
	 * @param pStage
	 * @return
	 * @throws Exception
	 */
	public static Session importSession(File pFile) throws Exception{
		Session vRet;
		
		if (pFile.exists()) {
			vRet = JAXB.unmarshal(pFile, Session.class);
			
			vRet.setId(genNewIDFromIDElementList(sessionList));
			addSession(vRet);
		}
		else throw new Exception("21; iSe,DaCon");
		
		return vRet;
	}
	/**	Dh	19.04.2021
	 * 
	 * @param pFile
	 * @return
	 * @throws Exception
	 */
	public static NPC importNPC(File pFile) throws Exception{
		NPC vRet;
		
		if (pFile.exists()) vRet = JAXB.unmarshal(pFile, NPC.class);
		else throw new Exception("21; iNPC,DaCon");
		
		return vRet;
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	19.04.2021
	 * 
	 * @param pPackID
	 * @param pSaveFile
	 * @throws Exception
	 */
	public static void exportPack(int pPackID, File pSaveFile) throws Exception {
		JAXBContext jc;
		Marshaller marschaller;
		
		Pack vPack = getPack(pPackID);
		
		if ((vPack != null) && (pSaveFile != null)) {
			if (!vPack.getName().equals("")) {	
				jc = JAXBContext.newInstance(Pack.class);
				marschaller = jc.createMarshaller();
				
				marschaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				
				if (!pSaveFile.exists()) {
					try{ pSaveFile.createNewFile();}
					catch(Exception ex) {throw ex;}
				}
				
			    marschaller.marshal(vPack, pSaveFile);
			} else throw new Exception("02; ePa,DaCon");
		} else throw new Exception("04; ePa,DaCon");
	}
	/**	Dh	19.04.2021
	 * 
	 * @param pPackID
	 * @param pSaveFile
	 * @throws Exception
	 */
	public static void exportSession(int pSessionID, File pSaveFile) throws Exception {
		int vCurPackID;
		JAXBContext jc;
		Marshaller marschaller;
		
		Session vSession = getSession(pSessionID);
		
		if ((vSession != null) && (pSaveFile != null)) {
			if (!vSession.getName().equals("")) {	
				jc = JAXBContext.newInstance(Session.class);
				marschaller = jc.createMarshaller();
				
				marschaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				
				if (!pSaveFile.exists()) {
					try{ pSaveFile.createNewFile();}
					catch(Exception ex) {throw ex;}
				}
				
				vCurPackID = vSession.getPackID();
				vSession.setPackID(-1);
			    marschaller.marshal(vSession, pSaveFile);
			    vSession.setPackID(vCurPackID);
			} else throw new Exception("02; eSe,DaCon");
		} else throw new Exception("04; eSe,DaCon");
	}
	/**	Dh	19.04.2021
	 * 
	 * @param pNPC
	 * @param pSaveFile
	 * @throws Exception
	 */
	public static void exportNPC(NPC pNPC, File pSaveFile) throws Exception {
		JAXBContext jc;
		Marshaller marschaller;
		
		if ((pNPC != null) && (pSaveFile != null)) {
			if (!pNPC.getName().equals("")) {	
				jc = JAXBContext.newInstance(NPC.class);
				marschaller = jc.createMarshaller();
				
				marschaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				
				if (!pSaveFile.exists()) {
					try{ pSaveFile.createNewFile();}
					catch(Exception ex) {throw ex;}
				}
				
				marschaller.marshal(pNPC, pSaveFile);
			} else throw new Exception("02; eNPC,DaCon");
		} else throw new Exception("04; eNPC,DaCon");
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	04.03.2021
	 * 
	 * @param pIDElementList
	 * @return
	 * @throws Exception
	 */
	public static int genNewIDFromIDElementList(List pIDElementList) throws Exception {
		int vRet = 0;
		IDElement vCur;
		
		if (pIDElementList != null) {
			pIDElementList.toFirst();
			while(!pIDElementList.isEnd()) {
				vCur = (IDElement) pIDElementList.getCurrent();
				
				if (vRet == vCur.getId()) vRet ++;
				else pIDElementList.toLast();
				
				pIDElementList.next();
			}
		}
		
		return vRet;
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	09.03.2021
	 * 
	 * @param pID
	 * @param pIDElementList
	 * @return
	 */
	private static boolean checkIfIDIsInList(int pID, List pIDElementList) {
		boolean vRet = false;
		IDElement vCur;
		
		pIDElementList.toFirst();
		while(!pIDElementList.isEnd() && (vRet == false)) {
			vCur = (IDElement)pIDElementList.getCurrent();
			
			if (vCur.getId() == pID) vRet = true;
			
			pIDElementList.next();
		}
		
		return vRet;
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	11.03.2021
	 * 
	 * @param pPack
	 * @throws Exception
	 */
	private static void setParentsForSubElementsInList(List pElementList, Pack pPack) throws Exception {
		Object vCur;
		
		pElementList.toFirst();
		while(!pElementList.isEnd()) {
			vCur = pElementList.getCurrent();
			
			if (vCur instanceof Subrace) ((Subrace) vCur).setParentRace(pPack.getRace(((Subrace) vCur).getParentID()));
			else if (vCur instanceof Subculture) ((Subculture) vCur).setParentCulture(pPack.getCulture(((Subculture) vCur).getParentID()));
			
			pElementList.next();
		}
	}
	
	/**	Dh	11.03.2021
	 * 
	 * @param pPackList
	 * @throws Exception
	 */
	private static void setParentsForSubElementsInPackList(List pPackList) throws Exception {
		Pack vCur;
		
		pPackList.toFirst();
		while(!pPackList.isEnd()) {
			vCur = (Pack) pPackList.getCurrent();
			
			setParentsForSubElementsInList(vCur.getCultureList().copyList(), vCur);
			setParentsForSubElementsInList(vCur.getRaceList().copyList(), vCur);
			
			pPackList.next();
		}
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	09.03.2021
	 * 
	 * @param pCopyPack
	 * @param pOriginPack
	 * @throws Exception
	 */
	private static void copyPack(Pack pCopyPack, Pack pOriginPack) throws Exception {
		pCopyPack.setName(pOriginPack.getName());
		
		pCopyPack.setRaceList(pOriginPack.getRaceList());
		pCopyPack.setCultureList(pOriginPack.getCultureList());
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	11.03.2021
	 * 
	 * @param pIDElementList
	 * @param pCur
	 * @throws Exception
	 */
	private static void sortRecusivDown(List pIDElementList, IDElement pCur) throws Exception {
		int vComparID = ((IDElement)pIDElementList.getCurrent()).getId();
		
		if ( vComparID > pCur.getId() ) {
			if (!pIDElementList.isFirst()) {
				pIDElementList.prior();
				sortRecusivDown(pIDElementList, pCur);
				pIDElementList.next();
			} else pIDElementList.insert(pCur);
		} else if (vComparID < pCur.getId()) {
			pIDElementList.next();
			pIDElementList.insert(pCur);
			pIDElementList.prior();
		} else throw new Exception("02; sRD,DaCon");
	}
	
	/**	Dh	11.03.2021
	 * 
	 * @param pIDElementList
	 * @throws Exception
	 */
	private static void sortListByID(List pIDElementList) throws Exception {
		IDElement vCur;
		int vOldID;
		
		if (pIDElementList != null) {
			
			vOldID = -1;
			pIDElementList.toFirst();
			while(!pIDElementList.isEnd()) {
				vCur = (IDElement) pIDElementList.getCurrent();
				
				if (vOldID > vCur.getId()) {
					if (pIDElementList.isLast()) {
						pIDElementList.remove();
						sortRecusivDown(pIDElementList, vCur);
					}else {
						pIDElementList.remove();
						pIDElementList.prior();
						sortRecusivDown(pIDElementList, vCur);
					}
					
				}else if (vOldID < vCur.getId()) vOldID = vCur.getId();
				else throw new Exception("02; sLBID,DaCon");
				
				pIDElementList.next();
			}
		}
	}
	
}
