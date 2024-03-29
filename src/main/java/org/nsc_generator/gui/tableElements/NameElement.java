/**	NSC_Generator v0.2		Dh	07.08.2023
 * 	
 * 	gui.tableElements
 * 	  NameElement
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

public class NameElement {
	private int id;
	private String name;
	
	/**	Dh	07.08.2023
	 * 
	 * @param pID
	 * @param pName
	 */
 	public NameElement(int pID, String pName) {
 		try {
			setId(pID);
			setName(pName);
		} catch(Exception ex) {LogManager.handleException(ex);}
	}

//--------------------------------------------------------------------------------------------------------
 	
 	/**	Dh	04.03.2021
 	 * 
 	 * @return
 	 */
 	public int getId() {
		return id;
	}
	/**	Dh	04.03.2021
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}
 	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	04.03.2021
	 * 
	 * @param pID
	 * @throws Exception
	 */
	public void setId(int pID) throws Exception {
		if (pID >= 0) id = pID;
		else throw new Exception("02; sId,PElTEl");
	}
	/**	Dh	04.03.2021
	 * 
	 * @param pName
	 * @throws Exception
	 */
	public void setName(String pName) throws Exception{
		if (pName != null) name = pName;
		else throw new Exception("02, sNa,PElTEl");
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	04.03.2021
	 * 
	 */
	@Override
	public String toString() {
		return this.name;
	}
	
//--------------------------------------------------------------------------------------------------------
 	
}
