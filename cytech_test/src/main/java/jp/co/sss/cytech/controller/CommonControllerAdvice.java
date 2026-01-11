package jp.co.sss.cytech.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jp.co.sss.cytech.entity.Category;
import jp.co.sss.cytech.entity.User;
import jp.co.sss.cytech.repository.CategoryRepository;
import jp.co.sss.cytech.repository.UserRepository;

@ControllerAdvice
public class CommonControllerAdvice {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
    private UserRepository userRepository;
	
	@ModelAttribute("categories")
	public List<Category> categories() {
		return categoryRepository.findAll();
	}
	
	@ModelAttribute
    public void addLoginUser(
            @AuthenticationPrincipal UserDetails userDetails,
            Model model) {

		if (userDetails != null) {
	        String email = userDetails.getUsername();

	        Optional<User> userOpt = userRepository.findByEmail(email);
	        if (userOpt.isPresent()) {
	            model.addAttribute(
	                "loginUserName",
	                userOpt.get().getUserName()
	            );
	        }
	    }
    }
}
