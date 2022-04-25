package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
//@Qualifier("mainDiscountPolicy") //"mainDiscountPolicy"는 문자이기 떄문에 컴파일에서 걸릴 수 없어 오타나면 캐치 못함
@MainDiscountPolicy                //그래서 직접 어노테이션 만들어주기
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade()== Grade.VIP){
            return price * discountPercent / 100;
        }else{
            return 0;
        }
    }

    public int getDiscountPercent() {
            return discountPercent;
    }
}
