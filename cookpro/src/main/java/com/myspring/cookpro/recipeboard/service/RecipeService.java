package com.myspring.cookpro.recipeboard.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.myspring.cookpro.recipeboard.dto.RecipeDTO;

public interface RecipeService {

	List<RecipeDTO> recipesList();

}
