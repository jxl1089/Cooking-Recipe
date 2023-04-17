package com.myspring.cookpro.qna.service;

import java.util.List;
import java.util.Map;

import com.myspring.cookpro.qna.dto.QnaDTO;

public interface QnaService {
	List<QnaDTO> listArticles();
	int addNewArticle(Map<String, Object> articleMap);
	Map viewArticle(int articleNo);
	void modArticle(Map<String, Object> articleMap);
	void removeArticle(int articleNo);
}
