package com.myspring.cookpro.qna.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspring.cookpro.qna.dao.QnaDAO;
import com.myspring.cookpro.qna.dto.QnaDTO;

@Service
public class QnaServiceImpl implements QnaService{
	@Autowired
	QnaDAO qnaDAO;

	@Override
	public List<QnaDTO> listArticles() {
		// TODO Auto-generated method stub
		return qnaDAO.selectAllArticlesList();
	}

	@Override
	public int addNewArticle(Map<String, Object> articleMap) {
		// TODO Auto-generated method stub
		int articleNo = qnaDAO.insertNewArticle(articleMap);
		articleMap.put("articleNo", articleNo);
		return articleNo;

	}

	@Override
	public Map viewArticle(int articleNo) {
		// TODO Auto-generated method stub
		Map articleMap = new HashMap();
		QnaDTO articleDTO = qnaDAO.selectArticle(articleNo);
		
		articleMap.put("article", articleDTO);
		
		return articleMap;
	}
	
	@Override
	public void modArticle(Map<String, Object> articleMap) {
		// TODO Auto-generated method stub
		qnaDAO.updateArticle(articleMap);
	}
	
	@Override
	public void removeArticle(int articleNo) {
		// TODO Auto-generated method stub
		qnaDAO.deleteArticle(articleNo);
	}
}
