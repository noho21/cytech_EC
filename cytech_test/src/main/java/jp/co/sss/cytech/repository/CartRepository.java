package jp.co.sss.cytech.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.cytech.entity.Cart;
import jp.co.sss.cytech.entity.Product;
import jp.co.sss.cytech.entity.User;

public interface CartRepository extends JpaRepository<Cart, Integer>{
	Optional<Cart> findByIdAndUser(Integer id, User user);

	Optional<Cart> findByUserAndProduct(User user, Product product);
	
	List<Cart> findByUser(User user);
}
