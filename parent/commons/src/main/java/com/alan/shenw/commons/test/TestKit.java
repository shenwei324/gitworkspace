package com.alan.shenw.commons.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.AssertionFailedError;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.alan.shenw.commons.test.annotation.CheckMethodWithoutTest;

public class TestKit extends Assert {

	@Test
	public void checkAnnotation() {
			Class<?> testClass = getClass();
			// check method without test
			CheckMethodWithoutTest anno = testClass.getAnnotation(CheckMethodWithoutTest.class);
			if (anno != null) {
				Class<?> src = anno.src();
				checkMethodsWithoutTest(src, this.getClass());
			}
	}
	
	public void checkMethodsWithoutTest(Class<?> src, Class<?> test) {
		Method[] methods = src.getDeclaredMethods();
		List<String> methodList = new ArrayList<String>();
		for (Method method : methods) {
			if (Modifier.isPublic(method.getModifiers())
					&& !method.getName().startsWith("get")
					&& !method.getName().startsWith("is")
					&& !method.getName().startsWith("set")) {
				methodList.add("test" + StringUtils.capitalize(method.getName()));
			}
		}
		
		Method[] testMethods = test.getDeclaredMethods();
		List<String> testMethodList = new ArrayList<String>();
		for (Method method : testMethods) {
			Annotation testAnnotation = method.getAnnotation(Test.class);
			if (testAnnotation != null && Modifier.isPublic(method.getModifiers())) {
				testMethodList.add(method.getName());
			}
		}
		
		for (String name : methodList) {
			int index = -1;
			for (int i = 0; i < testMethodList.size(); i++) {
				String testName = testMethodList.get(i);
				if (testName.startsWith(name)) {
					index = i;
					break;
				}
			}
			if (index != -1) {
				testMethodList.remove(index);
			} else {
				String methodName = StringUtils.uncapitalize(name.substring(4));
				throw new AssertionFailedError("Test method not found for method [" + methodName + "].");
			}
		}
	}
}
