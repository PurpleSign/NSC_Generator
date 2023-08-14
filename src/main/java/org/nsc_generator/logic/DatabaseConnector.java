/**	NSC_Generator v0.2		Dh	14.08.2023
 * 	
 * 	logic
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

package org.nsc_generator.logic;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.nsc_generator.logic.pack.Pack;
import org.nsc_generator.logic.pack.Subculture;
import org.nsc_generator.logic.pack.Subrace;

public abstract class DatabaseConnector {
	private static String gamePath = "/NSC_Generator";
	
	private static String packPath = "/packs/";
	private static String sessionPath = "/sessions/";
	
	private static File systemFile;
	
	private static ArrayList<Pack> packs;
	private static ArrayList<Session> sessions;
	
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

	/**	Dh	14.08.2023
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public static Pack getPack(int pID) throws Exception{
		Pack vRet = null;
		
		if (pID >= 0) {
			for (int i=0; (i<packs.size()) && (vRet == null); i++) {
				if (packs.get(i).getId() == pID) vRet = packs.get(i);
			}
			
			if (vRet == null) throw new Exception("02a; gPa,DaCon");
		} else throw new Exception("02b; gPa,DaCon");
		
		return vRet;
	}
	/**	Dh	14.08.2023
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public static Session getSession(int pID) throws Exception{
		Session vRet = null;
		
		if (pID >= 0) {
			for (int i=0; (i<sessions.size()) && (vRet == null); i++) {
				if (sessions.get(i).getId() == pID) vRet = sessions.get(i);
			}
			
			if (vRet == null) throw new Exception("02a; gSe,DaCon");
		} else throw new Exception("02b; gSe,DaCon");
		
		return vRet;
	}
	
	/**	Dh	14.08.2023
	 * 
	 * @return
	 */
	public static ArrayList<Pack> getPacks() {
		return packs;
	}
	/**	Dh	14.08.2023
	 * 
	 * @return
	 */
	public static ArrayList<Session> getSessions() {
		return sessions;
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	14.08.2023
	 * 
	 * @return
	 * @throws Exception
	 */
 	public static Pack newPack() throws Exception{
		Pack vRet = new Pack(genNewIDFromIDElementList(packs), "");

		addPack(vRet);
		
		return vRet;
	}
 	/**	Dh	14.08.2023
 	 * 
 	 * @param pName
 	 * @param pPackID
 	 * @return
 	 * @throws Exception
 	 */
 	public static Session newSession(String pName, int pPackID) throws Exception{
		Session vRet;
		
		if (doesPackExist(pPackID)) {
			vRet = new Session(genNewIDFromIDElementList(sessions), pName, getPack(pPackID));
			addSession(vRet);
		} else throw new Exception("02; nSe,DaCon");
		
		return vRet;
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	14.08.2023
	 * 
	 * @param pPack
	 * @throws Exception
	 */
	public static void addPack(Pack pPack) throws Exception{
		if (pPack != null) {
			if (!checkIfIDIsInList(pPack.getId(), packs)) {
				packs.add(pPack);
				
				packs.sort((pPack1, pPack2) -> {
					return pPack1.getId() - pPack2.getId();
				});
			} else throw new Exception("02; aPa,DaCon:"+pPack.getId());
		} else throw new Exception("04; aPa,DaCon");
	}
	/**	Dh	14.08.2023
	 * 
	 * @param pSession
	 * @throws Exception
	 */
	public static void addSession(Session pSession) throws Exception{
		if (pSession != null) {
			if (!checkIfIDIsInList(pSession.getId(), sessions)) {
				sessions.add(pSession);
				
				sessions.sort((pSession1, pSession2) -> {
					return pSession1.getId() - pSession2.getId();
				});
			}
			else throw new Exception("02; aSe,DaCon");
		} else throw new Exception("04; aSe,DaCon");
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	14.08.2023
	 * 
	 * @param pID
	 * @throws Exception
	 */
	public static void removePack(int pID) throws Exception{
		if (pID >= 0) {
			
			for (int i=0; i<packs.size(); i++) {
				if (packs.get(i).getId() == pID) {
					packs.remove(i);
					deletePack(pID);
					
					i = packs.size();
				}
			}
		}else throw new Exception("02; rPa,DaCon");
	}
	/**	Dh	14.08.2023
	 * 
	 * @param pID
	 * @throws Exception
	 */
	public static void removeSession(int pID) throws Exception{
		if (pID >= 0) {
			for (int i=0; i<sessions.size(); i++) {
				if (sessions.get(i).getId() == pID) {
					sessions.remove(i);
					deleteSession(pID);
					
					i = sessions.size();
				}
			}
		}else throw new Exception("02; rSe,DaCon");
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	14.08.2023
	 * 
	 * @param pID
	 * @throws Exception
	 */
	public static void resetPack(int pID) throws Exception{
		Pack vCur, vOldPack;
		
		if (pID >= 0) {
			for (int i=0; i<packs.size(); i++) {
				vCur = packs.get(i);
				
				if (vCur.getId() == pID) {
					vOldPack = loadPack(pID);
					
					copyPack(vCur, vOldPack);
					
					i = packs.size();
				}
			}
		}else throw new Exception("02; rsPa,DaCon");
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	14.08.2023
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public static boolean doesPackExist(int pID) throws Exception{
		boolean vRet = false;
		
		if (pID >= 0) {
			for (int i=0; (i<packs.size()) && (vRet == false); i++) {
				if (packs.get(i).getId() == pID) vRet = true;
			}
		} else throw new Exception("02; dPaE,DaCon");
		
		return vRet;
	}
	/**	Dh	14.08.2023
	 * 
	 * @param pID
	 * @return
	 * @throws Exception
	 */
	public static boolean doesSessionExist(int pID) throws Exception{
		boolean vRet = false;
		
		if (pID >= 0) {
			for (int i=0; (i<sessions.size()) && (vRet == false); i++) {
				if (sessions.get(i).getId() == pID) vRet = true;
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
	
	/**	Dh	14.08.2023
	 * 
	 * @throws Exception
	 */
	public static void loadAllPacks() throws Exception{
		Pack vPack;
		File vDirectory, vCurFile;
		Object[] vPackFiles;
		
		packs = new ArrayList<Pack>();
		
		vDirectory = new File(systemFile+packPath);
		vPackFiles = Files.list(vDirectory.toPath()).toArray();
		
		if (vPackFiles != null) {
			for (int i=0; i<vPackFiles.length; i++) {
				vCurFile = ((Path)vPackFiles[i]).toFile();
				
				vPack = JAXB.unmarshal(vCurFile, Pack.class);
				packs.add(vPack);
			}
			
			packs.sort((pPack1, pPack2) -> {
				return pPack1.getId() - pPack2.getId();
			});
			setParentsForSubElementsInPackList(packs);
		}
	}
	/**	Dh	14.08.2023
	 * 
	 * @throws Exception
	 */
	public static void loadAllSessions() throws Exception{
		Session vSession;
		File vDirectory, vCurFile;
		Object[] vSessionFiles;
		
		sessions = new ArrayList<Session>();
		
		vDirectory = new File(systemFile+sessionPath);
		vSessionFiles = Files.list(vDirectory.toPath()).toArray();
		
		if (vSessionFiles != null) {
			for (int i=0; i<vSessionFiles.length; i++) {
				vCurFile = ((Path)vSessionFiles[i]).toFile();
				
				vSession = JAXB.unmarshal(vCurFile, Session.class);
				sessions.add(vSession);
			}
			
			sessions.sort((pSession1, pSession2) -> {
				return pSession1.getId() - pSession2.getId();
			});
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
	
	/**	Dh	14.08.2023
	 * 
	 * @param pStage
	 * @return
	 * @throws Exception
	 */
	public static Pack importPack(File pFile) throws Exception{
		Pack vRet;
		
		if (pFile.exists()) {
			vRet = JAXB.unmarshal(pFile, Pack.class);
			
			vRet.setId(genNewIDFromIDElementList(packs));
			addPack(vRet);
		}
		else throw new Exception("21; iPa,DaCon");
		
		return vRet;
	}
	/**	Dh	14.08.2023
	 * 
	 * @param pStage
	 * @return
	 * @throws Exception
	 */
	public static Session importSession(File pFile) throws Exception{
		Session vRet;
		
		if (pFile.exists()) {
			vRet = JAXB.unmarshal(pFile, Session.class);
			
			vRet.setId(genNewIDFromIDElementList(sessions));
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
	
	/**	Dh	13.08.2023
	 * 
	 * @param pIDElementList
	 * @return
	 * @throws Exception
	 */
	public static int genNewIDFromIDElementList(ArrayList<? extends IDElement> pIDElementList) throws Exception {
		int vRet = 0;
		IDElement vCur;
		
		if (pIDElementList != null) {
			for (int i=0; i<pIDElementList.size(); i++) {
				vCur = pIDElementList.get(i);
				
				if (vRet == vCur.getId()) vRet ++;
				else i = pIDElementList.size();
			}
		}
		
		return vRet;
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	14.08.2023
	 * 
	 * @param pID
	 * @param pIDElementList
	 * @return
	 */
	private static boolean checkIfIDIsInList(int pID, ArrayList<? extends IDElement> pIDElementList) {
		boolean vRet = false;
		IDElement vCur;
		
		for (int i=0; (i<pIDElementList.size()) && (vRet == false); i++) {
			vCur = pIDElementList.get(i);
			
			if (vCur.getId() == pID) vRet = true;
		}
		
		return vRet;
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	14.08.2023
	 * 
	 * @param pPack
	 * @throws Exception
	 */
	private static <T extends IDElement> void setParentsForSubElementsInList(ArrayList<T> pElementList, Pack pPack) throws Exception {
		for (T vCur : pElementList) {
			if (vCur instanceof Subrace) ((Subrace) vCur).setParentRace(pPack.getRace(((Subrace) vCur).getParentID()));
			else if (vCur instanceof Subculture) ((Subculture) vCur).setParentCulture(pPack.getCulture(((Subculture) vCur).getParentID()));
		}
	}
	
	/**	Dh	14.08.2023
	 * 
	 * @param pPackList
	 * @throws Exception
	 */
	private static void setParentsForSubElementsInPackList(ArrayList<Pack> pPackList) throws Exception {
		for (Pack vCur : pPackList) {
			setParentsForSubElementsInList(vCur.getCultures(), vCur);
			setParentsForSubElementsInList(vCur.getRaces(), vCur);
		}
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	14.08.2023
	 * 
	 * @param pCopyPack
	 * @param pOriginPack
	 * @throws Exception
	 */
	private static void copyPack(Pack pCopyPack, Pack pOriginPack) throws Exception {
		pCopyPack.setName(pOriginPack.getName());
		
		pCopyPack.setRaces(pOriginPack.getRaces());
		pCopyPack.setCultures(pOriginPack.getCultures());
	}
	
}
