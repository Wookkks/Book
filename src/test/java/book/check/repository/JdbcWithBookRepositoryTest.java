package book.check.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import book.check.domain.WithBook;

@SpringBootTest
class JdbcWithBookRepositoryTest {

	@Autowired JdbcWithBookRepository repository;
	
	@Test
	void save() {
		WithBook withBook = new WithBook();
		withBook.setW_title("title");
		withBook.setW_memo("memo");
		withBook.setW_area("area");
		withBook.setW_pwd("pwd");
		
		WithBook saveWithBook = repository.saveWithBook(withBook);
		assertThat(withBook.getW_no()).isEqualTo(saveWithBook.getW_no());
	}

}
