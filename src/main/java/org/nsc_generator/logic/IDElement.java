/**	NSC_Generator v0.21		Dh	15.08.2023
 * 	
 * 	logic
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

package org.nsc_generator.logic;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;

import org.nsc_generator.logic.pack.Culture;
import org.nsc_generator.logic.pack.GenElement;
import org.nsc_generator.logic.pack.Pack;
import org.nsc_generator.logic.pack.PrioElement;
import org.nsc_generator.logic.pack.PrioList;
import org.nsc_generator.logic.pack.ProbElement;
import org.nsc_generator.logic.pack.ProbList;
import org.nsc_generator.logic.pack.Race;
import org.nsc_generator.logic.pack.Subculture;
import org.nsc_generator.logic.pack.Subrace;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "_type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = GenElement.class, name = "_type"),
  @JsonSubTypes.Type(value = PrioElement.class, name = "_type"),
  @JsonSubTypes.Type(value = ProbElement.class, name = "_type"),
  @JsonSubTypes.Type(value = PrioList.class, name = "_type"),
  @JsonSubTypes.Type(value = ProbList.class, name = "_type"),
  @JsonSubTypes.Type(value = Culture.class, name = "_type"),
  @JsonSubTypes.Type(value = Subculture.class, name = "_type"),
  @JsonSubTypes.Type(value = Race.class, name = "_type"),
  @JsonSubTypes.Type(value = Subrace.class, name = "_type"),
  @JsonSubTypes.Type(value = Pack.class, name = "_type"),
  @JsonSubTypes.Type(value = NPC.class, name = "_type"),
  @JsonSubTypes.Type(value = Session.class, name = "_type")
})
@JsonPropertyOrder({
	"id", "name"
})
public abstract class IDElement {
	@JsonProperty("id")
	private int id;
	@JsonProperty("name")
	private String name;
	
	/**	Dh	24.02.2021
	 * 
	 */
	public IDElement() {
		id = -1;
		name = "";
	}
	/**	Dh	07.08.2023
	 * 
	 * @param pID
	 * @param pName
	 */
	public IDElement(int pID, String pName) {
		try {
			setId(pID);
			setName(pName);
		}catch(Exception ex) {LogManager.handleException(ex);}
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	24.02.2021
	 * 
	 * @return
	 */
	@XmlAttribute
	public int getId() {
		return id;
	}
	/**	Dh	24.02.2021
	 * 
	 * @return
	 */
	@XmlAttribute
	public String getName() {
		return name;
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	24.02.2021
	 * 
	 * @param pId
	 * @throws Exception
	 */
	public void setId(int pID) throws Exception{
		if (pID >= -1) id = pID;
		else throw new Exception("02; sId,IDEle:"+id+","+name);
	}
	/**	Dh	24.02.2021
	 * 
	 * @param pName
	 * @throws Exception
	 */
	public void setName(String pName) throws Exception{
		if (pName != null) name = pName;
		else throw new Exception("04; sNa,IDEle");
	}

//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	08.08.2023
	 * 
	 * @param <T>
	 * @param pArrayList
	 * @return
	 */
	/*protected <T> List convertArrayListToList(ArrayList<T> pArrayList) {
		List vRet = new List();
		
		if ((pArrayList != null) && (!pArrayList.isEmpty())) {
			for (T vTemp : pArrayList) {
				vRet.append(vTemp);
			}
		}
		
		return vRet;
	}*/
	
	/**	Dh	08.08.2023
	 * 
	 * @param <T>
	 * @param pList
	 * @return
	 */
	/*protected <T> ArrayList<T> convertListToArrayList(List pList){
		ArrayList<T> vRet = new ArrayList<T>();
		
		if ((pList != null) && (!pList.isEmpty())) {
			pList.toFirst();
			
			while(!pList.isEnd()) {
				vRet.add( (T)pList.getCurrent() );
				
				pList.next();
			}
		}
		
		return vRet;
	}*/
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	08.08.2023
	 * 
	 * @param pID
	 * @param pIDList
	 * @return
	 * @throws Exception
	 */
	protected boolean isIDInIDElementList(int pID, ArrayList<? extends IDElement> pIDList) throws Exception{
		boolean vRet = false;
		
		if (pIDList != null) {
			if (pID >= -1) {
				for (IDElement vCur : pIDList) {
					if (vCur.getId() == pID) vRet = true;
				}
			} else throw new Exception("02; ciIDaEiL,Pac");
		} else throw new Exception("04; ciIDaEiL,Pac");
		
		return vRet;
	}
	
}
