package com.icejoywoo.commons;

import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Hello world!
 * 
 */
public class Main {
	public static void main(String[] args) {
		Map testMap = ArrayUtils.toMap(new String[][] { 
				{ "keyA", "中文测试" },
				{ "B", "test" }, 
				{ "C", "testC" } 
				});
		System.out.println("Hello World!");
		System.out.println(testMap);
	}
}
