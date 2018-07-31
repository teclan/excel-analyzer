package excel.analyzer.handler;

import org.apache.poi.ss.usermodel.Row;

public interface Handler {
	
	public void handle(String outFileName,int rowIndex,Row row);
	

}
