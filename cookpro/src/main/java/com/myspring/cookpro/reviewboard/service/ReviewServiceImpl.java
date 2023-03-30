package com.myspring.cookpro.reviewboard.service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspring.cookpro.reviewboard.dao.review_dao;
import com.myspring.cookpro.reviewboard.dto.review_article_dto;
import com.myspring.cookpro.reviewboard.dto.review_image_dto;

public class ReviewServiceImpl implements ReviewService{
	@Autowired
	review_dao review_dao;
	
	@Override
	public List<review_article_dto> review_listArticles() {
		// TODO Auto-generated method stub
		return review_dao.selectAllArticlesList();
	}

	@Override
	public int review_addNewArticle(Map<String, Object> articleMap) {
		// TODO Auto-generated method stub
		int articleNo = review_dao.review_insertNewArticle(articleMap);
		articleMap.put("articleNo", articleNo);
		review_dao.review_insertNewImage(articleMap);
		return articleNo;
	}

	@Override
	public Map review_viewArticle(int articleNo) {
		// TODO Auto-generated method stub
		Map articleMap = new HashMap();
		review_article_dto review_article_dto = review_dao.selectArticle(articleNo);
		List<review_image_dto> imageFileList = review_dao.selectImageFileList(articleNo);
		articleMap.put("article",review_article_dto);
		articleMap.put("imageFileList", imageFileList);
		return articleMap;
	}

	@Override
	public void review_modArticle(Map<String, Object> articleMap) {
		// TODO Auto-generated method stub
		review_dao.reivew_updateArticle(articleMap);
	}

	@Override
	public void review_removeArticle(int articleNo) {
		// TODO Auto-generated method stub
		review_dao.review_deleteArticle(articleNo);
	}

}
