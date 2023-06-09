package book.check.domain;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
public class Noti {
	private Long n_no;
	private String n_title;
	private String n_content;
	private Date n_date;
	private String n_category;
	
	public Noti () {};
	
	public Noti(String n_title, String n_content, String n_category) {
		this.n_title = n_title;
		this.n_content = n_content;
		this.n_category = n_category;
	}
}