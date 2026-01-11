package jp.co.sss.cytech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sss.cytech.entity.Product;
import jp.co.sss.cytech.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Product getProduct(Integer productId) {
        return productRepository
                .findById(productId)
                .orElseThrow(() -> 
                    new IllegalArgumentException("商品が存在しません"));
    }   
}
