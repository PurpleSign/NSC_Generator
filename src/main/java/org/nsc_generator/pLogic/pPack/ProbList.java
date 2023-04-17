/**	NSC_Generator v0.0		Dh	17.10.2021
 * 	
 * 	pLogic.pPack
 *    IDElement
 * 	    ProbList
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

import pDataStructures.List;
import org.nsc_generator.pLogic.IDElement;
import org.nsc_generator.pLogic.MainManager;

import java.util.Random;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name = "problist")
@XmlSeeAlso({ProbElement.class})

public class ProbList extends IDElement {
	private List probElementList;
	
	/**	Dh	24.02.2021
	 * 
	 */
	public ProbList() {
		probElementList = new List();
	}
	/**	Dh	25.02.2021
	 * 
	 * @param pProbElementList
	 */
	public ProbList(List pProbElementList) {
		try {
			setProbElementList(pProbElementList);
		} catch(Exception ex) {MainManager.handleException(ex);}
	}

//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	24.02.2021
	 * 
	 * 	pProbElementID muss groessergleich 0.
	 * 
	 * @param pProbElementID
	 * @return
	 * @throws Exception
	 */
	public ProbElement getProbElement(int pProbElementID) throws Exception{
		ProbElement vRet = null;
		
		if (pProbElementID >= 0) {
			probElementList.toFirst();
			
			while(!probElementList.isEnd() && vRet == null) {
				vRet = (ProbElement)probElementList.getCurrent();
				
				if (vRet.getId() != pProbElementID) vRet = null;
				
				probElementList.next();
			}
		} else throw new Exception("02; gPrEl,ProLis");
		
		return vRet;
	}
	/**	Dh	24.02.2021
	 * 
	 * @return
	 */
	public List getProbElementList() {
		return probElementList;
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	24.02.2021
	 * 
	 * 	pProbElementList darf nicht null sein und nur Objekte vom Typ ProbElement enthalten, die eine summierte
	 * 		pProbability von 100 haben.
	 * 
	 * @param pProbElementList
	 * @throws Exception
	 */
	public void setProbElementList(List pProbElementList) throws Exception {
		Object vCur;
		int vTotalProbability = 0;
		
		if (pProbElementList != null) {
			pProbElementList.toFirst();
			
			while(!pProbElementList.isEnd()) {
				vCur = pProbElementList.getCurrent();
				
				if (vCur instanceof ProbElement) vTotalProbability += ((ProbElement)vCur).getProbability();
				else throw new Exception("06; sPrElLi,ProLi");
				
				pProbElementList.next();
			}
			
			if ((vTotalProbability == 100) || (vTotalProbability == 0 && pProbElementList.getContentNumber() == 0))
				probElementList = pProbElementList;
			else throw new Exception("02; sPrElLi,ProLi");
		} else throw new Exception("04; sPrElLi,ProbLi");
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	24.02.2021
	 * 
	 * 	Waehlt zufaellig anhand der entsprechenden Wahrscheinlichkeiten ein ProbElement
	 * 		aus der pProbElementList Liste aus und gibt sie zurueck.
	 * 
	 * @return
	 * @throws Exception
	 */
	public ProbElement genProbElement() throws Exception {
		ProbElement vRet = null;
		Random vRand = new Random();
		double vCurProbCount, vProb;
		
		vCurProbCount = 0;
		vProb = vRand.nextDouble();
		
		probElementList.toFirst();
		while(!probElementList.isEnd() && (vRet == null)) {
			vRet = (ProbElement) probElementList.getCurrent();
			
			vCurProbCount += (vRet.getProbability()/100);
			if (vProb >=  vCurProbCount) vRet = null;
			
			probElementList.next();
		}
		
		if (vRet == null) throw new Exception("04; gePrEl,ProLi");
		
		return vRet;
	}
	
}
