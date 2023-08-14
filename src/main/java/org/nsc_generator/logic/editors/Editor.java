/**	NSC_Generator v0.2		Dh	13.08.2023
 * 	
 * 	logic.editors
 * 	  Editor
 * 
 * 	pElementType:
 * 		0:	ProbElement
 * 		1:	PrioElement
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
import org.nsc_generator.logic.pack.GenElement;
import org.nsc_generator.logic.pack.PrioElement;
import org.nsc_generator.logic.pack.ProbElement;

public abstract class Editor {

	public Editor() {
		
	}

//--------------------------------------------------------------------------------------------------------

	/**	Dh	13.08.2023
	 * 
	 * 	pID muss groessergleich 0 sein und in pElementList enthalten sein, pElementList darf nicht null sein.
	 * 		Auﬂerdem muss das gesuchte Element vom Type ProbElement oder PrioElement sein.
	 * 
	 * @param pID
	 * @param pElementList
	 * @param pElementType
	 * @return
	 * @throws Exception
	 */
	protected Object[] getElementFromElementListAsArray(int pID, ArrayList<? extends IDElement> pElementList) throws Exception{
		IDElement vCur;
		Object[] vRet = new Object[3];
		
		if (pID >= 0) {
			for (int i=0; (i<pElementList.size()) && (pID >= 0); i++) {
				vCur = (IDElement) pElementList.get(i);
				
				if (vCur.getId() == pID) {
					if (vCur instanceof ProbElement) vRet = transformProbElementToList((ProbElement)vCur);
					else if (vCur instanceof PrioElement) vRet = transformPrioElementToList((PrioElement)vCur);
					else throw new Exception("06, gEfELaA,Edi");
					
					pID = -1;
				}
			}
		} else throw new Exception("02; gEfELaA,Edi");
		
		return vRet;
	}
	
	/**	Dh	14.03.2021
	 * 
	 * 	pName darf nicht null sein unt pProbalility muss zwischen 0 und 100 liegen.
	 * 
	 * @param pProbElement
	 * @param pName
	 * @param pProbability
	 * @throws Exception
	 */
	private void setProbElement(ProbElement pProbElement, String pName, double pProbability) throws Exception {
		if (pProbElement != null) {
			pProbElement.setName(pName);
			pProbElement.setProbability(pProbability);
		} else throw new Exception("04; sProE,Edi");
	}
	/**	Dh	14.03.2021
	 * 
	 * 	pName darf nicht null sein unt pPriority muss groessergleich 0 sein.
	 * 
	 * @param pPrioElement
	 * @param pName
	 * @param pPriority
	 * @throws Exception
	 */
	private void setPrioElement(PrioElement pPrioElement, String pName, int pPriority) throws Exception {
		if (pPrioElement != null) {
			pPrioElement.setName(pName);
			pPrioElement.setPriority(pPriority);
		} else throw new Exception("04; sPriE,Edi");
	}
	//-----
	/**	Dh	12.08.2023
	 * 
	 * 	pID und pValue muessen groessergleich 0 sein, und pName und pElementList duerfen nicht null sein.
	 * 		Auﬂerdem muss pID in pElementList vorhanden sein.
	 * 
	 * 	pElementType:
	 * 		0:	ProbElement
	 * 		1:	PrioElement
	 * 
	 * @param pID
	 * @param pName
	 * @param pProbability
	 * @param pProbElementList
	 * @throws Exception
	 */
	protected <T extends IDElement> void setElementFromElementList(int pID, String pName, Object pValue, ArrayList<T> pElementList, int pElementType) throws Exception{
		T vCur;
		
		if (pElementList != null) {
			if (pID >= 0) {
				for (int i=0; (i<pElementList.size()) && (pID >= 0); i++) {
					vCur =  pElementList.get(i);
					
					if (vCur.getId() == pID) {
						switch(pElementType) {
						case 0:
							if ((vCur instanceof ProbElement) && (pValue instanceof Double)) setProbElement((ProbElement) vCur, pName, (double) pValue);
							else throw new Exception("06a; sEfEL,Edi");
							
							break;
						case 1:
							if ((vCur instanceof PrioElement) && (pValue instanceof Integer)) setPrioElement((PrioElement) vCur, pName, (int) pValue);
							else throw new Exception("06b; sEfEL,Edi");
							
							break;
						default:
							throw new Exception("02b; sEfEL,Edi");
						}
						
						pID = -1;
					}
				}
			}else throw new Exception("02a; sEfEL,Edi");
		} else throw new Exception("04; sEfEL,Edi");
	}
	
	/**	Dh	13.08.2023
	 * 
	 * 	pElementList darf nicht null sein, und muss nach IDs sortiert sein.
	 * 
	 * 	pElementType:
	 * 		0:	ProbElement
	 * 		1:	PrioElement
	 * 
	 * @param pName
	 * @param pProbability
	 * @param pProbElementList
	 * @throws Exception
	 */
	protected <T extends IDElement>  void addElementToElementList(String pName, Object pValue, ArrayList<T> pElementList, int pElementType) throws Exception {
		int vID;
		T vAdd;
		
		if (pElementList != null) {
			vID = genNewIDFromIDElementList(pElementList);	
			
			switch(pElementType) {
			case 0:
				if (pValue instanceof Double) vAdd = (T)new ProbElement(vID, pName, (double)pValue);
				else throw new Exception("06a; aEtEL,Edi");
				break;
			case 1:
				if (pValue instanceof Integer) vAdd = (T)new PrioElement(vID, pName, (int)pValue);
				else throw new Exception("06b; aEtEL,Edi");
				break;
			default:
				throw new Exception("02b; aEtEL,Edi");
			}
			
			if (pElementList.size() != 0) {
				if (isIDInIDElementList(vID, pElementList)) throw new Exception("02a; aEtEL,Edi");
			}else pElementList.add(vAdd);
			
			pElementList.sort((pObj1, pObj2) -> {
				return pObj1.getId() - pObj2.getId();
			});
		} else throw new Exception("04; aEtEL,Edi");
	}
	
	/**	Dh	13.08.2023
	 * 
	 * 	pElementList darf nicht nuzll sein, pID muss groessergleich 0 sein und in pElemntList vorhanden.
	 * 
	 * @param pID
	 * @param pProbElementList
	 * @throws Exception
	 */
	protected void removeElementFromElementList(int pID, ArrayList<? extends IDElement> pElementList) throws Exception{
		if (pElementList != null) {
			if (pID >= 0) {
				for (int i=0; i<pElementList.size(); i++) {
					if (pElementList.get(i).getId() == pID) {
						pElementList.remove(i);
						i--;
					}
				}
			}else throw new Exception("02; rEfEL,Edi");
		} else throw new Exception("04; rEfEL,Edi");
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	13.08.2023
	 * 
	 * 	Generiert eine neue ID, die noch nicht in der angegebenen pIDElementList vorkommt.
	 * 		Dazu muss die pIDElement nach ihren IDs geordnet sein.
	 * 
	 * 	pIDElementList darf nicht null sein.
	 * 
	 * @param pIDElementList
	 * @return
	 * @throws Exception
	 */
	protected int genNewIDFromIDElementList(ArrayList<? extends IDElement> pIDElementList) throws Exception {
		return DatabaseConnector.genNewIDFromIDElementList(pIDElementList);
	}
	
	/**	Dh	13.08.2023
	 * 
	 * 	Erzeugt eine List von Objekt Array (id, name) der pIDElementList.
	 * 
	 * 	pIDElementList darf nicht null sein und muss aus IDElement Objekten bestehen.
	 * 
	 * @param pIDElementList
	 * @return
	 * @throws Exception
	 */
	protected ArrayList<Object[]> genObjectArrayListFromIDElementList(ArrayList<? extends IDElement> pIDElementList) throws Exception{
		Object[] vElement;
		ArrayList<Object[]> vRet;
 		
 		if (pIDElementList != null) {
 			vRet = new ArrayList<Object[]>();
 			
 			for (IDElement vCur : pIDElementList) {
 				vElement = new Object[2];
 	 			
 	 			vElement[0] = vCur.getId();
 	 			vElement[1] = vCur.getName();
 	 			
 	 			vRet.add(vElement);
 			}
 		} else throw new Exception("04; gOALfIDEL,Edi");
 		
 		return vRet;
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	27.02.2021
	 * 
	 * 	pGenElement darf nicht null sein.
	 * 
	 * @param pGenElement
	 * @return
	 * @throws Exception
	 */
	protected int[] transformGenElementToList(GenElement pGenElement) throws Exception {
		int[] vRet = new int[3];
		
		if (pGenElement != null) {
			vRet[0] = pGenElement.getNumber();
			vRet[1] = pGenElement.getSide();
			vRet[2] = pGenElement.getOffset();
		}else throw new Exception("04; tGEtL,Edi");
		
		return vRet;
	}
	/**	Dh	27.02.2021
	 * 
	 * 	pProbElement darf nicht null sein.
	 * 
	 * @param pProbElement
	 * @return
	 * @throws Exception
	 */
	private Object[] transformProbElementToList(ProbElement pProbElement) throws Exception{
		Object[] vRet = new Object[3];
		
		if (pProbElement != null) {
			vRet[0] = pProbElement.getId();
			vRet[1] = pProbElement.getName();
			vRet[2] = pProbElement.getProbability();
		} else throw new Exception("04; tPEtL,Edi");
		
		return vRet;
	}
	/**	Dh	14.03.2021
	 * 
	 * 	pPrioElement darf nicht null sein.
	 * 
	 * @param pPrioElement
	 * @return
	 * @throws Exception
	 */
	private Object[] transformPrioElementToList(PrioElement pPrioElement) throws Exception{
		Object[] vRet = new Object[3];
		
		if (pPrioElement != null) {
			vRet[0] = pPrioElement.getId();
			vRet[1] = pPrioElement.getName();
			vRet[2] = pPrioElement.getPriority();
		} else throw new Exception("04; tPriEtL,Edi");
		
		return vRet;
	}
	
	/**	Dh	13.08.2023
	 * 
	 * 	pElementList darf nicht null sein und pElementType muss zwischen 0 und 1 sein.
	 * 
	 * 	pElementType:
	 * 		0:	ProbElement
	 * 		1:	PrioElement
	 * 
	 * @param pElementList
	 * @param pElementType
	 * @return
	 * @throws Exception
	 */
	private <T extends IDElement> ArrayList<Object[]> transformElementListToList(ArrayList<T> pElementList, int pElementType) throws Exception{
		ArrayList<Object[]> vRet = new ArrayList<Object[]>();
		
		if (pElementList!= null) {
			for (T vCur : pElementList) {
				switch(pElementType) {
				case 0:
					vRet.add(transformProbElementToList((ProbElement)vCur));
					
					break;
				case 1:
					vRet.add(transformPrioElementToList((PrioElement)vCur));
					
					break;
				default:
					throw new Exception("02; tELtL,Edi");
				}
			}
		}else throw new Exception("04; tELtL,Edi");
		
		return vRet;
	}
	//-----
	/**	Dh	13.08.2023
	 * 
	 * 	pProbElementList darf nicht null sein.
	 * 
	 * @param pProbElementList
	 * @return
	 * @throws Exception
	 */
	protected ArrayList<Object[]> transformProbElementListToList(ArrayList<ProbElement> pProbElementList) throws Exception{
		return transformElementListToList(pProbElementList, 0);
	}
	/**	Dh	13.08.2023
	 * 
	 * 	pPrioElementList darf nicht null sein.
	 * 
	 * @param pPrioElementList
	 * @return
	 * @throws Exception
	 */
	protected ArrayList<Object[]> transformPrioElementListToList(ArrayList<PrioElement> pPrioElementList) throws Exception{
		return transformElementListToList(pPrioElementList, 1);
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	13.08.2023
	 * 
	 * @param pID
	 * @param pIDElementList
	 * @return
	 * @throws Exception
	 */
	protected boolean isIDInIDElementList(int pID, ArrayList<? extends IDElement> pIDElementList) throws Exception {
		boolean vRet = false;
		
		for (int i=0; (i<pIDElementList.size()) && (vRet == false); i++) {
			if (pIDElementList.get(i).getId() == pID) vRet = true;
		}
		
		return vRet;
	}
	
}
