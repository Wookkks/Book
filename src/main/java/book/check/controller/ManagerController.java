package book.check.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
		
	//공지사항 등록 폼
	@GetMapping("/noti/add")
	public String notiForm() {
		log.info("[GET] notiForm 실행");
		return "manager/m_noti_addForm";
	}
	
	//공지사항 등록  	
	//에러발생 Column 'n_name' cannot be null
	//공지사항 등록에서 n_name은 어떻게?
	@PostMapping("/noti/add")
	public String notiCreate(@ModelAttribute Noti noti, Model model, RedirectAttributes redirect) {
		log.info("[GET] notiCreate 실행");
		notiService.saveNoti(noti);
		redirect.addAttribute("n_no", noti.getN_no());
		return "redirect:/manager/m_noti";
	}

	//공지사항 수정 폼
	@GetMapping("/noti/edit{n_no}")
	public String notiEditForm(@PathVariable Long n_no, Model model) {
		log.info("[GET] notiEditForm() 실행");
		model.addAttribute("notiEdit", notiService.findByNo(n_no).get());
		return "manager/m_noti_editForm";
	}
	
	//공지사항 수정
	@PostMapping("/noti/edit{n_no}")
	public String notiEdit(@PathVariable Long n_no, @ModelAttribute Noti noti, Model model, RedirectAttributes redirect) {
		log.info("[POST] notiEdit() 실행");
		model.addAttribute("noti", notiService.updateNoti(n_no, noti));
		redirect.addAttribute("noti", n_no);
		return "redirect:/manager/m_noti";
	}

	
	//공지사항 삭제
	@PostMapping("/noti")
	public String notiDelete(Long n_no) {
		log.info("[POST] notiDelete 실행");
		notiService.deleteNoti(n_no);
		return "manager/m_noti";
	}
	
}