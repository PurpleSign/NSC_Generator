/**	NSC_Generator v0.2		Dh	07.08.2023
 * 	
 * 	logic
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

package org.nsc_generator.logic;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;

import pDataStructures.List;

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
	/**	Dh	07.08.2023
	 * 
	 * @param pID
	 * @param pName
	 */
	public IDElement(int pID, String pName) {
		try {
			setId(pID);
			setName(pName);
		}catch(Exception ex) {LogManager.handleException(ex);}
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
		else throw new Exception("02; sId,IDEle:"+id+","+name);
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

//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	08.08.2023
	 * 
	 * @param <T>
	 * @param pArrayList
	 * @return
	 */
	protected <T> List convertArrayListToList(ArrayList<T> pArrayList) {
		List vRet = new List();
		
		if ((pArrayList != null) && (!pArrayList.isEmpty())) {
			for (T vTemp : pArrayList) {
				vRet.append(vTemp);
			}
		}
		
		return vRet;
	}
	
	/**	Dh	08.08.2023
	 * 
	 * @param <T>
	 * @param pList
	 * @return
	 */
	protected <T> ArrayList<T> convertListToArrayList(List pList){
		ArrayList<T> vRet = new ArrayList<T>();
		
		if ((pList != null) && (!pList.isEmpty())) {
			pList.toFirst();
			
			while(!pList.isEnd()) {
				vRet.add( (T)pList.getCurrent() );
				
				pList.next();
			}
		}
		
		return vRet;
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	08.08.2023
	 * 
	 * @param pID
	 * @param pIDList
	 * @return
	 * @throws Exception
	 */
	protected boolean isIDInIDElementList(int pID, ArrayList<? extends IDElement> pIDList) throws Exception{
		boolean vRet = false;
		
		if (pIDList != null) {
			if (pID >= -1) {
				for (IDElement vCur : pIDList) {
					if (vCur.getId() == pID) vRet = true;
				}
			} else throw new Exception("02; ciIDaEiL,Pac");
		} else throw new Exception("04; ciIDaEiL,Pac");
		
		return vRet;
	}
	
}
