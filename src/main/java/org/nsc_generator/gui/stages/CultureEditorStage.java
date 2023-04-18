/**	NSC_Generator v0.0		Dh	02.03.2021
 * 	
 * 	pGUI
 * 	  EditorStage
 * 	    CultureEditorStage
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
import org.nsc_generator.logic.editors.CultureEditor;

public class CultureEditorStage extends EditorStage {

	/**	DH	02.03.2021
	 * 
	 * @param pSceneFileName
	 * @param pTitle
	 * @param pIsEdit
	 * @param pCultureEditor
	 */
	public CultureEditorStage(boolean pIsEdit, ParentStageController pParentController, CultureEditor pCultureEditor) {
		super("org/nsc_generator/stages/Culture_Editor_Scene.fxml", "Kultur Editor", pIsEdit, pParentController, pCultureEditor);
	}

}
