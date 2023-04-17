/**	NSC_Generator v0.0		Dh	17.10.2021
 * 	
 * 	pLogic
 * 	  IDElement
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

package org.nsc_generator.pLogic;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "non_player_character")
//@XmlType(propOrder = {})

public class NPC extends IDElement {
	private int raceID, cultureID, sexID, soID, sexualityID, hairlenghtID, haircolorID, eyecolorID, complexionID, quirkID;
	private int age, height, weight;
	private String culture, race, sex, so, sexuality, hairlength, haircolor, eyecolor, complexion, quirk, note;
	
	/**	Dh	17.10.2021
	 * 
	 */
	public NPC() {
		super();

		raceID = -1;
		cultureID = -1;
		
		sexID = -1;
		soID = -1;
		sexualityID = -1;
		hairlenghtID = -1;
		haircolorID = -1;
		eyecolorID = -1;
		complexionID = -1;
		quirkID = -1;
		
		age = 0;
		height = 0;
		weight = 0;
		
		culture = "";
		race = "";
		sex = "";
		so = "";
		sexuality = "";
		hairlength = "";
		haircolor = "";
		eyecolor = "";
		complexion = "";
		quirk = "";
		note = "";
	}
	/**	Dh	17.10.2021
	 * 
	 */
	public NPC(int pID) {
		super(pID, "");

		raceID = -1;
		cultureID = -1;
		
		sexID = -1;
		soID = -1;
		sexualityID = -1;
		hairlenghtID = -1;
		haircolorID = -1;
		eyecolorID = -1;
		complexionID = -1;
		quirkID = -1;
		
		age = 0;
		height = 0;
		weight = 0;
		
		culture = "";
		race = "";
		sex = "";
		so = "";
		sexuality = "";
		hairlength = "";
		haircolor = "";
		eyecolor = "";
		complexion = "";
		quirk = "";
		note = "";
	}

