package jp.co.sss.cytech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import jp.co.sss.cytech.dto.ItemDetailDto;
import jp.co.sss.cytech.entity.Product;
import jp.co.sss.cytech.entity.SalesItem;
import jp.co.sss.cytech.entity.User;
import jp.co.sss.cytech.form.ReviewPostForm;
import jp.co.sss.cytech.repository.SalesItemRepository;
import jp.co.sss.cytech.repository.UserRepository;
import jp.co.sss.cytech.service.ItemService;
import jp.co.sss.cytech.service.ReviewService;

@Controller
public class ItemController {

	@Autowired
	private SalesItemRepository salesItemRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ItemService itemService;
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping("/top")
	public String showTopPage(Model model) {
		List<SalesItem> salesItems = salesItemRepository.findActiveSalesItems();
		model.addAttribute("salesItems", salesItems);
		return "top";
	}
	
	@GetMapping("/items/findAll")
	public String itemList(Model model) {
		model.addAttribute("productList", itemService.findAll());
		return "items/item_list";
	}
	
	@GetMapping("/items/search")
	public String search(
		@RequestParam(required = false) String keyword,
		@RequestParam(required = false) Integer categoryId,
		Model model
	) {
		List<Product> itemList = itemService.search(keyword, categoryId);
		model.addAttribute("productList", itemList);
		return "items/item_list";
	}
	
	@GetMapping("/items/detail/{id}")
	public String itemDetail(@PathVariable int id, Model model) {
		ItemDetailDto detail = itemService.getItemDetail(id);
		
		model.addAttribute("product", detail.getProduct());
	    model.addAttribute("reviews", detail.getReviews());
		return "items/item_detail";
	}
	
	@GetMapping("/reviews/{id}/post")
	public String reviewPostForm(@PathVariable int id, Model model) {
		model.addAttribute("reviewPostForm", new ReviewPostForm());
		model.addAttribute("productId", id);
		return "items/review_post";
	}
	
	@PostMapping("/reviews/{id}/post")
	public String reviewPost(
			@PathVariable Integer id,
		    Model model,
			@Valid ReviewPostForm form,
	        BindingResult bindingResult,
	        @AuthenticationPrincipal UserDetails userDetails
	        ) {
		
		if (bindingResult.hasErrors()) {
		    model.addAttribute("productId", id);
		    return "items/review_post";
	    }
		
        User user = userRepository
        		.findByEmail(userDetails.getUsername())
        		.orElseThrow();

        if (!user.getEmail().equals(form.getEmail())) {
            bindingResult.rejectValue(
                "email",
                "email.mismatch",
                "ログイン中のメールアドレスと一致しません"
            );

            model.addAttribute("productId", id);
            return "items/review_post";
        }
		
		reviewService.postReview(
		        user,
		        id,
		        form.getRating(),
		        form.getComment(),
		        form.getDummyUserName(),
		        form.getReviewImage()
		    );
		
		model.addAttribute("productId", id);
		model.addAttribute("reviewPostForm", new ReviewPostForm());
	    
	    return "redirect:/items/detail/" + id;

	}
}
