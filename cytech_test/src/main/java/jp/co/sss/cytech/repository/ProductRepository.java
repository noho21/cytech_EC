package jp.co.sss.cytech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.cytech.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	List<Product> findByProductNameContainingAndCategory_Id(String keyword, Integer categoryId);
	
	List<Product> findByProductNameContaining(String keyword);
	
	List<Product> findByCategory_Id(Integer categoryId);
}
