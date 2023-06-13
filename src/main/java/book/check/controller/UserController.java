package book.check.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import book.check.domain.Member;
import book.check.domain.Noti;
import book.check.domain.Review;
import book.check.service.ApplyService;
import book.check.service.MemberService;
import book.check.service.NotiService;
import book.check.service.ReviewService;
import book.check.service.WithBookService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {
	
	private final MemberService memberService;
	private final ReviewService reviewService;
	private final ApplyService applyService;
	private final WithBookService withBookService;
	private final NotiService notiService;
	
	public UserController(MemberService memberService, ReviewService reviewService, ApplyService applyService, 
			WithBookService withbookService, NotiService notiService) {
		this.memberService = memberService;
		this.reviewService = reviewService;
		this.applyService = applyService;
		this.withBookService = withbookService;
		this.notiService = notiService;
	}
	
	// 메인
	@GetMapping("/main")
	public String main() {
		log.info("[GET] main 실행");
		return "user/main";
	}
	
	// 공지 
	@GetMapping("/noti")
	public String noti(){
		log.info("[GET] noti 실행");
		return "user/u_noti";
	}
	
	// 공지 상세
	@GetMapping("/noti/deatil/{n_no}")
	public String notiDetail() {
		log.info("[GET] notiDetail 실행");
		return "user/u_noti_detail";
	}
	
	// 가입신청 폼
	@GetMapping("/join")
	public String getJoin() {
		log.info("[GET] getJoin 실행");
		return "user/u_join";
	}
	
	// 가입신청
	@PostMapping("/join")
	public String postJoin(Member member) {
		log.info("[postJoin] 실행");
		memberService.saveMember(member);
		return "redirect:/user/main";
	}
	
	// 활동후기
	@GetMapping("/how")
	public String getReview(Model model) {
		log.info("[getReview] 실행");
		List<Review> review = reviewService.findAll();
		List<Noti> noti = notiService.findAll();
		model.addAttribute("review", review);
		model.addAttribute("noti", noti);
		return "user/u_how";
	}
	
	// 활동후기 등록
	@PostMapping("/how")
	public String postReview(Review review) {
		log.info("[postReview] 실행");
		reviewService.saveReview(review);
		return "redirect:/user/how";
	}
	
	// 책 나눔
	@GetMapping("/share")
	public String with() {
		log.info("[GET] with 실행");
		return "user/u_share_book";
	}
	
	// 책 나눔 등록 폼
	@GetMapping("/share/add")
	public String getWithAdd() {
		log.info("[GET] getWithAdd 실행");
		return "user/u_share_addForm";
	}
	
	// 책 나눔 등록
	@PostMapping("/share/add")
	public String postWithAdd() {
		log.info("[GET] postWithAdd 실행");
		return "redirect:/user/u_share_book";
	}
	
	// 분석
	@GetMapping("/chart")
	public String chart() {
		log.info("[GET] chart 실행");
		return "/user/u_chart";
	}
	/*
	 * // 책 나눔 수정 폼
	 * 
	 * @GetMapping("/share/edit{w_no}") public String getWithEdit() {
	 * 
	 * return "user/u_share_editForm"; }
	 * 
	 * // 책 나눔 수정
	 * 
	 * @PostMapping("/share/edit{w_no}") public String postWithEdit() {
	 * 
	 * return "redirect:/user/u_share_book"; }
	 * 
	 * // 책 나눔 신청 폼 (핸드폰번호 가운데 4자리)
	 * 
	 * @GetMapping("/apply") public String getApply() {
	 * 
	 * return "user/u_share_addForm"; }
	 * 
	 * // 책 나눔 신청
	 * 
	 * @PostMapping("/apply") public String postApply() {
	 * 
	 * return "redirect:/user/u_share"; }
	 */
	
	
}
