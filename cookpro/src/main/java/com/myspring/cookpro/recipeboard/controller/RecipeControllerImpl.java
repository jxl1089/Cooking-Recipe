package com.myspring.cookpro.recipeboard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RecipeControllerImpl implements RecipeController{

	@Override
	@RequestMapping("/recipeboard/recipeList.do")
	public ModelAndView listArticle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName= request.getParameter("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}

	@Override
	@RequestMapping(value="/recipeboard/*Form.do", method=RequestMethod.GET)
	public ModelAndView r_form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String viewName= request.getParameter("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}

	
}
