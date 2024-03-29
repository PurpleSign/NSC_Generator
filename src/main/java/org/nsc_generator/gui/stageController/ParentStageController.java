/**	NSC_Generator v0.2		Dh	07.08.2023
 * 	
 * 	gui.stageController
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

package org.nsc_generator.gui.stageController;

import org.nsc_generator.gui.ParentControllerInterface;
import org.nsc_generator.gui.stages.EditorStage;
import org.nsc_generator.logic.LogManager;
import org.nsc_generator.logic.MainManagerInterface;
import org.nsc_generator.logic.editors.Editor;

public abstract class ParentStageController extends EditorController implements ParentControllerInterface {
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
	public void setUp(boolean pIsEdit, boolean pIsMobile, ParentControllerInterface pParentController, Editor pEditor) throws Exception{
		super.setUp(pIsEdit, pIsMobile, pParentController, pEditor);
	}
	/**	Dh	22.05.2021
	 * 
	 */
	public void setUp(boolean pIsEdit, boolean pIsMobile, ParentControllerInterface pParentController, Editor pEditor, MainManagerInterface pMainManager) throws Exception{
		super.setUp(pIsEdit, pIsMobile, pParentController, pEditor, pMainManager);
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	07.08.2023
	 * 
	 */
	public void closeChildStage() {
		childStage.hide();
		
		try{updateAll();} 
		catch(Exception ex) {LogManager.handleException(ex);}
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
