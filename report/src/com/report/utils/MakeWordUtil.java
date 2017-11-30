package com.report.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;
import freemarker.template.Configuration;
import freemarker.template.Template;
/**
 * 文件下载工具类
 */
public class MakeWordUtil {
	
	/**
	 * 将模板和数据模型合并生成文件
	 * @param dataMap
	 * @param fileType
	 * @param filePath
	 * @param fileName
	 */
	public static  void createWord(Map<String, Object> dataMap, String fileType,String filePath ,String fileName) {
		try {
			// 创建配置实例
			Configuration configuration = new Configuration();
			// 设置编码
			configuration.setDefaultEncoding("UTF-8");
			// 设置FreeMarker的模版文件位置
			configuration.setClassForTemplateLoading(MakeWordUtil.class,File.separator+"com"+File.separator+"report"+File.separator+"word"+File.separator);
			// 获取模板
			Template template = configuration.getTemplate(fileType); 
			// 输出文件
			File outFile;
			if(fileName.indexOf(".xls")>-1){//是否下载xlsx文件
				outFile = new File(filePath + File.separator + fileName);
			}else{
				outFile = new File(filePath + File.separator + fileName+ ".doc");
			}
			// 如果输出目标文件夹不存在，则创建
			if (!outFile.getParentFile().exists()) {
				outFile.getParentFile().mkdirs();
			}
			// 将模板和数据模型合并生成文件
			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(outFile), "UTF-8"));
			// 生成文件
			template.process(dataMap, out);

			// 关闭流
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * 删除word
     * @param file
     */
    public static void deleteFile(File file){ 
		if(file.exists()){                    
		    if(file.isFile()){                    
		    	file.delete();                       
		    }else if(file.isDirectory()){              
		    	File files[] = file.listFiles();               
		    		for(int i=0;i<files.length;i++){            
		    			deleteFile(files[i]);             
		    		} 
		    } 
		    file.delete(); 
		 }
	} 
    
}
