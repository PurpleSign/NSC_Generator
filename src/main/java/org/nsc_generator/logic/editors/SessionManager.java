/**	NSC_Generator v0.0		Dh	19.04.2021
 * 	
 * 	pLogic.pEditors
 * 	  Editor
 * 	    SessionEditor
 * 	      SesseionManger
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

package org.nsc_generator.logic.editors;


import pDataStructures.List;
import org.nsc_generator.logic.DatabaseConnector;
import org.nsc_generator.logic.MainManager;
import org.nsc_generator.logic.Session;

public class SessionManager extends SessionEditor {
	private int packID;
	private SessionEditor sessionEditor;
	
	/**	Dh	10.03.2021
	 * 
	 * @param pSession
	 */
	public SessionManager(SessionEditor pSessionEditor) {
		super();
		
		sessionEditor = pSessionEditor;
		if (sessionEditor != null) session = sessionEditor.getSession();
		
		packID = -1;
	}

//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	11.03.2021
	 * 
	 * @return
	 */
	public int getCurrentPackID() {
		return packID;
	}
	
	/**	Dh	10.03.2021
	 * 
	 * @return
	 * @throws Exception
	 */
	public List getSessionList() throws Exception{
 		return genObjectArrayListFromIDElementList(DatabaseConnector.getSessionList());
 	}
	
	/**	Dh	11.03.2021
	 * 
	 * @return
	 * @throws Exception
	 */
	public SessionEditor getSessionEditor() throws Exception {
		if (session != null) return new SessionEditor(session);
		else throw new Exception("04; gSE,SeMan");
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	10.03.2021
	 * 
	 */
	public void setCurrentPack(int pID) {
		packID = pID;
	}
	
	/**	Dh	10.03.2021
	 * 
	 * @param pID
	 * @throws Exception
	 */
	public void setSession(int pID) throws Exception{
 		Session vCur = DatabaseConnector.getSession(pID);
 		
 		if (vCur != null) session = vCur;
 		else throw new Exception("04; sSe,SeEd");
 	}
	/**	Dh	11.03.2021
	 * 
	 * @throws Exception
	 */
	public void newSession() throws Exception{
		if ((packID >= 0) && (DatabaseConnector.doesPackExist(packID))) {
			session = DatabaseConnector.newSession("Name", packID);
		}
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	11.03.2021
	 * 
	 * @throws Exception
	 */
	public void delete() throws Exception{
 		if ((sessionEditor == null) || (session.getId() != sessionEditor.session.getId())) {
 			DatabaseConnector.removeSession(session.getId());
 			session = null;
 		} else MainManager.handleMessage("Diese Sitzung kann nicht gelöscht werden.");
 	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	10.03.2021
	 * 
	 * @throws Exception
	 */
	public void applyCurrentSession() throws Exception {
 		if ((sessionEditor != null) && (session != null)) sessionEditor.session = session;
 	}
	
}
