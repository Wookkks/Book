package book.check.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	@PostMapping("/noti")
	public String noti (Model model) {
		log.info("[GET] noti 실행");
		List<Noti> noti = notiService.findAll();
		model.addAttribute("noti", noti);
		return "manager/m_noti";
	}
	
	//공지사항
	@GetMapping("/noti")
	public String notiList (Model model) {
		log.info("[GET] noti 실행");
		List<Noti> noti = notiService.findAll();
		model.addAttribute("noti", noti);
		return "manager/m_noti";
	}
	
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

	// 공지사항 상세
	@GetMapping("/noti/detail{n_no}")
	public String notiDetail(@PathVariable Long n_no, Model model) {
		log.info("[GET] notiDetail() 실행");
		List<Noti> noti = notiService.findAll();
		model.addAttribute("notiDetail", noti);
		model.addAttribute("noti", notiService.findByNo(n_no).get());
		return "manager/m_noti_detail";
	}
	
	//공지사항 수정 폼
	@GetMapping("/noti/edit{n_no}")
	public String notiEditForm(@PathVariable Long n_no, Model model) {
		log.info("[GET] notiEditForm() 실행");
		List<Noti> noti = notiService.findAll();
		model.addAttribute("notiEdit", noti);
		model.addAttribute("noti", notiService.findByNo(n_no).get());
		return "manager/m_noti_editForm";
	}
	
	//공지사항 수정  
	@PostMapping("/noti/edit{n_no}")
	public String notiEdit(@PathVariable Long n_no, @ModelAttribute Noti noti, Model model) {
		log.info("[POST] notiEdit() 실행");
		model.addAttribute("noti", notiService.updateNoti(n_no, noti));
		return "redirect:/manager/noti/detail{n_no}"; 
	}
	
	//공지사항 삭제(공지상세에서 삭제)
	@PostMapping("/noti/del")
	public String notiDelete(@RequestParam("n_no") Long n_no) {//,Model model
	    log.info("[POST] notiDelete 실행");
	    notiService.deleteNoti(n_no);
	    return "redirect:/manager/noti";
	}
	
	//공지사항 삭제(공지리스트에서 삭제, 모달X)
	@GetMapping("/noti/list/delprocess{n_no}")
	public String notiListDeleteProcess(@RequestParam("n_no") Long n_no) {
		log.info("[GET] notiListDeleteProcess 실행");
		notiService.deleteNoti(n_no);
		return "redirect:/manager/noti";
	}
	
}