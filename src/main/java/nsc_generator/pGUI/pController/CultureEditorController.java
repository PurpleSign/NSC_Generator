/**	NSC_Generator v0.0		Dh	27.08.2022
 * 	
 * 	pGUI.pController
 * 	  EditorController
 * 	    ParentStageController
 * 	      CultureEditorController
 * 
 * 	CaseNumber (int):
 * 		0	Sexuality
 * 		1	Hairlength
 * 		2	SO
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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import nsc_generator.pGUI.ProbElementEditorStage;
import nsc_generator.pGUI.pTableElement.NameElement;
import nsc_generator.pGUI.pTableElement.ProbElementTableElement;
import nsc_generator.pLogic.MainManager;
import nsc_generator.pLogic.pEditors.CultureEditor;
import nsc_generator.pLogic.pEditors.Editor;

public class CultureEditorController extends ParentStageController implements ParentProbElementEditorControllerInterface {
	@FXML
	private TextField tfName;
	@FXML
	private Button btSex_Add, btSex_Edit, btSex_Remove, btHair_Add, btHair_Edit, btHair_Remove,
		btSo_Add, btSo_Edit, btSo_Remove, btApply, btDelete, btBack;
	@FXML
	private CheckBox cbSubculture;
	
	@FXML
	private ChoiceBox<NameElement> cbParent;
	@FXML
	private TableView<ProbElementTableElement> tSex, tHair, tSo;
	@FXML
	private TableColumn<ProbElementTableElement, String> tcSex_Name, tcHair_Name, tcSo_Name;
	@FXML
	private TableColumn<ProbElementTableElement, Double> tcSex_Prob, tcHair_Prob, tcSo_Prob;
	
	private ObservableList<NameElement> liParent;
	private ObservableList<ProbElementTableElement> liSex, liHair, liSo;
	
	private CultureEditor cultureEditor;
	//protected ProbElementEditorStage childStage;
	
	/**	Dh	02.03.2021
	 * 
	 */
	public CultureEditorController() {
		super();
	}

	/**	Dh	20.05.2021
	 * 
	 */
	public void setUp(boolean pIsEdit, boolean pIsMobile, ParentStageControllerInterface pParentController, Editor pCultureEditor) throws Exception{
		super.setUp(pIsEdit, pIsMobile,pParentController, pCultureEditor);
		
		if (pCultureEditor != null) {
			if (pCultureEditor instanceof CultureEditor) {
				cultureEditor = (CultureEditor)pCultureEditor;
				
				tfName.setText(cultureEditor.getName());
				
				cbSubculture.setSelected(cultureEditor.isSubculture());
				liParent = FXCollections.observableArrayList();
				
				tSex.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
					setEnabledSexListButtons(true);
				});
				tHair.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
					setEnabledHairListButtons(true);
				});
				tSo.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
					setEnabledSoListButtons(true);
				});
				
				liSex = FXCollections.observableArrayList();
				liHair = FXCollections.observableArrayList();
				liSo = FXCollections.observableArrayList();
				
				tcSex_Name.setCellValueFactory(new PropertyValueFactory<ProbElementTableElement, String>("name"));
				tcSex_Prob.setCellValueFactory(new PropertyValueFactory<ProbElementTableElement, Double>("probability"));
				
				tcHair_Name.setCellValueFactory(new PropertyValueFactory<ProbElementTableElement, String>("name"));
				tcHair_Prob.setCellValueFactory(new PropertyValueFactory<ProbElementTableElement, Double>("probability"));
				
				tcSo_Name.setCellValueFactory(new PropertyValueFactory<ProbElementTableElement, String>("name"));
				tcSo_Prob.setCellValueFactory(new PropertyValueFactory<ProbElementTableElement, Double>("probability"));
				
				if (!pIsEdit) btDelete.setVisible(false);
				
				updateAll();
				setEnabled();
				
				if (cbSubculture.isSelected()) cbParent.getSelectionModel().select(getNameElementFromNameElementListByID((int)cultureEditor.getParentCulture()[0], liParent));
			} else throw new Exception("06; su,CuEdCon");
		} else throw new Exception("04; sU,CuEdCon");
	}

