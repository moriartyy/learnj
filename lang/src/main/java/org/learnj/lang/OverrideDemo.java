package org.learnj.lang;

import java.io.IOException;
/*
 * 
 * 使用override可以重新定接口的返回值，但必须继承自原返回值，也可以替换抛出异常类型，或者不再抛出异常。
 * 
 */
public class OverrideDemo {
	
	class Result {
		
	}
	
	class ComplexResult extends Result {
		
	}
	
	interface A {
		Result foo(String p1) throws IOException;
	}
	
	interface B extends A {
		
		@Override
		ComplexResult foo(String p1);
	}

}
