/**	NSC_Generator v0.2		Dh	14.08.2023
 * 	
 * 	logic.editors
 * 	  Editor
 * 	    PackEditor
 * 	      PackManager
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

package org.nsc_generator.logic.editors;

import java.io.File;
import java.util.ArrayList;

import org.nsc_generator.logic.DatabaseConnector;
import org.nsc_generator.logic.LogManager;
import org.nsc_generator.logic.pack.Pack;

public class PackManager extends PackEditor {
	private int oldPackID;
	private SessionEditor sessionEditor;
	
	/**	Dh	11.03.2021
	 * 
	 * @param pPackEditor
	 */
	public PackManager(SessionEditor pSessionEditor) {
		super();
		
		sessionEditor = pSessionEditor;
		
		if (pSessionEditor != null) {
			pack = pSessionEditor.getUsedPack();
			
			if (pack != null) oldPackID = pack.getId();
			else oldPackID = -1;
		} else oldPackID = -1;
	}

//--------------------------------------------------------------------------------------------------------

	/**	Dh	14.08.2023
 	 * 
 	 * @return
 	 */
 	public ArrayList<Object[]> getPackList() throws Exception{
 		return genObjectArrayListFromIDElementList(DatabaseConnector.getPacks());
 	}
 	
 	//----------------------------------------------------------------------------------------------------
 	
 	/**	Dh	09.03.2021
 	 * 
 	 * @param pID
 	 * @throws Exception
 	 */
 	public void setPack(int pID) throws Exception{
 		Pack vCur = DatabaseConnector.getPack(pID);
 		
 		if (vCur != null) pack = vCur;
 		else throw new Exception("04; sPa,PaEd");
 	}
	
//--------------------------------------------------------------------------------------------------------
	
 	/**	Dh	09.03.2021
 	 * 
 	 * @throws Exception
 	 */
 	public PackEditor newPack() throws Exception{
 		return new PackEditor(DatabaseConnector.newPack());
 	}
	/**	Dh	10.03.2021
	 * 
	 * @return
	 * @throws Exception
	 */
 	public PackEditor editPack() throws Exception{
 		if (pack != null) return new PackEditor(pack);
 		else throw new Exception("04; ePa,PaMa");
 	}
 	
 	/**	Dh	19.04.2021
 	 * 
 	 * @param pFile
 	 * @throws Exception
 	 */
 	public void importPack(File pFile) throws Exception{
 		Pack vNewPack;
 		
 		vNewPack = DatabaseConnector.importPack(pFile);
 		
 		if (vNewPack != null) pack = vNewPack;
 	}
 	/**	Dh	19.04.2021
 	 * 
 	 * @param pSaveFile
 	 * @throws Exception
 	 */
 	public void exportPack(File pSaveFile) throws Exception{
 		if (pack != null) DatabaseConnector.exportPack(pack.getId(), pSaveFile);
 	}
 	
	/**	Dh	07.08.2023
	 * 
	 */
 	public void delete() throws Exception{
 		if (pack.getId() != oldPackID) super.delete();
 		else LogManager.handleMessage("Diese Pack kann nicht gelöscht werden.");
 	}
 	
 	//----------------------------------------------------------------------------------------------------
 	
 	/**	Dh	11.03.2021
 	 * 
 	 */
 	public void applyCurrentPack(boolean isNewSession) throws Exception {
 		if ((sessionEditor != null) && (pack != null)) {
 			if (isNewSession)sessionEditor.setNewPackID(pack.getId());
 			else sessionEditor.setCurrentPack(pack.getId());
 		} else throw new Exception("04; aCP,PaMan");
 	}
 	
//--------------------------------------------------------------------------------------------------------
	
}
