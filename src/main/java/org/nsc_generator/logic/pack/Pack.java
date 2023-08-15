/**	NSC_Generator v0.21		Dh	15.08.2023
 * 	
 * 	logic.pack
 * 	  IDElement
 * 	    Pack
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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "pack")
@XmlType(propOrder = {"cultures", "races", "quirkList"})
@XmlSeeAlso({Culture.class, Subculture.class, Race.class, Subrace.class, ProbList.class, ProbElement.class, 
			PrioList.class, PrioElement.class})
@JsonRootName(value = "pack")
@JsonPropertyOrder({
	"id", "name", "cultures", "races", "quirkList"
})
public class Pack extends IDElement {
	@JsonProperty("cultures")
	private ArrayList<Culture> cultures;
	@JsonProperty("races")
	private ArrayList<Race>	races;
	@JsonProperty("quirkList")
	private PrioList quirkList;
	
	/**	Dh	08.08.2023
	 * 
	 */
	public Pack() {
		super();
		
		cultures = new ArrayList<Culture>();
		races = new ArrayList<Race>();
		
		quirkList = new PrioList();
	}
	/**	Dh	08.08.2023
	 * 
	 * @param pID
	 * @param pName
	 */
	public Pack(int pID, String pName) {
		super(pID, pName);
		
		cultures = new ArrayList<Culture>();
		races = new ArrayList<Race>();
		
		quirkList = new PrioList();
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	08.08.2023
	 * 
	 * 	Sucht die Kultur mit der pCultureID aus der cultureList.
	 * 		pCultureID muss groessergleich 0 sein.
	 * 
	 * @param pCultureID
	 * @return
	 * @throws Exception
	 */
	public Culture getCulture(int pCultureID) throws Exception {
		Culture vRet = null;
		
		if (pCultureID >= 0) {
			for (int i=0; (i<cultures.size()) && (vRet == null); i++) {
				if (cultures.get(i).getId() == pCultureID) vRet = cultures.get(i);
			}
		}else throw new Exception("02; gCu,Pac");
		
		return vRet;
	}
	/**	Dh	08.08.2023
	 * 
	 * 	Sucht die Rasse/Ethnie mit der pRaceID aus der raceList.
	 * 		pRaceID muss groessergleich 0 sein.
	 * 
	 * @param pRaceID
	 * @return
	 * @throws Exception
	 */
	public Race getRace(int pRaceID) throws Exception {
		Race vRet = null;
		
		if (pRaceID >= 0) {
			for (int i=0; (i<races.size()) && (vRet == null); i++) {
				if (races.get(i).getId() == pRaceID) vRet = races.get(i);
			}
		}else throw new Exception("02; gRa,Pac");
		
		return vRet;
	}
	
	/**	Dh	08.08.2023
	 * 
	 * @return
	 */
	public ArrayList<Culture> getCultures(){
		return cultures;
	}
	/**	Dh	08.08.2023
	 * 
	 * @return
	 */
	public ArrayList<Race> getRaces(){
		return races;
	}
	
	/**	Dh	14.03.2021
	 * 
	 * @return
	 */
	public PrioList getQuirkList() {
		return quirkList;
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	08.08.2023
	 * 
	 * 	Ersetzt ein vorhandene Kultur aus der culuterList, und schreibt bei allen evt.
	 * 		Subculters die parentCulters auf das neue um.
	 * 
	 * @param pCulture
	 * @throws Exception
	 */
	public void setCulture(Culture pCulture) throws Exception {
		Culture vCur = null;
		
		if (pCulture != null) {
			for (int i=0; i<cultures.size(); i++) {
				vCur = cultures.get(i);
				
				if (vCur.getId() == pCulture.getId()) cultures.set(i, pCulture);
				else if ((vCur instanceof Subculture) && ((Subculture)vCur).getParentID() == pCulture.getId())
					((Subculture)vCur).setParentCulture(pCulture);
			}
		}else throw new Exception("04; sCu,Pac");
	}
	/**	Dh	08.08.2023
	 * 
	 * 	Ersetzt ein vorhandene Rasse/ethnie aus der raceList, und schreibt bei allen evt.
	 * 		Subraces die parentRaces auf das neue um.
	 * 
	 * @param pRace
	 * @throws Exception
	 */
	public void setRace(Race pRace) throws Exception {
		Race vCur = null;
		
		if (pRace!= null) {
			for (int i=0; i<races.size(); i++) {
				vCur = races.get(i);
				
				if (vCur.getId() == pRace.getId()) races.set(i, pRace);
				else if ((vCur instanceof Subrace) && ((Subrace)vCur).getParentID() == pRace.getId())
					((Subrace)vCur).setParentRace(pRace);
			}
		}else throw new Exception("04; sRa,Pac");
	}
		
	/**	Dh	12.08.2023
	 * 
	 * 	Setzt die CultureList und prueft, ob fuer alle in ihr enthaltenen CultureIDs eine ID existiert.
	 * 		pCultureList darf nicht null sein und nur Objekte von Type Culture enthalten.
	 * 
	 * @param pCultures
	 * @throws Exception
	 */
	public void setCultures(ArrayList<Culture> pCultures) throws Exception{
		int vCurID;
		ArrayList<Integer> vIDList = new ArrayList<Integer>();
		
		if (pCultures != null) {
			for (Culture vCurCulture : pCultures) {
				vCurID = vCurCulture.getId();
				
				if (vIDList.contains(Integer.valueOf(vCurID))) throw new Exception("02a; sCu,Pac");
				vIDList.add(Integer.valueOf(vCurID));
			}
			
			if (!checkIfAssociatedCultureIDsExists(pCultures, vIDList)) throw new Exception("02b; sCu,Pac");
			else cultures = pCultures;
		}else throw new Exception("04; sCu,Pac");
	}
	/**	Dh	12.08.2023
	 * 
	 * 	Setzt die RaceList und prueft, ob fuer alle in ihr enthaltenen RaceIDs eine ID existiert.
	 * 		pRaceList darf nicht null sein und nur Objekte von Type Race enthalten.
	 * 
	 * @param pRaces
	 * @throws Exception
	 */
	public void setRaces(ArrayList<Race> pRaces) throws Exception{
		int vCurID;
		ArrayList<Integer> vIDList = new ArrayList<Integer>();
		
		if (pRaces != null) {
			for (Race vCurRace : pRaces) {
				vCurID = vCurRace.getId();
				
				if (vIDList.contains( Integer.valueOf(vCurID) )) throw new Exception("02a; sRa,Pac");
				vIDList.add(Integer.valueOf(vCurID));
			}
			
			if (!checkIfAssociatedRaceIDsExists(pRaces, vIDList)) throw new Exception("02b; sRa,Pac");
			else races = pRaces;
		}
	}
	
	/**	Dh	14.03.2021
	 * 
	 * 	pQuirkList darf nicht null sein.
	 * 
	 * @param pQuirkList
	 * @throws Exception
	 */
	public void setQuirkList(PrioList pQuirkList) throws Exception{
		if (pQuirkList != null) quirkList = pQuirkList;
		else throw new Exception("04; sQLi,Pac");
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	08.08.2023
	 * 
	 * 	Fuegt pCulture in die cultureList hinzu.
	 * 		pCulture darf nicht null sein und ihre ID darf noch nicht in der Liste vorhanden sein.
	 * 
	 * @param pCulture
	 * @throws Exception
	 */
	public void addCulture(Culture pCulture) throws Exception{		
		if (pCulture != null) {
			if (isIDInIDElementList(pCulture.getId(), cultures) == false) {
				cultures.add(pCulture);
				
				cultures.sort((pCul1, pCul2) -> {
					return (pCul1.getId() - pCul2.getId());
				});
			}else throw new Exception("02; aCu,Pac");
		} else throw new Exception("04; aCu,Pac");
	}
	/**	Dh	08.08.2023
	 * 
	 * 	Fuegt pRace in die raceList hinzu.
	 * 		pRace darf nicht null sein und ihre ID darf noch nicht in der Liste vorhanden sein.
	 * 
	 * @param pRace
	 * @throws Exception
	 */
	public void addRace(Race pRace) throws Exception{
		if (pRace != null) {
			if (isIDInIDElementList(pRace.getId(), races) == false) {
				races.add(pRace);
				
				races.sort((pRace1, pRace2) -> {
					return (pRace1.getId() - pRace2.getId());
				});
			} else throw new Exception("02; aRa,Pac");
		} else throw new Exception("04; aRa,Pac");
	}
	
	/**	Dh	08.08.2023
	 * 
	 * 	Entfernt die der pCultureID entsprechenden Kultur aus der cultureList, und preuft, ob diese ID in
	 * 		anderen originCultureLists vorkommt, falls ja werden diese entfernt und die wahrscheinlichkeiten auf eine
	 * 		andere Kultur uebertragen, und ob diese Kultur eine parentCulture von einer anderen Subcultre ist, falls ja,
	 * 		wird diese Subculture zu einer Culture transformiert.
	 * 
	 * 	pCultureID muss groessergleich 0 sein.
	 * 
	 * @param pCultureID
	 * @throws Exception
	 */
	public void removeCulture(int pCultureID) throws Exception{
		Culture vCurCult;
		ArrayList<ProbElement> vProbElementList;
		ProbElement vCurProbEle, vTempProbEle;
		
		if (pCultureID >= 0) {
			for (int i=0; i<cultures.size(); i++) {
				if (cultures.get(i).getId() == pCultureID) {
					cultures.remove(i);
					i--;
				}else {
					vCurCult = cultures.get(i);
					
					vProbElementList = vCurCult.getOriginCultureList().getProbElements();
					vCurProbEle = vCurCult.getOriginCultureList().getProbElement(pCultureID);
					
					if (vCurProbEle != null) {
						vProbElementList.remove(vCurProbEle);
						vTempProbEle = vProbElementList.get( vProbElementList.size()-1 );
						
						if (vTempProbEle != null) vTempProbEle.setProbability(vTempProbEle.getProbability() + vCurProbEle.getProbability());
					}
					
					if ((vCurCult instanceof Subculture) && ( ((Subculture)vCurCult).getParentID() == pCultureID )) {
						vCurCult = transformSubcultureToCulture((Subculture)vCurCult);
						
						cultures.set(i, vCurCult);
					}
				}
			}
		}else throw new Exception("02; rCul,Pac");
	}
	/**	Dh	08.08.2023
	 * 
	 * 	Entfernt die der pRaceID entsprechenden Rasse aus der raceList, und preuft, ob diese ID in
	 * 		anderen raceLists vorkommt, falls ja werden diese entfernt und die wahrscheinlichkeiten auf eine
	 * 		andere Rasse uebertragen, und ob diese Rasse eine parentRace von einer anderen Subrace ist, falls ja,
	 * 		wird diese Subrace zu einer Race transformiert.
	 * 
	 * 	pRaceID muss groessergleich 0 sein.
	 * 
	 * @param pRaceID
	 * @throws Exception
	 */
	public void removeRace(int pRaceID) throws Exception{
		Race vCurRace;
		ArrayList<ProbElement> vProbElementList;
		ProbElement vCurProbEle, vTempProbEle;
		
		if (pRaceID >= 0) {
			for (Culture vCulture : cultures) {
				vProbElementList = vCulture.getRaceList().getProbElements();
				vCurProbEle = vCulture.getRaceList().getProbElement(pRaceID);
				
				if (vCurProbEle != null) {
					vProbElementList.remove(vCurProbEle);
					vTempProbEle = vProbElementList.get( vProbElementList.size()-1 );
					
					if (vTempProbEle != null) vTempProbEle.setProbability(vTempProbEle.getProbability() + vCurProbEle.getProbability());
				}
			}
			
			for (int i=0; i<races.size(); i++) {
				if (races.get(i).getId() == pRaceID) {
					races.remove(i);
					i--;
				}else {
					vCurRace = races.get(i);
					
					if ((vCurRace instanceof Subrace) && (((Subrace)vCurRace).getParentID() == pRaceID)) {
						vCurRace = transformSubraceToRace((Subrace) vCurRace);
						
						races.set(i, vCurRace);
					}
				}
			}
		}else throw new Exception("02; rRac,Pac");
	}
	
	/**	Dh	08.08.2023
	 * 
	 * 	Wandelt das zur pSubcultureID gehoerenden Subculture Objekt in ein Culture Objekt um.
	 * 		Die pSubcultureID muss groessergleich 0 sein und zu einem Subculture Objekt gehoehren.
	 * 
	 * @param pSubculutreID
	 * @throws Exception
	 */
	public void transformSubcultureToCulture(int pSubculutreID) throws Exception{
		Culture vCur;
		
		if (pSubculutreID >= 0) {
			for (int i=0; i<cultures.size(); i++) {
				vCur = cultures.get(i);
				
				if (vCur.getId() == pSubculutreID) {
					if (vCur instanceof Subculture) {
						vCur = transformSubcultureToCulture((Subculture) vCur);
						
						cultures.set(i, vCur);
						i = cultures.size();
					} else throw new Exception("06; tSctC,Pac");
				}
			}
		}else throw new Exception("02; tSctC,Pac");
	}
	/**	Dh	08.08.2023
	 * 
	 * 	Wandelt das zur pSubraceID gehoerenden Subrace Objekt in ein Race Objekt um.
	 * 		Die pSubraceID muss groessergleich 0 sein und zu einem Subrace Objekt gehoehren.
	 * 
	 * @param pSubraceID
	 * @throws Exception
	 */
	public void transformSubraceToRace(int pSubraceID) throws Exception{
		Race vCur;
		
		if (pSubraceID >= 0) {
			for (int i=0; i<races.size(); i++) {
				vCur = races.get(i);
				
				if (vCur.getId() == pSubraceID) {
					if (vCur instanceof Subrace) {
						vCur = transformSubraceToRace((Subrace) vCur);
						
						races.set(i, vCur);
						i = races.size();
					} else throw new Exception("06; tSrtR,Pac");
				}
			}
		}else throw new Exception("02; tSrtR,Pac");
	}
	//----
	/**	Dh	08.08.2023
	 * 
	 * 	Wandelt das zur pCultureID gehoerenden Culture Objekt in ein Subculture Objekt um.
	 * 		Die pCultureID muss groessergleich 0 sein und zu einem Culture Objekt gehoehren.
	 * 		Die pParentCultureID muss groessergleich 0 sein und darf nicht gleich der pCultureID sein.
	 * 
	 * @param pCultureID
	 * @param pParentCultureID
	 * @throws Exception
	 */
	public void transformCultureToSubculture(int pCultureID, int pParentCultureID) throws Exception{
		Culture vCur, vParCult;
		
		vParCult = null;
		
		if ((pParentCultureID >= 0) && (pCultureID >= 0) && (pCultureID != pParentCultureID)) {
			for (int i=0; (i<cultures.size()) && (vParCult == null); i++) {
				if (cultures.get(i).getId() == pParentCultureID) vParCult = cultures.get(i);
			}
			
			if (vParCult != null) {
				for (int i=0; i<cultures.size(); i++) {
					vCur = cultures.get(i);
					
					if (vCur.getId() == pCultureID) {
						if (!(vCur instanceof Subculture)) {
							vCur = transformCultureToSubculture(vCur, vParCult);
							
							cultures.set(i, vCur);
							i = cultures.size();
						} else throw new Exception("06; tCtSc,Pac");
					}
				}
			} else throw new Exception("07; tCtSc,Pac");
		}else throw new Exception("02; tCtSc,Pac");
	}
	/**	Dh	08.08.2023
	 * 
	 * 	Wandelt das zur pRaceID gehoerenden Race Objekt in ein Subrace Objekt um.
	 * 		Die pRaceID muss groessergleich 0 sein und zu einem Race Objekt gehoehren.
	 * 		Die pParentRaceID muss groessergleich 0 sein und darf nicht gleich der pRaceID sein.
	 * 
	 * @param pRaceID
	 * @param pParentRaceID
	 * @throws Exception
	 */
	public void transformRaceToSubrace(int pRaceID, int pParentRaceID) throws Exception{
		Race vCur, vParRace;
		
		vParRace = null;
		
		if ((pParentRaceID >= 0) && (pRaceID >= 0) && (pRaceID != pParentRaceID)) {
			for (int i=0; (i<races.size()) && (vParRace == null); i++) {
				if (races.get(i).getId() == pParentRaceID) vParRace = races.get(i);
			}
			
			if (vParRace != null) {
				for (int i=0; i<races.size(); i++) {
					vCur = races.get(i);
					
					if (vCur.getId() == pRaceID) {
						if (!(vCur instanceof Subrace)) {
							vCur = transformRaceToSubrace(vCur, vParRace);
							
							races.set(i, vCur);
							i = races.size();
						} else throw new Exception("06; tRtSr,Pac");
					}
				}
			} else throw new Exception("07; tCtSc,Pac");
		}else throw new Exception("02; tRtSr,Pac");
	}
	
	//----------------------------------------------------------------------------------------------------

	/**	Dh	08.08.2023
	 * 	
	 * 	Erschafft aus einem Subculture Objekt ein vollwertiges Culture Objekt, anhand der parentCulture.
	 * 		Die fehlenden Variablen werden von der parentCulture uebernommen.
	 * 
	 * @param pSubculture
	 * @return
	 * @throws Exception
	 */
	public Culture transformSubcultureToCulture(Subculture pSubculture) throws Exception {
		Culture vRet = new Culture();
		
		if (pSubculture != null) {
			try {
				vRet.setId(pSubculture.getId());
				vRet.setName(pSubculture.getName());
				
				if (pSubculture.getOriginCultureList().getProbElements().size() != 0)
					vRet.setOriginCultureList(pSubculture.getOriginCultureList());
				else vRet.setOriginCultureList(pSubculture.getParentCulture().getOriginCultureList());
				if (pSubculture.getRaceList().getProbElements().size() != 0)
					vRet.setRaceList(pSubculture.getRaceList());
				else vRet.setRaceList(pSubculture.getParentCulture().getRaceList());
				if (pSubculture.getSexualityList().getProbElements().size() != 0)
					vRet.setSexualityList(pSubculture.getSexualityList());
				else vRet.setSexualityList(pSubculture.getParentCulture().getSexualityList());
				if (pSubculture.getHairlengthList().getProbElements().size() != 0)
					vRet.setHairlengthList(pSubculture.getHairlengthList());
				else vRet.setHairlengthList(pSubculture.getParentCulture().getHairlengthList());
				if (pSubculture.getSoList().getProbElements().size() != 0)
					vRet.setSoList(pSubculture.getSoList());
				else vRet.setSoList(pSubculture.getParentCulture().getSoList());
				
			}catch(Exception ex) {throw ex;}
		}else throw new Exception("04; tScTC,Pac");
		
		return vRet;
	}
	/**	Dh	08.08.2023
	 * 	
	 * 	Erschafft aus einem Subrace Objekt ein vollwertiges Race Objekt, anhand der parentRace.
	 * 		Die fehlenden Variablen werden von der parentRace uebernommen.
	 * 
	 * @param pSubrace
	 * @return
	 * @throws Exception
	 */
	public Race transformSubraceToRace(Subrace pSubrace) throws Exception {
		Race vRet = new Race();
		
		if (pSubrace != null) {
			try {
				vRet.setId(pSubrace.getId());
				vRet.setName(pSubrace.getName());
				
				if ((pSubrace.getAge().getNumber() != 0)  || (pSubrace.getAge().getSide() != 0) || (pSubrace.getAge().getOffset() != 0))
					vRet.setAge(pSubrace.getAge());
				else vRet.setAge(pSubrace.getParentRace().getAge());
				if ((pSubrace.getHeight().getNumber() != 0)  || (pSubrace.getHeight().getSide() != 0) || (pSubrace.getHeight().getOffset() != 0))
					vRet.setHeight(pSubrace.getHeight());
				else vRet.setHeight(pSubrace.getParentRace().getHeight());
				if ((pSubrace.getWeight().getNumber() != 0)  || (pSubrace.getWeight().getSide() != 0) || (pSubrace.getWeight().getOffset() != 0))
					vRet.setWeight(pSubrace.getWeight());
				else vRet.setWeight(pSubrace.getParentRace().getWeight());
				
				if (pSubrace.getSexList().getProbElements().size() != 0)
					vRet.setSexList(pSubrace.getSexList());
				else vRet.setSexList(pSubrace.getParentRace().getSexList());
				if (pSubrace.getComplexionList().getProbElements().size() != 0)
					vRet.setComplexionList(pSubrace.getComplexionList());
				else vRet.setSexList(pSubrace.getParentRace().getSexList());
				if (pSubrace.getHaircolorList().getProbElements().size() != 0)
					vRet.setHaircolorList(pSubrace.getHaircolorList());
				else vRet.setHaircolorList(pSubrace.getParentRace().getHaircolorList());
				if (pSubrace.getEyecolorList().getProbElements().size() != 0)
					vRet.setEyecolorList(pSubrace.getEyecolorList());
				else vRet.setEyecolorList(pSubrace.getParentRace().getEyecolorList());
			}catch(Exception ex) {throw ex;}
		}else throw new Exception("04; tSrTR,Pac");
		
		return vRet;
	}
	//----
	/**	Dh	25.02.2021
	 * 	
	 * 	Erschafft aus einem Culture Objekt ein Subculture Objekt, anhand der parentCulture.
	 * 
	 * @param pCulture
	 * @param pParentCulture
	 * @return
	 * @throws Exception
	 */
	public Subculture transformCultureToSubculture(Culture pCulture, Culture pParentCulture) throws Exception{
		Subculture vRet = new Subculture();
		
		if (pCulture != null) {
			try {
				vRet.setId(pCulture.getId());
				vRet.setName(pCulture.getName());
				
				vRet.setOriginCultureList(pCulture.getOriginCultureList());
				vRet.setRaceList(pCulture.getRaceList());
				vRet.setSexualityList(pCulture.getSexualityList());
				vRet.setHairlengthList(pCulture.getHairlengthList());
				vRet.setSoList(pCulture.getSoList());
				
				vRet.setParentCulture(pParentCulture);
				
			}catch(Exception ex) {throw ex;}
		}else throw new Exception("04; tCtSc,Pac");
		
		return vRet;
	}
	/**	Dh	12.03.2021
	 * 	
	 * 	Erschafft aus einem RAce Objekt ein Subrace Objekt, anhand der parentRace.
	 * 
	 * @param pRace
	 * @param pParentRace
	 * @return
	 * @throws Exception
	 */
	public Subrace transformRaceToSubrace(Race pRace, Race pParentRace) throws Exception{
		Subrace vRet = new Subrace();
		
		if (pRace != null) {
			try {
				vRet.setId(pRace.getId());
				vRet.setName(pRace.getName());
				
				vRet.setAge(pRace.getAge());
				vRet.setHeight(pRace.getHeight());
				vRet.setWeight(pRace.getWeight());
				
				vRet.setSexList(pRace.getSexList());
				vRet.setComplexionList(pRace.getComplexionList());
				vRet.setHaircolorList(pRace.getHaircolorList());
				vRet.setEyecolorList(pRace.getEyecolorList());
				
				vRet.setParentRace(pParentRace);
				
			}catch(Exception ex) {throw ex;}
		}else throw new Exception("04; tRtSr,Pac");
		
		return vRet;
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	14.03.2021
	 * 
	 * 	Generiert Eigenschaft aus der Liste, anhand der jeweiligen Prioritaeten.
	 * 
	 * @return
	 * @throws Exception
	 */
	public PrioElement genQuirk() throws Exception {
		return quirkList.genPrioElement();
	}
	
	/**	Dh	14.03.2021
	 * 
	 * @param pCurrentRegionCultureID
	 * @param pNPC
	 * @throws Exception
	 */
	public void genCharacter(int pCurrentRegionCultureID, NPC pNPC) throws Exception {
		ProbElement vCurProbEle;
		Culture vCurRegCulture, vOrigCulture;
		
		if (pNPC != null) {
			
			vCurRegCulture = getCulture(pCurrentRegionCultureID);
			
			if (vCurRegCulture != null) {
				
				vCurProbEle = vCurRegCulture.genOriginCulture();
				
				vOrigCulture = getCulture(vCurProbEle.getId());
				pNPC.setCulture(vCurProbEle.getName());
				pNPC.setCultureID(vCurProbEle.getId());
				
				vOrigCulture.genCharacter(pNPC);
				
				getRace(pNPC.getRaceID()).genCharacter(pNPC);
			}else throw new Exception("02; gC,Pac");
			
			pNPC.setQuirk(genQuirk().getName());
			
		} else throw new Exception("04, gC,Pac");
		
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	12.08.2023
	 * 
	 * @param pProbElement
	 * @param pIDList
	 * @return
	 */
	private boolean checkIfIDElementIsInIDList(IDElement pIDElement, ArrayList<Integer> pIDList) {		
		return pIDList.contains( Integer.valueOf( pIDElement.getId() ));
	}
	
	/**	Dh	12.08.2023
	 * 
	 * @param pProbElementList
	 * @param pIDList
	 * @return
	 */
	private boolean checkIfProbListIDsAreInIDList(ArrayList<ProbElement> pProbElementList, ArrayList<Integer> pIDList) {
		boolean vRet = true;
		
		for (int i=0; (i<pProbElementList.size()) && (vRet == true); i++) {
			if ( !pIDList.contains( Integer.valueOf( pProbElementList.get(i).getId() ) ) )  vRet = false;
		}
		
		return vRet;
	}
	
	/**	Dh	12.08.2023
	 * 
	 * @param pCultureList
	 * @param pIDList
	 * @return
	 * @throws Exception
	 */
	private boolean checkIfAssociatedCultureIDsExists(ArrayList<Culture> pCultureList, ArrayList<Integer> pIDList) throws Exception{
		boolean vRet = true;
		Culture vCurCult;
		
		for (int i=0; (i<pCultureList.size()) && (vRet == true); i++) {
			vCurCult = pCultureList.get(i);
			
			vRet = checkIfProbListIDsAreInIDList(vCurCult.getOriginCultureList().getProbElements(), pIDList);
			if ((vRet == true) && (vCurCult instanceof Subculture)) {
				vRet = checkIfIDElementIsInIDList(((Subculture)vCurCult).getParentCulture(), pIDList);
			}
		}
		
		return vRet;
	}
	/**	Dh	12.08.2023
	 * 
	 * @param pRaceList
	 * @param pIDList
	 * @return
	 * @throws Exception
	 */
	private boolean checkIfAssociatedRaceIDsExists(ArrayList<Race> pRaceList, ArrayList<Integer> pIDList) throws Exception{
		boolean vRet = true;
		Race vCurRace;
		
		for (int i=0; (i<pRaceList.size()) && (vRet == true); i++) {
			vCurRace = pRaceList.get(i);
			
			if (vCurRace instanceof Subrace) {
				vRet = checkIfIDElementIsInIDList(((Subrace)vCurRace).getParentRace(), pIDList);
			}
		}
		
		return vRet;
	}
		
}
