package com.tjoeun.springDI_xml_interface;

public class PencilHBEraser implements Pencil {

	@Override
	public void use() {
		
		System.out.println("지우개가 달린 HB 연필로 글을 씁니다.");
		
	}
}
