package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Reporter;


public class ExcelConfig {
	
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell cell;
	private static XSSFRow row;

	/*
	 * DOES THE CONFIGURATION FOR THE EXCEL PATH
	 * 
	 */
	public static void setExcelFile(String path) throws Exception {
		try {
			FileInputStream ExcelFile = new FileInputStream(path);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
		} catch (Exception e) {
			Log.info("Error in excel file in the method:setExcelFile");
			throw (e);
		}
	}

	/*
	 * READ THE DATA FROM EXCEL FILE BASED ON ROW NO, CELL NO, AND SHEET NAME
	 * 
	 */

	public static String getCellData(int iRowNum, int iColNum, String sSheetName) throws Exception {
		ExcelWSheet = ExcelWBook.getSheet(sSheetName);
		try {
			cell = ExcelWSheet.getRow(iRowNum).getCell(iColNum);
			String sCellData = cell.getStringCellValue().trim();
			return sCellData;
		} catch (Exception e) {
			Log.info("Error in excel file in the method: getCellData");			
			throw (e);
		}
	}

	/*
	 * WRITE THE DATA INTO THE EXCELFILE. *
	 * 
	 */
	public static void setCellData(String sResult, int iRowNum, int iColNum, String sSheetName, String PathExcel)
			throws Exception {
		ExcelWSheet = ExcelWBook.getSheet(sSheetName);
		try {
			row = ExcelWSheet.getRow(iRowNum);
			cell = row.getCell(iColNum);
			if (cell == null) {
				cell = row.createCell(iColNum);
				cell.setCellValue(sResult);
			} else {
				cell.setCellValue(sResult);
			}
			FileOutputStream fileOut = new FileOutputStream(PathExcel);
			ExcelWBook.write(fileOut);
			// fileOut.flush();
			fileOut.close();
			ExcelWBook = new XSSFWorkbook(new FileInputStream(PathExcel));
		} catch (Exception e) {
			Log.info("Error in excel file in the method: setCellData");			
			throw (e);
		}
	}
	/*
	 * THW METHOD GET THE ROW NO FROM THE EXCEL SHEET FOR THE TEST CASE ID *
	 * 
	 */

	public static int getRowContains(String sTestName, int iColNum, String sSheetName) throws Exception {
		int i;
		ExcelWSheet = ExcelWBook.getSheet(sSheetName);
		try {
			int iRowCount = ExcelConfig.getRowUsed(sSheetName);
			boolean found = false;
			for (i = 0; i < iRowCount; i++) {
				
				if (ExcelConfig.getCellData(i, iColNum, sSheetName).equalsIgnoreCase(sTestName)) {
					found = true;
					break;
				}
			}
			if (!found) {
				throw new Exception();
			}
			
			return i;
		} catch (Exception e) {
			Log.info("Given testid is invalid");
			throw (e);
		}
	}

	/*
	 * GET THE NO OF ROW USED IN A GIVEN SHEET *
	 * 
	 */

	public static int getRowUsed(String sSheetName) throws Exception {
		ExcelWSheet = ExcelWBook.getSheet(sSheetName);
		try {
			int iRowCount = ExcelWSheet.getLastRowNum();
			return iRowCount;
		} catch (Exception e) {
			Log.info("Error in excel file in the method: getRowUsed");
			throw (e);
		}
	}

}
