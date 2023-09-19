package com.tjoeun.springDI_xml_in_java;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// k20230717_5_springDI_xml_namespace 프로젝트의 applicationCTX.xml 파일에서 Student
// 클래스의 bean 설정을 java 클래스로 구현한다.

// java 클래스를 이용해서 bean 설정을 하려면 적당한 이름으로 클래스를 만들고 
// @Configuration 어노테이션을 붙여서 이 클래스가 bean을 설정하는 xml 파일의 <beans>
// 태그 역할을 한다고 알려준다.

@Configuration // 이 클래스는 DI 설정에 사용되는 클래스임을 spring에게 알려준다.
public class ApplicationConfig {
	
// 적당한 이름으로 메소드를 만들고 @Bean 어노테이션을 붙여서 bean을 설정하는 xml 파일의
// <bean> 태그 역할을 한다는 것을 spring에게 알려준다.
	
// <bean id="student" class="com.tjoeun.springDI_xml_in_java.Student"> => xml 파일의 bean 설정
// @Bean
// public 리턴타입[class 속성값 => 클래스 이름만] 메소드이름[id 속성값]()	{
//		생성자나 setter 메소드를 사용해서 bean을 초기화하는 작업을 실행한다.
//		...
//		return bean객체;
//	}	
	
	@Bean
	public Student student2() {
		
		ArrayList<String> hobbies = new ArrayList<String>();
		hobbies.add("등산");
		hobbies.add("바둑");
		hobbies.add("낚시");
		
		// 기본생성자로 생성
//		Student student = new Student();
//		student.setName("홍길동");
//		student.setAge(20);
//		student.setHobbies(hobbies);		
		
		// 생성자이용해서 생성
		Student student = new Student("임꺽정", 35, hobbies);
		student.setHeigth(178);
		student.setWeight(78);
		
		return student; // 초기화된 bean 객체를 리턴시킨다.
	}
}
