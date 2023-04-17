/**	NSC_Generator v0.0		Dh	12.03.2021
 * 	
 * 	pLogic.pPack
 * 	  IDElement
 * 	    Race
 * 	      Subrace
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

package org.nsc_generator.pLogic.pPack;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.nsc_generator.pLogic.MainManager;

@XmlRootElement(name = "subrace")

public class Subrace extends Race implements SubElement {
	private int parentID;
	private Race parentRace;

	/**	Dh	11.03.2021
	 * 
	 */
	public Subrace() {
		super();
		
		parentRace = new Race();
		parentID = -1;
	}
	/**	Dh	25.02.2021
	 * 
	 * @param pID
	 * @param pName
	 * @param pParentRace
	 */
	public Subrace(int pID, String pName, Race pParentRace) {
		super(pID, pName);
		
		try {setParentRace(pParentRace);}
		catch(Exception ex) {MainManager.handleException(ex);}
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
	public Race getParentRace() {
		return parentRace;
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	11.03.2021
	 * 
	 * @param pParentRaceID
	 */
	public void setParentID(int pParentID) {
		parentID = pParentID;
		parentRace = new Race(pParentID, "Test Fehler, Wrapper");
	}
	
	/**	Dh	11.03.2021
	 * 
	 * 	pParentRace darf nicht null sein.
	 * 
	 * @param pParentRace
	 * @throws Exception
	 */
	public void setParentRace(Race pParentRace) throws Exception {
		if (pParentRace != null) {
			parentRace = pParentRace;
			parentID = parentRace.getId();
		}
		else throw new Exception("04; sPaRa,SRac");
	}
	
	/**	Dh	11.03.2021
	 * 
	 */
	public void setAge(GenElement pAge) throws Exception{
		age = pAge;
	}
	/**	Dh	11.03.2021
	 * 
	 */
	public void setHeight(GenElement pHeight) throws Exception{
		height = pHeight;
	}
	/**	Dh	11.03.2021
	 * 
	 */
	public void setWeight(GenElement pWeight) throws Exception{
		weight = pWeight;
	}
	
	/**	Dh	11.03.2021
	 * 
	 */
	public void setSexList(ProbList pSexList) throws Exception{
		sexList = pSexList;
	}
	/**	Dh	11.03.2021
	 * 
	 */
	public void setHaircolorList(ProbList pHaircolorList) throws Exception{
		haircolorList = pHaircolorList;
	}
	/**	Dh	11.03.2021
	 * 
	 */
	public void setEyecolorList(ProbList pEyecolorList) throws Exception{
		eyecolorList = pEyecolorList;
	}
	/**	Dh	12.03.2021
	 * 
	 */
	public void setComplexionList(ProbList pComplexionList) throws Exception{
		complexionList = pComplexionList;
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	12.03.2021
	 * 	
	 * 	Generiert ein Alter aus den gesetzten Parametern, falls das in der Subrasse gesetzt ist.
	 * 		Falls nicht, nimmt es gesetzten Parameter der angegeben Elternrasse.
	 * 
	 */
	public int genAge() {
		int vRet = -1;
		
		if ((this.getAge().getNumber() != 0) || (this.getAge().getSide() != 0) || (this.getAge().getOffset() != 0)) {
			vRet = super.genAge();
		} else vRet = parentRace.genAge();
		
		return vRet;
	}
	/**	Dh	12.03.2021
	 * 	
	 * 	Generiert ein Alter aus den gesetzten Parametern, falls das in der Subrasse gesetzt ist.
	 * 		Falls nicht, nimmt es gesetzten Parameter der angegeben Elternrasse.
	 * 
	 */
	public int genHeight() {
		int vRet = -1;
		
		if ((this.getHeight().getNumber() != 0) || (this.getHeight().getSide() != 0) || (this.getHeight().getOffset() != 0)) {
			vRet = super.genHeight();
		} else vRet = parentRace.genHeight();
		
		return vRet;
	}
	/**	Dh	12.03.2021
	 * 	
	 * 	Generiert ein Alter aus den gesetzten Parametern, falls das in der Subrasse gesetzt ist.
	 * 		Falls nicht, nimmt es gesetzten Parameter der angegeben Elternrasse.
	 * 
	 */
	public int genWeight(int pHeight) {
		int vRet = -1;
		
		if ((this.getWeight().getNumber() != 0) || (this.getWeight().getSide() != 0) || (this.getWeight().getOffset() != 0)) {
			vRet = super.genWeight(pHeight);
		} else vRet = parentRace.genWeight(pHeight);
		
		return vRet;
	}
	
	/**	Dh	12.03.2021
	 * 	
	 * 	Generiert ein Geschlecht aus der Liste, anhand der jeweiligen Wahrscheinlichkeiten, falls das in der Subrasse gesetzt ist.
	 * 		Falls nicht, nimmt es die Liste der angegeben Elternrasse.
	 * 
	 */
	public ProbElement genSex() throws Exception {
		ProbElement vRet = null;
		
		if (this.getSexList().getProbElementList().getContentNumber() != 0) {
			vRet = super.genSex();
		} else vRet = parentRace.genSex();
		
		return vRet;
	}
	/**	Dh	12.03.2021
	 * 	
	 * 	Generiert eine Haarfarbe aus der Liste, anhand der jeweiligen Wahrscheinlichkeiten, falls das in der Subrasse gesetzt ist.
	 * 		Falls nicht, nimmt es die Liste der angegeben Elternrasse.
	 * 
	 * @return
	 * @throws Exception
	 */
	public ProbElement genHaircolor() throws Exception {
		ProbElement vRet = null;
		
		if (this.getHaircolorList().getProbElementList().getContentNumber() != 0) {
			vRet = super.genHaircolor();
		} else vRet = parentRace.genHaircolor();
		
		return vRet;
	}
	/**	Dh	12.03.2021
	 * 	
	 * 	Generiert eine Augenfarbe aus der Liste, anhand der jeweiligen Wahrscheinlichkeiten, falls das in der Subrasse gesetzt ist.
	 * 		Falls nicht, nimmt es die Liste der angegeben Elternrasse.
	 * 
	 * @return
	 * @throws Exception
	 */
	public ProbElement genEyecolor() throws Exception {
		ProbElement vRet = null;
		
		if (this.getEyecolorList().getProbElementList().getContentNumber() != 0) {
			vRet = super.genEyecolor();
		} else vRet = parentRace.genEyecolor();
		
		return vRet;
	}
	/**	Dh	12.03.2021
	 * 
	 * 	Generiert einen Teint aus der Liste, anhand der jeweiligen Wahrscheinlichkeiten, falls das in der Subrasse gesetzt ist.
	 * 		Falls nicht, nimmt es die Liste der angegeben Elternrasse.
	 */
	public ProbElement genComplexion() throws Exception {
		ProbElement vRet = null;
		
		if (this.getComplexionList().getProbElementList().getContentNumber() != 0) {
			vRet = super.genComplexion();
		} else vRet = parentRace.genComplexion();
		
		return vRet;
	}
}
