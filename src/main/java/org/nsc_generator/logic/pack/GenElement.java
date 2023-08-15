/**	NSC_Generator v0.21		Dh	15.08.2023
 * 	
 * 	logic.pack
 * 	  GenElement
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

import java.util.Random;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.nsc_generator.logic.LogManager;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@XmlRootElement(name = "genelement")
@XmlType(propOrder = {"number", "side", "offset"})

@JsonRootName(value = "genelement")
@JsonPropertyOrder({
	"id", "name", "number", "side", "offset"
})
public class GenElement {
	@JsonProperty("number")
	private int number;
	@JsonProperty("side")
	private int side;
	@JsonProperty("offset")
	private int offset;
	
	/**	Dh	23.02.2021
	 * 
	 */
	public GenElement() {
		number = 0;
		side = 0;
		offset = 0;
	}
	/**	Dh	07.08.2023
	 * 
	 * @param pNumber
	 * @param pSide
	 */
	public GenElement(int pNumber, int pSide) {
		this(pNumber, pSide, 0);
	}
	/**	Dh	07.08.2023
	 * 
	 * @param pNumber
	 * @param pSide
	 * @param pOffset
	 */
	public GenElement(int pNumber, int pSide, int pOffset) {
		try {
			setNumber(pNumber);
			setSide(pSide);
			setOffset(pOffset);
		} catch(Exception ex) {LogManager.handleException(ex);}
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	23.02.2021
	 * 
	 * @return
	 */
	@XmlAttribute
	public int getNumber() {
		return number;
	}
	/**	Dh	25.02.2021
	 * 
	 * @return
	 */
	@XmlAttribute
	public int getSide() {
		return side;
	}
	/**	Dh	25.02.2021
	 * 
	 * @return
	 */
	@XmlAttribute
	public int getOffset() {
		return offset;
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	23.02.2021
	 * 
	 * pNumber muss groessergleich 0 sein.
	 * 
	 * @param pNumber
	 * @throws Exception
	 */
	public void setNumber(int pNumber) throws Exception {
		if (pNumber >= 0) number = pNumber;
		else throw new Exception("02; sNum,GeEle");
	}
	/**	Dh	23.02.2021
	 * 
	 * 	pSide muss groessergleich 0 sein.
	 * 
	 * @param pSide
	 * @throws Exception
	 */
	public void setSide(int pSide) throws Exception{
		if (pSide >= 0) side = pSide;
		else throw new Exception("02; sSid,GeEle");
	}
	/**	Dh	23.02.2021
	 * 
	 * @param pOffset
	 * @throws Exception
	 */
	public void setOffset(int pOffset) throws Exception{
		offset = pOffset;
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	23.02.2021
	 * 
	 * 	Generiert anhand der eingestellten Zustandsvariablen einen Wert.
	 * 		number W side + offset.
	 * 
	 * @return
	 */
	protected int genValue() {
		int vRet = offset;
		Random vRan = new Random();
		
		if (side > 0) {
			for (int i=0; i < number; i++) {
				vRet += vRan.nextInt(side) + 1;
			}
		}
		
		return vRet;
	}
	
	/**	Dh	27.02.2021
	 * 
	 * @return
	 */
	public GenElement copy() {
		return new GenElement(number, side, offset);
	}
}
