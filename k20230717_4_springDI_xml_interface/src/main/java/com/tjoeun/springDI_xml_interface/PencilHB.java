package com.tjoeun.springDI_xml_interface;

public class PencilHB implements Pencil {

	@Override
	public void use() {
		
		System.out.println("HB 연필로 글을 씁니다.");
		
	}
}
