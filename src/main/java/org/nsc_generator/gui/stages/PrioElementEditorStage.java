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

package org.nsc_generator.gui.stages;

import org.nsc_generator.gui.stageController.ParentPrioElementEditorControllerInterface;
import org.nsc_generator.gui.stageController.PrioElementEditorController;
import org.nsc_generator.logic.MainManager;
import org.nsc_generator.logic.editors.Editor;

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
		super("org/nsc_generator/stages/PrioElement_Editor_Scene.fxml", pTitle, pIsEdit, pParentController, pEditor);
		
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
