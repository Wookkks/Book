package book.check.domain;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
public class Noti {
	private Long n_no;
	private String n_name;
	private String n_title;
	private String n_content;
	private Date n_date;
	
	public Noti () {};
	
	public Noti(String n_name, String n_title, String n_content) {
		this.n_name = n_name;
		this.n_title = n_title;
		this.n_content = n_content;
	}
}