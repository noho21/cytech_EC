package jp.co.sss.cytech.entity;


import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sales_items")
public class SalesItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sale_item_id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;
	
	@Column(name = "sale_name")
	private String saleName;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "discount_rate")
	private Integer discountRate;
	
	@Column(name = "sales_img_path")
	private String salesImgPath;
	
	@Column(name = "start_month")
	private LocalDate startMonth;
	
	@Column(name = "end_month")
	private LocalDate endMonth;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
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

	public LocalDate getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(LocalDate startMonth) {
		this.startMonth = startMonth;
	}

	public LocalDate getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(LocalDate endMonth) {
		this.endMonth = endMonth;
	}
}
