package book.check.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpInc;
import org.springframework.stereotype.Service;

import book.check.domain.WithBook;
import book.check.repository.WithBookRepository;

@Service
public class WithBookService {
	
	@Autowired
	WithBookRepository withBookRepository;
	
	public WithBook saveWithBook(WithBook withBook) {
		return withBookRepository.saveWithBook(withBook);
	}
	
	public Optional<WithBook> findByNo(Long w_no){
		return withBookRepository.findByNo(w_no);
	}
	
	public List<WithBook> findAll(){
		return withBookRepository.findAll();
	}
	
	public List<WithBook> search(String title){
		return withBookRepository.search(title);
	}
	
	public WithBook updateWithBook(Long w_no, WithBook withBook) {
		return withBookRepository.updateWithBook(w_no, withBook);
	}
	
	public WithBook findPwd(Long w_no) {
		return withBookRepository.findPwd(w_no);
	}
	
	public Optional<WithBook> pwd(String w_pwd){
		return withBookRepository.pwd(w_pwd);
	}
	
	public WithBook updateYN(Long w_no, WithBook withBook) {
		return withBookRepository.updateYN(w_no, withBook);
	}
}