package excel.analyzer.utils;

import java.io.InputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import excel.analyzer.handler.Handler;

public class ExcelUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtils.class);

	public static void analyzer(String filePath, Handler handler) {

		Workbook wb = null;
		InputStream input = null;
		File file = null;
		try {
			file = new File(filePath);
			input = new FileInputStream(file);
		} catch (IOException e1) {
			LOGGER.error(e1.getMessage(), e1);
		}
		try {
			wb = WorkbookFactory.create(input);
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage(), e1);
		}

		try {
			for (int sheetNum = 0; sheetNum < wb.getNumberOfSheets(); sheetNum++) {
				Sheet sheet = wb.getSheetAt(sheetNum);
				LOGGER.info("sheet {},name:{}", sheetNum, sheet.getSheetName());

				int LastRow = sheet.getLastRowNum();

				for (int i = 0; i <= LastRow; i++) {
					Row row = sheet.getRow(i);
					handler.handle(file.getName().substring(0, file.getName().lastIndexOf("."))+".txt",i, row);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

	}

	public static String getValue(Cell cell) {

		if (Objects.isNull(cell)) {
			return "";
		}
		cell.setCellType(Cell.CELL_TYPE_STRING);
		if (Objects.isNullString(cell)) {
			return "";
		}
		return cell.getStringCellValue().trim();
	}

	public static String getPhoneValueString(Cell cell) {

		if (Objects.isNull(cell)) {
			return "";
		}
		if (Objects.isNullString(cell)) {
			return "";
		}

		try {
			BigDecimal bd = new BigDecimal(cell.getNumericCellValue());
			return bd.toPlainString();
		} catch (Exception e) {
			return getValue(cell);
		}
	}

	public static String getNumberValue(Cell cell) {

		if (Objects.isNull(cell)) {
			return "";
		}
		if (Objects.isNullString(cell)) {
			return "";
		}

		BigDecimal bd = new BigDecimal(cell.getNumericCellValue());
		return bd.toPlainString();
	}

}
