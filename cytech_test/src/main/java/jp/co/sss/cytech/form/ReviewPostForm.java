package jp.co.sss.cytech.form;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;

public class ReviewPostForm {
private String dummyUserName;
private Integer rating;
@NotBlank(message = "メールアドレスを入力してください")
private String email;
@NotBlank(message = "コメントを入力してください")
private String comment;
private MultipartFile reviewImage;

public String getDummyUserName() {
	return dummyUserName;
}
public void setDummyUserName(String dummyUserName) {
	this.dummyUserName = dummyUserName;
}
public Integer getRating() {
	return rating;
}
public void setRating(Integer rating) {
	this.rating = rating;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getComment() {
	return comment;
}
public void setComment(String comment) {
	this.comment = comment;
}
public MultipartFile getReviewImage() {
	return reviewImage;
}
public void setReviewImage(MultipartFile reviewImage) {
	this.reviewImage = reviewImage;
}
}
