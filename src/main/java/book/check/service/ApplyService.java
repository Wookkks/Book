package book.check.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import book.check.domain.Apply;
import book.check.repository.JdbcApplyRepository;

@Service
public class ApplyService {
	
	@Autowired
	JdbcApplyRepository applyRepository;
	
	// 나눔신청
	public Apply saveApply(Apply apply) {
		return applyRepository.saveApply(apply);
	}
	
	// 신청목록
	public List<Apply> findAll(){
		return applyRepository.findAll();
	}
}
