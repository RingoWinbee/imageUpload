package com.example.service;

import java.util.Optional;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.example.dao.InterfaceImageDao;
import com.example.entity.Image;

@Service("ImageService")
public class ImageService {
	
	@Resource(name="ImageDao")
	private InterfaceImageDao imageDao;
	
	@Transactional
	public void addImage(String hashCode,String filePath,String fileType,long length) {
		Image i=new Image();
		i.setHashCode(hashCode);
		i.setFilePath(filePath);
		i.setFileType(fileType);
		i.setLeadConut(1);
		i.setDownloadConut(0);
		i.setLength(length);
		imageDao.save(i);
	}
	
	@Transactional
	//用于判断数据库里是否已经有这张图片
	public boolean isHasImage(String hashCode) {
		Optional<Image> image = imageDao.findById(hashCode);
		if (image.isPresent())
			return true;
		else
			return false;
	}
	
	@Transactional
	//用于修改图片的引用次数
	public void updateLeadCount(String hashCode,int leadCount) {
		imageDao.updateLeadCount(leadCount, hashCode);
	}
	
	@Transactional
	//用于修改图片的下载次数
	public void updateDownloadCount(String hashCode,int downloadCount) {
		imageDao.updateDownloadCount(downloadCount, hashCode);
	}
	
	@Transactional
	//用于获取该图片
	public Image queryImageByHashCode(String hashCode) {
		Optional<Image> image=imageDao.findById(hashCode);
		if (image.isPresent())
			return image.get();
		else 
			return null;
	}
	
	@Transactional
	@Modifying//update或者delete操作，在更新或者删除操作时，此注解必须加
	//用于删除图片
	public void deleteImage(String hashCode) {
		Image i=new Image();
		i.setHashCode(hashCode);
		imageDao.delete(i);
	}
}
