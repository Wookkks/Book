package book.check.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import book.check.domain.Apply;
import book.check.domain.How;
import book.check.domain.Member;
import book.check.domain.Noti;
import book.check.domain.Review;
import book.check.domain.WithBook;
import book.check.service.ApplyService;
import book.check.service.HowService;
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
	private final HowService howService;
	
	public UserController(MemberService memberService, ReviewService reviewService, ApplyService applyService, 
			WithBookService withbookService, NotiService notiService, HowService howService) {
		this.memberService = memberService;
		this.reviewService = reviewService;
		this.applyService = applyService;
		this.withBookService = withbookService;
		this.notiService = notiService;
		this.howService = howService;
	}
	
	// 메인
	@GetMapping("/main")
	public String main(Model model) {
		List<Noti> noti = notiService.findAll();
		model.addAttribute("noti", noti);
		return "user/main";
	}
	// 개인정보처리방침
	@GetMapping("/privacy")
	public String privacy(Model model) {
		List<Noti> noti = notiService.findAll();
		model.addAttribute("noti", noti);
		return "user/u_privacy";
	}
	// 이용약관
	@GetMapping("/terms")
	public String terms(Model model) {
		List<Noti> noti = notiService.findAll();
		model.addAttribute("noti", noti);
		return "user/u_terms";
	}
	// 공지 
	@GetMapping("/noti")
	public String noti(Model model){
		List<Noti> noti = notiService.findAll();
		model.addAttribute("noti", noti);
		return "user/u_noti";
	}
	
	// 공지 상세
	@GetMapping("/noti/{n_no}")
	public String notiDetail(@PathVariable Long n_no, Model model) {
		Noti noti = notiService.findByNo(n_no).get();
		List<Noti> notiAll = notiService.findAll(); 
		model.addAttribute("noti", noti);
		model.addAttribute("notiAll", notiAll);
		return "user/u_noti_detail";
	}
	
	// 가입신청 폼
	@GetMapping("/join")
	public String getJoin(Model model) {
		List<Noti> noti = notiService.findAll();
		model.addAttribute("noti", noti);
		return "user/u_join";
	}
	
	// 가입신청
	@PostMapping("/join")
	public String postJoin(Member member) {
		memberService.saveMember(member);
		return "redirect:/user/main";
	}
	
	// 활동후기
	@GetMapping("/how")
	public String getReview(Model model) {
		List<Review> review = reviewService.findAll();
		List<Noti> noti = notiService.findAll();
		model.addAttribute("review", review);
		model.addAttribute("noti", noti);
		return "user/u_how";
	}
	
	// 월별 활동후기
	@GetMapping("/how/{h_month}")
	public String getMonthHow(@PathVariable String h_month, Model model) {
		log.info("[GET] monthHow 실행");
		List<How> how = howService.findAll(h_month);
		List<Review> review = reviewService.findAll();
		List<Noti> noti = notiService.findAll();
		model.addAttribute("how", how);
		model.addAttribute("review", review);
		model.addAttribute("noti", noti);
		return "user/u_how";
	}
	
	// 활동후기 등록
	@PostMapping("/how")
	public String postReview(Review review) {
		reviewService.saveReview(review);
		return "redirect:/user/how";
	}
	
	// 책 나눔
	@GetMapping("/share")
	public String getWith(Model model) {
		List<WithBook> withBook = withBookService.findAll();
		List<Noti> noti = notiService.findAll();
		model.addAttribute("withBook", withBook);
		model.addAttribute("noti", noti);
		return "user/u_share_book";
	}
	
	@PostMapping("/share")
	public String postWith(@ModelAttribute WithBook withBook, @RequestParam Long w_no, @RequestParam String w_pwd) {
		String userPwd = withBookService.findByNo(w_no).get().getW_pwd();
		if(w_pwd.equals(userPwd)) {
			withBookService.updateYN(w_no, withBook);
			return "redirect:/user/share";
		}else {
			return "redirect:/user/alert";
		}
	}
	@GetMapping("/alert")
	public String alert(Model model) {
		String message = "비밀번호가 일치하지 않습니다";
		model.addAttribute("message", message);
		
		return "user/u_alert";
	}
	// 책 나눔 등록 폼
	@GetMapping("/share/add")
	public String getWithAdd(Model model) {
		List<Noti> noti = notiService.findAll();
		model.addAttribute("noti", noti);
		return "user/u_share_addForm";
	}
	
	// 책 나눔 등록
	@PostMapping("/share/add")
	public String postWithAdd(WithBook withBook) {
		withBookService.saveWithBook(withBook);
		return "redirect:/user/share";
	}
	
	// 신청 현황 및 완료여부 폼
	@GetMapping("/share/cur/{w_no}")
	public String getCur(Model model, @PathVariable Long w_no) {
		List<Apply> apply = applyService.findAll(w_no);
		List<Noti> noti = notiService.findAll();
		WithBook withBook = withBookService.findByNo(w_no).get();
		model.addAttribute("apply", apply);
		model.addAttribute("noti", noti);
		model.addAttribute("withBook", withBook);
		return "user/u_share_current";
	}
	
//	// 신청 현황 및 완료여부
//	@PostMapping("/share/cur/")
//	public String postCur(@RequestParam String w_pwd, Long w_no) {
//		String userPwd = withBookService.pwd(w_pwd).get().getW_pwd();
//		if(userPwd == w_pwd) {
//			return "user/u_share_current";
//		}
//		return "user/u_share_book";
//	}
	// 분석
	@GetMapping("/chart")
	public String chart(Model model) {
		List<Noti> noti = notiService.findAll();
		model.addAttribute("noti", noti);
		return "user/u_chart";
	}
	
	  // 책 나눔 수정 폼	  
	  @GetMapping("/share/edit{w_no}") 
	  public String getWithEdit() {
	  
	  return "user/u_share_editForm";
	  }
	  
	  // 책 나눔 수정	  
	  @PostMapping("/share/edit{w_no}")
	  public String postWithEdit() {
	  
	  return "redirect:/user/u_share_book";
	  }
	  
	  // 책 나눔 신청 폼 (핸드폰번호 가운데 4자리)	  
	  @GetMapping("/apply/{w_no}")
	  public String getApply() {
	  log.info("[GET] apply 실행");
	  return "user/u_apply_addForm";
	  }
	  
	  // 책 나눔 신청
	  @PostMapping("/apply")
	  public String postApply(Apply apply) {
	  log.info("[POST] apply 실행");
	  applyService.saveApply(apply);
	  return "redirect:/user/share";
	  }
}
