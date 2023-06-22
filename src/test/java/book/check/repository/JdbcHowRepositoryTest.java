package book.check.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import book.check.domain.How;

@SpringBootTest
class JdbcHowRepositoryTest {

	@Autowired JdbcHowRepository repository;
	
	@Test
	void 어때책첵등록() {
			How how = new How();
			how.setH_month("2022-06");
			how.setH_week("둘째주");
			how.setH_title("제모옥");
			how.setH_content("내요옹");
			How saveHow = repository.saveHow(how);
			assertThat(how.getH_no()).isEqualTo(saveHow.getH_no());
	}

}