/**	NSC_Generator v0.0		Dh	17.10.2021
 * 	
 * 	pLogic.pPack
 * 	  NameStructure
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
import javax.xml.bind.annotation.XmlType;

import pDataStructures.List;

@XmlRootElement(name = "nameStructure")
//@XmlType(propOrder = {"cultureList", "raceList", "quirkList"})
@XmlSeeAlso({NameField.class, ProbList.class, ProbElement.class, PrioList.class, PrioElement.class})

public class NameStructure {
	private List nameFieldList, nameListList;
	
	public NameStructure() {
		// TODO Auto-generated constructor stub
	}

//--------------------------------------------------------------------------------------------------------

	//----------------------------------------------------------------------------------------------------
	
//--------------------------------------------------------------------------------------------------------
	
}
