/**	NSC_Generator v0.21		Dh	15.08.2023
 * 	
 * 	logic.pack
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
import org.nsc_generator.logic.LogManager;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@XmlRootElement(name = "probelement")
@JsonRootName(value = "probelements")
@JsonPropertyOrder({
	"id", "name", "probability"
})
public class ProbElement extends IDElement{
	@JsonProperty("probability")
	private double probability;
	
	/**	Dh	24.02.2021
	 * 
	 */
	public ProbElement() {
		super();
		
		probability = 0;
	}
	/**	Dh	07.08.2023
	 * 
	 * @param pId
	 * @param pName
	 * @param pProbability
	 */
	public ProbElement(int pID, String pName, double pProbability) {
		super(pID, pName);
		
		try {setProbability(pProbability);} 
		catch(Exception ex) {LogManager.handleException(ex);}
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
