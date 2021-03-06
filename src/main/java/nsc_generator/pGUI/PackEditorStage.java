/**	NSC_Generator v0.0		Dh	09.03.2021
 * 	
 * 	pGUI
 * 	  EditorStage
 * 	    PackEditorStage
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
import nsc_generator.pLogic.pEditors.PackEditor;

public class PackEditorStage extends EditorStage {

	/**	Dh	09.03.2021
	 * 
	 * @param pPackEditor
	 */
	public PackEditorStage(boolean pIsEdit, ParentStageController pParentController, PackEditor pPackEditor) {
		super("pScene/Pack_Editor_Scene.fxml", "Pack Editor", pIsEdit, pParentController, pPackEditor);
	}

}
