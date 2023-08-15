/**	NSC_Generator v0.21		Dh	15.08.2023
 * 	
 * 	logic.pack
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

package org.nsc_generator.logic.pack;

import java.util.ArrayList;
import java.util.Random;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

import org.nsc_generator.logic.IDElement;
import org.nsc_generator.logic.LogManager;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@XmlRootElement(name = "priolist")
@XmlSeeAlso({PrioElement.class})
@JsonRootName(value = "priolist")

public class PrioList extends IDElement{
	@XmlTransient
	private int totalPriorityNumber;
	@JsonProperty("prioElements")
	private ArrayList<PrioElement> prioElements;
	
	/**	Dh	08.08.2023
	 * 
	 */
	public PrioList() {
		this(new ArrayList<PrioElement>());
	}
	/**	Dh	08.08.2023
	 * 
	 * @param pPrioElementList
	 */
	public PrioList(ArrayList<PrioElement> pPrioElements) {
		this(0, "", pPrioElements);
	}
	/**	Dh	08.08.2023
	 * 
	 * @param pID
	 * @param pName
	 * @param pPrioElementList
	 */
	public PrioList(int pID, String pName, ArrayList<PrioElement> pPrioElements) {
		try {
			super.setId(pID);
			super.setName(pName);
			
			setPrioElements(pPrioElements);
		} catch(Exception ex) {LogManager.handleException(ex);}
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	08.08.2023
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
			for (int i=0; (i < prioElements.size()) && (vRet == null); i++) {
				if (prioElements.get(i).getId() == pPrioElementID) vRet = prioElements.get(i);
			}
		} else throw new Exception("02; gPrEl,PriLis");
		
		return vRet;
	}
	
	/**	Dh	08.08.2023
	 * 
	 * @return
	 */
	public ArrayList<PrioElement> getPrioElements(){
		return prioElements;
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	08.08.2023
	 * 
	 * @param pPrioElements
	 * @throws Exception
	 */
	public void setPrioElements(ArrayList<PrioElement> pPrioElements) throws Exception {
		if (pPrioElements != null) prioElements = pPrioElements;
		else prioElements = new ArrayList<PrioElement>();
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	08.08.2023
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
			
			for (int i=0; (i<prioElements.size()) && (vRet == null); i++) {
				vRet = prioElements.get(i);
				
				vCurPrioNumber += vRet.getPriority();
				if (vPrioNumber >  vCurPrioNumber) vRet = null;
			}
		}else vRet = new PrioElement(0, "", 0);
		
		if (vRet == null) throw new Exception("04; gePrEl,PriLi");
		
		return vRet;
	}
		
}
