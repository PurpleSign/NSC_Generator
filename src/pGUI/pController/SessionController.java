/**	NSC_Generator v0.0		Dh	13.03.2021
 * 	
 * 	pGUI.pController
 * 	  EditorController
 * 	    SessionController
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

package pGUI.pController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import pGUI.EditorStage;
import pGUI.NPCEditorStage;
import pGUI.PackEditorStage;
import pGUI.PackManagerStage;
import pGUI.SessionManagerStage;
import pGUI.pTableElement.NameElement;
import pLogic.MainManager;
import pLogic.pEditors.Editor;
import pLogic.pEditors.NPCEditor;
import pLogic.pEditors.PackManager;
import pLogic.pEditors.SessionEditor;
import pLogic.pEditors.SessionManager;

public class SessionController extends ParentStageController {
	@FXML
	private MenuItem miNewSession, miDeleteSession, miSaveSession, miImportSession, miExportSession,
		miPreferences, miClose, miNewPack, miEditPack, miImportPack, miExportPack, miOpenPackManager,
		miNewNPC, miEditNPC, miRemoveNPC, miImportNPC, miExportNPC;
	
	@FXML
	private TextField tfSessionName, tfPackName, tfName, tfRace, tfCulture, tfJob, tfSex, tfSO, tfAge, 
		tfHeight, tfWeight, tfComplexion, tfHairlength, tfHaircolor, tfEyecolor, tfQuirk, tfSexuality;
	@FXML
	private TextArea taNote;
	@FXML
	private Button btAdd, btEdit, btRemove, btSave, btReset;
	
	@FXML
	private ListView<NameElement> lvNPC;
	
	private ObservableList<NameElement> liNPC;
	
	private SessionEditor sessionEditor;
	private EditorStage childStage;
	
	/**	Dh	09.03.2021
	 * 
	 */
	public SessionController() {
		super();
	}
	
	/**	Dh	09.03.2021
	 * 
	 */
	public void setUp(boolean pIsEdit, ParentStageControllerInterface pParentController, Editor pPackEditor) throws Exception {
		super.setUp(pIsEdit, pParentController, pPackEditor);
		
		if (pPackEditor != null) {
			if (pPackEditor instanceof SessionEditor) {
				sessionEditor = (SessionEditor)pPackEditor;
				
				tfSessionName.setText(sessionEditor.getSessionName());
				
				lvNPC.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
					try {
						if (newSel != null) sessionEditor.setCurrentNPCID(newSel.getId());
						else sessionEditor.setCurrentNPCID(-1);
						
						setEnabledNPCInfoElements(true);
						updateCurrentNPCInfo();
					} catch(Exception ex) {MainManager.handleException(ex);}
				});
				
				liNPC = FXCollections.observableArrayList();
				
				setEnabled();				
				updateAll();
			} else throw new Exception("06; su,SeEdCon");
		} else throw new Exception("04; sU,SeEdCon");
	}

//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	11.03.2021
	 * 
	 */
	@Override
	public void closeChildStage() {
		childStage.hide();
		
		try{
			if (childStage instanceof PackEditorStage) sessionEditor.updatePack();
			if ((childStage instanceof PackManagerStage) && (sessionEditor.getNewPackID() >= 0)) {
				sessionEditor.newSession();
			}
			
			updateAll();
		} catch(Exception ex) {MainManager.handleException(ex);}
		setEnabled();
	}

