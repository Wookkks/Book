package book.check.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import book.check.domain.Apply;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class JdbcApplyRepository implements ApplyRepository{
	
	private final JdbcTemplate jdbcTemplate;
	
	public JdbcApplyRepository(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private RowMapper<Apply> applyRowMapper(){
		return new RowMapper<Apply>() {
			@Override
			public Apply mapRow(ResultSet rs, int rowNum) throws SQLException {
				Apply apply = new Apply();
				apply.setA_name(rs.getString("a_name"));
				apply.setA_phone(rs.getString("a_phone"));
				return apply;
			}
		};
	}

	@Override
	public Apply saveApply(Apply apply) {
		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		jdbcInsert.withTableName("APPLY").usingGeneratedKeyColumns("a_no");
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("a_name", apply.getA_name());
		parameters.put("a_phone", apply.getA_phone());
		Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
		apply.setA_no(key.longValue());
		return apply;
	}

	@Override
	public List<Apply> findAll() {
		String sql = "SELECT * FROM APPLY";
		List<Apply> result = jdbcTemplate.query(sql, applyRowMapper());
		return result;
	}
	
}
