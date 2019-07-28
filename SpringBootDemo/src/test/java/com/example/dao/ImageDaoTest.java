package com.example.dao;

import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.test.annotation.Rollback;

import com.example.entity.BaseEntity;
import com.example.entity.Image;

//一定要写extends BaseTestCaseJunit4,因为没写这个花费了很多时间!!!
public class ImageDaoTest extends BaseTestCaseJunit4 {

	@Resource(name="ImageDao")
	private InterfaceImageDao imageDao;

	@Test
	@Rollback(false)
	public void testAdd() {
		Image image = new Image();
		image.setHashCode("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		image.setLeadConut(0);
		image.setDownloadConut(0);
		image.setFilePath("aa/aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		image.setFileType("jps");
		image.setLength(389);
		imageDao.save(image);
	}

	@Test
	@Rollback(false)
	public void testUpdateLeadCount() {
//		Optional<Image> image = imageDao.findById("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//		if (image.isPresent()) {
//			Image i = image.get();
//			imageDao.updateLeadCount(1, i.getHashCode());
//		}
		imageDao.updateLeadCount(1, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
	}
	
	@Test
	@Rollback(false)
	public void testUpdateDownloadCount() {
//		Optional<Image> image = imageDao.findById("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//		if (image.isPresent()) {
//			Image i = image.get();
//			imageDao.updateLeadCount(1, i.getHashCode());
//		}
		imageDao.updateDownloadCount(1, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
	}
	
	@Test
	@Rollback(false)
	public void testQueryById() {
		Optional<Image> image = imageDao.findById("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		if (image.isPresent()) {
			System.out.println(image.get().getHashCode());
			
			
		}
	}
	
	@Test
	@Rollback(false)
	@Modifying//update或者delete操作，在更新或者删除操作时，此注解必须加
	public void testDelete() {
		Image i=new Image();
		i.setHashCode("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		imageDao.delete(i);
	}
	
}
