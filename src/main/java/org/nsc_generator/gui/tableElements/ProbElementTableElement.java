/**	NSC_Generator v0.2		Dh	07.08.2023
 * 	
 * 	gui.tableElement
 * 	  NameElement
 * 	    ProbElementTableElement
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

package org.nsc_generator.gui.tableElements;

import org.nsc_generator.logic.LogManager;

public class ProbElementTableElement extends NameElement{
	private double probability;
	
	/**	Dh	07.08.2023
	 * 
	 * @param pID
	 * @param pName
	 * @param pProbability
	 */
	public ProbElementTableElement(int pID, String pName, double pProbability) {
		super(pID, pName);
		
		try {setProbability(pProbability);}
		catch(Exception ex) {LogManager.handleException(ex);}
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	04.03.2021
	 * 
	 * @return
	 */
	public double getProbability() {
		return probability;
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	04.03.2021
	 * 
	 * @param pProbability
	 * @throws Exception
	 */
	public void setProbability(double pProbability) throws Exception {
		if ((pProbability >= 0) && (pProbability <= 100)) probability = pProbability;
		else throw new Exception("02, sPr,PElTEl");
	}
	
//--------------------------------------------------------------------------------------------------------

}
