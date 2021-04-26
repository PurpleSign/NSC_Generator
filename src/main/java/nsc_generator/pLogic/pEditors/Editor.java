/**	NSC_Generator v0.0		Dh	15.03.2021
 * 	
 * 	pLogic.pEditors
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

package nsc_generator.pLogic.pEditors;

import pDataStructures.List;
import nsc_generator.pLogic.DatabaseConnector;
import nsc_generator.pLogic.IDElement;
import nsc_generator.pLogic.pPack.GenElement;
import nsc_generator.pLogic.pPack.PrioElement;
import nsc_generator.pLogic.pPack.ProbElement;

public abstract class Editor {

	public Editor() {
		
	}

//--------------------------------------------------------------------------------------------------------

	/**	Dh	14.03.2021
	 * 
	 * 	pID muss groessergleich 0 sein und in pElementList enthalten sein, pElementList darf nicht null sein.
	 * 		Außerdem muss das gesuchte Element vom Type ProbElement oder PrioElement sein.
	 * 
	 * @param pID
	 * @param pElementList
	 * @param pElementType
	 * @return
	 * @throws Exception
	 */
	protected Object[] getElementFromElementListAsArray(int pID, List pElementList) throws Exception{
		IDElement vCur;
		Object[] vRet = new Object[3];
		
		if (pID >= 0) {
			pElementList.toFirst();
			while(!pElementList.isEnd() && (pID >= 0)) {
				vCur = (IDElement) pElementList.getCurrent();
				
				if (vCur.getId() == pID) {
					if (vCur instanceof ProbElement) vRet = transformProbElementToList((ProbElement)vCur);
					else if (vCur instanceof PrioElement) vRet = transformPrioElementToList((PrioElement)vCur);
					else throw new Exception("06, gEfELaA,Edi");
					
					pID = -1;
				}
				
				pElementList.next();
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
	/**	Dh	15.03.2021
	 * 
	 * 	pID und pValue muessen groessergleich 0 sein, und pName und pElementList duerfen nicht null sein.
	 * 		Außerdem muss pID in pElementList vorhanden sein.
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
	protected void setElementFromElementList(int pID, String pName, Object pValue, List pElementList, int pElementType) throws Exception{
		IDElement vCur;
		
		if (pElementList != null) {
			if (pID >= 0) {
				pElementList.toFirst();
				while(!pElementList.isEnd() && (pID >= 0)) {
					vCur = (IDElement) pElementList.getCurrent();
					
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
					
					pElementList.next();
				}
			}else throw new Exception("02a; sEfEL,Edi");
		} else throw new Exception("04; sEfEL,Edi");
	}
	
	/**	Dh	15.03.2021
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
	protected void addElementToElementList(String pName, Object pValue, List pElementList, int pElementType) throws Exception {
		int vID;
		IDElement vCur, vAdd;
		
		if (pElementList != null) {
			vID = genNewIDFromIDElementList(pElementList);	
			
			switch(pElementType) {
			case 0:
				if (pValue instanceof Double) vAdd = new ProbElement(vID, pName, (double)pValue);
				else throw new Exception("06a; aEtEL,Edi");
				break;
			case 1:
				if (pValue instanceof Integer) vAdd = new PrioElement(vID, pName, (int)pValue);
				else throw new Exception("06b; aEtEL,Edi");
				break;
			default:
				throw new Exception("02b; aEtEL,Edi");
			}
			
			if (pElementList.getContentNumber() != 0) {
				pElementList.toFirst();
				while(!pElementList.isEnd()) {
					vCur = (IDElement) pElementList.getCurrent();
						
					if (vCur.getId() == vID) throw new Exception("02a; aEtEL,Edi");
					else if (vCur.getId() > vID) {
						pElementList.insert(vAdd);
						
						pElementList.toLast();
					} else if (pElementList.isLast()) {
						pElementList.append(vAdd);
						pElementList.toLast();
					}
					
					pElementList.next();
				}
			} else pElementList.append(vAdd);
		} else throw new Exception("04; aEtEL,Edi");
	}
	
	/**	Dh	15.03.2021
	 * 
	 * 	pElementList darf nicht nuzll sein, pID muss groessergleich 0 sein und in pElemntList vorhanden.
	 * 
	 * @param pID
	 * @param pProbElementList
	 * @throws Exception
	 */
	protected void removeElementFromElementList(int pID, List pElementList) throws Exception{
		IDElement vCur;
		
		if (pElementList != null) {
			if (pID >= 0) {
				pElementList.toFirst();
				while(!pElementList.isEnd()) {
					vCur = (IDElement) pElementList.getCurrent();
					
					if (vCur.getId() == pID) {
						pElementList.remove();
						pElementList.toLast();
					}
					
					pElementList.next();
				}
			}else throw new Exception("02; rEfEL,Edi");
		} else throw new Exception("04; rEfEL,Edi");
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	09.03.2021
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
	protected int genNewIDFromIDElementList(List pIDElementList) throws Exception {
		return DatabaseConnector.genNewIDFromIDElementList(pIDElementList);
	}
	
	/**	Dh	27.02.2021
	 * 
	 * 	Erzeugt eine List von Objekt Array (id, name) der pIDElementList.
	 * 
	 * 	pIDElementList darf nicht null sein und muss aus IDElement Objekten bestehen.
	 * 
	 * @param pIDElementList
	 * @return
	 * @throws Exception
	 */
	protected List genObjectArrayListFromIDElementList(List pIDElementList) throws Exception{
		Object[] vElement;
		List vRet;
 		IDElement vCur;
 		
 		if (pIDElementList != null) {
 			vRet = new List();
 			
 			pIDElementList.toFirst();
 	 		while(!pIDElementList.isEnd()) {
 	 			vCur = (IDElement) pIDElementList.getCurrent();
 	 			vElement = new Object[2];
 	 			
 	 			vElement[0] = vCur.getId();
 	 			vElement[1] = vCur.getName();
 	 			
 	 			vRet.append(vElement);
 	 			pIDElementList.next();
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
	
	/**	Dh	14.03.2021
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
	private List transformElementListToList(List pElementList, int pElementType) throws Exception{
		List vRet = new List();
		
		if (pElementList!= null) {
			pElementList.toFirst();
			while(!pElementList.isEnd()) {
				switch(pElementType) {
				case 0:
					vRet.append(transformProbElementToList((ProbElement)pElementList.getCurrent()));
					
					break;
				case 1:
					vRet.append(transformPrioElementToList((PrioElement)pElementList.getCurrent()));
					
					break;
				default:
					throw new Exception("02; tELtL,Edi");
				}
				
				pElementList.next();
			}
		}else throw new Exception("04; tELtL,Edi");
		
		return vRet;
	}
	//-----
	/**	Dh	14.03.2021
	 * 
	 * 	pProbElementList darf nicht null sein.
	 * 
	 * @param pProbElementList
	 * @return
	 * @throws Exception
	 */
	protected List transformProbElementListToList(List pProbElementList) throws Exception{
		return transformElementListToList(pProbElementList, 0);
	}
	/**	Dh	14.03.2021
	 * 
	 * 	pPrioElementList darf nicht null sein.
	 * 
	 * @param pPrioElementList
	 * @return
	 * @throws Exception
	 */
	protected List transformPrioElementListToList(List pPrioElementList) throws Exception{
		return transformElementListToList(pPrioElementList, 1);
	}
	
}
