package com.tjoeun.springWEB_redirect;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping("/confirm")
	public String confirm(HttpServletRequest request, Model model) {
		logger.info("컨트롤러의 confirm() 메소드 실행");
		String id =  request.getParameter("id");
		logger.info("{}",id);
		model.addAttribute("id", id);
		
		// id가 "abc"면 idOK.jsp를 브라우저에 표시하고 abc가 아니면 idNG.jsp를 
		// 브라우저에 표시한다.
		// return id.equals("abc")?"idOK":"idNG"; // 뷰 페이지 이름
		// 아래과 같이 "redirect:" 붙여서 리턴시키면 "/WEB-INF/views/" 폴더의
		// confirmOK.jsp를 호출하는 것이 아니라 컨트롤러의 @RequestMapping("/confirmOK")
		// 어노테이션이 붙어있는 메소드를 호출한다.
		return id.equals("abc")?"redirect:confirmOK":"redirect:confirmNG";
	}
	
	@RequestMapping("/confirmOK")
	public String confirmOK(HttpServletRequest request, Model model) {
		logger.info("컨트롤러의 confirmOK() 메소드 실행");
		return "idOK";
	}
	
	@RequestMapping("/confirmNG")
	public String confirmNG(HttpServletRequest request, Model model) {
		logger.info("컨트롤러의 confirmNG() 메소드 실행");
		return "idNG";
	}
	
}
