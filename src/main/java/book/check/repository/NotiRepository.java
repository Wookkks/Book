package book.check.repository;

import java.util.List;
import java.util.Optional;

import book.check.domain.Noti;

public interface NotiRepository {
	//공지등록
	Noti saveNoti(Noti noti);
	
	//공지 등록번호로 조회
	Optional<Noti> findByNo(Long notiNo);
	
	//공지 제목으로 조회
	Optional<Noti> findByTitle(String notiTitle);
	
	//공지 전체목록
	List<Noti> findAll(); 
	 
	//공지수정
	Noti updateNoti(Long n_no, Noti updateNoti);
	
	//공지삭제
	void deleteNoti(Long n_no);
	
	
	
}
