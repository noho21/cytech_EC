package jp.co.sss.cytech.bean;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ReviewBean implements Serializable{
	private Integer id;
	
	private ProductBean product;
	private UserBean user;
	
	private Integer rating;
	private String ratingStars;
	private String comment;
	private String dummyUserName;
	private String reviewImgPath;
	private LocalDateTime createdAt;
	
	public ReviewBean() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ProductBean getProduct() {
		return product;
	}

	public void setProduct(ProductBean product) {
		this.product = product;
	}

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getRatingStars() {
	    return "★".repeat(rating) + "☆".repeat(5 - rating);
	}

	public void setRatingStars(String ratingStars) {
		this.ratingStars = ratingStars;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDummyUserName() {
		return dummyUserName;
	}

	public void setDummyUserName(String dummyUserName) {
		this.dummyUserName = dummyUserName;
	}

	public String getReviewImgPath() {
		return reviewImgPath;
	}

	public void setReviewImgPath(String reviewImgPath) {
		this.reviewImgPath = reviewImgPath;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
}
