/**	NSC_Generator v0.1		Dh	22.05.2021
 * 	
 * 	pGUI.pMobile
 * 	  BasicView
 * 	    SessionManagerView
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

package org.nsc_generator.pGUI.pMobile;

import org.nsc_generator.pGUI.pController.ParentStageControllerInterface;
import org.nsc_generator.pLogic.MainManagerInterface;
import org.nsc_generator.pLogic.pEditors.Editor;

public class SessionManagerView extends BasicView {

	/**	Dh	22.05.2021
	 * 
	 * @param pIsEdit
	 * @param pParentController
	 * @param pEditor
	 */
	public SessionManagerView(boolean pIsEdit,	ParentStageControllerInterface pParentController, Editor pEditor, MainManagerInterface pMainManagerMobile) {
		super("pScene/Session_Manager_Scene.fxml", "Sitzungs Manager", pIsEdit, pParentController, pEditor, pMainManagerMobile);
	}

}
