/**	NSC_Generator v0.0		Dh	22.05.2021
 * 	
 * 	pLogic
 * 	  MessageHandler
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


package org.nsc_generator.logic;

public abstract class MessageHandler {
	private static MainManagerInterface mainManager;

	/**	Dh	22.05.2021
	 * 
	 * @param pMainManager
	 */
	public static void initMessageHandler(MainManagerInterface pMainManager) {
		mainManager = pMainManager;
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	22.05.2021
	 * 
	 * @param ex
	 */
	public static void handleException(Exception ex) {
		mainManager.handleException(ex);
	}
	/**	Dh	22.05.2021
	 * 
	 * @param pMessage
	 */
	public static void handleMessage(String pMessage) {
		mainManager.handleMessage(pMessage);
	}
	
}
