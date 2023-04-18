/**	NSC_Generator v0.0		Dh	20.05.2021
 * 	
 * 	pGUI
 * 	  EditorStage
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

package org.nsc_generator.gui.stages;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import org.nsc_generator.gui.stageController.EditorController;
import org.nsc_generator.gui.stageController.ParentStageControllerInterface;
import org.nsc_generator.logic.MainManager;
import org.nsc_generator.logic.editors.Editor;

public abstract class EditorStage extends Stage {
	protected String zSceneFileName, zTitle;
	
	protected boolean isEdit;
	
	protected Parent root;
	protected Scene scene;
	protected EditorController controller;
	
	/**	Dh	14.03.2021
	 * 
	 * @param pName
	 * @param pIsEdit
	 * @param pEditor
	 */
	public EditorStage(String pSceneFileName, String pTitle, boolean pIsEdit, ParentStageControllerInterface pParentController, Editor pEditor) {
		super();
		
		zSceneFileName = pSceneFileName;
		zTitle = pTitle;
		
		isEdit = pIsEdit;
		
		this.setOnCloseRequest(event -> {controller.back();});
		
		//System.out.println("" + this.getIcons().isEmpty()); 

		init(pIsEdit, pParentController, pEditor);
	}

//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	20.05.2021
	 * 
	 * @param pName
	 * @param pIsEdit
	 * @param pEditor
	 */
	private void init(boolean pIsEdit, ParentStageControllerInterface pParentController, Editor pEditor) {
		FXMLLoader fxmlloader;
		try {
			fxmlloader = new FXMLLoader(getClass().getResource("/"+zSceneFileName));
			root = fxmlloader.load();
			scene = new Scene(root);
			controller = fxmlloader.getController();
			//controller.setUp(pIsEdit, pParentController, pEditor);
			
			this.setScene(scene);
			this.sizeToScene();
			this.setResizable(false);
			
			this.setTitle(zTitle);
			this.initModality(Modality.WINDOW_MODAL);
			this.setIconified(false);
			
			this.show();
			controller.setUp(pIsEdit, false, pParentController, pEditor);
		} catch(Exception ex) {MainManager.handleException(ex);}
		
	}

}
