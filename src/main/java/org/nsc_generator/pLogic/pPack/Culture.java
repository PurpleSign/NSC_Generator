/**	NSC_Generator v0.0		Dh	12.03.2021
 * 	
 * 	pLogic.pPack
 * 	  IDElement
 * 	    Culture
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

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.nsc_generator.pLogic.IDElement;
import org.nsc_generator.pLogic.MainManager;
import org.nsc_generator.pLogic.NPC;

@XmlRootElement(name = "culture")
@XmlSeeAlso(Subculture.class)

public class Culture extends IDElement{
	protected ProbList originCultureList, raceList, sexualityList, hairlengthList, soList;
	
	/**	Dh	24.02.2021
	 * 
	 */
	public Culture() {
		super();
		
		originCultureList = new ProbList();
		raceList = new ProbList();
		sexualityList = new ProbList();
		hairlengthList = new ProbList();
		soList = new ProbList();
	}
	/**	Dh	24.02.2021
	 * 
	 * @param pID
	 * @param pName
	 */
	public Culture(int pID, String pName) {
		super(pID, pName);
		
		originCultureList = new ProbList();
		raceList = new ProbList();
		sexualityList = new ProbList();
		hairlengthList = new ProbList();
		soList = new ProbList();
	}
	/**	Dh	25.02.2021
	 * 
	 * @param pID
	 * @param pName
	 * @param pOriginCultureList
	 * @param pRaceList
	 * @param pSexualityList
	 * @param pHairlengthList
	 * @param pSOList
	 */
	public Culture(int pID, String pName, ProbList pOriginCultureList, ProbList pRaceList, 
			ProbList pSexualityList, ProbList pHairlengthList, ProbList pSOList) {
		super(pID, pName);
		try {
			setOriginCultureList(pOriginCultureList);
			setRaceList(pRaceList);
			setSexualityList(pSexualityList);
			setHairlengthList(pHairlengthList);
			setSoList(pSOList);
		} catch(Exception ex) {MainManager.handleException(ex);}
	}

//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	24.02.2021
	 * 
	 * @return
	 */
	public ProbList getOriginCultureList() {
		return originCultureList;
	}
	/**	Dh	24.02.2021
	 * 
	 * @return
	 */
	public ProbList getRaceList() {
		return raceList;
	}
	/**	Dh	24.02.2021
	 * 
	 * @return
	 */
	public ProbList getSexualityList() {
		return sexualityList;
	}
	/**	Dh	24.02.2021
	 * 
	 * @return
	 */
	public ProbList getHairlengthList() {
		return hairlengthList;
	}
	/**	Dh	24.02.2021
	 * 
	 * @return
	 */
	public ProbList getSoList() {
		return soList;
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	24.02.2021
	 * 
	 * 	pOriginCultureList darf nicht null sein.
	 * 
	 * @param pOriginCultureList
	 * @throws Exception
	 */
	public void setOriginCultureList(ProbList pOriginCultureList) throws Exception{
		if (pOriginCultureList != null) originCultureList = pOriginCultureList;
		else throw new Exception("04; sOCLi,Cul");
	}
	/**	Dh	24.02.2021
	 * 
	 * 	pRaceList darf nicht null sein.
	 * 
	 * @param pRaceList
	 * @throws Exception
	 */
	public void setRaceList(ProbList pRaceList) throws Exception{
		if (pRaceList != null) raceList = pRaceList;
		else throw new Exception("04; sRaLi,Cul");
	}
	/**	Dh	24.02.2021
	 * 
	 * 	pSexualityList darf nicht null sein.
	 * 
	 * @param pSexualityList
	 * @throws Exception
	 */
	public void setSexualityList(ProbList pSexualityList) throws Exception{
		if (pSexualityList != null) sexualityList = pSexualityList;
		else throw new Exception("04; sSeLi,Cul");
	}
	/**	Dh	24.02.2021
	 * 
	 * 	pHairlengthList darf nicht null sein.
	 * 
	 * @param pHairlengthList
	 * @throws Exception
	 */
	public void setHairlengthList(ProbList pHairlengthList) throws Exception{
		if (pHairlengthList != null) hairlengthList = pHairlengthList;
		else throw new Exception("04; sHalLi,Cul");
	}
	/**	Dh	24.02.2021
	 * 
	 * 	pSOList darf nicht null sein.
	 * 
	 * @param pSOList
	 * @throws Exception
	 */
	public void setSoList(ProbList pSOList) throws Exception{
		if (pSOList != null) soList = pSOList;
		else throw new Exception("04; sSoLi,Cul");
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	24.02.2021
	 * 
	 * 	Generiert eine Herkunftskultur aus der Liste, anhand der jeweiligen Wahrscheinlichkeiten.
	 * 
	 * @return
	 * @throws Exception
	 */
	public ProbElement genOriginCulture() throws Exception {
		return originCultureList.genProbElement();
	}
	/**	Dh	24.02.2021
	 * 
	 * 	Generiert eine Rasse/Ethnie aus der Liste, anhand der jeweiligen Wahrscheinlichkeiten.
	 * 
	 * @return
	 * @throws Exception
	 */
	public ProbElement genRace() throws Exception {
		return raceList.genProbElement();
	}
	/**	Dh	24.02.2021
	 * 
	 * 	Generiert eine Sexualität aus der Liste, anhand der jeweiligen Wahrscheinlichkeiten.
	 * 
	 * @return
	 * @throws Exception
	 */
	public ProbElement genSexuality() throws Exception {
		return sexualityList.genProbElement();
	}
	/**	Dh	24.02.2021
	 * 
	 * 	Generiert eine Haarlaenge aus der Liste, anhand der jeweiligen Wahrscheinlichkeiten.
	 * 
	 * @return
	 * @throws Exception
	 */
	public ProbElement genHairlength() throws Exception {
		return hairlengthList.genProbElement();
	}
	/**	Dh	24.02.2021
	 * 
	 * 	Generiert einen SozialStatus (So) aus der Liste, anhand der jeweiligen Wahrscheinlichkeiten.
	 * 
	 * @return
	 * @throws Exception
	 */
	public ProbElement genSo() throws Exception {
		return soList.genProbElement();
	}
	
	/**	Dh	12.03.2021
	 * 
	 * 	Generiert die fehlenden kulturellen Elemente des NPC und ubergibt diese an den uebergebenen NPC.
	 * 	pNPC darf nicht null sein.
	 * 
	 * @param pNPC
	 * @throws Exception
	 */
	public void genCharacter(NPC pNPC) throws Exception {
		ProbElement vCur;
		
		if (pNPC != null) {
			try {
				vCur = genRace();
					
				pNPC.setRaceID(vCur.getId());
				pNPC.setRace(vCur.getName());
				
				pNPC.setSexuality(genSexuality().getName());
				pNPC.setHairlength(genHairlength().getName());
				pNPC.setSo(genSo().getName() );
			} catch(Exception ex) {throw ex;}
		} else throw new Exception("04; gCha,Cul");
	}
	
}
