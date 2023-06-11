package book.check.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
public class Member {
	private Long m_no;
	private String m_name;
	private String m_phone;
	private String m_address;
	
	public Member() {};
	
	public Member(String m_name, String m_phone, String m_address) {
		this.m_name = m_name;
		this.m_phone = m_phone;
		this.m_address = m_address;
	}
}