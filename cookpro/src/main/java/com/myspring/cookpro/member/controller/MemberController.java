package com.myspring.cookpro.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myspring.cookpro.member.dto.MemberDTO;

public interface MemberController {
	public String main(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public int checkId(@RequestParam("id") String id,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	public void sendMail(@RequestParam("email") String email,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	public String checkAuth(@RequestParam("authNo") int authNo,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	public void addMember(@ModelAttribute("member") MemberDTO member,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView login(MemberDTO member, RedirectAttributes rAttr, 
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView logout(RedirectAttributes rAttr, 
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	public void modMember(@ModelAttribute("member") MemberDTO member, 
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	public void removeMember(@RequestParam("id") String id, RedirectAttributes rAttr,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	public String searchId(@ModelAttribute("member") MemberDTO member,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	public String searchPwd(@ModelAttribute("member") MemberDTO member,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	public void csMail(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
