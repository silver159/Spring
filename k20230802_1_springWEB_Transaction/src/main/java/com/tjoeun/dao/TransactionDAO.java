package com.tjoeun.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.tjoeun.vo.CardVO;

public class TransactionDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(TransactionDAO.class);
	
// JdbcTemplate 객체 선언
	private JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public void buyTicket(final CardVO cardVO) {
		logger.info("TransactionDAO 클래스의 buyTicket() 메소드 실행");
//		logger.info("{}", cardVO);
		
		String sql = "insert into card (consumerid, amount) values (?, ?)";
		template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, cardVO.getConsumerId());
				pstmt.setString(2, cardVO.getAmount());
			}
		});
		
		template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String sql = "insert into ticket (consumerid, countnum) values (?, ?)";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, cardVO.getConsumerId());
				pstmt.setString(2, cardVO.getAmount());
				return pstmt;
			}
		});
	}
}
