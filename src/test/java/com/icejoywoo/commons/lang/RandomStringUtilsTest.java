package com.icejoywoo.commons.lang;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;

public class RandomStringUtilsTest {
	
	@Test
	public void testRandom() {
		System.out.println(RandomStringUtils.random(10));
		System.out.println(RandomStringUtils.randomAlphabetic(10));
		System.out.println(RandomStringUtils.randomNumeric(10));
	}

}
