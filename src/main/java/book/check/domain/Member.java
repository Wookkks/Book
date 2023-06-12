package book.check.domain;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
public class Member {
	private Long m_no;
	private String m_name;
	private String m_phone;
	private Date m_birth;
	
	public Member() {};
	
	public Member(String m_name, String m_phone, Date m_birth) {
		this.m_name = m_name;
		this.m_phone = m_phone;
		this.m_birth = m_birth;
	}
}