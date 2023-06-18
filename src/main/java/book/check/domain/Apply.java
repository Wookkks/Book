package book.check.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Apply {
	private Long a_no;
	private Long w_no;
	private String a_name;
	private String a_phone;
	
	public Apply() {}

	public Apply(Long w_no, String a_name, String a_phone) {
		this.w_no = w_no;
		this.a_name = a_name;
		this.a_phone = a_phone;
	}
}
