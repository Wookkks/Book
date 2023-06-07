package book.check.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {
	
	// 공지 
	@GetMapping("/noti")
	public String noti(){
		log.info("[GET] noti 실행");
		return "user/u_noti";
	}
	
	// 공지 상세
	@GetMapping("/noti/deatil/{n_no}")
	public String notiDetail() {
		
		return "user/u_noti_detail";
	}
	
	// 가입신청 폼
	@GetMapping("/join")
	public String getJoin() {
		
		return "user/u_join";
	}
	
	// 가입신청
	@PostMapping("/join")
	public String postJoin() {
		
		return "redirect:/user/main";
	}
	
	// 활동후기
	@GetMapping("/how")
	public String Review() {
		
		return "user/u_how";
	}
	
	// 활동후기 폼
	@GetMapping("/add/how")
	public String getAddReview() {
		
		return "user/u_how_addForm";
	}
	
	// 활동후기 등록
	@PostMapping("add/how")
	public String postAddReview() {
		
		return "redirect:/user/u_how";
	}
	
	// 활동후기 수정 폼
	@GetMapping("edit/how{r_no}")
	public String getEditReview() {
		
		return "user/u_how_editForm";
	}
	
	// 활동 후기 수정
	@PostMapping("edit/how{r_no}")
	public String postEditReview() {
		
		return "redirect:/user/u_how";
	}
	
	// 활동후기 삭제
	@PostMapping("/how")
	public String deleteReview() {
		
		return "redirect:/user/u_how";
	}
	
	// 책 나눔
	@GetMapping("/with")
	public String with() {
		
		return "user/u_with_book";
	}
	
	// 책 나눔 등록 폼
	@GetMapping("/add/with")
	public String getAddWith() {
		
		return "user/u_with_book_addForm";
	}
	
	// 책 나눔 등록
	@PostMapping("/add/with")
	public String postAddWith() {
		
		return "redirect:/user/u_with_book";
	}
	
	// 책 나눔 수정 폼 
	@GetMapping("/edit/with{w_no}")
	public String getWithEdit() {
		
		return "user/u_with_book_editForm";
	}
	
	// 책 나눔 수정
	@PostMapping("/edit/with{w_no}")
	public String postWithEdit() {
		
		return "redirect:/user/u_with_book";
	}
	
	// 책 나눔 신청 폼
	@GetMapping("/apply")
	public String getApply() {
		
		return "user/u_share_addForm";
	}
	
	// 책 나눔 신청
	@PostMapping("/apply")
	public String postApply() {
		
		return "redirect:/user/u_share";
	}
	
	// 분석
	@GetMapping("/chart")
	public String chart() {
		
		return "/user/u_chart";
	}
	
}
