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

import book.check.domain.Review;

@Repository
public class JdbcReviewRepository implements ReviewRepository{
	
	private final JdbcTemplate jdbcTemplate;
	
	public JdbcReviewRepository(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private RowMapper<Review> reviewRowMapper(){
		return new RowMapper<Review>() {
			@Override
			public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
				Review review = new Review();
				review.setR_no(rs.getLong("r_no"));
				review.setR_name(rs.getString("r_name"));
				review.setR_content(rs.getString("r_content"));
				review.setR_date(rs.getDate("r_date"));
				return review;
			}
		};
	}

	@Override
	public Review saveReview(Review review) {
		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		jdbcInsert.withTableName("REVIEW").usingGeneratedKeyColumns("r_no");
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("r_name", review.getR_name());
		parameters.put("r_content", review.getR_content());
		parameters.put("r_date", review.getR_date());
		Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
		review.setR_no(key.longValue());
		return review;
	}

	@Override
	public List<Review> findAll() {
		String sql = "SELECT * FROM REVIEW";
		return jdbcTemplate.query(sql, reviewRowMapper());
	}
	
	@Override
	public Optional<Review> findByNo(Long r_no) {
		String sql = "SELECT * FROM REVIEW WHERE R_NO = ?";
		List<Review> result =jdbcTemplate.query(sql, reviewRowMapper(), r_no);
		return result.stream().findAny();
	}

	@Override
	public Review updateReview(Long r_no, Review review) {
		String sql = "UPDATE REVIEW SET R_NAME = ? R_CONTENT = ? WHERE R_NO = ?";
		jdbcTemplate.update(sql, review.getR_name(), review.getR_content(), r_no);
		review.setR_no(r_no);
		return findByNo(r_no).get();
	}
	@Override
	public void deleteReview(Long r_no) {
		String sql = "DELETE FROM REVIEW WHERE R_NO = ?";
		jdbcTemplate.update(sql, r_no);
	}
}