//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	11.03.2021
	 * 
	 */
	@FXML
	protected void newSession() {
		try {
			childStage = new PackManagerStage(false, this, new PackManager(sessionEditor) );
			setDisabled();
		}catch(Exception ex) {MainManager.handleException(ex);}
	}
	
	/**	Dh	11.03.2021
	 * 
	 */
	@FXML
	protected void loadSession() {
		try {
			childStage = new SessionManagerStage(true, this, new SessionManager(sessionEditor));
			setDisabled();
		}catch(Exception ex) {MainManager.handleException(ex);}
	}
	/**	Dh	08.03.2021
	 * 
	 */
	@FXML
	protected void saveSession() {
		try{
			if (checkAll() == true) {
				sessionEditor.setSessionName(tfSessionName.getText());
				sessionEditor.save();
			}
		} catch(Exception ex) {MainManager.handleException(ex);}
	}
	
	/**	Dh	08.03.2021
	 * 
	 */
	@FXML
	protected void close() {
		MainManager.closeApp();
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	09.03.2021
	 * 
	 */
	@FXML
	protected void newPack() {
		try {
			childStage = new PackEditorStage(false, this, sessionEditor.addPack());
			setDisabled();
		}catch(Exception ex) {MainManager.handleException(ex);}
	}
	/**	Dh	09.03.2021
	 * 
	 */
	@FXML
	protected void editPack() {
		try {
			childStage = new PackEditorStage(true, this, sessionEditor.editPack());
			setDisabled();
		}catch(Exception ex) {MainManager.handleException(ex);}
	}
	
	/**	Dh	09.03.2021
	 * 
	 */
	@FXML
	protected void openPackManager() {
		try {
			childStage = new PackManagerStage(true, this, new PackManager(sessionEditor) );
			setDisabled();
		}catch(Exception ex) {MainManager.handleException(ex);}
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	08.03.2021
	 * 
	 */
	@FXML
	protected void addNPC() {
		NPCEditor vNPCEditor;
		
		try {
			vNPCEditor = sessionEditor.addNPC();

			childStage = new NPCEditorStage(false, this, vNPCEditor);

			setDisabled();
		}catch(Exception ex) {MainManager.handleException(ex);}
	}
	/**	Dh	08.03.2021
	 * 
	 */
	@FXML
	protected void editNPC() {
		NPCEditor vNPCEditor;
		NameElement vCur = lvNPC.getSelectionModel().getSelectedItem();
		
		try {
			if (vCur != null) {
				vNPCEditor = sessionEditor.editNPC();
				
				childStage = new NPCEditorStage(true, this, vNPCEditor);
				
				setDisabled();
			}
		}catch(Exception ex) {MainManager.handleException(ex);}
	}
	
	/**	Dh	08.03.2021
	 * 
	 */
	@FXML
	protected void removeNPC() {
		NameElement vCur = lvNPC.getSelectionModel().getSelectedItem();
		
		if (vCur != null) {
			try {
				sessionEditor.removeNPC(vCur.getId());
				
				updateNPCList();
			}catch(Exception ex) {MainManager.handleException(ex);}
		}
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	08.03.2021
	 * 
	 */
	@FXML
	protected void apply() {
		try {sessionEditor.setNPCNote(taNote.getText());}
		catch(Exception ex) {MainManager.handleException(ex);}
	}
	/**	Dh	08.03.2021
	 * 
	 */
	@FXML
	protected void delete() {}
	/**	Dh	08.03.2021
	 * 
	 */
	@FXML
	public void back() {
		taNote.setText(sessionEditor.getNPCNote());
	}

//--------------------------------------------------------------------------------------------------------

	/**	Dh	09.03.2021
	 * 
	 * @param pEnabled
	 */
	private void setEnabledSelectedNPCMenuItem(boolean pEnabled) {
		if (lvNPC.getSelectionModel().isEmpty() == true) {
			miEditNPC.setDisable(true);
			miRemoveNPC.setDisable(true);
		} else {
			miEditNPC.setDisable(!pEnabled);
			miRemoveNPC.setDisable(!pEnabled);
		}
	}
	/**	Dh	08.03.2021
	 * 
	 * @param pEnabled
	 */
	private void setEnabledSelectedNPCButtons(boolean pEnabled) {
		if (lvNPC.getSelectionModel().isEmpty() == true) {
			btEdit.setDisable(true);
			btRemove.setDisable(true);
			
			btSave.setDisable(true);
			btReset.setDisable(true);
		} else {
			btEdit.setDisable(!pEnabled);
			btRemove.setDisable(!pEnabled);
			
			btSave.setDisable(!pEnabled);
			btReset.setDisable(!pEnabled);
		}
	}
	/**	Dh	13.03.2021
	 * 
	 * @param pEnabled
	 */
	private void setEnabledNPCInfoFields(boolean pEnabled) {
		if (lvNPC.getSelectionModel().isEmpty()) pEnabled = false;
		
		tfName.setDisable(!pEnabled);
		
		tfRace.setDisable(!pEnabled);
		tfCulture.setDisable(!pEnabled);
		tfJob.setDisable(!pEnabled);
		tfSex.setDisable(!pEnabled);
		
		tfSO.setDisable(!pEnabled);
		tfAge.setDisable(!pEnabled);
		tfHeight.setDisable(!pEnabled);
		tfWeight.setDisable(!pEnabled);
		
		tfComplexion.setDisable(!pEnabled);
		tfHairlength.setDisable(!pEnabled);
		tfHaircolor.setDisable(!pEnabled);
		tfEyecolor.setDisable(!pEnabled);
		
		tfQuirk.setDisable(!pEnabled);
		tfSexuality.setDisable(!pEnabled);
		
		taNote.setDisable(!pEnabled);
	}
	
	/**	Dh	09.03.2021
	 * 
	 * @param pEnabled
	 */
	private void setEnabledNPCInfoElements(boolean pEnabled) {
		setEnabledSelectedNPCMenuItem(pEnabled);
		
		setEnabledSelectedNPCButtons(pEnabled);
		setEnabledNPCInfoFields(pEnabled);
	}
	
	/**	Dh	08.03.2021
	 * 
	 * @param pEnabled
	 */
	protected void setEnabledAll(boolean pEnabled) {
		tfSessionName.setDisable(!pEnabled);
		
		setEnabledNPCInfoElements(pEnabled);
		
		btAdd.setDisable(!pEnabled);
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	13.03.2021
	 * 
	 * @throws Exception
	 */
	private void updateCurrentNPCInfo() throws Exception {
		
		if (!lvNPC.getSelectionModel().isEmpty()) {
			tfName.setText(sessionEditor.getNPCName());
			
			tfRace.setText(sessionEditor.getNPCRace());
			tfCulture.setText(sessionEditor.getNPCCulture());
			tfJob.setText(sessionEditor.getNPCJob());
			tfSex.setText(sessionEditor.getNPCSex());
			
			tfSO.setText(sessionEditor.getNPCSO());
			tfAge.setText("" + sessionEditor.getNPCAge());
			tfHeight.setText("" + sessionEditor.getNPCHeight());
			tfWeight.setText("" + sessionEditor.getNPCWeight());
			
			tfComplexion.setText(sessionEditor.getNPCComplexion());
			tfHairlength.setText(sessionEditor.getNPCHairlength());
			tfHaircolor.setText(sessionEditor.getNPCHaircolor());
			tfEyecolor.setText(sessionEditor.getNPCEyecolor());
			
			tfQuirk.setText(sessionEditor.getNPCQuirk());
			tfSexuality.setText(sessionEditor.getNPCSexuality());
			
			taNote.setText(sessionEditor.getNPCNote());
		} else {
			tfName.setText("");
			
			tfRace.setText("");
			tfCulture.setText("");
			tfJob.setText("");
			tfSex.setText("");
			
			tfSO.setText("");
			tfAge.setText("");
			tfHeight.setText("");
			tfWeight.setText("");
			
			tfComplexion.setText("");
			tfHairlength.setText("");
			tfHaircolor.setText("");
			tfEyecolor.setText("");
			
			tfQuirk.setText("");
			tfSexuality.setText("");
			
			taNote.setText("");
		}
	}
	/**	Dh	09.03.2021
	 * 
	 * @throws Exception
	 */
	private void updatePackName() throws Exception{
		tfPackName.setText(sessionEditor.getPackName());
	}
	
	/**	Dh	09.03.2021
	 * 
	 * @throws Exception
	 */
	private void updateNPCList() throws Exception {
		updateNameElementListElementList(sessionEditor.getNPCList(), liNPC, lvNPC);
	}
	
	/**	Dh	11.03.2021
	 * 
	 * @throws Exception
	 */
	protected void updateAll() throws Exception {
		updateNPCList();
		
		updatePackName();
		updateCurrentNPCInfo();
		
		tfSessionName.setText(sessionEditor.getSessionName());
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	11.03.2021
	 * 
	 * @return
	 */
	private boolean checkAll() {
		boolean vRet = true;
		
		if (tfSessionName.getText().equals("")) vRet = false;
		
		return vRet;
	}
	
}
