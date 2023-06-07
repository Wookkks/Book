package book.check.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import book.check.domain.Noti;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class JdbcNotiRepository implements NotiRepository {
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcNotiRepository(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private RowMapper<Noti> notiRowMapper() {
		return new RowMapper<Noti>() {			// 이 라인 이해가 안 됨 return new ~~ ?
				
			@Override
			public Noti mapRow(ResultSet rs, int rowNum) throws SQLException { 
				//ResultSet에 값 받아서 Noti객체에 저장함. rowNum숫자만큼 반복
				// TODO Auto-generated method stub
				Noti noti = new Noti(); //db에서 noti 하나씩 추출해옴 <-아래코드 이용
				noti.setN_no(rs.getLong("n_no"));
				noti.setN_title(rs.getString("n_title"));
				noti.setN_content(rs.getString("n_content"));
				noti.setN_date(rs.getDate("n_date")); //date도 가져와야함
				return noti; //noti반환
			} //이거 안닫아서 오류
			
		};

	}
	//공지등록saveNoti
	//처음에 override안된 이유는 처음에 implements를 안했었음
	@Override
	public Noti saveNoti(Noti noti) {
		// TODO Auto-generated method stub
		SimpleJdbcInsert jdbcinsert = new SimpleJdbcInsert(jdbcTemplate)
				.usingColumns("noti")
				.usingGeneratedKeyColumns("n_no");
		
		//키(row),값 받아옴  
		Map<String, Object> parameters = new HashMap<String, Object>();  // 이 부분 이해 아직
		//Map은 인터페이스라서 객체생성못하고 선언만 가능
		//자식인 HashMap으로 객체생성하고 부모Map의 메서드를 상속받음
		parameters.put("n_title", noti.getN_title());//컬럼명key과 실제값value 짝으로 관리-row로 들어감
		parameters.put("n_content", noti.getN_content());
		parameters.put("n_date", noti.getN_date());
		
		Number key = jdbcinsert.executeAndReturnKey(parameters).longValue();
		return noti;
	}

	@Override
	public Optional<Noti> findByNo(Long notiNo) {
		// TODO Auto-generated method stub
		List<Noti> result = jdbcTemplate.query("select * from NOTI where n_no = ?", notiRowMapper(), notiNo);
		return result.stream().findAny();
	}

	@Override
	public List<Noti> findByTitle(String notiTitle) {
		// TODO Auto-generated method stub
		List<Noti> result = jdbcTemplate.query("select * from NOTI where n_title like ?", notiRowMapper(), notiTitle);
		String title = "%" + notiTitle + "%";  // % 이건 와일드카드?
		return result;
	}

	@Override
	public List<Noti> findAll() {
		// TODO Auto-generated method stub
//		List<Noti> result = jdbcTemplate.query("select * from NOTI", notiRowMapper()); //notiRowMapper뒤에 소괄호 안해서 틀렸음
//		return result;
		//혹은
		return jdbcTemplate.query("select * from NOTI", notiRowMapper());//return type List니까 바로 return
	}

	@Override
	public Noti updateNoti(Long n_no, Noti updateNoti) {
		// TODO Auto-generated method stub
		//길어서 sql문에 저장
		String sql = "update NOTI set n_title, n_content, n_date, where n_no = ?";
		int result = jdbcTemplate.update(sql, updateNoti.getN_title(), updateNoti.getN_content(), updateNoti.getN_date(), updateNoti);
		return result;?? 
//				여기서부터 작성하면 됨
	}

	@Override
	public void deleteNoti(Long n_no) {
		// TODO Auto-generated method stub
		
	}

			
}
	
		//공지 번호로 조회 findByNo
				//공지 제목으로 조회 findByTitle
				//공지수정updateNoti
				//공지삭제deleteNoti
				//공지 전체목록 findAll
		

		
