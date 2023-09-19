package com.tjoeun.springDI_xml_constructor;

public class Student {
	
	private String name;
	private int age;
	private int gradeNum;
	private int classNum;
	
	public Student() {
		System.out.println("Student 클래스의 기본생성자로 bean을 만든다.");
	}

	public Student(String name, int age, int gradeNum, int classNum) {
		this.name = name;
		this.age = age;
		this.gradeNum = gradeNum;
		this.classNum = classNum;
		System.out.println("데이터를 전달받아 초기화시키는 생성자 실행");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getGradeNum() {
		return gradeNum;
	}

	public void setGradeNum(int gradeNum) {
		this.gradeNum = gradeNum;
	}

	public int getClassNum() {
		return classNum;
	}

	public void setClassNum(int classNum) {
		this.classNum = classNum;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", gradeNum=" + gradeNum + ", classNum=" + classNum + "]";
	}
}
