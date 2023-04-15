/**	NSC_Generator v0.0		Dh	27.08.2022
 * 	
 * 	pGUI.pController
 * 	  EditorController
 * 	    NPCEditorController
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
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;

import nsc_generator.pGUI.pTableElement.NameElement;
import nsc_generator.pLogic.MainManager;
import nsc_generator.pLogic.pEditors.Editor;
import nsc_generator.pLogic.pEditors.NPCEditor;

public class NPCEditorController extends EditorController {
	@FXML
	private TextField tfName, tfRace, tfCulture, tfJob, tfSex, tfSO, tfAge, tfHeight, 
		tfWeight, tfComplexion, tfHairlength, tfHaircolor, tfEyecolor, tfQuirk, tfSexuality;
	@FXML
	private Button btName, btRace, btCulture, btJob, btSex, btSO, btAge, btHeight, btWeight,
		btComplexion, btHairlength, btHaircolor, btEyecolor, btQuirk, btSexuality,
		btApply, btNew, btDelete, btBack;
	@FXML
	private CheckBox cbRaceLock, cbCultureLock, cbJobLock, cbAgeLock, cbHeightLock, cbWeightLock,
	 cbSOLock, cbQuirkLock, cbSexLock, cbSexualityLock, cbComplexionLock, cbEyecolorLock, 
	 cbHairlengthLock, chHaircolorLock;
			
	@FXML
	private ChoiceBox<NameElement> cbCurrentRegion;
	
	private ObservableList<NameElement> liPossibleRegions;
	
	private NPCEditor npcEditor;
	
	/**	Dh	07.03.2021
	 * 
	 */
	public NPCEditorController() {
		super();
	}
	
	/**	Dh	20.05.2021
	 * 
	 */
	public void setUp(boolean pIsEdit, boolean pIsMobile, ParentStageControllerInterface pParentController, Editor pPackEditor) throws Exception {
		super.setUp(pIsEdit, pIsMobile,pParentController, pPackEditor);
		
		if (pPackEditor != null) {
			if (pPackEditor instanceof NPCEditor) {
				npcEditor = (NPCEditor)pPackEditor;
				
				liPossibleRegions = FXCollections.observableArrayList();
				
				tfAge.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
				tfHeight.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
				tfWeight.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
				
				if (!pIsEdit) btDelete.setVisible(false);		
				
				updateAll();
			} else throw new Exception("06; su,NPCEdCon");
		} else throw new Exception("04; sU,NPCEdCon");
	}

