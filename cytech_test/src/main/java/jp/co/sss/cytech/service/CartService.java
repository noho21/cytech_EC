package jp.co.sss.cytech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sss.cytech.entity.Cart;
import jp.co.sss.cytech.entity.Product;
import jp.co.sss.cytech.entity.User;
import jp.co.sss.cytech.repository.CartRepository;
import jp.co.sss.cytech.repository.OrderItemRepository;
import jp.co.sss.cytech.repository.OrderRepository;
import jp.co.sss.cytech.repository.ProductRepository;
import jp.co.sss.cytech.repository.UserRepository;


@Service
public class CartService {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	public Cart getCart(Integer cartId, User user) {
	    return cartRepository
	        .findByIdAndUser(cartId, user)
	        .orElseThrow(() -> new IllegalArgumentException("不正なカートです"));
	}
	
	@Transactional
	public Integer addToCart(User user, Integer productId, Integer quantity) {
		System.out.println("addToCart 呼ばれた quantity=" + quantity);
		Product product = productRepository
        		.findById(productId)
        		.orElseThrow();
		
		Cart cart = cartRepository
	            .findByUserAndProduct(user, product)
	            .orElse(null);

	    if (cart == null) {
	        // 初めて追加する商品
	        cart = new Cart();
	        cart.setUser(user);
	        cart.setProduct(product);
	        cart.setQuantity(quantity);
	    } else {
	        // すでにある → 数量加算
	        cart.setQuantity(cart.getQuantity() + quantity);
	    }
        
        cartRepository.save(cart);
        return cart.getId();
	}
	
	
	public List<Cart> getCartItems(User user) {
	    return cartRepository.findByUser(user);
	}
	
	@Transactional
	public void deleteCart(Integer cartId, User user) {

	    Cart cart = cartRepository
	            .findByIdAndUser(cartId, user)
	            .orElseThrow(() -> 
	                new IllegalArgumentException("不正なカート操作です"));

	    cartRepository.delete(cart);
	}
	
	@Transactional
	public void clearCart(User user) {
	    List<Cart> carts = cartRepository.findByUser(user);
	    cartRepository.deleteAll(carts);
	}
}
