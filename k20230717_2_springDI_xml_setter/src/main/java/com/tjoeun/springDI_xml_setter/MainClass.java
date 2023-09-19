package com.tjoeun.springDI_xml_setter;

import java.util.ArrayList;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) 
	{
		/*
		MyInfo myInfo = new MyInfo();
		myInfo.setName("홍길동");
		myInfo.setHeight(170);
		myInfo.setWeight(120);
		ArrayList<String> hobbies = new ArrayList<String>();
		hobbies.add("등산");
		hobbies.add("바둑");
		hobbies.add("낚시");
		myInfo.setHobbies(hobbies);
		BMICalculator bmiCalculator = new BMICalculator();
		myInfo.setBmiCalculator(bmiCalculator);
		*/
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		MyInfo myInfo = ctx.getBean("myInfo", MyInfo.class);
				
		myInfo.getMyInfo();
	}

}
