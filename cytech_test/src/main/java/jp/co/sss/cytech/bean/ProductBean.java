package jp.co.sss.cytech.bean;

import java.io.Serializable;


public class ProductBean implements Serializable{
	private Integer id;
	private String productName;
	private Integer price;
	private Integer stock;
	private String comment;
	private String imgPath;
	private Integer includeTax;
	
	private CompanyBean company;
	private CategoryBean category;
	
	public ProductBean() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Integer getIncludeTax() {
		return includeTax;
	}

	public void setIncludeTax(Integer includeTax) {
		this.includeTax = includeTax;
	}

	public CompanyBean getCompany() {
        return company;
    }

	public void setCompany(CompanyBean company) {
        this.company = company;
    }

	public CategoryBean getCategory() {
		return category;
	}

	public void setCategory(CategoryBean category) {
		this.category = category;
	}
	
}
