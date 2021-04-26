/**	NSC_Generator v0.0		Dh	09.03.2021
 * 	
 * 	pGUI.pController
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

package nsc_generator.pGUI.pController;

public interface ParentProbElementEditorControllerInterface extends ParentStageControllerInterface{
	
	public abstract void setProbElement(int pCaseNumber, int pProbElementID, String pName, double pProb) throws Exception;
	public abstract Object[] getProbElement(int pCaseNumber, int pProbElementID) throws Exception;
	
	public abstract void addProbElement(int pCaseNumber, String pName, double pProb) throws Exception;
	public abstract void removeProbElement(int pCaseNumber, int pProbElementID) throws Exception;
	
}
