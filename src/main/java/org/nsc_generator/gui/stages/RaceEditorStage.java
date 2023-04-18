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

package org.nsc_generator.gui.stages;

import org.nsc_generator.gui.stageController.ParentStageController;
import org.nsc_generator.logic.editors.RaceEditor;

public class RaceEditorStage extends EditorStage {
	
	/**	Dh	01.03.2021
	 * 
	 * @param pIsEdit
	 * @param pEditor
	 */
	public RaceEditorStage(boolean pIsEdit, ParentStageController pParentControlelr, RaceEditor pRaceEditor) {
		super("org/nsc_generator/stages/Race_Editor_Scene.fxml", "Rassen Editor", pIsEdit, pParentControlelr, pRaceEditor);
	}
	
}
