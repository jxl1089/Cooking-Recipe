package com.myspring.cookpro.reviewboard.dao;

import java.util.List;
import java.util.Map;

import com.myspring.cookpro.reviewboard.dto.review_article_dto;
import com.myspring.cookpro.reviewboard.dto.review_image_dto;

public interface review_dao {
List<review_article_dto> selectAllArticlesList();
int review_insertNewArticle(Map<String, Object> articleMap);
review_article_dto selectArticle(int articleNo);
void reivew_updateArticle(Map<String, Object> articleMap);
void review_deleteArticle(int articleNo);
void review_insertNewImage(Map<String, Object> articleMap);
List<review_image_dto>selectImageFileList(int articleNo);
}
