/**	NSC_Generator v0.2		Dh	14.08.2023
 * 	
 * 	logic.editors
 * 	  Editor
 * 	    PackEditor
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

import org.nsc_generator.logic.DatabaseConnector;
import org.nsc_generator.logic.IDElement;
import org.nsc_generator.logic.LogManager;
import org.nsc_generator.logic.pack.Culture;
import org.nsc_generator.logic.pack.Pack;
import org.nsc_generator.logic.pack.PrioElement;
import org.nsc_generator.logic.pack.PrioList;
import org.nsc_generator.logic.pack.ProbElement;
import org.nsc_generator.logic.pack.ProbList;
import org.nsc_generator.logic.pack.Race;

public class PackEditor extends Editor {
	protected Pack pack;
	
	private ArrayList<PrioElement> quirks;
	
	/**	Dh	13.08.2023
	 * 
	 * @param pPack
	 */
	public PackEditor() {
		pack = null;
		
		//quirkList = new List();
		quirks = new ArrayList<PrioElement>();
	}
	/**	Dh	13.08.2023
	 * 
	 * @param pPack
	 */
 	public PackEditor(Pack pPack) {
		if (pPack != null) {
			pack = pPack;
			
			//quirkList = pack.getQuirkList().getPrioElementList().copyList();
			quirks = (ArrayList<PrioElement>)pack.getQuirkList().getPrioElements().clone();
		}else LogManager.handleException(new Exception("04; PaEd"));
	}
 	
