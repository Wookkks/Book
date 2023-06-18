package book.check.repository;

import java.util.List;
import java.util.Optional;

import book.check.domain.WithBook;

public interface WithBookRepository {
	
	// 나눔글 등록
	WithBook saveWithBook(WithBook withBook);
	
	// 나눔글 등록번호로 조회
	Optional<WithBook> findByNo(Long w_no);
	
	// 나눔글 전체 목록 조회
	List<WithBook> findAll();
	
	// 나눔글 제목으로 검색
	List<WithBook> search(String title);
	
	// 나눔글 수정
	WithBook updateWithBook(Long w_no, WithBook withBook);
	
	WithBook updateYN(Long w_no, WithBook withBook);
	
	// 나눔글 비밀번호 
	WithBook findPwd(Long w_no);
	
	Optional<WithBook> pwd(String w_pwd); 
	
	// 나눔글 삭제
	void deleteWithBook(Long w_no);
	
}
