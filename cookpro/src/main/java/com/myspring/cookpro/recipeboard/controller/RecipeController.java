package com.myspring.cookpro.recipeboard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface RecipeController {
	
	public ModelAndView listArticle(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView r_form(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
