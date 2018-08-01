package excel.analyzer.handler;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import excel.analyzer.utils.ExcelUtils;
import excel.analyzer.utils.FileUtils;
import excel.analyzer.utils.Objects;

public class DefaultHandler implements Handler {
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultHandler.class);

	public void handle(String outFileName,int rowIndex, Row row) {
		
		for(int i=0;i<row.getLastCellNum();i++) {
			Cell cell = row.getCell(i);
			String value = ExcelUtils.getNumberValue(cell);
			
			if(Objects.isNotNullString(value)) {
				LOGGER.info("{}:坐标:({},{})",value,rowIndex+1,i+1);
				FileUtils.randomWrite2File(outFileName, String.format("%s		%s		%s\n", value,rowIndex+1,i+1));
			}
			
		}
		
	}

}
