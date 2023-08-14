/**	NSC_Generator v0.0		Dh	18.10.2021
 * 	
 * 	logic.pack
 * 	  IDElement
 * 	    AbstractCondition
 * 		  NumberCondition
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

public class NumberCondition extends AbstractCondition {

	public NumberCondition() {
		// TODO Auto-generated constructor stub
	}

	public NumberCondition(int pID, int pAssignmentAttribute, int pCompareMethode, Object pConditionValue1,
			Object pConditionValue2) {
		super(pID, pAssignmentAttribute, pCompareMethode, pConditionValue1, pConditionValue2);
		// TODO Auto-generated constructor stub
	}

//--------------------------------------------------------------------------------------------------------

		
	//----------------------------------------------------------------------------------------------------
		
	/**	Dh	18.10.2021
	 * 
	 */
	public void setAssignmentAttribute(int pAssignmentAttribute) throws Exception{
		if (pAssignmentAttribute <= 10) {
			super.setAssignmentAttribute(pAssignmentAttribute);
		} else throw new Exception("02; sAA,NCo");
	}
	/**	Dh	18.10.2021
	 * 
	 */
	public void setCompareMethod(int pCompareMethod) throws Exception{
		if (pCompareMethod <= 10) {
			super.setCompareMethod(pCompareMethod);
		} else throw new Exception("02; sCM,NCo");
	}
		
	/**	Dh	18.10.2021
	 * 
	 * @param pConditionValue
	 * @throws Exception
	 */
	public void setConditionValue1(double pConditionValue) throws Exception{
		super.setConditionValue1(pConditionValue);
	}
	/**	Dh	18.10.2021
	 * 
	 * @param pConditionValue
	 * @throws Exception
	 */
	public void setConditionValue2(double pConditionValue) throws Exception{
		super.setConditionValue2(pConditionValue);
	}
		
//--------------------------------------------------------------------------------------------------------
		
	/**	Dh	18.10.2021
	 * 
	 */
	public boolean isConditionSatisfied(Object pCompareValue) throws Exception{
		if (pCompareValue instanceof Double ) return isConditionSatisfied((double) pCompareValue);
		else throw new Exception("06; iCoS,NCo");
	}
	
	public boolean isConditionSatisfied(double pCompareValue) throws Exception{
		boolean vRet = false;
		
		return vRet;
	}
	
}
