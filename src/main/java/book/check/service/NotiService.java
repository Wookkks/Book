package book.check.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import book.check.domain.Noti;
import book.check.repository.NotiRepository;

@Service
public class NotiService {
	// 1. 생성자 주입
	// @Autowired
	// interface NotiRepository
	//	private final NotiRepository notiRepository;

	//	public NotiService(NotiRepository notiRepository) {
	//		this.notiRepository = notiRepository;

	// 2. 필드주입
	@Autowired
	NotiRepository notiRepository;

	// 공지등록
	public void saveNoti(Noti noti) {
		notiRepository.saveNoti(noti);
	}

	// 공지 등록번호로 조회
	Optional<Noti> findByNo(Long n_no) {
		return notiRepository.findByNo(n_no);
	}

	// 공지 제목으로 조회
	Optional<Noti> findByTitle(String n_title) {
		return notiRepository.findByTitle(n_title);
	}

	// 공지 전체목록
	List<Noti> findAll() {
		return notiRepository.findAll();
	}

	// 공지수정
	Noti updateNoti(Long n_no, Noti update_noti) {
		return notiRepository.updateNoti(n_no, update_noti);
	}

	// 공지삭제
	void deleteNoti(Long n_no) {
		notiRepository.deleteNoti(n_no);
	}
}