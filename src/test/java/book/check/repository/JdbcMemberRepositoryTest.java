package book.check.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import book.check.domain.Member;

@SpringBootTest
class JdbcMemberRepositoryTest {

	@Autowired
	MemberRepository repository;

	@Test
	void 멤버가입() {
		Member member = new Member();
		member.setM_name("길동");
		member.setM_phone("01011112222");
		member.setM_birth(new Date(2023, 06, 23));

		Member saveMember = repository.saveMember(member);
		assertThat(member.getM_no()).isEqualTo(saveMember.getM_no());

	}

}
