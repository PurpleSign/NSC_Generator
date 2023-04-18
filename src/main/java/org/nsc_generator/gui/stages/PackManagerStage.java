/**	NSC_Generator v0.0		Dh	09.03.2021
 * 	
 * 	pGUI
 * 	  EditorStage
 * 	    PackManagerStage
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

public class PackManagerStage extends EditorStage {

	public PackManagerStage(boolean pIsEdit, ParentStageControllerInterface pParentController, Editor pEditor) {
		super("org/nsc_generator/stages/Pack_Manager_Scene.fxml", "Pack Manager", pIsEdit, pParentController, pEditor);
	}

}