//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	07.03.2021
	 * 
	 * @return
	 */
	@XmlAttribute
	public int getCultureID() {
		return cultureID;
	}
	/**	Dh	07.03.2021
	 * 
	 * @return
	 */
	@XmlAttribute
	public int getRaceID() {
		return raceID;
	}
	/**	Dh	17.10.2021
	 * 
	 * @return
	 */
	@XmlAttribute
	public int getSexID() {
		return sexID;
	}
	/**	Dh	17.10.2021
	 * 
	 * @return
	 */
	@XmlAttribute
	public int getSoID() {
		return soID;
	}
	/**	Dh	17.10.2021
	 * 
	 * @return
	 */
	@XmlAttribute
	public int getSexualityID() {
		return sexualityID;
	}
	/**	Dh	17.10.2021
	 * 
	 * @return
	 */
	@XmlAttribute
	public int getHairlengthID() {
		return hairlenghtID;
	}
	/**	Dh	17.10.2021
	 * 
	 * @return
	 */
	@XmlAttribute
	public int getHaircolorID() {
		return haircolorID;
	}
	/**	Dh	17.10.2021
	 * 
	 * @return
	 */
	@XmlAttribute
	public int getEyecolorID() {
		return eyecolorID;
	}
	/**	Dh	17.10.2021
	 * 
	 * @return
	 */
	@XmlAttribute
	public int getComplexionID() {
		return complexionID;
	}
	/**	Dh	17.10.2021
	 * 
	 * @return
	 */
	@XmlAttribute
	public int getQuirkID() {
		return quirkID;
	}
	
	/**	Dh	23.02.2021
	 * 
	 * @return
	 */
	public int getAge() {
		return age;
	}
	/**	Dh	23.02.2021
	 * 
	 * @return
	 */
	public int getHeight() {
		return height;
	}
	/**	Dh	23.02.2021
	 * 
	 * @return
	 */
	public int getWeight() {
		return weight;
	}
	
	/**	Dh	23.02.2021
	 * 
	 * @return
	 */
	@XmlAttribute
	public String getCulture() {
		return culture;
	}
	/**	Dh	23.02.2021
	 * 
	 * @return
	 */
	@XmlAttribute
	public String getRace() {
		return race;
	}
	/**	Dh	23.02.2021
	 * 
	 * @return
	 */
	@XmlAttribute
	public String getSex() {
		return sex;
	}
	/**	Dh	07.03.2021
	 * 
	 * @return
	 */
	public String getSo() {
		return so;
	}
	/**	Dh	23.02.2021
	 * 
	 * @return
	 */
	public String getSexuality() {
		return sexuality;
	}
	/**	Dh	23.02.2021
	 * 
	 * @return
	 */
	public String getHairlength() {
		return hairlength;
	}
	/**	Dh	23.02.2021
	 * 
	 * @return
	 */
	public String getHaircolor() {
		return haircolor;
	}
	/**	Dh	23.02.2021
	 * 
	 * @return
	 */
	public String getEyecolor() {
		return eyecolor;
	}
	/**	Dh	12.03.2021
	 * 
	 * @return
	 */
	public String getComplexion() {
		return complexion;
	}
	/**	Dh	23.02.2021
	 * 
	 * @return
	 */
	public String getQuirk() {
		return quirk;
	}
	/**	Dh	23.02.2021
	 * 
	 * @return
	 */
	public String getNote() {
		return note;
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	12.03.2021
	 * 
	 * @param pCultureID
	 * @throws Exception
	 */
	public void setCultureID(int pCultureID) throws Exception{
		if (pCultureID >= -1) cultureID = pCultureID;
		else throw new Exception("02; sCID,NPC");
	}
	/**	Dh	12.03.2021
	 * 
	 * @param pRaceID
	 * @throws Exception
	 */
	public void setRaceID(int pRaceID) throws Exception{
		if (pRaceID >= -1) raceID = pRaceID;
		else throw new Exception("02; sRID,NPC");
	}
	/**	Dh	17.10.2021
	 * 
	 * @param pSexID
	 * @throws Exception
	 */
	public void setSexID(int pSexID) throws Exception{
		if (pSexID >= -1) sexID = pSexID;
		else throw new Exception("02; sSeID,NPC");
	}
	/**	Dh	17.10.2021
	 * 
	 * @param pSoID
	 * @throws Exception
	 */
	public void setSoID(int pSoID) throws Exception{
		if (pSoID >= -1) soID = pSoID;
		else throw new Exception("02; sSoID,NPC");
	}
	/**	Dh	17.10.2021
	 * 
	 * @param pSexualityID
	 * @throws Exception
	 */
	public void setSexualityID(int pSexualityID) throws Exception{
		if (pSexualityID >= -1) sexualityID = pSexualityID;
		else throw new Exception("02; sSeID,NPC");
	}
	/**	Dh	17.10.2021
	 * 
	 * @param pHairlengthID
	 * @throws Exception
	 */
	public void setHairlengthID(int pHairlengthID) throws Exception{
		if (pHairlengthID >= -1) hairlenghtID = pHairlengthID;
		else throw new Exception("02; sHlID,NPC");
	}
	/**	Dh	17.10.2021
	 * 
	 * @param pHaircolorID
	 * @throws Exception
	 */
	public void setHaircolorID(int pHaircolorID) throws Exception{
		if (pHaircolorID >= -1) haircolorID = pHaircolorID;
		else throw new Exception("02; sHcID,NPC");
	}
	/**	Dh	17.10.2021
	 * 
	 * @param pEyecolorID
	 * @throws Exception
	 */
	public void setEyecolorID(int pEyecolorID) throws Exception{
		if (pEyecolorID >= -1) eyecolorID = pEyecolorID;
		else throw new Exception("02; sEcID,NPC");
	}
	/**	Dh	17.10.2021
	 * 
	 * @param pComplexionID
	 * @throws Exception
	 */
	public void setComplexionID(int pComplexionID) throws Exception{
		if (pComplexionID >= -1) complexionID = pComplexionID;
		else throw new Exception("02; sCoID,NPC");
	}
	/**	Dh	17.10.2021
	 * 
	 * @param pQuirkID
	 * @throws Exception
	 */
	public void setQuirkID(int pQuirkID) throws Exception{
		if (pQuirkID >= -1) quirkID = pQuirkID;
		else throw new Exception("02; sQID,NPC");
	}
	
	/**	Dh	23.02.2021
	 * 
	 * 	Setzt das Alter (age) des NPCs. pAge muss größergleich 0 sein.
	 * 
	 * @param pAge
	 * @throws Exception
	 */
	public void setAge(int pAge) throws Exception{
		if (pAge >= 0) age = pAge;
		else throw new Exception("02; sAge,NPC");
	}
	/**	Dh	23.02.2021
	 * 
	 * 	Setzt die Größe(height) des NPCs. pHeight muss größergleich 0 sein.
	 * 
	 * @param pHeight
	 * @throws Exception
	 */
	public void setHeight(int pHeight) throws Exception{
		if (pHeight >= 0) height = pHeight;
		else throw new Exception("02; sHei,NPC");
	}
	/**	Dh	23.02.2021
	 * 
	 * 	Setzt das Gewicht(weight) des NPCs. pWeight muss größergleich 0 sein.
	 * 
	 * @param pWeight
	 * @throws Exception
	 */
	public void setWeight(int pWeight) throws Exception{
		if (pWeight >= 0) weight = pWeight;
		else throw new Exception("02; sWei,NPC");
	}
	
	/**	Dh	23.02.2021
	 * 
	 * 	Setzt die Kultur des NPCs. pCulture darf nicht null sein.
	 * 
	 * @param pCulture
	 * @throws Exception
	 */
	public void setCulture(String pCulture) throws Exception{
		if (pCulture != null) culture = pCulture;
		else throw new Exception("04; sCul,NPC");
	}
	/**	Dh	23.02.2021
	 * 
	 * 	Setzt die Rasse/Ethnie des NPCs. pRace darft nicht null sein.
	 * 
	 * @param pRace
	 * @throws Exception
	 */
	public void setRace(String pRace) throws Exception{
		if (pRace != null) race = pRace;
		else throw new Exception("04; sRac,NPC");
	}
	/**	Dh	23.02.2021
	 * 
	 * 	Setzt das Geschelcht des NPCs. pSex darft nicht null sein.
	 * 
	 * @param pSex
	 * @throws Exception
	 */
	public void setSex(String pSex) throws Exception{
		if (pSex != null) sex = pSex;
		else throw new Exception("04; sSex,NPC");
	}
	/**	Dh	07.03.2021
	 * 
	 * @param pSO
	 * @throws Exception
	 */
	public void setSo(String pSO) throws Exception{
		if (pSO != null) so = pSO;
		else throw new Exception("04; sSO,NPC");
	}
	/**	Dh	23.02.2021
	 * 
	 * 	Setzt die Sexualität des NPCs. pSexuality darft nicht null sein.
	 * 
	 * @param pSexuality
	 * @throws Exception
	 */
	public void setSexuality(String pSexuality) throws Exception{
		if (pSexuality != null) sexuality = pSexuality;
		else throw new Exception("04; sSeu,NPC");
	}
	/**	Dh	23.02.2021
	 * 
	 * 	Setzt die Haarlaenge des NPCs. pHairlenght darft nicht null sein.
	 * 
	 * @param pHairlength
	 * @throws Exception
	 */
	public void setHairlength(String pHairlength) throws Exception{
		if (pHairlength != null) hairlength = pHairlength;
		else throw new Exception("04; sHaL,NPC");
	}
	/**	Dh	23.02.2021
	 * 
	 * 	Setzt die Haarfarbe des NPCs. pHaircolor darft nicht null sein.
	 * 
	 * @param pHaircolor
	 * @throws Exception
	 */
	public void setHaircolor(String pHaircolor) throws Exception{
		if (pHaircolor != null) haircolor = pHaircolor;
		else throw new Exception("04; sHaC,NPC");
	}
	/**	Dh	23.02.2021
	 * 
	 * 	Setzt die Augenfarbe des NPCs. pEyecolor darft nicht null sein.
	 * 
	 * @param pEyecolor
	 * @throws Exception
	 */
	public void setEyecolor(String pEyecolor) throws Exception{
		if (pEyecolor != null) eyecolor = pEyecolor;
		else throw new Exception("04; sEyC,NPC");
	}
	/**	Dh	12.03.2021
	 * 
	 * 	Setzt den Teint des NPCs. pComplexion darft nicht null sein.
	 * 
	 * @param pComplexion
	 * @throws Exception
	 */
	public void setComplexion(String pComplexion) throws Exception {
		if (pComplexion != null) complexion = pComplexion;
		else throw new Exception("04; sCoC,NPC");
	}
	/**	Dh	23.02.2021
	 * 
	 * 	Setzt die Eigenschaft des NPCs. pQuirk darft nicht null sein.
	 * 
	 * @param pQuirk
	 * @throws Exception
	 */
	public void setQuirk(String pQuirk) throws Exception{
		if (pQuirk != null) quirk = pQuirk;
		else throw new Exception("04; sQui,NPC");
	}
	/**	Dh	23.02.2021
	 * 
	 * 	Setzt die Notizen des NPCs. pNote darft nicht null sein.
	 * 
	 * @param pNote
	 * @throws Exception
	 */
	public void setNote(String pNote) throws Exception{
		if (pNote != null) note = pNote;
		else throw new Exception("04; sNot,NPC");
	}
	
}
