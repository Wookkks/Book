package book.check.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import book.check.domain.Noti;

@SpringBootTest
class JdbcNotiRepositoryTest {
	
	@Autowired JdbcNotiRepository repository;

	@Test
	void 공지저장() {
		Noti noti = new Noti();
		noti.setN_no(1111L);
		noti.setN_title("title");
		noti.setN_content("content");
		noti.setN_category("나눔책첵");
		
		Noti saveNoti = repository.saveNoti(noti);
		assertThat(noti.getN_no()).isEqualTo(saveNoti.getN_no());
	
//	//findByNo
//	@Test
//	
//	//findByTitle
//	@Test
//	
//	//findAll
//	@Test
//	
//	//updateNoti
//	@Test
//	
//	//deleteNoti
//	@Test
	

}
}

