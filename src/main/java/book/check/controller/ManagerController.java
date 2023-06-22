package book.check.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import book.check.domain.How;
import book.check.domain.Noti;
import book.check.service.HowService;
import book.check.service.NotiService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/manager")
public class ManagerController {
	
	private final NotiService notiService;
	private final HowService howService;

	public ManagerController(NotiService notiService, HowService howService) {
		this.notiService = notiService;
		this.howService = howService;
	}
	
	//공지사항
	@PostMapping("/noti")
	public String noti (Model model) {
		List<Noti> noti = notiService.findAll();
		model.addAttribute("noti", noti);
		return "redirect:/manager/noti";
	}
	
	//공지사항
	@GetMapping("/noti")
	public String notiList (Model model) {
		List<Noti> noti = notiService.findAll();
		model.addAttribute("noti", noti);
		return "manager/m_noti";
	}
	
	//공지사항 등록 폼
	@GetMapping("/noti/add")
	public String notiForm(Model model) {
		List<Noti> noti = notiService.findAll();
		model.addAttribute("notiAdd", noti);
		return "manager/m_noti_addForm";
	}
	
	//공지사항 등록 	
	@PostMapping("/noti/add")
	public String notiCreate(@ModelAttribute Noti noti) { //, Model model
		notiService.saveNoti(noti);
		return "redirect:/manager/noti"; 
	}

	// 공지사항 상세
	@GetMapping("/noti/detail{n_no}")
	public String notiDetail(@PathVariable Long n_no, Model model) {
		List<Noti> noti = notiService.findAll();
		model.addAttribute("notiDetail", noti);
		model.addAttribute("noti", notiService.findByNo(n_no).get());
		return "manager/m_noti_detail";
	}
	
	//공지사항 수정 폼
	@GetMapping("/noti/edit{n_no}")
	public String notiEditForm(@PathVariable Long n_no, Model model) {
		List<Noti> noti = notiService.findAll();
		model.addAttribute("notiEdit", noti);
		model.addAttribute("noti", notiService.findByNo(n_no).get());
		return "manager/m_noti_editForm";
	}
	
	//공지사항 수정  
	@PostMapping("/noti/edit{n_no}")
	public String notiEdit(@PathVariable Long n_no, @ModelAttribute Noti noti, Model model) {
		model.addAttribute("noti", notiService.updateNoti(n_no, noti));
		return "redirect:/manager/noti/detail{n_no}"; 
	}
	
	//공지사항 삭제(공지상세에서 삭제)
	@PostMapping("/noti/del")
	public String notiDelete(@RequestParam("n_no") Long n_no) {
	    notiService.deleteNoti(n_no);
	    return "redirect:/manager/noti";
	}
	
	//공지사항 삭제(공지리스트에서 삭제, 모달X)
	@GetMapping("/noti/list/delprocess{n_no}")
	public String notiListDeleteProcess(@RequestParam("n_no") Long n_no) {
		notiService.deleteNoti(n_no);
		return "redirect:/manager/noti";
	}
	
	//how
	//어때책첵
	@GetMapping("/how")
	public String how(@RequestParam(required = false) String h_month, Model model) {
		if(h_month == null) {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			h_month = sdf.format(date);
		}
		List<Noti> noti = notiService.findAll();
		List<How> how = howService.findAll(h_month);
		model.addAttribute("noti", noti);
		model.addAttribute("how", how);
		return "manager/m_how";
	}
	
	//어때책첵 수정 폼
	@GetMapping("/how/edit/{h_no}")
	public String howEditForm(@PathVariable Long h_no, Model model) {
		List<Noti> noti = notiService.findAll();
		model.addAttribute("noti", noti);
		model.addAttribute("how", howService.findByNo(h_no).get());
		return "manager/m_how_editForm";
	}
	
	//어때책첵 수정
	@PostMapping("/how/edit")
	public String howEdit(@ModelAttribute How how) {
		howService.updateHow(how.getH_no(), how);
		return "redirect:/manager/how";
	}
	
	//어때책첵 등록 폼
	@GetMapping("/how/add")
	public String howForm(Model model, String h_month) {
		List<How> how = howService.findAll(h_month);
		List<Noti> noti = notiService.findAll();
		model.addAttribute("noti", noti);
		model.addAttribute("howForm", how);
		return "manager/m_how_addForm";
	}
	
	//어때책첵 등록
	@PostMapping("/how/add")
	public String howCreate(@ModelAttribute How how) { //, Model model
		howService.saveHow(how);
		return "redirect:/manager/how";
	}
	
}