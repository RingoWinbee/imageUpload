package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.Image;
import com.example.service.ImageService;
import com.example.util.Md5;
import com.sun.jersey.core.header.ContentDisposition;

@RestController
public class ProviderController {
	
	@Resource(name="ImageService")
	private ImageService imageService;
	
	//定义文件储存路径
	private String savePath="D:\\upload\\";
	/**
	 * 
	 * 用于给用户上传图片
	 * @RequestParam(value = "picture", required = true)
	 * @param HttpServletRequest  req
	 * @param HttpServletResponse response
	 * @throws Exception
	 * @throws IOException
	 */
	@RequestMapping(value = "/imageUpload", method = RequestMethod.POST)
	public @ResponseBody String handleFileUpload(@RequestParam(name = "picture", required = true)MultipartFile file) throws IOException {
		//定义文件后缀名
		String fileLastName=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
		//获得文件的二进制格式
		byte[] bytes = file.getBytes();
		//获取文件大小s
		long length = file.getSize();
		//将图片本身转化为32位哈希码,用于文件名来储存,防止文件重复
		String fileHash=new Md5().md5HashCode32(bytes);
		//在哈希码的前两位和后面其余位之间加/,从而实现储存的时候自己生成对应的文件夹来储存
		String twoFront=fileHash.substring(0,2);
		String otherBack=fileHash.substring(2,32);
		String newFileHash=twoFront+"/"+otherBack;
		//这里判断数据库里面是否有相同哈希值的图片,如果有则直接数据库的引用值加1
		if(imageService.isHasImage(fileHash)) {
			Image i=imageService.queryImageByHashCode(fileHash);
			imageService.updateLeadCount(fileHash, i.getLeadConut()+1);
		}else {//如果数据库没有重复文件,则要执行文件夹储存的操作
			//判断文件夹下有没有文件哈希值前两位对应的目录,如果没有则创建
		    File filePath = new File(savePath+twoFront);
		    if(!filePath.exists())//如果文件夹不存在
		    	filePath.mkdir();//创建文件夹
		    //将路径和文件名进行组合构建File对象
		    File fileToSave = new File(savePath+newFileHash);
		    //通过Spring的文件复制函数将二进制格式的文件复制到对应的路径下
		    FileCopyUtils.copy(bytes, fileToSave);
		    //将新建的文件信息同步至数据库
		    imageService.addImage(fileHash, newFileHash, fileLastName, length);
		}
	    //返回文件储存路径
	    return fileHash;
	  }
	
	/**
	 * 
	 * 用于响应用户删除图片
	 * 
	 * @param HttpServletRequest  req
	 * @param HttpServletResponse response
	 * @throws Exception
	 * @throws IOException
	 */
	@RequestMapping(value = "/deleteImage/{hashCode}", method = RequestMethod.POST)
	public @ResponseBody String handleFileDelete(@PathVariable("hashCode") String hashCode) {
		Image i=imageService.queryImageByHashCode(hashCode);
		if(i.getLeadConut()>0)
		imageService.updateLeadCount(hashCode, i.getLeadConut()-1);
		return "删除成功";
	}
	
	/**
	 * 
	 * 根据hash值返回图片
	 * 
	 * ResponseEntity<byte[]>
	 * produces = MediaType.IMAGE_PNG_VALUE
	 * @param HttpServletRequest  req
	 * @param HttpServletResponse response
	 * @throws Exception
	 * @throws IOException
	 * 
	 */
	@RequestMapping(value = "/image/{image_name}",produces = MediaType.IMAGE_PNG_VALUE)
	public byte[] handleFileGet(@PathVariable("image_name") String image_name) throws IOException {
		Image i=imageService.queryImageByHashCode(image_name);
		String filePath=savePath+i.getFilePath();
		InputStream fis=null;
		try {
			fis = new FileInputStream(new File(filePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] imageContent=FileCopyUtils.copyToByteArray(fis);
		return imageContent;
	}
	@RequestMapping("/provider/demo")
    public String Demo(){
        return "ProviderDemo";  
    }
}

