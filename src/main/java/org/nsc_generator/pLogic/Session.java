/**	NSC_Generator v0.0		Dh	10.03.2021
 * 	
 * 	pLogic
 * 	  IDElement
 * 	    Session
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
package org.nsc_generator.pLogic;

import pDataStructures.List;
import org.nsc_generator.pLogic.pPack.Pack;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "session")
@XmlSeeAlso({NPC.class})
@XmlType(propOrder = {"npcList", "currentNPCID"})

public class Session extends IDElement {
	private Pack usedPack;
	private NPC currentNPC;
	
	private List npcList;
	
	/**	Dh	25.02.2021
	 * 
	 */
	public Session() {
		super();
		
		usedPack = new Pack();
		currentNPC = new NPC();
		
		npcList = new List();
	}
	/**	Dh	25.02.2021
	 * 
	 * @param pID
	 * @param pName
	 * @param pPack
	 */
	public Session(int pID, String pName, Pack pPack) {
		super(pID, pName);
		
		try {
			setUsedPack(pPack);
		} catch(Exception ex) {MainManager.handleException(ex);}
		currentNPC = new NPC();
		
		npcList = new List();
	}
	/**	Dh	07.03.2021
	 * 
	 * @param pID
	 * @param pName
	 * @param pPack
	 * @param pNPCList
	 * @param pCurrentNPC
	 */
	public Session(int pID, String pName, Pack pPack, List pNPCList, NPC pCurrentNPC) {
		super(pID, pName);
		
		try {
			setUsedPack(pPack);
			setCurrentNPC(pCurrentNPC.getId());
			
			setNpcList(pNPCList);
		} catch(Exception ex) {MainManager.handleException(ex);}
	}

