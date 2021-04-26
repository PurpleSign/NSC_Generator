/**	NSC_Generator v0.0		Dh	11.03.2021
 * 	
 * 	pLogic.pPack
 * 	  SubElement
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

package nsc_generator.pLogic.pPack;

public interface SubElement {

	public int getParentID();
	
	public void setParentID(int pParentID);
	
}
