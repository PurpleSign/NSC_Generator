/**	NSC_Generator v0.2		Dh	18.04.2023
 * 	
 * 	gui
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

package org.nsc_generator.gui;

public interface ParentControllerInterface {

	public abstract void closeChildStage();
	
	public abstract void setDisabled();
	public abstract void setEnabled();
	
}
