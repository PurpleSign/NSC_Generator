/**	NSC_Generator v0.0		Dh	15.03.2021
 * 	
 * 	pLogic.pEditors
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

package nsc_generator.pLogic.pEditors;

import pDataStructures.List;
import nsc_generator.pLogic.MainManager;
import nsc_generator.pLogic.pPack.Culture;
import nsc_generator.pLogic.pPack.Pack;
import nsc_generator.pLogic.pPack.ProbList;
import nsc_generator.pLogic.pPack.Subculture;

public class CultureEditor extends Editor {
	private PackEditor packEditor;
	private Culture culture;
	
	private List sexualityElementList, hairlengthElementList, soElementList;
	
	/**	Dh	12.03.2021
	 * 
	 * @param pCulture
	 * @param pPackEditor
	 */
	public CultureEditor(Culture pCulture, PackEditor pPackEditor) {
		if (pPackEditor != null) packEditor = pPackEditor;
		else MainManager.handleException(new Exception("04b; CuEdi"));
		
		if (pCulture != null) {
			culture = pCulture;
			
			sexualityElementList = culture.getSexualityList().getProbElementList().copyList();
			hairlengthElementList = culture.getHairlengthList().getProbElementList().copyList();
			soElementList = culture.getSoList().getProbElementList().copyList();
		} else MainManager.handleException(new Exception("04a; CuEdi"));
	}

