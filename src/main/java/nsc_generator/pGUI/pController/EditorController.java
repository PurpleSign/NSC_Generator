/**	NSC_Generator v0.0		Dh	19.04.2021
 * 	
 * 	pGUI.pController
 * 	  EditorController
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

package nsc_generator.pGUI.pController;

import java.io.File;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import pDataStructures.List;

import nsc_generator.pGUI.pTableElement.NameElement;
import nsc_generator.pGUI.pTableElement.PrioElementTableElement;
import nsc_generator.pGUI.pTableElement.ProbElementTableElement;
import nsc_generator.pGUI.pTableElement.TableRowModel;
import nsc_generator.pLogic.pEditors.Editor;

public abstract class EditorController {
	protected boolean isEdit;
	protected ParentStageControllerInterface parentController;
	
	/**	Dh	02.03.2021
	 * 
	 */
	public EditorController() {
		super();
	}
	
	/**	Dh	04.03.2021
	 * 
	 * @throws Exception
	 */
	public void setUp(boolean pIsEdit, ParentStageControllerInterface pParentController, Editor pEditor) throws Exception{
		isEdit = pIsEdit;
		
		parentController = pParentController;
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	02.03.2021
	 * 
	 */
	@FXML
	protected abstract void apply();
	
	/**	Dh	02.03.2021
	 * 
	 */
	@FXML
	protected abstract void delete();
	
	/**	Dh	02.03.2021
	 * 
	 */
	@FXML
	public abstract void back();
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	05.03.2021
	 * 
	 * @param pNumber
	 * @param pSide
	 * @param pOffset
	 * @return
	 */
	protected int[] preparingGenElementFieldsForInput(String pNumber, String pSide, String pOffset) {
		int[] vRet = new int[3];
		
		if (!pNumber.equals("")) vRet[0] = Integer.parseInt(pNumber);
		else vRet[0] = 0;
		
		if (!pSide.equals("")) vRet[1] = Integer.parseInt(pSide);
		else vRet[1] = 0;
		
		if (!pOffset.equals("")) vRet[2] = Integer.parseInt(pOffset);
		else vRet[2] = 0;
		
		return vRet;
	}
	
	/**	Dh	07.03.2021
	 * 
	 * @param pTable
	 * @return
	 * @throws Exception
	 */
	protected List getDistributionsListFromTables(TableView<TableRowModel> pTable) throws Exception {
		List vRet, vColumnList;
		TableRowModel vCur;
		ObservableList<TableRowModel> vRowLists;
		
		if (pTable != null) {
			vRet = new List();
			vRowLists = pTable.getItems();
			
			for (int i=0; i<pTable.getColumns().size() ; i++) {
				vColumnList = new List();
				
				for (int u=-1; u<vRowLists.size(); u++) {
					if (u == -1) {
						vCur = vRowLists.get(u+1);
						
						if (i == 0) vColumnList.append(new Object[] { null, null });
						else vColumnList.append(new Object[] {  vCur.getID(i-1), "" });
					}else {
						vCur = vRowLists.get(u);
						
						if (i == 0) vColumnList.append(new Object[] { vCur.getDistroElementID(), vCur.getDistroElementName() });
						else vColumnList.append(new Object[] { vCur.getID(i-1), vCur.getDistroProb(i-1) });
					}
				}
				
				vRet.append(vColumnList);
			}
		} else throw new Exception("04; gDLfT,EdCon");
		
		return vRet;
	}
	
	/**	Dh	04.03.2021
	 * 
	 * @param pObjectArray
	 * @return
	 * @throws Exception
	 */
	protected NameElement convertToNameElement(Object[] pObjectArray) throws Exception {
		NameElement vRet;
		int vID;
		String vName;
		
		if (pObjectArray != null) {
			if (pObjectArray.length == 2) {
				if (pObjectArray[0] instanceof Integer) vID = (int) pObjectArray[0];
				else throw new Exception("06a; ctNE,EdCon");
				
				if (pObjectArray[1] instanceof String) vName = (String) pObjectArray[1];
				else throw new Exception("06b; ctNE,EdCon");
					
				vRet = new NameElement(vID, vName);
			} else throw new Exception("01; ctNE,EdCon");
		} else throw new Exception("04; ctNE,EdCon");
		
		return vRet;
	}
	
	/**	Dh	04.03.2021
	 * 
	 * @param pObjectArray
	 * @return
	 * @throws Exception
	 */
 	protected ProbElementTableElement convertToProbElementTableElement(Object[] pObjectArray) throws Exception{
		ProbElementTableElement vRet;
		int vID;
		String vName;
		double vProb;
		
		if (pObjectArray != null) {
			if (pObjectArray.length == 3) {
				if (pObjectArray[0] instanceof Integer) vID = (int) pObjectArray[0];
				else throw new Exception("06a; ctPETE,EdCon");
				
				if (pObjectArray[1] instanceof String) vName = (String) pObjectArray[1];
				else throw new Exception("06b; ctPETE,EdCon");
				
				if (pObjectArray[2] instanceof Double) vProb = (double) pObjectArray[2];
				else throw new Exception("06c; ctPETE,EdCon");
					
				vRet = new ProbElementTableElement(vID, vName, vProb);
			} else throw new Exception("01; ctPETE,EdCon");
		} else throw new Exception("04; ctPETE,EdCon");
		
		return vRet;
	}
 	/**	Dh	18.03.2021
 	 * 
 	 * @param pObjectArray
 	 * @return
 	 * @throws Exception
 	 */
 	protected PrioElementTableElement convertToPrioElementTableElement(Object[] pObjectArray) throws Exception{
		PrioElementTableElement vRet;
		int vID;
		String vName;
		int vPrio;
		
		if (pObjectArray != null) {
			if (pObjectArray.length == 3) {
				if (pObjectArray[0] instanceof Integer) vID = (int) pObjectArray[0];
				else throw new Exception("06a; ctPETE,EdCon");
				
				if (pObjectArray[1] instanceof String) vName = (String) pObjectArray[1];
				else throw new Exception("06b; ctPETE,EdCon");
				
				if (pObjectArray[2] instanceof Integer) vPrio = (int) pObjectArray[2];
				else throw new Exception("06c; ctPETE,EdCon");
					
				vRet = new PrioElementTableElement(vID, vName, vPrio);
			} else throw new Exception("01; ctPETE,EdCon");
		} else throw new Exception("04; ctPETE,EdCon");
		
		return vRet;
	}

 	//----------------------------------------------------------------------------------------------------
 	
 	/**	Dh	05.03.2021
 	 * 
 	 * @param pID
 	 * @param pNameElementList
 	 * @return
 	 * @throws Exception
 	 */
 	protected NameElement getNameElementFromNameElementListByID(int pID, ObservableList<NameElement> pNameElementList) throws Exception{
 		NameElement vRet = null;
 		//ListIterator<NameElement> vLiIterator;
 		
 		if (pID >= 0) {
 			if (pNameElementList != null) {
 				for (NameElement vCur : pNameElementList) {
 					if (vCur.getId() == pID) vRet = vCur;
 				}
 			} else throw new Exception("04; gNEfNELibID,EdCon");
 		} else throw new Exception("02; gNEfNELibID,EdCon");
 		
 		return vRet;
 	}
 	
 	/**	Dh	04.03.2021
 	 * 
 	 * @param pGenElementArray
 	 * @param pNumber
 	 * @param pSide
 	 * @param pOffset
 	 * @throws Exception
 	 */
 	protected void updateGenElementTextfields(int[] pGenElementArray, TextField pNumber, TextField pSide, TextField pOffset) throws Exception {
 		if (pGenElementArray != null) {
 			if (pGenElementArray.length == 3) {
 				pNumber.setText("" + pGenElementArray[0]);
 				pSide.setText("" + pGenElementArray[1]);
 				pOffset.setText("" + pGenElementArray[2]);
 			} else throw new Exception("01; uGETF,EdCon");
 		} else throw new Exception("04; uGETF,EdCon");
 	}
 	/**	Dh	04.03.2021
 	 * 
 	 * @param pProbElementList
 	 * @param pTableElementList
 	 * @param pTableView
 	 * @throws Exception
 	 */
 	protected void updateProbElementTableElementList (List pProbElementList, ObservableList<ProbElementTableElement> pTableElementList, 
 			TableView<ProbElementTableElement> pTableView) throws Exception {
 		if (pProbElementList != null) {
 			pTableElementList.clear();
			
			pProbElementList.toFirst();
			while(!pProbElementList.isEnd()) {
				pTableElementList.add( convertToProbElementTableElement((Object[])pProbElementList.getCurrent()) );
				
				pProbElementList.next();
			}
			pTableView.setItems(pTableElementList);
		}else throw new Exception("04; uPETEL,EdiCon");
 	}
 	/**	Dh	15.03.2021
 	 * 
 	 * @param pPrioElementList
 	 * @param pTableElementList
 	 * @param pTableView
 	 * @throws Exception
 	 */
 	protected void updatePrioElementTableElementList (List pPrioElementList, ObservableList<PrioElementTableElement> pTableElementList, 
 			TableView<PrioElementTableElement> pTableView) throws Exception {
 		if (pPrioElementList != null) {
 			pTableElementList.clear();
			
			pPrioElementList.toFirst();
			while(!pPrioElementList.isEnd()) {
				pTableElementList.add( convertToPrioElementTableElement((Object[])pPrioElementList.getCurrent()) );
				
				pPrioElementList.next();
			}
			pTableView.setItems(pTableElementList);
		}else throw new Exception("04; uPETEL,EdiCon");
 	}
 	/**	Dh	10.03.2021
 	 * 
 	 * @param pProbElementList
 	 * @param pTableElementList
 	 * @param pListView
 	 * @throws Exception
 	 */
 	protected void updateNameElementListElementList (List pProbElementList, ObservableList<NameElement> pTableElementList, 
 			ListView<NameElement> pListView) throws Exception {
 		if (pProbElementList != null) {
 			try {pTableElementList.clear();}
 			catch (Exception ex) {}
			
			pProbElementList.toFirst();
			while(!pProbElementList.isEnd()) {
				pTableElementList.add( convertToNameElement((Object[])pProbElementList.getCurrent()) );
				
				pProbElementList.next();
			}
			pListView.setItems(pTableElementList);
		}else throw new Exception("04; uPETEL,EdiCon");
 	}
 	
 	
 	/**	Dh	08.03.2021
 	 * 
 	 * @param pNameElementList
 	 * @param pChoiceBoxList
 	 * @param pChoiceBox
 	 * @throws Exception
 	 */
 	protected void updateChoiceBoxList(List pNameElementList, ObservableList<NameElement> pChoiceBoxList, ChoiceBox<NameElement> pChoiceBox) throws Exception{
 		if (pNameElementList != null) {
 			pChoiceBoxList.clear();
			
			pNameElementList.toFirst();
			while(!pNameElementList.isEnd()) {
				pChoiceBoxList.add( convertToNameElement( (Object[])pNameElementList.getCurrent()) );
				
				pNameElementList.next();
			}
			
			pChoiceBox.setItems(pChoiceBoxList);
		}
 	}
 	//-----
 	/**	Dh	07.03.2021
 	 * 
 	 * @param pDataList
 	 * @param pTable
 	 * @throws Exception
 	 */
 	protected void updateDistributionTable(List pDataList, TableView<TableRowModel> pTable) throws Exception {
 		int i, u;
		
		List vCurColumn;
		ObservableList<TableRowModel> vDataTable;		

		if (pDataList != null) {
			pTable.getItems().clear();
			pTable.getColumns().clear();
			
			vDataTable = FXCollections.observableArrayList();
			
			pDataList.toFirst();
			i=-1;
			while(!pDataList.isEnd()) {
				vCurColumn = (List) pDataList.getCurrent();
				
				vCurColumn.toFirst();
				if (pDataList.isFirst()) pTable.getColumns().add(TableRowModel.creatTitleColumn() );
				else pTable.getColumns().add(TableRowModel.creatColumn(i, (String)((Object[])vCurColumn.getCurrent())[1]));
					
				u = 0;
				vCurColumn.next();
				while(!vCurColumn.isEnd()) {
					if (pDataList.isFirst()) vDataTable.add( new TableRowModel((int)((Object[])vCurColumn.getCurrent())[0], 
							(String)((Object[])vCurColumn.getCurrent())[1], pDataList.getContentNumber()-1) );
					else {
						vDataTable.get(u).setID(i, (int)((Object[])vCurColumn.getCurrent())[0]);
						vDataTable.get(u).setDistroProb(i, (double)((Object[])vCurColumn.getCurrent())[1]);
					}
						
					u++;
					vCurColumn.next();
				}
				
				i++;
				pDataList.next();
			}
			
			pTable.setItems(vDataTable);
			TableRowModel.setTableEditable(pTable);
		}else throw new Exception("04; uDT,EdiCon");
 	}
 	
 	
 	/**	Dh	04.03.2021
 	 * 
 	 * @param pProbElementList
 	 * @return
 	 * @throws Exception
 	 */
 	protected boolean checkProbElementList(List pProbElementList) throws Exception{
 		Object[] vCur;
 		boolean vRet = false;
 		double vTotalProb = 0;
 		
 		if (pProbElementList != null) {
 			pProbElementList.toFirst();
 			while(!pProbElementList.isEnd()) {
 				vCur = (Object[])pProbElementList.getCurrent();
 				
 				if (vCur.length == 3) {
 					if (vCur[2] instanceof Double) vTotalProb += (double) vCur[2];
 					else throw new Exception("06; cPELi,EdCon");
 				} else throw new Exception("01; cPELi,EdCon");
 				
 				pProbElementList.next();
 			}
 			
 			if (vTotalProb == 100) vRet = true;
 		} else throw new Exception("04; cPELi,EdCon");
 		
 		return vRet;
 	}
 	/**	Dh	18.03.2021
 	 * 
 	 * @param pPrioElementList
 	 * @return
 	 * @throws Exception
 	 */
 	protected boolean checkPrioElementList(List pPrioElementList) throws Exception{
 		Object[] vCur;
 		boolean vRet = true;
 		
 		if (pPrioElementList != null) {
 			pPrioElementList.toFirst();
 			while(!pPrioElementList.isEnd()) {
 				vCur = (Object[])pPrioElementList.getCurrent();
 				
 				if (vCur.length == 3) {
 					if (vCur[2] instanceof Integer) {
 						if ((int) vCur[2] < 0) throw new Exception("02; cPELi,EdCon");
 					}else throw new Exception("06; cPELi,EdCon");
 				} else throw new Exception("01; cPELi,EdCon");
 				
 				pPrioElementList.next();
 			}
 		} else throw new Exception("04; cPELi,EdCon");
 		
 		return vRet;
 	}
 	
 	//----------------------------------------------------------------------------------------------------
 	
 	/**	Dh	19.04.2021
 	 * 	
 	 * 	pType:
 	 * 		0: Pack
 	 * 		1: Session
 	 * 		3: NPC
 	 * @param pFileChooser
 	 */
 	protected static void configureFileChooser(FileChooser pFileChooser, int pType) {
		pFileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		switch(pType) {
			case 0:
				pFileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Pack", "*.pac"));
			case 1:
				pFileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Session", "*.ses"));
			case 2:
				pFileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("NSC", "*.nsc"));
			default:
		}
	}
 	
}
