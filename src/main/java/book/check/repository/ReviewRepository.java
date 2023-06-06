package book.check.repository;

import java.util.List;

import book.check.domain.Review;

public interface ReviewRepository {
	
	// 후기 등록
	Review saveReview(Review review);
	
	// 후기 목록
	List<Review> findAll();
	
	// 후기 삭제
	void deleteReview(Long r_no);
}
