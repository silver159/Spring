package com.tjoeun.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.tjoeun.vo.CardVO;

public class TransactionDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(TransactionDAO.class);
	
// JdbcTemplate 객체 선언
	private JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
// 내부 트랜잭션	

// 트랜잭션 템플릿을 사용하기 위해 TransactionTemplate 클래스 객체를 선언한다.
	private TransactionTemplate transactionTemplate;
	
// 트랜잭션 처리를 위해 TransactionTemplate 클래스 객체를 초기화 하기 위해 setter 메소드를 
// 선언하고 servlet-context.xml 파일에서 초기화 시킨다.
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	public void buyTicket(final CardVO cardVO) {
		logger.info("내부 트랜잭션 실행");
		
		// 트랜잭션 템플릿 코딩		
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					
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
			});
			logger.info("트랜잭션이 정상적으로 처리되었으므로 commit 합니다.");
		} catch (Exception e) {
//			e.printStackTrace();
			logger.info("트랜잭션이 정상적으로 처리되지 않았으므로 rollback 합니다.");
		}
	}
}
