package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component  //memoryMemberRepository라는 이름으로 등록(클래스 이름에서 첫글자 소문자로)
public class MemoryMemberRepository implements MemberRepository{

    //실무에서는 동시성 이슈가 있으니까 concurrent hash 사용해야 함 여기서는 그냥 hashmap
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
