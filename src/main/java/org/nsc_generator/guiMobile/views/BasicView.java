/**	NSC_Generator v0.2		Dh	07.08.2023
 * 	
 * 	gui.views
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

package org.nsc_generator.guiMobile.views;

import org.nsc_generator.gui.BasicController;
import org.nsc_generator.logic.LogManager;

import com.gluonhq.charm.glisten.mvc.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class BasicView extends View {
	protected boolean isEdit;
	protected String viewFileName, title;
	
	protected Parent root;
	protected BasicController controller;
	
	/**	Dh	18.04.2023
	 * 
	 * @param pViewFileName
	 * @param pTitle
	 */
	public BasicView(String pViewFileName, String pTitle, boolean pIsEdit) {
		super();
		
		viewFileName = pViewFileName;
		title = pTitle;
		
		isEdit = pIsEdit;
		
		init();
	}

//--------------------------------------------------------------------------------------------------------

	/**	Dh	07.08.2023
	 * 
	 */
	private void init() {
		FXMLLoader fxmlloader;
		try {
			fxmlloader = new FXMLLoader(getClass().getResource("/"+viewFileName));
			root = fxmlloader.load();
			
			controller = fxmlloader.getController();
			
			this.setCenter(root);
		} catch(Exception ex) {LogManager.handleException(ex);}
	}
	
//--------------------------------------------------------------------------------------------------------

	
}
