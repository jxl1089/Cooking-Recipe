package com.myspring.cookpro.recipeboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspring.cookpro.recipeboard.dao.RecipeDAO;
import com.myspring.cookpro.recipeboard.dto.RecipeDTO;

@Service
public class RecipeServiceImpl implements RecipeService{
	@Autowired
	private RecipeDAO recipeDAO;

	@Override
	public List<RecipeDTO> recipesList() {
		// TODO Auto-generated method stub
		return recipeDAO.selectAllRecipesList();
	}
}
