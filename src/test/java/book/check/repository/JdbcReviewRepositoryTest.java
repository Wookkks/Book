package book.check.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import book.check.domain.Review;

@SpringBootTest
class JdbcReviewRepositoryTest {
	
	@Autowired
	JdbcReviewRepository repository;
	
	@Test
	void saveReview() {
		Review review = new Review("name", "content");
		Review saveReview = repository.saveReview(review);
		assertThat(review.getR_no()).isEqualTo(saveReview.getR_no());
	}

}
