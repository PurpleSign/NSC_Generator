/**	NSC_Generator v0.2		Dh	07.08.2023
 * 	
 * 	gui.stageController
 * 	  EditorController
 * 	    ManagerController
 * 	      PackManagerController
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

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

import org.nsc_generator.gui.ParentControllerInterface;
import org.nsc_generator.gui.stages.PackEditorStage;
import org.nsc_generator.gui.tableElements.NameElement;
import org.nsc_generator.logic.LogManager;
import org.nsc_generator.logic.MainManager;
import org.nsc_generator.logic.editors.Editor;
import org.nsc_generator.logic.editors.PackManager;

public class PackManagerController extends ManagerController {
	@FXML
	private Button btEdit;
	@FXML
	private ListView<NameElement> lvCultures, lvRaces;

	private ObservableList<NameElement> liCultures, liRaces;
	
	private PackManager packManager;
	
	/**	Dh	09.03.2021
	 * 
	 */
	public PackManagerController() {
		super();
	}

	/**	Dh	07.08.2023
	 * 
	 * @param pIsEdit
	 * @param pParentController
	 * @param pPackEditor
	 * @throws Exception
	 */
	public void setUp(boolean pIsEdit, boolean pIsMobile, ParentControllerInterface pParentController, Editor pPackManager) throws Exception {
		super.setUp(pIsEdit, pIsMobile, pParentController, pPackManager);
		
		if (pPackManager != null) {
			if (pPackManager instanceof PackManager) {
				packManager = (PackManager)pPackManager;
				
				lvNameElements.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
					try{
						if (newSel != null) packManager.setPack(newSel.getId());
						
						updateNameElementInfos();
						setEnabledNameElementElements(true);
					} catch(Exception ex) {LogManager.handleException(ex);}
				});
				
				liNameElements = FXCollections.observableArrayList();
				
				lvNameElements.setItems(liNameElements);
				
				liCultures = FXCollections.observableArrayList();
				liRaces = FXCollections.observableArrayList();
				
				lvCultures.setItems(liCultures);
				lvRaces.setItems(liRaces);
				
				btExport.setDisable(true);
				//btImport.setDisable(true);
				btDirectory.setDisable(true);
				
				if (!pIsEdit) btCancel.setVisible(false);
				
				updateAll();
				setEnabled();
			} else throw new Exception("06; su,PaMaCon");
		} else throw new Exception("04; sU,PaMaCon");
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	07.08.2023
	 * 
	 */
	@FXML
	protected void addNameElement() {
		try {
			childStage = new PackEditorStage(false, this, packManager.newPack());
			setDisabled();
		}catch(Exception ex) {LogManager.handleException(ex);}
	}
	
	/**	Dh	07.08.2023
	 * 
	 */
	@FXML
	protected void editNameElement() {
		try {
			Platform.runLater(() -> {lvNameElements.getSelectionModel().clearSelection();}); 
			childStage = new PackEditorStage(true, this, packManager.editPack());
			setDisabled();
		}catch(Exception ex) {LogManager.handleException(ex);}
	}
	
	/**	Dh	07.08.2023
	 * 
	 */
	@FXML
	protected void importNameElement() {
		File vFile;
		FileChooser vFileChooser = new FileChooser();
		
		configureFileChooser(vFileChooser, 0);
		
		vFileChooser.setTitle("Wähle Pack Datei");
		
		vFile = vFileChooser.showOpenDialog(MainManager.getPrimaryStage());
		
		if (vFile != null) {
			try{
				packManager.importPack(vFile);
				
				updateNameElementList();
				updateNameElementInfos();
			} catch(Exception ex) {LogManager.handleException(ex);}
		}
	}
	/**	Dh	07.08.2023
	 * 
	 */
	@FXML
	protected void exportNameElement() {
		File vFile;
		FileChooser vFileChooser = new FileChooser();
		
		configureFileChooser(vFileChooser, 0);
		
		vFileChooser.setTitle("Wähle Ziel Pack Datei");
		
		vFile = vFileChooser.showSaveDialog(MainManager.getPrimaryStage());
		
		if (vFile != null) {
			try{packManager.exportPack(vFile);
			} catch(Exception ex) {LogManager.handleException(ex);}
		}
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	07.08.2023
	 * 
	 */
	@FXML
	protected void apply() {
		try {
			packManager.applyCurrentPack(!isEdit);
			parentController.closeChildStage();
		}catch(Exception ex) {LogManager.handleException(ex);}
	}
	/**	Dh	07.08.2023
	 * 
	 */
	@FXML
	protected void delete() {
		try{
			lvNameElements.getSelectionModel().clearSelection();
			packManager.delete();
			updateAll();
		}catch(Exception ex) {LogManager.handleException(ex);}
	}
	/**	Dh	09.03.2021
	 * 
	 */
	@FXML
	public void back() {
		parentController.closeChildStage();
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	10.03.2021
	 * 
	 */
	protected void setEnabledSelectedNameElementButtons(boolean pEnabled) {
		super.setEnabledSelectedNameElementButtons(pEnabled);
		if (lvNameElements.getSelectionModel().isEmpty() == true) {
			btEdit.setDisable(true);
		} else {
			btEdit.setDisable(!pEnabled);
		}
	}
	
	/**	Dh	09.03.2021
	 * 
	 */
	protected void setEnabledSelectetNameElementFields(boolean pEnabled) {
		if (lvNameElements.getSelectionModel().isEmpty() == true) {
			lvCultures.setDisable(true);
			lvRaces.setDisable(true);
		} else {
			lvCultures.setDisable(!pEnabled);
			lvRaces.setDisable(!pEnabled);
		}
	}
	
	//----------------------------------------------------------------------------------------------------

	/**	Dh	10.03.2021
	 * 
	 */
	protected void updateNameElementInfos() throws Exception{
		if (!lvNameElements.getSelectionModel().isEmpty()) {
			tfName.setText(packManager.getName());
			
			updateNameElementListElementList(packManager.getCultureList(), liCultures, lvCultures);
			updateNameElementListElementList(packManager.getRaceList(), liRaces, lvRaces);
		}
	}
	/**	Dh	09.03.2021
	 * 
	 */
	protected void updateNameElementList() throws Exception{
		updateNameElementListElementList(packManager.getPackList(), liNameElements, lvNameElements);
	}
	protected void updateDirectoryField() throws Exception{
		
	}
	
}
