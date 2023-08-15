/**	NSC_Generator v0.21		Dh	15.08.2023
 * 	
 * 	logic.pack
 * 	  IDElement
 * 	    Culture
 * 	      Subculture
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

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

import org.nsc_generator.logic.LogManager;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@XmlRootElement(name = "subculture")
@XmlSeeAlso({ProbList.class, ProbElement.class})

@JsonRootName(value = "subculture")
@JsonPropertyOrder({
	"id", "name", "parentID", "originCultureList", "raceList", "sexualityList", "hairlengthList", "soList"
})
public class Subculture extends Culture implements SubElement{
	@JsonProperty("parentID")
	private int parentID;
	@JsonIgnore
	private Culture parentCulture;
	
	/**	Dh	11.03.2021
	 * 
	 */
	public Subculture() {
		super();
		
		parentID = -1;
	}
	/**	Dh	07.08.2023
	 * 
	 * @param pID
	 * @param pName
	 * @param pParentCulture
	 */
	public Subculture(int pID, String pName, Culture pParentCulture) {
		super(pID, pName);
		
		try {setParentCulture(pParentCulture);}
		catch(Exception ex) {LogManager.handleException(ex);}
	}
	/**	Dh	07.08.2023
	 * 
	 * @param pID
	 * @param pName
	 * @param pOriginCultureList
	 * @param pRaceList
	 * @param pSexualityList
	 * @param pHairlengthList
	 * @param pSOList
	 * @param pParentCulture
	 */
	public Subculture(int pID, String pName, ProbList pOriginCultureList, ProbList pRaceList, ProbList pSexualityList,
			ProbList pHairlengthList, ProbList pSOList, Culture pParentCulture) {
		super(pID, pName, pOriginCultureList, pRaceList, pSexualityList, pHairlengthList, pSOList);
		
		try {setParentCulture(pParentCulture);}
		catch(Exception ex) {LogManager.handleException(ex);}
	}

//--------------------------------------------------------------------------------------------------------

	/**	Dh	11.03.2021
	 * 
	 * @return
	 */
	@XmlAttribute
	public int getParentID() {
		return parentID;
	}
	
	/**	Dh	24.02.2021
	 * 
	 * @return
	 */
	@XmlTransient
	public Culture getParentCulture() {
		return parentCulture;
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	11.03.2021
	 * 
	 * @param pParentCultureID
	 */
	public void setParentID(int pParentID) {
		parentID = pParentID;
		parentCulture = new Culture(pParentID, "Test Fehler, Wrapper");
	}
	
	/**	Dh	11.03.2021
	 * 
	 * 	pParentCulture darf nicht null sein.
	 * 
	 * @param pParentCulture
	 * @throws Exception
	 */
	public void setParentCulture(Culture pParentCulture) throws Exception {
		if (pParentCulture != null) {
			parentCulture = pParentCulture;
			parentID = parentCulture.getId();
		}
		else throw new Exception("04; sPaCu,SCul");
	}
	
	/**	Dh	11.03.2021
	 * 
	 */
	public void setSexualityList(ProbList pSexualityList) throws Exception{
		sexualityList = pSexualityList;
	}
	/**	Dh	11.03.2021
	 * 
	 */
	public void setHairlengthList(ProbList pHairlengthList) throws Exception{
		hairlengthList = pHairlengthList;
	}
	/**	Dh	11.03.2021
	 * 
	 */
	public void setSoList(ProbList pSOList) throws Exception{
		soList = pSOList;
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	14.08.2023
	 * 	
	 * 	Generiert eine Herkunftskultur aus der Liste, anhand der jeweiligen Wahrscheinlichkeiten, falls das in der Subkultur gesetzt ist.
	 * 		Falls nicht, nimmt es die Liste der angegeben Elternkultur.
	 * 
	 */
	public ProbElement genOriginCulture() throws Exception {
		ProbElement vRet = null;
		
		if (this.getOriginCultureList().getProbElements().size() != 0) {
			vRet = super.genOriginCulture();
		} else vRet = parentCulture.genOriginCulture();
		
		return vRet;
	}
	/**	Dh	14.08.2023
	 * 	
	 * 	Generiert eine REasse/Ethnie aus der Liste, anhand der jeweiligen Wahrscheinlichkeiten, falls das in der Subkultur gesetzt ist.
	 * 		Falls nicht, nimmt es die Liste der angegeben Elternkultur.
	 * 
	 */
	public ProbElement genRace() throws Exception {
		ProbElement vRet = null;
		
		if (this.getRaceList().getProbElements().size() != 0) {
			vRet = super.genRace();
		} else vRet = parentCulture.genRace();
		
		return vRet;
	}
	/**	Dh	14.08.2023
	 * 	
	 * 	Generiert eine Sexualitaet aus der Liste, anhand der jeweiligen Wahrscheinlichkeiten, falls das in der Subkultur gesetzt ist.
	 * 		Falls nicht, nimmt es die Liste der angegeben Elternkultur.
	 * 
	 */
	public ProbElement genSexuality() throws Exception {
		ProbElement vRet = null;
		
		if (this.getSexualityList().getProbElements().size() != 0) {
			vRet = super.genSexuality();
		} else vRet = parentCulture.genSexuality();
		
		return vRet;
	}
	/**	Dh	14.08.2023
	 * 	
	 * 	Generiert eine Haarlaenge aus der Liste, anhand der jeweiligen Wahrscheinlichkeiten, falls das in der Subkultur gesetzt ist.
	 * 		Falls nicht, nimmt es die Liste der angegeben Elternkultur.
	 * 
	 */
	public ProbElement genHairlength() throws Exception {
		ProbElement vRet = null;
		
		if (this.getHairlengthList().getProbElements().size() != 0) {
			vRet = super.genHairlength();
		} else vRet = parentCulture.genHairlength();
		
		return vRet;
	}
	/**	Dh	14.08.2023
	 * 	
	 * 	Generiert einen SozialStatus (So) aus der Liste, anhand der jeweiligen Wahrscheinlichkeiten, falls das in der Subkultur gesetzt ist.
	 * 		Falls nicht, nimmt es die Liste der angegeben Elternkultur.
	 * 
	 */
	public ProbElement genSo() throws Exception {
		ProbElement vRet = null;
		
		if (this.getSoList().getProbElements().size() != 0) {
			vRet = super.genSo();
		} else vRet = parentCulture.genSo();
		
		return vRet;
	}
	
}