//--------------------------------------------------------------------------------------------------------

	/**	Dh 	12.03.2021
	 * 
	 * 	CaseNumber (int):
	 * 		0	Sexuality
	 * 		1	Hairlength
	 * 		2	So
	 */
	public void setProbElement(int pCaseNumber, int pProbElementID, String pName, double pProb) throws Exception{
		switch(pCaseNumber) {
		case 0:
			cultureEditor.setSexualityElement(pProbElementID, pName, pProb);
			break;
		case 1:
			cultureEditor.setHairlengthElement(pProbElementID, pName, pProb);
			break;
		case 2:
			cultureEditor.setSoElement(pProbElementID, pName, pProb);
			break;
		default:
			throw new Exception("02; sPE,CuEdCon");
		}
	}
	/**	Dh	12.03.2021
	 * 
	 * 	CaseNumber (int):
	 * 		0	Sexuality
	 * 		1	Hairlength
	 * 		2	So
	 */
	public Object[] getProbElement(int pCaseNumber, int pProbElementID) throws Exception{
		Object[] vRet = null;
		
		switch(pCaseNumber) {
		case 0:
			vRet = cultureEditor.getSexualityElement(pProbElementID);
			break;
		case 1:
			vRet = cultureEditor.getHairlengthElement(pProbElementID);
			break;
		case 2:
			vRet = cultureEditor.getSoElement(pProbElementID);
			break;
		default:
			throw new Exception("02; gPE,CuEdCon");
		}
		
		return vRet;
	}
	
	/**	Dh	12.03.2021
	 * 
	 * 	CaseNumber (int):
	 * 		0	Sexuality
	 * 		1	Hairlength
	 * 		2	So
	 */
	public void addProbElement(int pCaseNumber, String pName, double pProb) throws Exception{
		switch(pCaseNumber) {
		case 0:
			cultureEditor.addSexualityElement(pName, pProb);
			break;
		case 1:
			cultureEditor.addHairlengthElement(pName, pProb);
			break;
		case 2:
			cultureEditor.addSoElement(pName, pProb);
			break;
		default:
			throw new Exception("02; aPE,CuEdCon");
		}
	}
	/**	Dh	12.03.2021
	 * 
	 * 	CaseNumber (int):
	 * 		0	Sexuality
	 * 		1	Hairlength
	 * 		2	So
	 */
	public void removeProbElement(int pCaseNumber, int pProbElementID) throws Exception{
		switch(pCaseNumber) {
		case 0:
			cultureEditor.removeSexualityElement(pProbElementID);
			break;
		case 1:
			cultureEditor.removeHairlengthElement(pProbElementID);
			break;
		case 2:
			cultureEditor.removeSoElement(pProbElementID);
			break;
		default:
			throw new Exception("02; rPE,CuEdCon");
		}
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	04.03.2021
	 * 
	 */
	@FXML
	protected void selectSubculture() {
		setEnabledParentListChoiceBox(true);
		try{updateParentChoiceBox();}
		catch(Exception ex) {MainManager.handleException(ex);}
	}
	
	/**	Dh	12.03.2021
	 * 
	 * 	CaseNumber (int):
	 * 		0	Sexuality
	 * 		1	Hairlength
	 * 		2	So
	 */
	@FXML
	protected void addSexuality() {
		try {
			childStage = new ProbElementEditorStage("Füge Sexualität hinzu", false, null, 0, -1, this);
			
			setDisabled();
		}catch(Exception ex) {MainManager.handleException(ex);}
	}
	/**	Dh	12.03.2021
	 * 	
	 * 	CaseNumber (int):
	 * 		0	Sexuality
	 * 		1	Hairlength
	 * 		2	So
	 */
	@FXML
	protected void addHairlength() {
		try {
			childStage = new ProbElementEditorStage("Füge Haarlänge hinzu", false, null, 1, -1, this);
			
			setDisabled();
		}catch(Exception ex) {MainManager.handleException(ex);}
	}
	/**	Dh	12.03.2021
	 * 
	 * 	CaseNumber (int):
	 * 		0	Sexuality
	 * 		1	Hairlength
	 * 		2	So
	 */
	@FXML
	protected void addSo() {
		try {
			childStage = new ProbElementEditorStage("Füge einen Sozialstatus hinzu", false, null, 2, -1, this);
			
			setDisabled();
		}catch(Exception ex) {MainManager.handleException(ex);}
	}
	
	/**	Dh	04.03.2021
	 * 
	 * 	CaseNumber (int):
	 * 		0	Sexuality
	 * 		1	Hairlength
	 * 		2	So
	 */
	@FXML
	protected void editSexuality() {
		ProbElementTableElement vCur = tSex.getSelectionModel().getSelectedItem();
		
		if (vCur != null) {
			childStage = new ProbElementEditorStage("Editiere Sexualität", true, null, 0, vCur.getId(), this);
			
			setDisabled();
		}
	}
	/**	Dh	04.03.2021
	 * 
	 * 	CaseNumber (int):
	 * 		0	Sexuality
	 * 		1	Hairlength
	 * 		2	So
	 */
	@FXML
	protected void editHairlength() {
		ProbElementTableElement vCur = tHair.getSelectionModel().getSelectedItem();
		
		if (vCur != null) {
			childStage = new ProbElementEditorStage("Editiere Haarlänge", true, null, 1, vCur.getId(), this);
			
			setDisabled();
		}
	}
	/**	Dh	27.08.2022
	 * 
	 * 	CaseNumber (int):
	 * 		0	Sexuality
	 * 		1	Hairlength
	 * 		2	So
	 */
	@FXML
	protected void editSo() {
		ProbElementTableElement vCur = tSo.getSelectionModel().getSelectedItem();
		
		if (vCur != null) {
			childStage = new ProbElementEditorStage("Editiere Sozialstatus", true, null, 2, vCur.getId(), this);
			
			setDisabled();
		}
	}
	
	/**	Dh	04.03.2021
	 * 	
	 * 	CaseNumber (int):
	 * 		0	Sexuality
	 * 		1	Hairlength
	 * 		2	So
	 */
	@FXML
	protected void removeSexuality() {
		ProbElementTableElement vCur = tSex.getSelectionModel().getSelectedItem();
		
		if (vCur != null) {
			try {
				removeProbElement(0, vCur.getId());
				
				updateSexualityList();
			}catch(Exception ex) {MainManager.handleException(ex);}
		}
	}
	/**	Dh	04.03.2021
	 * 
	 * 	CaseNumber (int):
	 * 		0	Sexuality
	 * 		1	Hairlength
	 * 		2	So
	 */
	@FXML
	protected void removeHairlength() {
		ProbElementTableElement vCur = tHair.getSelectionModel().getSelectedItem();
		
		if (vCur != null) {
			try {
				removeProbElement(1, vCur.getId());
				
				updateHairlengthList();
			}catch(Exception ex) {MainManager.handleException(ex);}
		}
	}
	/**	Dh	27.08.2022
	 * 
	 * 	CaseNumber (int):
	 * 		0	Sexuality
	 * 		1	Hairlength
	 * 		2	So
	 */
	@FXML
	protected void removeSo() {
		ProbElementTableElement vCur = tSo.getSelectionModel().getSelectedItem();
		
		if (vCur != null) {
			try {
				removeProbElement(2, vCur.getId());
				
				updateSoList();
			}catch(Exception ex) {MainManager.handleException(ex);}
		}
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	14.03.2021
	 * 
	 */
	public void closeChildStage() {
		childStage.hide();
		
		try{updateLists();} 
		catch(Exception ex) {MainManager.handleException(ex);}
		setEnabled();
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	12.03.2021
	 * 
	 */
	@FXML
	protected void apply() {
		try{
			if (checkInputs() == true) {
				cultureEditor.setName(tfName.getText());
				
				if (cbSubculture.isSelected()) {
					if (cultureEditor.isSubculture()) cultureEditor.setParentCulture(cbParent.getSelectionModel().getSelectedItem().getId());
					else cultureEditor.transformToSubculture(cbParent.getSelectionModel().getSelectedItem().getId());
				} else if (cultureEditor.isSubculture()) cultureEditor.transformFromSubculture();
				
				cultureEditor.setSexualityList();
				cultureEditor.setHairlengthList();
				cultureEditor.setSoList();
				
				if (!isEdit) cultureEditor.add();
				
				parentController.closeChildStage();
			}
		}catch(Exception ex) {MainManager.handleException(ex);}
	}
	
	/**	Dh	05.03.2021
	 * 
	 */
	@FXML
	protected void delete() {
		try{
			if (isEdit) {
				cultureEditor.remove();
				parentController.closeChildStage();
			}
		}catch(Exception ex) {MainManager.handleException(ex);}
	}
	
	/**	Dh	03.03.2021
	 * 
	 */
	@FXML
	public void back() {
		parentController.closeChildStage();
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	04.03.2021
	 * 
	 * @param pEnabled
	 */
	private void setEnabledSexListButtons(boolean pEnabled) {
		if (tSex.getSelectionModel().isEmpty() == true) {
			btSex_Edit.setDisable(true);
			btSex_Remove.setDisable(true);
		} else {
			btSex_Edit.setDisable(!pEnabled);
			btSex_Remove.setDisable(!pEnabled);
		}
	}
	/**	Dh	04.03.2021
	 * 
	 * @param pEnabled
	 */
	private void setEnabledHairListButtons(boolean pEnabled) {
		if (tHair.getSelectionModel().isEmpty() == true) {
			btHair_Edit.setDisable(true);
			btHair_Remove.setDisable(true);
		} else {
			btHair_Edit.setDisable(!pEnabled);
			btHair_Remove.setDisable(!pEnabled);
		}
	}
	/**	Dh	12.03.2021
	 * 
	 * @param pEnabled
	 */
	private void setEnabledSoListButtons(boolean pEnabled) {
		if (tSo.getSelectionModel().isEmpty() == true) {
			btSo_Edit.setDisable(true);
			btSo_Remove.setDisable(true);
		} else {
			btSo_Edit.setDisable(!pEnabled);
			btSo_Remove.setDisable(!pEnabled);
		}
	}
	//-----
	/**	Dh	04.03.2021
	 * 
	 * @param pEnabled
	 */
	private void setEnabledParentListChoiceBox(boolean pEnabled) {
		if (!cbSubculture.isSelected()) cbParent.setDisable(true);
		else cbParent.setDisable(!pEnabled);
	}
	
	/**	Dh	12.03.2021
	 * 
	 * @param pEnabled
	 */
 	protected void setEnabledAll(boolean pEnabled) {
		tfName.setDisable(!pEnabled);
		
		cbSubculture.setDisable(!pEnabled);
		setEnabledParentListChoiceBox(pEnabled);
		
		btSex_Add.setDisable(!pEnabled);
		btHair_Add.setDisable(!pEnabled);
		btSo_Add.setDisable(!pEnabled);
		
		tSex.setDisable(!pEnabled);
		tHair.setDisable(!pEnabled);
		tSo.setDisable(!pEnabled);
		
		setEnabledSexListButtons(pEnabled);
		setEnabledHairListButtons(pEnabled);
		setEnabledSoListButtons(pEnabled);
		
		btApply.setDisable(!pEnabled);
		btDelete.setDisable(!pEnabled);
		btBack.setDisable(!pEnabled);
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	04.03.2021
	 * 
	 * @throws Exception
	 */
	private void updateSexualityList() throws Exception {
		updateProbElementTableElementList(cultureEditor.getSexualityList(), liSex, tSex);
	}
	/**	Dh	04.03.2021
	 * 
	 * @throws Exception
	 */
	private void updateHairlengthList() throws Exception {
		updateProbElementTableElementList(cultureEditor.getHairlengthList(), liHair, tHair);
	}
	/**	Dh	12.03.2021
	 * 
	 * @throws Exception
	 */
	private void updateSoList() throws Exception {
		updateProbElementTableElementList(cultureEditor.getSoList(), liSo, tSo);
	}
	//-----
	/**	Dh	08.03.2021
	 * 
	 * @throws Exception
	 */
	private void updateParentChoiceBox() throws Exception{
		if (cbSubculture.isSelected() == true) updateChoiceBoxList(cultureEditor.getPossibleParentList(), liParent, cbParent);
	}
	
	/**	Dh	12.03.2021
	 * 
	 * @throws Exception
	 */
	private void updateLists() throws Exception{
		updateSexualityList();
		updateHairlengthList();
		updateSoList();
	}
	
	/**	Dh	04.03.2021
	 * 
	 * @throws Exception
	 */
	protected void updateAll() throws Exception{
		updateLists();
		updateParentChoiceBox();
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	08.03.2021
	 * 
	 * @return
	 * @throws Exception
	 */
	private boolean checkInputs() throws Exception {
		boolean vRet = true;
		
		if (tfName.getText().equals("")) {
			vRet = false;
			MainManager.handleMessage("Der Name fehlt.");
		}
		
		if (cbSubculture.isSelected()) {
			if (cbParent.getSelectionModel().getSelectedItem() == null) {
				vRet = false;
				MainManager.handleMessage("Es wurde kein Kultur gewählt.");
			}
			
			if ((cultureEditor.getSexualityList().getContentNumber() >= 1) && (checkProbElementList(cultureEditor.getSexualityList()) == false)) {
				vRet = false;
				MainManager.handleMessage("Der wert aller Wahrscheinlichkeitswerte der Sexualitätsliste beträgt nicht 100.");
			}
			if ((cultureEditor.getHairlengthList().getContentNumber() >= 1) && (checkProbElementList(cultureEditor.getHairlengthList()) == false)) {
				vRet = false;
				MainManager.handleMessage("Der wert aller Wahrscheinlichkeitswerte der Haarlängenliste beträgt nicht 100.");
			}
				
		}else {
			if (checkProbElementList(cultureEditor.getSexualityList()) == false) {
				vRet = false; 
				MainManager.handleMessage("Der wert aller Wahrscheinlichkeitswerte der Sexualitätsliste beträgt nicht 100.");
			}
			if (checkProbElementList(cultureEditor.getHairlengthList()) == false) {
				vRet = false;
				MainManager.handleMessage("Der wert aller Wahrscheinlichkeitswerte der Haarlängenliste beträgt nicht 100.");
			}
		}
		
		return vRet;
	}
	
}
