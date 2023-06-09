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
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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
		SimpleJdbcInsert jdbcinsert = new SimpleJdbcInsert(jdbcTemplate);
		jdbcinsert.withTableName("noti").usingGeneratedKeyColumns("n_no");
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("n_title", noti.getN_title());
		parameters.put("n_content", noti.getN_content());
		Number key = jdbcinsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
		noti.setN_no(key.longValue());
		return noti;
	}

	@Override
	public Optional<Noti> findByNo(Long n_no) {
		List<Noti> result = jdbcTemplate.query("SELECT * FROM NOTI WHERE N_NO = ?", notiRowMapper(), n_no);
		return result.stream().findAny();
	}

	@Override
	public Optional<Noti> findByTitle(String n_title) {
		String sql = "SELECT * FROM NOTI WHERE N_TITLE LIKE ?";
		String title = "%" + n_title + "%";
		List<Noti> result = jdbcTemplate.query(sql, notiRowMapper(), title);
		return result.stream().findAny();
	}

	@Override
	public List<Noti> findAll() {
		List<Noti> result = jdbcTemplate.query("SELECT * FROM NOTI", notiRowMapper()); 
		return result;
	}

	@Override
	public Noti updateNoti(Long n_no, Noti updateNoti) {
		String sql = "UPDATE NOTI SET N_TITLE, N_CONTENT, WHERE N_NO = ?";
		jdbcTemplate.update(sql, updateNoti.getN_title(), updateNoti.getN_content(), n_no);
		updateNoti.setN_no(n_no);
		return findByNo(n_no).get();
	}

	@Override
	public void deleteNoti(Long n_no) {
		jdbcTemplate.update("DELETE FROM NOTI WHERE N_NO = ?", n_no);
	}
}