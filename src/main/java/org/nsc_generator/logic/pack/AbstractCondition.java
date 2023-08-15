/**	NSC_Generator v0.2		Dh	07.08.2023
 * 	
 * 	logic.pack
 * 	  IDElement
 * 	    AbstractCondition
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


import org.nsc_generator.logic.IDElement;
import org.nsc_generator.logic.LogManager;

//@XmlRootElement(name = "abstractCondition")
//@XmlType(propOrder = {"cultureList", "raceList", "quirkList"})
//@XmlSeeAlso({ProbList.class, ProbElement.class, PrioList.class, PrioElement.class})

public abstract class AbstractCondition extends IDElement {
	private boolean negation;
	private int assignmentAttribute, compareMethod;
	private Object conditionValue1, conditionValue2;
	
	/**	Dh	17.10.2021
	 * 
	 */
	public AbstractCondition() {
		super();
		
		negation = false;
		assignmentAttribute = -1;
		compareMethod = -1;
	}
	/**	Dh	07.08.2023
	 * 
	 * @param pID
	 * @param pAssignmentAttribute
	 * @param pCompareMethode
	 * @param pConditionValue1
	 * @param pConditionValue2
	 */
	public AbstractCondition(int pID, int pAssignmentAttribute, int pCompareMethode, Object pConditionValue1, Object pConditionValue2) {
		super(pID, "");
		
		negation = false;
		try {
			setAssignmentAttribute(pAssignmentAttribute);
			setCompareMethod(pCompareMethode);
			
			setConditionValue1(pConditionValue1);
			setConditionValue2(pConditionValue2);
		}catch(Exception ex) {LogManager.handleException(ex);}
	}

//--------------------------------------------------------------------------------------------------------

	/**	Dh	17.10.2021
	 * 
	 * @return
	 */
	public boolean isNegation() {
		return negation;
	}
	
	/**	Dh	17.10.2021
	 * 
	 * @return
	 */
	public int getAssignmentAttribute() {
		return assignmentAttribute;
	}
	/**	Dh	17.10.2021
	 * 
	 * @return
	 */
	public int getCompareMethod() {
		return compareMethod;
	}
	
	/**	Dh	17.10.2021
	 * 
	 * @return
	 */
	public Object getConditionValue1() {
		return conditionValue1;
	}
	/**	Dh	17.10.2021
	 * 
	 * @return
	 */
	public Object getConditionValue2() {
		return conditionValue2;
	}
	
	//----------------------------------------------------------------------------------------------------
		
	/**	Dh	17.10.2021
	 * 
	 * @param pNegation
	 */
	public void setNegation(boolean pNegation) {
		negation = pNegation;
	}
	
	/**	Dh	17.10.2021
	 * 
	 * @param pAssignmentAttribute
	 * @throws Exception
	 */
	public void setAssignmentAttribute(int pAssignmentAttribute) throws Exception{
		if (pAssignmentAttribute >= 0 && pAssignmentAttribute <= 10) {
			assignmentAttribute = pAssignmentAttribute;
		} else throw new Exception("02; sAA,ACo");
	}
	/**	Dh	17.10.2021
	 * 
	 * @param pCompareMethod
	 * @throws Exception
	 */
	public void setCompareMethod(int pCompareMethod) throws Exception{
		if (pCompareMethod >= 0) {
			compareMethod = pCompareMethod;
		} else throw new Exception("02; sCM,ACo");
	}
	
	/**	Dh	17.10.2021
	 * 
	 * @param pConditionValue
	 * @throws Exception
	 */
	public void setConditionValue1(Object pConditionValue) throws Exception{
		if (pConditionValue != null) {
			conditionValue1 = pConditionValue;
		}else throw new Exception("04; sCV1,ACo");
	}
	/**	Dh	17.10.2021
	 * 
	 * @param pConditionValue
	 * @throws Exception
	 */
	public void setConditionValue2(Object pConditionValue) throws Exception{
		conditionValue2 = pConditionValue;
	}
	
//--------------------------------------------------------------------------------------------------------
	
	public abstract boolean isConditionSatisfied(Object pCompareValue) throws Exception;
	
}
