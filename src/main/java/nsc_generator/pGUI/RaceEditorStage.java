/**	NSC_Generator v0.0		Dh	02.03.2021
 * 	
 * 	pGUI
 * 	  EditorStage
 * 	    RaceEditorStage
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
import nsc_generator.pLogic.pEditors.RaceEditor;

public class RaceEditorStage extends EditorStage {
	
	/**	Dh	01.03.2021
	 * 
	 * @param pIsEdit
	 * @param pEditor
	 */
	public RaceEditorStage(boolean pIsEdit, ParentStageController pParentControlelr, RaceEditor pRaceEditor) {
		super("pScene/Race_Editor_Scene.fxml", "Rassen Editor", pIsEdit, pParentControlelr, pRaceEditor);
	}
	
}
