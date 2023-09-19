package com.tjoeun.springDI_java_in_xml;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

// java 파일에서 xml 파일의 bean 설정 정보를 읽어오려면 @ImportResource 어노테이션으로
// 읽어들일 xml 파일을 java 파일에 포함시키면 된다.
@ImportResource("classpath:applicationCTX.xml")

@Configuration // 이 클래스는 DI 설정에 사용되는 클래스임을 spring에게 알려준다.
public class ApplicationConfig {
	
	@Bean
	public Student student2() {
		
		ArrayList<String> hobbies = new ArrayList<String>();
		hobbies.add("등산");
		hobbies.add("바둑");
		hobbies.add("낚시");
		Student student = new Student("임꺽정", 35, hobbies);
		student.setHeigth(178);
		student.setWeight(78);
		
		return student; // 초기화된 bean 객체를 리턴시킨다.
	}
}
