/**	NSC_Generator v0.0		Dh	17.10.2021
 * 	
 * 	pLogic.pPack
 * 	  IDElement
 * 	    NameField
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

package org.nsc_generator.logic.pack;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.nsc_generator.logic.IDElement;
import pDataStructures.List;

@XmlRootElement(name = "nameField")
//@XmlType(propOrder = {"cultureList", "raceList", "quirkList"})
@XmlSeeAlso({ProbList.class, ProbElement.class, PrioList.class, PrioElement.class})

public class NameField extends IDElement {
	private List nameListList, conditionList;
	
	
	public NameField() {
		// TODO Auto-generated constructor stub
	}

	public NameField(int pID, String pName) {
		super(pID, pName);
		// TODO Auto-generated constructor stub
	}

}
