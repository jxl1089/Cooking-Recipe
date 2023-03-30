package com.myspring.cookpro.reviewboard.dto;
import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class Review_image_dto {
private int imageFileNo;
private String imageFileName;
private Date regDate; //등록일자
private int articleNo;
public int getImageFileNo() {
	return imageFileNo;
}
public void setImageFileNo(int imageFileNo) {
	this.imageFileNo = imageFileNo;
}
public String getImageFileName() {
	return imageFileName;
}
public void setImageFileName(String imageFileName) {
	this.imageFileName = imageFileName;
}
public Date getRegDate() {
	return regDate;
}
public void setRegDate(Date regDate) {
	this.regDate = regDate;
}
public int getArticleNo() {
	return articleNo;
}
public void setArticleNo(int articleNo) {
	this.articleNo = articleNo;
}
}
