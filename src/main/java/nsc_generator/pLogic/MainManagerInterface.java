/**	NSC_Generator v0.1		Dh	22.05.2021
 * 	
 * 	pLogic
 * 	  MainManagerInterface
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

package nsc_generator.pLogic;

public interface MainManagerInterface {

	public void closeApp();
	
//--------------------------------------------------------------------------------------------------------
	
	public void handleException(Exception ex);
	public void handleMessage(String pMessage);
	
}
