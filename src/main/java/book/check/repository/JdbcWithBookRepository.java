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

import book.check.domain.WithBook;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class JdbcWithBookRepository implements WithBookRepository{
	
	private final JdbcTemplate jdbcTemplate;
	
	public JdbcWithBookRepository(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private RowMapper<WithBook> withRowMapper() {
		return new RowMapper<WithBook>() {
			@Override
			public WithBook mapRow(ResultSet rs, int rowNum) throws SQLException {
				log.info("withRowMapper() 실행");
				WithBook withBook = new WithBook();
				withBook.setB_no(rs.getLong("b_no"));
				withBook.setB_title(rs.getString("b_title"));
				withBook.setB_memo(rs.getString("b_memo"));
				withBook.setB_area(rs.getString("b_area"));
				withBook.setB_pwd(rs.getString("b_pwd"));
				withBook.setB_date(rs.getDate("b_date"));
				return withBook;
			}
		};
	}
	
	@Override
	public WithBook saveWithBook(WithBook withbook) {
		log.info("save() 실행");
		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		jdbcInsert.withTableName("WITHBOOK").usingGeneratedKeyColumns("b_no");
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("b_title", withbook.getB_title());
		parameters.put("b_memo", withbook.getB_memo());
		parameters.put("b_area", withbook.getB_area());
		parameters.put("b_pwd", withbook.getB_pwd());
		parameters.put("b_date", withbook.getB_date());
		Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
		withbook.setB_no(key.longValue());
		return withbook;
	}

	@Override
	public Optional<WithBook> findByNo(Long w_no) {
		String sql = "SELECT * FROM WITHBOOK WHERE W_NO = ?";
		List<WithBook> result = jdbcTemplate.query(sql, withRowMapper(), w_no);
		return result.stream().findAny();
	}
	
	@Override
	public List<WithBook> findAll() {
		String sql = "SELECT * FROM WITHBOOK";
		List<WithBook> result = jdbcTemplate.query(sql, withRowMapper());
		return result;
	}

	@Override
	public List<WithBook> search(String title) {
		String sql = "SELECT * FROM WITHBOOK WHERE W_TITLE LIKE ?";
		String search = "%" + title + "%";
		List<WithBook> result = jdbcTemplate.query(sql, withRowMapper(), search);
		return result;
	}

	@Override
	public WithBook updateWithBook(Long w_no, WithBook withBook) {
		String sql = "UPDATE WITHBOOK SET W_TITLE = ?, W_MEMO = ?, W_AREA = ?, W_PWD = ? WHERE W_NO = ?";
		jdbcTemplate.update(sql, withBook.getB_title(), withBook.getB_memo(), withBook.getB_area()
				, withBook.getB_pwd(), w_no);
		withBook.setB_no(w_no);
		return findByNo(w_no).get();
	}

	@Override
	public void deleteWithBook(Long w_no) {
		String sql = "DELETE FROM WITHBOOK WHERE W_NO = ?";
		jdbcTemplate.update(sql, w_no);
	}

	
}
