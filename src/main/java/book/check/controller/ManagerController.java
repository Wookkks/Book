package book.check.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import book.check.domain.Noti;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/manager")
public class ManagerController {
	//<관리자>
	
//	작성 시작 부//Service단 생성예정. Repository랑 둘 다 의존성 주입 예정.  
//	private final NotiRepository notiRepository;
//	
//	@Autowired
//	public NotiController(NotiRepository notiRepository) {
//		this.notiRepository = notiRepository;
//	}
	
	//공지사항
	@GetMapping("/noti")
	public String noti () {
		return "manager/m_noti";
	}
		
	//공지사항 상세 X //공지사항 페이지에 목록들 전체 뜨고 각각 수정/삭제버튼 있음
	
	//공지사항 등록 폼
	@GetMapping("add/noti")
	public String notiForm() {
	
		return "manager/m_noti_addForm";
	}
	
	//공지사항 등록
	@PostMapping("add/noti")
	public String notiCreate(@ModelAttribute Noti noti, RedirectAttributes redirectAttributes, Model model) {
		
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