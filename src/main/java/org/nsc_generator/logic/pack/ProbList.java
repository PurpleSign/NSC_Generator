/**	NSC_Generator v0.2		Dh	08.08.2023
 * 	
 * 	logic.pack
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

package org.nsc_generator.logic.pack;

import org.nsc_generator.logic.IDElement;
import org.nsc_generator.logic.LogManager;

import java.util.ArrayList;
import java.util.Random;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name = "problist")
@XmlSeeAlso({ProbElement.class})

public class ProbList extends IDElement {
	private ArrayList<ProbElement> probElements;
	
	/**	Dh	08.08.2023
	 * 
	 */
	public ProbList() {
		probElements = new ArrayList<ProbElement>();
	}
	/**	Dh	08.08.2023
	 * 
	 * @param pProbElementList
	 */
	public ProbList(ArrayList<ProbElement> pProbElements) {
		try {
			setProbElements(pProbElements);
		} catch(Exception ex) {LogManager.handleException(ex);}
	}

//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	08.08.2023
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
			for (int i=0; (i<probElements.size()) && (vRet == null); i++) {
				if (probElements.get(i).getId() == pProbElementID) vRet = probElements.get(i);
			}
		} else throw new Exception("02; gPrEl,ProLis");
		
		return vRet;
	}
	
	/**	Dh	08.08.2023
	 * 
	 * @return
	 */
	public ArrayList<ProbElement> getProbElements(){
		return probElements;
	}
	
	//----------------------------------------------------------------------------------------------------
	/**	Dh	08.08.2023
	 * 
	 * @param pProbElements
	 * @throws Exception
	 */
	public void setProbElements(ArrayList<ProbElement> pProbElements) throws Exception{
		int vTotalProb = 0;
		
		if (pProbElements != null) {
			for (ProbElement vCur : pProbElements) {
				vTotalProb += vCur.getProbability();
			}
			
			if ((vTotalProb == 100) || ((vTotalProb == 0) && (pProbElements.size() == 0))) probElements = pProbElements;
			else throw new Exception("02; sPE,ProLi");
		}
		else probElements = new ArrayList<ProbElement>();
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	08.08.2023
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
		
		for (int i=0; (i<probElements.size()) && (vRet == null); i++) {
			vRet = probElements.get(i);
			
			vCurProbCount += (vRet.getProbability()/100);
			if (vProb >=  vCurProbCount) vRet = null;
		}
		
		if (vRet == null) throw new Exception("04; gePrEl,ProLi");
		
		return vRet;
	}
	
}
