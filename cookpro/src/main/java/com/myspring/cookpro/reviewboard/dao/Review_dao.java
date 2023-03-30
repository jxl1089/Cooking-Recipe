package com.myspring.cookpro.reviewboard.dao;

import java.util.List;
import java.util.Map;

import com.myspring.cookpro.reviewboard.dto.Review_article_dto;
import com.myspring.cookpro.reviewboard.dto.Review_image_dto;

public interface Review_dao {
List<Review_article_dto> selectAllArticlesList();
int review_insertNewArticle(Map<String, Object> articleMap);
Review_article_dto selectArticle(int articleNo);
void reivew_updateArticle(Map<String, Object> articleMap);
void review_deleteArticle(int articleNo);
void review_insertNewImage(Map<String, Object> articleMap);
List<Review_image_dto>selectImageFileList(int articleNo);
}
