/**	NSC_Generator v0.2		Dh	17.04.2023
 * 	
 * 	logic
 * 	  MainManagerMobile
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
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.gluonhq.charm.glisten.application.AppManager;
import com.gluonhq.charm.glisten.visual.Swatch;

public class MainManagerMobile extends Application implements MainManagerInterface {
	protected static double version = 0.2;
	
	protected final AppManager appManager = AppManager.initialize(this::postInit);
	
	/**	Dh	20.05.2021
	 * 
	 * @param args
	 */
 	public static void main(String[] args) {
		launch(args);
	}

 	/**	Dh	17.04.2023
 	 * 
 	 */
 	public void start(Stage pPrimaryStage) throws Exception{
 		appManager.start(pPrimaryStage);
 	}
 	
 	//----------------------------------------------------------------------------------------------------
 	
	/**	Dh	07.08.2023
	 * 
	 */
	public void init() {
		try {
			LogManager.initLogManager(this);
			DatabaseConnector.initConnector();
			
			//addViewFactory(HOME_VIEW, () -> new SessionManagerView(false, null, new SessionManager(null), this));
			//addViewFactory(HOME_VIEW, () -> new TestView("pMobileScene/Test_Scene2.fxml"));
			//addViewFactory(HOME_VIEW, () -> new TestView("pMobileScene/TestScene.fxml"));
			//addViewFactory(HOME_VIEW, () -> new View());
			//handleMessage("Hello :)");
		}catch(Exception ex) {handleException(ex);}

		appManager.addViewFactory(appManager.HOME_VIEW, null);
		
	}

	/**	Dh	17.04.2023
	 * 
	 * @param scene
	 */
	public void postInit(Scene scene) {
		Swatch.PURPLE.assignTo(scene);
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	11.03.2021
	 * 
	 * @param pSessionEditor
	 */
	/*public void startSessionEditor(SessionEditor pSessionEditor) {
		mainStage.close();
		mainStage = new SessionEditorStage(pSessionEditor);
	}*/
	
	/**	Dh	22.05.2021
	 * 
	 */
	public void closeApp() {
		//mainStage.close();
		//primaryStage.close();
		//System.out.println("Hallo");
		//getDrawer().close();
		//LifecycleEvent.fireEvent(getView(), new LifecycleEvent.CLOSE_REQUEST());
	}

	//----------------------------------------------------------------------------------------------------
	
	public void newViewView(String pViewName) {
		
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	20.05.2021
	 * 
	 * @return
	 */
 	/*public Stage getMainStage() {
		return mainStage;
	}*/
	/**	Dh	20.05.2021
	 * 
	 * @return
	 */
	/*public Stage getPrimaryStage() {
		return primaryStage;
	}*/
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	22.05.2021
	 * 
	 * @param ex
	 */
	public void handleException(Exception ex) {
		
	}
	/**	Dh	22.05.2021
	 * 
	 * @param pMessage
	 */
	public void handleMessage(String pMessage) {
		
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	22.05.2021
	 * 
	 */
	public void closeMessage() {
		
	}
	
}
