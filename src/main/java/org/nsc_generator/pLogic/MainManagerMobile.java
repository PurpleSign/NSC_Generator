/**	NSC_Generator v0.1		Dh	22.05.2021
 * 	
 * 	pLogic
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

package org.nsc_generator.pLogic;

import com.gluonhq.attach.util.Platform;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.LifecycleEvent;
import com.gluonhq.charm.glisten.layout.Layer;
import com.gluonhq.charm.glisten.layout.layer.PopupView;
import com.gluonhq.charm.glisten.mvc.View;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.nsc_generator.pGUI.InfoStage;
import org.nsc_generator.pGUI.SessionEditorStage;
import org.nsc_generator.pGUI.pMobile.InformationLayer;
import org.nsc_generator.pGUI.pMobile.SessionManagerView;
import org.nsc_generator.pGUI.pMobile.TestView;
import org.nsc_generator.pLogic.pEditors.SessionEditor;
import org.nsc_generator.pLogic.pEditors.SessionManager;

public class MainManagerMobile extends MobileApplication implements MainManagerInterface {
	//private static Stage mainStage, primaryStage;	
	private InformationLayer informationLayer;
	
	/**	Dh	20.05.2021
	 * 
	 * @param args
	 */
 	public static void main(String[] args) {
		launch(args);
	}

	/**	Dh	22.05.2021
	 * 
	 */
	public void init() {
		try {
			MessageHandler.initMessageHandler(this);
			DatabaseConnector.initConnector();
			
			addViewFactory(HOME_VIEW, () -> new SessionManagerView(false, null, new SessionManager(null), this));
			//addViewFactory(HOME_VIEW, () -> new TestView("pMobileScene/Test_Scene2.fxml"));
			//addViewFactory(HOME_VIEW, () -> new TestView("pMobileScene/TestScene.fxml"));
			//addViewFactory(HOME_VIEW, () -> new View());
			//handleMessage("Hello :)");
		}catch(Exception ex) {handleException(ex);}

		
		
	}

	public void postInit(Scene scene) {
		
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
		informationLayer = new InformationLayer(true, ex.getMessage());
		informationLayer.show();
	}
	/**	Dh	22.05.2021
	 * 
	 * @param pMessage
	 */
	public void handleMessage(String pMessage) {
		informationLayer = new InformationLayer(false, pMessage);
		informationLayer.show();
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	22.05.2021
	 * 
	 */
	public void closeMessage() {
		informationLayer.hide();
		informationLayer = null;
	}
	
}
