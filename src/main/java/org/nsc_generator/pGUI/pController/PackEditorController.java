/**	NSC_Generator v0.0		Dh	23.08.2022
 * 	
 * 	pGUI.pController
 * 	  EditorController
 * 	    PackEditorController
 * 
 * 	CaseNumber (int):
 * 		0	Quirk
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

package org.nsc_generator.pGUI.pController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import pDataStructures.List;

import org.nsc_generator.pGUI.CultureEditorStage;
import org.nsc_generator.pGUI.RaceEditorStage;
import org.nsc_generator.pGUI.pTableElement.NameElement;
import org.nsc_generator.pGUI.PrioElementEditorStage;
import org.nsc_generator.pGUI.pTableElement.PrioElementTableElement;
import org.nsc_generator.pGUI.pTableElement.TableRowModel;
import org.nsc_generator.pLogic.MainManager;
import org.nsc_generator.pLogic.pEditors.CultureEditor;
import org.nsc_generator.pLogic.pEditors.Editor;
import org.nsc_generator.pLogic.pEditors.PackEditor;
import org.nsc_generator.pLogic.pEditors.RaceEditor;

public class PackEditorController extends ParentStageController implements ParentPrioElementEditorControllerInterface{
	@FXML
	private TextField tfName;
	@FXML
	private Button btCulture_Add, btCulture_Edit, btCulture_Remove, btRace_Add, btRace_Edit,
		btRace_Remove, btOCP_Apply, btOCP_Back, btRP_Apply, btRP_Back, btApply, btDelete, btBack,
		btQuirk_Add, btQuirk_Edit, btQuirk_Remove;
	
	@FXML
	private ListView<NameElement> lvCulture, lvRace;
	@FXML
	private TableView<TableRowModel> tOriginalCultureProbability, tRaceProbability;
	@FXML
	private TableView<PrioElementTableElement> tQuirk;
	@FXML
	private TableColumn<PrioElementTableElement, String> tcQuirk_Name;
	@FXML
	private TableColumn<PrioElementTableElement, Integer> tcQuirk_Prio;
	
	private ObservableList<NameElement> liCulture, liRace;
	private ObservableList<PrioElementTableElement> liQuirk;
	
	private PackEditor packEditor;
	
	/**	Dh	02.03.2021
	 * 
	 */
 	public PackEditorController() {
		super();
	}
	
	/**	Dh	20.05.2021
	 * 
	 * @param pPackEditor
	 * @throws Exception
	 */
	public void setUp(boolean pIsEdit, boolean pIsMobile, ParentStageControllerInterface pParentController, Editor pPackEditor) throws Exception {
		super.setUp(pIsEdit, pIsMobile, pParentController, pPackEditor);
		
		if (pPackEditor != null) {
			if (pPackEditor instanceof PackEditor) {
				packEditor = (PackEditor)pPackEditor;
				
				tfName.setText(packEditor.getName());
				
				lvCulture.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
					setEnabledCultureListButtons(true);
				});
				lvRace.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
					setEnabledRaceListButtons(true);
				});
				tQuirk.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
					setEnabledQuirkListButtons(true);
				});
				
				liCulture = FXCollections.observableArrayList();
				liRace = FXCollections.observableArrayList();
				liQuirk = FXCollections.observableArrayList();
				
				tcQuirk_Name.setCellValueFactory(new PropertyValueFactory<PrioElementTableElement, String>("name"));
				tcQuirk_Prio.setCellValueFactory(new PropertyValueFactory<PrioElementTableElement, Integer>("priority"));
				
				lvCulture.setItems(liCulture);
				lvRace.setItems(liRace);
				tQuirk.setItems(liQuirk);
				
				updateAll();
				setEnabled();
			} else throw new Exception("06; su,PaEdCon");
		} else throw new Exception("04; sU,PaEdCon");
	}

