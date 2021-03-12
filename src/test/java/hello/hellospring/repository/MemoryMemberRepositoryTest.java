package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repositiry = new MemoryMemberRepository();

    @AfterEach
    public void afterEaach() {
        repositiry.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repositiry.save(member);

        Member result = repositiry.findById(member.getId()).get();
        Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repositiry.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repositiry.save(member2);

       Member result = repositiry.findByName("spring1").get();

       assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repositiry.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repositiry.save(member2);

        List<Member> result = repositiry.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
