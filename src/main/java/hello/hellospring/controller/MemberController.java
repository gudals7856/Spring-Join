package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// 스프링 컨테이너가 관리하게 됨
@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    // 회원 컨트롤러에서 회원을 실제로 등록하는 기능 구현
    @PostMapping("members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        
        memberService.join(member);

        return "redirect:/";     // 회원 가입이 끝나면 홈 화면으로 돌려보냄
    }

    // 회원 컨트롤러에서 회원 조회 기능
    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        
        // members 리스트 전체를 모델에 담아서 view에 넘긴다
        model.addAttribute("members", members);
        return "members/memberlist";
    }
}
