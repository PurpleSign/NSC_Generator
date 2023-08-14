/**	NSC_Generator v0.2		Dh	13.08.2023
 * 	
 * 	logic.editors
 * 	  Editor
 * 	    RaceEditor
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

package org.nsc_generator.logic.editors;

import java.util.ArrayList;

import org.nsc_generator.logic.LogManager;
import org.nsc_generator.logic.pack.GenElement;
import org.nsc_generator.logic.pack.Pack;
import org.nsc_generator.logic.pack.ProbElement;
import org.nsc_generator.logic.pack.ProbList;
import org.nsc_generator.logic.pack.Race;
import org.nsc_generator.logic.pack.Subrace;

public class RaceEditor extends Editor {
	private PackEditor packEditor;
	private Race race;
	
	private ArrayList<ProbElement> sexElements, complexionElements, haircolorElements, eyecolorElements;
	
	/**	Dh	13.08.2023
	 * 
	 * @param pRace
	 * @param pPackEditor
	 */
	public RaceEditor(Race pRace, PackEditor pPackEditor) {
		if (pPackEditor != null) packEditor = pPackEditor;
		else LogManager.handleException(new Exception("04b; RaEdt"));
		
		if (pRace != null) {
			race = pRace;
			
			sexElements = (ArrayList<ProbElement>) race.getSexList().getProbElements().clone();
			complexionElements = (ArrayList<ProbElement>) race.getComplexionList().getProbElements().clone();
			haircolorElements = (ArrayList<ProbElement>) race.getHaircolorList().getProbElements().clone();
			eyecolorElements = (ArrayList<ProbElement>) race.getEyecolorList().getProbElements().clone();
		} else LogManager.handleException(new Exception("04a; RaEdt"));
	}

