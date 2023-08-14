/**	NSC_Generator v0.2		Dh	07.08.2023
 * 	
 * 	logic
 * 	  LogManager
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

public abstract class LogManager {
	private static double version = 0.2;
	private static MainManagerInterface mainManager;

	/**	Dh	07.08.2023
	 * 
	 * @param pMainManager
	 * @throws Exception
	 */
	public static void initLogManager(MainManagerInterface pMainManager) throws Exception {
		if (pMainManager != null) mainManager = pMainManager;
		else throw new Exception("04; iLM,LoM");
	}

//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	07.08.2023
	 * 
	 * @return
	 */
	public static double getVersion() {
		return version;
	}
	
	//------------------------------------------------------------------------------------------------
	
	/**	Dh	07.08.2023
	 * 
	 */
	public static void closeApp() {
		if (mainManager != null) mainManager.closeApp();
	}

//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	07.08.2023
	 * 
	 * @param ex
	 */
	public static void handleException(Exception ex) {
		if (mainManager != null) mainManager.handleException(ex);
	}
	/**	Dh	07.08.2023
	 * 
	 * @param pMessage
	 */
	public static void handleMessage(String pMessage) {
		if (mainManager != null) mainManager.handleMessage(pMessage);
	}
	
}
