package com.myspring.cookpro.recipeboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspring.cookpro.recipeboard.dao.RecipeDAO;

@Service
public class RecipeServiceImpl implements RecipeService{
	@Autowired
	private RecipeDAO recipeDAO;
}