//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	27.02.2021
	 * 
	 * @return
	 */
	public String getName() {
		return culture.getName();
	}

	/**	Dh	14.03.2021
	 * 
	 * 	Gibt eine Sexualitaet als Array (id, name, probability) zurueck.
	 * 
	 * 	pSexualityElementID muss goressergleich 0 sein und muss in der sexualityElementList vorkommen.
	 * 
	 * @param pSexualityElementID
	 * @return
	 * @throws Exception
	 */
	public Object[] getSexualityElement(int pSexualityElementID) throws Exception{
		return getElementFromElementListAsArray(pSexualityElementID, sexualityElementList);
	}
	/**	Dh	14.03.2021
	 * 
	 * 	Gibt eine Haarlaenge als Array (id, name, probability) zurueck.
	 * 
	 * 	pHairlengthElementID muss goressergleich 0 sein und muss in der hairlengthElementList vorkommen.
	 * 
	 * @param pHairlengthElementID
	 * @return
	 * @throws Exception
	 */
	public Object[] getHairlengthElement(int pHairlengthElementID) throws Exception{
		return getElementFromElementListAsArray(pHairlengthElementID, hairlengthElementList);
	}
	/**	Dh	14.03.2021
	 * 
	 * 	Gibt einen SO als Array (id, name, probability) zurueck.
	 * 
	 * 	pSoElementID muss goressergleich 0 sein und muss in der soElementList vorkommen.
	 * 
	 * @param pSoElementID
	 * @return
	 * @throws Exception
	 */
	public Object[] getSoElement(int pSoElementID) throws Exception{
		return getElementFromElementListAsArray(pSoElementID, soElementList);
	}
	//-----
	/**	Dh	27.02.2021
	 * 
	 * 	Gibt die Sexualitaet als Liste von Arrays ((id, name, probability), ...) zurueck.
	 * 
	 * @return
	 * @throws Exception
	 */
	public List getSexualityList() throws Exception{
		return transformProbElementListToList(sexualityElementList);
	}
	/**	Dh	27.02.2021
	 * 
	 * 	Gibt die Haarlaenge als Liste von Arrays ((id, name, probability), ...) zurueck.
	 * 
	 * @return
	 * @throws Exception
	 */
	public List getHairlengthList() throws Exception{
		return transformProbElementListToList(hairlengthElementList);
	}
	/**	Dh	12.03.2021
	 * 
	 * 	Gibt den SO als Liste von Arrasys ((id, name, probability), ...) zurueck.
	 * 
	 * @return
	 * @throws Exception
	 */
	public List getSoList() throws Exception{
		return transformProbElementListToList(soElementList);
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
	/**	Dh	05.03.2021
	 * 
	 * @return
	 */
	public List getPossibleParentList() throws Exception {
		int vID;
		List vRet, vCultList;
		
		if (culture != null) {
			vID = culture.getId();
			vCultList = packEditor.getCultureList();
			
			if (vCultList != null) {
				vRet = new List();
				
				vCultList.toFirst();
				while(!vCultList.isEnd()) {
					if ( (int)((Object[])vCultList.getCurrent())[0] != vID ) vRet.append(vCultList.getCurrent());
					
					vCultList.next();
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
	
	/**	Dh	15.03.2021
	 * 
	 * 	Setzt pName und pProbability des Sexualitaets Objektes mit der pSexualityElementID.
	 * 
	 * 	pName darf nicht null sein, pSexualityElementID und pProbability muessen groesser gleich 0 sein, und pSexualityElementID
	 * 		muss in der sexualityElementList enthalten sein.
	 * 
	 * @param pSexualityElementID
	 * @param pName
	 * @param pProbability
	 * @throws Exception
	 */
	public void setSexualityElement(int pSexualityElementID, String pName, double pProbability) throws Exception{
		setElementFromElementList(pSexualityElementID, pName, pProbability, sexualityElementList, 0);
	}
	/**	Dh	15.03.2021
	 * 
	 * 	Setzt pName und pProbability des Hairlength Objektes mit der pHairlengthElementID.
	 * 
	 * 	pName darf nicht null sein, pHairlengthElementID und pProbability muessen groesser gleich 0 sein, und pHairlengthElementID
	 * 		muss in der hairlengthElementList enthalten sein.
	 * 
	 * @param pHairlengthElementID
	 * @param pName
	 * @param pProbability
	 * @throws Exception
	 */
	public void setHairlengthElement(int pHairlengthElementID, String pName, double pProbability) throws Exception{
		setElementFromElementList(pHairlengthElementID, pName, pProbability, hairlengthElementList, 0);
	}
	/**	Dh	15.03.2021
	 * 
	 * 	Setzt pName und pProbability des SO Objektes mit der pSoElementID.
	 * 
	 * 	pName darf nicht null sein, pSoElementID und pProbability muessen groesser gleich 0 sein, und pSoElementID
	 * 		muss in der soElementList enthalten sein.
	 * 
	 * @param pSoElementID
	 * @param pName
	 * @param pProbability
	 * @throws Exception
	 */
	public void setSoElement(int pSoElementID, String pName, double pProbability) throws Exception{
		setElementFromElementList(pSoElementID, pName, pProbability, soElementList, 0);
	}
	//-----
	/**	Dh	27.02.2021
	 * 
	 * @param pList
	 * @throws Exception
	 */
	public void setSexualityList() throws Exception{
		culture.setSexualityList(new ProbList(sexualityElementList));
	}
	/**	Dh	27.02.2021
	 * 
	 * @param pList
	 * @throws Exception
	 */
	public void setHairlengthList() throws Exception{
		culture.setHairlengthList(new ProbList(hairlengthElementList));
	}
	/**	Dh	12.03.2021
	 * 
	 * @throws Exception
	 */
	public void setSoList() throws Exception{
		culture.setSoList(new ProbList(soElementList));
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
	
	/**	Dh	15.03.2021
	 * 
	 * 	Fuegt ein ProbElement mit den pName und pProbability in die sexualityElementList ein.
	 * 
	 * 	pName darf nicht null sein und pProbability muss groessergleich 0 sein.
	 * 
	 * @param pName
	 * @param pProbability
	 * @throws Exception
	 */
	public void addSexualityElement(String pName, double pProbability) throws Exception{
		addElementToElementList(pName, pProbability, sexualityElementList, 0);
	}
	/**	Dh	15.03.2021
	 * 
	 * 	Fuegt ein ProbElement mit den pName und pProbability in die hairlengthElementList ein.
	 * 
	 * 	pName darf nicht null sein und pProbability muss groessergleich 0 sein.
	 * 
	 * @param pName
	 * @param pProbability
	 * @throws Exception
	 */
	public void addHairlengthElement(String pName, double pProbability) throws Exception{
		addElementToElementList(pName, pProbability, hairlengthElementList, 0);
	}
	/**	Dh	15.03.2021
	 * 
	 * 	Fuegt ein ProbElement mit den pName und pProbability in die soElementList ein.
	 * 
	 * 	pName darf nicht null sein und pProbability muss groessergleich 0 sein.
	 * 
	 * @param pName
	 * @param pProbability
	 * @throws Exception
	 */
	public void addSoElement(String pName, double pProbability) throws Exception{
		addElementToElementList(pName, pProbability, soElementList, 0);
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
	
	/**	Dh	15.03.2021
	 * 
	 * 	Entfernt die zur pSexualityElementId gehoerende Sexualitaet aus der sexualityElementList.
	 * 
	 * 	pSexualityElementID muss groessergleich 0 sein und in sexualityElementList vorhanden sein.
	 * 
	 * @param pSexualityElementID
	 * @throws Exception
	 */
	public void removeSexualityElement(int pSexualityElementID) throws Exception{
		removeElementFromElementList(pSexualityElementID, sexualityElementList);
	}
	/**	Dh	15.03.2021
	 * 
	 * 	Entfernt die zur pHairlengthElementID gehoerende Haarlaenge aus der haielengthElementList.
	 * 
	 * 	pHairlengthElementID muss groessergleich 0 sein und in hairlengthElementList vorhanden sein.
	 * 
	 * @param pHairlengthElementID
	 * @throws Exception
	 */
	public void removeHairlengthElement(int pHairlengthElementID) throws Exception{
		removeElementFromElementList(pHairlengthElementID, hairlengthElementList);
	}
	/**	Dh	15.03.2021
	 * 
	 * 	Entfernt die zur pSoElementID gehoerende Haarlaenge aus der soElementList.
	 * 
	 * 	pSoElementID muss groessergleich 0 sein und in soElementList vorhanden sein.
	 * 
	 * @param pSoElementID
	 * @throws Exception
	 */
	public void removeSoElement(int pSoElementID) throws Exception{
		removeElementFromElementList(pSoElementID, soElementList);
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
