package book.check.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import book.check.domain.Apply;
import book.check.repository.ApplyRepository;

@Service
public class ApplyService {
	
	@Autowired
	ApplyRepository applyRepository;
	
	// 나눔신청
	public Apply saveApply(Apply apply) {
		return applyRepository.saveApply(apply);
	}
	
	// 신청목록
	public List<Apply> findAll(){
		return applyRepository.findAll();
	}
}
