/**	NSC_Generator v0.0		Dh	07.03.2021
 * 	
 * 	pGUI.pTableElement
 * 	  TableRowModel
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

package nsc_generator.pGUI.pTableElement;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;

import nsc_generator.pLogic.MainManager;

public class TableRowModel {
	private int distroElementID;
	private String distroElementName;
	private int[] idList;
	private double[] distroProbs; 
	
	/**	Dh	06.03.2021
	 * 
	 */
	public TableRowModel() {
		distroElementID = -1;
		distroElementName = "";
		
		idList = new int[0];
		distroProbs = new double[0];
	}
	/**	Dh	06.03.2021
	 * 
	 * @param pID
	 * @param pName
	 */
	public TableRowModel(int pDistroElementID, String pName, int pDistroNumber) {
		try{
			setDistroElementID(pDistroElementID);
			setDistroElementName(pName);
		} catch(Exception ex) {MainManager.handleException(ex);}
		
		idList = new int[pDistroNumber];
		distroProbs = new double[pDistroNumber];
	}
	
//--------------------------------------------------------------------------------------------------------

	/**	Dh	06.03.2021
	 * 
	 * @return
	 */
	public int getDistroElementID() {
		return distroElementID;
	}
	/**	Dh	06.03.2021
	 * 
	 * @return
	 */
	public String getDistroElementName() {
		return distroElementName;
	}
		
	/**	Dh	07.03.2021
	 * 
	 * @param pIndex
	 * @return
	 * @throws Exception
	 */
	public int getID(int pIndex) throws Exception{
		if ((pIndex >= 0) && (pIndex < idList.length)) return idList[pIndex];
		else throw new Exception("07; gID,TRM");
	}
	/**	Dh	06.03.2021
	 * 	
	 * @param pIndex
	 * @return
	 * @throws Exception
	 */
	public double getDistroProb(int pIndex) throws Exception {
		double vRet;
		
		if ((pIndex >= 0) && (pIndex < distroProbs.length)) vRet = distroProbs[pIndex];
		else throw new Exception("02; gDP,TRM");
		
		return vRet;
	}
	/**	Dh	06.03.2021
	 * 
	 * @return
	 */
	public double[] getDistroProbs() {
		return distroProbs;	
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/**	Dh	06.03.2021
	 * 
	 * @param pDistroElementID
	 * @throws Exception
	 */
	public void setDistroElementID(int pDistroElementID) throws Exception{
		if (pDistroElementID >= 0) distroElementID = pDistroElementID;
		else throw new Exception("02; sDEIF,TRM");
	}
	/**	Dh	06.03.2021
	 * 
	 * @param pElementName
	 * @throws Exception
	 */
	public void setDistroElementName(String pDistroElementName) throws Exception{
		if (pDistroElementName != null) distroElementName = pDistroElementName;
		else throw new Exception("04; sDEN,TRM");
	}
	
	/**	Dh	07.03.2021
	 * 
	 * @param pIndex
	 * @param pID
	 * @throws Exception
	 */
	public void setID(int pIndex, int pID) throws Exception{
		if ((pIndex >= 0) && (pIndex < idList.length)) {
			if (pID >= 0) idList[pIndex] = pID;
			else throw new Exception("02; sID,TRM");
		} else throw new Exception("07; sID,TRM");
	}
	/**	Dh	06.03.2021
	 * 
	 * @param pIndex
	 * @param pProb
	 * @throws Exception
	 */
	public void setDistroProb(int pIndex, double pProb) throws Exception{
		if ((pIndex >= 0) && (pIndex < distroProbs.length)) {
			if ((pProb >= 0) && (pProb <= 100)) distroProbs[pIndex]  = pProb;
			else throw new Exception("02; sDP,TRM");
		} else throw new Exception("07; sDP,TRM");
	}
	/**	Dh	06.03.2021
	 * 
	 * @param pProbs
	 * @throws Exception
	 */
	public void setDistroProbs(double[] pProbs) throws Exception{
		if (pProbs != null) {
			distroProbs = pProbs;
		} else throw new Exception("04; sDPs,TRM");
	}
	
//--------------------------------------------------------------------------------------------------------
	
	/**	Dh	06.03.2021
	 * 
	 * @return
	 */
	public static TableColumn<TableRowModel, String> creatTitleColumn(){
		TableColumn<TableRowModel, String> vRet = new TableColumn<>();
		//Stop[] stops = new Stop[] {new Stop(0, Color.LIGHTGRAY), new Stop(1, Color.GAINSBORO)};
		//LinearGradient vLG = new LinearGradient(0.0, 0.0, 25.0, 100.0, false, CycleMethod.REPEAT, stops);
		
		vRet.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TableRowModel,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<TableRowModel, String> param) {
				return new SimpleStringProperty( param.getValue().getDistroElementName() );
			}
		});
		/*vRet.setCellFactory(new Callback<TableColumn<TableRowModel,String>, TableCell<TableRowModel,String>>() {
			@Override
			public TableCell<TableRowModel, String> call(TableColumn<TableRowModel, String> param) {
				return new TableCell<TableRowModel, String>(){
					@Override
					public void updateIndex(int i) {
						super.updateIndex(i);
						
						this.setBackground(new Background(new BackgroundFill(vLG, null, getInsets())));
					}
				};
			}
		});*/
		vRet.setSortable(false);
		
		return vRet;
	}
	/**	Dh	06.03.2021
	 * 
	 * @param pColumnID
	 * @param pColumnName
	 * @return
	 */
	public static TableColumn<TableRowModel, Number> creatColumn(final int pColumnID, String pColumnName) {
		TableColumn<TableRowModel, Number> vRet = new TableColumn<>(pColumnName);
		//TableColumn<S, T>
		
		vRet.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
		vRet.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TableRowModel, Number>, ObservableValue<Number>>() {
			@Override
			public ObservableValue<Number> call(CellDataFeatures<TableRowModel, Number> param) {
				ObservableValue<Number> vRet = null;
				
				try {
					vRet = new SimpleDoubleProperty( param.getValue().getDistroProb(pColumnID) );
				} catch(Exception ex) {MainManager.handleException(ex);}
				
				return vRet;
			}
		});
		
		vRet.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TableRowModel,Number>>() {
			
			@Override
			public void handle(CellEditEvent<TableRowModel, Number> event) {
				double vValue = (event.getNewValue() != null) ? event.getNewValue().doubleValue() : event.getOldValue().doubleValue();
				
				try {event.getTableView().getItems().get(event.getTablePosition().getRow()).setDistroProb(pColumnID, vValue);}
				catch(Exception ex) {MainManager.handleException(ex);}
				
				event.getTableView().refresh();
			}
		});
		vRet.setEditable(true);
		vRet.setSortable(false);
		
		return vRet;
	}
	
	/**	Dh	06.03.2021
	 * 
	 * @param pTable
	 * @throws Exception
	 */
	public static void setTableEditable(TableView<TableRowModel> pTable) throws Exception {
		pTable.setEditable(true);
		pTable.getSelectionModel().cellSelectionEnabledProperty().set(true);
		
		/*pTable.setOnKeyPressed(event -> {
			if (event.getCode().isDigitKey()) editFocusedCell(pTable);
		});*/
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/*private static void editFocusedCell(TableView<TableRowModel> pTable) {
		TablePosition<TableRowModel, Number> focusedCell = pTable.focusModelProperty().get().focusedCellProperty().get();
		    pTable.edit(focusedCell.getRow(), focusedCell.getTableColumn());
	}*/
	
}
