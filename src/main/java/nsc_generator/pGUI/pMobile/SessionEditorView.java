/**	NSC_Generator v0.1		Dh	22.05.2021
 * 	
 * 	pGUI.pMobile
 * 	  BasicView
 * 	    SessionEditorView
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

package nsc_generator.pGUI.pMobile;

import nsc_generator.pLogic.MainManagerInterface;
import nsc_generator.pLogic.pEditors.Editor;

public class SessionEditorView extends BasicView {

	/**	Dh	22.05.2021
	 * 
	 * @param pEditor
	 */
	public SessionEditorView(Editor pEditor, MainManagerInterface pMainManagerMobile) {
		super("pScene/Session_Editor_Scene.fxml", "NSC Generator", true, null, pEditor, pMainManagerMobile);
	}

}
