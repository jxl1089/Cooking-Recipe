package com.myspring.cookpro.member.controller;

import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myspring.cookpro.member.dto.MemberDTO;
import com.myspring.cookpro.member.service.MemberService;

@Controller
public class MemberControllerImpl implements MemberController{
	@Autowired
	private MemberService memberService;

	private int randomNum;

	/* 메인 페이지 */
	@RequestMapping("/")
	public String main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "main";
	}

	/* Form.jsp 실행 */
	@RequestMapping("/member/*Form.do")
	public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");

		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}

	/* 아이디 중복 체크 */
	@ResponseBody
	@RequestMapping(value="/member/check.do", method = RequestMethod.POST)
	public int checkId(@RequestParam("id") String id,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		int result = memberService.checkById(id);

		return result;
	}

	/* 이메일 인증번호 전송 */
	@RequestMapping(value="/member/mail.do", method=RequestMethod.POST)
	public void sendMail(@RequestParam("email") String email,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Random r = new Random();
		randomNum = r.nextInt(888888) + 111111;

		String msg;
		msg = "안녕하세요. 인증번호는 ";
		msg += randomNum;
		msg += " 입니다.";

		memberService.sendMail(email, "[CookPro] 인증번호", msg);
	}

	/* 이메일 인증번호 확인 */
	@ResponseBody
	@RequestMapping(value="/member/auth.do", method=RequestMethod.POST)
	public String checkAuth(@RequestParam("authNo") int authNo,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(authNo == randomNum) {
			return "Y";
		} else {
			return "N";
		}
	}

	/* 회원가입 */
	@RequestMapping(value = "/member/addMember.do", method = RequestMethod.POST)
	public void addMember(@ModelAttribute("member") MemberDTO member,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		int result = memberService.addMember(member);

		out.print("<script>");
		if(result == 1) {
			out.print("alert('회원가입에 성공하였습니다. 환영합니다!');");
			out.print("location.href='"+request.getContextPath()+"/'");
		} else {
			out.print("alert('회원가입에 실패하였습니다. 다시 시도해주세요.');");
			out.print("location.href='"+request.getContextPath()+"/member/memberForm.do'");
		}
		out.print("</script>");
		out.close();
	}

	/* 로그인 */
	@RequestMapping(value = "/member/login.do", method = RequestMethod.POST)
	public ModelAndView login(MemberDTO member, RedirectAttributes rAttr, 
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		member = memberService.login(member);

		ModelAndView mav = new ModelAndView();

		if(member != null) {
			HttpSession session = request.getSession();
			session.setAttribute("member", member);
			session.setAttribute("isLogOn", true);

			rAttr.addAttribute("msg", "login");

			String action = (String) session.getAttribute("action");
			session.removeAttribute("action");

			if(action != null) {
				mav.setViewName("redirect:"+action);
			} else {
				mav.setViewName("redirect:/");
			}

		} else {
			rAttr.addAttribute("result", "loginFailed");
			mav.setViewName("redirect:/member/loginForm.do");
		}

		return mav;
	}

	/* 로그아웃 */
	@RequestMapping(value = "/member/logout.do", method = RequestMethod.GET)
	public ModelAndView logout(RedirectAttributes rAttr, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession(false);

		Boolean isLogOn = (Boolean) session.getAttribute("isLogOn");

		ModelAndView mav = new ModelAndView();

		if(session != null && isLogOn != null) {
			session.invalidate();
			rAttr.addAttribute("result", "logout");
		} else {
			rAttr.addAttribute("result", "notLogin");
		}

		mav.setViewName("redirect:/member/loginForm.do");
		return mav;
	}
	
	/* 회원 정보 수정 */
	@RequestMapping(value="/member/modMember.do", method=RequestMethod.POST)
	public void modMember(@ModelAttribute("member") MemberDTO member, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int result = memberService.modMember(member);
		
		out.print("<script>");
		if(result == 1) {
			out.print("alert('정보 수정이 완료되었습니다');");
			out.print("location.href='"+request.getContextPath()+"/member/myPageForm.do'");
		} else {
			out.print("alert('정보 수정에 실패했습니다. 다시 시도해주세요.');");
			out.print("location.href='"+request.getContextPath()+"/member/modForm.do'");
		}
		out.print("</script>");
		out.close();
	}



	/* 회원 탈퇴 */
	@RequestMapping(value = "/member/delMember.do", method = RequestMethod.GET)
	public void removeMember(@RequestParam("id") String id, RedirectAttributes rAttr,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		int result = memberService.delMember(id);
		
		out.print("<script>");
		if(result == 1) {
			out.print("alert('회원 탈퇴가 완료되었습니다. 이용해주셔서 감사드립니다.');");
			out.print("location.href='"+request.getContextPath()+"/'");
		} else {
			out.print("alert('탈퇴에 실패했습니다. 다시 시도해주세요.');");
			out.print("location.href='"+request.getContextPath()+"/member/myPageForm.do'");
		}
		out.print("</script>");
		out.close();
		
		HttpSession session = request.getSession(false);
		Boolean isLogOn = (Boolean) session.getAttribute("isLogOn");
		
		if(session != null && isLogOn != null) {
			session.invalidate();
			rAttr.addAttribute("result", "logout");
		} else {
			rAttr.addAttribute("result", "notLogin");
		}
	}
	
	/* 아이디 찾기 */
	@ResponseBody
	@RequestMapping(value="/member/searchId.do", method = RequestMethod.POST)
	public String searchId(@ModelAttribute("member") MemberDTO member,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String result = memberService.searchId(member);
		
		return result;
	}
	
	/* 비밀번호 찾기 */
	@ResponseBody
	@RequestMapping(value="/member/searchPwd.do", method = RequestMethod.POST)
	public String searchPwd(@ModelAttribute("member") MemberDTO member,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String result = memberService.searchPwd(member);

		return result;
	}
	
}
	
	










//	/* 게시글 수정 페이지 실행 */
//	@RequestMapping(value="/board/modArticle.do")
//	public ModelAndView modArticle(@RequestParam("articleNO") int articleNO, HttpServletRequest request,
//		HttpServletResponse response) throws Exception {
//	String viewName = (String) request.getAttribute("viewName");
//
//	ModelAndView mav = new ModelAndView();
//	mav.addObject("article", boardService.viewArticle(articleNO));
//	mav.setViewName(viewName);
//
//	return mav;

//	}

//	}

