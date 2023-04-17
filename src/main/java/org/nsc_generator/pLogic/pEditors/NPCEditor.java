/**	NSC_Generator v0.0		Dh	17.10.2021
 * 	
 * 	pLogic.pEditors
 * 	  Editor
 * 	    NPCEditor
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

package org.nsc_generator.pLogic.pEditors;

import pDataStructures.List;
import org.nsc_generator.pLogic.MainManager;
import org.nsc_generator.pLogic.NPC;
import org.nsc_generator.pLogic.pPack.PrioElement;
import org.nsc_generator.pLogic.pPack.ProbElement;

public class NPCEditor extends Editor {
	private int raceID, cultureID;
	
	private SessionEditor sessionEditor;
	private NPC npc;
	
	/**	Dh	07.03.2021
	 * 
	 * @param pPack
	 * @param pNPC
	 */
	public NPCEditor(NPC pNPC, SessionEditor pSessionEditor) {
		if ((pSessionEditor != null) && (pNPC != null)) {
			npc = pNPC;
			sessionEditor = pSessionEditor;
			
			cultureID = npc.getCultureID();
			raceID = npc.getRaceID();
		}else MainManager.handleException(null);
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	07.03.2021
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getName() throws Exception{
		return npc.getName();
	}
	
	/**	Dh	07.03.2021
	 * 
	 * @return
	 */
	public String getRace() {
		return npc.getRace();
	}
	/**	Dh	07.03.2021
	 * 
	 * @return
	 */
	public String getCulture() {
		return npc.getCulture();
	}
	/**	Dh	07.03.2021
	 * 
	 * @return
	 */
	public String getJob() {
		return null;
	}
	/**	Dh	07.03.2021
	 * 
	 * @return
	 */
	public String getSex() {
		return npc.getSex();
	}
	
	/**	Dh	07.03.2021
	 * 
	 * @return
	 */
	public String getSO() {
		return npc.getSo();
	}
	/**	Dh	07.03.2021
	 * 
	 * @return
	 */
	public int getAge() {
		return npc.getAge();
	}
	/**	Dh	07.03.2021
	 * 
	 * @return
	 */
	public int getHeight() {
		return npc.getHeight();
	}
	/**	Dh	07.03.2021
	 * 
	 * @return
	 */
	public int getWeight() {
		return npc.getWeight();
	}
	
	/**	Dh	12.03.2021
	 * 
	 * @return
	 */
	public String getComplexion() {
		return npc.getComplexion();
	}
	/**	Dh	07.03.2021
	 * 
	 * @return
	 */
	public String getHairlength() {
		return npc.getHairlength();
	}
	/**	Dh	07.03.2021
	 * 
	 * @return
	 */
	public String getHaircolor() {
		return npc.getHaircolor();
	}
	/**	Dh	07.03.2021
	 * 
	 * @return
	 */
	public String getEyecolor() {
		return npc.getEyecolor();
	}
	
	/**	Dh	07.03.2021
	 * 
	 * @return
	 */
	public String getQuirk() {
		return npc.getQuirk();
	}
	/**	Dh	07.03.2021
	 * 
	 * @return
	 */
	public String getSexuality() {
		return npc.getSexuality();
	}
	
	/**	Dh	08.03.2021
	 * 
	 * @return
	 * @throws Exception
	 */
	public List getPossibleRegionCultures() throws Exception{
		return genObjectArrayListFromIDElementList(sessionEditor.getUsedPack().getCultureList());
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	07.03.2021
	 * 
	 * @param pName
	 * @throws Exception
	 */
	public void setName(String pName) throws Exception {
		npc.setName(pName);
	}
	
	/**	Dh	07.03.2021
	 * 
	 * @param pRace
	 * @throws Exception
	 */
	public void setRace(String pRace) throws Exception {
		npc.setRace(pRace);
	}
	/**	Dh	07.03.2021
	 * 
	 * @param pCulture
	 * @throws Exception
	 */
	public void setCulture(String pCulture) throws Exception {
		npc.setCulture(pCulture);
	}
	
	public void setJob(String pJob) throws Exception {
		
	}
	/**	Dh	07.03.2021
	 * 
	 * @param pSex
	 * @throws Exception
	 */
	public void setSex(String pSex) throws Exception {
		npc.setSex(pSex);
	}
	
	/**	Dh	07.03.2021
	 * 
	 * @param pSO
	 * @throws Exception
	 */
	public void setSO(String pSO) throws Exception {
		npc.setSo(pSO);
	}
	/**	Dh	07.03.2021
	 * 
	 * @param pAge
	 * @throws Exception
	 */
	public void setAge(int pAge) throws Exception {
		npc.setAge(pAge);
	}
	/**	Dh	07.03.2021
	 * 
	 * @param pHeight
	 * @throws Exception
	 */
	public void setHeight(int pHeight) throws Exception {
		npc.setHeight(pHeight);
	}
	/**	Dh	07.03.2021
	 * 
	 * @param pWeight
	 * @throws Exception
	 */
	public void setWeight(int pWeight) throws Exception {
		npc.setWeight(pWeight);
	}
	
	/**	Dh	12.03.2021
	 * 
	 * @param pComplexion
	 * @throws Exception
	 */
	public void setComplexion(String pComplexion) throws Exception {
		npc.setComplexion(pComplexion);
	}
	/**	Dh	07.03.2021
	 * 
	 * @param pHairlength
	 * @throws Exception
	 */
	public void setHairlength(String pHairlength) throws Exception {
		npc.setHairlength(pHairlength);
	}
	/**	Dh	07.03.2021
	 * 
	 * @param pHaircolor
	 * @throws Exception
	 */
	public void setHaircolor(String pHaircolor) throws Exception {
		npc.setHaircolor(pHaircolor);
	}
	/**	Dh	07.03.2021
	 * 
	 * @param pEyecolor
	 * @throws Exception
	 */
	public void setEyecolor(String pEyecolor) throws Exception {
		npc.setEyecolor(pEyecolor);
	}
	
	/**	Dh	07.03.2021
	 * 
	 * @param pQuirk
	 * @throws Exception
	 */
	public void setQuirk(String pQuirk) throws Exception {
		npc.setQuirk(pQuirk);
	}
	/**	Dh	07.03.2021
	 * 
	 * @param pSexuality
	 * @throws Exception
	 */
	public void setSexuality(String pSexuality) throws Exception {
		npc.setSexuality(pSexuality);
	}
		
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	12.03.2021
	 * 
	 * @return
	 */
	public boolean hasRaceID() {
		return (raceID != -1);
	}
	/**	Dh	12.03.2021
	 * 
	 * @return
	 */
	public boolean hasCultureID() {
		return (cultureID != -1);
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	07.03.2021
	 * 
	 * @throws Exception
	 */
	public void add() throws Exception{
		sessionEditor.addNPC(npc);
	}
	/**	Dh	07.03.2021
	 * 
	 * @throws Exception
	 */
	public void remove() throws Exception{
		sessionEditor.removeNPC(npc.getId());
	}
	
//--------------------------------------------------------------------------------------------------------
	
	public void genName() {
		
	}
	
	/**	Dh	12.03.2021
	 * 
	 * @throws Exception
	 */
	public void genRace() throws Exception {
		ProbElement vProEle;
		
		if (cultureID >= 0) {
			vProEle = sessionEditor.getUsedPack().getCulture(cultureID).genRace();
			
			npc.setRace(vProEle.getName());
			npc.setRaceID(vProEle.getId());
			raceID = vProEle.getId();
		} else throw new Exception("02; gRa,NPCEdi");
	}
	/**	Dh	12.03.2021
	 * 
	 * @param pCurrentRegionCulturID
	 * @throws Exception
	 */
	public void genCulture(int pCurrentRegionCulturID) throws Exception {
		ProbElement vProEle;
		
		if (pCurrentRegionCulturID >= 0) {
			vProEle = sessionEditor.getUsedPack().getCulture(pCurrentRegionCulturID).genOriginCulture();
			
			npc.setCulture(vProEle.getName());
			npc.setCultureID(vProEle.getId());
			cultureID = vProEle.getId();
		} else throw new Exception("02; gCu,NPCEdi");
	}
	
	public void genJob() {
	
	}
	/**	Dh	17.10.2021
	 * 
	 * @throws Exception
	 */
	public void genSex() throws Exception {
		ProbElement vProEle;
		
		if (raceID >= 0) {
			vProEle = sessionEditor.getUsedPack().getRace(raceID).genSex();
			
			npc.setSexID(vProEle.getId());
			npc.setSex(vProEle.getName());
		} else throw new Exception("02; gSe,NPCEdi");
	}
	
	/**	Dh	17.10.2021
	 * 
	 * @throws Exception
	 */
	public void genSO() throws Exception {
		ProbElement vProEle;
		
		if (cultureID >= 0) {
			vProEle = sessionEditor.getUsedPack().getCulture(cultureID).genSo();
			
			npc.setSoID(vProEle.getId());
			npc.setSo(vProEle.getName());
		} else throw new Exception("02; gSo,NPCEdi");
	}
	/**	Dh	12.03.2021
	 * 
	 * @throws Exception
	 */
	public void genAge() throws Exception {
		if (raceID >= 0) npc.setAge(sessionEditor.getUsedPack().getRace(raceID).genAge());
		else throw new Exception("02; gAg,NPCEdi");
	}
	/**	Dh	12.03.2021
	 * 
	 * @throws Exception
	 */
	public void genHeight() throws Exception {
		if (raceID >= 0) npc.setHeight(sessionEditor.getUsedPack().getRace(raceID).genHeight());
		else throw new Exception("02; gHe,NPCEdi");
	}
	/**	Dh	12.03.2021
	 * 
	 * @throws Exception
	 */
	public void genWeight() throws Exception {
		if ((raceID >= 0) && (npc.getHeight() >= 0))
			npc.setWeight(sessionEditor.getUsedPack().getRace(raceID).genWeight(npc.getHeight()));
		else throw new Exception("02; gWe,NPCEdi");
	}
	
	/**	Dh	17.10.2021
	 * 
	 * @throws Exception
	 */
	public void genComplexion() throws Exception {
		ProbElement vProEle;
		
		if (raceID >= 0) {
			vProEle = sessionEditor.getUsedPack().getRace(raceID).genComplexion();
			
			npc.setComplexionID(vProEle.getId());
			npc.setComplexion(vProEle.getName());
		} else throw new Exception("02; gCo,NPCEdi");
	}
	/**	Dh	17.10.2021
	 * 
	 * @throws Exception
	 */
	public void genHairlength() throws Exception {
		ProbElement vProEle;
		
		if (cultureID >= 0) {
			vProEle = sessionEditor.getUsedPack().getCulture(cultureID).genHairlength();
			
			npc.setHairlengthID(vProEle.getId());
			npc.setHairlength(vProEle.getName());
		} else throw new Exception("02; gHl,NPCEdi");
	}
	/**	Dh	17.10.2021
	 * 
	 * @throws Exception
	 */
	public void genHaircolor() throws Exception {
		ProbElement vProEle;
		
		if (raceID >= 0) {
			vProEle = sessionEditor.getUsedPack().getRace(raceID).genHaircolor();
			
			npc.setHaircolorID(vProEle.getId());
			npc.setHaircolor(vProEle.getName());
		} else throw new Exception("02; gHc,NPCEdi");
	}
	/**	Dh	17.10.2021
	 * 
	 * @throws Exception
	 */
	public void genEyecolor() throws Exception {
		ProbElement vProEle;
		
		if (raceID >= 0) {
			vProEle = sessionEditor.getUsedPack().getRace(raceID).genEyecolor();
			
			npc.setEyecolorID(vProEle.getId());
			npc.setEyecolor(vProEle.getName());
		} else throw new Exception("02; gEc,NPCEdi");
	}

	/**	Dh	17.10.2021
	 * 
	 * @throws Exception
	 */
	public void genQuirk() throws Exception {
		PrioElement vPriEle = sessionEditor.getUsedPack().genQuirk();
		
		npc.setQuirkID(vPriEle.getId());
		npc.setQuirk(vPriEle.getName());
	}
	/**	Dh	17.10.2021
	 * 
	 * @throws Exception
	 */
	public void genSexuality() throws Exception {
		ProbElement vProEle;
		
		if (cultureID >= 0) {
			vProEle = sessionEditor.getUsedPack().getCulture(cultureID).genSexuality();
			
			npc.setSexualityID(vProEle.getId());
			npc.setSexuality(vProEle.getName());
		} else throw new Exception("02; gSeu,NPCEdi");
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	14.03.2021
	 * 
	 * @param pCurRegCultureID
	 * @throws Exception
	 */
	public void genAll(int pCurRegCultureID) throws Exception {
		sessionEditor.getUsedPack().genCharacter(pCurRegCultureID, npc);
	}
	
}
