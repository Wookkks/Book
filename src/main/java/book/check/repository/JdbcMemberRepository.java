package book.check.repository;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import book.check.domain.Member;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class JdbcMemberRepository implements MemberRepository {
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcMemberRepository(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

//	private RowMapper<Member> memberRowMapper() {
//		return new RowMapper<Member>() {
//			@Override
//			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Member member = new Member();
//				member.setM_no(rs.getLong("m_no"));
//				member.setM_name(rs.getString("m_name"));
//				member.setM_address(rs.getString("m_phone"));
//				member.setM_address(rs.getString("m_address"));
//				return member;
//			}
//		};
//	}

	@Override
	public Member saveMember(Member member) {
		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		jdbcInsert.withTableName("MEMBER").usingGeneratedKeyColumns("m_no");
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("m_name", member.getM_name());
		parameters.put("m_phone", member.getM_phone());
		parameters.put("m_address", member.getM_address());
		Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
		member.setM_no(key.longValue());
		return member;
	}
}