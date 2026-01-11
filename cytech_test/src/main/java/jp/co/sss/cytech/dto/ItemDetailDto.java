package jp.co.sss.cytech.dto;

import java.util.List;

import jp.co.sss.cytech.bean.ProductBean;
import jp.co.sss.cytech.bean.ReviewBean;

public class ItemDetailDto {

    private ProductBean product;
    private List<ReviewBean> reviews;

    public ItemDetailDto(ProductBean product, List<ReviewBean> reviews) {
        this.product = product;
        this.reviews = reviews;
    }

    public ProductBean getProduct() {
        return product;
    }

    public List<ReviewBean> getReviews() {
        return reviews;
    }
}
