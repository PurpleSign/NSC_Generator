/**	NSC_Generator v0.2		Dh	07.08.2023
 * 	
 * 	gui.viewController
 * 	  BasciController
 * 	    ParentViewController
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

package org.nsc_generator.guiMobile.viewController;

import org.nsc_generator.gui.BasicController;
import org.nsc_generator.gui.ParentControllerInterface;
import org.nsc_generator.guiMobile.views.BasicView;
import org.nsc_generator.logic.LogManager;
import org.nsc_generator.logic.editors.Editor;

public abstract class ParentViewController extends BasicController implements ParentControllerInterface {
	protected BasicView childView;
	
	/**	Dh	19.04.2023
	 * 
	 */
	public ParentViewController() {
		super();
	}
	
	/**	Dh	19.04.2023
	 * 
	 */
	public void setUp(boolean pIsEdit, ParentControllerInterface pParentController, Editor pEditor) throws Exception{
		super.setUp(pIsEdit, pParentController, pEditor);
	}

//--------------------------------------------------------------------------------------------------------

	/**	Dh	07.08.2023
	 * 
	 */
	public void closeChildStage() {
		
		
		try{updateAll();} 
		catch(Exception ex) {LogManager.handleException(ex);}
		setEnabled();
	}
	
	/**	Dh	19.04.2023
	 * 
	 */
	public void setDisabled() {
		setEnabledAll(false);
	}
	/**	Dh	19.04.2023
	 * 
	 */
	public void setEnabled() {
		setEnabledAll(true);
	}
	
//--------------------------------------------------------------------------------------------------------

	protected abstract void setEnabledAll(boolean pEnabled);
	
	protected abstract void updateAll() throws Exception;
	
}
