package com.myspring.cookpro.reviewboard.dto;

import java.sql.Date;
import org.springframework.stereotype.Component;

@Component
public class Review_article_dto {
private int review_level;
private int review_no;
private String review_title;
private String review_content;
private String id;
private Date writeDate;
private int reviewParent_no;
public int getReviewParent_no() {
	return reviewParent_no;
}
public void setReviewParent_no(int reviewParent_no) {
	this.reviewParent_no = reviewParent_no;
}
public int getReview_level() {
	return review_level;
}
public void setReview_level(int review_level) {
	this.review_level = review_level;
}
public int getReview_no() {
	return review_no;
}
public void setReview_no(int review_no) {
	this.review_no = review_no;
}
public String getReview_title() {
	return review_title;
}
public void setReview_title(String review_title) {
	this.review_title = review_title;
}
public String getReview_content() {
	return review_content;
}
public void setReview_content(String review_content) {
	this.review_content = review_content;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public Date getWriteDate() {
	return writeDate;
}
public void setWriteDate(Date writeDate) {
	this.writeDate = writeDate;
}

}
