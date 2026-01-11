package jp.co.sss.cytech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import jp.co.sss.cytech.entity.User;
import jp.co.sss.cytech.form.UserRegisterForm;
import jp.co.sss.cytech.repository.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/user/register")
	public String register(Model model) {
		model.addAttribute("userRegisterForm", new UserRegisterForm());
		return "user/register";
	}
	
	@PostMapping("/user/register")
	public String registerConfirm(
			@Valid UserRegisterForm form,
	        BindingResult bindingResult,
	        Model model) {
		
		if (userRepository.existsByEmail(form.getEmail())) {
			bindingResult.rejectValue(
				"email",
				null,
				"すでに登録されているメールアドレスです"
			);
		}
		
		if (!form.getPassword().equals(form.getPasswordConfirm())) {
		    bindingResult.rejectValue(
		        "passwordConfirm",
		        null,
		        "パスワードが一致しません"
		    );
		}
		
		if (bindingResult.hasErrors()) {
	        model.addAttribute("userRegisterForm", form);
	        return "user/register";
	    }
		
		User user = new User();
		user.setUserName(form.getUserName());
		user.setUserNameKana(form.getUserNameKana());
		user.setEmail(form.getEmail());
		user.setPhone(form.getPhone());
		user.setUserAddress(form.getUserAddress());
		user.setBuildingAddress(form.getBuildingAddress());
		user.setPassword(passwordEncoder.encode(form.getPassword()));
		
		userRepository.save(user);
	
		return "redirect:/login";
	}
	

	@GetMapping("/user/mypage")
	public String showMypage(
			@AuthenticationPrincipal UserDetails userDetails,
			Model model) {
		
		User user = userRepository.findByEmail(userDetails.getUsername())
		        .orElseThrow(() -> new IllegalStateException("ユーザーが存在しません"));
		model.addAttribute("user", user);
		return "user/mypage";
	}
}
