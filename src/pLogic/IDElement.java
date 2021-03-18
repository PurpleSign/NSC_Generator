/**	NSC_Generator v0.0		Dh	25.02.2021
 * 	
 * 	pLogic
 * 	  IDElement
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

package pLogic;

import javax.xml.bind.annotation.XmlAttribute;

public abstract class IDElement {
	private int id;
	private String name;
	
	/**	Dh	24.02.2021
	 * 
	 */
	public IDElement() {
		id = -1;
		name = "";
	}
	/**	Dh	25.02.2021
	 * 
	 * @param pID
	 * @param pName
	 */
	public IDElement(int pID, String pName) {
		try {
			setId(pID);
			setName(pName);
		}catch(Exception ex) {MainManager.handleException(ex);}
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	24.02.2021
	 * 
	 * @return
	 */
	@XmlAttribute
	public int getId() {
		return id;
	}
	/**	Dh	24.02.2021
	 * 
	 * @return
	 */
	@XmlAttribute
	public String getName() {
		return name;
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	24.02.2021
	 * 
	 * @param pId
	 * @throws Exception
	 */
	public void setId(int pID) throws Exception{
		if (pID >= 0) id = pID;
		else throw new Exception("02; sId,IDEle");
	}
	/**	Dh	24.02.2021
	 * 
	 * @param pName
	 * @throws Exception
	 */
	public void setName(String pName) throws Exception{
		if (pName != null) name = pName;
		else throw new Exception("04; sNa,IDEle");
	}

}
