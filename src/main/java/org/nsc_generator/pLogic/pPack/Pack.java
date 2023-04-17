/**	NSC_Generator v0.0		Dh	23.08.2022
 * 	
 * 	pLogic.pPack
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

package org.nsc_generator.pLogic.pPack;

import pDataStructures.List;
import org.nsc_generator.pLogic.IDElement;
import org.nsc_generator.pLogic.MainManager;
import org.nsc_generator.pLogic.NPC;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "pack")
@XmlType(propOrder = {"cultureList", "raceList", "quirkList"})
@XmlSeeAlso({Culture.class, Subculture.class, Race.class, Subrace.class, ProbList.class, ProbElement.class, 
			PrioList.class, PrioElement.class})

public class Pack extends IDElement {
	private List cultureList, raceList; 
	private PrioList quirkList;
	
	/**	Dh	23.08.2022
	 * 
	 */
	public Pack() {
		super();
		
		cultureList = new List();
		raceList = new List();
		
		quirkList = new PrioList();
	}
	/**	Dh	23.08.2022
	 * 
	 * @param pID
	 * @param pName
	 */
	public Pack(int pID, String pName) {
		super(pID, pName);
		
		cultureList = new List();
		raceList = new List();
		
		quirkList = new PrioList();
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	24.02.2021
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
			cultureList.toFirst();
			while((!cultureList.isEnd()) && (vRet == null)) {
				vRet = (Culture)cultureList.getCurrent();
				
				if (vRet.getId() != pCultureID) vRet = null;
				
				cultureList.next();
			}
		}else throw new Exception("02; gCu,Pac");
		
		return vRet;
	}
	/**	Dh	24.02.2021
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
			raceList.toFirst();
			while((!raceList.isEnd()) && (vRet == null)) {
				vRet = (Race)raceList.getCurrent();
				
				if (vRet.getId() != pRaceID) vRet = null;
				
				raceList.next();
			}
		}else throw new Exception("02; gRa,Pac");
		
		return vRet;
	}

	/**	Dh	24.02.2021
	 * 
	 * @return
	 */
	public List getCultureList() {
		return cultureList;
	}
	/**	Dh	24.02.2021
	 * 
	 * @return
	 */
	public List getRaceList() {
		return raceList;
	}
	
	/**	Dh	14.03.2021
	 * 
	 * @return
	 */
	public PrioList getQuirkList() {
		return quirkList;
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	24.02.2021
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
			cultureList.toFirst();
			while((!cultureList.isEnd())) {
				vCur = (Culture)cultureList.getCurrent();
				
				if (vCur.getId() == pCulture.getId()) {
					if (!cultureList.isLast()) {
						cultureList.remove();
						cultureList.insert(pCulture);
					} else {
						cultureList.remove();
						cultureList.append(pCulture);
					}
				}else {
					if ((vCur instanceof Subculture) && ( ((Subculture)vCur).getId() == pCulture.getId() )) 
						((Subculture)vCur).setParentCulture(pCulture);
						
					vCur = null;
				}
				
				cultureList.next();
			}
		}else throw new Exception("04; sCu,Pac");
	}
	/**	Dh	24.02.2021
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
			raceList.toFirst();
			while((!raceList.isEnd())) {
				vCur = (Race)raceList.getCurrent();
				
				if (vCur.getId() == pRace.getId()) {
					if (!raceList.isLast()) {
						raceList.remove();
						raceList.insert(pRace);
					} else {
						raceList.remove();
						raceList.append(pRace);
					}
				}else {
					if ((vCur instanceof Subrace) && ( ((Subrace)vCur).getId() == pRace.getId() )) 
						((Subrace)vCur).setParentRace(pRace);
						
					vCur = null;
				}
				
				raceList.next();
			}
		}else throw new Exception("04; sRa,Pac");
	}
	
	/**	Dh	25.02.2021
	 * 
	 * 	Setzt die CultureList und prueft, ob fuer alle in ihr enthaltenen CultureIDs eine ID existiert.
	 * 		pCultureList darf nicht null sein und nur Objekte von Type Culture enthalten.
	 * 
	 * @param pCultureList
	 * @throws Exception
	 */
	public void setCultureList(List pCultureList) throws Exception {
		int vCurID;
		Object vTemp = null;
		List vIDList = new List();
		
		if (pCultureList != null) {
			pCultureList.toFirst();
			while(!pCultureList.isEnd()) {					// Erstellt eine ID List und checkt, ob diese bereits vorhanden ist.
				vTemp = pCultureList.getCurrent();
				
				if (vTemp instanceof Culture) {
					vCurID = ((Culture) vTemp).getId();
					
					vIDList.toFirst();
					while(!vIDList.isEnd()) {
						if (((int)vIDList.getCurrent()) == vCurID) throw new Exception("02a; sCuLi,Pac");
						
						vIDList.next();
					}
					
					vIDList.append(vCurID);
				} else throw new Exception("06; sCuLi,Pac");
				
				pCultureList.next();
			}
			
			if (checkIfAssociatedCultureIDsExists(pCultureList, vIDList) == false) throw new Exception("02b; sCuLi,Pac");
			
			cultureList = pCultureList;
		}else throw new Exception("04; sCuLi,Pac");
	}
	/**	Dh	07.03.2021
	 * 
	 * 	Setzt die RaceList und prueft, ob fuer alle in ihr enthaltenen RaceIDs eine ID existiert.
	 * 		pRaceList darf nicht null sein und nur Objekte von Type Race enthalten.
	 * 
	 * @param pRaceList
	 * @throws Exception
	 */
	public void setRaceList(List pRaceList) throws Exception {
		int vCurID;
		Object vTemp = null;
		List vIDList = new List();
		
		if (pRaceList != null) {
			pRaceList.toFirst();
			while(!pRaceList.isEnd()) {					// Erstellt eine ID List und checkt, ob diese bereits vorhanden ist.
				vTemp = pRaceList.getCurrent();
				
				if (vTemp instanceof Race) {
					vCurID = ((Race) vTemp).getId();
					
					vIDList.toFirst();
					while(!vIDList.isEnd()) {
						if (((int)vIDList.getCurrent()) == vCurID) throw new Exception("02a; sRaLi,Pac");
						
						vIDList.next();
					}
					
					vIDList.append(vCurID);
				} else throw new Exception("06; sRaLi,Pac");
				
				pRaceList.next();
			}
			
			if (checkIfAssociatedRaceIDsExists(pRaceList, vIDList) == false) throw new Exception("02b; sRaLi,Pac");
			
			raceList = pRaceList;
		}else throw new Exception("04; sRaLi,Pac");
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
	
	/**	Dh	04.03.2021
	 * 
	 * 	Fuegt pCulture in die cultureList hinzu.
	 * 		pCulture darf nicht null sein und ihre ID darf noch nicht in der Liste vorhanden sein.
	 * 
	 * @param pCulture
	 * @throws Exception
	 */
	public void addCulture(Culture pCulture) throws Exception{
		int vCultureID;
		Culture vCur;
		
		if (pCulture != null) {
			vCultureID = pCulture.getId();
			
			if (cultureList.getContentNumber() != 0) {
				cultureList.toFirst();
				while(!cultureList.isEnd()) {
					vCur = (Culture)cultureList.getCurrent();
					
					if (vCur.getId() == pCulture.getId()) throw new Exception("02; aCu,Pac");
					else if (vCultureID < vCur.getId()) {
						cultureList.insert(pCulture);
						cultureList.toLast();
					}else if (cultureList.isLast()) {
						cultureList.append(pCulture);
						cultureList.toLast();
					}
					
					cultureList.next();
				}
			} else cultureList.append(pCulture);
		} else throw new Exception("04; aCu,Pac");
	}
	/**	Dh	04.03.2021
	 * 
	 * 	Fuegt pRace in die raceList hinzu.
	 * 		pRace darf nicht null sein und ihre ID darf noch nicht in der Liste vorhanden sein.
	 * 
	 * @param pRace
	 * @throws Exception
	 */
	public void addRace(Race pRace) throws Exception{
		int vRaceID;
		Race vCur;
		
		if (pRace != null) {
			vRaceID = pRace.getId();
			
			if (raceList.getContentNumber() != 0) {
				raceList.toFirst();
				while(!raceList.isEnd()) {
					vCur = (Race)raceList.getCurrent();
					
					if (vCur.getId() == vRaceID) throw new Exception("02; aRa,Pac");
					else if (vRaceID < vCur.getId()) {
						raceList.insert(pRace);
						raceList.toLast();
					} else if (raceList.isLast()) {
						raceList.append(pRace);
						raceList.toLast();
					}
					
					raceList.next();
				}
			} else raceList.append(pRace);
		} else throw new Exception("04; aRa,Pac");
	}
	
	/**	Dh	27.02.2021
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
		List vProbElementList;
		ProbElement vCurProbEle, vTempProbEle;
		
		if (pCultureID >= 0) {
			cultureList.toFirst();
			while(!cultureList.isEnd()) {
				vCurCult = (Culture) cultureList.getCurrent();
				
				if (vCurCult.getId() == pCultureID) cultureList.remove();
				else {
					vProbElementList = vCurCult.getOriginCultureList().getProbElementList();
					
					vProbElementList.toFirst();
					while(!vProbElementList.isEnd()) {
						vCurProbEle = (ProbElement)vProbElementList.getCurrent();
						if ( vCurProbEle.getId() == pCultureID ) {
							vProbElementList.remove();
							vTempProbEle = (ProbElement)vProbElementList.getCurrent();
							
							if (vTempProbEle != null) vTempProbEle.setProbability(vTempProbEle.getProbability() + vCurProbEle.getProbability());
							else throw new Exception("04; rCul,Pac");
						}
						
						vProbElementList.next();
					}
					
					if ((vCurCult instanceof Subculture) && ( ((Subculture)vCurCult).getParentCulture().getId() == pCultureID )) {
						vCurCult = transformSubcultureToCulture((Subculture)vCurCult);
						
						if (cultureList.isLast()) {
							cultureList.remove();
							cultureList.append(vCurCult);
						}else {
							cultureList.remove();
							cultureList.insert(vCurCult);
						}
					}
					
					cultureList.next();
				}
			}
		}else throw new Exception("02; rCul,Pac");
	}
	/**	Dh	27.02.2021
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
		List vProbElementList;
		ProbElement vCurProbEle, vTempProbEle;
		
		if (pRaceID >= 0) {
			cultureList.toFirst();
			while(!cultureList.isEnd()) {
				vProbElementList = ((Culture) cultureList.getCurrent()).getRaceList().getProbElementList();
				
				vProbElementList.toFirst();
				while(!vProbElementList.isEnd()) {
					vCurProbEle = (ProbElement)vProbElementList.getCurrent();
					
					if (vCurProbEle.getId() == pRaceID) {
						vProbElementList.remove();
						vTempProbEle = (ProbElement)vProbElementList.getCurrent();
						
						if (vTempProbEle != null) vTempProbEle.setProbability(vTempProbEle.getProbability() + vCurProbEle.getProbability());
						else throw new Exception("04; rRac,Pac");
					}
					
					vProbElementList.next();
				}
				
				cultureList.next();
			}
			
			raceList.toFirst();
			while(!raceList.isEnd()) {
				vCurRace = (Race) raceList.getCurrent();
				
				if (vCurRace.getId() == pRaceID) raceList.remove();
				else if ((vCurRace instanceof Subrace) && ( ((Subrace)vCurRace).getParentRace().getId() == pRaceID )) {
					vCurRace = transformSubraceToRace((Subrace)vCurRace);
					
					if (raceList.isLast()) {
						raceList.remove();
						raceList.append(vCurRace);
					}else {
							raceList.remove();
						raceList.insert(vCurRace);
					}
					
					raceList.next();
				} else raceList.next();
			}
		}else throw new Exception("02; rRac,Pac");
	}
	
	/**	Dh	05.03.2021
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
			cultureList.toFirst();
			while(!cultureList.isEnd()) {
				vCur = (Culture) cultureList.getCurrent();
				
				if (vCur.getId() == pSubculutreID) {
					if (vCur instanceof Subculture) {
						vCur = transformSubcultureToCulture((Subculture) vCur);
						
						if (cultureList.isLast()) {
							cultureList.remove();
							cultureList.append(vCur);
						}else {
							cultureList.remove();
							cultureList.insert(vCur);
						}
						
						cultureList.toLast();
					} else throw new Exception("06; tSctC,Pac");
				}
				
				cultureList.next();
			}
		}else throw new Exception("02; tSctC,Pac");
	}
	/**	Dh	05.03.2021
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
			raceList.toFirst();
			while(!raceList.isEnd()) {
				vCur = (Race) raceList.getCurrent();
				
				if (vCur.getId() == pSubraceID) {
					if (vCur instanceof Subrace) {
						vCur = transformSubraceToRace((Subrace) vCur);
						
						if (raceList.isLast()) {
							raceList.remove();
							raceList.append(vCur);
						}else {
							raceList.remove();
							raceList.insert(vCur);
						}
						
						raceList.toLast();
					} else throw new Exception("06; tSrtR,Pac");
				}
				
				raceList.next();
			}
		}else throw new Exception("02; tSrtR,Pac");
	}
	//----
	/**	Dh	05.03.2021
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
			cultureList.toFirst();
			while(!cultureList.isEnd() && (vParCult == null)) {
				vParCult = (Culture) cultureList.getCurrent();
				
				if (vParCult.getId() != pParentCultureID) vParCult = null;
				
				cultureList.next();
			}
			
			if (vParCult != null) {
				cultureList.toFirst();
				while(!cultureList.isEnd()) {
					vCur = (Culture) cultureList.getCurrent();
					
					if (vCur.getId() == pCultureID) {
						if (!(vCur instanceof Subculture)) {
							vCur = transformCultureToSubculture(vCur, vParCult);
							
							if (cultureList.isLast()) {
								cultureList.remove();
								cultureList.append(vCur);
							}else {
								cultureList.remove();
								cultureList.insert(vCur);
							}
							
							cultureList.toLast();
						} else throw new Exception("06; tCtSc,Pac");
					}
					
					cultureList.next();
				}
			} else throw new Exception("07; tCtSc,Pac");
		}else throw new Exception("02; tCtSc,Pac");
	}
	/**	Dh	05.03.2021
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
			raceList.toFirst();
			while(!raceList.isEnd() && (vParRace == null)) {
				vParRace = (Race) raceList.getCurrent();
				
				if (vParRace.getId() != pParentRaceID) vParRace = null;
				
				raceList.next();
			}
			
			if (vParRace != null) {
				raceList.toFirst();
				while(!raceList.isEnd()) {
					vCur = (Race) raceList.getCurrent();
					
					if (vCur.getId() == pRaceID) {
						if (!(vCur instanceof Subrace)) {
							vCur = transformRaceToSubrace(vCur, vParRace);
							
							if (raceList.isLast()) {
								raceList.remove();
								raceList.append(vCur);
							}else {
								raceList.remove();
								raceList.insert(vCur);
							}
							
							raceList.toLast();
						} else throw new Exception("06; tRtSr,Pac");
					}
					
					raceList.next();
				}
			} else throw new Exception("07; tCtSc,Pac");
		}else throw new Exception("02; tRtSr,Pac");
	}
	
	//----------------------------------------------------------------------------------------------------

	/**	Dh	24.02.2021
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
				
				if (pSubculture.getOriginCultureList().getProbElementList().getContentNumber() != 0)
					vRet.setOriginCultureList(pSubculture.getOriginCultureList());
				else vRet.setOriginCultureList(pSubculture.getParentCulture().getOriginCultureList());
				if (pSubculture.getRaceList().getProbElementList().getContentNumber() != 0)
					vRet.setRaceList(pSubculture.getRaceList());
				else vRet.setRaceList(pSubculture.getParentCulture().getRaceList());
				if (pSubculture.getSexualityList().getProbElementList().getContentNumber() != 0)
					vRet.setSexualityList(pSubculture.getSexualityList());
				else vRet.setSexualityList(pSubculture.getParentCulture().getSexualityList());
				if (pSubculture.getHairlengthList().getProbElementList().getContentNumber() != 0)
					vRet.setHairlengthList(pSubculture.getHairlengthList());
				else vRet.setHairlengthList(pSubculture.getParentCulture().getHairlengthList());
				if (pSubculture.getSoList().getProbElementList().getContentNumber() != 0)
					vRet.setSoList(pSubculture.getSoList());
				else vRet.setSoList(pSubculture.getParentCulture().getSoList());
				
			}catch(Exception ex) {throw ex;}
		}else throw new Exception("04; tScTC,Pac");
		
		return vRet;
	}
	/**	Dh	12.03.2021
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
				
				if (pSubrace.getSexList().getProbElementList().getContentNumber() != 0)
					vRet.setSexList(pSubrace.getSexList());
				else vRet.setSexList(pSubrace.getParentRace().getSexList());
				if (pSubrace.getComplexionList().getProbElementList().getContentNumber() != 0)
					vRet.setComplexionList(pSubrace.getComplexionList());
				else vRet.setSexList(pSubrace.getParentRace().getSexList());
				if (pSubrace.getHaircolorList().getProbElementList().getContentNumber() != 0)
					vRet.setHaircolorList(pSubrace.getHaircolorList());
				else vRet.setHaircolorList(pSubrace.getParentRace().getHaircolorList());
				if (pSubrace.getEyecolorList().getProbElementList().getContentNumber() != 0)
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
	
	/**	Dh	25.02.2021
	 * 
	 * @param pProbElementList
	 * @return
	 */
 	private List extractIDsFromProbList(List pProbElementList) {
		List vRet = new List();
		
		pProbElementList.toFirst();
		while(!pProbElementList.isEnd()) {
			vRet.append(((ProbElement)pProbElementList.getCurrent()).getId());
			
			pProbElementList.next();
		}
		
		return vRet;
	}
	/**	Dh	25.02.2021
	 * 
	 * @param pToCheckIDs
	 * @param pIDList
	 * @return
	 */
	private boolean checkIfIDsAreCoveredInIDList(List pToCheckIDs, List pIDList) {
		boolean vRet = false;
		List vContainsIDList;
		
		pIDList.toFirst();
		vContainsIDList = new List();
		while(!pIDList.isEnd()) {		
			pToCheckIDs.toFirst();
			while(!pToCheckIDs.isEnd()) {
				if (pIDList.getCurrent() == pToCheckIDs.getCurrent()) vContainsIDList.append(true);
				
				pToCheckIDs.next();
			}
			
			pIDList.next();
		}
		
		if (vContainsIDList.getContentNumber() == pToCheckIDs.getContentNumber()) vRet = true;
		
		return vRet;
	}
	/**	Dh	25.02.2021
	 * 
	 * @param pProbElementList
	 * @param pIDList
	 * @return
	 */
	private boolean checkIfProbListIDsAreInIDList(List pProbElementList, List pIDList) {
		boolean vRet = false;
		
		vRet = checkIfIDsAreCoveredInIDList(extractIDsFromProbList(pProbElementList), pIDList);
		
		return vRet;
	}
	
	/**	Dh	25.02.2021
	 * 
	 * @param pCultureList
	 * @param pIDList
	 * @return
	 * @throws Exception
	 */
	private boolean checkIfAssociatedCultureIDsExists(List pCultureList, List pIDList) throws Exception{
		boolean vRet = true;
		Culture vCurCult;
		
		pCultureList.toFirst();
		while(!pCultureList.isEnd() && (vRet == true)) {
			vCurCult = (Culture)pCultureList.getCurrent();
			
			vRet = checkIfProbListIDsAreInIDList(vCurCult.getOriginCultureList().getProbElementList(), pIDList);
			if ((vRet == true) && (vCurCult instanceof Subculture))
				vRet = checkIfIDsAreCoveredInIDList(new List( (Object) ((Subculture)vCurCult).getParentCulture().getId() ), pIDList);
			
			pCultureList.next();
		}
		
		return vRet;
	}
	/**	Dh	25.02.2021
	 * 
	 * @param pRaceList
	 * @param pIDList
	 * @return
	 * @throws Exception
	 */
	private boolean checkIfAssociatedRaceIDsExists(List pRaceList, List pIDList) throws Exception{
		boolean vRet = true;
		Race vCurRace;
		
		pRaceList.toFirst();
		while(!pRaceList.isEnd() && (vRet == true)) {
			vCurRace = (Race)pRaceList.getCurrent();
			
			if (vCurRace instanceof Subrace)
				vRet = checkIfIDsAreCoveredInIDList(new List( (Object) ((Subrace)vCurRace).getParentRace().getId() ), pIDList);
			
			pRaceList.next();
		}
		
		return vRet;
	}
	
}
