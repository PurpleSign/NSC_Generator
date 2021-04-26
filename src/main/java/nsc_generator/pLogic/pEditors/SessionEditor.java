/**	NSC_Generator v0.0		Dh	19.04.2021
 * 	
 * 	pLogic.pEditors
 * 	  Editor
 * 	    SessionEditor
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
 */

package nsc_generator.pLogic.pEditors;

import java.io.File;

import pDataStructures.List;
import nsc_generator.pLogic.DatabaseConnector;
import nsc_generator.pLogic.MainManager;
import nsc_generator.pLogic.NPC;
import nsc_generator.pLogic.Session;
import nsc_generator.pLogic.pPack.Pack;

public class SessionEditor extends Editor {
	private int newPackID;
	protected Session session;
	
	/**	Dh	10.03.2021
	 * 
	 */
	public SessionEditor() {
		session = null;
		newPackID = -1;
	}
	/**	Dh	09.03.2021
	 * 	
	 * @param pSession
	 */
	public SessionEditor(Session pSession) {
		if (pSession != null) session = pSession;
		else MainManager.handleException(new Exception("04; SeEd"));
		
		newPackID = -1;
	}

//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	07.03.2021
	 * 
	 * @return
	 */
	public String getSessionName() {
		return session.getName();
	}
	/**	Dh	19.04.2021
	 * 
	 * @return
	 */
	public String getPackName() {
		String vRet;
		
		if (session.getUsedPack() != null) vRet = session.getUsedPack().getName();
		else vRet = "";
		
		return vRet;
	}
	
	/**	Dh	09.03.2021
	 * 
	 * @return
	 */
	public int getNewPackID() {
		return newPackID;
	}
	
	/**	Dh	07.03.2021
	 * 
	 * @return
	 */
	public int getCurrentNPCID() {
		return session.getCurrentNPC().getId();
	}
	/**	Dh	08.03.2021
	 * 
	 * @return
	 */
	public int getNPCAge() {
		return session.getCurrentNPC().getAge();
	}
	/**	Dh	08.03.2021
	 * 
	 * @return
	 */
	public int getNPCHeight() {
		return session.getCurrentNPC().getHeight();
	}
	/**	Dh	08.03.2021
	 * 
	 * @return
	 */
	public int getNPCWeight() {
		return session.getCurrentNPC().getWeight();
	}
	
	/**	Dh	08.03.2021
	 * 
	 * @return
	 */
	public String getNPCName() {
		return session.getCurrentNPC().getName();
	}
	/**	Dh	08.03.2021
	 * 
	 * @return
	 */
	public String getNPCRace() {
		return session.getCurrentNPC().getRace();
	}
	/**	Dh	08.03.2021
	 * 
	 * @return
	 */
	public String getNPCCulture() {
		return session.getCurrentNPC().getCulture();
	}
	
	public String getNPCJob() {
		return null;
	}
	/**	Dh	08.03.2021
	 * 
	 * @return
	 */
	public String getNPCSex() {
		return session.getCurrentNPC().getSex();
	}
	/**	Dh	08.03.2021
	 * 
	 * @return
	 */
	public String getNPCSO() {
		return session.getCurrentNPC().getSo();
	}
	/**	Dh	13.03.2021
	 * 
	 * @return
	 */
	public String getNPCComplexion() {
		return session.getCurrentNPC().getComplexion();
	}
	/**	Dh	08.03.2021
	 * 
	 * @return
	 */
	public String getNPCHairlength() {
		return session.getCurrentNPC().getHairlength();
	}
	/**	Dh	08.03.2021
	 * 
	 * @return
	 */
	public String getNPCHaircolor() {
		return session.getCurrentNPC().getHaircolor();
	}
	/**	Dh	08.03.2021
	 * 
	 * @return
	 */
	public String getNPCEyecolor() {
		return session.getCurrentNPC().getEyecolor();
	}
	/**	Dh	08.03.2021
	 * 
	 * @return
	 */
	public String getNPCQuirk() {
		return session.getCurrentNPC().getQuirk();
	}
	/**	Dh	08.03.2021
	 * 	
	 * @return
	 */
	public String getNPCSexuality() {
		return session.getCurrentNPC().getSexuality();
	}
	/**	Dh	08.03.2021
	 * 
	 * @return
	 */
	public String getNPCNote() {
		return session.getCurrentNPC().getNote();
	}
	
	/**	Dh	11.03.2021
	 * 
	 * @return
	 */
	public Pack getUsedPack() {
		Pack vRet;
		
		if (session != null) vRet = session.getUsedPack();
		else vRet = null;
		
		return vRet;
	}
	
	/**	Dh	10.03.2021
	 * 
	 * @return
	 */
	protected Session getSession() {
		return session;
	}
	