//--------------------------------------------------------------------------------------------------------

 	/**	Dh	27.02.2021
 	 * 
 	 * @return
 	 */
 	public String getName() {
 		return pack.getName();
 	}
 	
 	/**	Dh	13.08.2023
 	 * 
 	 * @param pQuirkElementID
 	 * @return
 	 * @throws Exception
 	 */
 	public Object[] getQuirkElement(int pQuirkElementID) throws Exception{
		return getElementFromElementListAsArray(pQuirkElementID, quirks);
	}
 	
 	/**	Dh	13.08.2023
 	 * 
 	 * @return
 	 * @throws Exception
 	 */
 	public ArrayList<Object[]> getQuirkList() throws Exception{
		return transformPrioElementListToList(quirks);
	}
 	//-----
 	/**	Dh	13.08.2023
 	 * 
 	 * 	Gibt eine Liste mit den Kulturen von pack zurueck, wobei die Culture-Objekten durch ein Object Array(Id, Name)
 	 * 		repr‰sentiert werden.
 	 * 
 	 * @return
 	 */
 	public ArrayList<Object[]> getCultureList() throws Exception{
 		return genObjectArrayListFromIDElementList(pack.getCultures());
 	}
 	/**	Dh	13.08.2023
 	 * 	 
 	 * 	Gibt eine Liste mit den Rassen/Ethnien von pack zurueck, wobei die Race-Objekten durch ein Object-Array(Id, Name)
 	 * 		repr‰sentiert werden.
 	 * 
 	 * @return
 	 */
 	public ArrayList<Object[]> getRaceList() throws Exception{
 		return genObjectArrayListFromIDElementList(pack.getRaces());
 	}
 	
 	/**	Dh	14.08.2023
 	 * 
 	 * 	Liefert eine Liste mit den OriginCultureList Wahrscheinlichkeitsverteilungen aller Kulturen zurueck.
 	 * 		Die List hat die Form, (( , culterName, ...), (culterName, probability, ...), ...)
 	 * 
 	 * @return
 	 * @throws Exception
 	 */
 	public ArrayList<ArrayList<Object[]>> getCultureDistributions() throws Exception {
 		return genTwoListDistribution(pack.getCultures(), pack.getCultures(), true);
 	}
 	/**	Dh	14.08.2023
 	 * 
 	 * 	Liefert eine Liste mit den RaceList Wahrscheinlichkeitsverteilungen aller Kulturen zurueck.
 	 * 		Die List hat die Form, (( , raceName, ...), (culterName, probability, ...), ...)
 	 * 
 	 * @return
 	 * @throws Exception
 	 */
 	public ArrayList<ArrayList<Object[]>> getRaceDistributions() throws Exception{
 		return genTwoListDistribution(pack.getCultures(), pack.getRaces(), false);
 	}
 	
 	/**	Dh	28.02.2021
 	 * 
 	 * @return
 	 */
 	public Pack getPack() {
 		return pack;
 	}
 	
 	//----------------------------------------------------------------------------------------------------
 	
 	/**	Dh	27.02.2021
 	 * 
 	 * @param pName
 	 * @throws Exception
 	 */
 	public void setName(String pName) throws Exception{
 		try { pack.setName(pName);}
 		catch(Exception ex) {throw ex;}
 	}
 	
 	/**	Dh	13.08.2023
 	 * 
 	 * 	Setzt pName und pPriority des Quirk Objektes mit der pQuirkElementID.
	 * 
	 * 	pName darf nicht null sein, pQuirkElementID und pPriority muessen groesser gleich 0 sein, und pQuirkElementID
	 * 		muss in den quirkElements enthalten sein.
 	 * 
 	 * @param pQuirkElementID
 	 * @param pName
 	 * @param pPriority
 	 * @throws Exception
 	 */
 	public void setQuirkElement(int pQuirkElementID, String pName, int pPriority) throws Exception{
		setElementFromElementList(pQuirkElementID, pName, pPriority, quirks, 1);
	}
	//-----
	/**	Dh	13.08.2023
	 * 
	 * @throws Exception
	 */
	public void setQuirkList() throws Exception{
		pack.setQuirkList(new PrioList(quirks));
	}
 	
 	/**	Dh	14.08.2023
 	 * 
 	 * 	Ubernimmt die OriginCulture Wahrscheinlichkeitsverteilung die in pCultureDistribution enthalten sind. 
 	 * 		pCultureDistribution muss die Form ((, cultureName), (cultureName, probability, ...), ...) haben.
 	 * 
 	 * @param pCultureDistribution
 	 * @throws Exception
 	 */
 	public void setCultureDistributions(ArrayList<ArrayList<Object[]>> pCultureDistribution) throws Exception{
 		setDistributions(pCultureDistribution, true);
 	}
 	/**	Dh	14.08.2023
 	 * 
 	 * 	Ubernimmt die Race Wahrscheinlichkeitsverteilung die in pRaceDistribution enthalten sind. 
 	 * 		pRaceDistribution muss die Form ((, raceName), (cultureName, probability, ...), ...) haben.
 	 * 
 	 * @param pRaceDistribution
 	 * @throws Exception
 	 */
 	public void setRaceDistributions(ArrayList<ArrayList<Object[]>> pRaceDistribution) throws Exception{
 		setDistributions(pRaceDistribution, false);
 	}
 	
 	//----------------------------------------------------------------------------------------------------
 	
 	/**	Dh	13.08.2023
	 * 
	 * 	Fuegt ein PrioElement mit den pName und pPriority in den quirkElements ein.
	 * 
	 * 	pName darf nicht null sein und pPriority muss groessergleich 0 sein.
 	 * 
 	 * @param pName
 	 * @param pPriority
 	 * @throws Exception
 	 */
	public void addQuirkElement(String pName, int pPriority) throws Exception{
		addElementToElementList(pName, pPriority, quirks, 1);
	}
 	//-----
 	/**	Dh	27.02.2021
 	 * 
 	 * 	Fuegt pCulture in die cultureList von pack ein.
 	 * 
 	 * 	pCulture darf nicht null sein und darf keine ID beseitzt die bereits in der
 	 * 		der cultureList vorhanden ist.
 	 * 
 	 * @param pCulture
 	 * @throws Exception
 	 */
 	public void addCulture(Culture pCulture) throws Exception{
 		pack.addCulture(pCulture);
 	}
 	/**	Dh	27.02.2021
 	 * 
 	 * 	Fuegt pRace in die raceList von pack ein.
 	 * 
 	 * 	pRace darf nicht null sein und darf keine ID beseitzt die bereits in der
 	 * 		der raceList vorhanden ist.
 	 * 
 	 * @param pRace
 	 * @throws Exception
 	 */
 	public void addRace(Race pRace) throws Exception {
 		pack.addRace(pRace);
 	}
 	
 	/**	Dh	13.08.2023
	 * 
	 * 	Entfernt die zur pQuirkElementID gehoerende Eigenschaft aus den quirkElements.
	 * 
	 * 	pQuirkElementID muss groessergleich 0 sein und in quirkElementList vorhanden sein.
 	 * 
 	 * @param pQuirkElementID
 	 * @throws Exception
 	 */
	public void removeQuirkElement(int pQuirkElementID) throws Exception{
		removeElementFromElementList(pQuirkElementID, quirks);
	}
 	
 	/**	Dh	27.02.2021
 	 * 
 	 * 	pCultureID muss groessergleich null sein und in der cultureList davon pack sein.
 	 * 
 	 * @param pCultureID
 	 * @throws Exception
 	 */
 	public void removeCulture(int pCultureID) throws Exception{
 		pack.removeCulture(pCultureID);
 	}
 	/**	Dh	27.02.2021
 	 * 
 	 * 	pRaceID muss groessergleich null sein und in der raceList davon pack sein.
 	 * 
 	 * @param pRaceID
 	 * @throws Exception
 	 */
 	public void removeRace(int pRaceID) throws Exception{
 		pack.removeRace(pRaceID);
 	}
 	
