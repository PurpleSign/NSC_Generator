/**	NSC_Generator v0.21		Dh	15.08.2023
 * 	
 * 	logic
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
package org.nsc_generator.logic;

import org.nsc_generator.logic.pack.Pack;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "session")
@XmlSeeAlso({NPC.class})
@XmlType(propOrder = {"npcs", "currentNPCID"})
@JsonRootName(value = "session")
@JsonPropertyOrder({
	"id", "name", "npcs", "currentNPCID"
})
public class Session extends IDElement {
	private Pack usedPack;
	private NPC currentNPC;
	
	private ArrayList<NPC> npcs;
	
	/**	Dh	08.08.2023
	 * 
	 */
	public Session() {
		super();
		
		usedPack = new Pack();
		currentNPC = new NPC();

		npcs = new ArrayList<NPC>();
	}
	/**	Dh	08.08.2023
	 * 
	 * @param pID
	 * @param pName
	 * @param pPack
	 */
	public Session(int pID, String pName, Pack pPack) {
		this(pID, pName, pPack, new ArrayList<NPC>(), new NPC());
	}
	/**	Dh	07.08.2023
	 * 
	 * @param pID
	 * @param pName
	 * @param pPack
	 * @param pNPCList
	 * @param pCurrentNPC
	 */
	public Session(int pID, String pName, Pack pPack, ArrayList<NPC> pNPCs, NPC pCurrentNPC) {
		super(pID, pName);
		
		try {
			setUsedPack(pPack);
			setCurrentNPC(pCurrentNPC.getId());
			
			setNpcs(pNPCs);
		} catch(Exception ex) {LogManager.handleException(ex);}
	}

//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	10.03.2021
	 * 
	 * @return
	 */
	@XmlAttribute
	@JsonProperty("packID")
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
	@JsonProperty("currentNPCID")
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
	
	/**	Dh	08.08.2023
	 * 
	 * @return
	 */
	public ArrayList<NPC> getNpcs(){
		return npcs;
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	07.08.2023
	 * 
	 * 	Moggelmethode zum laden des usedPack beim laden der Session.
	 */
	public void setPackID(int pID)  {
		try {
			if (pID >= 0) usedPack = DatabaseConnector.getPack(pID);
			else usedPack = null;
		}catch(Exception ex) {LogManager.handleException(ex);}
	}
	/**	Dh	08.08.2023
	 * 
	 * @param pID
	 */
	public void setCurrentNPCID(int pID)  {
		try {
			if (pID >= 0) {
				currentNPC = null;
				for (int i=0; (i<npcs.size()) && (currentNPC == null); i++) {
					if (npcs.get(i).getId() == pID) currentNPC = npcs.get(i);
				}
				
				if (currentNPC == null) throw new Exception("02; sCNPCID,Se");
			}else currentNPC = null;
		}catch(Exception ex) {LogManager.handleException(ex);}
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
	/**	Dh	08.08.2023
	 * 
	 * @param pNPC
	 */
	public void setCurrentNPC(int pCurrentNPCID) throws Exception{
		if (pCurrentNPCID >= 0) {
			currentNPC = null;
			
			for (int i=0; (i<npcs.size()) && (currentNPC == null); i++) {
				if (npcs.get(i).getId() == pCurrentNPCID) currentNPC = npcs.get(i);
			}
			
			if (currentNPC == null) throw new Exception("02b; sCNPC,Ses");
		} else if (pCurrentNPCID == -1) currentNPC = null;
		else throw new Exception("02; sCNPC,Ses");
	}
	
	/**	Dh	08.08.2023
	 * 
	 * @param pNPCs
	 * @throws Exception
	 */
	public void setNpcs(ArrayList<NPC> pNPCs) throws Exception {
		ArrayList<NPC> vCheckList = new ArrayList<NPC>();
		
		if (pNPCs != null) {
			for (NPC vCur : pNPCs) {
				if (isIDInIDElementList(vCur.getId(), vCheckList) == true) throw new Exception("02; sNPC,Ses");
				vCheckList.add(vCur);
			}
			
			npcs = pNPCs;
		} else throw new Exception("04; sNPC,Ses");
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	08.08.2023
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
				for (NPC vNPC : npcs) {
					if (vNPC.getId() == pNPC.getId()) throw new Exception("02a; aNPC,Ses");
				}
				
				npcs.add(pNPC);
			} else throw new Exception("02b; aNPC,Ses");
			
		}else throw new Exception("04; aNPC,Ses");
	}
	/**	Dh	08.08.2023
	 * 
	 * 	Entfernt ein NPC Objekt aus der npcList, zu der die uebergebene pNPCID gehoert.
	 * 		pNPCID muss groessergleich 0 sein und muss zu einem Objekt der Liste gehoeren.
	 * 
	 * @param pNPCID
	 * @throws Exception
	 */
	public void removeNPC(int pNPCID) throws Exception{
		NPC vCur = null;
		
		if (pNPCID >= 0) {
			for (int i=0; (i<npcs.size()) && (vCur == null); i++) {
				if (npcs.get(i).getId() == pNPCID) npcs.remove(i);
			}
		}else throw new Exception("02; rNPC,Ses");
	}
	
	/**	Dh	08.08.2023
	 * 
	 * 	Leert die npcList.
	 */
	public void removeAllNPC() {
		npcs = new ArrayList<NPC>();
	}
	

	
}