//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	10.03.2021
	 * 
	 * @return
	 */
	@XmlAttribute
	public int getPackID() {
		int vRet;
		
		if (usedPack != null) vRet = usedPack.getId();
		else vRet = -1;
		
		return vRet;
	}
	/**	Dh	10.03.2021
	 * 
	 * @return
	 */
	@XmlElement(name = "currentNPCID")
	public int getCurrentNPCID() {
		int vRet;
		
		if (currentNPC != null) vRet = currentNPC.getId();
		else vRet = -1;
		
		return vRet;
	}
	
	/**	Dh	25.02.2021
	 * 
	 * @return
	 */
	@XmlTransient
	public Pack getUsedPack() {
		return usedPack;
	}
	/**	Dh	25.02.2021
	 * 
	 * @return
	 */
	@XmlTransient
	public NPC getCurrentNPC() {
		return currentNPC;
	}
	
	/**	Dh	25.02.2021
	 * 
	 * @return
	 */
	public List getNpcList() {
		return npcList;
	}

	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	10.03.2021
	 * 
	 * 	Moggelmethode zum laden des usedPack beim laden der Session.
	 */
	public void setPackID(int pID)  {
		try {
			if (pID >= 0) usedPack = DatabaseConnector.getPack(pID);
			else usedPack = null;
		}catch(Exception ex) {MainManager.handleException(ex);}
	}
	/**	Dh	10.03.2021
	 * 
	 * @param pID
	 */
	public void setCurrentNPCID(int pID)  {
		try {
			if (pID >= 0) {
				npcList.toFirst();
				while(!npcList.isEnd() && (currentNPC == null)) {
					currentNPC = (NPC) npcList.getCurrent();
					
					if (currentNPC.getId() != pID) currentNPC = null;
					
					npcList.next();
				}
				
				if (currentNPC == null) throw new Exception("02; sCNPCID,Se");
			}
			else currentNPC = null;
		}catch(Exception ex) {MainManager.handleException(ex);}
	}
	
	/**	Dh	25.02.2021
	 * 
	 * 	pPack darft nicht null sein.
	 * 
	 * @param pPack
	 * @throws Exception
	 */
	public void setUsedPack(Pack pPack) throws Exception{
		if (pPack != null) usedPack = pPack;
		else throw new Exception("04; sUP,Ses");
	}
	/**	Dh	08.03.2021
	 * 
	 * @param pNPC
	 */
	public void setCurrentNPC(int pCurrentNPCID) throws Exception{
		NPC vCur;
		
		if (pCurrentNPCID >= 0) {
			currentNPC = null;
			npcList.toFirst();
			
			while(!npcList.isEnd() && (currentNPC == null)) {
				vCur = (NPC)npcList.getCurrent();
				
				if (vCur.getId() == pCurrentNPCID) currentNPC = vCur;
				
				npcList.next();
			}
			
			if (currentNPC == null) throw new Exception("02b; sCNPC,Ses");
		} else if (pCurrentNPCID == -1) currentNPC = null;
		else throw new Exception("02; sCNPC,Ses");
	}
	
	/**	Dh	10.03.2021
	 * 
	 * 	pNPCList darf nicht null sein und nur NPC Objekte enthalten, die keine redundanten IDs haben.
	 * 
	 * @param pNPCList
	 * @throws Exception
	 */
	public void setNpcList(List pNPCList) throws Exception {
		Object vCur;
		List vIDList;
		
		if (pNPCList != null) {
			vIDList = new List();
			pNPCList.toFirst();
			while(!pNPCList.isEnd()) {
				vCur = pNPCList.getCurrent();
				
				if (vCur instanceof NPC) {
					if (isIDInIDList( ((NPC)vCur).getId() , vIDList) == true) throw new Exception("02; sNPCL,Ses");
					
					vIDList.append( ((NPC)vCur).getId() );
				}else throw new Exception("06; sNPCL,Ses");
				
				pNPCList.next();
			}
			
			npcList = pNPCList;
		}else throw new Exception("04; sNPCL,Ses");
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	25.02.2021
	 * 
	 * 	Fuegt einen NPC in die npcList ein.
	 * 		pNPC darf nicht null sein, dessen ID muss groessergleich null sein und darf nocht nicht vorhanden sein.
	 * 
	 * @param pNPC
	 * @throws Exception
	 */
	public void addNPC(NPC pNPC) throws Exception{
		if (pNPC != null) {
			if (pNPC.getId() >= 0) {
				npcList.toFirst();
				while(!npcList.isEnd()) {
					if ( ((NPC)npcList.getCurrent()).getId() == pNPC.getId() ) throw new Exception("02a; aNPC,Ses");
					
					npcList.next();
				}
				
				npcList.append(pNPC);
			} else throw new Exception("02b; aNPC,Ses");
			
		}else throw new Exception("04; aNPC,Ses");
	}
	/**	Dh	25.02.2021
	 * 
	 * 	Entfernt ein NPC Objekt aus der npcList, zu der die uebergebene pNPCID gehoert.
	 * 		pNPCID muss groessergleich 0 sein und muss zu einem Objekt der Liste gehoeren.
	 * 
	 * @param pNPCID
	 * @throws Exception
	 */
	public void removeNPC(int pNPCID) throws Exception{
		NPC vCur;
		
		if (pNPCID >= 0) {
			npcList.toFirst();
			while(!npcList.isEnd() && (pNPCID >= 0)) {
				vCur = (NPC) npcList.getCurrent();
				
				if (vCur.getId() == pNPCID) {
					npcList.remove();
					pNPCID = -1;
				}
				
				npcList.next();
			}
			
			if (pNPCID >= 0) throw new Exception("07; rNPC,Ses");
		}else throw new Exception("02; rNPC,Ses");
	}
	
	/**	Dh	25.02.2021
	 * 
	 * 	Leert die npcList.
	 */
	public void removeAllNPC() {
		npcList = new List();
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	25.02.2021
	 * 
	 * @param pID
	 * @param pIDList
	 * @return
	 */
	private boolean isIDInIDList(int pID, List pIDList) {
		boolean vRet = false;
		
		pIDList.toFirst();
		while (!pIDList.isEnd() && (vRet == false)) {
			if ( (int)pIDList.getCurrent() == pID ) vRet = true;
			
			pIDList.next();
		}
		
		return vRet;
	}
	
}
