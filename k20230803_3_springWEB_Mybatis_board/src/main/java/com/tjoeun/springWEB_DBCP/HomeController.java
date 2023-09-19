package com.tjoeun.springWEB_DBCP;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjoeun.dao.MyBatisDAO;
import com.tjoeun.vo.MvcBoardList;
import com.tjoeun.vo.MvcBoardVO;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
// 여기부터
//	private JdbcTemplate template;
//	
//	@Autowired 
//	public void setTemplate(JdbcTemplate template) {
////		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//		this.template = template;
//		
//		Constant.template = this.template;
//	}
// 여기까지 mybatis로 변환이 완료되면 주석으로 처리한다.
	
// servlet-context.xml 파일에서 생성한 mybatis bean(sqlSession)을 사용하기 위해 SqlSession
// 인터페이스 타입의 객체를 생성한다.
// servlet-context.xml 파일에서 생성한 mybatis bean을 자동으로 SqlSession 인터페이스 타입의 
// 객체에 넣어주도록 하기 위해서 @Autowired 어노테이션을 붙여준다.
	@Autowired
	private SqlSession sqlSession; 
	
	@RequestMapping("/") 
	public String home(HttpServletRequest request, Model model) {
		logger.info("MvcBoard 게시판 실행");
		return "redirect:list";
	}
	
	@RequestMapping("/insert")
	public String insert(HttpServletRequest request, Model model) {
		logger.info("컨트롤러의 insert() 메소드 실행");
		return "insert";
	}
	
	@RequestMapping("/insertOK")
	public String insertOK(HttpServletRequest request, Model model, MvcBoardVO mvcBoardVO) {
		logger.info("컨트롤러의 insertOK() 메소드 실행 - 커맨드 객체 사용");
		
//		model.addAttribute("request", request);
//		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
//		MvcBoardService service = ctx.getBean("insert", InsertService.class);
//		service.execute(model);
//		logger.info("{}", mvcBoardVO);
		
		// mapper로 사용할 인터페이스 객체에 mybatis mapper를 넣어준다.
		MyBatisDAO mapper = sqlSession.getMapper(MyBatisDAO.class);
		
		// 메인글을 저장하는 insert sql 명령을 실행한다.
		mapper.insert(mvcBoardVO);
		
		return "redirect:list"; // @RequestMapping("/list") 호출
	}
	
	@RequestMapping("/list")
	public String list (HttpServletRequest request, Model model) {
		logger.info("컨트롤러의 list() 메소드 실행");
		
//		model.addAttribute("request", request);
//		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
//		MvcBoardService service = ctx.getBean("select", SelectService.class);
//		service.execute(model);
		
//		mapper를 얻어온다.
		MyBatisDAO mapper = sqlSession.getMapper(MyBatisDAO.class);
		
		// 1페이지에 표시한 글의 개수, 브라우저에 표시할 페이지 번호, 전체 글의 개수를 저장한다.
		int pageSize = 10;
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (NumberFormatException e) {}
		int totalCount = mapper.selectCount();
//		logger.info("{}", totalCount);
		
		// 1페이지 분량의 글 목록과 페이지 작업에 사용할 변수로 초기화시킨다.
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		MvcBoardList mvcBoardList = ctx.getBean("mvcBoardList", MvcBoardList.class);
		mvcBoardList.initMvcBoardList(pageSize, totalCount, currentPage);
//		logger.info("{}", mvcBoardList);
		
		// 1페이지 분량의 메인글을 얻어온다.
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		hmap.put("startNo", mvcBoardList.getStartNo());
		hmap.put("endNo", mvcBoardList.getEndNo());
		mvcBoardList.setList(mapper.selectList(hmap));
//		logger.info("{}", mvcBoardList);
		
		// list.jsp로 넘겨줄 데이터를 Model 인터페이스 객체에 넣어준다.
		model.addAttribute("boardList", mvcBoardList);
		
		return "list"; // list.jsp 호출
	}
	
	@RequestMapping("/increment")
	public String increment (HttpServletRequest request, Model model) {
		logger.info("컨트롤러의 increment() 메소드 실행");
//		model.addAttribute("request", request);
//		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
//		MvcBoardService service = ctx.getBean("increment", IncrementService.class);
//		service.execute(model);
		
		// mapper를 얻어온다.
		MyBatisDAO mapper = sqlSession.getMapper(MyBatisDAO.class);
		
		// 조회수를 증가시킬 글 번호를 받는다.
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		// 조회수를 증가시키는 메소드를 실행한다.
		mapper.increment(idx);
		
		// 조회수를 증가시킨 글번호와 작업후 돌아갈 페이지 번호를 medel 인터페이스 객체에
		// 넣어준다.
		model.addAttribute("idx", request.getParameter("idx"));
		model.addAttribute("currentPage", request.getParameter("currentPage"));
		
		return "redirect:contentView";
	}
	
	@RequestMapping("/contentView")
	public String contentView (HttpServletRequest request, Model model) {
		logger.info("컨트롤러의 contentView() 메소드 실행");
		model.addAttribute("request", request);
		
//		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
//		MvcBoardService service = ctx.getBean("contentView", ContentViewService.class);
//		service.execute(model);
		
//		mapper를 얻어온다.
		MyBatisDAO mapper = sqlSession.getMapper(MyBatisDAO.class);	
		
		// 조회수를 증가시킨(화면에 표시할) 글번호를 받는다.
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		// 조회수를 증가시킨 글 1건을 얻어와서 MvcBoardVO 클래스 객체에 저장한다.
		MvcBoardVO mvcBoardVO = mapper.selectByIdx(idx);
		
		// contentView.jsp로 넘겨줄 글 1건, 작업후 돌아갈 페이지 번호, 줄바꿈에 사용할 
		// "\r\n"을 Model 인터페이스 객체에 넣어준다.
		model.addAttribute("vo", mvcBoardVO);
		model.addAttribute("currentPage", request.getParameter("currentPage"));
		model.addAttribute("enter", "\r\n"); // reple.jsp에서 필요한 작업
		
		return "contentView";
	}
	
	@RequestMapping("/update")
	public String update (HttpServletRequest request, Model model, MvcBoardVO mvcBoardVO) {
		logger.info("컨트롤러의 update() 메소드 실행 - 커맨드 객체 사용");
		
//		model.addAttribute("request", request);
//		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
//		MvcBoardService service = ctx.getBean("update", UpdateService.class);		
//		service.execute(model);
		
		// mapper를 얻어온다.
		MyBatisDAO mapper = sqlSession.getMapper(MyBatisDAO.class);	
		
		// 커맨드 객체를 사용해서 넘어오는 데이터를 받아 처리하므로 request로 넘어오는 
		// 데이터를 받아주는 작업을 실행할 필요가 없다.
		// 글을 수정하는 메소드를 실행한다.
		mapper.update(mvcBoardVO);
		
		// 글 수정 작업 후 돌아갈 페이지 번호를 Model 인터페이스 객체에 넣어준다.
		model.addAttribute("currentPage", request.getParameter("currentPage"));
		
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete (HttpServletRequest request, Model model) {
		logger.info("컨트롤러의 delete() 메소드 실행");
		
//		model.addAttribute("request", request);
//		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
//		MvcBoardService service = ctx.getBean("delete", DeleteService.class);		
//		service.execute(model);
		
		// mapper를 얻어온다.
		MyBatisDAO mapper = sqlSession.getMapper(MyBatisDAO.class);
		
		// 삭제할 글 번호를 받는다.
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		// 글을 삭제하는 메소드를 실행한다.
		mapper.delete(idx);
		
		// 글 삭제 작업 후 돌아갈 페이지 번호를 Model 인터페이스 객체에 넣어준다.
		model.addAttribute("currentPage", request.getParameter("currentPage"));
		
		return "redirect:list";
	}
	
	@RequestMapping("/reply")
	public String reply (HttpServletRequest request, Model model) {
		logger.info("컨트롤러의 reply() 메소드 실행");
		
//		model.addAttribute("request", request);
//		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
//		MvcBoardService service = ctx.getBean("contentView", ContentViewService.class);	
//		service.execute(model);
		
		// mapper를 얻어온다.
		MyBatisDAO mapper = sqlSession.getMapper(MyBatisDAO.class);	
		
		// 답글을 입력할 글번호를 받는다.
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		// 답글을 입력할 글을 얻어와서 MvcBoardVO 클래스 객체에 넣어준다.
		MvcBoardVO mvcBoardVO = mapper.selectByIdx(idx);
		
		// 답글을 입력할 글, 작업후 돌아갈 페이지 번호, 줄바꿈에 사용할 "\r\n"을 
		// Model 인터페이스 객체에 넣어준다.
		model.addAttribute("vo", mvcBoardVO);
		model.addAttribute("currentPage", request.getParameter("currentPage"));
		model.addAttribute("enter", "\r\n"); // reple.jsp에서 필요한 작업
		
		return "reply";
	}
	
	@RequestMapping("/replyInsert")
	public String replyInsert(HttpServletRequest request, Model model, MvcBoardVO mvcBoardVO) {
		logger.info("컨트롤러의 replyInsert() 메소드 실행");
		
//		model.addAttribute("request", request);
//		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
//		MvcBoardService service = ctx.getBean("reple",RepleService.class);
//		service.execute(model);
		
//		mapper를 얻어온다.
		MyBatisDAO mapper = sqlSession.getMapper(MyBatisDAO.class);
		logger.info("{}", mvcBoardVO);
			
		// 커맨드 객체를 사용해서 답글 내용을 받았고 답글은 질문글 바로 아래에 위치해야 
		// 하므로 lev와 seq를 1씩 증가시켜서 넣어준다.
		mvcBoardVO.setLev(mvcBoardVO.getLev() + 1);
		mvcBoardVO.setSeq(mvcBoardVO.getSeq() + 1);
		
		// 답글이 삽입될 위치를 정하기 위해 조건에 만족하는 seq를 1씩 증가시키는 메소드를
		// 실행한다.
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		hmap.put("gup", mvcBoardVO.getGup());
		hmap.put("seq", mvcBoardVO.getSeq());
		mapper.replyIncrement(hmap);
		
		// 답글을 저장하는 메소드를 실행한다.
		mapper.replyInsert(mvcBoardVO);		
		
		// 답글 저장 후 돌아갈 페이지 번호를 Model 인터페이스 객체에 넣어준다.
		model.addAttribute("currentPage", request.getParameter("currentPage"));
		
		return "redirect:list";
	}
	
}
