/**	NSC_Generator v0.2		Dh	07.08.2023
 * 	
 * 	gui.tableElements
 * 	  NameElement
 * 	    PrioElementTableElement
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

public class PrioElementTableElement extends NameElement {
	private int priority;
	
	/**	Dh	07.08.2023
	 * 
	 * @param pID
	 * @param pName
	 * @param pPriority
	 */
	public PrioElementTableElement(int pID, String pName, int pPriority) {
		super(pID, pName);
		
		try {setPriority(pPriority);}
		catch(Exception ex) {LogManager.handleException(ex);}
	}

//--------------------------------------------------------------------------------------------------------

	/**	Dh	15.03.2021
	 * 
	 * @return
	 */
	public int getPriority() {
		return priority;
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	15.03.2021
	 * 
	 * @param pPriority
	 */
	public void setPriority(int pPriority) {
		if (pPriority >= 0) {
			priority = pPriority;
		}
	}
	
}
