package book.check.domain;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class WithBook {
	private Long b_no;
	private String b_title;
	private String b_memo;
	private String b_area;
	private String b_pwd;
	private Date b_date;
	
	public WithBook() {}
	
	public WithBook(String b_title, String b_memo, String b_area, String b_pwd) {
		this.b_title = b_title;
		this.b_memo = b_memo;
		this.b_area = b_area;
		this.b_pwd = b_pwd;
	}
}
