/**	NSC_Generator v0.2		Dh	18.04.2023
 * 	
 * 	gui
 * 	  BasicController
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

package org.nsc_generator.gui;

import org.nsc_generator.logic.editors.Editor;

import javafx.fxml.FXML;

public abstract class BasicController {
	protected boolean isEdit;
	protected ParentControllerInterface parentController;
	
	/**	Dh	18.04.2023
	 * 
	 */
	public BasicController() {
		super();
	}

	/**	Dh	18.04.2023
	 * 
	 * @param pIsEdit
	 * @param pParentController
	 * @param pEditor
	 * @throws Exception
	 */
	public void setUp(boolean pIsEdit,  ParentControllerInterface pParentController, Editor pEditor) throws Exception{
		isEdit = pIsEdit;
		
		parentController = pParentController;
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	18.04.2023
	 * 
	 */
	@FXML
	protected abstract void apply();
	
	/**	Dh	18.04.2023
	 * 
	 */
	@FXML
	protected abstract void delete();
	
	/**	Dh	18.04.2023
	 * 
	 */
	@FXML
	public abstract void back();
	
//--------------------------------------------------------------------------------------------------------
	
}
