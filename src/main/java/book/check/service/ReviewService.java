package book.check.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import book.check.domain.Review;
import book.check.repository.ReviewRepository;

@Service
public class ReviewService {
	
	@Autowired
	ReviewRepository reviewRepository;
	
	public Review saveReview(Review review) {
		return reviewRepository.saveReview(review);
	}
	
	public List<Review> findAll() {
		return reviewRepository.findAll();
	}
}