//--------------------------------------------------------------------------------------------------------	
	
	/**	Dh	27.02.2021
	 * 
	 * @return
	 */
	public String getName() {
		return race.getName();
	}
	
	/**	Dh	27.02.2021
	 * 
	 * 	Gibt das Alter als Array (number, side, offset) zurueck.
	 * 
	 * @return
	 * @throws Exception
	 */
	public int[] getAge() throws Exception {
		return transformGenElementToList(race.getAge());
	}
	/**	Dh	27.02.2021
	 * 
	 * 	Gibt die Groesse als Array (number, side, offset) zurueck.
	 * 
	 * @return
	 * @throws Exception
	 */
	public int[] getHeight() throws Exception {
		return transformGenElementToList(race.getHeight());
	}
	/**	Dh	27.02.2021
	 * 
	 * 	Gibt das Gewicht als Array (number, side, offset) zurueck.
	 * 
	 * @return
	 * @throws Exception
	 */
	public int[] getWeight() throws Exception {
		return transformGenElementToList(race.getWeight());
	}
	
	/**	Dh	13.08.2023
	 * 
	 * 	Gibt ein Geschlecht als Array (id, name, probability) zurueck.
	 * 
	 * 	pSexElementID muss goressergleich 0 sein und muss in den sexElements vorkommen.
	 * 
	 * @param pSexElementID
	 * @return
	 * @throws Exception
	 */
	public Object[] getSexElement(int pSexElementID) throws Exception{
		return getElementFromElementListAsArray(pSexElementID, sexElements);
	}
	/**	Dh	13.08.2023
	 * 
	 * 	Gibt einen Teint als Array (id, name, probability) zurueck.
	 * 
	 * 	pComplexionElementID muss goressergleich 0 sein und muss in den complexionElements vorkommen.
	 * 
	 * @param pComplexionElementID
	 * @return
	 * @throws Exception
	 */
	public Object[] getComplexionElement(int pComplexionElementID) throws Exception{
		return getElementFromElementListAsArray(pComplexionElementID, complexionElements);
	}
	/**	Dh	13.08.2023
	 * 
	 * 	Gibt eine Haarfarbe als Array (id, name, probability) zurueck.
	 * 
	 * 	pHaircolorElementID muss goressergleich 0 sein und muss in der haircolorElements vorkommen.
	 * 
	 * @param pHaircolorElementID
	 * @return
	 * @throws Exception
	 */
	public Object[] getHaircolorElement(int pHaircolorElementID) throws Exception{
		return getElementFromElementListAsArray(pHaircolorElementID, haircolorElements);
	}
	/**	Dh	13.08.2023
	 * 
	 * 	Gibt eine Augenfarbe als Array (id, name, probability) zurueck.
	 * 
	 * 	pEyecolorElementID muss goressergleich 0 sein und muss in den eyecolorElements vorkommen.
	 * 
	 * @param pEyecolorElementID
	 * @return
	 * @throws Exception
	 */
	public Object[] getEyecolorElement(int pEyecolorElementID) throws Exception{
		return getElementFromElementListAsArray(pEyecolorElementID, eyecolorElements);
	}
	//-----
	/**	Dh	13.08.2023
	 * 
	 * 	Gibt das Geschlecht als Liste von Arrays ((id, name, probability), ...) zurueck.
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Object[]> getSexList() throws Exception{
		return transformProbElementListToList(sexElements);
	}
	/**	Dh	13.08.2023
	 * 
	 * 	Gibt den Teint als Liste von Arrays ((id, name, probability), ...) zurueck.
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Object[]> getComplexionList() throws Exception{
		return transformProbElementListToList(complexionElements);
	}
	/**	Dh	13.08.2023
	 * 
	 * 	Gibt die Haarfarbe als Liste von Arrays ((id, name, probability), ...) zurueck.
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Object[]> getHaircolorList() throws Exception{
		return transformProbElementListToList(haircolorElements);
	}
	/**	Dh	13.08.2023
	 * 
	 * 	Gibt die Augenfarbe als Liste von Arrays ((id, name, probability), ...) zurueck.
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Object[]> getEyecolorList() throws Exception{
		return transformProbElementListToList(eyecolorElements);
	}
	
	/**	Dh	28.02.2021
	 * 
	 * @return
	 */
	public boolean isSubrace() {
		return (race instanceof Subrace);
	}
	/**	Dh	28.02.2021
	 * 
	 * @return
	 * @throws Exception
	 */
	public Object[] getParentRace() throws Exception{
		Object[] vRet = null;
		Race vCur;
		
		if (race instanceof Subrace) {
			vRet = new Object[2];
			vCur = ((Subrace) race).getParentRace();
			
			vRet[0] = vCur.getId();
			vRet[1] = vCur.getName();
		} else throw new Exception("06; gPR,RaEdi");
		
		return vRet;
	}
	//-----
	/**	Dh	13.08.2023
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Object[]> getPossibleParentList() throws Exception {
		int vID;
		ArrayList<Object[]> vRet, vRaceList;
		
		if (race != null) {
			vID = race.getId();
			vRaceList = packEditor.getRaceList();
			
			if (vRaceList != null) {
				vRet = new ArrayList<Object[]>();
				
				for (Object[] vCur : vRaceList) {
					if ( (int)(vCur[0]) != vID ) vRet.add(vCur);
				}
			} else throw new Exception ("04b; gPPL,RaEdi");
		} else throw new Exception ("04a; gPPL,RaEdi");
		
		return vRet;
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	27.02.2021
	 * 
	 * 	pName darf nicht null sein.
	 * 
	 * @param pName
	 * @throws Exception
	 */
	public void setName(String pName) throws Exception {
		race.setName(pName);
	}
	
	/**	Dh	27.02.2021
	 * 
	 * 	pNumber und pSide muessen groessergleich 0 sein.
	 * 
	 * @param pNumber
	 * @param pSide
	 * @param pOffset
	 * @throws Exception
	 */
	public void setAge(int pNumber, int pSide, int pOffset) throws Exception{
		race.setAge(new GenElement(pNumber, pSide, pOffset));
	}
	/**	Dh	27.02.2021
	 * 
	 * 	pNumber und pSide muessen groessergleich 0 sein.
	 * 
	 * @param pNumber
	 * @param pSide
	 * @param pOffset
	 * @throws Exception
	 */
	public void setHeight(int pNumber, int pSide, int pOffset) throws Exception{
		race.setHeight(new GenElement(pNumber, pSide, pOffset));
	}
	/**	Dh	27.02.2021
	 * 
	 * 	pNumber und pSide muessen groessergleich 0 sein.
	 * 
	 * @param pNumber
	 * @param pSide
	 * @param pOffset
	 * @throws Exception
	 */
	public void setWeight(int pNumber, int pSide, int pOffset) throws Exception{
		race.setWeight(new GenElement(pNumber, pSide, pOffset));
	}
	
	/**	Dh	13.08.2023
	 * 
	 * 	Setzt pName und pProbability des Sex Objektes mit der pSexElementID.
	 * 
	 * 	pName darf nicht null sein, pSexElementID und pProbability muessen groesser gleich 0 sein, und pSexElementID
	 * 		muss in den sexElements enthalten sein.
	 * 
	 * @param pSexElementID
	 * @param pName
	 * @param pProbability
	 * @throws Exception
	 */
	public void setSexElement(int pSexElementID, String pName, double pProbability) throws Exception{
		setElementFromElementList(pSexElementID, pName, pProbability, sexElements, 0);
	}
	/**	Dh	13.08.2023
	 * 
	 * 	Setzt pName und pProbability des Teint Objektes mit der pComplexionElementID.
	 * 
	 * 	pName darf nicht null sein, pComplexionElementID und pProbability muessen groesser gleich 0 sein, und pComplexionElementID
	 * 		muss in den complexionElements enthalten sein.
	 * 
	 * @param pComplexionElementID
	 * @param pName
	 * @param pProbability
	 * @throws Exception
	 */
	public void setComplexionElement(int pComplexionElementID, String pName, double pProbability) throws Exception{
		setElementFromElementList(pComplexionElementID, pName, pProbability, complexionElements, 0);
	}
	/**	Dh	13.08.2023
	 * 
	 * 	Setzt pName und pProbability des Haircolor Objektes mit der pHaircolorElementID.
	 * 
	 * 	pName darf nicht null sein, pHaircolorElementID und pProbability muessen groesser gleich 0 sein, und
	 * 		pHaircolorElementID muss in den haircolorElements enthalten sein.
	 * 
	 * @param pHaircolorElementID
	 * @param pName
	 * @param pProbability
	 * @throws Exception
	 */
	public void setHaircolorElement(int pHaircolorElementID, String pName, double pProbability) throws Exception{
		setElementFromElementList(pHaircolorElementID, pName, pProbability, haircolorElements, 0);
	}
	/**	Dh	13.08.2023
	 * 
	 * 	Setzt pName und pProbability des Eyecolor Objektes mit der pEyecolorElementID.
	 * 
	 * 	pName darf nicht null sein, pEyecolorElementID und pProbability muessen groesser gleich 0 sein, und
	 * 		pEyecolorElementID muss in den eyecolorElements enthalten sein.
	 * 
	 * @param pEyecolorElementID
	 * @param pName
	 * @param pProbability
	 * @throws Exception
	 */
	public void setEyecolorElement(int pEyecolorElementID, String pName, double pProbability) throws Exception{
		setElementFromElementList(pEyecolorElementID, pName, pProbability, eyecolorElements, 0);
	}
	//-----
	/**	Dh	13.08.2023
	 * 
	 * @throws Exception
	 */
	public void setSexList() throws Exception{
		race.setSexList(new ProbList(sexElements));
	}
	/**	Dh	13.08.2023
	 * 
	 * @throws Exception
	 */
	public void setComplexionList() throws Exception{
		race.setComplexionList(new ProbList(complexionElements));
	}
	/**	Dh	13.08.2023
	 * 
	 * @throws Exception
	 */
	public void setHaircolorList() throws Exception{
		race.setHaircolorList(new ProbList(haircolorElements));
	}
	/**	Dh	13.08.2023
	 * 
	 * @throws Exception
	 */
	public void setEyecolorList() throws Exception{
		race.setEyecolorList(new ProbList(eyecolorElements));
	}
	
	/**	Dh	28.02.2021
	 * 
	 * 	Setzt die ParentRace ueber die pParentRaceID; diese wird im pack in der raceList gesucht.
	 * 
	 * 	pParentRaceID muss groessegleich 0 sein und in der raceList von pack enthalten sein.
	 * 
	 * @param pParentRaceID
	 * @throws Exception
	 */
	public void setParentRace(int pParentRaceID) throws Exception{
		Race vPar;
		
		if (race instanceof Subrace) {
			vPar = packEditor.getPack().getRace(pParentRaceID);
			
			if (checkRecursiveIfRootedParentrace(vPar) == true) ((Subrace) race).setParentRace(vPar);
			else throw new Exception("04; sPR,RaEdi");
		} else throw new Exception("06; sPR,RaEdi");
	}
	
