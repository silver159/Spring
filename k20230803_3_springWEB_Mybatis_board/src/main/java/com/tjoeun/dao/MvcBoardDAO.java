package com.tjoeun.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.tjoeun.springWEB_DBCP.Constant;
import com.tjoeun.vo.MvcBoardVO;

public class MvcBoardDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(MvcBoardDAO.class);
	
// JdbcTemplate 설정	
// DAO 클래스에서 JdbcTemplate을 사용하기 위해 JdbcTemplate 클래스 타입의 객체를 선언한다.
	private JdbcTemplate template;
	
// DAO 클래스의 bean이 기본 생성자로 생성되는 순간 servlet-context.xml 파일에서 생성되어
// 컨트롤러가 전달받아 Constant 클래스의 JdbcTemplate 클래스 타입의 static 객체에 저장한
// bean으로 초기화 하긴 뭘 해 초기화 한다고 새것이 될 것 같아?ㅠ
	public MvcBoardDAO() {
		template = Constant.template;
	}
	
// InsertService 클래스에서 호출되는 테이블에 저장할 객체를 넘겨받고 메인글을 저장하는 
// insert sql 명령을 실행하는 메소드
	public void insert(final MvcBoardVO mvcBoardVO) {
		logger.info("MvcBoardDAO 클래스의 insert() 메소드 실행");
		
		String sql = "insert into MVCBOARD (idx, name, subject, content, gup, lev, seq ) "+
		 "values (MVCBOARD_idx_seq.nextval, ?, ?, ?, MVCBOARD_idx_seq.currval, 0 ,0 )";
		
		template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, mvcBoardVO.getName());
				pstmt.setString(2, mvcBoardVO.getSubject());
				pstmt.setString(3, mvcBoardVO.getContent());
			}
		});
	}
	
// SelectService 클래스에서 호출되는 테이블에 저장된 전체 글의 개수를 얻어오는
// select sql 명령을 실행하는 메소드
	public int selectCount() {
		logger.info("MvcBoardDAO 클래스의 selectCount() 메소드 실행");
		String sql = "SELECT count(*) FROM MVCBOARD";
		return template.queryForObject(sql, Integer.class);
	}
	
// SelectService 클래스에서 호출되는 브라우저에 표시할 1페이지 분량의 글 목록을 
// 얻어오기 위해서 시작 인덱스, 끝 인덱스가 저장된 HashMap 객체를 넘겨받고 1페이지
// 분량의 글 목록을 얻어오는 select sql 명령을 실행하는 메소드
	public ArrayList<MvcBoardVO> selectList(HashMap<String, Integer> hmap) {
		logger.info("MvcBoardDAO 클래스의 selectList() 메소드 실행");
		String sql ="select * from ("+
					" 	select rownum rnum, AA.* from ("+
					" 		select * from mvcboard order by gup desc, seq" +
					"	) AA where rownum <= " + hmap.get("endNo")+
					") where rnum >= " + hmap.get("startNo");
		return (ArrayList<MvcBoardVO>) template.query(sql, new BeanPropertyRowMapper(MvcBoardVO.class));
		
	}
	
// InsertService 클래스에서 호출되는 조회수를 증가시킬 글번호를 넘겨받고 조회수를
// 증가시키는 update sql 명령을 실행하는 메소드
	public void increment(final int idx) {
		logger.info("MvcBoardDAO 클래스의 increment() 메소드 실행");
		String sql = "update mvcboard set hit = hit + 1 where idx = ?";
		template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, idx);
			}
		});
	}
	
// ContentViewService 클래스에서 호출되는 조회수를 증가시킨 글번호를 넘겨받고 
// 조회수를 증가시킨 글 1건을 얻어오는 select sql 명령을 실행하는 메소드
	public MvcBoardVO selectByIdx(int idx) {
		logger.info("MvcBoardDAO 클래스의 selectByIdx() 메소드 실행");
		String sql = "SELECT * FROM MVCBOARD where idx = " + idx;
		return template.queryForObject(sql, new BeanPropertyRowMapper(MvcBoardVO.class));
	}
	
// UpdateService 클래스에서 호출되는 수정할 글번호와 데이터(제목, 내용)를 넘겨받고 글 1건을
// 수정하는 update sql 명령을 실행하는 메소드 ①
	public void update(final int idx, final String subject, final String content) {
		logger.info("MvcBoardDAO 클래스의 update() 메소드 실행");
		String sql = "update mvcboard set subject = ?, content = ? where idx = ?";
		template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, subject);
				pstmt.setString(2, content);
				pstmt.setInt(3, idx);
			}
		});
	}
	
// UpdateService 클래스에서 호출되는 수정할 글번호와 데이터(제목, 내용)를 넘겨받고 글 1건을
// 수정하는 update sql 명령을 실행하는 메소드 ②
	public void update(final MvcBoardVO mvcBoardVO) {
		logger.info("MvcBoardDAO 클래스의 update() 메소드 실행");
		String sql = "update mvcboard set subject = ?, content = ? where idx = ?";
		template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, mvcBoardVO.getSubject());
				pstmt.setString(2, mvcBoardVO.getContent());
				pstmt.setInt(3, mvcBoardVO.getIdx());
			}
		});
	}
	
// DeleteService 클래스에서 호출되는 삭제할 글번호를 넘겨받고 글 1건을 삭제하는 delete sql  
// 명령을 실행하는 메소드
	public void delete(final int idx) {
		logger.info("MvcBoardDAO 클래스의 delete() 메소드 실행");
		String sql = "delete from mvcboard where idx = ?";
		template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, idx);
			}
		});
	}
	
// RepleService 클래스에서 호출되는 글그룹과 글이 출력되는 순서가 저장된 HashMap 객체를 
// 넘겨받고 조건에 만족하는 레코드의 1씩 증가시키는 update sql 명령을 실행하는 메소드
	public void replyIncrement(final HashMap<String, Integer> hmap) {
		logger.info("MvcBoardDAO 클래스의 replyIncrement() 메소드 실행");
		String sql = "update mvcboard set seq = seq + 1 where gup = ? and seq >= ?";
		template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				
				pstmt.setInt(1, hmap.get("gup"));
				pstmt.setInt(2, hmap.get("seq"));
				
			}
		});
	}
	
// RepleService 클래스에서 호출되는	답글이 저장된 객체를 넘겨받고 답글을 저장하는 insert sql
// 명령을 실행하는 메소드
	public void replyInsert(final MvcBoardVO mvcBoardVO) {
		logger.info("MvcBoardDAO 클래스의 replyIncrement() 메소드 실행");
		String sql = "insert into MVCBOARD (idx, name, subject, content, gup, lev, seq ) "+
				 "values (MVCBOARD_idx_seq.nextval, ?, ?, ?, ?, ? ,?)";
		
		template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				
				pstmt.setString(1, mvcBoardVO.getName());
				pstmt.setString(2, mvcBoardVO.getSubject());
				pstmt.setString(3, mvcBoardVO.getContent());
				pstmt.setInt(4, mvcBoardVO.getGup());
				pstmt.setInt(5, mvcBoardVO.getLev());
				pstmt.setInt(6, mvcBoardVO.getSeq());
				
			}
		});
	}
}
