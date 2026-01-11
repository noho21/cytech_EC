package jp.co.sss.cytech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sss.cytech.bean.CompanyBean;
import jp.co.sss.cytech.bean.ProductBean;
import jp.co.sss.cytech.bean.ReviewBean;
import jp.co.sss.cytech.dto.ItemDetailDto;
import jp.co.sss.cytech.entity.Product;
import jp.co.sss.cytech.entity.Review;
import jp.co.sss.cytech.repository.ProductRepository;
import jp.co.sss.cytech.repository.ReviewRepository;

@Service
@Transactional
public class ItemService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public List<Product> search(String keyword, Integer categoryId) {
		
		if (keyword != null && !keyword.isBlank()
			&& categoryId != null) {
			return productRepository.findByProductNameContainingAndCategory_Id(keyword, categoryId);
		} else if (keyword != null && !keyword.isBlank()) {
	        return productRepository.findByProductNameContaining(keyword);

	    } else if (categoryId != null) {
	        return productRepository.findByCategory_Id(categoryId);

	    } else {
	        return productRepository.findAll();
	    }
	}
	
	public ItemDetailDto getItemDetail(int productId) {

        Product product = productRepository.findById(productId).orElseThrow();

        ProductBean productBean = new ProductBean();
        BeanUtils.copyProperties(product, productBean);

        CompanyBean companyBean = new CompanyBean();
        BeanUtils.copyProperties(product.getCompany(), companyBean);
        productBean.setCompany(companyBean);

        List<Review> reviews = reviewRepository.findByProductId(productId);

        List<ReviewBean> reviewBeans = new ArrayList<>();
        for (Review review : reviews) {
            ReviewBean bean = new ReviewBean();
            BeanUtils.copyProperties(review, bean);
            reviewBeans.add(bean);
        }

        return new ItemDetailDto(productBean, reviewBeans);
    }

}