//--------------------------------------------------------------------------------------------------------	

	/**	Dh	13.08.2023
	 * 
	 * 	Fuegt ein ProbElement mit den pName und pProbability in den sexElements ein.
	 * 
	 * 	pName darf nicht null sein und pProbability muss groessergleich 0 sein.
	 * 
	 * @param pName
	 * @param pProbability
	 * @throws Exception
	 */
	public void addSexElement(String pName, double pProbability) throws Exception{
		addElementToElementList(pName, pProbability, sexElements, 0);
	}
	/**	Dh	13.08.2023
	 * 
	 * 	Fuegt ein ProbElement mit den pName und pProbability in den complexionElements ein.
	 * 
	 * 	pName darf nicht null sein und pProbability muss groessergleich 0 sein.
	 * 
	 * @param pName
	 * @param pProbability
	 * @throws Exception
	 */
	public void addComplexionElement(String pName, double pProbability) throws Exception{
		addElementToElementList(pName, pProbability, complexionElements, 0);
	}
	/**	Dh	13.08.2023
	 * 
	 * 	Fuegt ein ProbElement mit den pName und pProbability in den haircolorElements ein.
	 * 
	 * 	pName darf nicht null sein und pProbability muss groessergleich 0 sein.
	 * 
	 * @param pName
	 * @param pProbability
	 * @throws Exception
	 */
	public void addHaircolorElement(String pName, double pProbability) throws Exception{
		addElementToElementList(pName, pProbability, haircolorElements, 0);
	}
	/**	Dh	13.08.2023
	 * 
	 * 	Fuegt ein ProbElement mit den pName und pProbability in den eyecolorElements ein.
	 * 
	 * 	pName darf nicht null sein und pProbability muss groessergleich 0 sein.
	 * 
	 * @param pName
	 * @param pProbability
	 * @throws Exception
	 */
	public void addEyecolorElement(String pName, double pProbability) throws Exception{
		addElementToElementList(pName, pProbability, eyecolorElements, 0);
	}
	
	/**	Dh	27.02.2021
	 * 	
	 * 	Fuegt die Rasse/Ethnie die gerade bearbeitet wurde der raceList des aktuellen packs hinzu.
	 */
	public void add() throws Exception {
		packEditor.addRace(race);
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	13.08.2023
	 * 
	 * 	Entfernt das zur pSexElementId gehoerende Geschlecht aus den sexElements.
	 * 
	 * 	pSexElementID muss groessergleich 0 sein und in sexElements vorhanden sein.
	 * 
	 * @param pSexElementID
	 * @throws Exception
	 */
	public void removeSexElement(int pSexElementID) throws Exception{
		removeElementFromElementList(pSexElementID, sexElements);
	}
	/**	Dh	13.08.2023
	 * 
	 * 	Entfernt das zur pComplexionElementId gehoerender Teint aus den complexionElements.
	 * 
	 * 	pComplexionElementID muss groessergleich 0 sein und in complexionElements vorhanden sein.
	 * 
	 * @param pComplexionElementID
	 * @throws Exception
	 */
	public void removeComplexionElement(int pComplexionElementID) throws Exception{
		removeElementFromElementList(pComplexionElementID, complexionElements);
	}
	/**	Dh	13.08.2023
	 * 
	 * 	Entfernt das zur pHaircolorElementId gehoerende Haarfarbe aus den haircolorElements.
	 * 
	 * 	pHaircolorElementID muss groessergleich 0 sein und in haircolorElements vorhanden sein.
	 * 
	 * @param pHaircolorElementID
	 * @throws Exception
	 */
	public void removeHaircolorElement(int pHaircolorElementID) throws Exception{
		removeElementFromElementList(pHaircolorElementID, haircolorElements);
	}
	/**	Dh	13.08.2023
	 * 
	 * 	Entfernt das zur pEyecolorElementId gehoerende Augenfarbe aus den eyecolorElements.
	 * 
	 * 	pEyecolorElementID muss groessergleich 0 sein und in eyecolorElements vorhanden sein.
	 * 
	 * @param pEyecolorElementID
	 * @throws Exception
	 */
	public void removeEyecolorElement(int pEyecolorElementID) throws Exception{
		removeElementFromElementList(pEyecolorElementID, eyecolorElements);
	}
	
	/**	Dh	27.02.2021
	 * 
	 * 	Entfernt die Rasse/Ethnie die gerade bearbeitet wurde aus der raceList des aktuellen packs.
	 */
	public void remove() throws Exception {
		packEditor.removeRace(race.getId());
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	05.03.2021
	 * 
	 * 	Tranformiert das aktuelle Race Objekt anhand der uebergebenen pParentRaceID in ein Subrace Objekt.
	 * 
	 * 	pParentRaceID ist groessergleich 0 und muss in der raceList von pack vorhanden sein.
	 * 
	 * @param pParentRaceID
	 * @throws Exception
	 */
	public void transformToSubrace(int pParentRaceID) throws Exception {
		int vRaceID;
		
		Pack vPack = packEditor.getPack();
		Race vPar = vPack.getRace(pParentRaceID);
		
		if (checkRecursiveIfRootedParentrace(vPar) == true) {
			vRaceID = race.getId();
			
			if (vPack.getRace(vRaceID) != null) {
				vPack.transformRaceToSubrace(vRaceID, pParentRaceID);
				race = vPack.getRace(vRaceID);
			} else race = vPack.transformRaceToSubrace(race, vPar);
			
		}
	}
	/**	Dh	05.03.2021
	 * 
	 * 	Transformiert ein Subrace Objekt in ein Race Objekt.
	 * 
	 * @throws Exception
	 */
	public void transformFromSubrace() throws Exception{
		int vRaceID = race.getId();
		Pack vPack = packEditor.getPack();
		
		if (vPack.getRace(vRaceID) != null) {
			vPack.transformSubraceToRace(vRaceID);
			race = vPack.getRace(vRaceID);
		} else race = vPack.transformSubraceToRace((Subrace)race);
		
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	28.02.2021
	 * 
	 * @param pParRace
	 * @return
	 */
	private boolean checkRecursiveIfRootedParentrace(Race pParRace) {
		boolean vRet = false;
		Race vCurPar;
		
		
		if (pParRace instanceof Subrace) {
			vCurPar = ((Subrace) pParRace).getParentRace();
			
			if (vCurPar != null) return checkRecursiveIfRootedParentrace(vCurPar);
		} else vRet = true;
		
		return vRet;
	}
	
}
