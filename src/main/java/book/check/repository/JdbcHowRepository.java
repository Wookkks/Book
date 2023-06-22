package book.check.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import book.check.domain.How;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class JdbcHowRepository implements HowRepository{
	
	private final JdbcTemplate jdbcTemplate;
	
	public JdbcHowRepository(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private RowMapper<How> howRoeMapper(){
		return new RowMapper<How>() {
			@Override
			public How mapRow(ResultSet rs, int rowNum) throws SQLException {
				How how = new How();
				how.setH_no(rs.getLong("h_no"));
				how.setH_month(rs.getString("h_month"));
				how.setH_week(rs.getString("h_week"));
				how.setH_title(rs.getString("h_title"));
				how.setH_content(rs.getString("h_content"));
				return how;
			}

		};
	}

	@Override
	public How saveHow(How how) {
		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		jdbcInsert.withTableName("HOW").usingGeneratedKeyColumns("h_no");
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("h_month", how.getH_month());
		parameters.put("h_week", how.getH_week());
		parameters.put("h_title", how.getH_title());
		parameters.put("h_content", how.getH_content());
		Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
		how.setH_no(key.longValue());
		return how;
	}
	
	@Override
	public Optional<How> findByNo(Long h_no) {
		String sql = "SELECT * FROM HOW WHERE H_NO = ?";
		List<How> result = jdbcTemplate.query(sql, howRoeMapper(), h_no);
		return result.stream().findAny();
	}

	@Override
	public How updateHow(Long h_no, How how) {
		log.info("updateHow 실행");
		String sql = "UPDATE HOW SET H_MONTH = ?, H_WEEK = ?, H_TITLE = ?, H_CONTENT = ? WHERE H_NO = ?";
		jdbcTemplate.update(sql, how.getH_month(), how.getH_week(), how.getH_title(), how.getH_content(), h_no);
		how.setH_no(h_no);
		log.info("updateHow 실행 끝");
		return findByNo(h_no).get();
	}

	@Override
	public List<How> findAll(String h_month) {
		String sql = "SELECT * FROM HOW WHERE H_MONTH LIKE ?";
		List<How> result = jdbcTemplate.query(sql, howRoeMapper(), h_month);
		return result;
	}
}
