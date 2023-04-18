package com.myspring.cookpro.recipeboard.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.myspring.cookpro.recipeboard.dto.RecipeDTO;

public interface RecipeService {

	List<RecipeDTO> recipesList();

	int addNewRecipe(Map<String, Object> recipeMap);

	Map viewRecipe(int recipeNo);

	void modRecipe(Map<String, Object> recipeMap);

	void likeupRecipe(Map<String, Object> recipeMap);

	void dislikeupRecipe(Map<String, Object> recipeMap);

	List<RecipeDTO> recipesListKr();

	List<RecipeDTO> recipesListEn();

	List<RecipeDTO> recipesListCn();

	List<RecipeDTO> recipesListJp();

	List<RecipeDTO> recipesListOt();

	List<RecipeDTO> recipesListTo();

	void removeSerivce(int recipe_no);

	List<RecipeDTO> recipesListMy();



}
