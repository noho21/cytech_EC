package jp.co.sss.cytech.bean;

import java.io.Serializable;

public class CategoryBean implements Serializable{
	private Integer id;
	private String categoryName;
	private String description;
	
	public CategoryBean() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
