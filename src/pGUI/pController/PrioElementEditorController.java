/**	NSC_Generator v0.0		Dh	18.03.2021
 * 	
 * 	pGUI.pController
 * 	  EditorController
 * 	    PrioElementEditorController
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
 * 
 */

package pGUI.pController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;
import pLogic.MainManager;
import pLogic.pEditors.Editor;

public class PrioElementEditorController extends EditorController {
	@FXML
	private TextField tfName, tfPrio;
	@FXML
	private Button btSave, btBack;
	
	private int caseNumber, prioElemenID; 
	
	protected ParentPrioElementEditorControllerInterface parentController;
	
	/**	Dh	18.03.2021
	 * 
	 */
	public PrioElementEditorController() {
		caseNumber = 0;
		prioElemenID = -1;
	}
	
	/**	Dh	18.03.2021
	 * 
	 */
	public void setUp(boolean pIsEdit, ParentStageControllerInterface pParentController, Editor pEditor) throws Exception {
		super.setUp(pIsEdit, pParentController, pEditor);
		
		parentController = (ParentPrioElementEditorControllerInterface)super.parentController;
		if (pEditor != null) throw new Exception("06; sU,PEEdCon");
	}
	/**	Dh	18.03.2021
	 * 
	 * @param pCaseNumber
	 * @param pPrioElementID
	 * @throws Exception
	 */
	public void setUpTwo(int pCaseNumber, int pPrioElementID) throws Exception {
		Object[] vPrioElement;
		
		if (pCaseNumber >= 0) {
			caseNumber = pCaseNumber;
			prioElemenID = pPrioElementID;
			
			tfPrio.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
			
			if (isEdit == true) {
				vPrioElement = parentController.getPrioElement(caseNumber, pPrioElementID);
				
				if (vPrioElement.length == 3) {
					if (vPrioElement[1] instanceof String) tfName.setText((String)vPrioElement[1]);
					else throw new Exception("06a, sUT,PEEdCon");
					
					if (vPrioElement[2] instanceof Integer) tfPrio.setText(""+(int)vPrioElement[2]);
					else throw new Exception("06b, sUT,PEEdCon");
				}else throw new Exception("01; sUT,PEEdCon");
			}
			
		} else throw new Exception("02; sUT,PEEdCon");
	}

//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	18.03.2021
	 * 
	 */
	protected void apply() {
		String vName;
		int vPrio;
		
		if (checkInputs() == true) {
			vName = tfName.getText();
			vPrio = Integer.parseInt(tfPrio.getText());
			
			try {
				if (isEdit == true) this.parentController.setPrioElement(caseNumber, prioElemenID, vName, vPrio);
				else this.parentController.addPrioElement(caseNumber, vName, vPrio);
			} catch(Exception ex) {MainManager.handleException(ex);}
			
			this.parentController.closeChildStage();
		}
	}
	
	protected void delete() {}
	
	/**	Dh	18.03.2021
	 * 
	 */
	public void back() {
		parentController.closeChildStage();
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	18.03.2021
	 * 
	 * @return
	 */
	private boolean checkInputs() {
		boolean vRet = true;
		
		if (tfName.getText().equals("")) {
			vRet = false;
			MainManager.handleMessage("Der Name fehlt.");
		}
		if (tfPrio.getText().equals("")) {
			vRet = false;
			MainManager.handleMessage("Ein Prioritätswert fehlt.");
		}
		else if (Integer.parseInt(tfPrio.getText()) < 0) {
			vRet = false;
			MainManager.handleMessage("Der Prioritätswert ist nicht größer 0.");
		}
		
		return vRet;
	}
	
}
