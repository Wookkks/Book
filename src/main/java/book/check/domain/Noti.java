package book.check.domain;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter 
@NoArgsConstructor
public class Noti {
	private Long n_no;
	private String n_title;
	private String n_content;
	private Date n_date;
	
	public Noti(String n_title, String n_content, Date n_date) {
		this.n_title = n_title;
		this.n_content = n_content;
		this.n_date = n_date;
	}
}
