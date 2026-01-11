package jp.co.sss.cytech.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.co.sss.cytech.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	
	@Query("""
	        SELECT o FROM Order o
	        JOIN FETCH o.orderItems oi
	        JOIN FETCH oi.product
	        WHERE o.id = :orderId
	    """)
	
	Optional<Order> findByIdWithItems(@Param("orderId") Integer orderId);
}
