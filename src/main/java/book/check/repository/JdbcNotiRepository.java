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
		return new RowMapper<Noti>() {
			@Override
			public Noti mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Noti noti = new Noti();
				noti.setN_no(rs.getLong("n_no"));
				noti.setN_title(rs.getString("n_title"));
				noti.setN_content(rs.getString("n_content"));
				noti.setN_date(rs.getDate("n_date"));
				return noti;
			}
		};
	}

	@Override
	public Noti saveNoti(Noti noti) {
		SimpleJdbcInsert jdbcinsert = new SimpleJdbcInsert(jdbcTemplate).usingColumns("noti")
				.usingGeneratedKeyColumns("n_no");
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("n_title", noti.getN_title());
		parameters.put("n_content", noti.getN_content());
		parameters.put("n_date", noti.getN_date());

		Number key = jdbcinsert.executeAndReturnKey(parameters).longValue();
		return noti;
	}
	
	@Override
	public Optional<Noti> findByNo(Long notiNo) {
		List<Noti> result = jdbcTemplate.query("select * from NOTI where n_no = ?", notiRowMapper(), notiNo);
		return result.stream().findAny();
	}

	@Override
	public List<Noti> findByTitle(String notiTitle) {
		List<Noti> result = jdbcTemplate.query("select * from NOTI where n_title like ?", notiRowMapper(), notiTitle);
		String title = "%" + notiTitle + "%";
		return result;
	}

	@Override
	public List<Noti> findAll() {
		return jdbcTemplate.query("select * from NOTI", notiRowMapper());
	}

	@Override
	public Noti updateNoti(Long n_no, Noti updateNoti) {
		String sql = "update NOTI set n_title, n_content, n_date, where n_no = ?";
		int result = jdbcTemplate.update(sql, updateNoti.getN_title(), updateNoti.getN_content(),
				updateNoti.getN_date(), n_no);
		return findByNo(n_no).get();
	}

	@Override
	public void deleteNoti(Long n_no) {
		jdbcTemplate.update("delete from NOTI where n_no = ?", n_no);
	}
}