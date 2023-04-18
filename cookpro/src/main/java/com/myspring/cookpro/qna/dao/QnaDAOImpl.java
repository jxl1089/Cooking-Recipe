package com.myspring.cookpro.qna.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myspring.cookpro.qna.dto.QnaDTO;

@Repository
public class QnaDAOImpl implements QnaDAO{
	@Autowired
	SqlSession sqlSession;

	@Override
	public List<QnaDTO> selectAllArticlesList() {
		// TODO Auto-generated method stub
		List<QnaDTO> articlesList = sqlSession.selectList("mapper.qna.selectAllArticlesList");
		
		return articlesList;
	}

	@Override
	public int insertNewArticle(Map<String, Object> articleMap) {
		// TODO Auto-generated method stub
		int articleNo = selectNewArticleNo();
		
		articleMap.put("articleNo", articleNo);
		sqlSession.insert("mapper.qna.insertNewArticle", articleMap);
		
		return articleNo;
	}
	
	private int selectNewArticleNo() {
		// TODO Auto-generated method stub
		
		Integer result = sqlSession.selectOne("mapper.qna.selectNewArticleNo");
		 if(result == null) {
				result = 1;
			}
		 return result;
	}
	
	@Override
	public QnaDTO selectArticle(int articleNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.qna.selectArticle", articleNo);
	}

	@Override
	public void updateArticle(Map<String, Object> articleMap) {
		// TODO Auto-generated method stub
		sqlSession.update("mapper.qna.updateArticle", articleMap);
	}
	//
	@Override
	public void deleteArticle(int articleNo) {
		// TODO Auto-generated method stub
		sqlSession.delete("mapper.qna.deleteArticle", articleNo);
	}

}
