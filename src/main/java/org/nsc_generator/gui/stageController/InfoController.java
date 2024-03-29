/**	NSC_Generator v0.1		Dh	22.05.2021
 * 	
 * 	gui.stageController
 * 	  EditorController
 * 	    InfoController
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
 * 
 */

package org.nsc_generator.gui.stageController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import org.nsc_generator.gui.stages.InfoStage;

public class InfoController extends EditorController {
	@FXML
	private Label lTitle, lText;
	@FXML
	private Button btCloButton;
	
	private InfoStage infoStage;
	
	/**	Dh	08.03.2021
	 * 
	 */
	public InfoController() {
		super();
	}
	
	/**	Dh	08.03.2021
	 * 
	 * @param pMessage
	 * @param pInfoStage
	 */
	public void setUpTwo(String pMessage, InfoStage pInfoStage) {
		lText.setText(pMessage);
		infoStage = pInfoStage;
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	08.03.2021
	 * 
	 */
	@FXML
	protected void apply() {}
	
	/**	Dh	08.03.2021
	 * 
	 */
	@FXML
	protected void delete() {}
	
	/**	Dh	22.05.2021
	 * 
	 */
	@FXML
	public void back() {
		infoStage.close();		
	}
	
//--------------------------------------------------------------------------------------------------------

}
