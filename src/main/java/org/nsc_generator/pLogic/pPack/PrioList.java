/**	NSC_Generator v0.0		Dh	23.08.2022
 * 	
 * 	pLogic.pPack
 * 	  IDElement
 * 	    PrioList
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

package org.nsc_generator.pLogic.pPack;

import java.util.Random;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

import pDataStructures.List;
import org.nsc_generator.pLogic.IDElement;
import org.nsc_generator.pLogic.MainManager;

@XmlRootElement(name = "priolist")
@XmlSeeAlso({PrioElement.class})

public class PrioList extends IDElement{
	@XmlTransient
	private int totalPriorityNumber;
	private List prioElementList;
	
	/**	Dh	23.08.2022
	 * 
	 */
	public PrioList() {
		prioElementList = new List();
		totalPriorityNumber = 0;
		
		try {
			super.setId(0);
			super.setName("");
		}catch(Exception ex) {MainManager.handleMessage(ex.getMessage());}
	}
	/**	Dh	23.08.2022
	 * 
	 * @param pPrioElementList
	 */
	public PrioList(List pPrioElementList) {
		try {
			super.setId(0);
			super.setName("");
			
			setPrioElementList(pPrioElementList);
		} catch(Exception ex) {MainManager.handleException(ex);}
	}
	/**	Dh	17.10.2021
	 * 
	 * @param pID
	 * @param pName
	 * @param pPrioElementList
	 */
	public PrioList(int pID, String pName, List pPrioElementList) {
		try {
			super.setId(pID);
			super.setName(pName);
			
			setPrioElementList(pPrioElementList);
		} catch(Exception ex) {MainManager.handleException(ex);}
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	14.03.2021
	 * 
	 * 	pPrioElementID muss groessergleich 0.
	 * 
	 * @param pPrioElementID
	 * @return
	 * @throws Exception
	 */
	public PrioElement getPrioElement(int pPrioElementID) throws Exception{
		PrioElement vRet = null;
		
		if (pPrioElementID >= 0) {
			prioElementList.toFirst();
			
			while(!prioElementList.isEnd() && vRet == null) {
				vRet = (PrioElement)prioElementList.getCurrent();
				
				if (vRet.getId() != pPrioElementID) vRet = null;
				
				prioElementList.next();
			}
		} else throw new Exception("02; gPrEl,PriLis");
		
		return vRet;
	}
	/**	Dh	14.03.2021
	 * 
	 * @return
	 */
	public List getPrioElementList() {
		return prioElementList;
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	18.03.2021
	 * 
	 * 	pPrioElementList darf nicht null sein und nur Objekte vom Typ PrioElement enthalten.
	 * 
	 * @param pPrioElementList
	 * @throws Exception
	 */
	public void setPrioElementList(List pPrioElementList) throws Exception {
		Object vCur;
		
		if (pPrioElementList != null) {
			totalPriorityNumber = 0;
			pPrioElementList.toFirst();
			
			while(!pPrioElementList.isEnd()) {
				vCur = pPrioElementList.getCurrent();
				
				if (vCur instanceof PrioElement) totalPriorityNumber += ((PrioElement)vCur).getPriority();
				
				pPrioElementList.next();
			}
			
			prioElementList = pPrioElementList;
		} else throw new Exception("04; sPrElLi,PribLi");
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	23.08.2022
	 * 
	 * 	Waehlt zufaellig anhand der entsprechenden Prioritaeten ein PrioElement
	 * 		aus der pPrioElementList aus und gibt sie zurueck.
	 * 
	 * @return
	 * @throws Exception
	 */
	public PrioElement genPrioElement() throws Exception {
		PrioElement vRet = null;
		Random vRand = new Random();
		int vCurPrioNumber, vPrioNumber;
		
		if (totalPriorityNumber > 0) {
			vCurPrioNumber = 0;
			vPrioNumber = vRand.nextInt(totalPriorityNumber)+1;
			
			prioElementList.toFirst();
			while(!prioElementList.isEnd() && (vRet == null)) {
				vRet = (PrioElement) prioElementList.getCurrent();
				
				vCurPrioNumber += vRet.getPriority();
				if (vPrioNumber >  vCurPrioNumber) vRet = null;
				
				prioElementList.next();
			}
		}else vRet = new PrioElement(0, "", 0);
		
		if (vRet == null) throw new Exception("04; gePrEl,PriLi");
		
		return vRet;
	}
	
}
