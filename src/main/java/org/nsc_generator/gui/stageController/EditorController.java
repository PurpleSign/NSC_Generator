/**	NSC_Generator v0.2		Dh	14.08.2023
 * 	
 * 	gui.stageController
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

package org.nsc_generator.gui.stageController;

import java.io.File;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import org.nsc_generator.gui.ParentControllerInterface;
import org.nsc_generator.gui.tableElements.NameElement;
import org.nsc_generator.gui.tableElements.PrioElementTableElement;
import org.nsc_generator.gui.tableElements.ProbElementTableElement;
import org.nsc_generator.gui.tableElements.TableRowModel;
import org.nsc_generator.logic.MainManagerInterface;
import org.nsc_generator.logic.editors.Editor;

public abstract class EditorController {
	protected boolean isEdit;
	protected boolean isMobile;
	protected ParentControllerInterface parentController;
	protected MainManagerInterface mainManager;
	
	/**	Dh	02.03.2021
	 * 
	 */
	public EditorController() {
		super();
	}
	
	/**	Dh	22.05.2021
	 * 
	 * @throws Exception
	 */
	public void setUp(boolean pIsEdit, boolean pIsMobile,  ParentControllerInterface pParentController, Editor pEditor) throws Exception{
		isEdit = pIsEdit;
		isMobile = pIsMobile;
		
		parentController = pParentController;
		mainManager = null;
	}
	
	/**	Dh	22.05.2021
	 * 
	 * @param pIsEdit
	 * @param pIsMobile
	 * @param pParentController
	 * @param pEditor
	 * @param pMainManager
	 * @throws Exception
	 */
	public void setUp(boolean pIsEdit, boolean pIsMobile,  ParentControllerInterface pParentController, Editor pEditor, MainManagerInterface pMainManager) throws Exception{
		isEdit = pIsEdit;
		isMobile = pIsMobile;
		
		parentController = pParentController;
		mainManager = pMainManager;
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
	
	/**	Dh	14.08.2023
	 * 
	 * @param pTable
	 * @return
	 * @throws Exception
	 */
	protected ArrayList<ArrayList<Object[]>> getDistributionsListFromTables(TableView<TableRowModel> pTable) throws Exception {
		ArrayList<ArrayList<Object[]>> vRet;
		ArrayList<Object[]> vColumnList;
		TableRowModel vCur;
		ObservableList<TableRowModel> vRowLists;
		
		if (pTable != null) {
			vRet = new ArrayList<ArrayList<Object[]>>();
			vRowLists = pTable.getItems();
			
			for (int i=0; i<pTable.getColumns().size() ; i++) {
				vColumnList = new ArrayList<Object[]>();
				
				for (int u=-1; u<vRowLists.size(); u++) {
					if (u == -1) {
						vCur = vRowLists.get(u+1);
						
						if (i == 0) vColumnList.add(new Object[] { null, null });
						else vColumnList.add(new Object[] {  vCur.getID(i-1), "" });
					}else {
						vCur = vRowLists.get(u);
						
						if (i == 0) vColumnList.add(new Object[] { vCur.getDistroElementID(), vCur.getDistroElementName() });
						else vColumnList.add(new Object[] { vCur.getID(i-1), vCur.getDistroProb(i-1) });
					}
				}
				
				vRet.add(vColumnList);
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
 	/**	Dh	14.08.2023
 	 * 
 	 * @param pProbElementList
 	 * @param pTableElementList
 	 * @param pTableView
 	 * @throws Exception
 	 */
 	protected void updateProbElementTableElementList (ArrayList<Object[]> pProbElementList, ObservableList<ProbElementTableElement> pTableElementList, 
 			TableView<ProbElementTableElement> pTableView) throws Exception {
 		if (pProbElementList != null) {
 			pTableElementList.clear();
			
 			for (Object[] vCur : pProbElementList) {
 				pTableElementList.add( convertToProbElementTableElement(vCur) );
 			}
			pTableView.setItems(pTableElementList);
		}else throw new Exception("04; uPETEL,EdiCon");
 	}
 	/**	Dh	14.08.2023
 	 * 
 	 * @param pPrioElementList
 	 * @param pTableElementList
 	 * @param pTableView
 	 * @throws Exception
 	 */
 	protected void updatePrioElementTableElementList (ArrayList<Object[]> pPrioElementList, ObservableList<PrioElementTableElement> pTableElementList, 
 			TableView<PrioElementTableElement> pTableView) throws Exception {
 		if (pPrioElementList != null) {
 			pTableElementList.clear();
			
 			for (Object[] vCur : pPrioElementList) {
 				pTableElementList.add( convertToPrioElementTableElement(vCur) );
 			}
			pTableView.setItems(pTableElementList);
		}else throw new Exception("04; uPETEL,EdiCon");
 	}
 	/**	Dh	14.08.2023
 	 * 
 	 * @param pProbElementList
 	 * @param pTableElementList
 	 * @param pListView
 	 * @throws Exception
 	 */
 	protected void updateNameElementListElementList (ArrayList<Object[]> pProbElementList, ObservableList<NameElement> pTableElementList, 
 			ListView<NameElement> pListView) throws Exception {
 		if (pProbElementList != null) {
 			try {pTableElementList.clear();}
 			catch (Exception ex) {}
			
 			for (Object[] vCur : pProbElementList) {
 				pTableElementList.add( convertToNameElement(vCur) );
 			}
			pListView.setItems(pTableElementList);
		}else throw new Exception("04; uPETEL,EdiCon");
 	}
 	
 	
 	/**	Dh	14.08.2023
 	 * 
 	 * @param pNameElementList
 	 * @param pChoiceBoxList
 	 * @param pChoiceBox
 	 * @throws Exception
 	 */
 	protected void updateChoiceBoxList(ArrayList<Object[]> pNameElementList, ObservableList<NameElement> pChoiceBoxList, ChoiceBox<NameElement> pChoiceBox) throws Exception{
 		if (pNameElementList != null) {
 			pChoiceBoxList.clear();
			
 			for (Object[] vCur : pNameElementList) {
 				pChoiceBoxList.add( convertToNameElement(vCur) );
 			}			
			pChoiceBox.setItems(pChoiceBoxList);
		}
 	}
 	//-----
 	/**	Dh	14.08.2023
 	 * 
 	 * @param pDataList
 	 * @param pTable
 	 * @throws Exception
 	 */
 	protected void updateDistributionTable(ArrayList<ArrayList<Object[]>> pDataList, TableView<TableRowModel> pTable) throws Exception {
 		ArrayList<Object[]> vCurColumn;
		ObservableList<TableRowModel> vDataTable;		

		if (pDataList != null) {
			pTable.getItems().clear();
			pTable.getColumns().clear();
			
			vDataTable = FXCollections.observableArrayList();
			
			for (int i=-1; i<(pDataList.size()-1); i++) {
				vCurColumn = pDataList.get(i+1);
				
				if (i == -1) pTable.getColumns().add(TableRowModel.creatTitleColumn());
				else pTable.getColumns().add(TableRowModel.creatColumn(i, (String)(vCurColumn.get(0)[1])  ));
				
				for (int u=0; u<vCurColumn.size()-1; u++) {
					if (i == -1) vDataTable.add( new TableRowModel((int) (vCurColumn.get(u+1)[0]) , (String) (vCurColumn.get(u+1)[1]), pDataList.size() - 1) );
					else {
						vDataTable.get(u).setID(i, (int) (vCurColumn.get(u+1)[0]));
						vDataTable.get(u).setDistroProb(i, (double) (vCurColumn.get(u+1)[1]));
					}
				}
			}
			
			pTable.setItems(vDataTable);
			TableRowModel.setTableEditable(pTable);
		}else throw new Exception("04; uDT,EdiCon");
 	}
 	
 	
 	/**	Dh	14.08.2023
 	 * 
 	 * @param pProbElementList
 	 * @return
 	 * @throws Exception
 	 */
 	protected boolean checkProbElementList(ArrayList<Object[]> pProbElementList) throws Exception{
 		boolean vRet = false;
 		double vTotalProb = 0;
 		
 		if (pProbElementList != null) {
 			for (Object[] vCur : pProbElementList) {
 				if (vCur.length == 3) {
 					if (vCur[2] instanceof Double) vTotalProb += (double) vCur[2];
 					else throw new Exception("06; cPELi,EdCon");
 				} else throw new Exception("01; cPELi,EdCon");
 			}
 			
 			if (vTotalProb == 100) vRet = true;
 		} else throw new Exception("04; cPELi,EdCon");
 		
 		return vRet;
 	}
 	/**	Dh	14.08.2023
 	 * 
 	 * @param pPrioElementList
 	 * @return
 	 * @throws Exception
 	 */
 	protected boolean checkPrioElementList(ArrayList<Object[]> pPrioElementList) throws Exception{
 		boolean vRet = true;
 		
 		if (pPrioElementList != null) {
 			for (Object[] vCur : pPrioElementList) {
 				if (vCur.length == 3) {
 					if (vCur[2] instanceof Integer) {
 						if ((int) vCur[2] < 0) throw new Exception("02; cPELi,EdCon");
 					}else throw new Exception("06; cPELi,EdCon");
 				} else throw new Exception("01; cPELi,EdCon");
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
