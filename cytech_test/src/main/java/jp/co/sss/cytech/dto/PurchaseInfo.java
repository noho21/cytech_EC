package jp.co.sss.cytech.dto;

import jp.co.sss.cytech.enums.PurchaseType;

public class PurchaseInfo {

	private PurchaseType type;
	private Integer productId;
	private Integer cartId;
	private Integer quantity;
	
	public PurchaseType getType() {
		return type;
	}
	public void setType(PurchaseType type) {
		this.type = type;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getCartId() {
		return cartId;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}
