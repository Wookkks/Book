package book.check.repository;

import java.util.List;
import java.util.Optional;

import book.check.domain.Review;

public interface ReviewRepository {
	
	// 후기 등록
	Review saveReview(Review review);
	
	// 후기글 번호로 조회
	Optional<Review> findByNo(Long r_no);
	
	// 후기 목록
	List<Review> findAll();
	
	// 후기 수정
	Review updateReview(Long r_no, Review review);
	
	// 후기 삭제
	void deleteReview(Long r_no);
}
