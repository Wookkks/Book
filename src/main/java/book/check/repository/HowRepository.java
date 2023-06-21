package book.check.repository;

import java.util.List;
import java.util.Optional;

import book.check.domain.How;

public interface HowRepository {
	
	// 어때책첵 글번호로 조회
	Optional<How> findByNo(Long h_no);
	
	// 어때책첵 등록
	How saveHow(How how);
	
	// 어때책첵 수정
	How updateHow(Long h_no, How how);
	
	// 어때책첵 월별 목록
	List<How> findAll(String h_month);
	
}
