package com.tjoeun.springDI_java;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass {
	
	public static void main(String[] args) {
		
		/*
		// xml 파일에서 설정한 bean 설정 정보를 읽어오려면 아래의 방법을 사용한다.
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		
		// getBean("id", bean을 생성한 클래스 이름.class);
		
		Student student = ctx.getBean("student", Student.class);
		System.out.println(student);
		*/
		
		// java 파일에서 설정한 bean 설정 정보를 읽어오려면 아래의 방법을 사용한다.
		// AnnotationConfigApplicationContext(bean을 설정한 클래스 이름.class)
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(ApplicationConfig.class);
		
		// getBean("메소드이름", 메소드의 리턴타입.class);
		Student student = ctx.getBean("student", Student.class);
		System.out.println(student);
	}
} 