//--------------------------------------------------------------------------------------------------------
	
	@FXML
	protected void genName() {
		
	}
	
	/**	Dh	12.03.2021
	 * 
	 */
	@FXML
	protected void genRace() {
		if (checkIfHaveCultureID()) {
			try{
				npcEditor.genRace();
				
				updateNPCFields();
			} catch(Exception ex) {MainManager.handleException(ex);}
		} else MainManager.handleMessage("Kultur fehlt!");
	}
	/**	Dh	12.03.2021
	 * 
	 */
	@FXML
	protected void genCulture() {
		if (checkIfRegionSet()) {
			try{
				npcEditor.genCulture(cbCurrentRegion.getSelectionModel().getSelectedItem().getId());
				
				updateNPCFields();
			} catch(Exception ex) {MainManager.handleException(ex);}
		} else MainManager.handleMessage("Aktuelle Region fehlt!");
	}
	
	@FXML
	protected void genJob() {
		
	}
	/**	Dh	12.03.2021
	 * 	
	 */
	@FXML
	protected void genSex() {
		if (checkIfHaveRaceID()) {
			try{
				npcEditor.genSex();
				
				updateNPCFields();
			} catch(Exception ex) {MainManager.handleException(ex);}
		}else MainManager.handleMessage("Rasse fehlt!");
	}
	
	/**	Dh	12.03.2021
	 * 
	 */
	@FXML
	protected void genSO() {
		if (checkIfHaveCultureID()) {
			try{
				npcEditor.genSO();
				
				updateNPCFields();
			} catch(Exception ex) {MainManager.handleException(ex);}
		} else MainManager.handleMessage("Kultur fehlt!");
	}
	/**	Dh	12.03.2021
	 * 
	 */
	@FXML
	protected void genAge() {
		if (checkIfHaveRaceID()) {
			try{
				npcEditor.genAge();
				
				updateNPCFields();
			} catch(Exception ex) {MainManager.handleException(ex);}
		} else MainManager.handleMessage("Rasse fehlt!");
	}
	/**	Dh	12.03.2021
	 * 
	 */
	@FXML
	protected void genHeight() {
		if (checkIfHaveRaceID()) {
			try{
				npcEditor.genHeight();
				
				updateNPCFields();
			} catch(Exception ex) {MainManager.handleException(ex);}
		} else MainManager.handleMessage("Rasse fehlt!");
	}
	/**	Dh	12.03.2021
	 * 
	 */
	@FXML
	protected void genWeight() {
		if (checkIfHaveRaceID() && chechIfHaveHeight()) {
			try{
				npcEditor.genWeight();
				
				updateNPCFields();
			} catch(Exception ex) {MainManager.handleException(ex);}
		} else MainManager.handleMessage("Rasse fehlt!");
	}
	
	/**	Dh	12.03.2021
	 * 
	 */
	@FXML
	protected void genComplexion() {
		if (checkIfHaveRaceID()) {
			try{
				npcEditor.genComplexion();
				
				updateNPCFields();
			} catch(Exception ex) {MainManager.handleException(ex);}
		} else MainManager.handleMessage("Rasse fehlt!");
	}
	/**	Dh	12.03.2021
	 * 
	 */
	@FXML
	protected void genHairlength() {
		if (checkIfHaveCultureID()) {
			try{
				npcEditor.genHairlength();
				
				updateNPCFields();
			} catch(Exception ex) {MainManager.handleException(ex);}
		} else MainManager.handleMessage("Kultur fehlt!");
	}
	/**	Dh	12.03.2021
	 * 
	 */
	@FXML
	protected void genHaircolor() {
		if (checkIfHaveRaceID()) {
			try{
				npcEditor.genHaircolor();
				
				updateNPCFields();
			} catch(Exception ex) {MainManager.handleException(ex);}
		} else MainManager.handleMessage("Rasse fehlt!");
	}
	/**	Dh	12.03.2021
	 * 
	 */
	@FXML
	protected void genEyecolor() {
		if (checkIfHaveRaceID()) {
			try{
				npcEditor.genEyecolor();
				
				updateNPCFields();
			} catch(Exception ex) {MainManager.handleException(ex);}
		} else MainManager.handleMessage("Rasse fehlt!");
	}
	
	/**	Dh	18.03.2021
	 * 
	 */
	@FXML
	protected void genQuirk() {
		try {
			npcEditor.genQuirk();
			
			updateNPCFields();
		} catch(Exception ex) {MainManager.handleException(ex);}
	}
	/**	Dh	12.03.2021
	 * 
	 */
	@FXML
	protected void genSexuality() {
		if (checkIfHaveCultureID()) {
			try{
				npcEditor.genSexuality();
				
				updateNPCFields();
			} catch(Exception ex) {MainManager.handleException(ex);}
		} else MainManager.handleMessage("Kultur fehlt!");
	}
	
	//----------------------------------------------------------------------------------------------------
	
	private void changeLock(int pLockInd) {		
		if ((0 <= pLockInd) && (pLockInd < 14)) {
			switch (pLockInd){
			case 0:
				
			}
		} else MainManager.handleException(new Exception("07; cLo,NPCEC"));
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	12.03.2021
	 * 
	 */
	@FXML
	protected void genAll() {
		try {
			if (!cbCurrentRegion.getSelectionModel().isEmpty()) {
				npcEditor.genAll(cbCurrentRegion.getSelectionModel().getSelectedItem().getId());
				
				updateNPCFields();
			} else MainManager.handleMessage("Aktuelle Region fehlt!");
		} catch(Exception ex) {MainManager.handleException(ex);}
	}
	
	/**	Dh	12.03.2021
	 * 
	 */
	@FXML
	protected void apply() {
		if (checkToApply() == true) {
			try {
				npcEditor.setName(tfName.getText());
				
				npcEditor.setRace(tfRace.getText());
				npcEditor.setCulture(tfCulture.getText());
				npcEditor.setJob(tfJob.getText());
				npcEditor.setSex(tfSex.getText());
				
				npcEditor.setSO(tfSO.getText());
				if (!tfAge.getText().equals("")) npcEditor.setAge( Integer.parseInt(tfAge.getText()) );
				if (!tfHeight.getText().equals("")) npcEditor.setHeight( Integer.parseInt(tfHeight.getText()) );
				if (!tfWeight.getText().equals("")) npcEditor.setWeight( Integer.parseInt(tfWeight.getText()) );
				
				npcEditor.setComplexion(tfComplexion.getText());
				npcEditor.setHairlength(tfHairlength.getText());
				npcEditor.setHaircolor(tfHaircolor.getText());
				npcEditor.setEyecolor(tfEyecolor.getText());
				
				npcEditor.setQuirk(tfQuirk.getText());
				npcEditor.setSexuality(tfSexuality.getText());
				
				if (!isEdit) npcEditor.add();
				
				parentController.closeChildStage();
			}catch(Exception ex) {MainManager.handleException(ex);}
			
		}
	}
	/**	Dh	08.03.2021
	 * 
	 */
	@FXML
	protected void delete() {
		try{
			npcEditor.remove();
			parentController.closeChildStage();
		} catch (Exception ex) {MainManager.handleException(ex);}
	}
	/**	Dh	08.03.2021
	 * 
	 */
	@FXML
	public void back() {
		parentController.closeChildStage();
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	08.03.2021
	 * 
	 * @throws Exception
	 */
	protected void updatePossibleRegions() throws Exception {
		updateChoiceBoxList(npcEditor.getPossibleRegionCultures(), liPossibleRegions, cbCurrentRegion);
	}
	/**	Dh	12.03.2021
	 * 
	 * @throws Exception
	 */
	protected void updateNPCFields() throws Exception{
		tfName.setText(npcEditor.getName());
		
		tfRace.setText(npcEditor.getRace());
		tfCulture.setText(npcEditor.getCulture());
		tfJob.setText(npcEditor.getJob());
		tfSex.setText(npcEditor.getSex());
		
		tfSO.setText(npcEditor.getSO());
		if (npcEditor.getAge() != 0) tfAge.setText("" + npcEditor.getAge());
		else tfAge.setText("");
		if (npcEditor.getHeight() != 0) tfHeight.setText("" + npcEditor.getHeight());
		else tfHeight.setText("");
		if (npcEditor.getWeight() != 0) tfWeight.setText("" + npcEditor.getWeight());
		else tfWeight.setText("");
		
		tfComplexion.setText(npcEditor.getComplexion());
		tfHairlength.setText(npcEditor.getHairlength());
		tfHaircolor.setText(npcEditor.getHaircolor());
		tfEyecolor.setText(npcEditor.getEyecolor());
		
		tfQuirk.setText(npcEditor.getQuirk());
		tfSexuality.setText(npcEditor.getSexuality());
	}
	
	/**	Dh	12.03.2021
	 * 
	 */
	protected void updateAll() throws Exception {
		updatePossibleRegions();
		updateNPCFields();
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	08.03.2021
	 * 
	 * @return
	 */
	private boolean checkToApply() {
		boolean vRet = true;
		
		if (tfName.getText().equals("")) vRet = false;
		
		return vRet;
	}
	
	/**	Dh	12.03.2021
	 * 
	 * @return
	 */
	private boolean checkIfRegionSet() {
		return !cbCurrentRegion.getSelectionModel().isEmpty();
	}
	/**	Dh	12.03.2021
	 * 
	 * @return
	 */
	private boolean checkIfHaveCultureID() {
		return npcEditor.hasCultureID();
	}
	/**	Dh	12.03.2021
	 * 
	 * @return
	 */
	private boolean checkIfHaveRaceID() {
		return npcEditor.hasRaceID();
	}
	/**	Dh	12.03.2021
	 * 
	 * @return
	 */
	private boolean chechIfHaveHeight() {
		return (npcEditor.getHeight() != 0);
	}
	
}
