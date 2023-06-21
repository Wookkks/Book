package book.check.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class How {
	private Long h_no;
	private String h_month;
	private String h_week;
	private String h_title;
	private String h_content;
	
	public How() {}
	
	public How(String h_month, String h_week, String h_title, String h_content) {
		this.h_month = h_month;
		this.h_week = h_week;
		this.h_title = h_title;
		this.h_content = h_content;
	}
}

