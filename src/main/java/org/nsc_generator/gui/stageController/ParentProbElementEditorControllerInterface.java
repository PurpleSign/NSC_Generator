/**	NSC_Generator v0.0		Dh	09.03.2021
 * 	
 * 	gui.stageController
 * 	  ParentStageControllerInterface
 * 	    ParentProbElementEditorControllerInterface
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

public interface ParentProbElementEditorControllerInterface extends ParentControllerInterface{
	
	public abstract void setProbElement(int pCaseNumber, int pProbElementID, String pName, double pProb) throws Exception;
	public abstract Object[] getProbElement(int pCaseNumber, int pProbElementID) throws Exception;
	
	public abstract void addProbElement(int pCaseNumber, String pName, double pProb) throws Exception;
	public abstract void removeProbElement(int pCaseNumber, int pProbElementID) throws Exception;
	
}
