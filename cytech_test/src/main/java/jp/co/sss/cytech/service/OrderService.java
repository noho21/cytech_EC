package jp.co.sss.cytech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sss.cytech.dto.PurchaseInfo;
import jp.co.sss.cytech.entity.Cart;
import jp.co.sss.cytech.entity.Order;
import jp.co.sss.cytech.entity.OrderItem;
import jp.co.sss.cytech.entity.Product;
import jp.co.sss.cytech.entity.User;
import jp.co.sss.cytech.enums.PurchaseType;
import jp.co.sss.cytech.repository.OrderItemRepository;
import jp.co.sss.cytech.repository.OrderRepository;
import jp.co.sss.cytech.repository.ProductRepository;
import jp.co.sss.cytech.repository.UserRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
    private CartService cartService;
	
	@Transactional
	public Order completePurchase(PurchaseInfo info, User user) {

		if (info == null) {
            throw new IllegalStateException("購入情報がありません");
        }

        Order order = new Order();
        order.setUser(user);
        order.setStatus("ORDERED");
        
        if (info.getType() == PurchaseType.CART) {
        	
        	List<Cart> carts = cartService.getCartItems(user);
            if (carts.isEmpty()) {
                throw new IllegalStateException("カートが空です");
            }
            
            int total = 0;

            for (Cart cart : carts) {
                total += cart.getProduct().getPrice() * cart.getQuantity();
            }
            order.setTotalAmount(total);

            Order savedOrder = orderRepository.save(order);

            for (Cart cart : carts) {
            	Product product = cart.getProduct();
            	
            	int newStock = product.getStock() - cart.getQuantity();
                if (newStock < 0) {
                    throw new IllegalStateException("在庫不足です");
                }

                product.setStock(newStock);
                productRepository.save(product);
                
                OrderItem item = new OrderItem();
                item.setOrder(savedOrder);
                item.setProduct(cart.getProduct());
                item.setQuantity(cart.getQuantity());
                item.setPrice(cart.getProduct().getPrice());
                orderItemRepository.save(item);
            }
            cartService.clearCart(user);
            return savedOrder;
        } else if (info.getType() == PurchaseType.SINGLE) {

        	Product product =
        		    productRepository.findById(info.getProductId()).orElseThrow();

        		int newStock = product.getStock() - info.getQuantity();
        		if (newStock < 0) {
        		    throw new IllegalStateException("在庫不足です");
        		}

        		product.setStock(newStock);
        		productRepository.save(product);

            int quantity = info.getQuantity();
            int total = product.getPrice() * quantity;

            order.setTotalAmount(total);

            Order savedOrder = orderRepository.save(order);

            OrderItem item = new OrderItem();
            item.setOrder(savedOrder);
            item.setProduct(product);
            item.setQuantity(quantity);
            item.setPrice(product.getPrice());
            orderItemRepository.save(item);
            
            return savedOrder;
        }
        throw new IllegalStateException("不正な購入タイプです");
	}

}
