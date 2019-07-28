package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Image;

@Repository("ImageDao")
public interface InterfaceImageDao extends JpaRepository<Image, String> {

//	//添加照片
//	public void addImage(Image image);
//	
//	//删除图片
//	public void deleteImage(String hashCode);
//	
	// 更改图片的引用次数
	@Modifying//update或者delete操作，在更新或者删除操作时，此注解必须加
	@Query(value = "update Image set leadConut = :leadConut where hashCode = :hashCode", nativeQuery = true)
	public void updateLeadCount(@Param("leadConut") int leadCount, @Param("hashCode") String hashCode);

	// 更改图片的下载次数
	@Modifying//update或者delete操作，在更新或者删除操作时，此注解必须加
	@Query(value = "update Image set downloadConut = :downloadConut where hashCode = :hashCode", nativeQuery = true)
	public void updateDownloadCount(@Param("downloadConut") int downloadCount, @Param("hashCode") String hashCode);

	//	//根据hash码获取图片信息
//	@Query("select i from Image i where i.hashCode=:hashCode")
//	public Image queryIamge(@Param("hashCode") String hashCode);
}
