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

package org.nsc_generator.gui.stages;

import org.nsc_generator.gui.stageController.ParentStageControllerInterface;
import org.nsc_generator.logic.editors.Editor;

public class SessionManagerStage extends EditorStage {

	/**	Dh	10.03.2021
	 * 
	 * @param pIsEdit
	 * @param pParentController
	 * @param pEditor
	 */
	public SessionManagerStage(boolean pIsEdit,	ParentStageControllerInterface pParentController, Editor pEditor) {
		super("org/nsc_generator/stages/Session_Manager_Scene.fxml", "Sitzungs Manager", pIsEdit, pParentController, pEditor);
	}

}
