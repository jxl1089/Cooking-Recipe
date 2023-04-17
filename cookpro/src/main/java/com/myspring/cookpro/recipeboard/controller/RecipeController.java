package com.myspring.cookpro.recipeboard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

public interface RecipeController {
	
	public ModelAndView listRecipe(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView r_form(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView viewRecipe(@RequestParam("recipe_no") int recipe_no, 
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity addNewRecipe(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception;
	public ResponseEntity removceRecipe (@RequestParam("recipe_no") int recipe_no, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity modRecipe(MultipartHttpServletRequest multiRequest, HttpServletResponse response) throws Exception;
	public void imageUpload(MultipartHttpServletRequest multipartRequest, HttpServletRequest request, HttpServletResponse response) throws Exception;
	//ModelAndView imageUpload(MultipartHttpServletRequest request) throws Exception;


}