//--------------------------------------------------------------------------------------------------------
 	
 	/**	Dh	13.08.2023
 	 * 
 	 * 	Erstellt ein neues CultureEditor Objekt, gibt dieses zurueck und erstellt eine neues Culture Element.
 	 * 		Bestimmt auﬂerdem ein neue ID fuer das Culture Element.
 	 * 
 	 * @return
 	 * @throws Exception
 	 */
 	public CultureEditor addCulture() throws Exception{
 		return new CultureEditor(new Culture(genNewIDFromIDElementList(pack.getCultures()), ""), this);
 	}
 	/**	Dh	13.08.2023
 	 * 
 	 * 	Erstellt ein neues RaceEditor Objekt, gibt dieses zurueck und erstellt eine neues Race Element.
 	 * 		Bestimmt auﬂerdem ein neue ID fuer das Race Element.
 	 * 
 	 * @return
 	 * @throws Exception
 	 */
 	public RaceEditor addRace() throws Exception {
 		return new RaceEditor(new Race(genNewIDFromIDElementList(pack.getRaces()),  ""), this);
 	}
 	
 	/**	Dh	27.02.2021
 	 * 
 	 * 	Erstellt ein neues CultureEditor Objekt, gibt dieses zurueck und sucht das zur pCultureID gehoerenden
 	 * 		Culture Objekt aus der cultureList von pack.
 	 * 
 	 * 	pCultureID muss groessergleich 0 sein und in der cultureList von pack vorhanden sein.
 	 * 
 	 * @param pCultureID
 	 * @return
 	 * @throws Exception
 	 */
 	public CultureEditor editCulture(int pCultureID) throws Exception {
 		return new CultureEditor(pack.getCulture(pCultureID), this);
 	}
 	/**	Dh	27.02.2021
 	 * 
 	 * 	Erstellt ein neues RaceEditor Objekt, gibt dieses zurueck und sucht das zur pRaceID gehoerenden
 	 * 		Race Objekt aus der raceList von pack.
 	 * 
 	 * 	pRaceID muss groessergleich 0 sein und in der raceList von pack vorhanden sein.
 	 * 
 	 * @param pRaceID
 	 * @return
 	 * @throws Exception
 	 */
 	public RaceEditor editRace(int pRaceID) throws Exception {
 		return new RaceEditor(pack.getRace(pRaceID), this);
 	}
 	
 	//----------------------------------------------------------------------------------------------------
 	
 	/**	Dh	07.03.2021
 	 * 
 	 * @throws Exception
 	 */
 	public void save() throws Exception {
 		DatabaseConnector.savePack(pack);
 	}
 	/**	Dh	09.03.2021
 	 * 
 	 * @throws Exception
 	 */
 	public void add() throws Exception{
 		DatabaseConnector.addPack(pack);
 	}
 	/**	Dh	09.03.2021
 	 * 
 	 * @throws Exception
 	 */
 	public void delete() throws Exception {
 		DatabaseConnector.removePack(pack.getId());
 		pack = null;
 	}
 	/**	Dh	09.03.2021
 	 * 
 	 * @throws Exception
 	 */
 	public void back() throws Exception{
 		DatabaseConnector.resetPack(pack.getId());
 	}
 	
