/**	NSC_Generator v0.2		Dh	07.08.2023
 * 	
 * 	gui.stageController
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

package org.nsc_generator.gui.stageController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;

import org.nsc_generator.gui.ParentControllerInterface;
import org.nsc_generator.logic.LogManager;
import org.nsc_generator.logic.editors.Editor;

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
	
	/**	Dh	20.05.2021
	 * 
	 */
	public void setUp(boolean pIsEdit, boolean pIsMobile, ParentControllerInterface pParentController, Editor pEditor) throws Exception {
		super.setUp(pIsEdit, pIsMobile, pParentController, pEditor);
		
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
	
	/**	Dh	07.08.2023
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
			} catch(Exception ex) {LogManager.handleException(ex);}
			
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
	
	/**	Dh	07.08.2023
	 * 
	 * @return
	 */
	private boolean checkInputs() {
		boolean vRet = true;
		
		if (tfName.getText().equals("")) {
			vRet = false;
			LogManager.handleMessage("Der Name fehlt.");
		}
		if (tfPrio.getText().equals("")) {
			vRet = false;
			LogManager.handleMessage("Ein Prioritätswert fehlt.");
		}
		else if (Integer.parseInt(tfPrio.getText()) < 0) {
			vRet = false;
			LogManager.handleMessage("Der Prioritätswert ist nicht größer 0.");
		}
		
		return vRet;
	}
	
}
