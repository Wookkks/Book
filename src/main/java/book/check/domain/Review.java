package book.check.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Review {
	private Long r_no;
	private String r_name;
	private String r_content;
	
	public Review() {}

	public Review(String r_name, String r_content) {
		this.r_name = r_name;
		this.r_content = r_content;
	}
	
}
