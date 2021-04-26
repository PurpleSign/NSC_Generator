/**	NSC_Generator v0.0		Dh	18.03.2021
 * 	
 * 	pGUI
 * 	  EditorStage
 * 	    PrioElementEditorStage
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

package nsc_generator.pGUI;

import nsc_generator.pGUI.pController.ParentPrioElementEditorControllerInterface;
import nsc_generator.pGUI.pController.PrioElementEditorController;
import nsc_generator.pLogic.MainManager;
import nsc_generator.pLogic.pEditors.Editor;

public class PrioElementEditorStage extends EditorStage {

	/**	Dh	18.03.2021
	 * 
	 * @param pTitle
	 * @param pIsEdit
	 * @param pEditor
	 * @param pCaseNumber
	 * @param pPrioElementID
	 * @param pParentController
	 */
	public PrioElementEditorStage(String pTitle, boolean pIsEdit, Editor pEditor, int pCaseNumber, int pPrioElementID, ParentPrioElementEditorControllerInterface pParentController) {
		super("pScene/PrioElement_Editor_Scene.fxml", pTitle, pIsEdit, pParentController, pEditor);
		
		init(pCaseNumber, pPrioElementID);
	}
	
	/**	Dh	18.03.2021
	 * 
	 * @param pCaseNumber
	 * @param pPrioElementID
	 */
	private void init(int pCaseNumber, int pPrioElementID) {
		try {
			PrioElementEditorController vController = (PrioElementEditorController) controller;
			
			vController.setUpTwo(pCaseNumber, pPrioElementID);
		} catch(Exception ex) {MainManager.handleException(ex);}
		
	}

}
