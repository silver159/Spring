package com.tjoeun.springDI_xml_setter;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		
		/*
		// 자바에서 사용하던 방식
		MyCalculator myCalculator = new MyCalculator();
		myCalculator.setFirstNum(8); // 초기화
		myCalculator.setSecondNum(5);
		
		// myCalculator.add(); // NullPointerException, myCalculator의 calculator가 null
		
		// Calculator calculator = new Calculator();
		// myCalculator.setCalculator(calculator);
		myCalculator.setCalculator(new Calculator());
		*/
		
		// xml 파일에서 설정한 bean 정보를 읽어 들인다.
		// class의 bean을 설정하는 xml 파일 만들기: src/main/resources에서 우클릭 =>
		// New => Spring Bean Configuration File => xml 파일이름 입력 => Finish
		
		// xml 파일에서 설정한 bean을 얻어온다.
		// applicationCTX.xml에 위치한 경로 src/main/resources를 classpath 라고 부른다.
		String configLocation = "classpath:applicationCTX.xml";
		
		// GenericXmlApplicationContext 클래스 생성자의 인수로 bean을 설정하는 xml 
		// 파일의 경로와 이름을 넘겨주면 xml 파일의 내용을 읽어서 java 객체로 변환한
		// 후 부모 클래스인 AbstractApplicationContext 클래스 객체에 저장한다. 
		// upcasting
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation); 
		
		// getBean() 메소드로 AbstractApplicationContext 클래스 타입의 객체 ctx에
		// 저장된 bean을 얻어온다.
		// getBean("얻어올 bean의 id", bean을 생성한 클래스 이름.class)
		// MyCalculator myCalculator = new MyCalculator();와 동일한 작업
		MyCalculator myCalculator = ctx.getBean("myCalculator", MyCalculator.class);
		System.out.println(myCalculator);
						
		myCalculator.add();
		myCalculator.sub();
		myCalculator.mul();
		myCalculator.div();
	}

}
