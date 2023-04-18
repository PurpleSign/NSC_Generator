/**	NSC_Generator v0.1		Dh	22.05.2021
 * 	
 * 	pGUI.pController
 * 	  EditorController
 * 	    ManagerController
 * 	      SessionkManagerController
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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import org.nsc_generator.gui.stages.PackManagerStage;
import org.nsc_generator.gui.tableElements.NameElement;
import org.nsc_generator.logic.MainManager;
import org.nsc_generator.logic.MainManagerInterface;
import org.nsc_generator.logic.MainManagerMobile;
import org.nsc_generator.logic.editors.Editor;
import org.nsc_generator.logic.editors.PackManager;
import org.nsc_generator.logic.editors.SessionManager;

public class SessionManagerController extends ManagerController {
	@FXML
	private TextField tfPack;
	
	@FXML
	private ListView<NameElement> lvNPCs;
	
	private ObservableList<NameElement> liNPCs;
	
	private SessionManager sessionManager;
	
	/**	Dh	10.03.2021
	 * 
	 */
	public SessionManagerController() {
		super();
	}
	
	/**	Dh	22.05.2021
	 * 
	 * 	Old, please don't use anymore.
	 */
	public void setUp(boolean pIsEdit, boolean pIsMobile, ParentStageControllerInterface pParentController, Editor pSessionManager) throws Exception {
		setUp(pIsEdit, pIsMobile, pParentController, pSessionManager, null);
	}
	/**	Dh	22.05.2021
	 * 
	 */
	public void setUp(boolean pIsEdit, boolean pIsMobile, ParentStageControllerInterface pParentController, Editor pSessionManager, MainManagerInterface pMainManager) throws Exception {
		super.setUp(pIsEdit, pIsMobile, pParentController, pSessionManager, pMainManager);
		
		if (pSessionManager != null) {
			if (pSessionManager instanceof SessionManager) {
				sessionManager = (SessionManager)pSessionManager;
				
				lvNameElements.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
					try{
						if (newSel != null) sessionManager.setSession(newSel.getId());
						
						updateNameElementInfos();
						setEnabledNameElementElements(true);
					} catch(Exception ex) {MainManager.handleException(ex);}
				});
				
				liNameElements = FXCollections.observableArrayList();
				
				lvNameElements.setItems(liNameElements);
				
				liNPCs = FXCollections.observableArrayList();
				lvNPCs.setItems(liNPCs);
				
				btExport.setDisable(true);
				btImport.setDisable(true);
				btDirectory.setDisable(true);
				
				if (!pIsEdit) btCancel.setText("Beenden");
				
				updateAll();
				setEnabled();
			} else throw new Exception("06; su,SeMaCon");
		} else throw new Exception("04; sU,SeMaCon");
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	11.03.2021
	 * 
	 */
	@Override
	public void closeChildStage() {
		childStage.hide();
		
		try{
			if (childStage instanceof PackManagerStage) {
				if (sessionManager.getCurrentPackID() >= 0) {
					sessionManager.newSession();
					
					if (isEdit) {
						sessionManager.applyCurrentSession();
						parentController.closeChildStage();
					}
					else MainManager.startSessionEditor(sessionManager.getSessionEditor());
				}
				else sessionManager.setCurrentPack(-1);
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
	protected void addNameElement() {
		try {
			childStage = new PackManagerStage(true, this, new PackManager(sessionManager));
			setDisabled();
		}catch(Exception ex) {MainManager.handleException(ex);}
	}
	
	/**	Dh	19.04.2021
	 * 
	 */
	@FXML
	protected void importNameElement() {
		File vFile;
		FileChooser vFileChooser = new FileChooser();
		
		configureFileChooser(vFileChooser, 1);
		
		vFileChooser.setTitle("Wähle Session Datei");
		
		vFile = vFileChooser.showOpenDialog(MainManager.getPrimaryStage());
		
		if (vFile != null) {
			try{
				sessionManager.importSession(vFile);
				
				updateNameElementList();
				updateNameElementInfos();
			} catch(Exception ex) {MainManager.handleException(ex);}
		}
	}
	/**	Dh	19.04.2021
	 * 
	 */
	@FXML
	protected void exportNameElement() {
		File vFile;
		FileChooser vFileChooser = new FileChooser();
		
		configureFileChooser(vFileChooser, 1);
		
		vFileChooser.setTitle("Wähle Session Datei");
		
		vFile = vFileChooser.showSaveDialog(MainManager.getPrimaryStage());
		
		if (vFile != null) {
			try{sessionManager.exportSession(vFile);
			} catch(Exception ex) {MainManager.handleException(ex);}
		}
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	11.03.2021
	 * 
	 */
	@FXML
	protected void apply() {
		try {
			if (isEdit) {
				sessionManager.applyCurrentSession();
				parentController.closeChildStage();
			}
			else MainManager.startSessionEditor(sessionManager.getSessionEditor());
		}catch(Exception ex) {MainManager.handleException(ex);}
	}
	/**	Dh	11.03.2021
	 * 
	 */
	@FXML
	protected void delete() {
		try{
			lvNameElements.getSelectionModel().clearSelection();
			sessionManager.delete();
			
			updateAll();
		}catch(Exception ex) {MainManager.handleException(ex);}
	}
	/**	Dh	22.05.2021
	 * 
	 */
	@FXML
	public void back() {
		if (isEdit) parentController.closeChildStage();
		else MainManager.closeApp();
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	10.03.2021
	 * 
	 */
	protected void setEnabledSelectetNameElementFields(boolean pEnabled) {
		if (lvNameElements.getSelectionModel().isEmpty() == true) {
			tfPack.setDisable(true);
			lvNPCs.setDisable(true);
		} else {
			tfPack.setDisable(!pEnabled);
			lvNPCs.setDisable(!pEnabled);
		}
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	10.03.2021
	 * 
	 */
	protected void updateNameElementInfos() throws Exception{
		if (!lvNameElements.getSelectionModel().isEmpty()) {
			tfName.setText(sessionManager.getSessionName());
			tfPack.setText(sessionManager.getPackName());
			
			updateNameElementListElementList(sessionManager.getNPCList(), liNPCs, lvNPCs);
		}
	}
	/**	Dh	10.03.2021
	 * 
	 */
	protected void updateNameElementList() throws Exception{
		updateNameElementListElementList(sessionManager.getSessionList(), liNameElements, lvNameElements);
	}
	
	protected void updateDirectoryField() throws Exception{
		
	}
	
}
