/**	NSC_Generator v0.0		Dh	07.03.2021
 * 	
 * 	pGUI
 * 	  EditorStage
 * 	    NPCEditorStage
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

import nsc_generator.pGUI.pController.ParentStageController;
import nsc_generator.pLogic.pEditors.Editor;

public class NPCEditorStage extends EditorStage {

	/**	Dh	07.03.2021
	 * 
	 * @param pIsEdit
	 * @param pParentController
	 * @param pEditor
	 */
	public NPCEditorStage(boolean pIsEdit, ParentStageController pParentController, Editor pEditor) {
		super("pScene/NPC_Editor_Scene.fxml", "NSC Bearbeitung", pIsEdit, pParentController, pEditor);
	}

}
