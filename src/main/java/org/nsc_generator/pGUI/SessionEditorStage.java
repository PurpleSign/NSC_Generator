/**	NSC_Generator v0.0		Dh	07.03.2021
 * 	
 * 	pGUI
 * 	  EditorStage
 * 	    SessionEditorStage
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

import org.nsc_generator.pLogic.pEditors.Editor;

public class SessionEditorStage extends EditorStage {

	/**	Dh	07.03.2021
	 * 
	 * @param pEditor
	 */
	public SessionEditorStage(Editor pEditor) {
		super("org/pScene/Session_Editor_Scene.fxml", "NSC Generator", true, null, pEditor);
		// TODO Auto-generated constructor stub
	}

}
