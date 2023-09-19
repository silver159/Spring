package com.tjoeun.springDI_xml_interface;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	
	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		
		Pencil pencil = ctx.getBean("pencil4B", Pencil4B.class);
		pencil.use(); // 다형성
		
		pencil = ctx.getBean("pencilHB", PencilHB.class);
		pencil.use(); // 다형성
		
		pencil = ctx.getBean("pencilHBErager", PencilHBEraser.class);
		pencil.use(); // 다형성
		
	}
}
