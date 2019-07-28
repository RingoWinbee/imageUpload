package com.example.dao;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.entity.Image;

//@Repository("ImageDao")
//public class ImageDao implements InterfaceImageDao{
//	
//	//这是之前的Hibernate的获取sessionFactory的方法,现在行不通了
////	@Resource
////	private SessionFactory sessionFactory;
//	
//    @Autowired
//    private EntityManagerFactory entityManagerFactory;
//
//    //springboot里获取sessionFactory的方法
//    public Session getSession() {
//        return entityManagerFactory.unwrap(SessionFactory.class).openSession();
//    }
//    
//	@Override
//	public void addImage(Image image) {
//		// TODO Auto-generated method stub
//		this.getSession().save(image);
//	}
//
//	@Override
//	public void deleteImage(String hashCode) {
//		// TODO Auto-generated method stub
//		Image i_delete=(Image)this.getSession().get(Image.class, hashCode);
//		this.getSession().delete(i_delete);
//	}
//
//	@Override
//	public void updateImage(Image image) {
//		// TODO Auto-generated method stub
//		this.getSession().merge(image);
//	}
//
//	@Override
//	public Image queryIamge(String hashCode) {
//		// TODO Auto-generated method stub
//		//HQL语句也可以写成from Image u where hashCode =?
//				return (Image) this.getSession().createQuery("from Image where hashCode =?")
//						.setParameter(0, hashCode).uniqueResult();
//	}
//
//}
