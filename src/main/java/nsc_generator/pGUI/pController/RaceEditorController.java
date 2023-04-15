/**	NSC_Generator v0.0		Dh	20.05.2021
 * 	
 * 	pGUI.pController
 * 	  EditorController
 * 	    RaceEditorController
 * 
 * 	CaseNumber (int):
 * 		0	Sex
 * 		1	Haircolor
 * 		2 	Eyecolor
 * 		3	Complexion
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
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.IntegerStringConverter;
import nsc_generator.pGUI.ProbElementEditorStage;
import nsc_generator.pGUI.pTableElement.NameElement;
import nsc_generator.pGUI.pTableElement.ProbElementTableElement;
import nsc_generator.pLogic.MainManager;
import nsc_generator.pLogic.pEditors.Editor;
import nsc_generator.pLogic.pEditors.RaceEditor;

public class RaceEditorController extends ParentStageController implements ParentProbElementEditorControllerInterface {
	@FXML
	private TextField tfName, tfAge_Number, tfAge_Side, tfAge_Offset, tfHeight_Number, tfHeight_Side,
		tfHeight_Offset, tfWeight_Number, tfWeight_Side, tfWeight_Offset;
	@FXML
	private Button btHair_Add, btHair_Edit, btHair_Remove, btSex_Add, btSex_Edit, btSex_Remove, 
		btEye_Add, btEye_Edit, btEye_Remove, btComplexion_Add, btComplexion_Edit, btComplexion_Remove,
		btApply, btDelete, btBack;
	@FXML
	private CheckBox cbSubrace;
	
	@FXML
	private ChoiceBox<NameElement> cbParent;
	@FXML
	private TableView<ProbElementTableElement> tHair, tSex, tEye, tComplexion;
	@FXML
	private TableColumn<ProbElementTableElement, String> tcHair_Name, tcSex_Name, tcEye_Name, tcComplexion_Name;
	@FXML
	private TableColumn<ProbElementTableElement, Double> tcHair_Prob, tcSex_Prob, tcEye_Prob, tcComplexion_Prob;
	
	private ObservableList<NameElement> liParent;
	private ObservableList<ProbElementTableElement> liSex, liHair, liEye, liComplexion;
	
	private RaceEditor raceEditor;
	//protected ProbElementEditorStage childStage;
	
	/**	Dh	03.03.2021
	 * 
	 */
	public RaceEditorController() {
		super();
	}
	
	/**	Dh	20.05.2021
	 * 	
	 */
	public void setUp(boolean pIsEdit, boolean pIsMobile, ParentStageControllerInterface pParentController, Editor pRaceEditor) throws Exception{
		super.setUp(pIsEdit, pIsMobile, pParentController, pRaceEditor);
		
		if (pRaceEditor != null) {
			if (pRaceEditor instanceof RaceEditor) {
				raceEditor = (RaceEditor)pRaceEditor;
				
				tfName.setText(raceEditor.getName());
				
				cbSubrace.setSelected(raceEditor.isSubrace());
				liParent = FXCollections.observableArrayList();
				
				tfAge_Number.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
				tfAge_Side.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
				tfAge_Offset.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
				//-----
				tfHeight_Number.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
				tfHeight_Side.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
				tfHeight_Offset.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
				//-----
				tfWeight_Number.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
				tfWeight_Side.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
				tfWeight_Offset.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
				
				tSex.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
					setEnabledSexListButtons(true);
				});
				tComplexion.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
					setEnabledComplexionListButtons(true);
				});
				tHair.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
					setEnabledHairListButtons(true);
				});
				tEye.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
					setEnabledEyeListButtons(true);
				});
				
				liSex = FXCollections.observableArrayList();
				liComplexion = FXCollections.observableArrayList();
				liHair = FXCollections.observableArrayList();
				liEye = FXCollections.observableArrayList();
				
				tcSex_Name.setCellValueFactory(new PropertyValueFactory<ProbElementTableElement, String>("name"));
				tcSex_Prob.setCellValueFactory(new PropertyValueFactory<ProbElementTableElement, Double>("probability"));
				
				tcComplexion_Name.setCellValueFactory(new PropertyValueFactory<ProbElementTableElement, String>("name"));
				tcComplexion_Prob.setCellValueFactory(new PropertyValueFactory<ProbElementTableElement, Double>("probability"));
				
				tcHair_Name.setCellValueFactory(new PropertyValueFactory<ProbElementTableElement, String>("name"));
				tcHair_Prob.setCellValueFactory(new PropertyValueFactory<ProbElementTableElement, Double>("probability"));
				
				tcEye_Name.setCellValueFactory(new PropertyValueFactory<ProbElementTableElement, String>("name"));
				tcEye_Prob.setCellValueFactory(new PropertyValueFactory<ProbElementTableElement, Double>("probability"));
				
				if (!pIsEdit) btDelete.setVisible(false);
				
				updateAll();
				setEnabled();
				
				if (cbSubrace.isSelected()) cbParent.getSelectionModel().select(getNameElementFromNameElementListByID((int)raceEditor.getParentRace()[0], liParent));
			} else throw new Exception("06; su,RaEdCon");
		} else throw new Exception("04; sU,RaEdCon");
	}

