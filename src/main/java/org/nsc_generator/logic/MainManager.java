/**	NSC_Generator v0.2		Dh	07.08.2023
 * 	
 * 	logic
 * 	  MainManager
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

import javafx.application.Application;
import javafx.stage.Stage;
import org.nsc_generator.gui.stages.InfoStage;
import org.nsc_generator.gui.stages.SessionEditorStage;
import org.nsc_generator.gui.stages.SessionManagerStage;
import org.nsc_generator.logic.editors.SessionEditor;
import org.nsc_generator.logic.editors.SessionManager;

public class MainManager extends Application implements MainManagerInterface{
	private static Stage mainStage, primaryStage;
	
	/**	Dh	25.02.2021
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**	Dh	07.08.2023
	 * 
	 */
	@Override
	public void start(Stage pPrimaryStage) {
		primaryStage = pPrimaryStage;
		
		try {
			LogManager.initLogManager(this);
			DatabaseConnector.initConnector();
			
			mainStage = new SessionManagerStage(false, null, new SessionManager(null));
		} catch (Exception ex) {LogManager.handleException(ex);}
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	11.03.2021
	 * 
	 * @param pSessionEditor
	 */
	public static void startSessionEditor(SessionEditor pSessionEditor) {
		mainStage.close();
		mainStage = new SessionEditorStage(pSessionEditor);
	}
	
	/**	Dh	07.08.2023
	 * 
	 */
	public void closeApp() {
		mainStage.close();
		primaryStage.close();
		System.exit(0);
	}

	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	19.04.2021
	 * 
	 * @return
	 */
	public static Stage getMainStage() {
		return mainStage;
	}
	/**	Dh	19.04.2021
	 * 
	 * @return
	 */
	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	07.08.2023
	 * 
	 * @param ex
	 */
	public void handleException(Exception ex) {
		new InfoStage(true, ex.getMessage());
		System.out.println(ex.getCause() + " : " + ex.getMessage());
		//closeApp();
	}
	/**	Dh	07.08.2023
	 * 
	 * @param pMessage
	 */
	public void handleMessage(String pMessage) {
		new InfoStage(false, pMessage);
	}
	
}
