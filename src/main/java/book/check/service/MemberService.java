package book.check.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import book.check.domain.Member;
import book.check.repository.MemberRepository;

@Service
public class MemberService {
	
	//1. 생성자 주입방식
	@Autowired
	private final MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	//2. 필드주입 방식
	// @Autowired
	// MemberRepository memberRepository;

	//가입신청
	public Member saveMember(Member member) {
		return memberRepository.saveMember(member);
	}
}