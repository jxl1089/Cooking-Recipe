package com.myspring.cookpro.review.service;

import java.util.List;
import java.util.Map;

import com.myspring.cookpro.review.dto.ReviewDTO;

public interface ReviewService {
	List<ReviewDTO> listArticles();
	int addNewArticle(Map<String, Object> articleMap);
	Map viewArticle(int articleNo);
	void modArticle(Map<String, Object> articleMap);
	void removeArticle(int articleNo);
}
