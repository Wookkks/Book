package book.check.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import book.check.domain.Noti;
import book.check.service.NotiService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/manager")
public class ManagerController {
	
	private final NotiService notiService;

	public ManagerController(NotiService notiService) {
		this.notiService = notiService;
	}
	
	//공지사항
	@GetMapping("/noti")
	public String noti (Model model) {
		log.info("[GET] noti 실행");
		List<Noti> noti = notiService.findAll();
		model.addAttribute(noti);
		return "manager/m_noti";
	}
		
	//공지사항 상세 X
	//공지사항 페이지에 목록들 전체 뜨고 각각 수정/삭제버튼 있음
	
	//공지사항 등록 폼
	@GetMapping("add/noti")
	public String notiForm() {
		log.info("[GET] notiForm 실행");
		return "manager/m_noti_addForm";
	}
	
	//공지사항 등록
	@PostMapping("add/noti")
	public String notiCreate(@ModelAttribute Noti noti, Model model, RedirectAttributes redirectAttributes) {
		
		return "redirect:/manager/m_noti";
	}
	
//	// 공지사항 글 작성 후 저장 // 삭제예정
//	@PostMapping("/create")
//	public String createNoti(@ModelAttribute Noti noti,
//			RedirectAttributes redirectAttributes, Model model) {
//
//		notiService.create(noti);
//		redirectAttributes.addAttribute("notiId", noti.getNotiId());
//		return "redirect:/noti/{notiId}";
//	}
	
	//공지사항 수정 폼
	@GetMapping("edit/noti{n_no}")
	public String notiEditForm() {
		
		return "manager/m_noti_editForm";
	}
	
	//공지사항 수정
	@PostMapping("/edit/noti{n_no}")
	public String notiEdit() {
		
		return "redirect:/manager/m_noti";
	}
	
	//공지사항 삭제
	@PostMapping("noti")
	public String notiDelete() {
	
		return "manager/m_noti";
	}
	
}