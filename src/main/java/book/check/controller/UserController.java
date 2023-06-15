package book.check.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import book.check.domain.Apply;
import book.check.domain.Member;
import book.check.domain.Noti;
import book.check.domain.Review;
import book.check.domain.WithBook;
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
	public String main(Model model) {
		log.info("[GET] main 실행");
		List<Noti> noti = notiService.findAll();
		model.addAttribute("noti", noti);
		return "user/main";
	}
	
	// 공지 
	@GetMapping("/noti")
	public String noti(Model model){
		log.info("[GET] noti 실행");
		List<Noti> noti = notiService.findAll();
		model.addAttribute("noti", noti);
		return "user/u_noti";
	}
	
	// 공지 상세
	@GetMapping("/noti/{n_no}")
	public String notiDetail(@PathVariable Long n_no, Model model) {
		log.info("[GET] notiDetail 실행");
		Noti noti = notiService.findByNo(n_no).get();
		model.addAttribute("noti", noti);
		return "user/u_noti_detail";
	}
	
	// 가입신청 폼
	@GetMapping("/join")
	public String getJoin(Model model) {
		log.info("[GET] getJoin 실행");
		List<Noti> noti = notiService.findAll();
		model.addAttribute("noti", noti);
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
	public String with(Model model) {
		log.info("[GET] with 실행");
		List<WithBook> withBook = withBookService.findAll();
		List<Noti> noti = notiService.findAll();
		model.addAttribute("withBook", withBook);
		model.addAttribute("noti", noti);
		return "user/u_share_book";
	}
	
	// 책 나눔 등록 폼
	@GetMapping("/share/add")
	public String getWithAdd(Model model) {
		log.info("[GET] getWithAdd 실행");
		List<Noti> noti = notiService.findAll();
		model.addAttribute("noti", noti);
		return "user/u_share_addForm";
	}
	
	// 책 나눔 등록
	@PostMapping("/share/add")
	public String postWithAdd(WithBook withBook) {
		log.info("[POST] postWithAdd 실행");
		withBookService.saveWithBook(withBook);
		return "redirect:/user/share";
	}
	
	// 신청 현황 및 완료여부 폼
	@GetMapping("/share/cur")
	public String getCur(Model model) {
		log.info("[GET] Cur 실행");
		List<Apply> apply = applyService.findAll();
		List<Noti> noti = notiService.findAll();
		model.addAttribute("apply", apply);
		model.addAttribute("noti", noti);
		return "/user/u_share_current";
	}
	// 신청 현황 및 완료여부
	@PostMapping("/share/cur")
	public String postCur() {
		
		return "user/u_share_book";
	}
	// 분석
	@GetMapping("/chart")
	public String chart(Model model) {
		log.info("[GET] chart 실행");
		List<Noti> noti = notiService.findAll();
		model.addAttribute("noti", noti);
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
