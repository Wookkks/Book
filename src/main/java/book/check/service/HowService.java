package book.check.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import book.check.domain.How;
import book.check.repository.JdbcHowRepository;

@Service
public class HowService {
	
	private final JdbcHowRepository howRepository;
	
	public HowService(JdbcHowRepository howRepository) {
		this.howRepository = howRepository;
	}
	
	public How saveHow(How how) {
		return howRepository.saveHow(how);
	}
	
	public Optional<How> findByNo(Long h_no) {
		return howRepository.findByNo(h_no);
	}
	
	public How updateHow(Long h_no, How how) {
		return howRepository.updateHow(h_no, how);
	}
	
	public List<How> findAll(String h_month) {
		return howRepository.findAll(h_month);
	}
}