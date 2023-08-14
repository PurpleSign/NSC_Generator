/**	NSC_Generator v0.2		Dh	12.08.2023
 * 	
 * 	logic.editors
 * 	  Editor
 * 	    CultureEditor
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
import org.nsc_generator.logic.pack.Culture;
import org.nsc_generator.logic.pack.Pack;
import org.nsc_generator.logic.pack.ProbElement;
import org.nsc_generator.logic.pack.ProbList;
import org.nsc_generator.logic.pack.Subculture;

public class CultureEditor extends Editor {
	private PackEditor packEditor;
	private Culture culture;
	
	private ArrayList<ProbElement> sexualityElements, hairlengthElements, soElements, sexIdentityElements;
	
	/**	Dh	13.08.2023
	 * 
	 * @param pCulture
	 * @param pPackEditor
	 */
	public CultureEditor(Culture pCulture, PackEditor pPackEditor) {
		if (pPackEditor != null) packEditor = pPackEditor;
		else LogManager.handleException(new Exception("04b; CuEdi"));
		
		if (pCulture != null) {
			culture = pCulture;
			
			sexualityElements = (ArrayList<ProbElement>)culture.getSexualityList().getProbElements().clone();
			hairlengthElements = (ArrayList<ProbElement>)culture.getHairlengthList().getProbElements().clone();
			soElements = (ArrayList<ProbElement>) culture.getSoList().getProbElements().clone();
			
			sexIdentityElements = new ArrayList<ProbElement>();
		} else LogManager.handleException(new Exception("04a; CuEdi"));
	}