	/**	Dh	07.03.2021
	 * 
	 * @return
	 * @throws Exception
	 */
	public List getNPCList() throws Exception {
		return genObjectArrayListFromIDElementList(session.getNpcList());
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	07.03.2021
	 * 
	 * @param pName
	 * @throws Exception
	 */
	public void setSessionName(String pName) throws Exception{
 		try { session.setName(pName);}
 		catch(Exception ex) {throw ex;}
 	}
	
	/**	Dh	08.03.2021
	 * 
	 * @param pCurrentNPCID
	 * @throws Exception
	 */
	public void setCurrentNPCID(int pCurrentNPCID) throws Exception {
		session.setCurrentNPC(pCurrentNPCID);
	}
	/**	Dh	11.03.2021
	 * 
	 * @param pPackID
	 * @throws Exception
	 */
	protected void setNewPackID(int pPackID) throws Exception {
		if (pPackID >= 0) newPackID = pPackID;
		else throw new Exception("02; sNPID,SeEdi");
	}
	
	/**	Dh	10.03.2021
	 * 
	 * @param pPackID
	 * @throws Exception
	 */
	protected void setCurrentPack(int pPackID) throws Exception{
		session.setUsedPack(DatabaseConnector.getPack(pPackID));
	}
	
	/**	Dh	08.03.2021
	 * 
	 * @param pNote
	 * @throws Exception
	 */
	public void setNPCNote(String pNote) throws Exception {
		session.getCurrentNPC().setNote(pNote);
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	07.03.2021
	 * 
	 * @param pNPC
	 * @throws Exception
	 */
	public void addNPC(NPC pNPC) throws Exception{
 		session.addNPC(pNPC);
 	}
	
	/**	Dh	07.03.2021
	 * 
	 * @param pNPCID
	 * @throws Exception
	 */
	public void removeNPC(int pNPCID) throws Exception{
 		session.removeNPC(pNPCID);
 	}
	
	/**	Dh	11.03.2021
	 * 
	 * @throws Exception
	 */
	public void newSession() throws Exception{
		if ((newPackID >= 0) && (DatabaseConnector.doesPackExist(newPackID))) {
			session = DatabaseConnector.newSession("Name", newPackID);
			newPackID = -1;
		}
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	08.03.2021
	 * 
	 * @return
	 * @throws Exception
	 */
	public NPCEditor addNPC() throws Exception{
		return new NPCEditor(new NPC(genNewIDFromIDElementList(session.getNpcList())), this);
	}
	/**	Dh	09.03.2021
	 * 
	 * @return
	 * @throws Exception
	 */
	public PackEditor addPack() throws Exception{
		Pack vNew = DatabaseConnector.newPack();
		newPackID = vNew.getId();
		return new PackEditor(vNew);
	}
	
	/**	Dh	08.03.2021
	 * 
	 * @return
	 * @throws Exception
	 */
	public NPCEditor editNPC() throws Exception {
 		return new NPCEditor(session.getCurrentNPC(), this);
 	}
	/**	Dh	09.03.2021
	 * 
	 * @return
	 * @throws Exception
	 */
	public PackEditor editPack() throws Exception{
		return new PackEditor(session.getUsedPack());
	}
	
	/**	Dh	19.04.2021
	 * 
	 * @param pFile
	 * @throws Exception
	 */
	public void importSession(File pFile) throws Exception{
 		Session vNewSession;
 		
 		vNewSession = DatabaseConnector.importSession(pFile);
 		
 		if (vNewSession != null) session = vNewSession;
 	}
 	/**	Dh	19.04.2021
 	 * 
 	 * @param pSaveFile
 	 * @throws Exception
 	 */
 	public void exportSession(File pSaveFile) throws Exception{
 		if (session != null) DatabaseConnector.exportSession(session.getId(), pSaveFile);
 	}
	
 	/**	Dh	19.04.2021
 	 * 
 	 * @param pFile
 	 * @throws Exception
 	 */
 	public void importPack(File pFile) throws Exception{
 		Pack vNewPack;
 		
 		vNewPack = DatabaseConnector.importPack(pFile);
 		
 		if (vNewPack != null) session.setUsedPack(vNewPack);
 	}
 	/**	Dh	19.04.2021
 	 * 
 	 * @param pSaveFile
 	 * @throws Exception
 	 */
 	public void exportPack(File pSaveFile) throws Exception{
 		if ((session != null) && (session.getUsedPack() != null)) DatabaseConnector.exportPack(session.getUsedPack().getId(), pSaveFile);
 	}
 	
 	/**	Dh	19.04.2021
 	 * 
 	 * @param pFile
 	 * @throws Exception
 	 */
 	public void importNPC(File pFile) throws Exception{
 		NPC vNewNPC;
 		
 		vNewNPC = DatabaseConnector.importNPC(pFile);
 		
 		if (vNewNPC != null) {
 			vNewNPC.setId(genNewIDFromIDElementList(session.getNpcList()));
 			session.addNPC(vNewNPC);
 			session.setCurrentNPC(vNewNPC.getId());
 		}
 	}
 	/**	Dh	19.04.2021
 	 * 
 	 * @param pSaveFile
 	 * @throws Exception
 	 */
 	public void exportNPC(File pSaveFile) throws Exception{
 		if ((session != null) && (session.getCurrentNPC() != null)) DatabaseConnector.exportNPC(session.getCurrentNPC(), pSaveFile);
 	}
 	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	09.03.2021
	 * 	
	 * @throws Exception
	 */
	public void updatePack() throws Exception{
		if (DatabaseConnector.doesPackExist(newPackID)) session.setUsedPack(DatabaseConnector.getPack(newPackID));
	}
	
	/**	Dh	09.03.2021
	 * 
	 * @throws Exception
	 */
	public void save() throws Exception {
 		DatabaseConnector.saveSession(session);
 	}
	
}
