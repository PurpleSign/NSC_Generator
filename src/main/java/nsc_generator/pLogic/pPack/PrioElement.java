/**	NSC_Generator v0.0		Dh	14.03.2021
 * 	
 * 	pLogic.pPack
 * 	  IDElement
 * 	    PrioElement
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

package nsc_generator.pLogic.pPack;

import javax.xml.bind.annotation.XmlRootElement;

import nsc_generator.pLogic.IDElement;
import nsc_generator.pLogic.MainManager;

@XmlRootElement(name = "prioelement")

public class PrioElement extends IDElement {
	private int priority;
	
	/**	Dh	14.03.2021
	 * 
	 */
	public PrioElement() {
		super();
		
		priority = -1;
	}
	/**	Dh	14.03.2021
	 * 
	 * @param pID
	 * @param pName
	 * @param pPriority
	 */
	public PrioElement(int pID, String pName, int pPriority) {
		super(pID, pName);
		
		try {setPriority(pPriority);}
		catch(Exception ex) {MainManager.handleException(ex);}
	}

//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	14.03.2021
	 * 
	 * @return
	 */
	public int getPriority() {
		return priority;
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	14.03.2021
	 * 
	 * 	pPriority muss groessergleich 0 sein.
	 * 
	 * @param pPriority
	 * @throws Exception
	 */
	public void setPriority(int pPriority) throws Exception{
		if (pPriority >= 0) priority = pPriority;
		else throw new Exception("02; sPri,PriEle");
	}
	
}
