/**	NSC_Generator v0.0		Dh	02.03.2021
 * 	
 * 	pGUI
 * 	  EditorStage
 * 	    ProbElementEditorStage
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

package org.nsc_generator.pGUI;

import org.nsc_generator.pGUI.pController.ParentProbElementEditorControllerInterface;
import org.nsc_generator.pGUI.pController.ProbElementEditorController;
import org.nsc_generator.pLogic.MainManager;
import org.nsc_generator.pLogic.pEditors.Editor;

public class ProbElementEditorStage extends EditorStage {	
	
	/**	Dh	02.03.2021
	 * 
	 * @param pTitle
	 * @param pIsEdit
	 * @param pEditor
	 */
	public ProbElementEditorStage(String pTitle, boolean pIsEdit, Editor pEditor, int pCaseNumber, int pProbElementID, ParentProbElementEditorControllerInterface pParentController) {
		super("org/pScene/ProbElement_Editor_Scene.fxml", pTitle, pIsEdit, pParentController, pEditor);

		init(pCaseNumber, pProbElementID);
	}

	/**	Dh	02.03.2021
	 * 
	 * @param pCaseNumber
	 * @param pProbElementID
	 */
	private void init(int pCaseNumber, int pProbElementID) {
		try {
			ProbElementEditorController vController = (ProbElementEditorController) controller;
			
			vController.setUpTwo(pCaseNumber, pProbElementID);
		} catch(Exception ex) {MainManager.handleException(ex);}
		
	}
		
}
