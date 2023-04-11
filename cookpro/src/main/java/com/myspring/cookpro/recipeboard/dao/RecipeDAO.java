package com.myspring.cookpro.recipeboard.dao;

import java.util.List;

import com.myspring.cookpro.recipeboard.dto.RecipeDTO;

public interface RecipeDAO {

	List<RecipeDTO> selectAllRecipesList();

}
