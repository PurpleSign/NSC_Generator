/**	NSC_Generator v0.0		Dh	11.03.2021
 * 	
 * 	pGUI.pController
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

package pGUI.pController;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import pGUI.PackEditorStage;
import pGUI.pTableElement.NameElement;
import pLogic.MainManager;
import pLogic.pEditors.Editor;
import pLogic.pEditors.PackManager;

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

	/**	Dh	10.03.2021
	 * 
	 * @param pIsEdit
	 * @param pParentController
	 * @param pPackEditor
	 * @throws Exception
	 */
	public void setUp(boolean pIsEdit, ParentStageControllerInterface pParentController, Editor pPackManager) throws Exception {
		super.setUp(pIsEdit, pParentController, pPackManager);
		
		if (pPackManager != null) {
			if (pPackManager instanceof PackManager) {
				packManager = (PackManager)pPackManager;
				
				lvNameElements.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
					try{
						if (newSel != null) packManager.setPack(newSel.getId());
						
						updateNameElementInfos();
						setEnabledNameElementElements(true);
					} catch(Exception ex) {MainManager.handleException(ex);}
				});
				
				liNameElements = FXCollections.observableArrayList();
				
				lvNameElements.setItems(liNameElements);
				
				liCultures = FXCollections.observableArrayList();
				liRaces = FXCollections.observableArrayList();
				
				lvCultures.setItems(liCultures);
				lvRaces.setItems(liRaces);
				
				btExport.setDisable(true);
				btImport.setDisable(true);
				btDirectory.setDisable(true);
				
				if (!pIsEdit) btCancel.setVisible(false);
				
				updateAll();
				setEnabled();
			} else throw new Exception("06; su,PaMaCon");
		} else throw new Exception("04; sU,PaMaCon");
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	09.03.2021
	 * 
	 */
	@FXML
	protected void addNameElement() {
		try {
			childStage = new PackEditorStage(false, this, packManager.newPack());
			setDisabled();
		}catch(Exception ex) {MainManager.handleException(ex);}
	}
	
	/**	Dh	10.03.2021
	 * 
	 */
	@FXML
	protected void editNameElement() {
		try {
			Platform.runLater(() -> {lvNameElements.getSelectionModel().clearSelection();}); 
			childStage = new PackEditorStage(true, this, packManager.editPack());
			setDisabled();
		}catch(Exception ex) {MainManager.handleException(ex);}
	}
	
	@FXML
	protected void importNameElement() {
		
	}
	
	@FXML
	protected void exportNameElement() {
		
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	11.03.2021
	 * 
	 */
	@FXML
	protected void apply() {
		try {
			packManager.applyCurrentPack(!isEdit);
			parentController.closeChildStage();
		}catch(Exception ex) {MainManager.handleException(ex);}
	}
	/**	Dh	10.03.2021
	 * 
	 */
	@FXML
	protected void delete() {
		try{
			lvNameElements.getSelectionModel().clearSelection();
			packManager.delete();
			updateAll();
		}catch(Exception ex) {MainManager.handleException(ex);}
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
