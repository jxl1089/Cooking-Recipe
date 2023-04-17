package com.myspring.cookpro.qna.dao;

import java.util.List;
import java.util.Map;

import com.myspring.cookpro.qna.dto.ImageDTO;
import com.myspring.cookpro.qna.dto.QnaDTO;

public interface QnaDAO {
	List<QnaDTO> selectAllArticlesList();
	int insertNewArticle(Map<String, Object> articleMap);
	QnaDTO selectArticle(int articleNo);
	void updateArticle(Map<String, Object> articleMap);
	void deleteArticle(int articleNo);
	void insertNewImage(Map<String, Object> articleMap);
	List<ImageDTO> selectImageFileList(int articleNo);

}
