package book.check.repository;

import book.check.domain.Member;

public interface MemberRepository {
	
	// 가입신청
	Member saveMember(Member member);
}