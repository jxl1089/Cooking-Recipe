package com.myspring.cookpro.reviewboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myspring.cookpro.reviewboard.dto.review_article_dto;
import com.myspring.cookpro.reviewboard.dto.review_image_dto;

public class review_daoimpl implements review_dao{

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<review_article_dto> selectAllArticlesList() {
		// TODO Auto-generated method stub
		List<review_article_dto> articlesList=  sqlSession.selectList("mapper.reviewboard.selectAllArticlesList");
		return articlesList;
	}

	@Override
	public int review_insertNewArticle(Map<String, Object> articleMap) {
		// TODO Auto-generated method stub
		int articleNo = selectNewArticleNo();
		articleMap.put("articleNo", articleNo);
		sqlSession.insert("mapper.reviewboard.insertNewArticle",articleMap);
		return articleNo;
		
	}
	private int selectNewArticleNo() {
		return sqlSession.selectOne("mapper.reviewboard.selectNewArticleNo");
	}

	@Override
	public review_article_dto selectArticle(int articleNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.reviewboard.selectArticle",articleNo);
	}

	@Override
	public void reivew_updateArticle(Map<String, Object> articleMap) {
		// TODO Auto-generated method stub
		sqlSession.update("mapper.reviewboard.updateArticle", articleMap);
	}

	@Override
	public void review_deleteArticle(int articleNo) {
		// TODO Auto-generated method stub
		sqlSession.delete("mapper.reviewboard.deleteArticle",articleNo);
	}

	@Override
	public void review_insertNewImage(Map<String, Object> articleMap) {
		// TODO Auto-generated method stub
		List<review_image_dto> imageFileList = (List<review_image_dto>) articleMap.get("imageFileList");
		int articleNo = (Integer)articleMap.get("articleNo");
		int imageFileNo = selectNewImageFileNo();
		for(review_image_dto image:imageFileList) {
			image.setImageFileNo(++imageFileNo);
			image.setArticleNo(articleNo);
		}
		sqlSession.insert("mapper.reviewboard.insertNewImage",imageFileList);
	}
	private int selectNewImageFileNo() {
		return sqlSession.selectOne("mapper.reviewboard.selectNewImageFileNo");
	}

	@Override
	public List<review_image_dto> selectImageFileList(int articleNo) {
		// TODO Auto-generated method stub
		List<review_image_dto> imageFileList = sqlSession.selectList("mapper.reviewboard.selectImageFIleList",articleNo);
		for(review_image_dto image:imageFileList) {
			System.out.println(image.getImageFileName());
		}
		return imageFileList; 
	}

}
