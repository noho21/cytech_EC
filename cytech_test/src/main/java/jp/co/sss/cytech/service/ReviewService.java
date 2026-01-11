package jp.co.sss.cytech.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import jp.co.sss.cytech.entity.Product;
import jp.co.sss.cytech.entity.Review;
import jp.co.sss.cytech.entity.User;
import jp.co.sss.cytech.repository.ProductRepository;
import jp.co.sss.cytech.repository.ReviewRepository;

@Transactional
@Service
public class ReviewService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;

	public void postReview(
			User user,
		    Integer productId,
		    Integer rating,
		    String comment,
		    String dummyUserName,
		    MultipartFile reviewImage
	    ) {
	        
	        Product product = productRepository
	        		.findById(productId)
	        		.orElseThrow();

	        String imagePath = saveImage(reviewImage);

	        Review review = new Review();
	        review.setUser(user);
	        review.setProduct(product);
	        review.setDummyUserName(
	            StringUtils.hasText(dummyUserName)
	                ? dummyUserName
	                : user.getUserName()
	        );
	        review.setRating(rating);
	        review.setComment(comment);
	        review.setReviewImgPath(imagePath);

	        reviewRepository.save(review);
	    }
	
	private String saveImage(MultipartFile image) {
	    if (image == null || image.isEmpty()) {
	        return null;
	    }

	    String fileName =
	        UUID.randomUUID() + "_" + image.getOriginalFilename();

	    Path savePath = Paths.get(
	        "src/main/resources/static/img/reviews",
	        fileName
	    );

	    try {
	        Files.copy(
	            image.getInputStream(),
	            savePath,
	            StandardCopyOption.REPLACE_EXISTING
	        );
	    } catch (IOException e) {
	        throw new RuntimeException("画像の保存に失敗しました", e);
	    }

	    return "/img/reviews/" + fileName;
	}

}
