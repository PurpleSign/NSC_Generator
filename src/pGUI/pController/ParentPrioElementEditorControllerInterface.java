/**	NSC_Generator v0.0		Dh	18.03.2021
 * 	
 * 	pGUI.pController
 * 	  ParentStageControllerInterface
 * 	    ParentPrioElementEditorControllerInterface
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

package pGUI.pController;

public interface ParentPrioElementEditorControllerInterface extends ParentStageControllerInterface {

	public abstract void setPrioElement(int pCaseNumber, int pPrioElementID, String pName, int pPrio) throws Exception;
	public abstract Object[] getPrioElement(int pCaseNumber, int pPrioElementID) throws Exception;
	
	public abstract void addPrioElement(int pCaseNumber, String pName, int pPrio) throws Exception;
	public abstract void removePrioElement(int pCaseNumber, int pPrioElementID) throws Exception;
	
}
