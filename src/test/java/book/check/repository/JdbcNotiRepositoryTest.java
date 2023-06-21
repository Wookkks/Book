package book.check.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

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
		noti.setN_no(1L);
		noti.setN_title("제목");
		noti.setN_content("내용ㅋ");
		noti.setN_category("나눔책첵");
		
		Noti saveNoti = repository.saveNoti(noti);
		assertThat(noti.getN_no()).isEqualTo(saveNoti.getN_no());
	}
	
	@Test
	void 공지전체조회() {
		 List<Noti> before = repository.findAll();
		 
		 List<Noti> after = repository.findAll();
		 assertThat(after.size()).isEqualTo(before.size());
	}
	
	@Test
	void 공지수정() {
		Noti noti = new Noti("제목1", "내용1", "나눔책첵");
		Noti saveNoti = repository.saveNoti(noti); //아래에서 수정테스트를 위해 수정 전 데이터 저장
		
		Noti updateNoti = new Noti("수정후", "내용2", "분석책첵");
		Noti result = repository.updateNoti(noti.getN_no(), updateNoti);
		
		assertThat(repository.findByNo(noti.getN_no()).get().getN_title()).isEqualTo(updateNoti.getN_title());
	}
}
