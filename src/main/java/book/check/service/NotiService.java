package book.check.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import book.check.domain.Noti;
import book.check.repository.NotiRepository;

@Service
public class NotiService {

	@Autowired
	NotiRepository notiRepository;

	// 공지등록
	public Noti saveNoti(Noti noti) {
		return notiRepository.saveNoti(noti);
	}

	// 공지 등록번호로 조회
	public Optional<Noti> findByNo(Long n_no) {
		return notiRepository.findByNo(n_no);
	}
	
	// 공지 제목으로 조회
	public Optional<Noti> findByTitle(String n_title) {
		return notiRepository.findByTitle(n_title);
	}

	// 공지 전체목록
	public List<Noti> findAll() {
		return notiRepository.findAll();
	}

	// 공지수정
	public Noti updateNoti(Long n_no, Noti update_noti) {
		return notiRepository.updateNoti(n_no, update_noti);
	}

	// 공지삭제
	public void deleteNoti(Long n_no) {
		notiRepository.deleteNoti(n_no);
	}
}
