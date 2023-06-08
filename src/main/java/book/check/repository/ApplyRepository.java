package book.check.repository;

import java.util.List;

import book.check.domain.Apply;

public interface ApplyRepository {
	
	// 요청글 등록
	Apply saveApply(Apply apply);
	
	// 요청글 목록
	List<Apply> findAll();
}
