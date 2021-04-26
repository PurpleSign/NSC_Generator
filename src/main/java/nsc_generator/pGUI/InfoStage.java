/**	NSC_Generator v0.0		Dh	14.03.2021
 * 	
 * 	pGUI
 * 	  EditorStage
 * 	    InfoStage
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

package nsc_generator.pGUI;

import nsc_generator.pGUI.pController.InfoController;

public class InfoStage extends EditorStage {

	/**	Dh	14.03.2021
	 * 
	 * @param pIsError
	 * @param pMessage
	 */
	public InfoStage(boolean pIsError, String pMessage) {
		super("pScene/Info_Scene.fxml", "Test", pIsError, null, null);
		
		if (pIsError) this.setTitle("Fehler!");
		else this.setTitle("Information");
		
		((InfoController)controller).setUpTwo(pMessage, this);
	}

}
