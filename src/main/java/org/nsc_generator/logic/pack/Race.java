/**	NSC_Generator v0.0		Dh	12.03.2021
 * 	
 * 	pLogic.pPack
 * 	  IDElement
 * 	    Race
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

import org.nsc_generator.logic.IDElement;
import org.nsc_generator.logic.NPC;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name = "race")
@XmlSeeAlso({Subrace.class, GenElement.class, ProbList.class, ProbElement.class})

public class Race extends IDElement {
	protected GenElement age, height, weight;
	protected ProbList sexList, haircolorList, eyecolorList, complexionList;
	
	/**	Dh	12.03.2021
	 * 
	 */
	public Race() {
		super();
		
		age = new GenElement();
		height = new GenElement();
		weight = new GenElement();
		
		sexList = new ProbList();
		haircolorList = new ProbList();
		eyecolorList = new ProbList();
		complexionList = new ProbList();
	}
	/**	Dh	12.03.2021
	 * 	
	 * @param pID
	 * @param pName
	 */
	public Race(int pID, String pName) {
		super(pID, pName);
		
		age = new GenElement();
		height = new GenElement();
		weight = new GenElement();
		
		sexList = new ProbList();
		haircolorList = new ProbList();
		eyecolorList = new ProbList();
		complexionList = new ProbList();
	}

//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	24.02.2021
	 * 
	 * @return
	 */
	public GenElement getAge() {
		return age;
	}
	/**	Dh	24.02.2021
	 * 
	 * @return
	 */
	public GenElement getHeight() {
		return height;
	}
	/**	Dh	24.02.2021
	 * 
	 * @return
	 */
	public GenElement getWeight() {
		return weight;
	}
	
	/**	Dh	24.02.2021
	 * 
	 * @return
	 */
	public ProbList getSexList() {
		return sexList;
	}
	/**	Dh	24.02.2021
	 * 
	 * @return
	 */
	public ProbList getHaircolorList() {
		return haircolorList;
	}
	/**	Dh	24.02.2021
	 * 
	 * @return
	 */
	public ProbList getEyecolorList() {
		return eyecolorList;
	}
	/**	Dh	12.02.2021
	 * 
	 * @return
	 */
	public ProbList getComplexionList() {
		return complexionList;
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	24.02.2021
	 * 
	 * 	pAge darf nicht null sein.
	 * 
	 * @param pAge
	 * @throws Exception
	 */
	public void setAge(GenElement pAge) throws Exception{
		if (pAge != null) age = pAge;
		else throw new Exception("04; sAg,Rac");
	}
	/**	Dh	24.02.2021
	 * 
	 * 	pHeight darf nicht null sein.
	 * 
	 * @param pHeight
	 * @throws Exception
	 */
	public void setHeight(GenElement pHeight) throws Exception{
		if (pHeight != null) height = pHeight;
		else throw new Exception("04; sHe,Rac");
	}
	/**	Dh	24.02.2021
	 * 
	 * 	pWeight darf nicht null sein.
	 * 
	 * @param pWeight
	 * @throws Exception
	 */
	public void setWeight(GenElement pWeight) throws Exception{
		if (pWeight != null) weight = pWeight;
		else throw new Exception("04; sWe,Rac");
	}
	
	/**	Dh	24.02.2021
	 * 
	 * 	pSexList darf nicht null sein.
	 * 
	 * @param pSexList
	 * @throws Exception
	 */
	public void setSexList(ProbList pSexList) throws Exception{
		if (pSexList != null) sexList = pSexList;
		else throw new Exception("04; sSeLi,Rac");
	}
	/**	Dh	24.02.2021
	 * 
	 * 	pHaircolorList darf nicht null sein.
	 * 
	 * @param pHaircolorList
	 * @throws Exception
	 */
	public void setHaircolorList(ProbList pHaircolorList) throws Exception{
		if (pHaircolorList != null) haircolorList = pHaircolorList;
		else throw new Exception("04; sHacLi,Rac");
	}
	/**	Dh	24.02.2021
	 * 
	 * 	pEyecolorList darf nicht null sein.
	 * 
	 * @param pEyecolorList
	 * @throws Exception
	 */
	public void setEyecolorList(ProbList pEyecolorList) throws Exception{
		if (pEyecolorList != null) eyecolorList = pEyecolorList;
		else throw new Exception("04; sEycLi,Rac");
	}
	/**	Dh	12.03.2021
	 * 
	 * 	pComplexionList darf nicht null sein.
	 * 
	 * @param pComplexionList
	 * @throws Exception
	 */
	public void setComplexionList(ProbList pComplexionList) throws Exception{
		if (pComplexionList != null) complexionList = pComplexionList;
		else throw new Exception("04; sCoLi,Rac");
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	24.02.2021
	 * 	
	 * 	Generiert ein Alter aus den gesetzten Parametern.
	 * 
	 * @return
	 */
	public int genAge() {
		return age.genValue();
	}
	/**	Dh	24.02.2021
	 * 	
	 * 	Generiert eine Groesse aus den gesetzten Parametern.
	 * 
	 * @return
	 */
	public int genHeight() {
		return height.genValue();
	}
	/**	Dh	12.03.2021
	 * 	
	 * 	Generiert ein Gewicht aus den gesetzten Parametern.
	 * 
	 * @return
	 */
	public int genWeight(int pHeight) {
		return weight.genValue() + pHeight;
	}
	
	/**	Dh	24.02.2021
	 * 
	 * 	Generiert ein Geschelcht aus der Liste, anhand der jeweiligen Wahrscheinlichkeiten.
	 * 
	 * @return
	 * @throws Exception
	 */
	public ProbElement genSex() throws Exception {
		return sexList.genProbElement();
	}
	/**	Dh	24.02.2021
	 * 
	 * 	Generiert eine Haarfarbe aus der Liste, anhand der jeweiligen Wahrscheinlichkeiten.
	 * 
	 * @return
	 * @throws Exception
	 */
	public ProbElement genHaircolor() throws Exception {
		return haircolorList.genProbElement();
	}
	/**	Dh	24.02.2021
	 * 
	 * 	Generiert eine Augenfarbe aus der Liste, anhand der jeweiligen Wahrscheinlichkeiten.
	 * 
	 * @return
	 * @throws Exception
	 */
	public ProbElement genEyecolor() throws Exception {
		return eyecolorList.genProbElement();
	}
	/**	Dh	12.03.2021
	 * 
	 * @return
	 * @throws Exception
	 */
	public ProbElement genComplexion() throws Exception {
		return complexionList.genProbElement();
	}
	
	/**	Dh	12.03.2021
	 * 
	 * 	Generiert die fehlenden ethnischen/rassischen Elemente des NPC und ubergibt diese an den uebergebenen NPC.
	 * 	pNPC darf nicht null sein.
	 * 
	 * @param pNPC
	 * @throws Exception
	 */
	public void genCharacter(NPC pNPC) throws Exception {
		if (pNPC != null) {
			try {
				pNPC.setAge(genAge());
				pNPC.setHeight(genHeight());
				pNPC.setWeight(genWeight(pNPC.getHeight()));
				
				pNPC.setSex(genSex().getName());
				pNPC.setHaircolor(genHaircolor().getName());
				pNPC.setEyecolor(genEyecolor().getName());
				pNPC.setComplexion(genComplexion().getName());
			} catch(Exception ex) {throw ex;}
		} else throw new Exception("04; gCha,Rac");
	}
	
}
