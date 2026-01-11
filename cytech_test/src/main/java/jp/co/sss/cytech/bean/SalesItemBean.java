package jp.co.sss.cytech.bean;

import java.io.Serializable;

public class SalesItemBean  implements Serializable{
	private Integer id;
	
	private ProductBean product;
	private CompanyBean company;
	
	private String saleName;
	private String description;
	private Integer discountRate;
	private String salesImgPath;
	private String startMonth;
	private String endMonth;
	
	public SalesItemBean() {
		
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
	public CompanyBean getCompany() {
		return company;
	}
	public void setCompany(CompanyBean company) {
		this.company = company;
	}
	public String getSaleName() {
		return saleName;
	}
	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(Integer discountRate) {
		this.discountRate = discountRate;
	}
	public String getSalesImgPath() {
		return salesImgPath;
	}
	public void setSalesImgPath(String salesImgPath) {
		this.salesImgPath = salesImgPath;
	}
	public String getStartMonth() {
		return startMonth;
	}
	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}
	public String getEndMonth() {
		return endMonth;
	}
	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}
	
	
}
