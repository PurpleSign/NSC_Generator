/**	NSC_Generator v0.0		Dh	10.03.2021
 * 	
 * 	pGUI
 * 	  EditorStage
 * 	    SessionManagerStage
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

import org.nsc_generator.pGUI.pController.ParentStageControllerInterface;
import org.nsc_generator.pLogic.pEditors.Editor;

public class SessionManagerStage extends EditorStage {

	/**	Dh	10.03.2021
	 * 
	 * @param pIsEdit
	 * @param pParentController
	 * @param pEditor
	 */
	public SessionManagerStage(boolean pIsEdit,	ParentStageControllerInterface pParentController, Editor pEditor) {
		super("org/pScene/Session_Manager_Scene.fxml", "Sitzungs Manager", pIsEdit, pParentController, pEditor);
	}

}
