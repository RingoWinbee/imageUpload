package com.example.dao;

import javax.transaction.Transactional;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.SpringBootDemoApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootDemoApplication.class)
@Transactional//执行delete和update必须加事务处理
//@EnableTransactionManagement(proxyTargetClass = true)
//@EnableJpaRepositories
//@EntityScan
//@DataJpaTest
//加入 AutoConfigureTestDatabase 注解
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BaseTestCaseJunit4 {
//	@Bean
//	PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
//		return new PersistenceExceptionTranslationPostProcessor();
//	}

}