//--------------------------------------------------------------------------------------------------------

	/**	Dh	18.03.2021
	 * 
	 * 	CaseNumber (int):
	 * 		0	Quirk
	 * 
	 * @param pCaseNumber
	 * @param pProbElementID
	 * @param pName
	 * @param pPrio
	 * @throws Exception
	 */
	public void setPrioElement(int pCaseNumber, int pPrioElementID, String pName, int pPrio) throws Exception{
		switch(pCaseNumber) {
		case 0:
			packEditor.setQuirkElement(pPrioElementID, pName, pPrio);
			break;
		default:
			throw new Exception("02; sPE,PaEdCon");
		}
	}
	/**	Dh	18.03.2021
	 * 	
	 *  CaseNumber (int):
	 * 		0	Quirk
	 */
	public Object[] getPrioElement(int pCaseNumber, int pPrioElementID) throws Exception{
		Object[] vRet = null;
		
		switch(pCaseNumber) {
		case 0:
			vRet = packEditor.getQuirkElement(pPrioElementID);
			break;
		default:
			throw new Exception("02; gPE,PaEdCon");
		}
		
		return vRet;
	}
	
	/**	Dh	18.03.2021
	 * 
	 * 	CaseNumber (int):
	 * 		0	Quirk
	 * 
	 * @param pCaseNumber
	 * @param pName
	 * @param pPrio
	 * @throws Exception
	 */
	public void addPrioElement(int pCaseNumber, String pName, int pPrio) throws Exception{
		switch(pCaseNumber) {
		case 0:
			packEditor.addQuirkElement(pName, pPrio);
			break;
		default:
			throw new Exception("02; aPE,PaEdCon");
		}
	}
	/**	Dh	18.03.2021
	 * 
	 * 	CaseNumber (int):
	 * 		0	Quirk
	 * 
	 */
	public void removePrioElement(int pCaseNumber, int pPrioElementID) throws Exception{
		switch(pCaseNumber) {
		case 0:
			packEditor.removeQuirkElement(pPrioElementID);
			break;
		default:
			throw new Exception("02; rPE,PaEdCon");
		}
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	18.03.2021
	 * 
	 * 	CaseNumber (int):
	 * 		0	Quirk
	 * 
	 */
	@FXML
	protected void addQuirk() {
		try {
			childStage = new PrioElementEditorStage("Füge eine Eigenschaft hinzu", false, null, 0, -1, this);
			
			setDisabled();
		}catch(Exception ex) {MainManager.handleException(ex);}
	}
	//-----
	/**	Dh	02.03.2021
	 * 
	 */
	@FXML
	protected void addCulture() {
		CultureEditor vCultureEditor;
		
		try {
			vCultureEditor = packEditor.addCulture();

			childStage = new CultureEditorStage(false, this, vCultureEditor);

			setDisabled();
		}catch(Exception ex) {MainManager.handleException(ex);}
	}
	/**	Dh	03.03.2021
	 * 
	 */
	@FXML
	protected void addRace() {
		RaceEditor vRaceEditor;
		
		try {
			vRaceEditor = packEditor.addRace();

			childStage = new RaceEditorStage(false, this, vRaceEditor);

			setDisabled();
		}catch(Exception ex) {MainManager.handleException(ex);}
	}
	
	/**	Dh	18.03.2021
	 * 	
	 * 	CaseNumber (int):
	 * 		0	Quirk
	 * 
	 */
	@FXML
	protected void editQuirk() {
		PrioElementTableElement vCur = tQuirk.getSelectionModel().getSelectedItem();
		
		if (vCur != null) {
			childStage = new PrioElementEditorStage("Editiere Eigenschaften", true, null, 0, vCur.getId(), this);
			
			setDisabled();
		}
	}
	//-----
	/**	Dh	04.03.2021
	 * 
	 */
	@FXML
	protected void editCulture() {
		CultureEditor vCultureEditor;
		NameElement vCur = lvCulture.getSelectionModel().getSelectedItem();
		
		try {
			if (vCur != null) {
				vCultureEditor = packEditor.editCulture(vCur.getId());
				
				childStage = new CultureEditorStage(true, this, vCultureEditor);
				
				setDisabled();
			}
		}catch(Exception ex) {MainManager.handleException(ex);}
	}
	/**	Dh	04.03.2021
	 * 
	 */
	@FXML
	protected void editRace() {
		RaceEditor vRaceEditor;
		NameElement vCur = lvRace.getSelectionModel().getSelectedItem();
		
		try {
			if (vCur != null) {
				vRaceEditor = packEditor.editRace(vCur.getId());
				
				childStage = new RaceEditorStage(true, this, vRaceEditor);
				
				setDisabled();
			}
		}catch(Exception ex) {MainManager.handleException(ex);}
	}
	
	/**	Dh	18.03.2021
	 * 
	 * 	CaseNumber (int):
	 * 		0	Quirk
	 * 
	 */
	@FXML
	protected void removeQuirk() {
		PrioElementTableElement vCur = tQuirk.getSelectionModel().getSelectedItem();
		
		if (vCur != null) {
			try {
				removePrioElement(0, vCur.getId());
				
				updateQuirkList();
			}catch(Exception ex) {MainManager.handleException(ex);}
		}
	}
	//-----
	/**	Dh	07.03.2021
	 * 
	 */
	@FXML
	protected void removeCulture() {
		NameElement vCur = lvCulture.getSelectionModel().getSelectedItem();
		
		if (vCur != null) {
			try {
				packEditor.removeCulture(vCur.getId());
				
				updateCultureList();
				updateTables();
			}catch(Exception ex) {MainManager.handleException(ex);}
		}
	}
	/**	Dh	07.03.2021
	 * 
	 */
	@FXML
	protected void removeRace() {
		NameElement vCur = lvRace.getSelectionModel().getSelectedItem();
		
		if (vCur != null) {
			try {
				packEditor.removeRace(vCur.getId());
				
				updateRaceList();
				updateTables();
			}catch(Exception ex) {MainManager.handleException(ex);}
		}
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	07.03.2021
	 * 
	 */
	@FXML
	protected void setOriginCultureTable() {
		try {
			if (checkDistroTable(tOriginalCultureProbability) == true)	
				packEditor.setCultureDistributions(getDistributionsListFromTables(tOriginalCultureProbability));
		}catch (Exception ex) {MainManager.handleException(ex);}
	}
	/**	Dh	06.03.2021
	 * 
	 */
	@FXML
	protected void resetOriginCultureTable() {
		try{updateCultureOriginTable();}
		catch(Exception ex) {MainManager.handleException(ex);}
	}
	
	/**	Dh	07.03.2021
	 * 
	 */
	@FXML
	protected void setRaceDistributionTable() {
		try {
			if (checkDistroTable(tRaceProbability) == true)	
				packEditor.setRaceDistributions(getDistributionsListFromTables(tRaceProbability));
		}catch (Exception ex) {MainManager.handleException(ex);}
	}
	/**	Dh	06.03.2021
	 * 
	 */
	@FXML
	protected void resetRaceDistributionTable() {
		try {updateRaceDistributionTable();}
		catch(Exception ex) {MainManager.handleException(ex);}
	}
	
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	23.08.2022
	 * 
	 */
	@FXML
 	protected void apply() {
		try {
			if (checkInputs() == true) {
				packEditor.setName(tfName.getText());
				packEditor.setQuirkList();
				
				//if (!isEdit) packEditor.add();
				
				packEditor.save();
				
				parentController.closeChildStage();
			}
		}
		catch (Exception ex) {MainManager.handleException(ex);}
	}
	
	@FXML
	protected void delete() {
		initExample();
	}
	/**	Dh	09.03.2021
	 * 
	 */
	@FXML
	public void back() {
		try{
			if (!isEdit) packEditor.delete();
			else packEditor.back();
			
			parentController.closeChildStage();
		} catch(Exception ex) {MainManager.handleException(ex);}
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	04.03.2021
	 * 
	 * @param pEnabled
	 */
	private void setEnabledCultureListButtons(boolean pEnabled) {
		if (lvCulture.getSelectionModel().isEmpty() == true) {
			btCulture_Edit.setDisable(true);
			btCulture_Remove.setDisable(true);
		} else {
			btCulture_Edit.setDisable(!pEnabled);
			btCulture_Remove.setDisable(!pEnabled);
		}
	}
	/**	Dh	04.03.2021
	 * 
	 * @param pEnabled
	 */
	private void setEnabledRaceListButtons(boolean pEnabled) {
		if (lvRace.getSelectionModel().isEmpty() == true) {
			btRace_Edit.setDisable(true);
			btRace_Remove.setDisable(true);
		} else {
			btRace_Edit.setDisable(!pEnabled);
			btRace_Remove.setDisable(!pEnabled);
		}
	}
	/**	Dh	15.03.2021
	 * 
	 * @param pEnabled
	 */
	private void setEnabledQuirkListButtons(boolean pEnabled) {
		if (tQuirk.getSelectionModel().isEmpty() == true) {
			btQuirk_Edit.setDisable(true);
			btQuirk_Remove.setDisable(true);
		} else {
			btQuirk_Edit.setDisable(!pEnabled);
			btQuirk_Remove.setDisable(!pEnabled);
		}
	}
	
	/**	Dh	15.03.2021
	 * 
	 * @param pEnabled
	 */
	protected void setEnabledAll(boolean pEnabled) {
		tfName.setDisable(!pEnabled);
		
		btCulture_Add.setDisable(!pEnabled);
		btRace_Add.setDisable(!pEnabled);
		
		setEnabledCultureListButtons(pEnabled);
		setEnabledRaceListButtons(pEnabled);
		setEnabledQuirkListButtons(pEnabled);
		
		btOCP_Apply.setDisable(!pEnabled);
		btOCP_Back.setDisable(!pEnabled);
		btRP_Apply.setDisable(!pEnabled);
		btRP_Back.setDisable(!pEnabled);
		
		btApply.setDisable(!pEnabled);
		btDelete.setDisable(!pEnabled);
		btBack.setDisable(!pEnabled);
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	04.03.2021
	 * 
	 * @throws Exception
	 */
	private void updateCultureList() throws Exception {
		List vNameEleList = packEditor.getCultureList();
		
		if (vNameEleList != null) {
			liCulture.clear();
			
			vNameEleList.toFirst();
			while(!vNameEleList.isEnd()) {
				liCulture.add( convertToNameElement((Object[])vNameEleList.getCurrent()) );
				
				vNameEleList.next();
			}
			lvCulture.setItems(liCulture);
		}else throw new Exception("04; uCuLis,PaEdiCOn");
	}
	/**	Dh	04.03.2021
	 * 
	 * @throws Exception
	 */
	private void updateRaceList() throws Exception {
		List vNameEleList = packEditor.getRaceList();
		
		if (vNameEleList != null) {
			liRace.clear();
			
			vNameEleList.toFirst();
			while(!vNameEleList.isEnd()) {
				liRace.add( convertToNameElement((Object[])vNameEleList.getCurrent()) );
				
				vNameEleList.next();
			}
			lvRace.setItems(liRace);
		}else throw new Exception("04; uRaLis,PaEdiCOn");
	}
	/**	Dh	15.03.2021
	 * 
	 * @throws Exception
	 */
	private void updateQuirkList() throws Exception {
		updatePrioElementTableElementList(packEditor.getQuirkList(), liQuirk, tQuirk);
	}
	
	/**	Dh	06.03.2021
	 * 
	 * @throws Exception
	 */
	@FXML
	private void updateCultureOriginTable() throws Exception{
		updateDistributionTable(packEditor.getCultureDistributions(), tOriginalCultureProbability);
	}
	/**	Dh	06.03.2021
	 * 
	 * @throws Exception
	 */
	@FXML
	private void updateRaceDistributionTable() throws Exception{
		updateDistributionTable(packEditor.getRaceDistributions(), tRaceProbability);
	}
	
	/**	Dh	06.03.2021
	 * 
	 * @throws Exception
	 */
	private void updateLists() throws Exception {
		updateCultureList();
		updateRaceList();
	}
	/**	Dh	06.03.2021
	 * 
	 * @throws Exception
	 */
	private void updateTables() throws Exception{
		updateCultureOriginTable();
		updateRaceDistributionTable();
	}
	
	/**	Dh	15.03.2021
	 * 
	 * @throws Exception
	 */
	protected void updateAll() throws Exception {
		updateLists();
		updateTables();
		
		updateQuirkList();
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	07.03.2021
	 * 
	 * @return
	 */
	private boolean checkPackName() {
		boolean vRet = true;
		
		if (tfName.getText().equals("")) vRet = false;
		
		return vRet;
	}
	/**	Dh	18.03.2021
	 * 
	 * @return
	 * @throws Exception
	 */
	private boolean checkQuirkTable() throws Exception {
		return checkPrioElementList(packEditor.getQuirkList());
	}
	/**	Dh	07.03.2021
	 * 
	 * @param pTable
	 * @return
	 * @throws Exception
	 */
	private boolean checkDistroTable(TableView<TableRowModel> pTable) throws Exception {
		boolean vRet = true;
		double vTotalColumnProb;
		
		TableRowModel vCur;
		ObservableList<TableRowModel> vRowLists;
		
		vRowLists = pTable.getItems();
			
		for (int i=1; i<pTable.getColumns().size() ; i++) {			
			vTotalColumnProb = 0;
			for (int u=0; u<vRowLists.size(); u++) {
				vCur = vRowLists.get(u);
				
				try {vTotalColumnProb += vCur.getDistroProb(i-1);}
				catch (Exception ex) {throw ex;}
			}
			
			if (vTotalColumnProb != 100) vRet = false;
		}
		
		return vRet;
	}
	
	/**	Dh	18.03.2021
	 * 
	 * @return
	 * @throws Exception
	 */
	private boolean checkInputs() throws Exception {
		boolean vRet = true;
		
		if (checkPackName() == false) {
			MainManager.handleMessage("Name Fehlt.");
			vRet = false;
		}
		
		if (checkQuirkTable() == false) {
			MainManager.handleMessage("Eigenschaftstabele falsch.");
			vRet = false;
		}
		
		if (checkDistroTable(tOriginalCultureProbability) == false) {
			MainManager.handleMessage("Kulturelle Herkunftsverteilungstabelle falsch.");
			vRet = false;
		}
		if (checkDistroTable(tRaceProbability) == false) {
			MainManager.handleMessage("Rassenverteilungstabele falsch.");
			vRet = false;
		}
		
		return vRet;
	}
	
	@FXML
	private void initExample() {
		CultureEditor vCultEditor;
		RaceEditor vRaceEditor;
		
		try {
			for (int i = 0; i < 2; i++) {
				vCultEditor = packEditor.addCulture();
				vRaceEditor = packEditor.addRace();
				
				switch(i) {
				case 0:
					vCultEditor.setName("Kultur01");
					vCultEditor.addSexualityElement("A", 100);
					vCultEditor.addHairlengthElement("lang", 100);
					
					vRaceEditor.setName("Rasse01");
					vRaceEditor.addSexElement("Mono", 100);
					vRaceEditor.addHaircolorElement("blond", 100);
					vRaceEditor.addEyecolorElement("grau", 100);
					
					break;
				case 1:
					vCultEditor.setName("Kultur02");
					vCultEditor.addSexualityElement("A", 100);
					vCultEditor.addHairlengthElement("kurz", 100);
					
					vRaceEditor.setName("Rasse02");
					vRaceEditor.addSexElement("Mono", 100);
					vRaceEditor.addHaircolorElement("schwarz", 100);
					vRaceEditor.addEyecolorElement("braun", 100);
					
					break;
				}
				
				vCultEditor.setSexualityList();
				vCultEditor.setHairlengthList();
				//-----
				vCultEditor.add();
				
				vRaceEditor.setAge(0, 0, 0);
				vRaceEditor.setHeight(0, 0, 0);
				vRaceEditor.setWeight(0, 0, 0);
				//-----
				vRaceEditor.setSexList();
				vRaceEditor.setHaircolorList();
				vRaceEditor.setEyecolorList();
				//-----
				vRaceEditor.add();
			}
			
			
			updateAll();
		}catch(Exception ex) {MainManager.handleException(ex);}
		
		
	}
	
}
