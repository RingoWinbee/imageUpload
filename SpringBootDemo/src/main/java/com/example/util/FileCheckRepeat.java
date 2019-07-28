package com.example.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileCheckRepeat {
	
	
	/** 
     * 判断对应的目录下有没有重复的文件名 
     * @param filePath  文件路径 
     * @return 
     * @throws FileNotFoundException 
     */  
	public boolean isHasRepeat(String filePath,String fileHash) throws FileNotFoundException {
		String[] list=new File(filePath).list();
		for(String fileName:list) {
			//如果文件夹下有和要储存的图片名字一样即代表是同一张图片,存在重复,返回true
			if(fileName.equals(fileHash))
				return true;
		}
		//不存在重复,返回false
		return false;
		
	}
}
