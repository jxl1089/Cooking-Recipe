package com.myspring.cookpro.recipeboard.dto;

import java.sql.Date;

public class RecipeDTO {
	private String id;
	private String recipe_title;
	private String recipe_detail;
	private int recipe_views;
	private int recipe_like;
	private int recipe_dislike;
	private String recipe_image;
	private int recipe_no;
	private String recipe_tab;
	private Date writeDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRecipe_title() {
		return recipe_title;
	}
	public void setRecipe_title(String recipe_title) {
		this.recipe_title = recipe_title;
	}
	public String getRecipe_detail() {
		return recipe_detail;
	}
	public void setRecipe_detail(String recipe_detail) {
		this.recipe_detail = recipe_detail;
	}
	public int getRecipe_views() {
		return recipe_views;
	}
	public void setRecipe_views(int recipe_views) {
		this.recipe_views = recipe_views;
	}
	public int getRecipe_like() {
		return recipe_like;
	}
	public void setRecipe_like(int recipe_like) {
		this.recipe_like = recipe_like;
	}
	public int getRecipe_dislike() {
		return recipe_dislike;
	}
	public void setRecipe_dislike(int recipe_dislike) {
		this.recipe_dislike = recipe_dislike;
	}
	public String getRecipe_image() {
		return recipe_image;
	}
	public void setRecipe_image(String recipe_image) {
		this.recipe_image = recipe_image;
	}
	public int getRecipe_no() {
		return recipe_no;
	}
	public void setRecipe_no(int recipe_no) {
		this.recipe_no = recipe_no;
	}
	public String getRecipe_tab() {
		return recipe_tab;
	}
	public void setRecipe_tab(String recipe_tab) {
		this.recipe_tab = recipe_tab;
	}
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	
	

}
