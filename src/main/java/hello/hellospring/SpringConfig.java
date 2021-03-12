package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepositiry memberRepositiry;

    @Autowired
    public SpringConfig(MemberRepositiry memberRepositiry) {
        this.memberRepositiry = memberRepositiry;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepositiry);
    }

//    스프링 빈에 직접 등록
//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }
}
