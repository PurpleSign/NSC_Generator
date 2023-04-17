/**	NSC_Generator v0.1		Dh	22.05.2021
 * 	
 * 	pGUI.pMobile
 * 	  InforamtionLayer
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

import com.gluonhq.charm.glisten.application.GlassPane;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.layout.Layer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import org.nsc_generator.pGUI.pController.EditorController;
import org.nsc_generator.pGUI.pController.InfoController;

public class InformationLayer extends Layer {
	protected String zSceneFileName, zTitle;
	
	protected boolean isError;
	
	protected Node root;
	protected EditorController controller;
	
	/**	Dh	22.05.2021
	 * 
	 * @param pIsError
	 * @param pMessage
	 */
	public InformationLayer(boolean pIsError, String pMessage) {
		super();
		FXMLLoader fxmlloader;
		
		isError = pIsError;
		zSceneFileName = "pMobileScene/Info_Layer.fxml";
		
		if (isError) zTitle = "Fehler!";
		else zTitle = "Information";
		
		
		try {
			fxmlloader = new FXMLLoader(getClass().getResource("/"+zSceneFileName));
			//root = new StackPane(fxmlloader.load());
			root = fxmlloader.load();
			//scene = new Scene(root);
			controller = fxmlloader.getController();
			//controller.setUp(pIsEdit, pParentController, pEditor);
			
			((InfoController)controller).setUpTwo(pMessage, null);
			
			this.getChildren().add(root);
			
			setBackgroundFade(0.5);
			
			//this.sizeToScene();
			//this.setResizable(false);
			
			//this.setTitle(zTitle);
			//this.initModality(Modality.WINDOW_MODAL);
			//this.setIconified(false);
		} catch(Exception ex) {;}
	}
	
	/**	Dh	22.05.2021
	 * 
	 */
	@Override
    public void layoutChildren() {
		GlassPane glassPane = MobileApplication.getInstance().getGlassPane();
        super.layoutChildren();
        root.setVisible(isShowing());
        if (!isShowing()) {
            return;
        }
        resizeRelocate((glassPane.getWidth() - getWidth())/2, (glassPane.getHeight()- getHeight())/2, getWidth(), getHeight());
    }

}
