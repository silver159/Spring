package com.tjoeun.springDI_xml_namespace;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		
		String configLocation = "classpath:applicationCTX.xml";
		String configLocation2 = "classpath:applicationCTX2.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation, configLocation2);
		
		Student student = ctx.getBean("student", Student.class);
		System.out.println(student);
		System.out.println("이름: " + student.getName());
		System.out.println("취미: " + student.getHobbies());
		System.out.println("========================================================");
		
		Student student2 = ctx.getBean("student", Student.class);
		System.out.println(student2);
		System.out.println("이름: " + student2.getName());
		System.out.println("취미: " + student2.getHobbies());
		System.out.println("========================================================");
		
		// student와 student2는 같은 bean을 저장하고 있으므로 비교하면 같다.
		System.out.println(student == student2 ? "같다." : "다르다."); // 같다.
		System.out.println(student.equals(student2) ? "같다." : "다르다."); // 같다.
		System.out.println("========================================================");
		
		Student student3 = ctx.getBean("student3", Student.class);
		System.out.println(student3);
		System.out.println("이름: " + student3.getName());
		System.out.println("취미: " + student3.getHobbies());
		System.out.println("========================================================");
		
		// student와 student3는 다른 bean을 저장하고 있으므로 비교하면 다르다.
		// hashCode() and equals() @Override하면 student.equals(student3)가 다르다 => 같다.
		System.out.println(student == student3 ? "같다." : "다르다."); // 다르다.
		System.out.println(student.equals(student3) ? "같다." : "다르다."); // 다르다 => 같다.
		System.out.println("========================================================");
		
		Student student4 = ctx.getBean("student4", Student.class);
		System.out.println(student4);
		System.out.println("이름: " + student4.getName());
		System.out.println("취미: " + student4.getHobbies());
		System.out.println("========================================================");
		
		// student와 student3는 다른 bean을 저장하고 있으므로 비교하면 다르다.
		System.out.println(student.equals(student4) ? "같다." : "다르다."); // 다르다 => 같다.
		System.out.println("========================================================");
		
		StudentInfo studentInfo = ctx.getBean("studentInfo", StudentInfo.class);
		studentInfo.getStudentInfo();
		System.out.println("========================================================");
		
		StudentInfo studentInfo2 = ctx.getBean("studentInfo2", StudentInfo.class);
		studentInfo2.getStudentInfo();
		System.out.println("========================================================");
		
		Student student5 = ctx.getBean("student5", Student.class);
		System.out.println(student5);
		System.out.println("이름: " + student5.getName());
		System.out.println("취미: " + student5.getHobbies());
		System.out.println("========================================================");
		
		Family family = ctx.getBean("family", Family.class);
		System.out.println(family);
		System.out.println("아빠 이름: " + family.getPapaName());
		System.out.println("엄마 이름: " + family.getMamiName());
		System.out.println("누나 이름: " + family.getSisterName());
		System.out.println("동생 이름: " + family.getBrotherName());
		
	}

} 
