package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements  MemberRepositiry{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
       // store를 루프를 돌면서 하나가 찾아지면 반환 없으면 Optional에 null이 포함되어 반환
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        // store에 있는 멤버들 반환
        return new ArrayList<>(store.values());
    }

    // 테스트 케이스 하나하나가 끝날 때마다 실행된다. store에 저장된 내용을 비워준다.
    public void clearStore() {
        store.clear();
    }
}

// 동작하는지 확인하기 위해 test케이스 작성!