//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	27.02.2021
	 * 
	 * @return
	 */
	public String getName() {
		return culture.getName();
	}

	/**	Dh	13.08.2023
	 * 
	 * 	Gibt eine Sexualitaet als Array (id, name, probability) zurueck.
	 * 
	 * 	pSexualityElementID muss goressergleich 0 sein und muss in der sexualityElements vorkommen.
	 * 
	 * @param pSexualityElementID
	 * @return
	 * @throws Exception
	 */
	public Object[] getSexualityElement(int pSexualityElementID) throws Exception{
		return getElementFromElementListAsArray(pSexualityElementID, sexualityElements);
	}
	/**	Dh	13.08.2023
	 * 
	 * 	Gibt eine Haarlaenge als Array (id, name, probability) zurueck.
	 * 
	 * 	pHairlengthElementID muss goressergleich 0 sein und muss in der hairlengthElements vorkommen.
	 * 
	 * @param pHairlengthElementID
	 * @return
	 * @throws Exception
	 */
	public Object[] getHairlengthElement(int pHairlengthElementID) throws Exception{
		return getElementFromElementListAsArray(pHairlengthElementID, hairlengthElements);
	}
	/**	Dh	13.08.2023
	 * 
	 * 	Gibt einen SO als Array (id, name, probability) zurueck.
	 * 
	 * 	pSoElementID muss goressergleich 0 sein und muss in der soElements vorkommen.
	 * 
	 * @param pSoElementID
	 * @return
	 * @throws Exception
	 */
	public Object[] getSoElement(int pSoElementID) throws Exception{
		return getElementFromElementListAsArray(pSoElementID, soElements);
	}
	/**	Dh	13.08.2023
	 * 
	 * 	Gibt eine SexIdentity als Array (id, name, probability) zurueck.
	 * 
	 * 	pSexIdentityElementID muss goressergleich 0 sein und muss in der sexIdentityElements vorkommen.
	 * 
	 * @param pSexIdentityElementID
	 * @return
	 * @throws Exception
	 */
	public Object[] getSexIdentityElement(int pSexIdentityElementID) throws Exception{
		return getElementFromElementListAsArray(pSexIdentityElementID, sexIdentityElements);
	}
	
	//-----
	/**	Dh	13.08.2023
	 * 
	 * 	Gibt die Sexualitaet als Liste von Arrays ((id, name, probability), ...) zurueck.
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Object[]> getSexualityList() throws Exception{
		return transformProbElementListToList(sexualityElements);
	}
	/**	Dh	13.08.2023
	 * 
	 * 	Gibt die Haarlaenge als Liste von Arrays ((id, name, probability), ...) zurueck.
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Object[]> getHairlengthList() throws Exception{
		return transformProbElementListToList(hairlengthElements);
	}
	/**	Dh	13.08.2023
	 * 
	 * 	Gibt den SO als Liste von Arrasys ((id, name, probability), ...) zurueck.
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Object[]> getSoList() throws Exception{
		return transformProbElementListToList(soElements);
	}
	/**	Dh	13.08.2023
	 * 
	 * 	Gibt die SexIdentity als Liste von Arrasys ((id, name, probability), ...) zurueck.
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Object[]> getSexIdentityList() throws Exception{
		return transformProbElementListToList(sexIdentityElements);
	}
	
	/**	Dh	28.02.2021
	 * 
	 * @return
	 */
	public boolean isSubculture() {
		return (culture instanceof Subculture);
	}
	/**	Dh	28.02.2021
	 * 
	 * @return
	 * @throws Exception
	 */
	public Object[] getParentCulture() throws Exception{
		Object[] vRet = null;
		Culture vCur;
		
		if (culture instanceof Subculture) {
			vRet = new Object[2];
			vCur = ((Subculture) culture).getParentCulture();
			
			vRet[0] = vCur.getId();
			vRet[1] = vCur.getName();
		} else throw new Exception("06; gPC,CuEdi");
		
		return vRet;
	}
	//-----
	/**	Dh	13.08.2023
	 * 
	 * @return
	 */
	public ArrayList<Object[]> getPossibleParentList() throws Exception {
		int vID;
		ArrayList<Object[]> vRet, vCultList;
		
		if (culture != null) {
			vID = culture.getId();
			vCultList = packEditor.getCultureList();
			
			if (vCultList != null) {
				vRet = new ArrayList<Object[]>();
				
				for (Object[] vCur : vCultList) {
					if ( (int)(vCur[0]) != vID ) vRet.add(vCur);
				}
			} else throw new Exception ("04b; gPPL,CuEdi");
		} else throw new Exception ("04a; gPPL,CuEdi");
		
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
		culture.setName(pName);
	}
	
	/**	Dh	13.08.2023
	 * 
	 * 	Setzt pName und pProbability des Sexualitaets Objektes mit der pSexualityElementID.
	 * 
	 * 	pName darf nicht null sein, pSexualityElementID und pProbability muessen groesser gleich 0 sein, und pSexualityElementID
	 * 		muss in der sexualityElements enthalten sein.
	 * 
	 * @param pSexualityElementID
	 * @param pName
	 * @param pProbability
	 * @throws Exception
	 */
	public void setSexualityElement(int pSexualityElementID, String pName, double pProbability) throws Exception{
		setElementFromElementList(pSexualityElementID, pName, pProbability, sexualityElements, 0);
	}
	/**	Dh	13.08.2023
	 * 
	 * 	Setzt pName und pProbability des Hairlength Objektes mit der pHairlengthElementID.
	 * 
	 * 	pName darf nicht null sein, pHairlengthElementID und pProbability muessen groesser gleich 0 sein, und pHairlengthElementID
	 * 		muss in der hairlengthElements enthalten sein.
	 * 
	 * @param pHairlengthElementID
	 * @param pName
	 * @param pProbability
	 * @throws Exception
	 */
	public void setHairlengthElement(int pHairlengthElementID, String pName, double pProbability) throws Exception{
		setElementFromElementList(pHairlengthElementID, pName, pProbability, hairlengthElements, 0);
	}
	/**	Dh	15.03.2021
	 * 
	 * 	Setzt pName und pProbability des SO Objektes mit der pSoElementID.
	 * 
	 * 	pName darf nicht null sein, pSoElementID und pProbability muessen groesser gleich 0 sein, und pSoElementID
	 * 		muss in der soElements enthalten sein.
	 * 
	 * @param pSoElementID
	 * @param pName
	 * @param pProbability
	 * @throws Exception
	 */
	public void setSoElement(int pSoElementID, String pName, double pProbability) throws Exception{
		setElementFromElementList(pSoElementID, pName, pProbability, soElements, 0);
	}
	/**	Dh	13.08.2023
	 * 
	 * 	Setzt pName und pProbability des SexIdentity Objektes mit der pSexIdentityElementID.
	 * 
	 * 	pName darf nicht null sein, pSexIdentityElementID und pProbability muessen groesser gleich 0 sein, und pSexIdentityElementID
	 * 		muss in der sexIdentityElements enthalten sein.
	 * 
	 * @param pSexIdentityElementID
	 * @param pName
	 * @param pProbability
	 * @throws Exception
	 */
	public void setSexIdentityElement(int pSexIdentityElementID, String pName, double pProbability) throws Exception{
		setElementFromElementList(pSexIdentityElementID, pName, pProbability, sexIdentityElements, 0);
	}
	//-----
	/**	Dh	13.08.2023
	 * 
	 * @param pList
	 * @throws Exception
	 */
	public void setSexualityList() throws Exception{
		culture.setSexualityList(new ProbList(sexualityElements));
	}
	/**	Dh	13.08.2023
	 * 
	 * @param pList
	 * @throws Exception
	 */
	public void setHairlengthList() throws Exception{
		culture.setHairlengthList(new ProbList(hairlengthElements));
	}
	/**	Dh	13.08.2023
	 * 
	 * @throws Exception
	 */
	public void setSoList() throws Exception{
		culture.setSoList(new ProbList(soElements));
	}
																				// Nochrt einfügen!!!!
	public void setSexIdentityList() throws Exception{
		//culture.set
	}
	
	/**	Dh	28.02.2021
	 * 
	 * 	Setzt die ParentCulture ueber die pParentCultureID; diese wird im pack in der cultureList gesucht.
	 * 
	 * 	pParentCultureID muss groessegleich 0 sein und in der cultureList von pack enthalten sein.
	 * 
	 * @param pParentCultureID
	 * @throws Exception
	 */
	public void setParentCulture(int pParentCultureID) throws Exception{
		Culture vPar;
		
		if (culture instanceof Subculture) {
			vPar = packEditor.getPack().getCulture(pParentCultureID);
			
			if (checkRecursiveIfRootedParentculture(vPar) == true) ((Subculture) culture).setParentCulture(vPar);
			else throw new Exception("04; sPC,CuEdi");
		} else throw new Exception("06; sPC,CuEdi");
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	13.08.2023
	 * 
	 * 	Fuegt ein ProbElement mit den pName und pProbability in die sexualityElements ein.
	 * 
	 * 	pName darf nicht null sein und pProbability muss groessergleich 0 sein.
	 * 
	 * @param pName
	 * @param pProbability
	 * @throws Exception
	 */
	public void addSexualityElement(String pName, double pProbability) throws Exception{
		addElementToElementList(pName, pProbability, sexualityElements, 0);
	}
	/**	Dh	13.08.2023
	 * 
	 * 	Fuegt ein ProbElement mit den pName und pProbability in die hairlengthElements ein.
	 * 
	 * 	pName darf nicht null sein und pProbability muss groessergleich 0 sein.
	 * 
	 * @param pName
	 * @param pProbability
	 * @throws Exception
	 */
	public void addHairlengthElement(String pName, double pProbability) throws Exception{
		addElementToElementList(pName, pProbability, hairlengthElements, 0);
	}
	/**	Dh	13.08.2023
	 * 
	 * 	Fuegt ein ProbElement mit den pName und pProbability in die soElements ein.
	 * 
	 * 	pName darf nicht null sein und pProbability muss groessergleich 0 sein.
	 * 
	 * @param pName
	 * @param pProbability
	 * @throws Exception
	 */
	public void addSoElement(String pName, double pProbability) throws Exception{
		addElementToElementList(pName, pProbability, soElements, 0);
	}
	/**	Dh	13.08.2023
	 * 
	 * 	Fuegt ein ProbElement mit den pName und pProbability in die sexIdentityElements ein.
	 * 
	 * 	pName darf nicht null sein und pProbability muss groessergleich 0 sein.
	 * 
	 * @param pName
	 * @param pProbability
	 * @throws Exception
	 */
	public void addSexIdentityElement(String pName, double pProbability) throws Exception{
		addElementToElementList(pName, pProbability, sexIdentityElements, 0);
	}
	
	/**	Dh	27.02.2021
	 * 	
	 * 	Fuegt die Kultur die gerade bearbeitet wurde der cultureList des aktuellen packs hinzu.
	 * 
	 * @throws Exception
	 */
	public void add() throws Exception {
		packEditor.addCulture(culture);
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	13.08.2023
	 * 
	 * 	Entfernt die zur pSexualityElementId gehoerende Sexualitaet aus der sexualityElements.
	 * 
	 * 	pSexualityElementID muss groessergleich 0 sein und in sexualityElements vorhanden sein.
	 * 
	 * @param pSexualityElementID
	 * @throws Exception
	 */
	public void removeSexualityElement(int pSexualityElementID) throws Exception{
		removeElementFromElementList(pSexualityElementID, sexualityElements);
	}
	/**	Dh	13.08.2023
	 * 
	 * 	Entfernt die zur pHairlengthElementID gehoerende Haarlaenge aus der haielengthElements.
	 * 
	 * 	pHairlengthElementID muss groessergleich 0 sein und in hairlengthElements vorhanden sein.
	 * 
	 * @param pHairlengthElementID
	 * @throws Exception
	 */
	public void removeHairlengthElement(int pHairlengthElementID) throws Exception{
		removeElementFromElementList(pHairlengthElementID, hairlengthElements);
	}
	/**	Dh	13.08.2023
	 * 
	 * 	Entfernt die zur pSoElementID gehoerende Haarlaenge aus der soElements.
	 * 
	 * 	pSoElementID muss groessergleich 0 sein und in soElements vorhanden sein.
	 * 
	 * @param pSoElementID
	 * @throws Exception
	 */
	public void removeSoElement(int pSoElementID) throws Exception{
		removeElementFromElementList(pSoElementID, soElements);
	}
	/**	Dh	13.08.2023
	 * 
	 * 	Entfernt die zur pSexIdentityElementID gehoerende sexuelle Identität aus den sexIdentityElements.
	 * 
	 * 	pSexIdentityElementID muss groessergleich 0 sein und in sexIdentityElements vorhanden sein.
	 * 
	 * @param pSoElementID
	 * @throws Exception
	 */
	public void removeSexIdentityElement(int pSexIdentityElementID) throws Exception{
		removeElementFromElementList(pSexIdentityElementID, sexIdentityElements);
	}
	
	/**	Dh	27.02.2021
	 * 
	 * 	Entfernt die Kultur die gerade bearbeitet wurde aus der cultureList des aktuellen packs.
	 */
	public void remove() throws Exception {
		packEditor.removeCulture(culture.getId());
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	05.03.2021
	 * 
	 * 	Tranformiert das aktuelle Culture Objekt anhand der uebergebenen pParentCultureID in ein Subculture Objekt.
	 * 
	 * 	pParentCultureID ist groessergleich 0 und muss in der cultureList von pack vorhanden sein.
	 * 
	 * @param pParentCultureID
	 * @throws Exception
	 */
	public void transformToSubculture(int pParentCultureID) throws Exception {
		int vCultureID;
		
		Pack vPack = packEditor.getPack();
		Culture vPar = vPack.getCulture(pParentCultureID);
		
		if (checkRecursiveIfRootedParentculture(vPar) == true) {
			vCultureID = culture.getId();
				
			if (vPack.getCulture(vCultureID) != null) {
				vPack.transformCultureToSubculture(vCultureID, pParentCultureID);
				culture = vPack.getCulture(vCultureID);
			} else culture = vPack.transformCultureToSubculture(culture, vPar);
		}
	}
	/**	Dh	05.03.2021
	 * 
	 * 	Transformiert ein Subculture Objekt in ein Culture Objekt.
	 * 
	 * @throws Exception
	 */
	public void transformFromSubculture() throws Exception{
		int vCultureID = culture.getId();
		Pack vPack = packEditor.getPack();
		
		if (vPack.getCulture(vCultureID) != null) {
			vPack.transformSubcultureToCulture(vCultureID);
			culture = vPack.getCulture(vCultureID);
		} else culture = vPack.transformSubcultureToCulture((Subculture)culture);
		
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	28.02.2021
	 * 
	 * @param pParCulture
	 * @return
	 */
	private boolean checkRecursiveIfRootedParentculture(Culture pParCulture) {
		boolean vRet = false;
		Culture vCurPar;
		
		
		if (pParCulture instanceof Subculture) {
			vCurPar = ((Subculture) pParCulture).getParentCulture();
			
			if (vCurPar != null) return checkRecursiveIfRootedParentculture(vCurPar);
		} else vRet = true;
		
		return vRet;
	}
	
}
