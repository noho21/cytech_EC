package jp.co.sss.cytech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jp.co.sss.cytech.dto.PurchaseInfo;
import jp.co.sss.cytech.entity.Cart;
import jp.co.sss.cytech.entity.Order;
import jp.co.sss.cytech.entity.Product;
import jp.co.sss.cytech.entity.User;
import jp.co.sss.cytech.enums.PurchaseType;
import jp.co.sss.cytech.enums.ReceivePlace;
import jp.co.sss.cytech.form.PurchaseForm;
import jp.co.sss.cytech.repository.OrderRepository;
import jp.co.sss.cytech.repository.UserRepository;
import jp.co.sss.cytech.service.CartService;
import jp.co.sss.cytech.service.OrderService;
import jp.co.sss.cytech.service.ProductService;

@Controller
public class CartController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
    private CartService cartService;
	@Autowired
    private OrderService orderService;
	@Autowired
    private ProductService productService;
	
	@PostMapping("/cart/add")
	public String cartAdd(
			@RequestParam Integer productId,
			@RequestParam Integer quantity,
			@AuthenticationPrincipal UserDetails userDetails,
			RedirectAttributes ra) {
		
		User user = userRepository
				.findByEmail(userDetails.getUsername())
				.orElseThrow();
		
		Integer cartId = cartService.addToCart(user, productId, quantity);
		
		ra.addFlashAttribute("message", "カートに追加しました");
		
		return "redirect:/cart/add/complete?cartId=" + cartId;
	}
	
	@GetMapping("/cart/add/complete")
	public String cartAddComplete(
	        @RequestParam Integer cartId,
	        @AuthenticationPrincipal UserDetails userDetails,
	        Model model
	) {
	    User user = userRepository
				.findByEmail(userDetails.getUsername())
				.orElseThrow();
	    
	    Cart cart = cartService.getCart(cartId, user);
	    
	    int selectedItemPrice =
	            cart.getProduct().getPrice() * cart.getQuantity();
	    
	    model.addAttribute("cart", cart);
	    model.addAttribute("selectedItemPrice", selectedItemPrice);
	    return "cart/cart_add";
	}

	@GetMapping("/cart/list")
	public String cartList(
			@AuthenticationPrincipal UserDetails userDetails,
	        Model model
			) {
		User user = userRepository
	            .findByEmail(userDetails.getUsername())
	            .orElseThrow();

	    List<Cart> cartItems = cartService.getCartItems(user);
	    
	    int totalQuantity = 0;
	    int totalPrice = 0;
	    int totalTaxPrice = 0;

	    for (Cart cart : cartItems) {
	        totalQuantity += cart.getQuantity();
	        totalPrice += cart.getProduct().getPrice() * cart.getQuantity();
	        totalTaxPrice += cart.getProduct().getIncludeTax() * cart.getQuantity();
	    }

	    model.addAttribute("totalQuantity", totalQuantity);
	    model.addAttribute("totalPrice", totalPrice);
	    model.addAttribute("totalTaxPrice", totalTaxPrice);
	    model.addAttribute("cartItems", cartItems);
	    
		return "cart/cart_list";
	}
	
	@PostMapping("/cart/delete")
	public String deleteCart(
			@RequestParam Integer cartId,
			@AuthenticationPrincipal UserDetails userDetails
	) {
		 User user = userRepository
				 .findByEmail(userDetails.getUsername())
				 .orElseThrow();
		 
		 cartService.deleteCart(cartId,  user);
		 
		 return "redirect:/cart/list";
	}

	@GetMapping("/purchase/input")
	public String purchaseInput(
			HttpSession session,
			@AuthenticationPrincipal UserDetails userDetails,
			Model model) {
	    PurchaseInfo info =
	        (PurchaseInfo) session.getAttribute("purchaseInfo");

	    User user = userRepository
	            .findByEmail(userDetails.getUsername())
	            .orElseThrow();
	    
	    model.addAttribute("user", user);
	    
	    if (info == null) {
	        return "redirect:/cart/list";
	    }
	    
	    if (!model.containsAttribute("purchaseForm")) {
	    	PurchaseForm form = new PurchaseForm();
	        form.setReceivePlace(ReceivePlace.REGISTERED); // enumなら
	        form.setRegisteredAddress(user.getUserAddress());
	        form.setRegisteredBuilding(user.getBuildingAddress());
	        model.addAttribute("purchaseForm", form);
	    }
	    
	    model.addAttribute("purchaseInfo", info);
	    
	    return "cart/purchase_input";
	}
	
	@PostMapping("/purchase/single/start")
	public String startSinglePurchase(
	        @RequestParam Integer productId,
	        @RequestParam Integer quantity,
	        HttpSession session
	) {
	    PurchaseInfo info = new PurchaseInfo();
	    info.setType(PurchaseType.SINGLE);
	    info.setProductId(productId);
	    info.setQuantity(quantity);

	    session.setAttribute("purchaseInfo", info);
	    
	    return "redirect:/purchase/input";
	}

	@PostMapping("/purchase/cart/start")
	public String startCartPurchase(HttpSession session) {
	    PurchaseInfo info = new PurchaseInfo();
	    info.setType(PurchaseType.CART);

	    session.setAttribute("purchaseInfo", info);
	    
	    return "redirect:/purchase/input";
	}
	
	@PostMapping("/purchase/confirm")
	public String purchaseConfirm(
			@Valid @ModelAttribute PurchaseForm form,
		    BindingResult result,
			HttpSession session,
	        @AuthenticationPrincipal UserDetails userDetails,
	        Model model
	) {
		System.out.println(result.getAllErrors());

		if (result.hasErrors()) {
	        User user = userRepository
	            .findByEmail(userDetails.getUsername())
	            .orElseThrow();

	        model.addAttribute("user", user);
	        return "cart/purchase_input";
	    }
		
		PurchaseInfo info =
		        (PurchaseInfo) session.getAttribute("purchaseInfo");
		
		if (info == null) {
	        return "redirect:/cart/list";
	    }

	    User user = userRepository
	            .findByEmail(userDetails.getUsername())
	            .orElseThrow();
	    
	    String address;
	    String building;

	    if (form.getReceivePlace() == ReceivePlace.REGISTERED) {
	        address = form.getRegisteredAddress();
	        building = form.getRegisteredBuilding();
	    } else {
	        address = form.getNewAddress();
	        building = form.getNewBuilding();
	    }

	    model.addAttribute("address", address);
	    model.addAttribute("building", building);
	    
	    session.setAttribute("purchaseForm", form);
	    
	    int totalPrice = 0;
	    int totalTaxPrice = 0;

	    if (info.getType() == PurchaseType.CART) {
	        List<Cart> cartItems = cartService.getCartItems(user);
	        model.addAttribute("cartItems", cartItems);
	        
	        for (Cart cart : cartItems) {
		        totalPrice += cart.getProduct().getPrice() * cart.getQuantity();
		        totalTaxPrice += cart.getProduct().getIncludeTax() * cart.getQuantity();
		    }
	    }
	    
	    if (info.getType() == PurchaseType.SINGLE) {
	        Product product = productService.getProduct(info.getProductId());
	        int quantity = info.getQuantity();
	        
	        model.addAttribute("product", product);
	        model.addAttribute("quantity", info.getQuantity());
	        
	        totalPrice = product.getPrice() * quantity;
	        totalTaxPrice = product.getIncludeTax() * quantity;
	    }
	    
	    model.addAttribute("totalPrice", totalPrice);
	    model.addAttribute("totalTaxPrice", totalTaxPrice);
	    model.addAttribute("form", form);
	    model.addAttribute("user", user);
	    
	    return "cart/purchase_confirm";
	}
	
	@PostMapping("/purchase/complete")
	public String purchaseComplete(
	        HttpSession session,
	        @AuthenticationPrincipal UserDetails userDetails) {

		PurchaseInfo info = (PurchaseInfo) session.getAttribute("purchaseInfo");
	    User user = userRepository
	        .findByEmail(userDetails.getUsername())
	        .orElseThrow();

	    Order savedOrder = orderService.completePurchase(info, user);

	    session.setAttribute("completedOrderId", savedOrder.getId());
	    session.removeAttribute("purchaseInfo");
	    session.removeAttribute("purchaseForm");
	    
	    return "redirect:/purchase/complete";
	}

	@GetMapping("/purchase/complete")
	public String purchaseCompleteGet(
			HttpSession session,
	        Model model,
	        @AuthenticationPrincipal UserDetails userDetails) {

	    Integer orderId = (Integer) session.getAttribute("completedOrderId");
	    if (orderId == null) {
	        return "redirect:/cart/list";
	    }

	    Order order = orderRepository.findByIdWithItems(orderId)
	    	    .orElseThrow(() -> new IllegalArgumentException("注文が見つかりません"));
	    
	    User user = userRepository
		        .findByEmail(userDetails.getUsername())
		        .orElseThrow();
	    model.addAttribute("order", order);
	    model.addAttribute("user", user);

	    session.removeAttribute("completedOrderId");
	    return "cart/purchase_complete";
	}

}
