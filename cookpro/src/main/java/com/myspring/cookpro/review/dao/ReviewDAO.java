package com.myspring.cookpro.review.dao;

import java.util.List;
import java.util.Map;

import com.myspring.cookpro.review.dto.ReviewDTO;

public interface ReviewDAO {
	List<ReviewDTO> selectAllArticlesList();
	int insertNewArticle(Map<String, Object> articleMap);
	ReviewDTO selectArticle(int articleNo);
	void updateArticle(Map<String, Object> articleMap);
	void deleteArticle(int articleNo);
}
