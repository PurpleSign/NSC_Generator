/**	NSC_Generator v0.1		Dh	22.05.2021
 * 	
 * 	pGUI.pMobile
 * 	  BasicView
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

package org.nsc_generator.pGUI.pMobile;

import com.gluonhq.charm.glisten.mvc.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.nsc_generator.pGUI.pController.EditorController;
import org.nsc_generator.pGUI.pController.ParentStageControllerInterface;
import org.nsc_generator.pLogic.MainManager;
import org.nsc_generator.pLogic.MainManagerInterface;
import org.nsc_generator.pLogic.pEditors.Editor;

public class BasicView extends View {
	protected String zSceneFileName, zTitle;
	
	protected boolean isEdit;
	
	protected Parent root;
	protected EditorController controller;
	
	
	/**	Dh	22.05.2021
	 * 
	 * @param pFXMLFileString
	 */
	public BasicView(String pFXMLFileString, String pTitle, boolean pIsEdit, ParentStageControllerInterface pParentController, Editor pEditor, MainManagerInterface pMainManagerMobile) {
		super();
		
		zSceneFileName = pFXMLFileString;
		zTitle = pTitle;
		
		isEdit = pIsEdit;
		
		init(pIsEdit, pParentController, pEditor, pMainManagerMobile);
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	22.05.2021
	 * 
	 * @param pIsEdit
	 * @param pParentController
	 * @param pEditor
	 */
	private void init(boolean pIsEdit, ParentStageControllerInterface pParentController, Editor pEditor, MainManagerInterface pMainManagerMobile) {
		FXMLLoader fxmlloader;
		try {
			fxmlloader = new FXMLLoader(getClass().getResource("/"+zSceneFileName));
			root = fxmlloader.load();
			//scene = new Scene(root);
			controller = fxmlloader.getController();
			//controller.setUp(pIsEdit, pParentController, pEditor);
			
			this.setCenter(root);
			
			//this.setScene(scene);
			//this.sizeToScene();
			//this.setResizable(false);
			
			//this.setTitle(zTitle);
			//this.initModality(Modality.WINDOW_MODAL);
			//this.setIconified(false);
			
			//this.show();
			controller.setUp(pIsEdit, true, pParentController, pEditor);
		} catch(Exception ex) {MainManager.handleException(ex);}
		
	}

}
