package com.tjoeun.vo;

import java.util.Date;

//VO(Value Object) 클래스 => 데이터 1건과 데이터를 처리하기 위한 메소드를 기억하는 클래스
//데이터를 저장해서 전송한다고 해서 DTO(Data Transfer Object)라고 부르기도 한다.
//bean => 자바로 처리하는 최소한의 작업 단위로 데이터를 기억하는 필드 (멤버 변수)와 필드에
//저장된 데이터를 입출력하는 getters & setters 메소드로만 구성된 클래스

public class MvcBoardVO {
	
	// 필드 선언
	private int idx;
	private String name;
	private String subject; // 메인글 제목
	private String content; // 메인글 내용
	private int gup;
	private int lev;
	private int seq;
	private int hit; 
	private Date writeDate;
	
	// getters & setters
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getGup() {
		return gup;
	}
	public void setGup(int gup) {
		this.gup = gup;
	}
	public int getLev() {
		return lev;
	}
	public void setLev(int lev) {
		this.lev = lev;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	
	// toString() 메소드 Override
	@Override
	public String toString() {
		return "mvcBoardVO [idx=" + idx + ", name=" + name + ", subject=" + subject + ", content=" + content + ", gup="
				+ gup + ", lev=" + lev + ", seq=" + seq + ", hit=" + hit + ", writeDate=" + writeDate + "]";
	}
	
}
