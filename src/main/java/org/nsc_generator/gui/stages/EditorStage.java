/**	NSC_Generator v0.2		Dh	07.08.2023
 * 	
 * 	gui.stages
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

import org.nsc_generator.gui.ParentControllerInterface;
import org.nsc_generator.gui.stageController.EditorController;
import org.nsc_generator.logic.LogManager;
import org.nsc_generator.logic.editors.Editor;

public abstract class EditorStage extends Stage {
	protected String stageFile, title;
	
	protected boolean isEdit;
	
	protected Parent root;
	protected Scene scene;
	protected EditorController controller;
	
	/**	Dh	18.04.2023
	 * 
	 * @param pName
	 * @param pIsEdit
	 * @param pEditor
	 */
	public EditorStage(String pStageFile, String pTitle, boolean pIsEdit, ParentControllerInterface pParentController, Editor pEditor) {
		super();
		
		stageFile = pStageFile;
		title = pTitle;
		
		isEdit = pIsEdit;
		
		this.setOnCloseRequest(event -> {controller.back();});
		
		//System.out.println("" + this.getIcons().isEmpty()); 

		init(pIsEdit, pParentController, pEditor);
	}

//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	07.08.2023
	 * 
	 * @param pName
	 * @param pIsEdit
	 * @param pEditor
	 */
	private void init(boolean pIsEdit, ParentControllerInterface pParentController, Editor pEditor) {
		FXMLLoader fxmlloader;
		try {
			fxmlloader = new FXMLLoader(getClass().getResource("/"+stageFile));
			root = fxmlloader.load();
			scene = new Scene(root);
			controller = fxmlloader.getController();
			
			this.setScene(scene);
			this.sizeToScene();
			this.setResizable(false);
			
			this.setTitle(title);
			this.initModality(Modality.WINDOW_MODAL);
			this.setIconified(false);
			
			this.show();
			controller.setUp(pIsEdit, false, pParentController, pEditor);
		} catch(Exception ex) {LogManager.handleException(ex);}
		
	}

}
