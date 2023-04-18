/**	NSC_Generator v0.0		Dh	25.02.2021
 * 	
 * 	pLogic.pPack
 * 	  IDElement
 * 	    ProbElement
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

import javax.xml.bind.annotation.XmlRootElement;

import org.nsc_generator.logic.IDElement;
import org.nsc_generator.logic.MainManager;

@XmlRootElement(name = "probelement")

public class ProbElement extends IDElement{
	private double probability;
	
	/**	Dh	24.02.2021
	 * 
	 */
	public ProbElement() {
		super();
		
		probability = 0;
	}
	/**	Dh	25.02.2021
	 * 
	 * @param pId
	 * @param pName
	 * @param pProbability
	 */
	public ProbElement(int pID, String pName, double pProbability) {
		super(pID, pName);
		
		try {setProbability(pProbability);} 
		catch(Exception ex) {MainManager.handleException(ex);}
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	23.02.2021
	 * 
	 * @return
	 */
	public double getProbability() {
		return probability;
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	23.02.2021
	 * 
	 * 	pProbability muss groessergleich 0 sein.
	 * 
	 * @param pProbability
	 * @throws Exception
	 */
	public void setProbability(double pProbability) throws Exception{
		if (pProbability >= 0) probability = pProbability;
		else throw new Exception("02; sPro,ProEle");
	}

}