//--------------------------------------------------------------------------------------------------------

	/**	Dh	12.03.2021
	 * 
	 * 	CaseNumber (int):
	 * 		0	Sex
	 * 		1	Haircolor
	 * 		2 	Eyecolor
	 * 		3 	Complexion
	 */
	public void setProbElement(int pCaseNumber, int pProbElementID, String pName, double pProb) throws Exception{
		switch(pCaseNumber) {
		case 0:
			raceEditor.setSexElement(pProbElementID, pName, pProb);
			break;
		case 1:
			raceEditor.setHaircolorElement(pProbElementID, pName, pProb);
			break;
		case 2:
			raceEditor.setEyecolorElement(pProbElementID, pName, pProb);
			break;
		case 3:
			raceEditor.setComplexionElement(pProbElementID, pName, pProb);
			break;	
		default:
			throw new Exception("02; sPE,RaEdCon");
		}
	}
	/**	Dh	12.03.2021
	 * 
	 * 	CaseNumber (int):
	 * 		0	Sex
	 * 		1	Haircolor
	 * 		2 	Eyecolor
	 * 		3	Complexion
	 */
	public Object[] getProbElement(int pCaseNumber, int pProbElementID) throws Exception{
		Object[] vRet = null;
		
		switch(pCaseNumber) {
		case 0:
			vRet = raceEditor.getSexElement(pProbElementID);
			break;
		case 1:
			vRet = raceEditor.getHaircolorElement(pProbElementID);
			break;
		case 2:
			vRet = raceEditor.getEyecolorElement(pProbElementID);
			break;	
		case 3:
			vRet = raceEditor.getComplexionElement(pProbElementID);
			break;
		default:
			throw new Exception("02; gPE,RaEdCon");
		}
		
		return vRet;
	}
	
	/**	Dh	12.03.2021
	 * 
	 * 	CaseNumber (int):
	 * 		0	Sex
	 * 		1	Haircolor
	 * 		2 	Eyecolor
	 * 		3 Complexion
	 */
	public void addProbElement(int pCaseNumber, String pName, double pProb) throws Exception{
		switch(pCaseNumber) {
		case 0:
			raceEditor.addSexElement(pName, pProb);
			break;
		case 1:
			raceEditor.addHaircolorElement(pName, pProb);
			break;
		case 2:
			raceEditor.addEyecolorElement(pName, pProb);
			break;	
		case 3:
			raceEditor.addComplexionElement(pName, pProb);
			break;
		default:
			throw new Exception("02; aPE,RaEdCon");
		}
	}
	/**	Dh	12.03.2021
	 * 
	 * 	CaseNumber (int):
	 * 		0	Sex
	 * 		1	Haircolor
	 * 		2 	Eyecolor
	 * 		3	Complexion
	 */
	public void removeProbElement(int pCaseNumber, int pProbElementID) throws Exception{
		switch(pCaseNumber) {
		case 0:
			raceEditor.removeSexElement(pProbElementID);
			break;
		case 1:
			raceEditor.removeHaircolorElement(pProbElementID);
			break;
		case 2:
			raceEditor.removeEyecolorElement(pProbElementID);
			break;	
		case 3:
			raceEditor.removeComplexionElement(pProbElementID);
			break;
		default:
			throw new Exception("02; rPE,RaEdCon");
		}
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	04.03.2021
	 * 
	 */
	@FXML
	protected void selectSubrace() {
		setEnabledParentListChoiceBox(true);
		try{updateParentChoiceBox();}
		catch(Exception ex) {MainManager.handleException(ex);}
	}
	
	/**	Dh	03.03.2021
	 * 	
	 * 	CaseNumber (int):
	 * 		0	Sex
	 * 		1	Haircolor
	 * 		2 	Eyecolor
	 * 		3	Complexion
	 */
	@FXML
	protected void addSex() {
		try {
			childStage = new ProbElementEditorStage("Füge Geschlecht hinzu", false, null, 0, -1, this);
			
			setDisabled();
		}catch(Exception ex) {MainManager.handleException(ex);}
	}
	/**	Dh	12.03.2021
	 * 	
	 * 	CaseNumber (int):
	 * 		0	Sex
	 * 		1	Haircolor
	 * 		2 	Eyecolor
	 * 		3	Complexion
	 * 
	 */
	@FXML
	protected void addComplexion() {
		try {
			childStage = new ProbElementEditorStage("Füge Teint hinzu", false, null, 3, -1, this);
			
			setDisabled();
		}catch(Exception ex) {MainManager.handleException(ex);}
	}
	/**	Dh	04.03.2021
	 * 
	 * 	CaseNumber (int):
	 * 		0	Sex
	 * 		1	Haircolor
	 * 		2 	Eyecolor
	 * 		3	Complexion
	 */
	@FXML
	protected void addHaircolor() {
		try {
			childStage = new ProbElementEditorStage("Füge Haarfarbe hinzu", false, null, 1, -1, this);
			
			setDisabled();
		}catch(Exception ex) {MainManager.handleException(ex);}
	}
	/**	Dh	04.03.2021
	 * 
	 * 	CaseNumber (int):
	 * 		0	Sex
	 * 		1	Haircolor
	 * 		2 	Eyecolor
	 * 		3	Complexion
	 */
	@FXML
	protected void addEyecolor() {
		try {
			childStage = new ProbElementEditorStage("Füge Augenfarbe hinzu", false, null, 2, -1, this);
			
			setDisabled();
		}catch(Exception ex) {MainManager.handleException(ex);}
	}
	
	/**	Dh	04.03.2021
	 * 
	 * 	CaseNumber (int):
	 * 		0	Sex
	 * 		1	Haircolor
	 * 		2 	Eyecolor
	 * 		3	Complexion
	 */
	@FXML
	protected void editSex() {
		ProbElementTableElement vCur = tSex.getSelectionModel().getSelectedItem();
		
		if (vCur != null) {
			childStage = new ProbElementEditorStage("Editiere Geschlecht", true, null, 0, vCur.getId(), this);
			
			setDisabled();
		}
	}
	/**	Dh	12.03.2021
	 * 
	 * 	CaseNumber (int):
	 * 		0	Sex
	 * 		1	Haircolor
	 * 		2 	Eyecolor
	 * 		3	Complexion
	 * 
	 */
	@FXML
	protected void editComplexion() {
		ProbElementTableElement vCur = tComplexion.getSelectionModel().getSelectedItem();
		
		if (vCur != null) {
			childStage = new ProbElementEditorStage("Editiere Teint", true, null, 3, vCur.getId(), this);
			
			setDisabled();
		}
	}
	/**	Dh	04.03.2021
	 * 
	 * 	CaseNumber (int):
	 * 		0	Sex
	 * 		1	Haircolor
	 * 		2 	Eyecolor
	 * 		3	Complexion
	 */
	@FXML
	protected void editHaircolor() {
		ProbElementTableElement vCur = tHair.getSelectionModel().getSelectedItem();
		
		if (vCur != null) {
			childStage = new ProbElementEditorStage("Editiere Haarfarbe", true, null, 1, vCur.getId(), this);
			
			setDisabled();
		}
	}
	/**	Dh	04.03.2021
	 * 
	 * 	CaseNumber (int):
	 * 		0	Sex
	 * 		1	Haircolor
	 * 		2 	Eyecolor
	 * 		3	Complexion
	 */
	@FXML
	protected void editEyecolor() {
		ProbElementTableElement vCur = tEye.getSelectionModel().getSelectedItem();
		
		if (vCur != null) {
			childStage = new ProbElementEditorStage("Editiere Haarfarbe", true, null, 2, vCur.getId(), this);
			
			setDisabled();
		}
	}
	
	/**	Dh	04.03.2021
	 * 
	 * 		CaseNumber (int):
	 * 		0	Sex
	 * 		1	Haircolor
	 * 		2 	Eyecolor
	 * 		3	Complexion
	 */
	@FXML
	protected void removeSex() {
		ProbElementTableElement vCur = tSex.getSelectionModel().getSelectedItem();
		
		if (vCur != null) {
			try {
				removeProbElement(0, vCur.getId());
				
				updateSexList();
			}catch(Exception ex) {MainManager.handleException(ex);}
		}
	}
	/**	Dh	12.03.2021
	 * 
	 * 		CaseNumber (int):
	 * 		0	Sex
	 * 		1	Haircolor
	 * 		2 	Eyecolor
	 * 		3	Complexion
	 * 
	 */
	@FXML
	protected void removeComplexion() {
		ProbElementTableElement vCur = tComplexion.getSelectionModel().getSelectedItem();
		
		if (vCur != null) {
			try {
				removeProbElement(3, vCur.getId());
				
				updateComplexionList();
			}catch(Exception ex) {MainManager.handleException(ex);}
		}
	}
	/**	Dh	04.03.2021
	 * 
	 * 		CaseNumber (int):
	 * 		0	Sex
	 * 		1	Haircolor
	 * 		2 	Eyecolor
	 * 		3	Complexion
	 */
	@FXML
	protected void removeHaircolor() {
		ProbElementTableElement vCur = tHair.getSelectionModel().getSelectedItem();
		
		if (vCur != null) {
			try {
				removeProbElement(1, vCur.getId());
				
				updateHaircolorList();
			}catch(Exception ex) {MainManager.handleException(ex);}
		}
	}
	/**	Dh	04.03.2021
	 * 
	 * 		CaseNumber (int):
	 * 		0	Sex
	 * 		1	Haircolor
	 * 		2 	Eyecolor
	 * 		3	Complexion
	 */
	@FXML
	protected void removeEyecolor() {
		ProbElementTableElement vCur = tEye.getSelectionModel().getSelectedItem();
		
		if (vCur != null) {
			try {
				removeProbElement(2, vCur.getId());
				
				updateEyecolorList();
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
		int[] vCur;
		
		try{
			if (checkInputs() == true) {
				raceEditor.setName(tfName.getText());
				
				if (cbSubrace.isSelected()) {
					if (raceEditor.isSubrace()) raceEditor.setParentRace(cbParent.getSelectionModel().getSelectedItem().getId());
					else raceEditor.transformToSubrace((cbParent.getSelectionModel().getSelectedItem().getId()));
				} else 	if (raceEditor.isSubrace()) raceEditor.transformFromSubrace();
				
				vCur = preparingGenElementFieldsForInput(tfAge_Number.getText(), tfAge_Side.getText(), tfAge_Offset.getText());
				raceEditor.setAge(vCur[0], vCur[1], vCur[2]);
				vCur = preparingGenElementFieldsForInput(tfHeight_Number.getText(), tfHeight_Side.getText(), tfHeight_Offset.getText());
				raceEditor.setHeight(vCur[0], vCur[1], vCur[2]);
				vCur = preparingGenElementFieldsForInput(tfWeight_Number.getText(), tfWeight_Side.getText(), tfWeight_Offset.getText());
				raceEditor.setWeight(vCur[0], vCur[1], vCur[2]);
				
				raceEditor.setSexList();
				raceEditor.setComplexionList();
				raceEditor.setHaircolorList();
				raceEditor.setEyecolorList();
				
				if (!isEdit) raceEditor.add();
				
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
				raceEditor.remove();
				parentController.closeChildStage();
			}
		} catch(Exception ex) {MainManager.handleException(ex);}
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
	/**	Dh	12.03.2021
	 * 
	 * @param pEnabled
	 */
	private void setEnabledComplexionListButtons(boolean pEnabled) {
		if (tComplexion.getSelectionModel().isEmpty() == true) {
			btComplexion_Edit.setDisable(true);
			btComplexion_Remove.setDisable(true);
		} else {
			btComplexion_Edit.setDisable(!pEnabled);
			btComplexion_Remove.setDisable(!pEnabled);
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
	/**	Dh	04.03.2021
	 * 
	 * @param pEnabled
	 */
	private void setEnabledEyeListButtons(boolean pEnabled) {
		if (tEye.getSelectionModel().isEmpty() == true) {
			btEye_Edit.setDisable(true);
			btEye_Remove.setDisable(true);
		} else {
			btEye_Edit.setDisable(!pEnabled);
			btEye_Remove.setDisable(!pEnabled);
		}
	}
	//-----
	/**	Dh	04.03.2021
	 * 
	 * @param pEnabled
	 */
	private void setEnabledParentListChoiceBox(boolean pEnabled) {
		if (!cbSubrace.isSelected()) cbParent.setDisable(true);
		else cbParent.setDisable(!pEnabled);
	}
	
	/**	Dh	12.03.2021
	 * 
	 * @param pEnabled
	 */
	protected void setEnabledAll(boolean pEnabled) {
		tfName.setDisable(!pEnabled);
		
		cbSubrace.setDisable(!pEnabled);
		setEnabledParentListChoiceBox(pEnabled);
		
		btSex_Add.setDisable(!pEnabled);
		btComplexion_Add.setDisable(!pEnabled);
		btHair_Add.setDisable(!pEnabled);
		btEye_Add.setDisable(!pEnabled);
		
		setEnabledSexListButtons(pEnabled);
		setEnabledComplexionListButtons(pEnabled);
		setEnabledHairListButtons(pEnabled);
		setEnabledEyeListButtons(pEnabled);
		
		btApply.setDisable(!pEnabled);
		btDelete.setDisable(!pEnabled);
		btBack.setDisable(!pEnabled);
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	04.03.2021
	 * 
	 * @throws Exception
	 */
	private void updateAgeFields() throws Exception {
		updateGenElementTextfields(raceEditor.getAge(), tfAge_Number, tfAge_Side, tfAge_Offset);
	}
	/**	Dh	04.03.2021
	 * 
	 * @throws Exception
	 */
	private void updateHeightFields() throws Exception {
		updateGenElementTextfields(raceEditor.getHeight(), tfHeight_Number, tfHeight_Side, tfHeight_Offset);
	}
	/**	Dh	04.03.2021
	 * 
	 * @throws Exception
	 */
	private void updateWeightFields() throws Exception {
		updateGenElementTextfields(raceEditor.getWeight(), tfWeight_Number, tfWeight_Side, tfWeight_Offset);
	}
	//-----
	/**	Dh	04.03.2021
	 * 
	 * @throws Exception
	 */
	private void updateSexList() throws Exception {
		updateProbElementTableElementList(raceEditor.getSexList(), liSex, tSex);
	}
	/**	Dh	12.03.2021
	 * 
	 * @throws Exception
	 */
	private void updateComplexionList() throws Exception {
		updateProbElementTableElementList(raceEditor.getComplexionList(), liComplexion, tComplexion);
	}
	/**	Dh	04.03.2021
	 * 
	 * @throws Exception
	 */
	private void updateHaircolorList() throws Exception {
		updateProbElementTableElementList(raceEditor.getHaircolorList(), liHair, tHair);
	}
	/**	Dh	04.03.2021
	 * 
	 * @throws Exception
	 */
	private void updateEyecolorList() throws Exception {
		updateProbElementTableElementList(raceEditor.getEyecolorList(), liEye, tEye);
	}
	//-----
	/**	Dh	08.03.2021
	 * 
	 * @throws Exception
	 */
	private void updateParentChoiceBox() throws Exception{
		if (cbSubrace.isSelected() == true) 
			updateChoiceBoxList(raceEditor.getPossibleParentList(), liParent, cbParent);
	}
	
	/**	Dh	04.03.2021
	 * 
	 */
	private void updateFields() throws Exception{
		updateAgeFields();
		updateHeightFields();
		updateWeightFields();
	}
	/**	Dh	12.03.2021
	 * 
	 * @throws Exception
	 */
	private void updateLists() throws Exception{
		updateSexList();
		updateComplexionList();
		updateHaircolorList();
		updateEyecolorList();
	}
	
	/**	Dh	04.03.2021
	 * 
	 * @throws Exception
	 */
	protected void updateAll() throws Exception{
		updateLists();
		updateParentChoiceBox();
		updateFields();
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	12.03.2021
	 * 
	 * @return
	 * @throws Exception
	 */
	private boolean checkInputs() throws Exception {
		boolean vRet = true;
		
		if (tfName.getText().equals("")) vRet = false;
		
		if (cbSubrace.isSelected()) {
			if (cbParent.getSelectionModel().getSelectedItem() == null) vRet = false;
			
			if ((!tfAge_Number.getText().equals("")) && ( Integer.parseInt(tfAge_Number.getText()) < 0)) vRet = false;
			if ((!tfAge_Side.getText().equals("")) && ( Integer.parseInt(tfAge_Side.getText()) < 0)) vRet = false;
			//-----
			if ((!tfHeight_Number.getText().equals("")) && ( Integer.parseInt(tfHeight_Number.getText()) < 0)) vRet = false;
			if ((!tfHeight_Side.getText().equals("")) && ( Integer.parseInt(tfHeight_Side.getText()) < 0)) vRet = false;
			//-----
			if ((!tfWeight_Number.getText().equals("")) && ( Integer.parseInt(tfWeight_Number.getText()) < 0)) vRet = false;
			if ((!tfWeight_Side.getText().equals("")) && ( Integer.parseInt(tfWeight_Side.getText()) < 0)) vRet = false;
			
			if ((raceEditor.getSexList().getContentNumber() >= 1) && (checkProbElementList(raceEditor.getSexList()) == false)) vRet = false; 
			if ((raceEditor.getComplexionList().getContentNumber() >= 1) && (checkProbElementList(raceEditor.getComplexionList()) == false)) vRet = false; 
			if ((raceEditor.getHaircolorList().getContentNumber() >= 1) &&(checkProbElementList(raceEditor.getHaircolorList()) == false)) vRet = false;
			if ((raceEditor.getEyecolorList().getContentNumber() >= 1) &&(checkProbElementList(raceEditor.getEyecolorList()) == false)) vRet = false;
		}else {
			if ((tfAge_Number.getText().equals("")) || ( Integer.parseInt(tfAge_Number.getText()) < 0)) vRet = false;
			if ((tfAge_Side.getText().equals("")) || ( Integer.parseInt(tfAge_Side.getText()) < 0)) vRet = false;
			if (tfAge_Offset.getText().equals("")) vRet = false;
			//-----
			if ((tfHeight_Number.getText().equals("")) || ( Integer.parseInt(tfHeight_Number.getText()) < 0)) vRet = false;
			if ((tfHeight_Side.getText().equals("")) || ( Integer.parseInt(tfHeight_Side.getText()) < 0)) vRet = false;
			if (tfHeight_Offset.getText().equals("")) vRet = false;
			//-----
			if ((tfWeight_Number.getText().equals("")) || ( Integer.parseInt(tfWeight_Number.getText()) < 0)) vRet = false;
			if ((tfWeight_Side.getText().equals("")) || ( Integer.parseInt(tfWeight_Side.getText()) < 0)) vRet = false;
			if (tfWeight_Offset.getText().equals("")) vRet = false;
			
			if (checkProbElementList(raceEditor.getSexList()) == false) vRet = false; 
			if (checkProbElementList(raceEditor.getComplexionList()) == false) vRet = false; 
			if (checkProbElementList(raceEditor.getHaircolorList()) == false) vRet = false;
			if (checkProbElementList(raceEditor.getEyecolorList()) == false) vRet = false;
		}
		
		return vRet;
	}
	
}
