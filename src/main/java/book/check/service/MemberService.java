package book.check.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import book.check.domain.Member;
import book.check.repository.MemberRepository;

@Service
public class MemberService {
	
	 @Autowired
	 MemberRepository memberRepository;

	//가입신청
	public Member saveMember(Member member) {
		return memberRepository.saveMember(member);
	}
}