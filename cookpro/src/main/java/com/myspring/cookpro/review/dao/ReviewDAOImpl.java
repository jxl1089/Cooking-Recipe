package com.myspring.cookpro.review.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myspring.cookpro.review.dto.ReviewDTO;

@Repository
public class ReviewDAOImpl implements ReviewDAO{
	@Autowired
	SqlSession sqlSession;

	@Override
	public List<ReviewDTO> selectAllArticlesList() {
		// TODO Auto-generated method stub
		List<ReviewDTO> articlesList = sqlSession.selectList("mapper.review.selectAllArticlesList");
		
		return articlesList;
	}

	@Override
	public int insertNewArticle(Map<String, Object> articleMap) {
		// TODO Auto-generated method stub
		int articleNo = selectNewArticleNo();
		
		articleMap.put("articleNo", articleNo);
		sqlSession.insert("mapper.review.insertNewArticle", articleMap);
		
		return articleNo;
	}
	
	private int selectNewArticleNo() {
		// TODO Auto-generated method stub
		
		 Integer result = sqlSession.selectOne("mapper.review.selectNewArticleNo");
		 if(result == null) {
				result = 1;
			}
		 return result;
	}
	
	@Override
	public ReviewDTO selectArticle(int articleNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.review.selectArticle", articleNo);
	}

	@Override
	public void updateArticle(Map<String, Object> articleMap) {
		// TODO Auto-generated method stub
		sqlSession.update("mapper.review.updateArticle", articleMap);
	}
	
	@Override
	public void deleteArticle(int articleNo) {
		// TODO Auto-generated method stub
		sqlSession.delete("mapper.review.deleteArticle", articleNo);
	}
}
