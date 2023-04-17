/**	NSC_Generator v0.0		Dh	09.03.2021
 * 	
 * 	pGUI.pController
 * 	  ParentStageControllerInterface
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

package org.nsc_generator.pGUI.pController;

public interface ParentStageControllerInterface {

	public abstract void closeChildStage();
	
	public abstract void setDisabled();
	public abstract void setEnabled();
	
}
