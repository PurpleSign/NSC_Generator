/**	NSC_Generator v0.1		Dh	22.05.2021
 * 	
 * 	pGUI.pController
 * 	  EditorController
 * 	    ParentStageController
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

package nsc_generator.pGUI.pController;

import nsc_generator.pGUI.EditorStage;
import nsc_generator.pLogic.MainManager;
import nsc_generator.pLogic.MainManagerInterface;
import nsc_generator.pLogic.pEditors.Editor;

public abstract class ParentStageController extends EditorController implements ParentStageControllerInterface {
	protected EditorStage childStage;
	
	/**	Dh	09.03.2021
	 * 
	 */
	public ParentStageController() {
		super();
	}
	
	/**	Dh	20.05.2021
	 * 	
	 */
	public void setUp(boolean pIsEdit, boolean pIsMobile, ParentStageControllerInterface pParentController, Editor pEditor) throws Exception{
		super.setUp(pIsEdit, pIsMobile, pParentController, pEditor);
	}
	/**	Dh	22.05.2021
	 * 
	 */
	public void setUp(boolean pIsEdit, boolean pIsMobile, ParentStageControllerInterface pParentController, Editor pEditor, MainManagerInterface pMainManager) throws Exception{
		super.setUp(pIsEdit, pIsMobile, pParentController, pEditor, pMainManager);
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	09.03.2021
	 * 
	 */
	public void closeChildStage() {
		childStage.hide();
		
		try{updateAll();} 
		catch(Exception ex) {MainManager.handleException(ex);}
		setEnabled();
	}
	
	/**	Dh	09.03.2021
	 * 
	 */
	public void setDisabled() {
		setEnabledAll(false);
	}
	/**	Dh	09.03.2021
	 * 
	 */
	public void setEnabled() {
		setEnabledAll(true);
	}
	
	
//--------------------------------------------------------------------------------------------------------
	
	protected abstract void setEnabledAll(boolean pEnabled);
	
	protected abstract void updateAll() throws Exception;
	
}
