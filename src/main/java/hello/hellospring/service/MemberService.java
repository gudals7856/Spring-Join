package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepositiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepositiry memberRepositiry;

    // 직접 new 하지 않고 외부에서 넣어줌
    public MemberService(MemberRepositiry memberRepositiry) {
        this.memberRepositiry = memberRepositiry;
    }

    /*
    회원 가입
     */
    public Long join(Member member) {
        vaildateDuplicateMember(member);    // 중복회원 검증
        memberRepositiry.save(member);
        return member.getId();
    }

    // null이 아니라 값이 있으면 동작함
    private void vaildateDuplicateMember(Member member) {
        memberRepositiry.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                });
    }

    /*
    전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepositiry.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepositiry.findById(memberId);
    }
}
