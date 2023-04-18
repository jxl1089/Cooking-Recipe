package com.myspring.cookpro.reviewboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myspring.cookpro.reviewboard.dto.Review_article_dto;
import com.myspring.cookpro.reviewboard.dto.Review_image_dto;

@Repository
public class Review_dao_impl implements Review_dao{

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<Review_article_dto> selectAllArticlesList() {
		// TODO Auto-generated method stub
		List<Review_article_dto> articlesList=  sqlSession.selectList("mapper.reviewboard.selectAllReviewList");
		return articlesList;
	}

	@Override
	public int review_insertNewArticle(Map<String, Object> articleMap) {
		// TODO Auto-generated method stub
		int articleNo = selectNewArticleNo();
		articleMap.put("review_no", articleNo);
		sqlSession.insert("mapper.reviewboard.insertArticle",articleMap);
		return articleNo;
		
	}
	private int selectNewArticleNo() {
		Integer result=  sqlSession.selectOne("mapper.reviewboard.selectNewReviewNo");
		if(result == null) {
			result = 1;
		}
		return (int) result;
	}

	@Override
	public Review_article_dto selectArticle(int articleNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.reviewboard.selectReview",articleNo);
	}

	@Override
	public void reivew_updateArticle(Map<String, Object> articleMap) {
		// TODO Auto-generated method stub
		sqlSession.update("mapper.reviewboard.updateReview", articleMap);
	}

	@Override
	public void review_deleteArticle(int articleNo) {
		// TODO Auto-generated method stub
		sqlSession.delete("mapper.reviewboard.deleteReview",articleNo);
	}

	

}

/*
@Repository
public class Review_dao_impl implements Review_dao {
    @Autowired
    private SqlSession sqlSession;
    
    @Override
    public List<Review_article_dto> selectAllArticlesList() {
        return sqlSession.selectList("mapper.reviewboard.selectAllArticlesList");
    }
    
    @Override
    public int review_insertNewArticle(Map<String, Object> articleMap) {
        int articleNo = selectNewNo("mapper.reviewboard.selectNewArticleNo");
        articleMap.put("articleNo", articleNo);
        sqlSession.insert("mapper.reviewboard.insertNewArticle", articleMap);
        return articleNo;
    }
    
    private int selectNewNo(String queryId) {
        Integer newNo = sqlSession.selectOne(queryId);
        if (newNo == null) {
            throw new RuntimeException("Failed to get new number");
        }
        return newNo;
    }
    
    @Override
    public Review_article_dto selectArticle(int articleNo) {
        return sqlSession.selectOne("mapper.reviewboard.selectArticle", articleNo);
    }
    
    @Override
    public void review_updateArticle(Map<String, Object> articleMap) {
        sqlSession.update("mapper.reviewboard.updateArticle", articleMap);
    }
    
    @Override
    public void review_deleteArticle(int articleNo) {
        sqlSession.delete("mapper.reviewboard.deleteArticle", articleNo);
    }
    
    @Override
    public void review_insertNewImage(Map<String, Object> articleMap) {
        List<Review_image_dto> imageFileList = (List<Review_image_dto>) articleMap.get("imageFileList");
        Integer articleNo = (Integer) articleMap.get("articleNo");
        int imageFileNo = selectNewNo("mapper.reviewboard.selectNewImageFileNo");
        for (Review_image_dto image : imageFileList) {
            image.setImageFileNo(++imageFileNo);
            image.setArticleNo(articleNo);
        }
        try {
            sqlSession.insert("mapper.reviewboard.insertNewImage", imageFileList);
        } catch (Exception e) {
            // �삤瑜� 泥섎━
            e.printStackTrace();
        }
    }
    
    @Override
    public List<Review_image_dto> selectImageFileList(int articleNo) {
        return sqlSession.selectList("mapper.reviewboard.selectImageFileList", articleNo);
    }
}
*/
