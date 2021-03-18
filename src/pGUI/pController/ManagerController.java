/**	NSC_Generator v0.0		Dh	09.03.2021
 * 	
 * 	pGUI.pController
 * 	  EditorController
 * 	    ManagerController
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

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import pGUI.pTableElement.NameElement;

public abstract class ManagerController extends ParentStageController {
	@FXML
	protected TextField tfDirectory, tfName;
	@FXML
	protected Button btDirectory, btNameElement_Add, btNameElement_Remove, 
		btApply, btImport, btExport, btCancel;
	
	@FXML
	protected ListView<NameElement> lvNameElements;
	
	protected ObservableList<NameElement> liNameElements;
	
	/**	Dh	09.03.2021
	 * 
	 */
	public ManagerController() {
		super();
	}

	
//--------------------------------------------------------------------------------------------------------
	
	@FXML
	protected abstract void addNameElement();
	
	@FXML
	protected abstract void importNameElement();
	
	@FXML
	protected abstract void exportNameElement();
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	09.03.2021
	 * 
	 * @param pEnabled
	 */
	protected void setEnabledSelectedNameElementButtons(boolean pEnabled) {
		if (lvNameElements.getSelectionModel().isEmpty() == true) {
			btNameElement_Remove.setDisable(true);
			btApply.setDisable(true);
		} else {
			btNameElement_Remove.setDisable(!pEnabled);
			btApply.setDisable(!pEnabled);
		}
	}
	/**	Dh	09.03.2021
	 * 
	 * @param pEnabled
	 */
	protected abstract void setEnabledSelectetNameElementFields(boolean pEnabled);
	
	/**	Dh	09.03.2021
	 * 
	 * @param pEnabled
	 */
	protected void setEnabledNameElementElements(boolean pEnabled) {
		setEnabledSelectedNameElementButtons(pEnabled);
		setEnabledSelectetNameElementFields(pEnabled);
	}
	
	/**	Dh	09.03.2021
	 * 
	 */
	protected void setEnabledAll(boolean pEnabled) {
		setEnabledNameElementElements(pEnabled);
		
		//btDirectory.setDisable(!pEnabled);
		
		btNameElement_Add.setDisable(!pEnabled);
		//btImport.setDisable(!pEnabled);
		//btExport.setDisable(!pEnabled);
		btCancel.setDisable(!pEnabled);
	}
	
	//----------------------------------------------------------------------------------------------------
	
	protected abstract void updateNameElementInfos() throws Exception;
	protected abstract void updateNameElementList() throws Exception;
	protected abstract void updateDirectoryField() throws Exception;
	
	/**	Dh	09.03.2021
	 * 
	 */
	protected void updateAll() throws Exception{
		updateNameElementList();
		updateNameElementInfos();
		
		updateDirectoryField();
	}
	
}