//--------------------------------------------------------------------------------------------------------

 	/**	Dh	14.08.2023
 	 * 
 	 * 	Macht Stuff bla, bla, bla, ....
 	 * 
 	 * @param pIDElementList
 	 * @param pObjectArray
 	 * @param pFirstList
 	 */
 	private void fillArrayWithExistingIDs(ArrayList<? extends IDElement> pIDElementList, Object[][] pObjectArray, boolean pFirstList) {
 		for (IDElement vCur : pIDElementList) {
 			if (pFirstList) pObjectArray[ vCur.getId() + 1 ][0] = 1;
 			else pObjectArray[0][ vCur.getId() + 1 ] = 1;
 		}
 	}
 	/**	Dh	14.08.2023
 	 * 
 	 * 	Fuellt das pObjectArray mit den possibilities der abhaengingen ProbElementListe von pCultureList.
 	 * 		Abhaengig von pIsCultureProbabiolity wird die OriginCultureList oder die RaceList geladen.
 	 * 		Alle leeren nicht Anfangsfelder werden mit 0 aufgefuellt.
 	 * 
 	 * @param pCultureList
 	 * @param pObjectArray
 	 * @param pIsCultureProbability
 	 * @throws Exception
 	 */
 	private void fillArrayWithProbabilities(ArrayList<Culture> pCultureList, Object[][] pObjectArray, boolean pIsCultureProbability) throws Exception{
 		int vID;
 		ArrayList<ProbElement> vProbElementList;
 		
 		for (int i=1; i < pObjectArray.length; i++) {
 			for (int u=1; u < pObjectArray[i].length; u++) {
 				pObjectArray[i][u] = 0.0;
 			}
 		}
 		
 		for (Culture vCur : pCultureList) {
 			vID = vCur.getId();
			if (pIsCultureProbability == true) vProbElementList = vCur.getOriginCultureList().getProbElements();
			else vProbElementList = vCur.getRaceList().getProbElements();
			
			for (ProbElement vTemp : vProbElementList) {
				pObjectArray[vID + 1][vTemp.getId() + 1] = vTemp.getProbability();
			}
 		}
 	}
 	/**	Dh	14.08.2023
 	 * 
 	 * 	Fuellt die pFillList mit den Probabilities von pObjectArray und den Title von pCultureList.
 	 * 		pCulture List muss schon eine Liste fuer die GUI sein.
 	 * 
 	 * @param pFillList
 	 * @param pCultureList
 	 * @param pObjectArray
 	 */
 	private void fillListWithArrayPlusTitle(ArrayList<ArrayList<Object[]>> pFillList, ArrayList<Object[]> pCultureList, Object[][] pObjectArray) {
 		int vID;
 		ArrayList<Object[]> vCurList;
 		
 		for (int i=0; i<pCultureList.size(); i++) {
 			vID = (int) (pCultureList.get(i)[0]);
 			
 			vCurList = new ArrayList<Object[]>();
 			vCurList.add(pCultureList.get(i));
 			
 			for (int u=0; u < pObjectArray[i].length; u++) {
 				vCurList.add(new Object[] {vID, pObjectArray[i][u]});
 			}
 			
 			pFillList.add(vCurList);
 		}
 	}
 	//-----
 	/**	Dh	05.03.2021
 	 * 
 	 * 	Reduziert pObjectArray um alle nicht vorhanden ID Zeilen und Spalten.
 	 * 
 	 * @param pObjectArray
 	 * @return
 	 */
 	private Object[][] reduceArrayToOnlyExistingIDs(Object[][] pObjectArray) {
 		int ii, uu, vRedLen1, vRedLen2, vNonExistingIDNumber1, vNonExistingIDNumber2;
 		Object vRet[][];
 		
 		vNonExistingIDNumber1 = 0;
 		vNonExistingIDNumber2 = 0;
 		
 		for (int i=0; i < pObjectArray.length; i++) {
 			if (pObjectArray[i][0] == null) vNonExistingIDNumber1 ++;
 		}
 		for (int i=0; i < pObjectArray[0].length; i++) {
 			if (pObjectArray[0][i] == null) vNonExistingIDNumber2 ++;
 		}
 		
 		vRedLen1 = pObjectArray.length - vNonExistingIDNumber1;
 		vRedLen2 = pObjectArray[0].length - vNonExistingIDNumber2;
 		
 		vRet = new Object[vRedLen1][vRedLen2];
 		
 		ii = 0;
 		for (int i=1;  i < pObjectArray.length; i++) {
 			uu = 0;
 			if (pObjectArray[i][0] != null) {
 				for (int u=1; u < pObjectArray[i].length; u++) {
 	 				if (pObjectArray[0][u] != null) {
 	 					vRet[ii][uu] = pObjectArray[i][u];
 	 					uu ++;
 	 				}
 	 			}
 				ii ++;
 			}
 		}
 		
 		return vRet;
 	}
 	
 	/**	Dh	14.08.2023
 	 * 
 	 * 	Generiert eine Liste mit den Verteilungswahrscheinlichkeiten aus der pCultureList und der pIDElemenList.
 	 * 		Je nach pIsCultureProbability werden die OriginCulture oder die Race List geladen, das muss mit der pIDElemenList matchen.
 	 * 
 	 * @param pCultureList
 	 * @param pIDElementList2
 	 * @param pIsCultureProbability
 	 * @return
 	 * @throws Exception
 	 */
 	private ArrayList<ArrayList<Object[]>> genTwoListDistribution(ArrayList<Culture> pCultureList, ArrayList<? extends IDElement> pIDElementList, boolean pIsCultureProbability) throws Exception{
 		Object[][] vObArray;
 		int vLen1, vLen2;
 		ArrayList<Object[]> vTemp;
 		ArrayList<ArrayList<Object[]>> vRet;
 		
 		vRet = new ArrayList<ArrayList<Object[]>>();
 		
 		if ((pCultureList != null) && (pIDElementList != null)) { 			
 			if (pCultureList.size() > 0) vLen1 = pCultureList.get(pCultureList.size()-1).getId() + 2;
 			else vLen1 = 2;
 			if (pIDElementList.size() > 0) vLen2 = pIDElementList.get(pIDElementList.size()-1).getId() + 2;
 			else vLen2 = 2;
 			
 			vObArray = new Object[vLen1][vLen2];
 			
 			fillArrayWithExistingIDs(pCultureList, vObArray, true);
 			fillArrayWithExistingIDs(pIDElementList, vObArray, false);
 					
 			fillArrayWithProbabilities(pCultureList, vObArray, pIsCultureProbability);
 			
 			vObArray = reduceArrayToOnlyExistingIDs(vObArray);
 			
 			vTemp = genObjectArrayListFromIDElementList(pIDElementList);
 			
 			vTemp.add(new Object[] {-1, ""});
 			vTemp.sort((pObjectArray1, pObjectArray2) -> {
 				return ((int) pObjectArray1[0]) - ((int)pObjectArray2[0]);
 			});
 			vRet.add(vTemp);
 			
 			fillListWithArrayPlusTitle(vRet, genObjectArrayListFromIDElementList(pCultureList), vObArray);
 		}else throw new Exception("04; gTLD,PaEdi");
 		
 		return vRet;
 	}
 	
 	//----------------------------------------------------------------------------------------------------
 	
 	/**	Dh	14.08.2023
 	 * 
 	 * 	Macht aus einer Wahrcheinlichkeitsverteilungs List eine ProbElementList und erstllt die entsprechenden ProbElements.
 	 * 
 	 * @param pList
 	 * @param pIDElementNameList
 	 * @return
 	 */
 	private ArrayList<ProbElement> transformListToProbElementList(ArrayList<Object[]> pList, ArrayList<Object[]> pIDElementNameList) {
 		Object[] vCur, vCurNameElement;
 		ArrayList<ProbElement> vRet = new ArrayList<ProbElement>();
 		
 		for (int i=1; i<pIDElementNameList.size(); i++) {
 			vCur = pList.get(i);
 			
 			if (((double)vCur[1]) != 0) {
 				vCurNameElement = pIDElementNameList.get(i);
 				
 				vRet.add(new ProbElement((int)vCurNameElement[0], (String) vCurNameElement[1], (double) vCur[1]));
 			}
 		}
 		
 		return vRet;
 	}
 	
 	/**	Dh	14.08.2023
 	 * 
 	 * 	Fuegt die durch pIsOriginCulture definiert Wahrscheinlichkeitsverteilung von pList den jeweiligen Kulturen hinzu.
 	 * 
 	 * @param pList
 	 * @param pIsOriginCulture
 	 * @throws Exception
 	 */
 	private void setDistributions(ArrayList<ArrayList<Object[]>> pList, boolean pIsOriginCulture) throws Exception{
 		Culture vCurCult;
 		ArrayList<Object[]> vIDElementList, vCurList;
 		
 		if (pList != null) {
 			vIDElementList = pList.get(0);
 			
 			for (int i=1; i<pList.size(); i++) {
 				vCurList = pList.get(i);
 				
 				vCurCult = pack.getCulture( (int)((Object[])vCurList.get(0))[0] );
 				
 				if (pIsOriginCulture == true) vCurCult.setOriginCultureList( new ProbList(transformListToProbElementList(vCurList, vIDElementList)));
 				else vCurCult.setRaceList( new ProbList(transformListToProbElementList(vCurList, vIDElementList)));
 			}
 		}else throw new Exception("04; sDi,PaEdi");
 		
 	}
 	
}
