package nsc_generator.pGUI.pMobile;

import com.gluonhq.charm.glisten.mvc.View;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
//import nsc_generator.pLogic.MainManager;

public class TestView extends View {

	protected Parent root;
	
	public TestView(String pFXMLFileName) {
		FXMLLoader fxmlloader;
		try {
			fxmlloader = new FXMLLoader(getClass().getResource("/"+pFXMLFileName));
			root = fxmlloader.load();
			//scene = new Scene(root);
			//controller.setUp(pIsEdit, pParentController, pEditor);
			((VBox)root).setAlignment(Pos.CENTER);
			//root.setStyle("Gluon");
			
			this.setCenter(root);
			
			//this.setScene(scene);
			//this.sizeToScene();
			//this.setResizable(false);
			
			//this.setTitle(zTitle);
			//this.initModality(Modality.WINDOW_MODAL);
			//this.setIconified(false);
			
			//this.show();
			//controller.setUp(pIsEdit, true, pParentController, pEditor);
		} catch(Exception ex) {;}//{MainManager.handleException(ex);}
	}

}
