package com.myspring.cookpro.reviewboard.service;

import java.util.List;
import java.util.Map;

import com.myspring.cookpro.reviewboard.dto.Review_article_dto;

public interface ReviewService {

	List<Review_article_dto> review_listArticles();
	int review_addNewArticle(Map<String, Object> articleMap);
	Map review_viewArticle(int articleNo);
	void review_modArticle(Map<String, Object> articleMap);
	void review_removeArticle(int articleNo);
}
