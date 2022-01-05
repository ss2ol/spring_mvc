package kr.co.js;

import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.js.domain.EmailReport;
import kr.co.js.domain.Item;
import kr.co.js.domain.Member;
import kr.co.js.domain.User;
import kr.co.js.service.ItemService;
import kr.co.js.validator.MemberValidator;

//빈이 자동생성
@Controller
public class HomeController {
	
	@Autowired
	private ItemService itemservice;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	//  /로 요청이 GET방식으로 오면 호출되는 메서드
	@RequestMapping(value = "/", method = RequestMethod.GET)
	//문자열을 리턴하면 이 이름리 뷰 이름이 되고 기본적으로 포워딩 됨
	//요청이 오면 home 으로 포워딩
	//home으로 포워딩 할때 ViewResolver 설정에 의해 WEB-INF.viwe/home.jsp파일로 포워딩
	
	public String home( Model model,HttpServletRequest request, HttpServletResponse response ) {
		
		model.addAttribute("list",itemservice.getList(request,response));
		return "home";
	}
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {
		return "hello";
	}
	
	@RequestMapping(value = "/detail/{num}", method = RequestMethod.GET)
	public void detail(@PathVariable int num) {
		System.out.println(num);
	}
	
	@RequestMapping(value = "/parameter", method = RequestMethod.GET)
	public String parameter() {
		return "parameter";
	}
	
	/*
	@RequestMapping(value = "/parameter", method = RequestMethod.POST)
	public String parameter(HttpServletRequest request) {
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		String job = request.getParameter("job");
		
		System.out.println(name);
		System.out.println(age);
		System.out.println(gender);
		System.out.println(job);
		
		
		return "hello";
	}
	
	*/
	/*
	//@Requestparam 이용하는 방법
	@RequestMapping(value = "/parameter", method = RequestMethod.POST)
	public String parameter(@RequestParam("name")String name,
			@RequestParam("age")String age,
			@RequestParam("gender")String gender,
			@RequestParam("job")String job) {
		
		System.out.println(name);
		System.out.println(age);
		System.out.println(gender);
		System.out.println(job);
		
		
		return "hello";
	}
	*/
	
	//Command 이용하는 방법
	@RequestMapping(value = "/parameter", method = RequestMethod.POST)
	public String parameter(User user) {
		System.out.println(user);
		return "hello";
	}
	
	@ModelAttribute("map")
	public Map<String, Object> allpass(){
		Map<String, Object> map = new HashMap<>();
		map.put("parameter", "클라이언트가 서버에게 전달하는 데이터");
		map.put("attribute", "서버가 클라이언트에게 전달하는 데이터");
		return map;
	}
	
	@RequestMapping(value = "/forward", method = RequestMethod.GET)
	public String forward(Model model) {
		model.addAttribute("data", "forwarding 할때 데이터 전달");
		
		return "result";
	}
	
	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
	public String redirect(Model model, HttpSession session, RedirectAttributes rattr) {
		//model.addAttribute("data", "redirecting 할때 데이터 전달");
		
		//이경우 session을 초기화하거나 data를 삭제하지 않는 한 계속 유지
		session.setAttribute("data", "value");
		
		//이경우는 세션에 저장했다가 한번 redirect하고 나면 자동 소멸
		rattr.addFlashAttribute("attr","value");
		
		return "redirect:result";
	}
	/*
	//상세보기 처리를 위한 메서드
	
	@RequestMapping("/detail.html")
	public String detail(@RequestParam("itemid") Integer itemid, Model model){
    //서비스 메서드 호출
	Item item = itemservice.getItem(itemid);
	//데이터를 저장
	model.addAttribute("item", item);
	//출력할 뷰이름 리턴
	return "detail";
	}
	
	*/
	
	@RequestMapping("/detail.html/{itemid}")
	public String detail(@PathVariable("itemid") Integer itemid, Model model){
	//서비스 메서드 호출
	Item item = itemservice.getItem(itemid);
	//데이터를 저장
	model.addAttribute("item", item);
	//출력할 뷰이름 리턴
	return "detail";
	}
	
	//exception 요청이 발생했을때 input.jsp를 출력하도록 해주는 메서드
	/*
	@RequestMapping(value = "/exception", method = RequestMethod.GET)
	//단순 페이지 이동 
	public String exception() {
	return "input";
	}
	*/
	
	//input.jsp의 from에서 submit을 눌렀을때 처리하도록 해주는 메서드
	//dividend와 divisor 파라미터의 값을 읽어서 나누셈을 해서 Model에 저장
	//result.jsp 파일에 그 내용을 출력하도록 하기
	@GetMapping("/cal")
	//파라미터 처리 
	public String cal(@RequestParam("dividend") int dividend , @RequestParam("divisor") int divisor, Model model) {
			model.addAttribute("result", dividend/divisor);
			return "result";
			}
	
	@ExceptionHandler(ArithmeticException.class)
	public String handleException(Exception e, Model model){
	model.addAttribute("content", e.getLocalizedMessage());
	return "error/exception";
	}
	
	
	//메세지 요청이 get방식으로 오면 처리하는 메서드 
	@GetMapping("/message")
	public String message(@ModelAttribute("member") Member member) {
		return "loginform";
	}
	//메세지 요청이 있으면 post방식으로 오묜 처리하는 메서드
	@PostMapping("/message")
	public String message(@Valid @ModelAttribute("member") Member member, BindingResult result) {
		//유효성 검사 수행
		//new MemberValidator().validate(member,result);
		//검사결과에 에러가 있으면
		if(result.hasErrors()) {
			return "loginform";
		}
		//검사결과에 에러가 없으면 시작 요청으로 리다이렉트
		return "redirect:/";
	}
	
	//모든 결과 페이지에 loginType라는  이름의 메서드의 리턴값이 전달 
	@ModelAttribute("loginTypes")
	public List<String> loginTypes(){
		List<String>list = new ArrayList<>();
		list.add("일반회원");
		list.add("VIP회원");
		list.add("비회원");
		return list;
		}
	//파일 업로드의 get 요청을 처리하는 메서드
		@GetMapping("fileupload")
		public String fileupload() {
			return "fileupload";
		}
		
		@PostMapping("fileuploadrequest")
	//파일 업로드를 Request를 이용해 처리 
		public String fileUpload(MultipartHttpServletRequest request) {
			String email = request.getParameter("email");
			MultipartFile report = request.getFile("report");
			
			System.out.println(email);
			System.out.println(report);
			return "redirect:/";
		}
		
		@PostMapping("fileuploadparam")
		//파일 업로드를 @RequestParam를 이용해 처리 
			public String fileUpload(@RequestParam("email") String email,@RequestParam("report") MultipartFile report) {
			
				System.out.println(email);
				System.out.println(report);
				return "redirect:/";
			}
		
		@PostMapping("fileuploadcommand")
		//파일 업로드를 @RequestParam를 이용해 처리 
			public String fileUpload(@ModelAttribute("emailReport") EmailReport emailReport, HttpServletRequest request ) {
			
			//파일을 업로드할 경로 생성
			String uploadPath = request.getServletContext().getRealPath("/upload");
				//
			String filename = emailReport.getReport().getOriginalFilename();
			
			//File filePath = new File(uploadPath +"/" + emailReport.getEmail() + filename);
			
			File filePath = new File(uploadPath +"/" + UUID.randomUUID() + filename);
			
			try {
				emailReport.getReport().transferTo(filePath);	
				}catch(Exception e) {
					System.out.println(e.getLocalizedMessage());
				}
				return "redirect:/";
			}
		
		
		
		
		
		
}
