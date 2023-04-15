/**	NSC_Generator v0.0		Dh	19.04.2021
 * 	
 * 	pLogic
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

package nsc_generator.pLogic;

import javafx.application.Application;
import javafx.stage.Stage;
import nsc_generator.pGUI.InfoStage;
import nsc_generator.pGUI.SessionEditorStage;
import nsc_generator.pGUI.SessionManagerStage;
import nsc_generator.pLogic.pEditors.SessionEditor;
import nsc_generator.pLogic.pEditors.SessionManager;

public class MainManager extends Application{
	private static Stage mainStage, primaryStage;
	
	/**	Dh	25.02.2021
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**	Dh	11.03.2021
	 * 
	 */
	@Override
	public void start(Stage pPrimaryStage) {
		primaryStage = pPrimaryStage;
		
		try {
			DatabaseConnector.initConnector();
			
			mainStage = new SessionManagerStage(false, null, new SessionManager(null));
		} catch (Exception ex) {MainManager.handleException(ex);}
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
	
	/**	Dh	08.03.2021
	 * 
	 */
	public static void closeApp() {
		mainStage.close();
		//primaryStage.close();
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
	
	/**	Dh	08.03.2021
	 * 
	 * @param ex
	 */
	public static void handleException(Exception ex) {
		new InfoStage(true, ex.getMessage());
		System.out.println(ex.getCause() + " : " + ex.getMessage());
		closeApp();
	}
	/**	Dh	08.03.2021
	 * 
	 * @param pMessage
	 */
	public static void handleMessage(String pMessage) {
		new InfoStage(false, pMessage);
	}
	
}
