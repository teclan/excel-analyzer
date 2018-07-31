package excel.analyzer;

import java.io.Console;
import java.io.File;

import org.slf4j.Logger;

import excel.analyzer.handler.DefaultHandler;
import excel.analyzer.handler.Handler;
import excel.analyzer.utils.ExcelUtils;


public class Main {
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {

		Handler handler = new DefaultHandler();

		File file = new File("excel");

		if (file.exists()) {
			
			File[] files = file.listFiles();
			
			for(File f:files) {
				LOGGER.info("\n\n正在解析文件 :{}\n\n",f.getName());
				ExcelUtils.analyzer(f.getAbsolutePath(), handler);
				LOGGER.info("\n\n=====================================================\n\n");
			}
		
			
		} else {
			LOGGER.error("\n\n当前目录没有找到文件夹 excel,请确认后重试 ...");
		}

		System.out.println("\n\n完成，按任意键关闭窗口 ...");
		Console console = System.console();
		while (console.readLine() != null) {
			System.exit(0);
		}
	}

}
