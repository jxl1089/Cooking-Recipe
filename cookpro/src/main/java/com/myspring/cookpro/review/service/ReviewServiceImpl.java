package com.myspring.cookpro.review.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspring.cookpro.review.dao.ReviewDAO;
import com.myspring.cookpro.review.dto.ReviewDTO;

@Service
public class ReviewServiceImpl implements ReviewService{
	@Autowired
	ReviewDAO reviewDAO;

	@Override
	public List<ReviewDTO> listArticles() {
		// TODO Auto-generated method stub
		return reviewDAO.selectAllArticlesList();
	}

	@Override
	public int addNewArticle(Map<String, Object> articleMap) {
		// TODO Auto-generated method stub
		int articleNo = reviewDAO.insertNewArticle(articleMap);
		articleMap.put("articleNo", articleNo);
		/* reviewDAO.insertNewImage(articleMap); */
		return articleNo;

	}

	@Override
	public Map viewArticle(int articleNo) {
		// TODO Auto-generated method stub
		Map articleMap = new HashMap();
		ReviewDTO articleDTO = reviewDAO.selectArticle(articleNo);
		
		/* List<ImageDTO> imageFileList = reviewDAO.selectImageFileList(articleNo); */
		
		articleMap.put("article", articleDTO);
		/* articleMap.put("imageFileList", imageFileList); */
		
		return articleMap;
	}
	
	@Override
	public void modArticle(Map<String, Object> articleMap) {
		// TODO Auto-generated method stub
		reviewDAO.updateArticle(articleMap);
	}
	
	@Override
	public void removeArticle(int articleNo) {
		// TODO Auto-generated method stub
		reviewDAO.deleteArticle(articleNo);
	}
}
