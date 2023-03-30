package com.myspring.cookpro.reviewboard.dto;

import java.sql.Date;
import org.springframework.stereotype.Component;

@Component
public class review_article_dto {
private int level;
private int articleNo;
private String title;
private String content;
private String imageFileName;
private String id;
private Date writeDate;

public int getLevel() {
	return level;
}
public void setLevel(int level) {
	this.level = level;
}
public int getArticleNo() {
	return articleNo;
}
public void setArticleNo(int articleNo) {
	this.articleNo = articleNo;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getImageFileName() {
	return imageFileName;
}
public void setImageFileName(String imageFileName) {
	this.imageFileName = imageFileName;
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
