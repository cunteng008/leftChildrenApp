package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class FileUtil {

	/** 
     * 以行为单位读取文件，常用于读面向行的格式化文件。 
     *  
     * @param fileName 文件绝对路径 
     */  
    public static void readFileByLines(String fileName,Vector v) {  
        File file = new File(fileName);  
        BufferedReader reader = null;// 创建缓存读取  
        try {  
            System.out.println("①.以行为单位读取文件内容，一次读一整行：");  
            reader = new BufferedReader(new FileReader(file));// 将文件放在缓存读取中  
            String tempString = null;  
            int line = 1;  
            // 一次读入一行，直到读入null为文件结束  
            while ((tempString = reader.readLine()) != null) {  
                // 显示行号  
            	v.add(tempString);
                line++;  
            }  
            reader.close();  
        } catch (IOException e) {// 捕获异常  
            e.printStackTrace();  
        } finally {// 内容总执行  
            if (reader != null) {  
                try {  
                    reader.close();// 关闭缓存读取  
                } catch (IOException e1) {  
                }  
            }  
        }  
    }  
}
