package com.example.dao;


import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class InitDB extends BaseTestCaseJunit4{

	@Test
	@Rollback(false)
	public void testInit() {
		//不用写东西，启动hibernate时会自动检查数据库，如果缺少表，则自动建表；如果表里缺少列，则自动添加列
	}

}
