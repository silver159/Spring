package com.tjoeun.springWEB_Transaction;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tjoeun.dao.TransactionDAO;
import com.tjoeun.service.TransactionService;
import com.tjoeun.vo.CardVO;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
/*	
// TicketInsert 클래스 객체를 사용해서 TransactionDAO 클래스에 접근하므로 주석으로 처리한다. 
 
	private TransactionDAO dao;
	
	@Autowired
	public void setDao(TransactionDAO dao) {
		this.dao = dao;
	}
*/	
	
// TransactionService 인터페이스 객체를 선언하고 setter 메소드를 만든다.
	private TransactionService service;
	
// servlet-context.xml 파일에서 만든 TicketInsert 클래스의 bean이 서버가 실행될 때 자동으로 
// 채워진다.
	@Autowired
	public void setService(TransactionService service) {
		this.service = service;
	}

	@RequestMapping("/")
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping("/ticket")
	public String ticket (HttpServletRequest request, Model model) {
		logger.info("컨트롤러의 ticket() 메소드 실행");
		
		return "ticket";
	}
	/*
	// 기본
	@RequestMapping("/ticketCard")
	public String ticketCard (HttpServletRequest request, Model model) {
		logger.info("컨트롤러의 ticketCard() 메소드 실행");
		logger.info("consumerId: {}",request.getParameter("consumerId"));
		logger.info("amount: {}", request.getParameter("amount"));
		
		model.addAttribute("consumerId", request.getParameter("consumerId"));
		model.addAttribute("amount", request.getParameter("amount"));
		
		return "ticketEnd";
	}
	*/ 
	
	// 커맨드 객체 사용
	@RequestMapping("/ticketCard")
	public String ticketCard (HttpServletRequest request, Model model, CardVO cardVO) {
		logger.info("컨트롤러의 ticketCard() 메소드 실행");
//		logger.info("{}", cardVO);
//		model.addAttribute("ticketInfo", cardVO);
		
//		dao.buyTicket(cardVO);
		
		// dao의 buyTicket() 메소드(내부 트랜잭션)를 바로 실행하지 않고 TicketInsert 클래스에서
		// 외부 트랜잭션에서 실행한다.
		service.execute(cardVO);
		
		return "ticketEnd";
	}
	
}
