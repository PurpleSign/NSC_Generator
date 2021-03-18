/**	NSC_Generator v0.0		Dh	08.03.2021
 * 	
 * 	pGUI.pController
 * 	  EditorController
 * 	    ProbElementEditorController
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

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.FloatStringConverter;
import pLogic.MainManager;
import pLogic.pEditors.Editor;

public class ProbElementEditorController extends EditorController{
	@FXML
	private TextField tfName, tfProb;
	@FXML
	private Button btSave, btBack;
	
	private int caseNumber, probElemenID; 
	
	protected ParentProbElementEditorControllerInterface parentController;
	
	/**	Dh	02.03.2021
	 * 
	 */
	public ProbElementEditorController() {
		caseNumber = 0;
		probElemenID = -1;
	}
	
	/**	Dh	04.03.2021
	 * 
	 */
	public void setUp(boolean pIsEdit, ParentStageControllerInterface pParentController, Editor pEditor) throws Exception {
		super.setUp(pIsEdit, pParentController, pEditor);
		
		parentController = (ParentProbElementEditorControllerInterface)super.parentController;
		if (pEditor != null) throw new Exception("06; sU,PEEdCon");
	}
	/**	Dh	04.03.2021
	 * 
	 * @param pCaseNumber
	 * @param pProbElementID
	 * @throws Exception
	 */
	public void setUpTwo(int pCaseNumber, int pProbElementID) throws Exception {
		Object[] vProbElement;
		
		if (pCaseNumber >= 0) {
			caseNumber = pCaseNumber;
			probElemenID = pProbElementID;
			
			tfProb.setTextFormatter(new TextFormatter<>(new FloatStringConverter()));
			
			if (isEdit == true) {
				vProbElement = parentController.getProbElement(caseNumber, pProbElementID);
				
				if (vProbElement.length == 3) {
					if (vProbElement[1] instanceof String) tfName.setText((String)vProbElement[1]);
					else throw new Exception("06a, sUT,PEEdCon");
					
					if (vProbElement[2] instanceof Double) tfProb.setText(""+(double)vProbElement[2]);
					else throw new Exception("06b, sUT,PEEdCon");
				}else throw new Exception("01; sUT,PEEdCon");
			}
			
		} else throw new Exception("02; sUT,PEEdCon");
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	02.03.2021
	 * 
	 */
	protected void apply() {
		String vName;
		double vProb;
		
		if (checkInputs() == true) {
			vName = tfName.getText();
			vProb = Double.parseDouble(tfProb.getText());
			
			try {
				if (isEdit == true) this.parentController.setProbElement(caseNumber, probElemenID, vName, vProb);
				else this.parentController.addProbElement(caseNumber, vName, vProb);
			} catch(Exception ex) {MainManager.handleException(ex);}
			
			this.parentController.closeChildStage();
		}
	}
	
	protected void delete() {}
	
	/**	Dh	02.03.2021
	 * 
	 */
	public void back() {
		parentController.closeChildStage();
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	08.03.2021
	 * 
	 * @return
	 */
	private boolean checkInputs() {
		boolean vRet = true;
		
		if (tfName.getText().equals("")) {
			vRet = false;
			MainManager.handleMessage("Der Name fehlt.");
		}
		if (tfProb.getText().equals("")) {
			vRet = false;
			MainManager.handleMessage("Ein Wahrscheinlichkeitswert fehlt.");
		}
		else if ((Double.parseDouble(tfProb.getText()) < 0) || (Double.parseDouble(tfProb.getText()) > 100)) {
			vRet = false;
			MainManager.handleMessage("Der Wahrscheinlichkeitswert liegt nicht zwischen 0 und 100.");
		}
		
		return vRet;
	}
}
