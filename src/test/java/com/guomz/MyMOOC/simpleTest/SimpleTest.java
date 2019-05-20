package com.guomz.MyMOOC.simpleTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import com.guomz.MyMOOC.BaseTest;

public class SimpleTest extends BaseTest{

	@Value("${limit.money}")
	private int money;
	
	@Test
	public void testValue()
	{
		System.out.println(money);
	}
}
