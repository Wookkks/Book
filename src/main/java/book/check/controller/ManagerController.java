package book.check.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
		model.addAttribute("noti", noti);
		return "manager/m_noti";
	}
	
	// 비번치고 관리자페이지들어갈 때 @PostMapping("/noti")인데 delete와 겹침-> delete주석처리
	//공지사항 삭제
//	@PostMapping("/noti")
//	public String notiDelete(Long n_no) {
//		log.info("[POST] notiDelete 실행");
//		notiService.deleteNoti(n_no);
//		return "manager/m_noti";
//	}
	
	//공지사항 등록 폼
	@GetMapping("/noti/add")
	public String notiForm(Model model) {
		log.info("[GET] notiForm 실행");
		List<Noti> noti = notiService.findAll();
		model.addAttribute("notiAdd", noti);
		return "manager/m_noti_addForm";
	}
	
	//공지사항 등록 	
	@PostMapping("/noti/add")
	public String notiCreate(@ModelAttribute Noti noti, Model model) {
		log.info("[POST] notiCreate() 실행");
		notiService.saveNoti(noti);
		return "redirect:/manager/noti";
	}

	// 공지사항 상세 //💎오른쪽 aside공지 반복이 안 됨. 해당 공지만 뜸💎
	@GetMapping("/noti/detail{n_no}")
	public String notiDetail(@PathVariable Long n_no, Model model) {
		log.info("[GET] notiDetail() 실행");
		model.addAttribute("noti", notiService.findByNo(n_no).get());
		return "manager/m_noti_detail";
	}
	
	//공지사항 수정 폼 //💎오른쪽 aside공지 반복이 안 됨. 해당 공지만 뜸💎
	@GetMapping("/noti/edit{n_no}")
	public String notiEditForm(@PathVariable Long n_no, Model model) {
		log.info("[GET] notiEditForm() 실행");
		List<Noti> noti = notiService.findAll();
		model.addAttribute("noti", notiService.findByNo(n_no).get());
		return "manager/m_noti_editForm";
	}
	
	//공지사항 수정  
	@PostMapping("/noti/edit{n_no}")
	public String notiEdit(@PathVariable Long n_no, @ModelAttribute Noti noti, Model model) {//, RedirectAttributes redirect
		log.info("[POST] notiEdit() 실행");
		model.addAttribute("noti", notiService.updateNoti(n_no, noti));
		return "redirect:/manager/noti";
	}
}