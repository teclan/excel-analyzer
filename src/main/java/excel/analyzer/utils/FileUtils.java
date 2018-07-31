package excel.analyzer.utils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

	 /**
     * @author Teclan 向文件追加内容，如果文件不存在，创建文件
     * @param fileName
     *            文件路径
     * @param content
     *            文件内容
     * 
     */
    public static void randomWrite2File(String fileName, String content) {
        RandomAccessFile randomFile = null;
        try {
            creatIfNeed(fileName);
            randomFile = new RandomAccessFile(fileName, "rw");
            long fileLength = randomFile.length();
            randomFile.seek(fileLength);
            randomFile.writeBytes(content);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                if (randomFile != null) {
                    randomFile.close();
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }
    
    public static void creatIfNeed(String fileName) {
        try {
            File parentFile = new File(fileName).getParentFile();
            if (parentFile != null) {
                parentFile.mkdirs();
            }
            new File(fileName).createNewFile();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}
